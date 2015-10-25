package dataservice.financedataservice;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;

public interface CollectionReceiptDataService {
	public int createCollection(CollectionReceiptPO po);
	public ArrayList<CollectionReceiptPO> getAllCollection();
	
	public ArrayList<GatheringReceiptPO> getGathering(String HallID,String Time);
	public double[]  getMoney(GatheringReceiptPO po);
	public double getTotalMoney(int[] money);
	
	//当天存储的持久化对象个数
	public int getNum();
	//根据ID查找入款单
	public CollectionReceiptPO findByID(String ID);
	//修改持久化对象
	public CollectionReceiptPO modify(CollectionReceiptPO po);
	
	
	
}
