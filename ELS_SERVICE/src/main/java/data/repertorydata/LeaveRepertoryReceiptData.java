package data.repertorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.repertorydataservice.LeaveRepertoryReceiptDataService;
import po.LeaveRepertoryReceiptPO;
import type.ReceiptState;
import file.JXCFile;

public class LeaveRepertoryReceiptData extends UnicastRemoteObject implements LeaveRepertoryReceiptDataService{

	private static final long serialVersionUID = 13L;
	
	JXCFile leaveReceiptFile;
	
	public LeaveRepertoryReceiptData() throws RemoteException{
		leaveReceiptFile = new JXCFile("info/repertoryInfo/leaveReceipt.ser");
	}
	
	public int addLeaveRepertoryReceipt(LeaveRepertoryReceiptPO lrrpo) throws RemoteException{
		leaveReceiptFile.write(lrrpo);
		return 0;
	}
	
	public int deleteLeaveReceipt(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		
		int returnNum = 1;
		
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				objectList.remove(lrrpo);
				returnNum = 0;
			}
		}
		leaveReceiptFile.writeM(objectList);
		return returnNum;
	}

	public int sendLeaveReceipt(String receiptID)throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.SUBMIT);
				returnNum = 0;
			}
		}
		leaveReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	public int approveLeaveReceipt(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.APPROVE);
				returnNum = 0;
			}
		}
		leaveReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	
	public int disapproveLeaveReceipt(String receiptID)throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.DISAPPROVE);
				returnNum = 0;
			}
		}
		leaveReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	
	public LeaveRepertoryReceiptPO findLeaveReceiptByReceiptID(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				return lrrpo;
			}
		}
		return null;
	}

	public ArrayList<LeaveRepertoryReceiptPO> findLeaveReceiptByCreatorID(String creatorID) throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptPOList = new ArrayList<LeaveRepertoryReceiptPO>();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getUserID().equals(creatorID)){
				leaveRepertoryReceiptPOList.add(lrrpo);
			}
		}
		
		return leaveRepertoryReceiptPOList;
	}
	
	public ArrayList<LeaveRepertoryReceiptPO> findLeaveReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptPOListByCreator = findLeaveReceiptByCreatorID(creator); 
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptPOListByCreatorAndKeyword = new ArrayList<LeaveRepertoryReceiptPO>();
		
		for(int i=0,size = leaveRepertoryReceiptPOListByCreator.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)leaveRepertoryReceiptPOListByCreator.get(i);
			if(lrrpo.getID().contains(keyword)){
				leaveRepertoryReceiptPOListByCreatorAndKeyword.add(lrrpo);
			}
		}
		return leaveRepertoryReceiptPOListByCreatorAndKeyword;
	}
	
	public ArrayList<LeaveRepertoryReceiptPO> getAllSubmitedLeaveReceipts() throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		ArrayList<LeaveRepertoryReceiptPO> leaveReceiptList = new ArrayList<LeaveRepertoryReceiptPO>();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getState().equals(ReceiptState.SUBMIT)){
				leaveReceiptList.add(lrrpo);
			}
		}
		return leaveReceiptList;
	}
	
	
	public ArrayList<LeaveRepertoryReceiptPO> getAllLeaveReceipts() throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		ArrayList<LeaveRepertoryReceiptPO> leaveReceiptList = new ArrayList<LeaveRepertoryReceiptPO>();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			leaveReceiptList.add(lrrpo);
		}
		return leaveReceiptList;
	}
	
	
	public String getLeaveReceiptPost() throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();

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
		LeaveRepertoryReceiptData lrrData;
		try{
			lrrData = new LeaveRepertoryReceiptData();
			try{
				
				System.out.println("所有出库单");
				ArrayList<LeaveRepertoryReceiptPO> errpoList = lrrData.getAllLeaveReceipts();
				for(LeaveRepertoryReceiptPO lrrpo : errpoList){
					System.out.println(lrrpo.receiptID+" "+lrrpo.userID+" "+lrrpo.getCreateTime()+" "+lrrpo.getRepertoryID()+" "+lrrpo.state);
					for(int i=0,size = lrrpo.getExpressIDList().length; i<size; i++){
						System.out.println(lrrpo.getExpressIDList()[i]+"  出库时间: "+lrrpo.getTimeList()[i]);
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
