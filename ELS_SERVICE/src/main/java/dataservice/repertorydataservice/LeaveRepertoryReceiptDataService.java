package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LeaveRepertoryReceiptPO;

public interface LeaveRepertoryReceiptDataService extends Remote {

	public int addLeaveRepertoryReceipt(LeaveRepertoryReceiptPO lrrpo) throws RemoteException;
	public int sendLeaveReceipt(String receiptID)throws RemoteException;
	public int approveLeaveReceipt(String receiptID)throws RemoteException;
	public int disapproveLeaveReceipt(String receiptID)throws RemoteException;
	public LeaveRepertoryReceiptPO findLeaveReceiptByReceiptID(String receiptID) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> findLeaveReceiptByCreatorID(String creatorID) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> findLeaveReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> getAllSubmitedLeaveReceipts() throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> getAllLeaveReceipts() throws RemoteException;
	public String getLeaveReceiptPost() throws RemoteException;
	
}
