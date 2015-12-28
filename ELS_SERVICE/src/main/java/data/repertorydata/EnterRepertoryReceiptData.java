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
import dataservice.repertorydataservice.EnterRepertoryReceiptDataService;
import po.EnterRepertoryReceiptPO;
import type.ReceiptState;

public class EnterRepertoryReceiptData extends UnicastRemoteObject implements EnterRepertoryReceiptDataService{

	private static final long serialVersionUID = 40L;
	
	public EnterRepertoryReceiptData() throws RemoteException{
		super();
	}
	
	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<EnterRepertoryReceiptPO> getEnterRepertoryReceiptList() throws RemoteException{
		String path = "repertoryInfo/enterReceipt.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<EnterRepertoryReceiptPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<EnterRepertoryReceiptPO> repertoryList = (ArrayList<EnterRepertoryReceiptPO>) in.readObject();
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
	public int saveEnterRepertoryReceiptList(ArrayList<EnterRepertoryReceiptPO> repertoryList) throws RemoteException {
		String path = "repertoryInfo/enterReceipt.ser";
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
	 * @param EnterRepertoryReceiptPO enterRepertoryReceiptpo
	 * @return 0(add succeed), 1(enterRepertoryReceipt with the ID has already existed)
	 * 
	 * */
	public int addEnterRepertoryReceipt(EnterRepertoryReceiptPO enterRepertoryReceiptpo) throws RemoteException{
    	ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
    	
    	for(int i=0; i<enterRepertoryReceiptList.size(); i++){
    		EnterRepertoryReceiptPO tmpEnterRepertoryReceiptpo = enterRepertoryReceiptList.get(i);
    		if(tmpEnterRepertoryReceiptpo.getReceiptID().endsWith(enterRepertoryReceiptpo.getReceiptID()))
    			return 1;
    	}
    	enterRepertoryReceiptList.add(enterRepertoryReceiptpo);
    	saveEnterRepertoryReceiptList(enterRepertoryReceiptList);
    	return 0;
    }
    
	
	/**
	 * 删除货物
	 * 
	 * @param String orderID
	 * @return 0(delete succeed), 1(delete failed)
	 * 
	 * */
    public int deleteEnterRepertoryReceipt(String receiptID) throws RemoteException{
    	ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<enterRepertoryReceiptList.size(); i++){
			EnterRepertoryReceiptPO tmpEnterRepertoryReceiptPO = enterRepertoryReceiptList.get(i);
			if(tmpEnterRepertoryReceiptPO.getReceiptID().equals(receiptID)){
				enterRepertoryReceiptList.remove(i);
				hasExist = true;
				break;
			}
		}
		
		saveEnterRepertoryReceiptList(enterRepertoryReceiptList);
		if(hasExist)
			return 0;
		else
			return 1;
    }
    
    
    public int sendEnterReceipt(String receiptID)throws RemoteException{
    	ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<enterRepertoryReceiptList.size(); i++){
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptList.get(i);
			if(errpo.getID().equals(receiptID)){
				errpo.setState(ReceiptState.SUBMIT);
				hasExist = true;
			}
		}
		saveEnterRepertoryReceiptList(enterRepertoryReceiptList);
		if(hasExist)
			return 0;
		else 
			return 1;
	}

	
	public int approveEnterReceipt(String receiptID) throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<enterRepertoryReceiptList.size(); i++){
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptList.get(i);
			if(errpo.getID().equals(receiptID)){
				errpo.setState(ReceiptState.APPROVE);
				hasExist = true;
			}
		}
		saveEnterRepertoryReceiptList(enterRepertoryReceiptList);
		if(hasExist)
			return 0;
		else 
			return 1;
	}
	
	
	public int disapproveEnterReceipt(String receiptID)throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
    	
    	boolean hasExist = false;
		for(int i=0; i<enterRepertoryReceiptList.size(); i++){
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptList.get(i);
			if(errpo.getID().equals(receiptID)){
				errpo.setState(ReceiptState.DISAPPROVE);
				hasExist = true;
			}
		}
		saveEnterRepertoryReceiptList(enterRepertoryReceiptList);
		if(hasExist)
			return 0;
		else 
			return 1;
	}
	
	
	public EnterRepertoryReceiptPO findEnterReceiptByReceiptID(String receiptID) throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
		
		for(int i=0; i<enterRepertoryReceiptList.size(); i++){
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptList.get(i);
			if(errpo.getID().equals(receiptID)){
				return errpo;
			}
		}
		return null;
	}

	public ArrayList<EnterRepertoryReceiptPO> findEnterReceiptByCreatorID(String creatorID) throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptPOList = new ArrayList<EnterRepertoryReceiptPO>();

		for(int i=0; i<enterRepertoryReceiptList.size(); i++){
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptList.get(i);
			if(errpo.getUserID().equals(creatorID)){
				enterRepertoryReceiptPOList.add(errpo);
			}
		}
		
		return enterRepertoryReceiptPOList;
	}
	
	public ArrayList<EnterRepertoryReceiptPO> findEnterReceiptByCreatorAndKeyword(String creator, String keyword) throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptPOListByCreator = findEnterReceiptByCreatorID(creator); 
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptPOListByCreatorAndKeyword = new ArrayList<EnterRepertoryReceiptPO>();
		
		for(int i=0,size = enterRepertoryReceiptPOListByCreator.size(); i<size; i++){
			EnterRepertoryReceiptPO errpo = (EnterRepertoryReceiptPO)enterRepertoryReceiptPOListByCreator.get(i);
			if(errpo.getID().contains(keyword)){
				enterRepertoryReceiptPOListByCreatorAndKeyword.add(errpo);
			}
		}
		return enterRepertoryReceiptPOListByCreatorAndKeyword;
	}
	
	public ArrayList<EnterRepertoryReceiptPO> getAllSubmitedEnterReceipts() throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
		ArrayList<EnterRepertoryReceiptPO> enterReceiptList = new ArrayList<EnterRepertoryReceiptPO>();
		
		for(int i=0; i<enterRepertoryReceiptList.size(); i++){
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptList.get(i);
			if(errpo.getState().equals(ReceiptState.SUBMIT)){
				enterReceiptList.add(errpo);
			}
		}
		return enterReceiptList;
	}
	
	
	public ArrayList<EnterRepertoryReceiptPO> getAllEnterReceipts() throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();
		return enterRepertoryReceiptList;
	}
	
	public String getEnterReceiptPost() throws RemoteException{
		ArrayList<EnterRepertoryReceiptPO> enterRepertoryReceiptList = getEnterRepertoryReceiptList();

		int size = enterRepertoryReceiptList.size();
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
