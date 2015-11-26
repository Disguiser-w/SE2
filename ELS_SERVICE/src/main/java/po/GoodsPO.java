package po;

import java.io.Serializable;

public class GoodsPO implements Serializable{
	
	private static final long serialVersionUID = 141250149L;
	
	private String Order_ID;
	private double fee;
	private String departurePlace, destination;
	private String enterTime[], leaveTime[], enterRepertoryID[], leaveRepertoryID[];
	
	public GoodsPO(String Order_ID, double fee, String departurePlace, String destination){
		this.Order_ID = Order_ID;
		this.fee = fee;
		this.departurePlace = departurePlace;
		this.destination = destination;
		enterTime = new String[4];
		leaveTime = new String[4];
		enterRepertoryID = new String[4];
		leaveRepertoryID = new String[4];
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
			if(enterTime[i] != null){
				String[] parts = enterTime[i].split(" ");
				enterDate[i] = parts[0];
			}
		}
		return enterDate;
	}
	
	public String getLatestEnterTime(){
		for(int i=0;i<3;i++){
			if((this.enterTime[i] != null)&&(this.enterTime[i+1] == null))
				return this.enterTime[i];	
		}
		return null;
	}
	
	public void setEnterTime(String enterTime){
		for(int i=0;i<4;i++){
			if(this.enterTime[i] == null)
			this.enterTime[i] = enterTime;
		}
	}
	
	public String[] getLeaveTime(){
		return this.leaveTime;
	}
	
	public String[] getLeaveDate(){
		String[] leaveDate = new String[4];
		for(int i=0;i<4;i++){
			if(leaveTime[i] != null){
				String[] parts = leaveTime[i].split(" ");
				leaveDate[i] = parts[0];
			}
		}
		return leaveDate;
	}
	
	public String getLatestLeaveTime(){
		for(int i=0;i<3;i++){
			if((this.leaveTime[i] != null)&&(this.leaveTime[i+1] == null))
				return this.leaveTime[i];	
		}
		return null;
	}
	
	public void setLeaveTime(String leaveTime){
		for(int i=0;i<4;i++){
			if(this.leaveTime[i] == null)
			this.leaveTime[i] = leaveTime;
		}
	}
	
	public String[] getEnterRepertoryID(){
		return this.enterRepertoryID;
	}
	
	public String getLatestEnterRepertoryID(){
		for(int i=0;i<3;i++){
			if((this.enterRepertoryID[i] != null)&&(this.enterRepertoryID[i+1] == null))
				return this.enterRepertoryID[i];	
		}
		return null;
	}
	
	public void setEnterRepertoryID(String enterRepertoryID){
		for(int i=0;i<4;i++){
			if(this.enterRepertoryID[i] == null)
			this.enterRepertoryID[i] = enterRepertoryID;
		}
	}
	
	public String[] getLeaveRepertoryID(){
		return this.leaveRepertoryID;
	}
	
	public String getLatestLeaveRepertoryID(){
		for(int i=0;i<3;i++){
			if((this.leaveRepertoryID[i] != null)&&(this.leaveRepertoryID[i+1] == null))
				return this.leaveRepertoryID[i];	
		}
		return null;
	}
	
	public void setLeaveRepertoryID(String leaveRepertoryID){
		for(int i=0;i<4;i++){
			if(this.leaveRepertoryID[i] == null)
			this.leaveRepertoryID[i] = leaveRepertoryID;
		}
	}
	
}
