package businesslogicservice.receiptblservice;

import vo.ReceiptVO;

public class ReceiptBLService_driver {
    public void drive(ReceiptBLService receiptBLService){
    	boolean addResult = receiptBLService.add(new ReceiptVO());
    	boolean modifyResult = receiptBLService.modify(new String());
    	if(addResult)
    		System.out.println("modify receipt successfully!");
    	String[] ID = {"141250185","141250182","141250139","141250147"};
    	boolean BatchResult = receiptBLService.batch(ID);
    	boolean ApproveResult = receiptBLService.approve("141250185");
    	
    	ReceiptVO vo = new ReceiptVO();
    	receiptBLService.update(vo);
    	receiptBLService.reply("141250185");
    	receiptBLService.view();
    	receiptBLService.refresh(); 	
    }
    
    public static void main(String[] args){
    	ReceiptBLService receiptbl_stub = new ReceiptBLService_stub();
    	ReceiptBLService_driver driver = new ReceiptBLService_driver();
    	driver.drive(receiptbl_stub);
    }
}
