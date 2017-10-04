package Air;

public class MyTimer implements Runnable
{
	private Thread clock;

	public MyTimer(){
		start();
	}
	
	public void start(){
		if(clock==null){
			clock=new Thread(this);
			clock.start();
		}
	}
	
	public void run(){
		while(clock!=null){
			try{
				Thread.sleep(Constant.smallSlide);
				//System.out.println(Constant.smallSlide);
				Master.getInstance().setSystemTime(Master.getInstance().getSystemTime()+Constant.smallSlide);
				Constant.systemTime = Master.getInstance().getSystemTime();
				Master.getInstance().transLongToTime(Master.getInstance().getSystemTime());
				Master.getInstance().updateCurTemp();
				Master.getInstance().updateAllRoomState();
				Master.getInstance().writeSystemTimeToConfig();
				
				AdminUI.getInstance().update();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void stop()
	{
		Master.getInstance().writeSystemTimeToConfig();
	    clock=null;
	}

}
