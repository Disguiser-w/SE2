/*
 * 表示收款单数据------与盛盛的名字要一样哒
 * */
package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GatheringReceiptVO {
	public  OrganizationVO businesshall;
	public  String time;
	public  ArrayList<ExpressVO> expressList;
	public  ArrayList<Double> money;
	public  double totalmoney;
	
	  public GatheringReceiptVO(){
	   }
	   
	public GatheringReceiptVO(OrganizationVO businesshall, String time, ArrayList<ExpressVO> expressList,
			ArrayList<Double> money, double totalmoney) {
		super();
		this.businesshall = businesshall;
		this.time = time;
		this.expressList = expressList;
		this.money = money;
		this.totalmoney = totalmoney;
	}
	

   String HallId; //这个也加一下
   String Id;
   
 
//   public GatheringReceiptPO(ArrayList<ExpressPO> expressList, ArrayList<Double> money){
//   	this.expressList = expressList;
//   	this.money = money;
//   	Date date = new Date();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		this.time = format.format(date);
//   }

//	public OrganizationPO getBusinesshall() {
//		return businesshall;
//	}

//	public void setBusinesshall(OrganizationPO businesshall) {
//		this.businesshall = businesshall;
//	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

//	public ArrayList<ExpressPO> getExpressList() {
//		return expressList;
//	}
//
//	public void setExpressList(ArrayList<ExpressPO> expressList) {
//		this.expressList = expressList;
//	}

	public ArrayList<Double> getMoney() {
		return money;
	}

	public void setMoney(ArrayList<Double> money) {
		this.money = money;
	}

	public double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	//加一下
	public String getHallId(){
		return HallId;	
	}
	
	public String getID(){
		return Id;
	}
   


}
