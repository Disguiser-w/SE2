package businesslogicservice.intermediateblservice.transferingblservice;

import vo.TransferingReceiptVO;

public class TransferingBLService_driver {
    public void drive(TransferingBLService tbls){
    	tbls.showTransferingReceipt();
    	tbls.addOrder(new String());
    	tbls.deleteOrder(new String());
    	tbls.modifyOrder(new String());
    	tbls.updateTransferingReceipt(new TransferingReceiptVO(null,null,null,null));
    }
    
    public static void main(String[] args){
    	TransferingBLService_driver driver = new TransferingBLService_driver();
    	driver.drive(new TransferingBLService_stub());
    }
}
