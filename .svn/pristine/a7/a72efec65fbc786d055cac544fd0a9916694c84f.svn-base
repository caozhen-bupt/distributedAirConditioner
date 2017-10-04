package Air;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;

public class details extends JFrame{
	private JTextField textField;
	private JLabel label_1;
	private JLabel label;
	private JList list;
	private String roomID;
//	private String userID;
	
	public details(String roomID) throws IOException {
		this.setBounds(10, 10, 600, 400);
		this.roomID = roomID;
		label_1 = new JLabel(roomID);
		label_1.setBounds(14, 13, 72, 18);
		label_1.setText(roomID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 41, 555, 204);
		
		label = new JLabel("\u603B\u8BA1\uFF1A");
		label.setBounds(288, 278, 36, 15);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(354, 274, 215, 23);
		textField.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		
		list = new JList();
		String [] output = printList();
		Arrays.sort(output);
		log(output);
		this.list.setListData(output);
		double totalFee = 0;
		for(int i = 0; i < 4 ; i++){
			if(Master.getInstance().getRoomList().get(i).getName().equals(this.roomID)){
				totalFee = Master.getInstance().getRoomList().get(i).getCost();
			}
		}
		textField.setText(String.valueOf(totalFee));
		Master.getInstance().getDatabase().deleteDetail(Integer.parseInt(roomID));
		
		scrollPane.setViewportView(list);
		getContentPane().add(label);
		getContentPane().add(textField);
		getContentPane().add(label_1);
		
		setVisible(true);
	}
	
	public String [] printList(){
		Vector<String> vec = Master.getInstance().getDatabase().getDetailList(this.roomID);
		String str = new String();
		String [] each = new String[10];
		String [] output = new String[vec.size()];
		DecimalFormat df = new DecimalFormat( "#.00");
		for(int i = 0; i < vec.size(); i++){
			str = vec.get(i);
			each = str.split("-");
			output[i] ="";
			output[i] += each[2]+"-";
			output[i] += each[3]+"-";
			output[i] += each[4]+" ";
			output[i] += each[5]+":";
			output[i] += each[6]+" ";
			
			if(each[1].equals("1")){
				output[i] += "开机   ";
			}
			else if(each[1].equals("2")){
				output[i] += "关机   ";
			}
			else if(each[1].equals("3")){
				output[i] += "请求开始服务   ";
			}
			else if(each[1].equals("4")){
				output[i] += "请求停止服务   ";
			}
			
			if(each[0].equals("0")){
				output[i] += "低速风   ";
			}
			else if(each[0].equals("1")){
				output[i] += "中速风   ";
			}
			else if(each[0].equals("2")){
				output[i] += "高速风  ";
			}
			
			output[i] += "室温";
			output[i] += df.format(Double.valueOf(each[7]));
			output[i] += "   目标温度：";
			output[i] += df.format(Double.valueOf(each[8]));
			output[i] += "   费用：";
			output[i] += df.format(Double.valueOf(each[9]));
		}
		return output;
	}
	public String getTotalFee(){//没调用
		Vector<String> vec = Master.getInstance().getDatabase().getDetailList(this.roomID);
		double totalFee=0;
		String str = new String();
		String [] each = new String[10];
		DecimalFormat df = new DecimalFormat( "#.00");
		for(int i = 0; i < vec.size(); i++){
			str = vec.get(i);
			each = str.split("-");
			totalFee += Double.valueOf(each[9]);
		}
		
		return df.format(totalFee);
	}
	
	public void log(String [] output)throws IOException{
		 FileWriter writer;
	        try {
	            writer = new FileWriter("C:/Users/asus/Desktop/abcd.txt");
				for(int i=0; i < output.length; i++){
					writer.write(output[i]);
					writer.write("\n");
					writer.flush();
				}
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        
    }
	
}


