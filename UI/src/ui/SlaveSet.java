package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SlaveSet extends JFrame{
	
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public SlaveSet() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("房间号");
		lblNewLabel.setBounds(46, 55, 78, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("初始温度");
		label.setBounds(46, 98, 78, 16);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("服务器地址");
		label_1.setBounds(46, 147, 78, 16);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(155, 52, 237, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(155, 95, 237, 22);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(155, 144, 237, 22);
		getContentPane().add(textField_2);
		
		JButton button = new JButton("进入房间");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int roomid=Integer.parseInt(textField.getText());
				double inittemp=Double.parseDouble(textField_1.getText());
				String ip=textField_2.getText();
				System.out.println(ip);
				setVisible(false);
				SlaveUI slave=new SlaveUI(roomid,inittemp,ip);
				
				
				
				
			}
		});
		button.setBounds(293, 198, 97, 25);
		getContentPane().add(button);
		this.setSize(500, 300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args){
		
	try{
		
		new SlaveSet();
		new SlaveSet();
		new SlaveSet();
		new SlaveSet();
		
	}
	catch(Exception e){
		e.printStackTrace();
	}		
}
}
