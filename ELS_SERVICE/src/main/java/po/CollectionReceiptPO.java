package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;

//vo不能被序列化，po中存在set
public class CollectionReceiptPO extends ReceiptPO implements Serializable{
	
	private static final long serialVersionUID = 3008819827906560098L;
	
	ArrayList<GatheringReceiptPO> gpo=new ArrayList<GatheringReceiptPO> ();
	double[] money;
	double totalMoney;
	String hallID;
	String date;
	String account;
	public CollectionReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state,double m,String date,String account){
		super(ID,userID,ReceiptType.COLLECTIONRECEIPT,state);
		totalMoney=m;
		this.account=account;
		this.date=date;
	}
	public CollectionReceiptPO(){
		
	}
	
	public ArrayList<GatheringReceiptPO> getGathering(){
		return gpo;
	}
	
	public String getHallID(){
		return hallID;
		}
	
	public String  getDate(){
		return date;
	}
	
//	public double[] getMoney(){
//		return money;
//	}
	
	public double getIncome(){
		return totalMoney;
	}
	
	public void setHallID(String id){
		hallID=id;
	}
	
	public void setDate(String d){
		date=d;
	}
	
	public void setGathering(ArrayList<GatheringReceiptPO> po){
		gpo=po;
	}
	
	public String getAccount(){
		return account;
	}
	

}
