package po;

import java.io.Serializable;

public class GoodsPO implements Serializable{
	
	private static final long serialVersionUID = 141250149L;
	
	private String Order_ID;
	private double fee;
	private String departurePlace, destination;
	private String enterTime[], leaveTime[], enterRepertoryID[], leaveRepertoryID[];
	private boolean inRepertory;
	
	public GoodsPO(String Order_ID, double fee, String departurePlace, String destination){
		this.Order_ID = Order_ID;
		this.fee = fee;
		this.departurePlace = departurePlace;
		this.destination = destination;
		enterTime = new String[5];
		for(int i=0;i<4;i++){
			enterTime[i] = "无";
		}
		leaveTime = new String[5];
		for(int i=0;i<4;i++){
			leaveTime[i] = "无";
		}
		enterRepertoryID = new String[5];
		for(int i=0;i<4;i++){
			enterRepertoryID[i] = "无";
		}
		leaveRepertoryID = new String[5];
		for(int i=0;i<4;i++){
			leaveRepertoryID[i] = "无";
		}
		setInRepertory(false);
	}
	
	public GoodsPO(String Order_ID, double fee, String departurePlace, String destination, String[] enterTime, String[] leaveTime,
			String[] enterRepertoryID, String[] leaveRepertoryID){
		this.Order_ID = Order_ID;
		this.fee = fee;
		this.departurePlace = departurePlace;
		this.destination = destination;
		this.enterTime = enterTime;
		this.leaveTime = leaveTime;
		this.enterRepertoryID = enterRepertoryID;
		this.leaveRepertoryID = leaveRepertoryID;
	}
	
	public GoodsPO(String Order_ID, double fee, String departurePlace, String destination, String[] enterTime, String[] leaveTime,
			String[] enterRepertoryID, String[] leaveRepertoryID, boolean b){
		this.Order_ID = Order_ID;
		this.fee = fee;
		this.departurePlace = departurePlace;
		this.destination = destination;
		this.enterTime = enterTime;
		this.leaveTime = leaveTime;
		this.enterRepertoryID = enterRepertoryID;
		this.leaveRepertoryID = leaveRepertoryID;
		this.inRepertory = b;
	}
	
	public String getOrder_ID(){
		return this.Order_ID;
	}
	
	public double getFee(){
		return this.fee;
	}
	
	public String getDeparturePlace(){
		return this.departurePlace;
	}
	
	public void setDeparturePlace(String departurePlace){
		this.departurePlace = departurePlace;
	}
	
	public String getDestination(){
		return this.destination;
	}
	
	public void setDestination(String destination){
		this.destination = destination;
	}
	
	public String[] getEnterTime(){
		return this.enterTime;
	}
	
	public String[] getEnterDate(){
		String[] enterDate = new String[4];
		for(int i=0;i<4;i++){
			enterDate[i] = "无";
		}
		for(int i=0;i<4;i++){
			if(!enterTime[i].equals("无")){
				String[] parts = enterTime[i].split(" ");
				enterDate[i] = parts[0];
			}
		}
		return enterDate;
	}
	
	public String getLatestEnterTime(){
		for(int i=0;i<3;i++){
			if(( !this.enterTime[i].equals("无") ) && (this.enterTime[i+1].equals("无")))
				return this.enterTime[i];	
		}
		return "无";
	}
	
	public String getThisRepertoryEnterTime(String repertoryID){
		for(int i=0;i<4;i++){
			if(this.enterRepertoryID[i].equals(repertoryID))
				return enterTime[i];
		}
		return "has not entered this repertory";
	}
	
	public void setEnterTime(String enterTime){
		for(int i=0;i<4;i++){
			if(this.enterTime[i].equals("无")){
				this.enterTime[i] = enterTime;
				break;
			}
		}
	}
	
	public String[] getLeaveTime(){
		return this.leaveTime;
	}
	
	public String[] getLeaveDate(){
		String[] leaveDate = new String[5];
		for(int i=0;i<4;i++){
			leaveDate[i] = "无";
		}
		for(int i=0;i<4;i++){
			if(!leaveTime[i].equals("无")){
				String[] parts = leaveTime[i].split(" ");
				leaveDate[i] = parts[0];
			}
		}
		return leaveDate;
	}
	
	public String getLatestLeaveTime(){
		for(int i=0;i<3;i++){
			if(( !leaveTime[i].equals("无") ) && (leaveTime[i+1].equals("无")))
				return this.leaveTime[i];	
		}
		return "无";
	}
	
	public String getThisRepertoryLeaveTime(String repertoryID){
		for(int i=0;i<4;i++){
			if(this.leaveRepertoryID[i].equals(repertoryID))
				return leaveTime[i];
		}
		return "has not left this repertory";
	}
	
	public void setLeaveTime(String leaveTime){
		for(int i=0;i<4;i++){
			if(this.leaveTime[i].equals("无")){
				this.leaveTime[i] = leaveTime;
				break;
			}
		}
	}
	
	public String[] getEnterRepertoryID(){
		return this.enterRepertoryID;
	}
	
	
	public String getLatestEnterRepertoryID(){
		for(int i=0;i<3;i++){
			if((!this.enterRepertoryID[i].equals("无"))&&(this.enterRepertoryID[i+1].equals("无")))
				return this.enterRepertoryID[i];	
		}
		return "无";
	}
	
	public void setEnterRepertoryID(String enterRepertoryID){
		for(int i=0;i<4;i++){
			if(this.enterRepertoryID[i].equals("无")){
				this.enterRepertoryID[i] = enterRepertoryID;
				break;
			}
		}
	}
	
	public String[] getLeaveRepertoryID(){
		return this.leaveRepertoryID;
	}
	
	public String getLatestLeaveRepertoryID(){
		for(int i=0;i<3;i++){
			if((!this.leaveRepertoryID[i].equals("无"))&&(this.leaveRepertoryID[i+1].equals("无")))
				return this.leaveRepertoryID[i];	
		}
		return "无";
	}
	
	public void setLeaveRepertoryID(String leaveRepertoryID){
		for(int i=0;i<4;i++){
			if(this.leaveRepertoryID[i].equals("无")){
				this.leaveRepertoryID[i] = leaveRepertoryID;
				break;
			}
		}
	}

	public boolean isInRepertory() {
		return this.inRepertory;
	}

	public void setInRepertory(boolean b) {
		this.inRepertory = b;
	}
	
}
