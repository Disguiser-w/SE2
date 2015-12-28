package intermediatetest;

import businesslogic.receiptbl.MockReceipt;
import businesslogicservice.intermediateblservice.TransferingBLService_stub;
import vo.ReceiptVO;

public class TransferingIntegration {
	public void testTransfering() {
		MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(
				null, "test", null, null);
		TransferingBLService_stub tbl = new TransferingBLService_stub();
		MockReceipt mr = new MockReceipt();

		if (mr.update((ReceiptVO) tbl
				.updateTransferingReceipt(transferingReceipt)))
			System.out.println("equals");
		else
			System.out.println("error");
	}
}
