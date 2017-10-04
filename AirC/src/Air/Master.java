package Air;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Spring;

import net.sf.json.JSONObject;

public class Master {
	private ArrayList <Room> roomList;
	private boolean open;//false:closed
	private int mode;//0:cooling, 1:heating
	private Database database;
	private String[] resourceArray;
	private double max_temp;
	private double min_temp;
	
	private long systemTime;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String allTimeT;
	private int availRe;
	
	private double defaultTemp;
	
	private static Master m = null; 
	
	private Master(){
		this.availRe=Constant.resourceNum;
		this.roomList = new ArrayList <Room> ();
		this.resourceArray= new String[Constant.resourceNum];
		this.max_temp = Constant.maxTemp;
		this.min_temp = Constant.minTemp;
		for(int i=0 ; i<3; i++){
			this.resourceArray[i]="null";
		}
		database = new Database();
		this.systemTime = Constant.getSystemTime();
//		this.setMode();
		//这里调用查询数据库里时间的函数,setSystemTime
	}


	public static Master getInstance() {  
        if (m == null) {    
            m = new Master();  
        }    
       return m;  
	} 
	//Master.getInstance().setMaster(true,27, 0, Constant.low, Constant.median, Constant.high, 1);
	public void setMaster(boolean open, int mode){
		this.mode = mode;
		this.open = open;
	}
	
	public void initMaster(){
		for(int i=0; i<4; i++){
			this.roomList.get(i).setOpenFalse();
			this.roomList.get(i).setReNumber(-1);
			this.roomList.get(i).setServeTime(0);
			this.roomList.get(i).setState(0);
			this.roomList.get(i).setTarTemp(25);
			this.roomList.get(i).setWindSpeed(0);
		}
		this.availRe=3;
		for(int i=0 ; i<3; i++){
			this.resourceArray[i]="null";
		}
	}
	public void initSlave(String roomID){
		for(int i=0; i < 4; i++){
			if(this.roomList.get(i).getName().equals(roomID)){
				this.roomList.get(i).setReNumber(-1);
				this.roomList.get(i).setServeTime(0);
				this.roomList.get(i).setState(0);
				this.roomList.get(i).setTarTemp(Constant.defaultTemp);
				this.roomList.get(i).setWindSpeed(Constant.defaultSpeed);
			}
		}
	}

	public void updateCurTemp(){
		if(this.open == true){
			Room r;
			for(int i = 0; i < 4; i++){
				r = roomList.get(i);
				if(r != null && r.getOpen() && r.getState()==2)
				 {
					 if((this.mode == 0) && (r.getCurTemp() > r.getTarTemp())){
						 if(r.getWindSpeed() == 0){
							 r.setCurTemp(r.getCurTemp() - Constant.low/(60000/Constant.smallSlide));
							 r.setCost(r.getCost() + Constant.low/(60000/Constant.smallSlide));
							 this.database.addStatCost(r.getName(), Constant.low/(60000/Constant.smallSlide), 0, 0, Constant.low/(60000/Constant.smallSlide), this.timeToString());
						 }
						 else if(r.getWindSpeed() == 1){
							 r.setCurTemp(r.getCurTemp() - Constant.median);
							 r.setCost(r.getCost() + Constant.median/(60000/Constant.smallSlide));
							 this.database.addStatCost(r.getName(), 0, Constant.median/(60000/Constant.smallSlide), 0, Constant.median/(60000/Constant.smallSlide), this.timeToString());
						 }
						 else if(r.getWindSpeed() == 2){
							 r.setCurTemp(r.getCurTemp() - Constant.high / 60);
							 r.setCost(r.getCost() +  Constant.high/(60000/Constant.smallSlide));
							 this.database.addStatCost(r.getName(), 0, 0, Constant.high/(60000/Constant.smallSlide), Constant.high/(60000/Constant.smallSlide), this.timeToString());
						 }
					 }
					 else if((this.mode == 1) && (r.getTarTemp() > r.getCurTemp())){
						 if(r.getWindSpeed() == 0){
							 r.setCurTemp(r.getCurTemp() + Constant.low);
							 r.setCost(r.getCost() + Constant.lowPower * Constant.moneyUnit);
						 }
						 else if(r.getWindSpeed() == 1){
							 r.setCurTemp(r.getCurTemp() + Constant.median);
							 r.setCost(r.getCost() + Constant.medianPower * Constant.moneyUnit);
						 }
						 else if(r.getWindSpeed() == 2){
							 r.setCurTemp(r.getCurTemp() + Constant.high);
							 r.setCost(r.getCost() + Constant.highPower * Constant.moneyUnit);
						 }
					 }
					 
					 
					//将结果放到roomlist的房间中
					for(int j = 0; j < 4; j++){
						if(this.roomList.get(j).getName().equals(r.getName())){
							this.roomList.get(j).setCurTemp(r.getCurTemp());
							this.roomList.get(j).setCost(r.getCost());
						}
					}
					
				}
			}
		}
	}
	
	
	
