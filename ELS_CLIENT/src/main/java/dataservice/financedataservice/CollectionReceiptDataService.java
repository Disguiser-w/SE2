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
//	public int getNum() throws RemoteException;
//	//根据ID查找入款单
//	public CollectionReceiptPO findByID(String ID) throws RemoteException;
//	//修改持久化对象
//	public CollectionReceiptPO modify(CollectionReceiptPO po) throws RemoteException;
//	
	//获取经营情况表需要
	public ArrayList<CollectionReceiptPO> getCollection_right(String beginTime,String endTime) throws RemoteException;
	//获取未审批的合计收款单(审批单据需要)
		public ArrayList<CollectionReceiptPO> getUnapprovedCollectionReceipt() throws RemoteException;

		
		//供总经理调用来存储已经审批结束的单据
		public int saveSubmittedCollectionReceiptInfo(CollectionReceiptPO po) throws RemoteException;

}

/*package dataservice.financedataservice;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;

public interface CollectionReceiptDataService {
	//创建入款单
	public int createCollection(CollectionReceiptPO po);
	//显示所有的入款单
	public ArrayList<CollectionReceiptPO> getAllCollection();
	//显示规定时间内的所有收款单
//	public ArrayList<GatheringReceiptPO> getGathering(String Time);
	//获取收款单金额总和
//	public ArrayList<Double>  getMoney(ArrayList<GatheringReceiptPO> pos);
//	public double getTotalMoney(ArrayList<Double> money);
	public double getTotalMoney(ArrayList<GatheringReceiptPO> pos);
	//当天存储的持久化对象个数
	public int getNum();
	//根据ID查找入款单
	public CollectionReceiptPO findByID(String ID);
	//修改持久化对象
	public CollectionReceiptPO modify(CollectionReceiptPO po);
	
	
	
}
*/
