package businesslogic.financebl.controller;

import java.util.ArrayList;

import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import businesslogic.financebl.CollectionReceiptBL;
import businesslogicservice.financeblservice.CollectionReceiptBLService;

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

	public ArrayList<GatheringReceiptVO> getGathering(String Time) {
		// TODO Auto-generated method stub
		return collectionReceiptBL.getGathering(Time);
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

	
	public static void main(String[] args) throws Exception{
		CollectionReceiptBLController controller=new CollectionReceiptBLController();
//		CollectionReceiptVO vo=new CollectionReceiptVO("HJSKD-20151216", "CW-00001", null, null, 2000, "20151206", "CW");
//		controller.creatCollection(vo);
		ArrayList<CollectionReceiptVO> vos=controller.getAllCollection();
		for(CollectionReceiptVO v:vos){
			System.out.println(v.ID+" "+v.userID+" "+v.date+" "+v.totalMoney);
		}
	}
}
