package businesslogicservice.intermediateblservice.tranferingblservice;

public class TransferingBLService_driver {
    public void drive(TransferingBLService tbls){
    	tbls.showTransferingReceipt();
    	tbls.addOrder(new String());
    	tbls.deleteOrder(new String());
    	tbls.modifyOrder(new String());
    	tbls.updateTransferingReceipt(new TransferingReceiptVO());
    }
    
    public static void main(String[] args){
    	TransferingBLService_driver driver = new TransferingBLService_driver();
    	driver.drive(new TransferingBLService_stub());
    }
}
