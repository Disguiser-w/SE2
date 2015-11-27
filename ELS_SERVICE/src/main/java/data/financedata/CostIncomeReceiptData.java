package data.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import File.JXCFile;
import po.CollectionReceiptPO;
import po.CostIncomeReceiptPO;
import po.PaymentReceiptPO;
import po.ReceiptPO.ReceiptState;
import type.ReceiptType;
import dataservice.financedataservice.CostIncomeReceiptDataService;

public class CostIncomeReceiptData implements CostIncomeReceiptDataService{

	JXCFile file;
	int num=0;
	
	public CostIncomeReceiptData(){
		super();
		file=new JXCFile("costincome.ser");
	}
	public int creatCostIncomeList(CostIncomeReceiptPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("costincome.ser");
		file.write(po);
		num++;
		return 0;
	}

	public ArrayList<CostIncomeReceiptPO> getAllCostIncomeList()
			throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("costincome.ser");
		ArrayList<Object> os=file.read();
		ArrayList<CostIncomeReceiptPO> costIncomeReceiptPOs=new ArrayList<CostIncomeReceiptPO>();
		for(Object o:os){
			CostIncomeReceiptPO costIncomeReceiptPO=(CostIncomeReceiptPO) o;
			costIncomeReceiptPOs.add(costIncomeReceiptPO);
		}
		return costIncomeReceiptPOs;
	}

	public ArrayList<CollectionReceiptPO> getCollection()
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

	public ArrayList<PaymentReceiptPO> getPayment() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNum() throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("costincome.ser");
		return 0;
	}

	public CostIncomeReceiptPO findByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public CostIncomeReceiptPO modify(CostIncomeReceiptPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args){
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
