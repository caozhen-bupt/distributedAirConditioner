package Air;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
//import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.text.DateFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.lang.String;

public class ManagerUI extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public ManagerUI() {
		this.setVisible(true);
		this.setSize(494,350);
//		new Manager(ID,password);
		JLabel cost = new JLabel("\u67E5\u770B\u7A7A\u8C03\u80FD\u8017\uFF1A");
		cost.setBounds(23, 44, 84, 15);
		
		JLabel label = new JLabel("\u67E5\u770B\u7A7A\u8C03\u4F7F\u7528\u60C5\u51B5\uFF1A");
		label.setBounds(23, 109, 113, 15);
		
		JLabel label_1 = new JLabel("\u5E74");
		label_1.setBounds(173, 44, 25, 15);
		
		JLabel label_2 = new JLabel("\u6708");
		label_2.setBounds(262, 44, 25, 15);
		
		JLabel label_3 = new JLabel("\u65E5");
		label_3.setBounds(355, 44, 25, 15);
		
		JButton button08 = new JButton("0\uFF1A00~8\uFF1A00");
		button08.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//0~8
				
				
			}
		});
		button08.setBounds(158, 105, 127, 23);
		
		
		JButton button816 = new JButton("8\uFF1A00~16\uFF1A00");
		button816.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//8~16
				
			}
		});
		button816.setBounds(158, 152, 127, 23);
		
		JButton button1624 = new JButton("16\uFF1A00~24\uFF1A00");
		button1624.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//16~24
				
				
			}
		});
		button1624.setBounds(158, 201, 127, 23);
		
		
		JLabel label_4 = new JLabel("\u65F6\u6BB5");
		label_4.setBounds(295, 109, 54, 15);
		
		JLabel label_5 = new JLabel("\u65F6\u6BB5");
		label_5.setBounds(295, 156, 54, 15);
		
		JLabel label_6 = new JLabel("\u65F6\u6BB5");
		label_6.setBounds(295, 205, 54, 15);
		
		
		
		textField = new JTextField();
		textField.setBounds(111, 41, 58, 21);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(198, 41, 60, 21);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(287, 41, 58, 21);
		textField_2.setColumns(10);
		
		JButton go1 = new JButton("\u67E5\u8BE2");
		go1.setBounds(283, 72, 63, 23);
		go1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year=textField.getText(); 
				String month=textField_1.getText();
				String day=textField_2.getText();
				//....................................
			}
		});
		
		JButton button = new JButton("\u6E05\u7A7A");
		button.setBounds(208, 72, 57, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(label);
		getContentPane().add(button08);
		getContentPane().add(label_4);
		getContentPane().add(button816);
		getContentPane().add(label_5);
		getContentPane().add(button1624);
		getContentPane().add(label_6);
		getContentPane().add(cost);
		getContentPane().add(textField);
		getContentPane().add(label_1);
		getContentPane().add(button);
		getContentPane().add(go1);
		getContentPane().add(textField_1);
		getContentPane().add(label_2);
		getContentPane().add(textField_2);
		getContentPane().add(label_3);
	}
}
