package dataservice.financedataservice;

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

	public ArrayList<GatheringReceiptPO> getGathering(String HallID, String Time) {
		// TODO Auto-generated method stub
		System.out.println("get Gathering successfully!");
		return null;
	}

	public double[] getMoney(GatheringReceiptPO po) {
		// TODO Auto-generated method stub
		System.out.println("get money  successfully!");
		return null;
	}

	public double getTotalMoney(int[] money) {
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

}
