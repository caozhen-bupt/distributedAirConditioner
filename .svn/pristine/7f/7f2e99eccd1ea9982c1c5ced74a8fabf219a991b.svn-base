package Air;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
public class AdminUI extends JFrame{
	private JList<String> list;
	private JList<String> list_1;
	private JList<String> list_2;
	private JList<String> list_3;
	private JLabel label_4;
	private JLabel label_5;
	
	private static AdminUI m;
	private AdminUI() {
		this.setVisible(false);
		this.setSize(613,603);
		
		JLabel label = new JLabel("\u623F\u95F41");
		label.setBounds(0, 0, 72, 18);
		
		JLabel label_1 = new JLabel("\u623F\u95F42");
		label_1.setBounds(306, 0, 72, 18);
		
		JLabel label_2 = new JLabel("\u623F\u95F43");
		label_2.setBounds(0, 250, 72, 18);
		
		JLabel label_3 = new JLabel("\u623F\u95F44");
		label_3.setBounds(306, 250, 72, 18);
		
		getContentPane().setLayout(null);
		getContentPane().add(label);
		getContentPane().add(label_1);
		getContentPane().add(label_2);
		getContentPane().add(label_3);
		
		String [] str = changeList(0);
		list = new JList<String>();
		list.setBounds(25, 28, 259, 212);
		list.setListData(str);
		getContentPane().add(list);
		
		String [] str1 = changeList(1);
		list_1 = new JList<String>();
		list_1.setBounds(307, 28, 259, 212);
		list_1.setListData(str1);
		getContentPane().add(list_1);
		
		String [] str2 = changeList(2);
		list_2 = new JList<String>();
		list_2.setBounds(25, 288, 259, 212);
		list_2.setListData(str2);
		getContentPane().add(list_2);
		
		String [] str3 = changeList(3);
		list_3 = new JList<String>();
		list_3.setBounds(307, 288, 259, 212);
		list_3.setListData(str3);
		getContentPane().add(list_3);
		
		label_4 = new JLabel("");
		label_4.setBounds(25, 510, 175, 15);
		if(Master.getInstance().getOpen()==true){
			label_4.setText("中央空调:开机");
		}
		else{
			label_4.setText("中央空调:关机");
		}
		getContentPane().add(label_4);
		
		label_5 = new JLabel("");
		label_5.setBounds(25, 535, 228, 15);
		label_5.setText(Master.getInstance().timeToString());
		getContentPane().add(label_5);
		
		JButton button = new JButton("\u5F00\u673A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Master.getInstance().setOpen(true);
				label_4.setText("中央空调:开机");
			}
		});
		button.setBounds(509, 510, 57, 23);
		getContentPane().add(button);
		
		JButton btnNewButton = new JButton("\u5173\u673A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Master.getInstance().setOpen(false);
				label_4.setText("中央空调:关机");
				Master.getInstance().initMaster();
			}
		});
		btnNewButton.setBounds(509, 535, 57, 23);
		getContentPane().add(btnNewButton);
		
		JButton button_1 = new JButton("\u5236\u51B7");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Master.getInstance().setMode(0);
			}
		});
		button_1.setBounds(392, 510, 72, 23);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5236\u70ED");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Master.getInstance().setMode(1);
			}
		});
		button_2.setBounds(392, 535, 72, 23);
		getContentPane().add(button_2);
		
	}
	
	public static AdminUI getInstance() {  
        if (m == null) {    
            m = new AdminUI();  
        }    
       return m;  
	}  
	
	public String[] changeList(int i){
		Room r = Master.getInstance().getRoomList().get(i);
		String [] str = new String[9];
		if(r.getOpen())
		{
			if(r.getState() == 2) str[0] = "空调状态：开,温控中";
			else if(r.getState() == 1) str[0] = "空调状态：挂起";
			else if(r.getState() == 0) str[0] = "空调状态：开,没有请求";
			else str[0] = "空调状态：开，出鬼了";
		}
		else
		{
			str[0] = "空调状态：关";
		}
		if(r.getUserIn()){
			str[1] = "是否存在住户：是";
			str[2] = "用户id：" + r.getUserID();
		}
		else{
			str[1] = "是否存在住户：否";
			str[2] = "用户id：无";
		}
		str[3] = "房间号：" + r.getName();
		str[4] = "当前温度：" +String.format("%.2f",r.getCurTemp()); 
		str[5] = "目标温度：" + String.format("%.2f",r.getTarTemp());
		if(r.getWindSpeed() == 0){
			str[6] = "风速：低";
		}
		else if(r.getWindSpeed() == 1){
			str[6] = "风速：中";
		}
		else if(r.getWindSpeed() == 2){
			str[6] = "风速：高";
		}
		str[7] = "费用：" + String.format("%.2f",r.getCost());
		if(Master.getInstance().getMode() == 0){
			str[8] = "模式：制冷";
		}
		else{
			str[8] = "模式：制热";
		}
		return str;
	
	}

	public void update()
	{
		String [] str = changeList(0);
		list.setListData(str);
		
		String [] str1 = changeList(1);
		list_1.setListData(str1);
	
		
		String [] str2 = changeList(2);
		list_2.setListData(str2);
	
		
		String [] str3 = changeList(3);
		list_3.setListData(str3);
		
		String time=Master.getInstance().timeToString();
		label_5.setText(time);
	}
}
