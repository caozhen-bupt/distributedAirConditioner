package Air;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class checkOutUI extends JFrame implements ActionListener{
	private JTextField text ;
	private JTextField text_1;
	JButton button;
	public checkOutUI() {
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u623F\u95F4\u53F7\uFF1A");
		label.setBounds(45, 58, 59, 18);
		getContentPane().add(label);
		
		text = new JTextField();
		text.setBounds(176, 58, 202, 24);
		getContentPane().add(text);
		
		text_1 = new JTextField();
		text_1.setBounds(176, 120, 202, 24);
		getContentPane().add(text_1);
		
		button = new JButton("\u7ED3\u8D26");
		button.addActionListener(this);
		
		button.setBounds(276, 205, 96, 27);
		getContentPane().add(button);
		
		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_1.setBounds(45, 120, 70, 18);
		getContentPane().add(label_1);
		
		this.setSize(434,350);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			try{
				String roomID = checkOutUI.this.text.getText();
				String userID = checkOutUI.this.text_1.getText();
//				boolean find = false;
//				for(int i = 0; i < 4; i++){
////					if(Master.getInstance().getRoomList().get(i).getName().equals(roomID) 
////							&& Master.getInstance().getRoomList().get(i).getUserID().equals(userID)
////							&& Master.getInstance().getRoomList().get(i).getUserIn() == true){
//////						find = true;
////						Master.getInstance().setOpenfalse(roomID);
////						Master.getInstance().getRoomList().get(i).setUserID(null);
////						Master.getInstance().getRoomList().get(i).setUserIn(false);
//	//					Master.getInstance().getRoomList().get(i).setState(1);
//						new details(roomID);
//					}
				
//				}
				if(Master.getInstance().checkout(roomID, userID)){
					new details(roomID);
					
				}
			}
			catch(RuntimeException ex){
	    		JOptionPane.showMessageDialog(this.getContentPane(),
	    				"输入信息有误。", "失败", JOptionPane.WARNING_MESSAGE);
	    	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		dispose();
	}
}
