package businesslogicservice.financeblservice;

import java.util.ArrayList;

import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;

public class CollectionReceiptBLService_stub implements CollectionReceiptBLService{

	public int creatCollection(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Create Collection successfully!");
		return 0;
	}

	public CollectionReceiptVO getCollection(String s) {
		// TODO Auto-generated method stub
		System.out.println("Get Collection successfully!");
		return null;
	}
	
	public ArrayList<CollectionReceiptVO> getAllCollection() {
		// TODO Auto-generated method stub
		System.out.println("Get all collection successfully!");
		return null;
	}

	public ArrayList<GatheringReceiptVO> getGathering(String Time) {
		// TODO Auto-generated method stub
		System.out.println("Get Gathering successfully!");
		return null;
	}

	public ArrayList<Double>  getMoney(ArrayList<GatheringReceiptVO> vo) {
		// TODO Auto-generated method stub
		System.out.println("Get each Gathering's money successfully!");
		return null;
	}

	public double getTotalMoney(ArrayList<Double> money) {
		// TODO Auto-generated method stub
		System.out.println("Get total money successfully!");
		return 0;
	}

	public String getCollectionListID() {
		// TODO Auto-generated method stub
		System.out.println("Get ID successfully!");
		return null;
	}

	
//	public String getHallID() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getDate() {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
