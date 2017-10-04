package Air;

import Air.Mess;

public class Reception{
	
	public Reception() {
//		super(ID, password);
	}

//	public boolean login(String id, String password){
//		//閹垮秳缍旈弫鐗堝祦鎼达拷
//		return true;
//	}
	
	public boolean checkUserID(String userID){
		//閹垮秳缍旈弫鐗堝祦鎼达拷
		return true;
	}
	
	
	public String distributeRoom(String userID){
		for(int i = 0; i < Master.getInstance().getRoomList().size(); i++){
			if(Master.getInstance().getRoomList().get(i).getUserIn() == false){
				Master.getInstance().getRoomList().get(i).setUserIn(true);
				Master.getInstance().getRoomList().get(i).setUserID(userID);
				return Master.getInstance().getRoomList().get(i).getName();
			}
		}
		return null;
	}
	
	public boolean userCheckOut(String userID){
		
		for(int i = 0; i < Master.getInstance().getRoomList().size(); i++){
			if(Master.getInstance().getRoomList().get(i).getUserID() == userID){
				Master.getInstance().getRoomList().get(i).setUserIn(false);
				Master.getInstance().getRoomList().get(i).setUserID(null);
				Master.getInstance().getRoomList().get(i).setOpen();
				Master.getInstance().getRoomList().get(i).setState(1);
				Master.getInstance().getRoomList().get(i).setCost(0);
				Master.getInstance().getRoomList().get(i).setTarTemp(Constant.defaultTemp);
				new Mess("userCheckOut"+Double.toString(Constant.defaultTemp));
				//閹垫挸宓冪拠锕�宕熼妴锟�
				return true;
			}
		}
		return false;
	}
}