	public void updateAllRoomState(){
		Room r;
		int update;
		int num;
		for(int i = 0; i < 4; i++){
			r = this.roomList.get(i);
			update = 0;
			num = r.getReNumber();
			if(r.getOpen() && num != -1
					&& ((r.getCurTemp() <= r.getTarTemp() && mode == 0) || (r.getCurTemp() >= r.getTarTemp() && mode == 1))){
				//TODO 到达目标温度
				r.setState(0);
				r.setServeTime(0);
				r.setReNumber(-1);
				this.database.addDetail(r.getName(), 3, r.getWindSpeed(), this.allTimeT, this.year, this.month, this.day, this.hour, this.minute, r.getCurTemp(), r.getTarTemp(), r.getCost());
				this.database.addStatTimes(r.getName(), 1, 0, 1, 0, 0, this.timeToString());
				Constant.writelog(r.getName(), 3, r.getWindSpeed(), r.getCurTemp(), r.getTarTemp(), r.getCost(), this.timeToString());
				this.resourceArray[num] = "null";
				this.availRe++;
				this.roomList.set(i, r);
				update = 1;
				
			}
			else if(r.getOpen() && r.getState() == 0 
					&& ((r.getCurTemp() - 1 >= r.getTarTemp() && mode == 0) || (r.getCurTemp() + 1 <= r.getTarTemp() && mode == 1))){
				this.updateSpecifiedRoom(r.getName());
			}
			if(update == 1 ){
				for(int j = 0; j < 4; j++){
					if(this.roomList.get(j).getState() == 1 ){
						//TODO 调度
						this.resourceArray[num]=this.roomList.get(j).getName();
						this.roomList.get(j).setState(2);
						this.database.addDetail(this.roomList.get(j).getName(), 4, this.roomList.get(j).getWindSpeed(), this.allTimeT, this.year, this.month, this.day, this.hour, this.minute, this.roomList.get(j).getCurTemp(), this.roomList.get(j).getTarTemp(), this.roomList.get(j).getCost());
						Constant.writelog(this.roomList.get(j).getName(), 4, this.roomList.get(j).getWindSpeed(), this.roomList.get(j).getCurTemp(), this.roomList.get(j).getTarTemp(), this.roomList.get(j).getCost(), this.timeToString());
						this.database.addStatTimes(r.getName(), 1, 1, 0, 0, 0, this.timeToString());
						this.availRe--;
						this.roomList.get(j).setReNumber(num);
						break;
					}
				}
			}
		}
	}
	
