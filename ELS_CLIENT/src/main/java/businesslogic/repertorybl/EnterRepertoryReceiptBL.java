package businesslogic.repertorybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnterRepertoryReceiptPO;
import vo.EnterRepertoryReceiptVO;
import businesslogic.datafactory.DataFactory;
import dataservice.repertorydataservice.EnterRepertoryReceiptDataService;

public class EnterRepertoryReceiptBL {

	public static EnterRepertoryReceiptDataService errdService;
	
	public EnterRepertoryReceiptBL(){
		try {
			errdService = DataFactory.getEnterRepertoryReceiptData();
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public int addEnterRepertoryReceipt(EnterRepertoryReceiptVO errvo){
		try{
			return errdService.addEnterRepertoryReceipt(enterRepertoryReceiptVOToPO(errvo));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int deleteEnterReceipt(String receiptID){
		try{
			return errdService.deleteEnterReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}

	public int sendEnterReceipt(String receiptID){
		try{
			return errdService.sendEnterReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int approveEnterReceipt(String receiptID){
		try{
			return errdService.approveEnterReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public int disapproveEnterReceipt(String receiptID){
		try{
			return errdService.disapproveEnterReceipt(receiptID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	public EnterRepertoryReceiptVO findEnterReceiptByReceiptID(String receiptID){
		try{
			return enterRepertoryReceiptPOToVO(errdService.findEnterReceiptByReceiptID(receiptID));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<EnterRepertoryReceiptVO> findEnterReceiptByCreatorID(String creatorID){
		ArrayList<EnterRepertoryReceiptVO> enterReceiptVOList = new ArrayList<EnterRepertoryReceiptVO>();
		
		try{
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.findEnterReceiptByCreatorID(creatorID);
			
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
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.findEnterReceiptByCreatorAndKeyword(creator, keyword);
			
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
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.getAllSubmitedEnterReceipts();
			
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
			ArrayList<EnterRepertoryReceiptPO> enterReceiptPOList = errdService.getAllEnterReceipts();
			
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
			return errdService.getEnterReceiptPost();
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
	
	
}
