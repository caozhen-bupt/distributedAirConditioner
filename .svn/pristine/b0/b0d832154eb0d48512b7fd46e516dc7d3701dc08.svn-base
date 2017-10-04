package Air;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Constant {

	public static int smallSlide = 5000;
	public static int resourceNum = 3;
	public static int defaultSpeed = 1;	
	public static long systemTime = 0;
	public static double maxTemp = 25;
	public static double minTemp = 18;
	public static double low = 0.333333;
	public static double median = 0.5;
	public static double high = 1;
	public static double naturalDiff = 1.00;
	public static double moneyUnit = 1;
	public static double lowPower = 0.75;
	public static double medianPower = 1;
	public static double highPower = 1.25;
	public static double defaultTemp = 25;
//	public static double defaultTarTemp = 25;
//	public static String r1 = "r1";
//	public static String m1 = "m1";
//	public static String a1 = "a1";
//	public static String userid = "0001";
//	public static int bigSlide = 500;
//	public static double difference = 0.05;
		
	public static long getSystemTime(){
		return systemTime;
	}
	
	public Constant()
	{
		
	}
	
	public boolean config()
	{
		try {
			File file=new File("E:\\彭程程\\ppt\\软件工程\\xj\\AirC\\src\\Air\\config.txt");
            if(file.isFile() && file.exists())
            {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                smallSlide = Integer.parseInt(bufferedReader.readLine());
            	resourceNum = Integer.parseInt(bufferedReader.readLine());
            	defaultSpeed = Integer.parseInt(bufferedReader.readLine());	
            	systemTime = Long.parseLong(bufferedReader.readLine());
            	maxTemp = Double.parseDouble(bufferedReader.readLine());
            	minTemp = Double.parseDouble(bufferedReader.readLine());
            	low = Double.parseDouble(bufferedReader.readLine());
            	median = Double.parseDouble(bufferedReader.readLine());
            	high = Double.parseDouble(bufferedReader.readLine());
            	naturalDiff = Double.parseDouble(bufferedReader.readLine());
            	moneyUnit = Double.parseDouble(bufferedReader.readLine());
            	lowPower = Double.parseDouble(bufferedReader.readLine());
            	medianPower = Double.parseDouble(bufferedReader.readLine());
            	highPower = Double.parseDouble(bufferedReader.readLine());
            	defaultTemp = Double.parseDouble(bufferedReader.readLine());
                read.close();
            }else{
            	System.out.println("找不到指定的文件");
            }
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean writeConfig()
	{
		try {
			File file=new File("E:\\彭程程\\ppt\\软件工程\\xj\\AirC\\src\\Air\\config.txt");
            if(file.isFile() && file.exists()){
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file));
                BufferedWriter bufferedWriter = new BufferedWriter(write);
                String res = new String();
                res += smallSlide;
                res += "\n";
            	res += resourceNum;
            	res += "\n";
            	res += defaultSpeed;
            	res += "\n";
            	res += systemTime;
            	res += "\n";
            	res += maxTemp;
            	res += "\n";
            	res += minTemp;
            	res += "\n";
            	res += low;
            	res += "\n";
            	res += median;
            	res += "\n";
            	res +=  high;
            	res += "\n";
            	res += naturalDiff;
            	res += "\n";
            	res += moneyUnit;
            	res += "\n";
            	res += lowPower;
            	res += "\n";
            	res += medianPower;
            	res += "\n";
            	res += highPower;
            	res += "\n";
            	res += defaultTemp;
            	res += "\n";
            	bufferedWriter.write(res);
            	bufferedWriter.flush();
                write.close();
            }else{
            	System.out.println("找不到指定的文件");
            }
		} catch (Exception e) {
			System.out.println("输出配置文件内容出错");
			e.printStackTrace();
			return false;
		}
		System.out.println(systemTime);
		return true;
	}
	
	public static boolean writelog(String room_id, int action, int wind, double curTemp, double tarTemp, double consume, String time)
	{
		try {
			File file=new File("E:\\彭程程\\ppt\\软件工程\\xj\\AirC\\src\\Air\\log.txt");
			//TODO destination!!
            if(file.isFile() && file.exists()){ //判断文件是否存在
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file,true));
                BufferedWriter bufferedWriter = new BufferedWriter(write);
                String res = new String();
                res += time;
                res += "   房间号：";
                res += room_id;
                res += "   风速：";
                res += wind;
                res += "   当前温度:";
                res += curTemp;
                res += "   目标温度:";
                res += tarTemp;
                res += "   费用:";
                res += consume;
                res += "   动作：";
                switch(action){
                case 1:
                	res += "开机";
                	break;
                case 2:
                	res += "关机";
                	break;
                case 3:
                	res += "到达目标温度";
                	break;
                case 4:
                	res += "调度";
                	break;
                case 5:
                	res += "调风速";
                	break;
                default:
                	res += "null";
                	break;
                }
                res += "\n";
                write.write(res);
                write.close();
            }else{
            	System.out.println("找不到指定的文件");
            }
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
//	public static main(String[] args)
//	{
//		if(Constant.config())System.out.print(smallSlide);
//	}

	
	
}
