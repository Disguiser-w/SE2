package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BusinessstatementReceiptPO;

public interface BusinessstatementReceiptDataService {
	//返回一个BusinessstatementReceiptPO记录
	public BusinessstatementReceiptPO showBSL(String beginTime,String endTime) throws RemoteException;
	//返回BusinessstatementReceiptPO的ArrayList
	public ArrayList<BusinessstatementReceiptPO> showAllBSList()throws RemoteException;
	//导出excel
	public int export(BusinessstatementReceiptPO po)throws RemoteException;

}
