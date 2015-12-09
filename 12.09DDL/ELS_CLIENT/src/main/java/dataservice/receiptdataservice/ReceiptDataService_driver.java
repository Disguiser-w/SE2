package dataservice.receiptdataservice;

import po.ReceiptPO;

public class ReceiptDataService_driver {
    public void drive(ReceiptDataService receiptDateService){
    	ReceiptPO po = new ReceiptPO();
    	receiptDateService.init();
    	
    	boolean addResult = receiptDateService.add(po);
    	boolean modResult = receiptDateService.modify(po);
    	ReceiptPO receipt = receiptDateService.find("141250185");
    	receiptDateService.get();
    }
    
    public static void main(String[] args){
    	ReceiptDataService receipt_stub = new ReceiptDataService_stub();
    	ReceiptDataService_driver driver = new ReceiptDataService_driver();
    	driver.drive(receipt_stub);
    }
}
