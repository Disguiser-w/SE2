package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BusinessStatementReceiptPO;

public interface BusinessstatementReceiptDataService {
	//返回一个BusinessstatementReceiptPO记录
	public BusinessStatementReceiptPO showBSL(String beginTime,String endTime) throws RemoteException;
	//返回BusinessstatementReceiptPO的ArrayList
	public ArrayList<BusinessStatementReceiptPO> showAllBSList()throws RemoteException;
	//导出excel
	public int export(BusinessStatementReceiptPO po)throws RemoteException;

}
