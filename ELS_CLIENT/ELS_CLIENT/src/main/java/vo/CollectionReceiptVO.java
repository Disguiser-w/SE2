package vo;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptState;
import type.ReceiptType;

public class CollectionReceiptVO extends ReceiptVO {
	public final ArrayList<GatheringReceiptVO> gvo;
	public final double[] money;
	public final double totalMoney;
	public final String hallID;
	public final String date;
	
	public CollectionReceiptVO(String ID,String userID,ReceiptState state,ArrayList<GatheringReceiptVO> gvo,double[] money,double totalMoney,String hallID,String date){
		super(ID,userID,ReceiptType.COLLECTIONRECEIPT,state);
		this.gvo = gvo;
		this.money =money;
		this.totalMoney = totalMoney;
		this.hallID =hallID;
		this.date = date;
	}
    //account还需不需要呢？？？


}
