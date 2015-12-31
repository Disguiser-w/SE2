package businesslogicservice.repertoryblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.LeaveRepertoryReceiptVO;

public interface LeaveRepertoryReceiptBLService {

	public int addLeaveRepertoryReceipt(LeaveRepertoryReceiptVO lrrvo) throws RemoteException;
	//public int deleteLeaveReceipt(String receiptID) throws RemoteException;
	public int sendLeaveReceipt(String receiptID)throws RemoteException;
	public int approveLeaveReceipt(String receiptID)throws RemoteException;
	public int disapproveLeaveReceipt(String receiptID)throws RemoteException;
	public LeaveRepertoryReceiptVO findLeaveReceiptByReceiptID(String receiptID) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptVO> findLeaveReceiptByCreatorID(String creatorID) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptVO> findLeaveReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptVO> getAllSubmitedLeaveReceipts() throws RemoteException;
	public ArrayList<LeaveRepertoryReceiptVO> getAllLeaveReceipts() throws RemoteException;
	public String getLeaveReceiptPost() throws RemoteException;
		
}
