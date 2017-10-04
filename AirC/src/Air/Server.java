package Air;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


    public void init() throws ServletException
    {
    	
    	new MainUI();
		
    }


    public void destroy()
    {
        // 什么也不做
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response); 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {

		    request.setCharacterEncoding("utf-8");
		    response.setContentType("text/json; charset=UTF-8");
		    response.addHeader("Access-Control-Allow-Origin", "*");

	        String acceptjson = "", type = "", content = "";
	        try {  
	            BufferedReader br = new BufferedReader(new InputStreamReader(  
	                    (ServletInputStream) request.getInputStream(), "utf-8"));  
	            StringBuffer sb = new StringBuffer("");  
	            String temp;  
	            while ((temp = br.readLine()) != null) {  
	                sb.append(temp);  
	            }  
	            br.close();  
	            acceptjson = sb.toString();  
	            if (acceptjson.equals("")) {  
	            	JSONObject new1 = new JSONObject();
	            	new1.element("type", "");
	            	new1.element("content", ""); 
	                response.getOutputStream().write(new1.toString().getBytes("UTF-8"));
	                
	            }  
	            else
	            {
//TODO	            	
	            	
	            	JSONObject jo = JSONObject.fromObject(acceptjson);  
	            	type = jo.getString("type");
	            	content = jo.getString("content");
	            	if(type.equals("connect"))
	            	{
	            		JSONObject subj = JSONObject.fromObject(content);  
	            		String room_id, init_temp;
//	            		int roomNumInt;
	            		room_id = subj.getString("room_id");
	            		
	            		//TODO
            			//发connected：
	            		 /* 上面这个函数就是把相应的东西打包成json，
	            		 * 	"status": (0 for IDLE, 1 for RUNNING, 2 for SUSPENDED)
							"curr_temp": (number),
							"targ_temp": (number),
							"speed": (number),
							"fee": (number),
							"max_temp": (number), // 温度范围
							"min_temp": (number),
							"mode": (0 for COOLING, 1 for HEATING) 
							写法和之前的那个tojson一样，然后把type改成"connected"
	            		 *  response.getOutputStream().write(newj.toString().getBytes("UTF-8"));        		
	            		 */
	            		if(!Master.getInstance().setRoomOpen(room_id)){
	            			//发reject
		            		 JSONObject newj = new JSONObject();
		            		 newj.element("type", "reject");
		            		 JSONObject new1 = new JSONObject();
		            		 new1.element("err_msg", "room_not_checked_in");
		            		 String content1 = new1.toString();
		            		 newj.element("content","\""+ content1+"\"");
		            		 System.out.println(newj.toString());
		            		 response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
	            		}
	            		else
	            		{
	            			init_temp = subj.getString("init_temp");
		            		System.out.println(jo.toString());
		            		Master.getInstance().setRoomCurTemp(room_id, Double.parseDouble(init_temp));
	            			
		            		JSONObject newj;
	            			newj= Master.getInstance().roomToJson_connect(room_id);
	            			System.out.println(newj.toString());
	            			response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
	            		}
	            		
	            		
	            		//以下是测试
/*	            		JSONObject newj = new JSONObject();
	            		newj.element("type", "connected");
	    				JSONObject subj1 = new JSONObject();
	    				subj1.element("status", "0");
	    				subj1.element("curr_temp", "20");
	    				subj1.element("targ_temp", "20");
	    				subj1.element("speed", "0");
	    				subj1.element("fee", "0");
	    				subj1.element("max_temp", "50");
	    				subj1.element("min_temp", "0");
	    				subj1.element("mode", "0");
	    				String content1 = subj1.toString();
	    				newj.element("content", content1);
    	          		System.out.println(newj.toString());
    	          		response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
*/
	            	}
	            	else if(type.equals("poll"))
	            	{

	            		System.out.println(jo.toString());
	            		JSONObject subj = JSONObject.fromObject(content);  
	            		String room_id,type1,curr_temp;
	            		room_id = subj.getString("room_id");
	            		type1 = subj.getString("type");
	            		curr_temp = subj.getString("curr_temp");
	            		if(type1.equals("1")){
	            			Master.getInstance().setRoomCurTemp(room_id, Double.parseDouble(curr_temp));
	            		}	            		
	            		System.out.println("..................................."+curr_temp+"..............................");
//TODO
	            		JSONObject newj1 = Master.getInstance().roomToJson_poll(room_id);
	            		System.out.println(newj1.toString());
	            		response.getOutputStream().write(newj1.toString().getBytes("UTF-8"));

	            	}
	            	else if(type.equals("tune_temp"))
	            	{
	            		System.out.println(jo.toString());
	            		JSONObject subj = JSONObject.fromObject(content);  
	            		String room_id,targ_temp;
	            		room_id = subj.getString("room_id");
	            		targ_temp = subj.getString("targ_temp");
	            		
	            		//TODO 这里调用将温度数据上传的函数
	            		if(Master.getInstance().getRoomOpen(room_id)){
	            			Master.getInstance().setRoomTemp(room_id, targ_temp);
	            			Master.getInstance().updateSpecifiedRoom(room_id);
	            		}
	            		
	            		response.setStatus(200);
	            	}
	            	else if(type.equals("tune_speed"))
	            	{
	            		System.out.println(jo.toString());
	            		JSONObject subj = JSONObject.fromObject(content);  
	            		String room_id, speed;
	            		room_id = subj.getString("room_id");
	            		speed = subj.getString("speed");
	            		//TODO 这里调用将风速数据上传的函数
	            		
	            		if(Master.getInstance().getRoomOpen(room_id)){
	            			Master.getInstance().setRoomSpeed(room_id, speed);
	            			//Master.getInstance().updateSpecifiedRoom(room_id);
	            		}
	            		
	            		response.setStatus(200);
	            	}
	            	else if(type.equals("disconnect"))
	            	{
	            		
	            		System.out.println(jo.toString());
	            		JSONObject subj = JSONObject.fromObject(content);  
	            		String room_id;
	            		room_id = subj.getString("room_id");
	            		//TODO 这里调用子函数关机函数
	            		Master.getInstance().setOpenfalse(room_id);
	            		Master.getInstance().initSlave(room_id);
	            		response.setStatus(200);
	            	}
	            	AdminUI.getInstance().update();//！！！！！！！！！！！！！！更新
	            }
	            
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	           
	        }  
		  }

}

