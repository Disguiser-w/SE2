package businesslogic.financebl.driver;

import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;
import vo.AccountVO;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import vo.PaymentReceiptVO;
import businesslogic.financebl.AccountBL;
import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import businesslogic.receiptbl.getDate;

public class PaymentReceipt_driver {
	public static void main(String[] args){
		PaymentReceiptBLController paymentController = new PaymentReceiptBLController();
		PaymentReceiptVO pvo = new PaymentReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.SUBMIT, 3000,2000,3000, getDate.getdate(), "鼓楼","doge");
		PaymentReceiptVO pvol = new PaymentReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.APPROVE, 3000,2000,3000, getDate.getdate(), "鼓楼","doge");
		paymentController.creatPaymentReceipt(pvo);
		ArrayList<PaymentReceiptVO> pvos=paymentController.getAllPaymentReceipt();
		System.out.println(pvos.size());
		paymentController.getPaymentReceiptListID();
		paymentController.getUnapprovedPaymentReceipt();
		paymentController.getFare("20151220");
		paymentController.getRent("20151220");
		double cost=paymentController.getSalary("20151020");
		System.out.println(cost);
		paymentController.saveSubmittedPaymentReceiptInfo(pvol);

	CollectionReceiptBLController collectionController = new CollectionReceiptBLController();
	CollectionReceiptVO cvo = new CollectionReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
			ReceiptState.SUBMIT, 3000, getDate.getdate(), "鼓楼");
	CollectionReceiptVO cvol = new CollectionReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
			ReceiptState.APPROVE, 3000, getDate.getdate(), "鼓楼");
	collectionController.creatCollection(cvo);
	ArrayList<CollectionReceiptVO> cvos=collectionController.getAllCollection();
	System.out.println(cvos.size());
	collectionController.getCollectionListID();
	ArrayList<GatheringReceiptVO> gvos=collectionController.getGathering("20151220");
	collectionController.getUnapprovedCollectionReceipt();
	collectionController.getCollection("HJSKD-20151220");
	double income=collectionController.getTotalMoney(gvos);
	System.out.println(income);
	collectionController.saveSubmittedCollectionReceiptInfo(cvol);
	
	AccountBL controller = new AccountBL();
	AccountVO test = new AccountVO("狗剩", 200);
	int result = controller.addAccount(test);
	System.out.println(result);
	ArrayList<AccountVO> vos=controller.findByKeyword("狗");
	System.out.println(vos.get(0).name);
	AccountVO vo=controller.findbyName("狗剩");
	
	System.out.println(vo.money);
	 controller.modifyAccount(vo, "doge");
	 controller.showAll();
	 
	 controller.addMoney("doge", 100);
	 controller.delMoney("doge", 100);
	controller.deleteAccount("doge");
	
	}
	
}
