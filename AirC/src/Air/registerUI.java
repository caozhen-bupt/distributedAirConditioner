package Air;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class registerUI extends JDialog{
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JEditorPane ID;
	private JEditorPane password;
	public registerUI() {
		getContentPane().setLayout(null);
		
		label = new JLabel("\u6CE8\u518C\uFF1A");
		label.setBounds(21, 22, 54, 15);
		getContentPane().add(label);
		
		label_1 = new JLabel("\u8BF7\u8F93\u5165\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_1.setBounds(46, 80, 121, 15);
		getContentPane().add(label_1);
		
		ID = new JEditorPane();
		ID.setBounds(188, 74, 161, 21);
		getContentPane().add(ID);
		
		label_2 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_2.setBounds(46, 137, 94, 15);
		getContentPane().add(label_2);
		
		password = new JEditorPane();
		password.setBounds(190, 137, 159, 21);
		getContentPane().add(password);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//注册信息完成，确定
			}
		});
		button.setBounds(298, 196, 87, 37);
		getContentPane().add(button);
	}
}
