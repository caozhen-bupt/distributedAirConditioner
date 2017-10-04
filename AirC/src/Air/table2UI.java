package Air;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;

public class table2UI extends JDialog{
	private JList list;
	private JList list_1;
	private JList list_2;
	private JList list_3;
	private JList list_4;
	private JList list_5;
	private JList list_6;
	private JList list_7;
	private JList list_8;
	private JList list_9;
	private JList list_10;
	private JList list_11;
	public table2UI() {
		this.setVisible(true);
		this.setSize(650,800);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5404\u623F\u95F4\u6BCF\u65E5\u4E0D\u540C\u98CE\u901F\u4E0B\u6D88\u8D39\u8D39\u7528\uFF1A");
		label.setBounds(181, 10, 211, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u623F\u95F4\u4E00\uFF1A");
		label_1.setBounds(10, 47, 54, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u4F4E\u98CE\uFF1A");
		label_2.setBounds(10, 72, 54, 15);
		getContentPane().add(label_2);
		
		list = new JList();
		list.setBounds(10, 97, 235, 63);
		getContentPane().add(list);
		
		JLabel label_3 = new JLabel("\u4E2D\u98CE\uFF1A");
		label_3.setBounds(10, 171, 54, 15);
		getContentPane().add(label_3);
		
		list_1 = new JList();
		list_1.setBounds(10, 196, 235, 63);
		getContentPane().add(list_1);
		
		JLabel label_4 = new JLabel("\u9AD8\u98CE\uFF1A");
		label_4.setBounds(10, 278, 54, 15);
		getContentPane().add(label_4);
		
		list_2 = new JList();
		list_2.setBounds(10, 303, 235, 63);
		getContentPane().add(list_2);
		
		JLabel label_5 = new JLabel("\u623F\u95F4\u4E8C\uFF1A");
		label_5.setBounds(283, 47, 54, 15);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u4F4E\u98CE\uFF1A");
		label_6.setBounds(283, 72, 54, 15);
		getContentPane().add(label_6);
		
		list_3 = new JList();
		list_3.setBounds(283, 97, 250, 63);
		getContentPane().add(list_3);
		
		JLabel label_7 = new JLabel("\u4E2D\u98CE\uFF1A");
		label_7.setBounds(283, 171, 54, 15);
		getContentPane().add(label_7);
		
		list_4 = new JList();
		list_4.setBounds(283, 196, 250, 63);
		getContentPane().add(list_4);
		
		JLabel label_8 = new JLabel("\u9AD8\u98CE\uFF1A");
		label_8.setBounds(283, 278, 54, 15);
		getContentPane().add(label_8);
		
		list_5 = new JList();
		list_5.setBounds(283, 303, 250, 63);
		getContentPane().add(list_5);
		
		JLabel label_9 = new JLabel("\u623F\u95F4\u4E09\uFF1A");
		label_9.setBounds(10, 404, 54, 15);
		getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("\u4F4E\u98CE\uFF1A");
		label_10.setBounds(10, 429, 54, 15);
		getContentPane().add(label_10);
		
		list_6 = new JList();
		list_6.setBounds(10, 454, 235, 63);
		getContentPane().add(list_6);
		
		JLabel label_11 = new JLabel("\u4E2D\u98CE\uFF1A");
		label_11.setBounds(10, 534, 54, 15);
		getContentPane().add(label_11);
		
		list_7 = new JList();
		list_7.setBounds(10, 559, 235, 63);
		getContentPane().add(list_7);
		
		JLabel label_12 = new JLabel("\u9AD8\u98CE\uFF1A");
		label_12.setBounds(10, 641, 54, 15);
		getContentPane().add(label_12);
		
		list_8 = new JList();
		list_8.setBounds(10, 666, 235, 63);
		getContentPane().add(list_8);
		
		JLabel label_13 = new JLabel("\u623F\u95F4\u56DB\uFF1A");
		label_13.setBounds(283, 404, 54, 15);
		getContentPane().add(label_13);
		
		JLabel label_14 = new JLabel("\u4F4E\u98CE\uFF1A");
		label_14.setBounds(283, 429, 54, 15);
		getContentPane().add(label_14);
		
		list_9 = new JList();
		list_9.setBounds(283, 454, 250, 63);
		getContentPane().add(list_9);
		
		JLabel label_15 = new JLabel("\u4E2D\u98CE\uFF1A");
		label_15.setBounds(283, 534, 54, 15);
		getContentPane().add(label_15);
		
		list_10 = new JList();
		list_10.setBounds(283, 559, 250, 63);
		getContentPane().add(list_10);
		
		JLabel label_16 = new JLabel("\u9AD8\u98CE\uFF1A");
		label_16.setBounds(283, 641, 54, 15);
		getContentPane().add(label_16);
		
		list_11 = new JList();
		list_11.setBounds(283, 666, 250, 63);
		getContentPane().add(list_11);
	}

}
