package businesslogic.financebl.controller;

import java.util.ArrayList;

import businesslogic.financebl.CollectionReceiptBL;
import businesslogicservice.financeblservice.CollectionReceiptBLService;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;

public class CollectionReceiptBLController implements CollectionReceiptBLService{

	private CollectionReceiptBL collectionReceiptBL;
	
	public CollectionReceiptBLController(){
		collectionReceiptBL=new CollectionReceiptBL();
	}
	public int creatCollection(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.creatCollection(vo);
	}

	public CollectionReceiptVO getCollection(String s) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getCollection(s);
	}

	public ArrayList<CollectionReceiptVO> getAllCollection() {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getAllCollection();
	}

	public ArrayList<GatheringReceiptVO> getGatheringByTime(String Time) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getGatheringByTime(Time);
	}
	
	public ArrayList<GatheringReceiptVO> getGatheingByBoth(String hallID,
			String time) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getGatheringByBoth(hallID, time);
	}
	
	public ArrayList<GatheringReceiptVO> getGatheringByHall(String hallID) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getGatheringByHall(hallID);
	}

	public double getTotalMoney(ArrayList<GatheringReceiptVO> vo) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getTotalMoney(vo);
	}

	public String getCollectionListID() {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getCollectionListID();
	}

	public ArrayList<CollectionReceiptVO> getUnapprovedCollectionReceipt() {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getUnapprovedCollectionReceipt();
	}
	
	public int saveSubmittedCollectionReceiptInfo(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.saveSubmittedCollectionReceiptInfo(vo);
	}


	public int excute(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.excute(vo);
	}
	
	
	
//	public static void main(String[] args) throws Exception{
//	CollectionReceiptBLController controller=new CollectionReceiptBLController();
////	CollectionReceiptVO vo=new CollectionReceiptVO("HJSKD-20151216", "CW-00001", null, null, 2000, "20151206", "CW");
////	controller.creatCollection(vo);
//	ArrayList<GatheringReceiptVO> vos = controller.getGathering("20151220");
//	System.out.println(vos.size());
////	ArrayList<CollectionReceiptVO> vos=controller.getAllCollection();
////	for(CollectionReceiptVO v:vos){
////		System.out.println(v.ID+" "+v.userID+" "+v.date+" "+v.totalMoney);
////	}
//}
//@Override
}
