package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LeaveRepertoryReceiptPO;

public interface LeaveRepertoryReceiptDataService extends Remote {

	public int addLeaveRepertoryReceipt(LeaveRepertoryReceiptPO lrrpo) throws RemoteException;
	public int sendLeaveRepertoryReceipt(String receiptID)throws RemoteException;
	public int approveLeaveRepertoryReceipt(String receiptID)throws RemoteException;
	public int disapproveLeaveRepertoryReceipt(String receiptID)throws RemoteException;
	public LeaveRepertoryReceiptPO findLeaveRepertoryReceiptByReceiptID(String receiptID) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> findLeaveRepertoryReceiptByCreatorID(String creatorID) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> findLeaveRepertoryReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> getAllSubmitedLeaveRepertoryReceipts() throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> getAllLeaveRepertoryReceipts() throws RemoteException;
	public String getLeaveRepertoryReceiptPost() throws RemoteException;
	
}
