package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;

public  class CollectionReceiptDataService_stub implements CollectionReceiptDataService{

	public int createCollection(CollectionReceiptPO po) {
		// TODO Auto-generated method stub
		System.out.println("Create Collection successfully!");
		return 0;
	}


	public ArrayList<CollectionReceiptPO> getAllCollection() {
		// TODO Auto-generated method stub
		System.out.println("get all Collection successfully!");
		return null;
	}


//	public ArrayList<Double> getMoney(ArrayList<GatheringReceiptPO> po) {
//		// TODO Auto-generated method stub
//		System.out.println("get money  successfully!");
//		return null;
//	}

	public double getTotalMoney(ArrayList<GatheringReceiptPO> po) {
		// TODO Auto-generated method stub
		System.out.println("get total money successfully!");
		return 0;
	}

	public int getNum() {
		// TODO Auto-generated method stub
		System.out.println("get money  successfully!");
		return 0;
	}

	public CollectionReceiptPO findByID(String ID) {
		// TODO Auto-generated method stub
		System.out.println("find  successfully!");
		return null;
	}

	public CollectionReceiptPO modify(CollectionReceiptPO po) {
		// TODO Auto-generated method stub
		System.out.println("modify  successfully!");
		return null;
	}


	public ArrayList<CollectionReceiptPO> getCollection_right(String beginTime,
			String endTime) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<CollectionReceiptPO> getUnapprovedCollectionReceipt() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int saveSubmittedCollectionReceiptInfo(
			CollectionReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return -1;
	}


//	@Override
//	public ArrayList<GatheringReceiptPO> getGathering(String Time) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
