package Air;

public class Room {
	private boolean open;//true:closed,false:opened
	private boolean userIn;//true:somebody in,false:nobody in
	private String userID;
	private String name;
	private double curTemp;
	private double tarTemp;
	private int windSpeed;//0:low, 1:median, 2:high
	private int state;//0:no request, 1:wait, 2:serving
	private double cost;
	private long serveTime;
	private int reNumber;
	
	public Room(){}
	public Room(String userID, String name, double tarTemp, int windSpeed, int state, double cost, int reNumber){
		this.open = false;
		this.userIn = false;
		this.userID = userID;
		this.serveTime=0;
		this.name = name;
		this.tarTemp = tarTemp;
		this.windSpeed = windSpeed;
		this.state = state;
		this.cost = cost;
		this.reNumber = reNumber;
		
	}

//	public Room(Room r){
//		this.open = r.getOpen();
//		this.userIn = r.getUserIn();
//		this.userID = r.getUserID();
//		this.name = r.getName();
//		this.curTemp = r.getCurTemp();
//		this.tarTemp = r.getTarTemp();
//		this.windSpeed = r.getWindSpeed();
//		this.state = r.getState();
//		this.cost = r.getCost();
//		this.serveTime=r.getServeTime();
//		this.reNumber=-1;
//	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCurTemp() {
		return curTemp;
	}

	public void setCurTemp(double curTemp) {
		this.curTemp = curTemp;
	}

	public double getTarTemp() {
		return tarTemp;
	}

	public void setTarTemp(double tarTemp) {
		this.tarTemp = tarTemp;
	}

	public int getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public boolean getOpen() {
		return open;
	}
	public boolean setOpen() {
		if(this.userIn && this.open == false){
			this.open = true;
			return true;
		}
		return false;
		
	}
	public boolean setOpenFalse(){
		if(this.open == true){
			this.open = false;
			this.reNumber = -1;
			return true;
		}
		return false;
	}
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public boolean getUserIn() {
		return userIn;
	}
	public void setUserIn(boolean userIn) {
		this.userIn = userIn;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public long getServeTime() {
		return serveTime;
	}
	public void setServeTime(long serveTime) {
		this.serveTime = serveTime;
	}
	public int getReNumber() {
		return reNumber;
	}
	public void setReNumber(int reNumber) {
		this.reNumber = reNumber;
	}


}
