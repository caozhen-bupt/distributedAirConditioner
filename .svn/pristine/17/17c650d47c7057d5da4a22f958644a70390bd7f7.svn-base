package ui;

import java.net.*;
import java.util.Scanner;
import java.io.*;
import net.sf.json.JSONObject;

public class SlaveSend {
	
	public static JSONObject post(String ip, JSONObject obj) throws IOException{

		try{
			
			  URL url = new URL(ip);//"http://10.204.32.41:8080/server/Server");
			  HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	
			  httpURLConnection.setDoInput(true);
			  httpURLConnection.setDoOutput(true);    // ���ø������ǿ��������
			  httpURLConnection.setRequestMethod("POST"); // ��������ʽ
			  httpURLConnection.setRequestProperty("charset", "utf-8");
	
			  PrintWriter pw = new PrintWriter(new BufferedOutputStream(httpURLConnection.getOutputStream()));
//			  pw.write("name=welcome");          // �������������ݣ��൱�ڷ�����ݸ��������
//			  pw.write("&age=14");
			  if(obj==null)
			  {
				  System.out.println("objnull");
				  return new JSONObject();
			  }
			  pw.write(obj.toString());
			  pw.flush();
			  pw.close();
	
			  BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
			  String line = null;
			  StringBuilder sb = new StringBuilder();
			  while ((line = br.readLine()) != null) {  // ��ȡ���
				  sb.append(line);
			  }
			  String temp=sb.toString();
			  if(!temp.equals("")){
				  JSONObject jo = JSONObject.fromObject(temp);
				  String x = jo.getString("type");
				  if(x.equals("connected")||x.equals("poll_res")){
//					  System.out.println(jo.toString());
					  return jo;
				  }
				  else if(x.equals("reject")){
					 System.out.println("rejected!!!!");
					 return new JSONObject();
				  }
				  else{
					 System.out.println("unknown!!!!");
					 return new JSONObject();
				  }
			  }
			  System.out.println("null!!!!");
			  br.close();
			  httpURLConnection.disconnect();
			  return new JSONObject();
		} catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new JSONObject();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new JSONObject();
        }
		
	}
	
/*
	public static void main(String args[])//main�����ﶼ�ǲ��Բ��֣�����������post()������json����Ϳ��Ե�����
											//����postʱ�򣬰�������json�Ĵ�����ʽ�����þ�����
	{
		try {
			  
			  int choice=0;
			  String ip="http://10.203.28.136:8080/AirC/Server";	//����������ip��ַ
			  Scanner sc=new Scanner(System.in);
			  
			  while(choice!=-1){									//���ǲ��Բ���
				  
				  JSONObject obj = new JSONObject();
				  JSONObject subobj = new JSONObject();
				  System.out.println("选择：");				  
				  choice=sc.nextInt();				  
				  
				  switch(choice){				  
				  case 0:				  	
					  //connect      		  				  			  
					  subobj.element("room_id", 1);				  	          
					  subobj.element("init_temp", 25);				  		
					  obj.element("type", "connect");      				  	  
					  obj.element("content", "\""+subobj.toString()+"\"");
					  //System.out.println(obj.toString());	
					  SlaveSend.post(ip,obj);
					  break;
					  
				  case 1:				  		
					  //poll				  			
					  subobj.element("room_id", 1);	
					  subobj.element("type", 1);	
					  subobj.element("curr_temp", 26);
					  obj.element("type", "poll");      				  	        
					  obj.element("content", "\""+subobj.toString()+"\"");
					  SlaveSend.post(ip,obj);
					  break;
				  
				  case 2:				  	     
					  //tune_temp				  			  
					  subobj.element("room_id", 1);				  	          
					  subobj.element("targ_temp", 40);				  			  
					  obj.element("type", "tune_temp");      				  	         
					  obj.element("content", "\""+subobj.toString()+"\"");
					  SlaveSend.post(ip,obj);
					  break;
					  
				  case 3:				  	 
					  //tune_speed				  		
					  subobj.element("room_id", 1);				  	          
					  subobj.element("speed", 2);				  	  
					  obj.element("type", "tune_speed");      				  	  
					  obj.element("content", "\""+subobj.toString()+"\"");
					  SlaveSend.post(ip,obj);
					  break;
					  
				  case 4:
					  //disconnected	
					  subobj.element("room_id", 1);		
		  			  obj.element("type", "disconnect");      
		  	          obj.element("content", "\""+subobj.toString()+"\"");
		  	          SlaveSend.post(ip,obj);
		  	          break;
		  	          
				  } 
				  				 
			  }
			  sc.close();
	    
		} catch (Exception e) {
			   e.printStackTrace();
		}	
	}
*/
}	
	
