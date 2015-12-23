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

	public int approveLeaveReceipt(LeaveRepertoryReceiptPO newLRRpo) throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(newLRRpo.getID())){
				lrrpo.setState(ReceiptState.APPROVE);
				returnNum = 0;
			}
		}
		leaveReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	public LeaveRepertoryReceiptPO findLeaveReceipt(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = leaveReceiptFile.read();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				return lrrpo;
			}
		}
		return null;
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
	
	
}
