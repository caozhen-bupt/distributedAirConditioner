package Air;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ReceptionUI extends JFrame implements ActionListener{
	private JButton button,button_1,button_2;
	public ReceptionUI() {
		new Reception();
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setSize(472,350);
		button = new JButton("\u7528\u6237\u5230\u6765");
		button.addActionListener(this);
		
		button.setBounds(141, 104, 113, 40);
		getContentPane().add(button);
		
		button_1 = new JButton("\u7528\u6237\u79BB\u5F00");
		button_1.addActionListener(this);
		
		button_1.setBounds(141, 190, 113, 40);
		getContentPane().add(button_1);
		
		button_2 = new JButton("\u6CE8\u9500");
		button_2.addActionListener(this);
		button_2.setBounds(377, 10, 77, 27);
		getContentPane().add(button_2);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			new CheckinUI();
		}
		else if(e.getSource() == button_1)
		{
			new checkOutUI();
		}
		else dispose();
		
	}
}
