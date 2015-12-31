package businesslogic.repertorybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.datafactory.DataFactory;
import dataservice.repertorydataservice.EnterRepertoryReceiptDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import po.EnterRepertoryReceiptPO;
import type.ReceiptState;
import vo.EnterRepertoryReceiptVO;

public class EnterRepertoryReceiptBL {

	public static RepertoryDataService rdService;
	public static EnterRepertoryReceiptDataService errdService;
	
	public EnterRepertoryReceiptBL(){
		try {
			rdService = DataFactory.getRepertoryData();
			errdService = DataFactory.getEnterRepertoryReceiptData();
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public int addEnterRepertoryReceipt(String repertoryID, String userID, String[] goodsIDList, String[] timeList){
		String now = getTimeNow();
		try{
			String receiptPost = errdService.getEnterRepertoryReceiptPost();
			EnterRepertoryReceiptVO errvo = new EnterRepertoryReceiptVO("RKD-"+receiptPost, userID, now, ReceiptState.DRAFT, repertoryID, goodsIDList, timeList);
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptVOToPO(errvo);
			
			//更新仓库的最近一次生成入库单的时间
			int returnNum1 = rdService.setLastCreateEnterReceiptTime(repertoryID, now);
			//添加入库单
			int returnNum2 = errdService.addEnterRepertoryReceipt(errpo);
			
			return returnNum1+returnNum2;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	/*public int deleteEnterReceipt(String receiptID){
		try{
			return errdService.deleteEnterRepertoryReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}*/

	public int sendEnterReceipt(String receiptID){
		try{
			return errdService.sendEnterRepertoryReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int approveEnterReceipt(String receiptID){
		try{
			return errdService.approveEnterRepertoryReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int disapproveEnterReceipt(String receiptID){
		try{
			return errdService.disapproveEnterRepertoryReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public EnterRepertoryReceiptVO findEnterReceiptByReceiptID(String receiptID){
		try{
			return enterRepertoryReceiptPOToVO(errdService.findEnterRepertoryReceiptByReceiptID(receiptID));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<EnterRepertoryReceiptVO> findEnterReceiptByCreatorID(String creatorID){
		ArrayList<EnterRepertoryReceiptVO> enterReceiptVOList = new ArrayList<EnterRepertoryReceiptVO>();
		
		try{
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.findEnterRepertoryReceiptByCreatorID(creatorID);
			
			for(int i=0, size = enterReceiptPOList.size(); i<size;i++){
				EnterRepertoryReceiptPO enterRepertoryReceiptPO = enterReceiptPOList.get(i);
				enterReceiptVOList.add(enterRepertoryReceiptPOToVO(enterRepertoryReceiptPO));
			}
			return enterReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return enterReceiptVOList;
		}
	}
	
	public ArrayList<EnterRepertoryReceiptVO> findEnterReceiptByCreatorAndKeyword(String creator, String keyword){
		ArrayList<EnterRepertoryReceiptVO> enterReceiptVOList = new ArrayList<EnterRepertoryReceiptVO>();
		
		try{
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.findEnterRepertoryReceiptByCreatorAndKeyword(creator, keyword);
			
			for(int i=0, size = enterReceiptPOList.size(); i<size;i++){
				EnterRepertoryReceiptPO enterRepertoryReceiptPO = enterReceiptPOList.get(i);
				enterReceiptVOList.add(enterRepertoryReceiptPOToVO(enterRepertoryReceiptPO));
			}
			return enterReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return enterReceiptVOList;
		}
	}
	
	public ArrayList<EnterRepertoryReceiptVO> getAllSubmitedEnterReceipts(){
		ArrayList<EnterRepertoryReceiptVO> enterReceiptVOList = new ArrayList<EnterRepertoryReceiptVO>();
		
		try{
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.getAllSubmitedEnterRepertoryReceipts();
			
			for(int i=0, size = enterReceiptPOList.size(); i<size;i++){
				EnterRepertoryReceiptPO enterRepertoryReceiptPO = enterReceiptPOList.get(i);
				enterReceiptVOList.add(enterRepertoryReceiptPOToVO(enterRepertoryReceiptPO));
			}
			return enterReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return enterReceiptVOList;
		}
	}
	
	
	public ArrayList<EnterRepertoryReceiptVO> getAllEnterReceipts(){
		ArrayList<EnterRepertoryReceiptVO> enterReceiptVOList = new ArrayList<EnterRepertoryReceiptVO>();
		
		try{
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.getAllEnterRepertoryReceipts();
			
			for(int i=0, size = enterReceiptPOList.size(); i<size;i++){
				EnterRepertoryReceiptPO enterRepertoryReceiptPO = enterReceiptPOList.get(i);
				enterReceiptVOList.add(enterRepertoryReceiptPOToVO(enterRepertoryReceiptPO));
			}
			return enterReceiptVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return enterReceiptVOList;
		}
	}
	
	public String getEnterReceiptPost(){
		try{
			return errdService.getEnterRepertoryReceiptPost();
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "wrong";
		}
	}
	
	public static EnterRepertoryReceiptPO enterRepertoryReceiptVOToPO(EnterRepertoryReceiptVO errvo){
		return new EnterRepertoryReceiptPO(errvo.receiptID, errvo.userID, errvo.createTime, errvo.state, errvo.repertoryID, errvo.expressIDList, errvo.timeList);
	}
	
	public static EnterRepertoryReceiptVO enterRepertoryReceiptPOToVO(EnterRepertoryReceiptPO errpo){
		return new EnterRepertoryReceiptVO(errpo.getReceiptID(), errpo.getUserID(), errpo.getCreateTime(), errpo.getState(), errpo.getReceiptID(), errpo.getExpressIDList(), errpo.getTimeList());
	}
	
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
}
