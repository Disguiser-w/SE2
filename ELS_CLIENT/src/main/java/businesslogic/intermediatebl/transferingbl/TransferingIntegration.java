package businesslogic.intermediatebl.transferingbl;

import vo.ReceiptVO;
import businesslogic.receiptbl.MockReceipt;
import businesslogic.receiptbl.ReceiptBL;

public class TransferingIntegration {
    public void testTransfering(){
    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null,"test",null,null);
    	TransferingBL tbl = new TransferingBL();
    	MockReceipt mr = new MockReceipt();
    	
    	if(mr.update((ReceiptVO)tbl.updateTransferingReceipt(transferingReceipt)))
    		System.out.println("equals");
    	else
    		System.out.println("error");
    }
}
