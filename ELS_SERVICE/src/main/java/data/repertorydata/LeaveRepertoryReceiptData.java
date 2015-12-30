package data.repertorydata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.FileGetter;

import dataservice.repertorydataservice.LeaveRepertoryReceiptDataService;
import po.LeaveRepertoryReceiptPO;
import type.ReceiptState;

public class LeaveRepertoryReceiptData extends UnicastRemoteObject implements LeaveRepertoryReceiptDataService{

	private static final long serialVersionUID = 40L;
	
	public LeaveRepertoryReceiptData() throws RemoteException{
		super();
	}
	
	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<LeaveRepertoryReceiptPO> getLeaveRepertoryReceiptList() throws RemoteException{
		String path = "repertoryInfo/leaveReceipt.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<LeaveRepertoryReceiptPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<LeaveRepertoryReceiptPO> repertoryList = (ArrayList<LeaveRepertoryReceiptPO>) in.readObject();
			in.close();
			return repertoryList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 写文件（增删改查统一调用它）
	 * 
	 * */
	public int saveLeaveRepertoryReceiptList(ArrayList<LeaveRepertoryReceiptPO> repertoryList) throws RemoteException {
		String path = "repertoryInfo/leaveReceipt.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(repertoryList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * 新增货物（每次AddOrder完成新增一个订单时调该方法）
	 * 
	 * @param LeaveRepertoryReceiptPO leaveRepertoryReceiptpo
	 * @return 0(add succeed), 1(leaveRepertoryReceipt with the ID has already existed)
	 * 
	 * */
	public int addLeaveRepertoryReceipt(LeaveRepertoryReceiptPO leaveRepertoryReceiptpo) throws RemoteException{
    	ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
    	
    	for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
    		LeaveRepertoryReceiptPO tmpLeaveRepertoryReceiptpo = leaveRepertoryReceiptList.get(i);
    		if(tmpLeaveRepertoryReceiptpo.getReceiptID().endsWith(leaveRepertoryReceiptpo.getReceiptID()))
    			return 1;
    	}
    	leaveRepertoryReceiptList.add(leaveRepertoryReceiptpo);
    	saveLeaveRepertoryReceiptList(leaveRepertoryReceiptList);
    	return 0;
    }
    
	
	/**
	 * 删除货物
	 * 
	 * @param String orderID
	 * @return 0(delete succeed), 1(delete failed)
	 * 
	 * */
    public int deleteLeaveRepertoryReceipt(String receiptID) throws RemoteException{
    	ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
			LeaveRepertoryReceiptPO tmpLeaveRepertoryReceiptPO = leaveRepertoryReceiptList.get(i);
			if(tmpLeaveRepertoryReceiptPO.getReceiptID().equals(receiptID)){
				leaveRepertoryReceiptList.remove(i);
				hasExist = true;
				break;
			}
		}
		
		saveLeaveRepertoryReceiptList(leaveRepertoryReceiptList);
		if(hasExist)
			return 0;
		else
			return 1;
    }
    
    
    public int sendLeaveRepertoryReceipt(String receiptID)throws RemoteException{
    	ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
			LeaveRepertoryReceiptPO lrrpo = leaveRepertoryReceiptList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.SUBMIT);
				hasExist = true;
			}
		}
		saveLeaveRepertoryReceiptList(leaveRepertoryReceiptList);
		if(hasExist)
			return 0;
		else 
			return 1;
	}

	
	public int approveLeaveRepertoryReceipt(String receiptID) throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
			LeaveRepertoryReceiptPO lrrpo = leaveRepertoryReceiptList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.APPROVE);
				hasExist = true;
			}
		}
		saveLeaveRepertoryReceiptList(leaveRepertoryReceiptList);
		if(hasExist)
			return 0;
		else 
			return 1;
	}
	
	
	public int disapproveLeaveRepertoryReceipt(String receiptID)throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
			LeaveRepertoryReceiptPO lrrpo = leaveRepertoryReceiptList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				lrrpo.setState(ReceiptState.DISAPPROVE);
				hasExist = true;
			}
		}
		saveLeaveRepertoryReceiptList(leaveRepertoryReceiptList);
		if(hasExist)
			return 0;
		else 
			return 1;
	}
	
	
	public LeaveRepertoryReceiptPO findLeaveRepertoryReceiptByReceiptID(String receiptID) throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
		
		for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
			LeaveRepertoryReceiptPO lrrpo = leaveRepertoryReceiptList.get(i);
			if(lrrpo.getID().equals(receiptID)){
				return lrrpo;
			}
		}
		return null;
	}

	public ArrayList<LeaveRepertoryReceiptPO> findLeaveRepertoryReceiptByCreatorID(String creatorID) throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptPOList = new ArrayList<LeaveRepertoryReceiptPO>();

		for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
			LeaveRepertoryReceiptPO lrrpo = leaveRepertoryReceiptList.get(i);
			if(lrrpo.getUserID().equals(creatorID)){
				leaveRepertoryReceiptPOList.add(lrrpo);
			}
		}
		
		return leaveRepertoryReceiptPOList;
	}
	
	public ArrayList<LeaveRepertoryReceiptPO> findLeaveRepertoryReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptPOListByCreator = findLeaveRepertoryReceiptByCreatorID(creator); 
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptPOListByCreatorAndKeyword = new ArrayList<LeaveRepertoryReceiptPO>();
		
		for(int i=0,size = leaveRepertoryReceiptPOListByCreator.size(); i<size; i++){
			LeaveRepertoryReceiptPO lrrpo = (LeaveRepertoryReceiptPO)leaveRepertoryReceiptPOListByCreator.get(i);
			if(lrrpo.getID().contains(keyword)){
				leaveRepertoryReceiptPOListByCreatorAndKeyword.add(lrrpo);
			}
		}
		return leaveRepertoryReceiptPOListByCreatorAndKeyword;
	}
	
	public ArrayList<LeaveRepertoryReceiptPO> getAllSubmitedLeaveRepertoryReceipts() throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
		ArrayList<LeaveRepertoryReceiptPO> leaveReceiptList = new ArrayList<LeaveRepertoryReceiptPO>();
		
		for(int i=0; i<leaveRepertoryReceiptList.size(); i++){
			LeaveRepertoryReceiptPO lrrpo = leaveRepertoryReceiptList.get(i);
			if(lrrpo.getState().equals(ReceiptState.SUBMIT)){
				leaveReceiptList.add(lrrpo);
			}
		}
		return leaveReceiptList;
	}
	
	
	public ArrayList<LeaveRepertoryReceiptPO> getAllLeaveRepertoryReceipts() throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();
		return leaveRepertoryReceiptList;
	}
	
	public String getLeaveRepertoryReceiptPost() throws RemoteException{
		ArrayList<LeaveRepertoryReceiptPO> leaveRepertoryReceiptList = getLeaveRepertoryReceiptList();

		int size = leaveRepertoryReceiptList.size();
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
				ArrayList<LeaveRepertoryReceiptPO> errpoList = lrrData.getAllLeaveRepertoryReceipts();
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
