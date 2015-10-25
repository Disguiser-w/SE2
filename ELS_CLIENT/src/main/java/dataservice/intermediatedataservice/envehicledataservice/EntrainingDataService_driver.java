package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

public class EntrainingDataService_driver {
    public void drive(EntrainingDataService ebls){
    	ebls.getTrainList();
    	ebls.getTrain(new String());
    	ebls.updateWaitingList(new TranferingReceiptPO());
    	ebls.entrain(new ArrayList<OrderPO>());
    	ebls.updateEntrainingReceiptList(new EntrainingReceiptPO());
    	ebls.computeFare(new ArrayList<EntrainingReceiptPO>());
    	ebls.updateFare(new FarePO());
    	ebls.getEntrainingReceipt(new ArrayList<EntrainingReceiptPO>());
    	ebls.updateEntrainingReceipt(new ArrayList<EntrainingReceiptPO>());
    }
    
    public static void main(String[] args){
    	EntrainingDataService_driver driver = new EntrainingDataService_driver();
    	driver.drive(new EntrainingDataService_stub());
    }
}
