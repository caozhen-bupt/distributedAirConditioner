package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import net.sf.json.JSONObject;
import java.math.BigDecimal;
import java.text.DecimalFormat;


public class SlaveUI extends JFrame{
	
	double temp;
	double fee=0.0;
	double target=25.0;
	int speedcount=0;
	double naturaltemp;
	int count=0;
	int roomid=1;int status=0;
	double max_temp=30,min_temp=10;
	//DecimalFormat dcmFmt = new DecimalFormat("0.00");
	String mode="COOLING";
	String ip="http://10.203.28.136:8080/AirC/Server";					//"http://10.204.32.41:8080/server/Server";

	JSONObject obj = new JSONObject();
	JSONObject subobj = new JSONObject();
	JSONObject receive = new JSONObject();
	JLabel cur_temp,targ_temp,spe,fe,mo;	
	
    public void Refresh(JSONObject receive){
    	
    	String recc=receive.toString();
    	if(recc.equals("{}")){
    		System.out.println("refresh={}\n");
    	}
    	else{
	    	String typee=receive.getString("type");
	    	if(typee.equals("connected")){
	    		
	    		String ct=receive.getString("content");
	    	//	System.out.println("connected"+ct);
	    		JSONObject contt=JSONObject.fromObject(ct);
	    		
	    		status=Integer.parseInt(contt.getString("status"));
	    		if(status==1){
	    			temp=Double.parseDouble(contt.getString("curr_temp")); 
	    		
	    		}
	    		
	    		target=Double.parseDouble(contt.getString("targ_temp"));
	    		speedcount=Integer.parseInt(contt.getString("speed"));
	    		fee=Double.parseDouble(contt.getString("fee"));
	    		max_temp=Double.parseDouble(contt.getString("max_temp"));
	    		min_temp=Double.parseDouble(contt.getString("min_temp"));
	    		mode=contt.getString("mode");
	    		if(!(target > min_temp && target < max_temp) && mode.equals("0")){
	    			target = max_temp;
	    		}
	    		else if(!(target > min_temp && target < max_temp) && mode.equals("1")){
	    			target = min_temp;
	    		}
	    		
	    		//cur_temp.setText(Double.toString(temp));
	    		cur_temp.setText(String.format("%3.2f",temp));
	    		targ_temp.setText(String.format("%3.2f",target));
	    		spe.setText(Integer.toString(speedcount));
	    		fe.setText(Double.toString(fee));
	    		if(mode.equals("0"))
	    			mode="COOLING";
	    		else
	    			mode="HEATING";
	    		mo.setText(mode);
	    	}
	    	else if(typee.equals("poll_res")){
	    		String ct=receive.getString("content");
	    	//	System.out.println("poll_res"+ct);
	    		JSONObject contt=JSONObject.fromObject(ct);
	    		
	    		//前锟斤拷锟角革拷connected锟窖撅拷锟斤拷锟斤拷锟剿ｏ拷锟斤拷锟节刚刚改碉拷锟斤拷锟斤拷锟斤拷盏锟斤拷锟斤拷锟斤拷锟斤拷锟�
	    		status=Integer.parseInt(contt.getString("status"));
	    		if(status==1){
	    			temp=Double.parseDouble(contt.getString("curr_temp"));  
	    		}
//	    		target=Double.parseDouble(contt.getString("targ_temp"));
//	    		speedcount=Integer.parseInt(contt.getString("speed"));
	    		fee=Double.parseDouble(contt.getString("fee"));
	    		mode=contt.getString("mode");
	    		
	    	//	cur_temp.setText(Double.toString(temp));
	    		cur_temp.setText(String.format("%3.2f",temp));
//	    		targ_temp.setText(Double.toString(target));
//	    		spe.setText(Integer.toString(speedcount));
	    		fe.setText(Double.toString(fee));
	    		if(mode.equals("0"))
	    			mode="COOLING";
	    		else
	    			mode="HEATING";
	    		mo.setText(mode);
	    		
	    	}
	    	else{
	    		System.out.println("refresh->reject\n");
	    	}
    	}
    }   
    public void update(){
    	//cur_temp.setText(Double.toString(temp));
    	cur_temp.setText(String.format("%3.2f",temp));
		targ_temp.setText(Double.toString(target));
		spe.setText(Integer.toString(speedcount));
		fe.setText(Double.toString(fee));
		mo.setText(mode);
    }
       
