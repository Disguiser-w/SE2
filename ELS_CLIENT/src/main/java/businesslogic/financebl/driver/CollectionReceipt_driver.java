package businesslogic.financebl.driver;

import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.receiptbl.GetDate;

public class CollectionReceipt_driver {

	public static void main(String[] args){
		CollectionReceiptBLController controller = new CollectionReceiptBLController();
		CollectionReceiptVO vo = new CollectionReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.SUBMIT, 3000, GetDate.getdate(), "鼓楼");
		CollectionReceiptVO vol = new CollectionReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.APPROVE, 3000, GetDate.getdate(), "鼓楼");
		controller.creatCollection(vo);
		ArrayList<CollectionReceiptVO> cvos=controller.getAllCollection();
		System.out.println(cvos.size());
		controller.getCollectionListID();
		ArrayList<GatheringReceiptVO> gvos=controller.getGatheringByTime("20151220");
		controller.getUnapprovedCollectionReceipt();
		controller.getCollection("HJSKD-20151220");
		double money=controller.getTotalMoney(gvos);
		System.out.println(money);
		controller.saveSubmittedCollectionReceiptInfo(vol);
	}
}