	public void updateSpecifiedRoom(String rname){
		Room r;
		for(int i = 0; i < 4; i++){
			r = this.roomList.get(i);
			if(r.getOpen() && r.getName().equals(rname) && r.getState() != 2 && this.availRe != 0){
				for(int j = 0; j < 3; j++){
					if( this.resourceArray[j].equals("null") ){
						//TODO 调度
						this.resourceArray[j] = rname;
						r.setReNumber(j);
						r.setState(2);
						this.database.addDetail(r.getName(), 4, r.getWindSpeed(), this.allTimeT, this.year, this.month, this.day, this.hour, this.minute, r.getCurTemp(), r.getTarTemp(), r.getCost());
						Constant.writelog(r.getName(), 4, r.getWindSpeed(), r.getCurTemp(), r.getTarTemp(), r.getCost(), this.timeToString());
						this.database.addStatTimes(r.getName(), 1, 1, 0, 0, 0, this.timeToString());
						this.availRe--;
						this.roomList.set(i, r);
						break;
					}
				}
				break;
			}
			else if(r.getOpen() && r.getName().equals(rname) && r.getState() != 2 && this.availRe == 0){//availRe不变，因为是取代；
				int find = -1;
				String findRoom = "null";
				int minWind = r.getWindSpeed();
				long maxServeTime = 0;
				for(int j = 0; j < 4; j++){
					if(this.roomList.get(j).getWindSpeed() < minWind){
						findRoom = this.roomList.get(j).getName();
						minWind = this.roomList.get(j).getWindSpeed();
						maxServeTime = this.roomList.get(j).getServeTime();
						find = j;
					}
					else if(this.getRoomList().get(j).getWindSpeed() == minWind 
							&& this.getRoomList().get(j).getServeTime() > maxServeTime){
						findRoom = this.roomList.get(j).getName();
						minWind=this.roomList.get(j).getWindSpeed();
						maxServeTime=this.roomList.get(j).getServeTime();
						find = j;
					}
				}
				if(find != -1){
					//TODO 调度
					r.setReNumber(this.roomList.get(find).getReNumber());
					r.setState(2);
					this.database.addDetail(r.getName(), 4, r.getWindSpeed(), this.allTimeT, this.year, this.month, this.day, this.hour, this.minute, r.getCurTemp(), r.getTarTemp(), r.getCost());
					this.database.addStatTimes(r.getName(), 1, 1, 0, 0, 0, this.timeToString());
					Constant.writelog(r.getName(), 4, r.getWindSpeed(), r.getCurTemp(), r.getTarTemp(), r.getCost(), this.timeToString());
					this.roomList.get(find).setState(1);
					this.roomList.get(find).setReNumber(-1);
					this.roomList.set(i, r);
				}
				else{
					r.setState(1);
					r.setReNumber(-1);
					this.roomList.set(i, r);
				}
				break;
			}
		}
	}


	public int getAvailRe() {
		return availRe;
	}

	public void setAvailRe(int availRe) {
		this.availRe = availRe;
	}


	public ArrayList <Room> getRoomList() {
		return this.roomList;
	}


	public void setRoomList(ArrayList <Room> roomList) {
		this.roomList = roomList;
	}
	public void addRoomList(Room r){
		this.roomList.add(r);
	}
	
	
	public boolean getRoomOpen(String roomId){
		for(int i=0; i<4; i++){
			if(this.roomList.get(i).getName().equals(roomId)){
				return this.roomList.get(i).getOpen();
			}
		}
		return false;
	}
	
	
	

	public double getDefaultTemp() {
		return defaultTemp;
	}

	public void setDefaultTemp(double defaultTemp) {
		this.defaultTemp = defaultTemp;
	}


	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean setRoomOpen(String room_id) {
		
		//TODO 开机
		for(int i=0; i<4; i++){
			Room r = this.roomList.get(i);
			if(r.getName().equals(room_id)){
				if(r.setOpen()){
					this.database.addDetail(room_id, 1, r.getWindSpeed(),allTimeT, this.year, this.month, this.day, this.hour, this.minute, r.getCurTemp(), r.getTarTemp(), r.getCost());
					Constant.writelog(r.getName(), 1, r.getWindSpeed(), r.getCurTemp(), r.getTarTemp(), r.getCost(), this.timeToString());
					this.database.addStatTimes(room_id, 1, 0, 0, 1, 0, this.timeToString());
					return true;
				}	
			}
		}
		return false;
	}
//	public Room checkRoom(String id){
//		for(int i = 0; i < 4; i++){
//			if(roomList.get(i).equals(id)){
//				return roomList.get(i);
//			}
//		}
//		return null;
//	}
	public void setRoomCurTemp(String room_id, double curTemp){
		for(int i = 0; i < 4; i++){
			if(this.roomList.get(i).getName().equals(room_id)){
				this.roomList.get(i).setCurTemp(curTemp);
				break;
			}
		}
	}
	
