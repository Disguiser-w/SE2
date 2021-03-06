package dataservice.intermediatedataservice.TranferingDataService;

import po.TransferingReceiptPO;

public class TransferingDataService_driver {
    public void drive(TransferingDataService tbls){
    	tbls.getTransferingReceipt();
    	tbls.addOrder(new String());
    	tbls.deleteOrder(new String());
    	tbls.modifyOrder(new String());
    	tbls.updateTransferingReceipt(new TransferingReceiptPO());
    }
    
    public static void main(String[] args){
    	TransferingDataService_driver driver = new TransferingDataService_driver();
    	driver.drive(new TransferingDataService_stub());
    }
}
