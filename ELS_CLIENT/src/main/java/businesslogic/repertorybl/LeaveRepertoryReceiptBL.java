package businesslogic.repertorybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LeaveRepertoryReceiptPO;
import vo.LeaveRepertoryReceiptVO;
import businesslogic.datafactory.DataFactory;
import dataservice.repertorydataservice.LeaveRepertoryReceiptDataService;

public class LeaveRepertoryReceiptBL {

public static LeaveRepertoryReceiptDataService errdService;
	
	public LeaveRepertoryReceiptBL(){
		try {
			errdService = DataFactory.getLeaveRepertoryReceiptData();
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public int addLeaveRepertoryReceipt(LeaveRepertoryReceiptVO errvo){
		try{
			return errdService.addLeaveRepertoryReceipt(leaveRepertoryReceiptVOToPO(errvo));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int deleteLeaveReceipt(String receiptID){
		try{
			return errdService.deleteLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}

	public int sendLeaveReceipt(String receiptID){
		try{
			return errdService.sendLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int approveLeaveReceipt(String receiptID){
		try{
			return errdService.approveLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int disapproveLeaveReceipt(String receiptID){
		try{
			return errdService.disapproveLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public LeaveRepertoryReceiptVO findLeaveReceiptByReceiptID(String receiptID){
		try{
			return leaveRepertoryReceiptPOToVO(errdService.findLeaveReceiptByReceiptID(receiptID));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<LeaveRepertoryReceiptVO> findLeaveReceiptByCreatorID(String creatorID){
		ArrayList<LeaveRepertoryReceiptVO> leaveReceiptVOList = new ArrayList<LeaveRepertoryReceiptVO>();
		
		try{
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = errdService.findLeaveReceiptByCreatorID(creatorID);
			
			for(int i=0, size = leaveReceiptPOList.size(); i<size;i++){
				LeaveRepertoryReceiptPO leaveRepertoryReceiptPO = leaveReceiptPOList.get(i);
				leaveReceiptVOList.add(leaveRepertoryReceiptPOToVO(leaveRepertoryReceiptPO));
			}
			return leaveReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return leaveReceiptVOList;
		}
	}
	
	public ArrayList<LeaveRepertoryReceiptVO> findLeaveReceiptByCreatorAndKeyword(String creator, String keyword){
		ArrayList<LeaveRepertoryReceiptVO> leaveReceiptVOList = new ArrayList<LeaveRepertoryReceiptVO>();
		
		try{
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = errdService.findLeaveReceiptByCreatorAndKeyword(creator, keyword);
			
			for(int i=0, size = leaveReceiptPOList.size(); i<size;i++){
				LeaveRepertoryReceiptPO leaveRepertoryReceiptPO = leaveReceiptPOList.get(i);
				leaveReceiptVOList.add(leaveRepertoryReceiptPOToVO(leaveRepertoryReceiptPO));
			}
			return leaveReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return leaveReceiptVOList;
		}
	}
	
	public ArrayList<LeaveRepertoryReceiptVO> getAllSubmitedLeaveReceipts(){
		ArrayList<LeaveRepertoryReceiptVO> leaveReceiptVOList = new ArrayList<LeaveRepertoryReceiptVO>();
		
		try{
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = errdService.getAllSubmitedLeaveReceipts();
			
			for(int i=0, size = leaveReceiptPOList.size(); i<size;i++){
				LeaveRepertoryReceiptPO leaveRepertoryReceiptPO = leaveReceiptPOList.get(i);
				leaveReceiptVOList.add(leaveRepertoryReceiptPOToVO(leaveRepertoryReceiptPO));
			}
			return leaveReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return leaveReceiptVOList;
		}
	}
	
	
	public ArrayList<LeaveRepertoryReceiptVO> getAllLeaveReceipts(){
		ArrayList<LeaveRepertoryReceiptVO> leaveReceiptVOList = new ArrayList<LeaveRepertoryReceiptVO>();
		
		try{
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = errdService.getAllLeaveReceipts();
			
			for(int i=0, size = leaveReceiptPOList.size(); i<size;i++){
				LeaveRepertoryReceiptPO leaveRepertoryReceiptPO = leaveReceiptPOList.get(i);
				leaveReceiptVOList.add(leaveRepertoryReceiptPOToVO(leaveRepertoryReceiptPO));
			}
			return leaveReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return leaveReceiptVOList;
		}
	}
	
	public String getLeaveReceiptPost(){
		try{
			return errdService.getLeaveReceiptPost();
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "wrong";
		}
	}
	
	public static LeaveRepertoryReceiptPO leaveRepertoryReceiptVOToPO(LeaveRepertoryReceiptVO errvo){
		return new LeaveRepertoryReceiptPO(errvo.receiptID, errvo.userID, errvo.createTime, errvo.state, errvo.repertoryID, errvo.expressIDList, errvo.timeList);
	}
	
	public static LeaveRepertoryReceiptVO leaveRepertoryReceiptPOToVO(LeaveRepertoryReceiptPO errpo){
		return new LeaveRepertoryReceiptVO(errpo.getReceiptID(), errpo.getUserID(), errpo.getCreateTime(), errpo.getState(), errpo.getReceiptID(), errpo.getExpressIDList(), errpo.getTimeList());
	}
	
}
