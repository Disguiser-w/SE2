package po;

import java.io.Serializable;

import vo.GoodsVO;

public class GoodsPO implements Serializable{
	
	private static final long serialVersionUID = 2L;
	private String Order_ID;
	private String departurePlace, destination;
	private String enterDate[], leaveDate[];
	
	public GoodsPO(String Order_ID, String departurePlace, String destination){
		this.Order_ID = Order_ID;
		this.departurePlace = departurePlace;
		this.destination = destination;
		enterDate = new String[4];
		leaveDate = new String[4];
	}
	
	public GoodsPO(String Order_ID, String departurePlace, String destination, String[] enterDate, String[] leaveDate){
		this.Order_ID = Order_ID;
		this.departurePlace = departurePlace;
		this.destination = destination;
		this.enterDate = enterDate;
		this.leaveDate = leaveDate;
	}
	
	public String getOrder_ID(){
		return this.Order_ID;
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
	
	public void setDeatination(String destination){
		this.destination = destination;
	}
	
	public void setEnterDate(String enterDate){
		for(int i=0;i<4;i++){
			if(this.enterDate[i] == null)
			this.enterDate[i] = enterDate;
		}
	}
	
	public String[] getEnterDate(){
		return this.enterDate;
	}
	
	public void setLeaveDate(String leaveDate){
		for(int i=0;i<4;i++){
			if(this.leaveDate[i] == null)
			this.leaveDate[i] = leaveDate;
		}
	}
	
	public String[] getLeaveDate(){
		return this.leaveDate;
	}
	
}
