package dataservice.financedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;

public interface CollectionReceiptDataService extends Remote{
	public int createCollection(CollectionReceiptPO po) throws RemoteException;
	public ArrayList<CollectionReceiptPO> getAllCollection() throws RemoteException;
	
//   public ArrayList<GatheringReceiptPO> getGathering(String Time) throws RemoteException;
//	public ArrayList<Double>  getMoney(GatheringReceiptPO po) throws RemoteException;
//	public double getTotalMoney(ArrayList<GatheringReceiptPO> gathering) throws RemoteException;
	//当天存储的持久化对象个数
	public int getNum() throws RemoteException;
	//根据ID查找入款单
	public CollectionReceiptPO findByID(String ID) throws RemoteException;
	//修改持久化对象
	public CollectionReceiptPO modify(CollectionReceiptPO po) throws RemoteException;
	
	//获取经营情况表需要
	public ArrayList<CollectionReceiptPO> getCollection_right(String beginTime,String endTime);
	

}
