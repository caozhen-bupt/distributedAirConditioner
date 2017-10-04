package Air;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newManagerUI extends JDialog{
	public newManagerUI() {
		this.setVisible(true);
		this.setSize(494,350);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u67E5\u770B\u5404\u623F\u95F4\u7EDF\u8BA1\u62A5\u8868");
		label.setBounds(137, 27, 150, 15);
		getContentPane().add(label);
		
		JButton button = new JButton("\u5404\u623F\u95F4\u6BCF\u65E5\u6D88\u8D39\u8D39\u7528");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new table1UI();
			}
		});
		button.setBounds(94, 81, 202, 33);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u5404\u623F\u95F4\u4E0D\u540C\u98CE\u901F\u4E0B\u6D88\u8D39\u8D39\u7528");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new table2UI();
			}
		});
		button_1.setBounds(93, 143, 203, 33);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5404\u623F\u95F4\u7EFC\u5408\u7EDF\u8BA1\u4FE1\u606F");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new table3UI();
			}
		});
		button_2.setBounds(95, 208, 201, 33);
		getContentPane().add(button_2);
	}
}
