package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnterRepertoryReceiptPO;

public interface EnterRepertoryReceiptDataService extends Remote {

	public int addEnterRepertoryReceipt(EnterRepertoryReceiptPO lrrpo) throws RemoteException;
	public int deleteEnterReceipt(String receiptID) throws RemoteException;
	public int approveEnterReceipt(EnterRepertoryReceiptPO newLRRpo) throws RemoteException;
	public EnterRepertoryReceiptPO findEnterReceipt(String receiptID) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> getAllSubmitedEnterReceipts() throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> getAllEnterReceipts() throws RemoteException;
	
}
