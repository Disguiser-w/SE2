package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnterRepertoryReceiptPO;

public interface EnterRepertoryReceiptDataService extends Remote {

	public int addEnterRepertoryReceipt(EnterRepertoryReceiptPO errpo) throws RemoteException;
	public int sendEnterReceipt(String receiptID) throws RemoteException;
	public int approveEnterReceipt(String receiptID) throws RemoteException;
	public int disapproveEnterReceipt(String receiptID) throws RemoteException;
	public EnterRepertoryReceiptPO findEnterReceiptByReceiptID(String receiptID) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> findEnterReceiptByCreatorID(String creatorID) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> findEnterReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> getAllSubmitedEnterReceipts() throws RemoteException;
	public ArrayList<EnterRepertoryReceiptPO> getAllEnterReceipts() throws RemoteException;
	public String getEnterReceiptPost() throws RemoteException;
	
}