	public void setRoomTarTemp(String roomID, double tarTemp){
		for(int i = 0; i < 4; i++){
			if(this.roomList.get(i).getName().equals(roomID)){
				this.roomList.get(i).setTarTemp(tarTemp);
				break;
			}
		}
	}
	
	public void setRoomTemp(String roomId, String temp) {
		for(int i = 0; i < this.roomList.size(); i++){
			if(this.roomList.get(i).getName().equals(roomId)){
				this.roomList.get(i).setTarTemp(Double.valueOf(temp));
				this.updateSpecifiedRoom(roomId);
				break;
			}
		}
	}
	
	public void setRoomSpeed(String roomId, String speed) {
		//TODO 调风速
		for(int i = 0; i < this.roomList.size(); i++){
			if(this.roomList.get(i).getName().equals(roomId)){
				this.roomList.get(i).setWindSpeed(Integer.valueOf(speed));
				this.updateSpecifiedRoom(roomId);
				this.database.addDetail(roomId, 5, this.roomList.get(i).getWindSpeed(), this.allTimeT, this.year, this.month, this.day, this.hour, this.minute, this.roomList.get(i).getCurTemp(), this.roomList.get(i).getTarTemp(), this.roomList.get(i).getCost());
				Constant.writelog(roomId, 5, this.roomList.get(i).getWindSpeed(), this.roomList.get(i).getCurTemp(), this.roomList.get(i).getTarTemp(), this.roomList.get(i).getCost(), this.timeToString());
				break;
			}
		}
		
	}
	
	public void setOpenfalse(String roomId) {
		for(int i = 0; i < this.roomList.size(); i++){
			Room r = this.roomList.get(i);
			if(r.getName().equals(roomId) && r.setOpenFalse()){
				this.database.addDetail(roomId, 2, r.getWindSpeed(), allTimeT,this.year, this.month, this.day, this.hour, this.minute, r.getCurTemp(), r.getTarTemp(), r.getCost());
				Constant.writelog(roomId, 2, r.getWindSpeed(), r.getCurTemp(), r.getTarTemp(), r.getCost(), this.timeToString());
				this.database.addStatTimes(roomId,  1, 0, 0,0, 1, this.timeToString());
			}
		}
	}
	
	public boolean checkout(String roomID, String userID){
		for(int i = 0; i < this.roomList.size(); i++){
			Room r = this.roomList.get(i);
			if(r.getName().equals(roomID) && r.getUserIn() == true && r.getUserID().equals(userID)){
				if(r.setOpenFalse()){
					this.database.addDetail(roomID, 2, r.getWindSpeed(), allTimeT,this.year, this.month, this.day, this.hour, this.minute, r.getCurTemp(), r.getTarTemp(), r.getCost());
					this.database.addStatTimes(roomID,  1, 0, 0,0, 1, this.timeToString());
				}
				this.database.deleteDetail(Integer.parseInt(roomID));
				r.setUserID(null);
				r.setUserIn(false);
				r.setCost(0);
				r.setServeTime(0);
				return true;
			}
		}
		return false;
	}
	
