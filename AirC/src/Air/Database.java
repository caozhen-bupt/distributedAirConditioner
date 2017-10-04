package Air;
import java.sql.*;
import java.util.Vector;


public class Database {
	static Connection conn;
	static Statement sql;
	static ResultSet res;
	public Database()
	{
		try{
            //call Class.forName to load drive
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动!");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动！");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/ex?useSSL=false";    
        //JDBC's URL ssl
        try {
            conn = DriverManager.getConnection(url,"root","");
            sql = conn.createStatement();
            System.out.println("成功连接到数据库！");
            String create1 = new String("create table if not exists details ("
            		+ "room_id varchar(4),"
            		+ "action int(1),"
            		+ "wind int(1),"
            		+ "allTime datetime,"
            		+ "year int(4),"
            		+ "month int(2),"
            		+ "day int(2),"
            		+ "hour int(2),"
            		+ "minute int(2),"
            		+ "curTemp double(30,10),"
            		+ "tarTemp double(30,10),"
            		+ "consume double(30,10))");
            sql.execute(create1);
            String create2 = new String("create table if not exists users ("
            		+ "uname varchar(6) not null,"
            		+ "password varchar(6),"
            		+ "utype int(1))");
            sql.execute(create2);
            String create4 = new String("create table if not exists statCost ("
            		+ "room_id varchar(4),"
            		+ "cost1 double(30,10),"
            		+ "cost2 double(30,10),"
            		+ "cost3 double(30,10),"
            		+ "costTotal double(30,10),"
            		+ "day date)");
            sql.execute(create4);
            String create5 = new String("create table if not exists statTimes ("
            		+ "room_id varchar(4),"
            		+ "length int(10),"
            		+ "schedule int(10),"
            		+ "reached int(10),"
            		+ "turnon int(10),"
            		+ "turnoff int(10),"
            		+ "day date)");
            sql.execute(create5);
            
            
        } catch (SQLException e){
            e.printStackTrace();
        }
	}
	
	
	public boolean addUser(String name,String password,int type)
	{//0-user 1-admin 2-manager 3-reception
		String add = new String("insert into users values("
				+ "\"" + name +"\"" +" ,"
				+ "\"" + password + "\"" +","
				+ type + ")");
		try {
			sql.execute(add);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean userLogin(String name)
	{
		String query = new String("select * from users where uname = " + "\""+name+"\"");
		try {
			res = sql.executeQuery(query);
			while(res.next())
			{
				int type = res.getInt("utype");
				if(type==0)return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean adminLogin(String name,String password,int type)
	{
		String query = new String("select * from users where uname = " + "\""+name+"\"");
		int utype;
		String upassword;
		try {
			res = sql.executeQuery(query);
			while(res.next())
			{
				utype = res.getInt("utype");
				upassword = res.getString("password");
				
				if(utype==type && upassword.equals(password))return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public Connection getConnection()
	{
        return conn;
    }
	
	public Vector<String> getDetailList(String room_id)
	{
		Vector<String> details = new Vector<String>();
		String query = new String("select * from details where room_id = " + room_id);
		try {
			res = sql.executeQuery(query);
			while(res.next())
			{
				//action 1-turn on 2-turn off 3-begin 4-end 5-dispatch
				//when type == 2,consume should be added,default 0
				String detail = new String();
				detail += res.getTime("allTime")+"-";
				detail += res.getInt("wind") + "-";
				detail += res.getInt("action") + "-";
				detail += res.getInt("year") + "-";
				detail += res.getInt("month") + "-";
				detail += res.getInt("day") + "-";
				detail += res.getInt("hour") + "-";
				detail += res.getInt("minute") + "-";
				detail += res.getDouble("curTemp") + "-";
				detail += res.getDouble("tarTemp") + "-";
				detail += res.getDouble("consume");
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return details;
	}
	
	public boolean addDetail(String room_id, int action, int wind, String allTime,int year, int month, int day, int hour, int minute, double curTemp, double tarTemp, double consume)
	{//action 1-turn on 2-turn off 3-begin 4-end 5-dispatch
		String update = new String("insert into details values("
				+ room_id +" ,"
				+ action +","
				+ wind + ","
				+ "\"" + allTime + "\","
				+ year + ","
				+ month + ","
				+ day + ","
				+ hour + ","
				+ minute + ","
				+ curTemp + ","
				+ tarTemp + ","
				+ consume + ")");
		boolean result = true;
		try {
			sql.executeUpdate(update);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean deleteDetail(int room_id)
	{
		String delete = new String("delete from details where room_id =" + room_id);
		boolean result = true;
		try {
			sql.executeUpdate(delete);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<String> getTotalCost(String date)
	{
		Vector<String> details = new Vector<String>();
		String detail = new String();
		try {
			for(int i = 0;i<3 ;i++)
			{
				String query = new String("select * from statCost where day = "+ "\"" + date +"\"");
				res = sql.executeQuery(query);
				detail = "";
				while(res.next())
				{
					detail = date;
					detail += "/" + res.getString("room_id");
					detail += "/" + res.getDouble("costTotal");
				}
				details.add(detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return details;
	}
	
	public Vector<String> getTotalCost()
	{
		Vector<String> details = new Vector<String>();
		String detail = new String();
		try {
			for(int i = 0;i<3 ;i++)
			{
				String query = new String("select * from statCost");
				res = sql.executeQuery(query);
				detail = "";
				while(res.next())
				{
					detail = res.getDate("day").toString();
					detail += "/" + res.getString("room_id");
					detail += "/" + res.getDouble("costTotal");
				}
				details.add(detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return details;
	}
	
	public Vector<String> getSpeCost(String date)
	{
		Vector<String> details = new Vector<String>();
		String detail = new String();
		try {
			for(int i = 0;i<3 ;i++)
			{
				String query = new String("select * from statCost where day = "+ "\"" + date +"\"");
				res = sql.executeQuery(query);
				detail = "";
				while(res.next())
				{
					detail = date;
					detail += "/" + res.getString("room_id");
					detail += "/" + res.getDouble("cost1");
					detail += "/" + res.getDouble("cost2");
					detail += "/" + res.getDouble("cost3");
				}
				details.add(detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return details;
	}
	
	public Vector<String> getSpeCost()
	{
		Vector<String> details = new Vector<String>();
		String detail = new String();
		try {
			for(int i = 0;i<3 ;i++)
			{
				String query = new String("select * from statCost");
				res = sql.executeQuery(query);
				detail = "";
				while(res.next())
				{
					detail = res.getDate("day").toString();
					detail += "/" + res.getString("room_id");
					detail += "/" + res.getDouble("cost1");
					detail += "/" + res.getDouble("cost2");
					detail += "/" + res.getDouble("cost3");
				}
				details.add(detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return details;
	}
	
	public Vector<String> getAveTimes(String date)
	{
		Vector<String> details = new Vector<String>();
		String detail = new String();
		try {
			for(int i = 0;i<3 ;i++)
			{
				String query = new String("select * from statTimes where day = "+ "\"" + date +"\"");
				res = sql.executeQuery(query);
				detail = "";
				while(res.next())
				{
					detail += res.getString("room_id");
					
					int length = res.getInt("length");
					detail += "/" + length*Constant.smallSlide/3600000 + "小时" + (length*Constant.smallSlide%3600000)/60000 + "分钟";
					detail += "/" + res.getInt("schedule");
					detail += "/" + res.getInt("reached");
					detail += "/" + res.getInt("turnon");
					detail += "/" + res.getInt("turnoff");
					
				}
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return details;
	}
	
	public Vector<String> getAveTimes()
	{
		Vector<String> details = new Vector<String>();
		String detail = new String();
		try {
			for(int i = 0;i<3 ;i++)
			{
				String query = new String("select * from statTimes");
				res = sql.executeQuery(query);
				detail = "";
				while(res.next())
				{
					detail += res.getString("room_id");
					
					int length = res.getInt("length");
					detail += "/" + length*Constant.smallSlide/3600000 + "小时" + (length*Constant.smallSlide%3600000)/60000 + "分钟";
					detail += "/" + res.getInt("schedule");
					detail += "/" + res.getInt("reached");
					detail += "/" + res.getInt("turnon");
					detail += "/" + res.getInt("turnoff");
					
				}
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return details;
	}
	
	public boolean addStatCost(String room_id,  double cost1, double cost2, double cost3,double cost4, String date)
	{
		boolean result = true;
		String query = new String("select * from statCost where day = "+ "\"" + date +"\" and room_id = " + room_id);
		try {
			res = sql.executeQuery(query);
			if(res.next())
			{
				String update = new String("update statCost set cost1 = cost1 + "
					+ cost1 + ", cost2 = cost2 + "
					+ cost2 + ", cost3 = cost3 + "
					+ cost3 + ", costTotal = costTotal + "
					+ cost4 + " where day = "
					+ "\""+date +"\""+ " and room_id = " + room_id);
				sql.executeUpdate(update);
			}
			else{
				String update = new String("insert into statCost values("
						+ room_id +" ,"
						+ cost1 + ","
						+ cost2 + ","
						+ cost3 + ","
						+ cost4 + ","
						+ "\""+date +"\""+ ")");
				sql.executeUpdate(update);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public boolean addStatTimes(String room_id,  int length, int schedule, int reached,int turnon, int turnoff, String date)
	{

		boolean result = true;
		String query = new String("select * from statCost where day = "+ "\"" + date + "\" and room_id = " + room_id);
		try {
			res = sql.executeQuery(query);
			if(res.next())
			{
				String update = new String("update statTimes set length = length + "
		        		+ length + ", schedule = schedule + "
		        		+ schedule + ", reached = reached + "
		        		+ reached + ", turnon = turnon + "
		        		+ turnon + ", turnoff = turnoff + "
		        		+ turnoff + " where day = "
						+ "\""+date +"\""+ " and room_id = " + room_id);
				sql.executeUpdate(update);
			}
			else{
				String update = new String("insert into statTimes values("
						+ room_id + ","
		        		+ length + ","
		        		+ schedule + ","
		        		+ reached + ","
		        		+ turnon + ","
		        		+ turnoff + ","
						+ "\"" + date + "\")");
				sql.executeUpdate(update);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			result = false;
		}
		return result;
	}
	
	
	
	public static void main(String[] args){
		Database db = new Database();
//		db.addDetail("1",1,0,"1997-01-22 17:00:20",1997, 1, 22, 12, 5, 18, 23,100);
//		System.out.println(db.getDetailList("1").get(0));
		db.addStatCost("1", 1, 2, 3, 6, "2017-01-01");
		db.addStatCost("1", 1, 2, 3, 6, "2017-01-01");
		db.addStatCost("2", 1, 2, 3, 6, "2017-01-01");
		db.addStatCost("1", 1, 2, 3, 6, "2017-01-02");
		System.out.println(db.getSpeCost().get(0));
		System.out.println(db.getSpeCost().get(1));
		System.out.println(db.getSpeCost().get(2));
		
		db.deleteDetail(1);
	}
}
