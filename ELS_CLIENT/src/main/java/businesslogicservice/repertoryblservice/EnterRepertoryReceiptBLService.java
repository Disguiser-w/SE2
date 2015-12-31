package businesslogicservice.repertoryblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.EnterRepertoryReceiptVO;

public interface EnterRepertoryReceiptBLService {

	public int addEnterRepertoryReceipt(String repertoryID, String userID, String[] goodsIDList, String[] timeList) throws RemoteException;
	public int sendEnterReceipt(String receiptID)throws RemoteException;
	public int approveEnterReceipt(String receiptID)throws RemoteException;
	public int disapproveEnterReceipt(String receiptID)throws RemoteException;
	public EnterRepertoryReceiptVO findEnterReceiptByReceiptID(String receiptID) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptVO> findEnterReceiptByCreatorID(String creatorID) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptVO> findEnterReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException;
	public ArrayList<EnterRepertoryReceiptVO> getAllSubmitedEnterReceipts() throws RemoteException;
	public ArrayList<EnterRepertoryReceiptVO> getAllEnterReceipts() throws RemoteException;
	public String getEnterReceiptPost() throws RemoteException;
	
}
