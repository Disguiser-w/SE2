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
	
	public int addEnterRepertoryReceipt(EnterRepertoryReceiptPO lrrpo) throws RemoteException{
		enterReceiptFile.write(lrrpo);
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

	public int approveEnterReceipt(EnterRepertoryReceiptPO newLRRpo) throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		
		int returnNum = 1;
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(newLRRpo.getID())){
				lrrpo.setState(ReceiptState.APPROVE);
				returnNum = 0;
			}
		}
		enterReceiptFile.writeM(objectList);
		return returnNum;
	}
	
	public EnterRepertoryReceiptPO findEnterReceipt(String receiptID) throws RemoteException{
		ArrayList<Object> objectList = enterReceiptFile.read();
		
		for(int i=0,size = objectList.size(); i<size; i++){
			EnterRepertoryReceiptPO lrrpo = (EnterRepertoryReceiptPO)objectList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				return lrrpo;
			}
		}
		return null;
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
	
	
}
