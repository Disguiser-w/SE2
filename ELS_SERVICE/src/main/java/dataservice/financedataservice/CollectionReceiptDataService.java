package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;

public interface CollectionReceiptDataService {
	public int createCollection(CollectionReceiptPO po) throws RemoteException;
	public ArrayList<CollectionReceiptPO> getAllCollection() throws RemoteException;
	
	public ArrayList<GatheringReceiptPO> getGathering(String HallID,String Time) throws RemoteException;
	public double[]  getMoney(GatheringReceiptPO po) throws RemoteException;
	public double getTotalMoney(int[] money) throws RemoteException;
	
	//当天存储的持久化对象个数
	public int getNum() throws RemoteException;
	//根据ID查找入款单
	public CollectionReceiptPO findByID(String ID) throws RemoteException;
	//修改持久化对象
	public CollectionReceiptPO modify(CollectionReceiptPO po) throws RemoteException;

}
