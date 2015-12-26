package businesslogic.repertorybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.LeaveRepertoryReceiptPO;
import type.ReceiptState;
import vo.LeaveRepertoryReceiptVO;
import businesslogic.datafactory.DataFactory;
import dataservice.repertorydataservice.LeaveRepertoryReceiptDataService;
import dataservice.repertorydataservice.RepertoryDataService;

public class LeaveRepertoryReceiptBL {

	public static RepertoryDataService rdService;
	public static LeaveRepertoryReceiptDataService lrrdService;
	
	public LeaveRepertoryReceiptBL(){
		try {
			rdService = DataFactory.getRepertoryData();
			lrrdService = DataFactory.getLeaveRepertoryReceiptData();
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public int addLeaveRepertoryReceipt(String repertoryID, String userID, String[] goodsIDList, String[] timeList){
		String now = getTimeNow();
		try{
			String receiptPost = lrrdService.getLeaveReceiptPost();
			LeaveRepertoryReceiptVO lrrvo = new LeaveRepertoryReceiptVO("CKD-"+receiptPost, userID, now, ReceiptState.DRAFT, repertoryID, goodsIDList, timeList);
			LeaveRepertoryReceiptPO lrrpo = leaveRepertoryReceiptVOToPO(lrrvo);
			
			//更新仓库的最近一次生成入库单的时间
			int returnNum1 = rdService.setLastCreateLeaveReceiptTime(repertoryID, now);
			//添加入库单
			int returnNum2 = lrrdService.addLeaveRepertoryReceipt(lrrpo);
			
			return returnNum1+returnNum2;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int deleteLeaveReceipt(String receiptID){
		try{
			return lrrdService.deleteLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}

	public int sendLeaveReceipt(String receiptID){
		try{
			return lrrdService.sendLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int approveLeaveReceipt(String receiptID){
		try{
			return lrrdService.approveLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int disapproveLeaveReceipt(String receiptID){
		try{
			return lrrdService.disapproveLeaveReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public LeaveRepertoryReceiptVO findLeaveReceiptByReceiptID(String receiptID){
		try{
			return leaveRepertoryReceiptPOToVO(lrrdService.findLeaveReceiptByReceiptID(receiptID));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<LeaveRepertoryReceiptVO> findLeaveReceiptByCreatorID(String creatorID){
		ArrayList<LeaveRepertoryReceiptVO> leaveReceiptVOList = new ArrayList<LeaveRepertoryReceiptVO>();
		
		try{
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = lrrdService.findLeaveReceiptByCreatorID(creatorID);
			
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
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = lrrdService.findLeaveReceiptByCreatorAndKeyword(creator, keyword);
			
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
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = lrrdService.getAllSubmitedLeaveReceipts();
			
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
			ArrayList<LeaveRepertoryReceiptPO> leaveReceiptPOList = lrrdService.getAllLeaveReceipts();
			
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
			return lrrdService.getLeaveReceiptPost();
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "wrong";
		}
	}
	
	public static LeaveRepertoryReceiptPO leaveRepertoryReceiptVOToPO(LeaveRepertoryReceiptVO lrrvo){
		return new LeaveRepertoryReceiptPO(lrrvo.receiptID, lrrvo.userID, lrrvo.createTime, lrrvo.state, lrrvo.repertoryID, lrrvo.expressIDList, lrrvo.timeList);
	}
	
	public static LeaveRepertoryReceiptVO leaveRepertoryReceiptPOToVO(LeaveRepertoryReceiptPO lrrpo){
		return new LeaveRepertoryReceiptVO(lrrpo.getReceiptID(), lrrpo.getUserID(), lrrpo.getCreateTime(), lrrpo.getState(), lrrpo.getReceiptID(), lrrpo.getExpressIDList(), lrrpo.getTimeList());
	}
	
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
}
