package Air;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class CheckinUI extends JDialog implements ActionListener{
	private JLabel label;
	private String userID;
	private JButton button ;
	private JTextField text;
	public CheckinUI() {
		getContentPane().setLayout(null);
		
		label = new JLabel("\u7528\u6237\u8EAB\u4EFD\u8BC1\u53F7");
		label.setBounds(34, 81, 127, 18);
		getContentPane().add(label);
		
		text = new JTextField();
		text.setBounds(175, 75, 139, 24);
		getContentPane().add(text);
		
		button = new JButton("\u786E\u5B9A");
		button.addActionListener(this);
		
		button.setBounds(235, 176, 79, 27);
		getContentPane().add(button);
		this.setSize(350,250);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			boolean find=false;
			try{
				userID = text.getText();
			
					for(int i = 0; i < 4; i++){
						if(Master.getInstance().getRoomList().get(i).getUserIn() == false){
							Master.getInstance().getRoomList().get(i).setUserIn(true);
							Master.getInstance().getRoomList().get(i).setUserID(userID);
							JOptionPane.showMessageDialog( this.getContentPane(), "用户房间号：" + Master.getInstance().getRoomList().get(i).getName() , "入住成功" , JOptionPane.PLAIN_MESSAGE);
							//TODO 写数据库
							find = true;
							break;
						}

					}
					if(!find){
						JOptionPane.showMessageDialog( this.getContentPane(), "对不起，客满！", "入住失败", JOptionPane.WARNING_MESSAGE) ;
					}
				
			}
			catch(RuntimeException ex){
	    		JOptionPane.showMessageDialog(this.getContentPane(),
	    				"输入信息有误。", "失败", JOptionPane.WARNING_MESSAGE);
	    	}
			
		}
		dispose();
	}
}
