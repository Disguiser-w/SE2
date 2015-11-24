package dataservice.financedataservice;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;

public interface CollectionReceiptDataService {
	//创建入款单
	public int createCollection(CollectionReceiptPO po);
	//显示所有的入款单
	public ArrayList<CollectionReceiptPO> getAllCollection();
	//显示规定时间内的所有收款单
	public ArrayList<GatheringReceiptPO> getGathering(String Time);
	//获取收款单金额总和
	public ArrayList<Double>  getMoney(ArrayList<GatheringReceiptPO> pos);
	public double getTotalMoney(ArrayList<Double> money);
	//当天存储的持久化对象个数
	public int getNum();
	//根据ID查找入款单
	public CollectionReceiptPO findByID(String ID);
	//修改持久化对象
	public CollectionReceiptPO modify(CollectionReceiptPO po);
	
	
	
}