//package Air;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletInputStream;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//import net.sf.json.JSONObject;
//
///**
// * Servlet implementation class Server
// */
//@WebServlet("/Server")
//public class Server extends HttpServlet {
//	private static final long serialVersionUID = 102831973239L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Server() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//
//
//    public void init() throws ServletException
//    {
//    	
//    	System.out.println("======================time++=============================");
//    	Room r;
////		for(int i = 1; i<5 ; i++){
////			String num = String.valueOf(i);
////			 r = new Room(null, num, 20, 18, 0, 0, 0);
////			//(String name, double curTemp, double tarTemp, int windSpeed, int state)
////			
////			Master.getInstance().addRoomList(r);
////		}
//    	new MainUI();
//		
//    }
//
//
//    public void destroy()
//    {
//        // 什么也不做
//    }
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//	//	response.getWriter().write("Hollo servlet");
//		 doPost(request, response); 
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//		      throws ServletException, IOException {
//
//		    request.setCharacterEncoding("utf-8");
//		    response.setContentType("text/json; charset=UTF-8");
//		   // response.setContentType("text/html;charset=UTF-8");  
//	        String acceptjson = "", type = "", content = "";  
//	        try {  
//	            BufferedReader br = new BufferedReader(new InputStreamReader(  
//	                    (ServletInputStream) request.getInputStream(), "utf-8"));  
//	            StringBuffer sb = new StringBuffer("");  
//	            String temp;  
//	            while ((temp = br.readLine()) != null) {  
//	                sb.append(temp);  
//	            }  
//	            br.close();  
//	            acceptjson = sb.toString();  
//	            if (acceptjson.equals("")) {  
//	            	
//	            	JSONObject newj = new JSONObject();
//	            	newj.element("type", type);
//	            	newj.element("content", content); 
//	                response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
//	                
//	            }  
//	            else 
//	            {
//	            	AdminUI.getInstance().update();//！！！！！！！！！！！！！！更新
//	            	JSONObject jo = JSONObject.fromObject(acceptjson);  
//	            	type = jo.getString("type");
//	            	content = jo.getString("content");
//	            	if(type.equals("login"))
//	            	{
//	            		JSONObject subj = JSONObject.fromObject(content);  
//	            		String roomNum, temperature;
//	            		int roomNumInt;
//	            		roomNum = subj.getString("room_id");
//	            		
//	            		temperature = subj.getString("start_temperature");
//	            		System.out.println(jo.toString());
//	            		if(!Master.getInstance().setRoomOpen(roomNum)){
//	            			JSONObject newj = new JSONObject();
//	            			newj.element("type", "reject");
//		            		newj.element("content", content);
//		            		response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
//	    	          		System.out.println(newj.toString());
//	            		}
//	            		else
//	            		{
////	            			Master.getInstance().getDatabase().addDetail(room, action, wind, year, month, day, hour, minute, temperP, temperN, comsume)
//	            			JSONObject newj;
//	            			newj= Master.getInstance().roomToJson(roomNum);
//	    	          		response.getOutputStream().write(newj.toString().getBytes("UTF-8"));        		
//		            		System.out.println(newj.toString());
//	            		}
//
//	            	}
//	            	else if(type.equals("heartbeat"))
//	            	{
//	            		System.out.println(jo.toString());
//	            		JSONObject subj = JSONObject.fromObject(content);  
//	            		String roomNum;
//	            		roomNum = subj.getString("room_id");
//	            		JSONObject newj = Master.getInstance().roomToJson(roomNum);
//    	          		response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
//    	          		System.out.println(newj.toString());
//	            		
//	            	}
//	            	else if(type.equals("temperature_turn"))
//	            	{
//	            		JSONObject subj = JSONObject.fromObject(content);  
//	            		String roomNum,targ_tem;
//	            		roomNum = subj.getString("room_id");
//	            		targ_tem = subj.getString("targ_tem");
//	            		//这里调用将温度数据上传的函数
//	            		if(Master.getInstance().getRoomOpen(roomNum)){
//	            			Master.getInstance().setRoomTemp(roomNum, targ_tem);
//	            			Master.getInstance().updateSpecifiedRoom(roomNum);
//	            		}
//	            		//以下是我测试用
//	            		/*
//		            	*	JSONObject newj = new JSONObject();
//	            		*	newj.element("type", "测试测试");
//	            		*	
//	            		*	newj.element("content", "调温度调温度");
//	            		*	System.out.println(jo.toString());  
//	    	            *    System.out.println(newj.toString());
//	            		*	response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
//	            		*/
//	            	}
//	            	else if(type.equals("speed_turning"))
//	            	{
//	            		JSONObject subj = JSONObject.fromObject(content);  
//	            		String roomNum, targ_sped;
//	            		roomNum = subj.getString("room_id");
//	            		targ_sped = subj.getString("targ_sped");
//	            		//这里调用将风速数据上传的函数
//	            		if(Master.getInstance().getRoomOpen(roomNum)){
//	            			Master.getInstance().setRoomSpeed(roomNum, targ_sped);
//	            			Master.getInstance().updateSpecifiedRoom(roomNum);
//	            		}
//	            		//以下是我测试用
//	            		/*
//	            		*	JSONObject newj = new JSONObject();
//	            		*	newj.element("type", "测试测试");
//	            		*	
//	            		*	newj.element("content", "调风速调风速");
//	            		*	System.out.println(jo.toString());  
//	    	            *   System.out.println(newj.toString());
//	            		*	response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
//	            		**/
//	            	}
//	            	else if(type.equals("logout"))
//	            	{
//	            		
//	            		//这里调用子函数关机函数
//	            		JSONObject subj = JSONObject.fromObject(content);  
//	            		String roomNum;
//	            		roomNum = subj.getString("room_id");
//	            		Master.getInstance().setOpenfalse(roomNum);
//	            		//以下是我测试用
//	            		/*
//		            	*	JSONObject newj = new JSONObject();
//	            		*	newj.element("type", "测试测试");
//	            		*	
//	            		*	newj.element("content", "关机关机");
//	            		*	System.out.println(jo.toString());  
//	    	            *    System.out.println(newj.toString());
//	            		*	response.getOutputStream().write(newj.toString().getBytes("UTF-8"));
//	            		**/
//	            	}
//	            	
//           	
//
//	            }
//	            
//	            
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	           
//	        }  
//		  }
//
//}
