package data.financedata;

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
import po.CollectionReceiptPO;
import type.ReceiptState;
import type.ReceiptType;
import dataservice.financedataservice.CollectionReceiptDataService;


public class CollectionReceiptData extends UnicastRemoteObject implements CollectionReceiptDataService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//计次数
	public CollectionReceiptData() throws RemoteException{
		super();
	}

	/**
	 * 读取所有的入款单
	 * */
	public ArrayList<CollectionReceiptPO> getCollectionReceiptPOs(){
		String path = "collectionInfo/collection.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<CollectionReceiptPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<CollectionReceiptPO> collectionReceiptPOs = (ArrayList<CollectionReceiptPO>) in.readObject();
			in.close();
			return collectionReceiptPOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 存储入款单
	 * */
	public int saveCollectionPOs(ArrayList<CollectionReceiptPO> pos){
		String path = "collectionInfo/collection.ser";
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
			out.writeObject(pos);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	/**
	 * 创建收款单，即将一个po写入序列化文件
	 *  success
	 * */
	public int createCollection(CollectionReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collections = getCollectionReceiptPOs();
		po.setState(ReceiptState.SUBMIT);
		if(findByID(po.getID())!=null){
			System.out.println("入款单已存在");
			return -1;
		}
		else{
		collections.add(po);
		saveCollectionPOs(collections);
		return 0;
		}
	}

	/**
	 * 获取所有的收款单------也就是从文件中读取放到po中
	 * success
	 * */
	public ArrayList<CollectionReceiptPO> getAllCollection()
			throws RemoteException {
		// TODO Auto-generated method stub
		if(getCollectionReceiptPOs()!=null){
		return getCollectionReceiptPOs();
		}
		else{
			return null;
		}
	}
	

	/**
	 * 获取已生成单据数量
	 * */
	public int getNum() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 查找：暂时先不写吧
	 * success
	 * */
	public CollectionReceiptPO findByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs = getCollectionReceiptPOs();
		for(int i=0;i<collectionReceiptPOs.size();i++){
			if(collectionReceiptPOs.get(i).getID().equals(ID)){
			return collectionReceiptPOs.get(i);
			}
		}
		return null;
	}

	/**
	 * 修改
	 * */
	public CollectionReceiptPO modify(CollectionReceiptPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 删除——本来不想写的，但是打不开ser文件23333
	 * success
	 * */
	public int delete(String ID) throws RemoteException{
		int isExsit=-1;
		   ArrayList<CollectionReceiptPO> collectionReceiptPOs = getCollectionReceiptPOs();
		  for(int i=0;i<collectionReceiptPOs.size();i++){
			if(collectionReceiptPOs.get(i).getID().equals(ID)){
				collectionReceiptPOs.remove(i);
				isExsit = 0;
				break;
			 }
		   }
		  saveCollectionPOs(collectionReceiptPOs);
		  return isExsit;
	}
	/**
	 * 根据时间筛选出入款单——只提供bl层调用，不需要写入文件（经营情况表）
	 * */
	public ArrayList<CollectionReceiptPO> getCollection_right(String beginTime,
			String endTime)  throws RemoteException{
		// TODO Auto-generated method stub
		
		ArrayList<CollectionReceiptPO> collectionReceiptPOs = getCollectionReceiptPOs();
		ArrayList<CollectionReceiptPO> result = new ArrayList<CollectionReceiptPO>();
		for(int i=0;i<collectionReceiptPOs.size();i++){
			if((collectionReceiptPOs.get(i).getDate().compareTo(beginTime)>=0)&&(collectionReceiptPOs.get(i).getDate().compareTo(endTime)<=0)){
				result.add(collectionReceiptPOs.get(i));
			}
		}
		return result;

		
	}
	

	/**
	 * 获取所有未审批的入款单
	 * */
	public ArrayList<CollectionReceiptPO> getUnapprovedCollectionReceipt() throws RemoteException{
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs = getCollectionReceiptPOs();
		ArrayList<CollectionReceiptPO> result = new ArrayList<CollectionReceiptPO>();
		
		for(int i=0;i<collectionReceiptPOs.size();i++){
			if(collectionReceiptPOs.get(i).getState() == ReceiptState.SUBMIT){
				result.add(collectionReceiptPOs.get(i));
			}
		}
		return result;

	}
	
	/**
	 * 总经理审批的信息通知
	 * */
	public String getFeedback(CollectionReceiptPO po){
		ArrayList<CollectionReceiptPO> collectionReceiptPOs = getCollectionReceiptPOs();
		String feedback = po.getID();
		for(int i=0;i<collectionReceiptPOs.size();i++){
			if(collectionReceiptPOs.get(i).getID().equals(po.getID())){
				if(po.getState() == ReceiptState.APPROVE){
					 feedback+="单据审批通过！";
					 }
					 else if(po.getState()==ReceiptState.DISAPPROVE){
						 feedback+="单据审批未通过！";
					 }
				}
			}
		return feedback;
		}
	

	/**
	 * 存储审批后结果
	 * */
	public int saveSubmittedCollectionReceiptInfo(CollectionReceiptPO  po) throws RemoteException{
		ArrayList<CollectionReceiptPO> collectionReceiptPOs = getCollectionReceiptPOs();
		for(int i=0;i<collectionReceiptPOs.size();i++){
			if(collectionReceiptPOs.get(i).getID().equals(po.getID())){
				collectionReceiptPOs.get(i).setState(ReceiptState.APPROVE);
			}
		}
		saveCollectionPOs(collectionReceiptPOs);
		return 0;

		
	}

	
	public static void main(String[] args) throws RemoteException{
		
		CollectionReceiptData collectionData=new CollectionReceiptData();
		CollectionReceiptPO po1=new CollectionReceiptPO("HJSKD-20151221", "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.DRAFT, 200, "2015-12-21", "鼓楼");
		CollectionReceiptPO po2=new CollectionReceiptPO("HJSKD-20151223", "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.DRAFT, 300, "2015-12-23", "仙林");
		CollectionReceiptPO po3=new CollectionReceiptPO("HJSKD-20151222", "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.DRAFT, 250, "2015-12-22", "鼓楼");
		CollectionReceiptPO po4=new CollectionReceiptPO("HJSKD-20151224", "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.DRAFT, 1000, "2015-12-24", "鼓楼");
		collectionData.createCollection(po3);
		collectionData.createCollection(po1);
		collectionData.createCollection(po2);
		collectionData.createCollection(po4);
		ArrayList<CollectionReceiptPO> pos=collectionData.getAllCollection();
		for(CollectionReceiptPO p:pos){
			System.out.println("ID: "+p.getID()+" "+p.getState().toString());
		}
		System.out.println("-------------------------------------------------------------------------------------");
//		ArrayList<CollectionReceiptPO> po_unproved=collectionData.getUnapprovedCollectionReceipt();
//		for(CollectionReceiptPO pp:po_unproved){
//			System.out.println("ID: "+pp.getID()+" "+pp.getIncome());
//		}
//		System.out.println("---------------------------------------------------------------------------------------");
//		ArrayList<CollectionReceiptPO> po_fromManager=new ArrayList<CollectionReceiptPO>();
//		CollectionReceiptPO po1=new CollectionReceiptPO("HJSKD-20151201", "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.APPROVE, 200, "20151201", "鼓楼");
//		CollectionReceiptPO po2=new CollectionReceiptPO("HJSKD-20151203", "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.APPROVE, 300, "20151203", "仙林");
//		po_fromManager.add(po1);
//		po_fromManager.add(po2);
		
//		collectionData.saveSubmittedCollectionReceiptInfo(po_fromManager);
		CollectionReceiptPO po_fromManager=new CollectionReceiptPO("HJSKD-20151204", "CW-00001", ReceiptType.COLLECTIONRECEIPT, ReceiptState.DISAPPROVE, 1000, "20151204", "鼓楼");
		String feedback=collectionData.getFeedback(po_fromManager);
		System.out.println("反馈："+feedback);
		collectionData.saveSubmittedCollectionReceiptInfo(po_fromManager);
		ArrayList<CollectionReceiptPO> result=collectionData.getAllCollection();
		for(CollectionReceiptPO ppp:result){
			System.out.println("ID :"+ppp.getID()+" "+ppp.getState().toString());
		}
		
/*		try{
			System.setProperty("java.rmi.server.hostname", "172.26.210.111");
			CollectionReceiptDataService data=new CollectionReceiptData();
			LocateRegistry.createRegistry(8888);
//			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.26.210.111:8888/CollectionReceiptDataService", data);
			System.out.println("Service start! 8888");
			
		ArrayList<CollectionReceiptPO> pos=data.getAllCollection();
			for(CollectionReceiptPO p:pos){
				System.out.println("ID:  "+p.getID());
			}
			System.out.println("---------------------------------------------------");
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		*/
/*		CollectionReceiptData data=new CollectionReceiptData();
//			data.createCollection(po1);
		//			data.createCollection(po2);
		//			data.createCollection(po3);
					ArrayList<CollectionReceiptPO> All;
					try {
						All = data.getAllCollection();
						for(CollectionReceiptPO p:All){
							System.out.println("ID: "+p.getID());
						}
						System.out.println(data.getNum());
						System.out.println();
						ArrayList<CollectionReceiptPO> por=data.getCollection_right("20151101", "20151127");
						for(CollectionReceiptPO p:por){
							System.out.println("ID :"+p.getID());
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//			ArrayList<CollectionReceiptPO> collectionReceiptPOs=data.getAllCollection();
//			for(CollectionReceiptPO po:collectionReceiptPOs){
//				System.out.println("ID:  "+po.getID());
//			}
//			CollectionReceiptPO po=data.findByID("HJSKD-20151126-00002");
//			System.out.println("ID: "+po.getID()+"  UserID: "+po.getUserID());
 */

	}

	



	/**
	 * 按时间获取所有的营业厅收款单------从文件中放到po中
	 * 才看到钦gg的需求里竟然要按天按营业厅查询，我们愉快地忽略吧233333
	 * 暂取名==
	 * 但是我这里可以直接读营业厅的文件吗，，，并不清楚——好像不行，应该是BL层直接调用BusinessService中的方法
	 * */
/*      public ArrayList<GatheringReceiptPO> getGathering(String Time)
//			throws RemoteException {
//		// TODO Auto-generated method stub
//		file=new JXCFile("gathering.ser");
//		ArrayList<Object> gathering=file.read();
//		if(gathering==null){
//			System.out.println("读取文件gathering.ser失败或文件为空");
//			return null;
//		}
//		else{
//			ArrayList<GatheringReceiptPO> buffer=new ArrayList<GatheringReceiptPO>();
//			for(Object o:gathering){
//				GatheringReceiptPO p=(GatheringReceiptPO) o;
//				buffer.add(p);
//			}
//			return buffer;
//		}
//	}
*/
	
	
}
