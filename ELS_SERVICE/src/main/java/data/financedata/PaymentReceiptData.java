


package data.financedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.FileGetter;
import po.PaymentReceiptPO;
import type.ReceiptState;
import dataservice.financedataservice.PaymentReceiptDataService;

public class PaymentReceiptData extends UnicastRemoteObject implements PaymentReceiptDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PaymentReceiptData() throws RemoteException{
		super();
	}
	
	/**
	 * 读取所有的入款单
	 * */
	public ArrayList<PaymentReceiptPO> getPaymentReceiptPOs(){
		String path = "paymentInfo/payment.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<PaymentReceiptPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<PaymentReceiptPO> paymentReceiptPOs = (ArrayList<PaymentReceiptPO>) in.readObject();
			in.close();
			return paymentReceiptPOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 存储入款单
	 * */
	public int savePaymentReceiptPOs(ArrayList<PaymentReceiptPO> pos){
		String path = "paymentInfo/payment.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			FileGetter.createFile(file);
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	public int creatPaymentReceipt(PaymentReceiptPO po) throws RemoteException {
		ArrayList<PaymentReceiptPO> payments = getPaymentReceiptPOs();
		po.setState(ReceiptState.SUBMIT);
		boolean isExsit = false;
		for(PaymentReceiptPO p : payments){
			if(p.getDate().contains(po.getDate().substring(0,7))){
				isExsit = true;
			}
		}
		if(isExsit == true){
			System.out.println("付款单已存在");
			return -1;
		}
		else{
		payments.add(po);
		savePaymentReceiptPOs(payments);
		return 0;
		}

	}

	public ArrayList<PaymentReceiptPO> getAllPaymentReceipt()
			throws RemoteException {
		// TODO Auto-generated method stub
		if(getPaymentReceiptPOs()!=null){
			return getPaymentReceiptPOs();
			}
			else{
				return null;
			}
	}
	
	public int getNum()  throws RemoteException{
		return 0;
	}
	

	/**
	 * 修改——暂时不需要
	 * */
	public PaymentReceiptPO modify(PaymentReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 删除——本来不想写的，但是打不开ser文件23333
	 * success
	 * */
	public int delete(String ID) throws RemoteException{
		int isExsit=-1;
		   ArrayList<PaymentReceiptPO> paymentReceiptPOs = getPaymentReceiptPOs();
		  for(int i=0;i<paymentReceiptPOs.size();i++){
			if(paymentReceiptPOs.get(i).getID().equals(ID)){
				paymentReceiptPOs.remove(i);
				isExsit = 0;
				break;
			 }
		   }
		  savePaymentReceiptPOs(paymentReceiptPOs);
		  return isExsit;
			}


	/**
	 * 按时间条件获取付款单（查看经营情况表需要）
	 * */
	public ArrayList<PaymentReceiptPO> getPayment_right(String beginTime,
			String endTime)  throws RemoteException{
		// TODO Auto-generated method stub
		ArrayList<PaymentReceiptPO> paymentReceiptPOs = getPaymentReceiptPOs();
		ArrayList<PaymentReceiptPO> result = new ArrayList<PaymentReceiptPO>();
		for(int i=0;i<paymentReceiptPOs.size();i++){
			if((paymentReceiptPOs.get(i).getDate().compareTo(beginTime)>=0)&&(paymentReceiptPOs.get(i).getDate().compareTo(endTime)<=0)){
				result.add(paymentReceiptPOs.get(i));
			}
		}
		return result;
	}

	/**
	 * 获取所有未审批的入款单
	 * */
	public ArrayList<PaymentReceiptPO> getUnapprovedPaymentReceipt() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PaymentReceiptPO> paymentReceiptPOs = getPaymentReceiptPOs();
		ArrayList<PaymentReceiptPO> result = new ArrayList<PaymentReceiptPO>();
		
		for(int i=0;i<paymentReceiptPOs.size();i++){
			if(paymentReceiptPOs.get(i).getState() == ReceiptState.SUBMIT){
				result.add(paymentReceiptPOs.get(i));
			}
		}
		return result;
	}
	
	/**
	 * 存储审批后的信息（总经理审批单据用）
	 * */
	 public int saveSubmittedPaymentReceiptInfo(PaymentReceiptPO po) throws RemoteException{
		 ArrayList<PaymentReceiptPO> paymentReceiptPOs = getPaymentReceiptPOs();
			for(int i=0;i<paymentReceiptPOs.size();i++){
				if(paymentReceiptPOs.get(i).getID().equals(po.getID())){
					paymentReceiptPOs.get(i).setState(ReceiptState.APPROVE);
				}
			}
			savePaymentReceiptPOs(paymentReceiptPOs);
			return 0;
	 }
	    
	
	public PaymentReceiptPO findByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PaymentReceiptPO> paymentReceiptPOs = getPaymentReceiptPOs();
		for(int i=0;i<paymentReceiptPOs.size();i++){
			if(paymentReceiptPOs.get(i).getID().equals(ID)){
			return paymentReceiptPOs.get(i);
			}
		}
		return null;
	}

	

	
	public static void main(String[] args) throws RemoteException{
		
		PaymentReceiptData data=new PaymentReceiptData();
//		PaymentReceiptPO po1=new PaymentReceiptPO("FKD-20151201", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151201", "boss", "本宝宝");
//		PaymentReceiptPO po2=new PaymentReceiptPO("FKD-20151211", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151211", "boss", "本宝宝");
//		PaymentReceiptPO po3=new PaymentReceiptPO("FKD-20151226", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151226", "boss", "本宝宝");
//		PaymentReceiptPO po4=new PaymentReceiptPO("FKD-20151227", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151227", "boss", "本宝宝");
		try {
//			data.creatPaymentReceipt(po1);
//			data.creatPaymentReceipt(po2);
//			data.creatPaymentReceipt(po3);
//			data.creatPaymentReceipt(po4);
			
			ArrayList<PaymentReceiptPO> All;
							All = data.getAllPaymentReceipt();
							for(PaymentReceiptPO p:All){
								System.out.println("ID: "+p.getID()+" "+p.getDate());
							}

						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		ArrayList<PaymentReceiptPO> pos = data.getPayment_right("2010-01-01", "2016-01-10");
		for(PaymentReceiptPO p : pos){
			System.out.println(p.getID());
		}
//		PaymentReceiptData paymentdata=new PaymentReceiptData();
//			PaymentReceiptPO po1=new PaymentReceiptPO("FKD-20151010", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20110101", "boss", "本宝宝");
//			PaymentReceiptPO po2=new PaymentReceiptPO("FKD-20151111", "呵呵", null, null, 200, 300, 1000, "20151111", "呵呵", "CW");
//			
//			paymentdata.creatPaymentReceipt(po1);
//			paymentdata.creatPaymentReceipt(po2);
//			ArrayList<PaymentReceiptPO> pos=paymentdata.getAllPaymentReceipt();
//
//			for(PaymentReceiptPO p:pos){
//				System.out.println(p.getID());
//			}
//			System.out.println("---------------------------------------------------------------------------------------");
//			ArrayList<PaymentReceiptPO> pos2=paymentdata.getPayment_right("20121210", "20151206");
//			for(PaymentReceiptPO p:pos2){
//				System.out.println(p.getID());
//			}
/*		try{
			/*System.setProperty("java.rmi.server.hostname", "172.26.210.111");
			PaymentReceiptDataService data=new PaymentReceiptData();
			LocateRegistry.createRegistry(8800);
//			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.26.210.111:8800/PaymentReceiptDataService", data);
			System.out.println("Service start 8800 !");
		
			PaymentReceiptDataService data=new PaymentReceiptData();
			
//			PaymentReceiptPO po1=new PaymentReceiptPO("FKD-20110101-00001", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.SUBMIT, 2000, 1000, 1000, "20110101", "boss", "本宝宝");
//			data.creatPaymentReceipt(po1);
			ArrayList<PaymentReceiptPO> test=data.getAllPaymentReceipt();
			for(PaymentReceiptPO p:test){
				System.out.println("ID: "+p.getID());
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	
		
		PaymentReceiptData data=new PaymentReceiptData();
		PaymentReceiptPO po1=new PaymentReceiptPO("FKD-20110101-00001", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20110101", "boss", "本宝宝");
//		PaymentReceiptPO po2=new PaymentReceiptPO("FKD-20110101-00002", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20110101", "boss", "本宝宝");
//		PaymentReceiptPO po3=new PaymentReceiptPO("FKD-20151126-00001", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151126", "boss", "本宝宝");
//		PaymentReceiptPO po4=new PaymentReceiptPO("FKD-20151127-00001", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151127", "boss", "本宝宝");
		try {
//			data.delete("FKD-20110101-00001");
//			data.delete("FKD-20110101-00002");
//			data.delete("FKD-20151126-00001");
//			data.delete("FKD-20151127-00001");
//			data.creatPaymentReceipt(po1);
//			data.creatPaymentReceipt(po2);
//			data.creatPaymentReceipt(po3);
//			data.creatPaymentReceipt(po4);
			
			ArrayList<PaymentReceiptPO> All;
							All = data.getAllPaymentReceipt();
							for(PaymentReceiptPO p:All){
								System.out.println("ID: "+p.getID());
							}
							System.out.println(data.getNum());
							System.out.println();
							ArrayList<PaymentReceiptPO> por=data.getPayment_right("20110101", "20151127");
							for(PaymentReceiptPO p:por){
								System.out.println("ID :"+p.getID());
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
}
	
*/

	}
}