	public class Poll implements Runnable{
		
		JSONObject objj;// = new JSONObject();
		JSONObject subobjj;// = new JSONObject();
		JSONObject receivee;// = new JSONObject();
		JSONObject objj1;// = new JSONObject();
		JSONObject subobjj1;// = new JSONObject();
	
		private Thread clock;
		
		public Poll(){
			start();
		}
		
		public void start(){
			if(clock==null){
				clock=new Thread(this);
				clock.start();
			}
		}
		
		public void run(){
			while(clock!=null){
				try{
					Thread.sleep(500);
					objj = new JSONObject();subobjj = new JSONObject();
					//objj1 = new JSONObject();subobjj1 = new JSONObject();
					subobjj.element("room_id", roomid);
					if(status==1){
						subobjj.element("type", 0);
					}
					else {
						subobjj.element("type", 1);
						 
						if( temp < naturaltemp )
						{
							temp=temp+0.1;
							update();
						}
					    else if(temp > naturaltemp)
						{
							temp=temp-0.1;
							update();
						}
						
					}
					subobjj.element("curr_temp", temp);
					objj.element("type", "poll");
					objj.element("content", "\""+subobjj.toString()+"\"");
					System.out.println(objj.toString());
					try {
						receivee=SlaveSend.post(ip,objj);
						System.out.println(receivee.toString());
						Refresh(receivee);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					

				}catch (InterruptedException e){
					e.printStackTrace();
				}			
			}
		}
		public void stop(){
		    clock=null;
		}				
	}
	
	public class Turn implements Runnable{
		
		double preTarget;
		int preSpeed; 
		JSONObject objj;// = new JSONObject();
		JSONObject subobjj;// = new JSONObject();
		JSONObject receivee;// = new JSONObject();
		private Thread clock;
		
		public Turn(){
			preTarget=temp;
			preSpeed=speedcount; 
			start();
		}
		
		public void start(){
			if(clock==null){
				clock=new Thread(this);
				clock.start();
			}
		}
		
