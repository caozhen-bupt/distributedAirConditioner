package Air;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;


import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
public class MainUI extends JFrame{
	
	public MainUI() {
		new Controller();
		this.setSize(400,350);
		JButton manager = new JButton("\u7ECF\u7406");
		manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newManagerUI();
//				new LoginUI(1);
			}
		});
		manager.setBounds(159, 99, 93, 27);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				Constant con = new Constant();
				con.writeConfig();
				System.exit(0);
			}
		});
			
		
		
		JButton admin = new JButton("\u7A7A\u8C03\u7BA1\u7406\u5458");
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUI.getInstance().setVisible(true);
				AdminUI.getInstance().update();
//				new LoginUI(0);
			}
		});
		admin.setBounds(159, 151, 107, 27);

		
		JButton reception = new JButton("\u524D\u53F0");
		reception.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReceptionUI();
//				new LoginUI(2);
			}
		});
		reception.setBounds(159, 204, 93, 27);

		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5206\u5E03\u5F0F\u6E29\u63A7\u7BA1\u7406\u7CFB\u7EDF\uFF0C\u8BF7\u9009\u62E9\u60A8\u7684\u8EAB\u4EFD");
		label.setBounds(64, 53, 316, 15);
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 14));
		getContentPane().add(label);
		getContentPane().add(manager);
		getContentPane().add(admin);
		getContentPane().add(reception);
		
		setVisible(true);
	}
}
