package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LeaveRepertoryReceiptPO;

public interface LeaveRepertoryReceiptDataService extends Remote {

	public int addLeaveRepertoryReceipt(LeaveRepertoryReceiptPO lrrpo) throws RemoteException;
	public int deleteLeaveReceipt(String receiptID) throws RemoteException;
	public int approveLeaveReceipt(LeaveRepertoryReceiptPO newLRRpo) throws RemoteException;
	public LeaveRepertoryReceiptPO findLeaveReceipt(String receiptID) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> getAllSubmitedLeaveReceipts() throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptPO> getAllLeaveReceipts() throws RemoteException;
	
}