		public void run(){
			while(clock!=null){
				try{
					Thread.sleep(1000);
				//	BigDecimal data1=new BigDecimal(preTarget),data2=new BigDecimal(target);
					if(target!=preTarget){					
						objj = new JSONObject();subobjj = new JSONObject();
						subobjj.element("room_id",roomid);subobjj.element("targ_temp",target);
						objj.element("type", "tune_temp");      				  	        
						objj.element("content", "\""+subobjj.toString()+"\"");
						System.out.println(objj.toString());
						preTarget=target;
						try {
							//receivee=
							SlaveSend.post(ip,objj);
						//	System.out.println("tune_temp");
							//Refresh(receivee);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
					}
					else if(preSpeed!=speedcount){
						objj = new JSONObject();subobjj = new JSONObject();
						subobjj.element("room_id",roomid);subobjj.element("speed",speedcount);
						objj.element("type", "tune_speed");      				  	        
						objj.element("content", "\""+subobjj.toString()+"\"");					
						System.out.println(objj.toString());
						preSpeed=speedcount;
						try {
							//receivee=
							SlaveSend.post(ip,objj);
						//	System.out.println("tune_speed");
							//Refresh(receivee);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
					}		

				}catch (InterruptedException e){
					e.printStackTrace();
				}			
			}
		}
		public void stop(){
		    clock=null;
		}				
	}

	Poll hb;
	Turn t;
	public SlaveUI(int ri,double inittemp,String ipp) {
		
		roomid=ri;
		temp=inittemp;
		target=temp;
		ip=ipp;
		naturaltemp=inittemp;

//		Tempimitate ti=new Tempimitate();
		
		//锟斤拷示锟斤拷前锟铰度★拷目锟斤拷锟铰度★拷锟斤拷锟劫★拷锟斤拷锟矫★拷锟斤拷锟斤拷锟斤拷锟斤拷		
		JLabel label_5 = new JLabel("\u623F\u95F4\u53F7");
		label_5.setBounds(100, 10, 42, 15);
		getContentPane().add(label_5);
		
		JLabel room = new JLabel(Integer.toString(roomid));
		room.setBounds(232, 10, 27, 15);
		getContentPane().add(room);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u6E29\u5EA6");
		lblNewLabel.setBounds(88, 35, 54, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u76EE\u6807\u6E29\u5EA6");
		label_1.setBounds(88, 60, 54, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u98CE\u901F");
		label_2.setBounds(115, 85, 54, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u8D39\u7528");
		label_3.setBounds(115, 110, 54, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u6A21\u5F0F");
		label_4.setBounds(115, 135, 54, 15);
		getContentPane().add(label_4);
		
//		cur_temp = new JLabel(Double.toString(temp));cur_temp.setBounds(232, 35, 53, 15);getContentPane().add(cur_temp);
		
		cur_temp = new JLabel();cur_temp.setBounds(232, 35, 53, 15);getContentPane().add(cur_temp);
		cur_temp.setText(String.format("%3.2f",temp));
		targ_temp = new JLabel(Double.toString(target));targ_temp.setBounds(232, 60, 54, 15);getContentPane().add(targ_temp);
		spe = new JLabel(Integer.toString(speedcount));spe.setBounds(232, 85, 54, 15);getContentPane().add(spe);
		fe = new JLabel(Double.toString(fee));fe.setBounds(232, 110, 54, 15);getContentPane().add(fe);
		mo = new JLabel(mode);mo.setBounds(231, 135, 54, 15);getContentPane().add(mo);
			
		final JButton poweron = new JButton("\u5F00\u542F\u7A7A\u8C03");final JButton button = new JButton("\u5173\u95ED\u7A7A\u8C03");
		poweron.setBounds(49, 185, 93, 23);
		poweron.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//锟斤拷锟酵匡拷锟秸碉拷锟斤拷
				subobj.element("room_id", roomid);				  	          
				subobj.element("init_temp", temp);				  		
				obj.element("type", "connect");      				  	  
				obj.element("content", "\""+subobj.toString()+"\"");
				System.out.println(obj.toString());
				try {
					receive=SlaveSend.post(ip,obj);
					if(!receive.getString("type").equals("reject")){
					System.out.println(receive.toString());
					Refresh(receive);		
				//	poweron.setEnabled(false);
					button.setEnabled(true);
					if(hb==null)
					{
						hb=new Poll();
						hb.stop();
						
					}
					if(t==null)
					{
						t=new Turn();
						t.stop();
					}															
					hb.start();
					t.start();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});	
		
		button.setBounds(49, 229, 93, 23);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//锟截伙拷
				 subobj.element("room_id", roomid);		
				 obj.element("type", "disconnect");      
	  	         obj.element("content", "\""+subobj.toString()+"\"");
	  	         System.out.println(obj.toString());
	  	         try {
					SlaveSend.post(ip,obj);
					button.setEnabled(false);
					poweron.setEnabled(true);
					if(hb==null)
					{
						hb=new Poll();
						hb.stop();
						
					}
					if(t==null)
					{
						t=new Turn();
						t.stop();
					}		
					hb.stop();
					t.stop();
//					refresh(receive);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
				
		JButton speed = new JButton("\u98CE\u901F");
		speed.setBounds(49, 278, 93, 23);
		speed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//锟斤拷锟斤拷
				speedcount = (speedcount+1)%3;	
				update();
			}
		});
		
		JButton up = new JButton("+");
		up.setBounds(266, 197, 54, 33);
		up.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//锟斤拷锟斤拷
				if(target < max_temp){
					target++;
					update();
				}
				
			}
		});
		
		JButton down = new JButton("-");
		down.setBounds(266, 260, 54, 33);
		down.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//锟斤拷锟斤拷
				if(target > min_temp){
					target--;
					update();
				}
				
			}
		});
		
		JLabel label = new JLabel("\u6E29\u5EA6");
		label.setBounds(222, 233, 37, 15);
		getContentPane().setLayout(null);
		getContentPane().add(poweron);
		getContentPane().add(button);
		getContentPane().add(speed);
		getContentPane().add(up);
		getContentPane().add(label);
		getContentPane().add(down);
		
		
		this.setSize(395, 372);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	//	this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	}
	
}


