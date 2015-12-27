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
import po.CostIncomeReceiptPO;
import type.ReceiptState;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import file.JXCFile;

public class CostIncomeReceiptData extends UnicastRemoteObject implements CostIncomeReceiptDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JXCFile file;
	int num=0;
	
	public CostIncomeReceiptData() throws RemoteException{
		super();
	}
	
	/**
	 * 读出所有的成本收益表
	 * */
	public ArrayList<CostIncomeReceiptPO> getCostIncomeReceiptPOs(){
		String path = "costincomeInfo/costincome.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<CostIncomeReceiptPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<CostIncomeReceiptPO> costIncomeReceiptPOs = (ArrayList<CostIncomeReceiptPO>) in.readObject();
			in.close();
			return costIncomeReceiptPOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 写入成本收益表
	 * */
	public int saveCostIncomeReceiptPOs(ArrayList<CostIncomeReceiptPO> pos){
		String path = "costincomeInfo/costincome.ser";
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
	public int creatCostIncomeList(CostIncomeReceiptPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CostIncomeReceiptPO> costIncomeReceiptPOs = getCostIncomeReceiptPOs();
		po.setState(ReceiptState.SUBMIT);
		if(findByID(po.getID())!=null){
			System.out.println("成本收益表已存在");
			return -1;
		}
		else{
			costIncomeReceiptPOs.add(po);
			saveCostIncomeReceiptPOs(costIncomeReceiptPOs);
			return 0;
		}
	}

	public ArrayList<CostIncomeReceiptPO> getAllCostIncomeList()
			throws RemoteException {
		// TODO Auto-generated method stub
	if(getAllCostIncomeList()!=null){
		return getAllCostIncomeList();
	}
	else{
		return null;
	}
	}


	public int getNum() throws RemoteException {
		// TODO Auto-generated method stub
//		file=new JXCFile("costincome.ser");
		return 0;
	}

	public CostIncomeReceiptPO findByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CostIncomeReceiptPO> costIncomeReceiptPOs = getCostIncomeReceiptPOs();
		for(int i=0;i<costIncomeReceiptPOs.size();i++){
			if(costIncomeReceiptPOs.get(i).getID().equals(ID)){
			return costIncomeReceiptPOs.get(i);
			}
		}
		return null;
	}

	/**
	 * 一直怀疑这些单据到底需不需要修改=。=
	 * */
	public CostIncomeReceiptPO modify(CostIncomeReceiptPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 总经理根据这个来查询
	 * */
	public CostIncomeReceiptPO getCostIncomeReceipt(String time)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*	public static void main(String[] args){
		try{
			System.setProperty("java.rmi.server.hostname", "172.26.209.182");
			CostIncomeReceiptDataService data=new CostIncomeReceiptData();
			LocateRegistry.createRegistry(8888);
//			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.26.209.182:8888/CostIncomeReceiptDataService", data);
			System.out.println("Service start!");
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CostIncomeReceiptData data=new CostIncomeReceiptData();
		CostIncomeReceiptPO po1=new CostIncomeReceiptPO("CBSYB-20151126-00001", "本宝宝", ReceiptType.COSTINCOMERECEPTION, ReceiptState.DRAFT, 200, 1222, 1022);
		try {
			data.creatCostIncomeList(po1);
			ArrayList<CostIncomeReceiptPO> pos=data.getAllCostIncomeList();
			for(CostIncomeReceiptPO p:pos){
				System.out.println("ID: "+p.getID());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
	*/
}
