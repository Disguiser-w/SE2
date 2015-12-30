package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnterRepertoryReceiptPO;

public interface EnterRepertoryReceiptDataService extends Remote {

	public int addEnterRepertoryReceipt(EnterRepertoryReceiptPO errpo) throws RemoteException;
	public int deleteEnterRepertoryReceipt(String receiptID) throws RemoteException;
	public int sendEnterRepertoryReceipt(String receiptID)throws RemoteException;
	public int approveEnterRepertoryReceipt(String receiptID)throws RemoteException;
	public int disapproveEnterRepertoryReceipt(String receiptID)throws RemoteException;
	public EnterRepertoryReceiptPO findEnterRepertoryReceiptByReceiptID(String receiptID) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> findEnterRepertoryReceiptByCreatorID(String creatorID) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> findEnterRepertoryReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> getAllSubmitedEnterRepertoryReceipts() throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> getAllEnterRepertoryReceipts() throws RemoteException;
	public String getEnterRepertoryReceiptPost() throws RemoteException;
	
}
