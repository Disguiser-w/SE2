


package data.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import file.JXCFile;
import po.PaymentReceiptPO;
import type.ReceiptState;
import type.ReceiptType;
import dataservice.financedataservice.PaymentReceiptDataService;

public class PaymentReceiptData extends UnicastRemoteObject implements PaymentReceiptDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	int num;
	public PaymentReceiptData() throws RemoteException{
		super();
		file=new JXCFile("info/paymentInfo/payment.ser");
	}

	public int creatPaymentReceipt(PaymentReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/paymentInfo/payment.ser");
		po.setState(ReceiptState.SUBMIT);
		file.write(po);
		num++;
		return 0;
	}

	public ArrayList<PaymentReceiptPO> getAllPaymentReceipt()
			throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/paymentInfo/payment.ser");
//		ArrayList<Object> payment=file.read();
		ArrayList<Object> payment=file.read();
		if(payment==null){
			System.out.println("读文件collection.ser失败或者文件为空");
			return null;
		}
		else{
			ArrayList<PaymentReceiptPO> buffer=new ArrayList<PaymentReceiptPO>();
			for(Object o:payment){
				PaymentReceiptPO p=(PaymentReceiptPO) o;
				buffer.add(p);
			}
			return buffer;
		}
	}
	
	public int getNum()  throws RemoteException{
		file=new JXCFile("info/paymentInfo/payment.ser");
		return num;
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
		file=new JXCFile("info/paymentInfo/payment.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("文件为空");
			return 1;
		}
		for(int i=0;i<os.size();i++){
			PaymentReceiptPO po=(PaymentReceiptPO) os.get(i);
			if(po.getID().equals(ID)){
				os.remove(i);
				System.out.println("remove successfully!");
			}
			}

		file.writeM(os);
		return 0;
	}

	public double getSalary()  throws RemoteException{
		// TODO Auto-generated method stub
		return 0;
	}

	public double getFare()  throws RemoteException{
		// TODO Auto-generated method stub
		return 0;
	}

	public double getRent()  throws RemoteException{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 按时间条件获取付款单（查看经营情况表需要）
	 * */
	public ArrayList<PaymentReceiptPO> getPayment_right(String beginTime,
			String endTime)  throws RemoteException{
		// TODO Auto-generated method stub
		file=new JXCFile("info/paymentInfo/payment.ser");
		ArrayList<Object> os=file.read();
		ArrayList<PaymentReceiptPO> pos=new ArrayList<PaymentReceiptPO>();
		if(beginTime.compareTo(endTime)>0){
			System.out.println("输入时间区间格式不对");
			return null;
		}
		else{
			for(Object o:os){
				PaymentReceiptPO p=(PaymentReceiptPO) o;
				if((p.getDate().compareTo(beginTime)>=0)&&(p.getDate().compareTo(endTime)<=0)){
					pos.add(p);
				}
			}
			return pos;
		}
	}

	/**
	 * 获取所有未审批的入款单
	 * */
	public ArrayList<PaymentReceiptPO> getUnapprovedPaymentReceipt() throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/paymentInfo/payment.ser");
		ArrayList<Object> os=file.read();
		ArrayList<PaymentReceiptPO> unprovedPOs=new ArrayList<PaymentReceiptPO>();
		for(Object o:os){
			PaymentReceiptPO po=(PaymentReceiptPO) o;
			if(po.getState()==ReceiptState.SUBMIT){
				unprovedPOs.add(po);
			}
		}
		return unprovedPOs;
	}
	
	/**
	 * 存储审批后的信息（总经理审批单据用）
	 * */
	 public int saveSubmittedPaymentReceiptInfo(PaymentReceiptPO po) throws RemoteException{
		 file=new JXCFile("info/paymentInfo/payment.ser");
		 ArrayList<Object> os=file.read();
		 for(int i=0;i<os.size();i++){
			 //文件中的
			 PaymentReceiptPO po_infile=(PaymentReceiptPO) os.get(i);
			 //总经理传过来的
				 if(po_infile.getID().equals(po.getID())){
					po_infile.setState(po.getState());
				 }
		 }
		 file.writeM(os);
		return 0;
		 
	 }
	    
	
	public PaymentReceiptPO findByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/paymentInfo/payment.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取付款单失败或付款单为空");
			return null;
		}
		else{
			for(Object o:os){
				PaymentReceiptPO po=(PaymentReceiptPO) o;
				if(po.getID().equals(ID)){
					return po;
				}
			}
		}
		return null;
	}

	

	
	public static void main(String[] args) throws RemoteException{
		
		PaymentReceiptData data=new PaymentReceiptData();
		PaymentReceiptPO po1=new PaymentReceiptPO("FKD-20151201", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151201", "boss", "本宝宝");
		PaymentReceiptPO po2=new PaymentReceiptPO("FKD-20151211", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151211", "boss", "本宝宝");
		PaymentReceiptPO po3=new PaymentReceiptPO("FKD-20151226", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151226", "boss", "本宝宝");
		PaymentReceiptPO po4=new PaymentReceiptPO("FKD-20151227", "CW-00001", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151227", "boss", "本宝宝");
		try {
//			data.delete("FKD-20110101-00001");
//			data.delete("FKD-20110101-00002");
//			data.delete("FKD-20151126-00001");
//			data.delete("FKD-20151127-00001");
			data.creatPaymentReceipt(po1);
			data.creatPaymentReceipt(po2);
			data.creatPaymentReceipt(po3);
			data.creatPaymentReceipt(po4);
			
			ArrayList<PaymentReceiptPO> All;
							All = data.getAllPaymentReceipt();
							for(PaymentReceiptPO p:All){
								System.out.println("ID: "+p.getID());
							}
//							System.out.println(data.getNum());
//							System.out.println();
//							ArrayList<PaymentReceiptPO> por=data.getPayment_right("20110101", "20151127");
//							for(PaymentReceiptPO p:por){
//								System.out.println("ID :"+p.getID());
//							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