	public JSONObject roomToJson_connect(String id){
		JSONObject newj = new JSONObject();
		for(int i = 0; i < 4; i++){
			if(this.roomList.get(i).getName().equals(id)){
				newj.element("type", "connected");
				JSONObject subj1 = new JSONObject();
				switch(this.roomList.get(i).getState()){
				case 0:
					subj1.element("status", 0);
					break;
				case 2:
					subj1.element("status", 1);
					break;
				case 1:
					subj1.element("status", 2);
					break;
				default:
					break;
				}
				
				subj1.element("curr_temp", this.roomList.get(i).getCurTemp());
				subj1.element("targ_temp", this.roomList.get(i).getTarTemp());
				subj1.element("speed", this.roomList.get(i).getWindSpeed());
				subj1.element("fee", this.roomList.get(i).getCost());
				subj1.element("max_temp", this.max_temp);
				subj1.element("min_temp", this.min_temp);
				subj1.element("mode", this.getMode());
				String content = subj1.toString();
				newj.element("content","\""+ content+"\"");
			}
		}
		return newj;
	}
	

	
	public JSONObject roomToJson_poll(String id){
		JSONObject newj = new JSONObject();
		for(int i = 0; i < 4; i++){
			if(this.roomList.get(i).getName().equals(id)){
				newj.element("type", "poll_res");
				JSONObject subj1 = new JSONObject();
				switch(this.roomList.get(i).getState()){
				case 0:
					subj1.element("status", 0);
					break;
				case 2:
					subj1.element("status", 1);
					break;
				case 1:
					subj1.element("status", 2);
					break;
				default:
					break;
				}
				
				subj1.element("curr_temp", this.roomList.get(i).getCurTemp());
				subj1.element("targ_temp", this.roomList.get(i).getTarTemp());
				subj1.element("speed", this.roomList.get(i).getWindSpeed());
				subj1.element("fee", this.roomList.get(i).getCost());
				subj1.element("mode", this.getMode());
				String content = subj1.toString();
				newj.element("content", "\""+content+"\"");
			}
		}
		return newj;
	}
	

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(long systemTime) {
		this.systemTime = systemTime;
	}
	public void transLongToTime(long time)
	{  
	    SimpleDateFormat allTimeF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    this.allTimeT = allTimeF.format(new Date(time * 1000L)); 
  
	    SimpleDateFormat yearF = new SimpleDateFormat("yyyy");  
	    SimpleDateFormat monthF = new SimpleDateFormat("MM");  
	    SimpleDateFormat dayF = new SimpleDateFormat("dd");  
	    SimpleDateFormat hourF = new SimpleDateFormat("HH");  
	    SimpleDateFormat minuteF = new SimpleDateFormat("mm"); 
	 
	    this.year = Integer.valueOf(yearF.format(new Date(time * 1000L)));  
	    this.month = Integer.valueOf(monthF.format(new Date(time * 1000L)));  
	    this.day = Integer.valueOf(dayF.format(new Date(time * 1000L)));  
	    this.hour = Integer.valueOf(hourF.format(new Date(time * 1000L)));  
	    this.minute = Integer.valueOf(minuteF.format(new Date(time * 1000L)));
		
	}
	
	public boolean isUserIn(String roomID){
		for(int i = 0; i < 4; i++){
			if(this.roomList.get(i).equals(roomID)){
				if(this.roomList.get(i).getUserIn() == true){
					return true;
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	
	public String timeToString(){
		String time = new String();
		time += Integer.toString(this.year)+'-';
		time += Integer.toString(this.month)+'-';
		time += Integer.toString(this.day)+' ';
		time += Integer.toString(this.hour)+':';
		time += Integer.toString(this.minute);
		return time;
	}
	public void writeSystemTimeToConfig(){
		 try {

			   String content = String.valueOf(this.systemTime);
			   File file = new File("time.txt");
			   // if file doesnt exists, then create it
			   if (!file.exists()) {
			     file.createNewFile();
			   }

			   FileWriter fw = new FileWriter(file.getAbsoluteFile());
			   BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(content);
			   bw.close();


			  } catch (IOException e) {
			   e.printStackTrace();
			  }
	}
}
