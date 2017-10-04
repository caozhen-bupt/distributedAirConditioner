package Air;


public class Controller {
	
	private MyTimer myTimer;
	public Controller(){
		//判断时间 设置模式 数据库存取
		Constant con = new Constant();
		con.config();
		Master.getInstance().setMaster(true, 0);
		Room r;
		for(int i = 1; i<5 ; i++){
			String num = String.valueOf(i);
			//String userID, String name, double curTemp, double tarTemp, int windSpeed, int state, double cost, int reNumber
			r = new Room(null, num, Constant.defaultTemp, Constant.defaultSpeed, 0, 0,-1);
			Master.getInstance().addRoomList(r);
		}
		myTimer = new MyTimer();
	}


}