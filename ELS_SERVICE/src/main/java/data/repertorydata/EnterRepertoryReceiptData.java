package data.repertorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.repertorydataservice.EnterRepertoryReceiptDataService;
import po.EnterRepertoryReceiptPO;
import type.ReceiptState;
import file.JXCFile;

public class EnterRepertoryReceiptData extends UnicastRemoteObject implements EnterRepertoryReceiptDataService{

	private static final long serialVersionUID = 40L;
	
	JXCFile enterReceiptFile;
	
	public EnterRepertoryReceiptData() throws RemoteException{
		enterReceiptFile = new JXCFile("info/repertoryInfo/enterReceipt.ser");
	}
	
	public int addEnterRepertoryReceipt(EnterRepertoryReceiptPO errpo) throws RemoteException{
		enterReceiptFile.write(errpo);
		return 0;
	}
	
	public int deleteEnterReceipt(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		
		int returnNum = 1;
		
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				objectList.remove(lrrpo);
				returnNum = 0;
			}
		}
		enterReceiptFile.writeM(objectList);
		return returnNum;
	}

	public int sendEnterReceipt(String receiptID)throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.SUBMIT);
				returnNum = 0;
			}
		}
		enterReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	
	public int approveEnterReceipt(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.APPROVE);
				returnNum = 0;
			}
		}
		enterReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	
	public int disapproveEnterReceipt(String receiptID)throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.DISAPPROVE);
				returnNum = 0;
			}
		}
		enterReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	
	public EnterRepertoryReceiptPO findEnterReceiptByReceiptID(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				return lrrpo;
			}
		}
		return null;
	}

	public ArrayList<EnterRepertoryReceiptPO> findEnterReceiptByCreatorID(String creatorID) throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptPOList = new ArrayList<EnterRepertoryReceiptPO>();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getUserID().equals(creatorID)){
				enterRepertoryReceiptPOList.add(lrrpo);
			}
		}
		
		return enterRepertoryReceiptPOList;
	}
	
	public ArrayList<EnterRepertoryReceiptPO> findEnterReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptPOListByCreator = findEnterReceiptByCreatorID(creator); 
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptPOListByCreatorAndKeyword = new ArrayList<EnterRepertoryReceiptPO>();
		
		for(int i=0,size = enterRepertoryReceiptPOListByCreator.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)enterRepertoryReceiptPOListByCreator.get(i);
			if(lrrpo.getID().contains(keyword)){
				enterRepertoryReceiptPOListByCreatorAndKeyword.add(lrrpo);
			}
		}
		return enterRepertoryReceiptPOListByCreatorAndKeyword;
	}
	
	public ArrayList<EnterRepertoryReceiptPO> getAllSubmitedEnterReceipts() throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		ArrayList<EnterRepertoryReceiptPO> enterReceiptList = new ArrayList<EnterRepertoryReceiptPO>();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getState().equals(ReceiptState.SUBMIT)){
				enterReceiptList.add(lrrpo);
			}
		}
		return enterReceiptList;
	}
	
	
	public ArrayList<EnterRepertoryReceiptPO> getAllEnterReceipts() throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		ArrayList<EnterRepertoryReceiptPO> enterReceiptList = new ArrayList<EnterRepertoryReceiptPO>();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			enterReceiptList.add(lrrpo);
		}
		return enterReceiptList;
	}
	
	public String getEnterReceiptPost() throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();

		if(objectList == null)
			return "00001";
		
		int size = objectList.size();
		int post = size + 1;
		if(post <= 9)
			return "0000"+(post);
		else if(post > 9 && post <= 99)
			return "000"+post;
		else
			return "00"+post;
	}
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
	
     public static void main(String[] args){
		EnterRepertoryReceiptData errData;
		try{
			errData = new EnterRepertoryReceiptData();
			try{
				
				System.out.println("所有入库单");
				ArrayList<EnterRepertoryReceiptPO> errpoList = errData.getAllEnterReceipts();
				for(EnterRepertoryReceiptPO errpo : errpoList){
					System.out.println(errpo.receiptID+" "+errpo.userID+" "+errpo.getCreateTime()+" "+errpo.getRepertoryID()+" "+errpo.state);
					for(int i=0,size = errpo.getExpressIDList().length; i<size; i++){
						System.out.println(errpo.getExpressIDList()[i]+"  入库时间: "+errpo.getTimeList()[i]);
					}
				}
				
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}
     
}
