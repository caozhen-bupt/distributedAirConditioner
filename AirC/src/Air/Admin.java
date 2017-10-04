package Air;


public class Admin{
	
	public Admin() {
//		super(ID, password);

	}

//	public boolean login(String id, String password){
//		//鎿嶄綔鏁版嵁搴�
//		return true;
//	}
	
	public void openMaster(){
		Master.getInstance().setOpen(true);
	}
	public void closeMaster(){
		Master.getInstance().setOpen(false);
	}
	public void superviseRoomState(){
		//....
	}
	public void configMaintain(){
		//...
	}
	public void setSmallSlide(int smallSlide){
		Constant.smallSlide = smallSlide;
	}
	
	
//	public void setBigSlide(int bigSlide){
//		Constant.bigSlide = bigSlide;
//	}
//	
//	public void setDifference(double difference) {
//		Constant.difference = difference;
//	}
	public void setResourceNum(int resourceNum) {
		Constant.resourceNum = resourceNum;
	}
	public void setLow(double low) {
		Constant.low = low;
	}
	public void setMedian(double median) {
		Constant.median = median;
	}
	public void setHigh(double high) {
		Constant.high = high;
	}
	public void setNaturalDiff(double naturalDiff) {
		Constant.naturalDiff = naturalDiff;
	}
	public void setMoneyUnit(double moneyUnit) {
		Constant.moneyUnit = moneyUnit;
	}
	public void setLowPower(double lowPower) {
		Constant.lowPower = lowPower;
	}
	public void setMedianPower(double medianPower) {
		Constant.medianPower = medianPower;
	}
	public void setHighPower(double highPower) {
		Constant.highPower = highPower;
	}
	public void setDefaultTemp(double defaultTemp) {
		Constant.defaultTemp = defaultTemp;
	}
	
}

