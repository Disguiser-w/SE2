package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntrainingReceiptPO;
import po.OrderPO;
import po.TransferingReceiptPO;
import po.farePO;

public class EntrainingDataService_driver {
    public void drive(EntrainingDataService ebls){
    	ebls.getTrainList();
    	ebls.getTrain(new String());
    	ebls.updateWaitingList(new TransferingReceiptPO());
    	ebls.entrain(new ArrayList<OrderPO>());
    	ebls.updateEntrainingReceiptList(new EntrainingReceiptPO());
    	ebls.computeFare(new ArrayList<EntrainingReceiptPO>());
    	ebls.updateFare(new farePO());
    	//拼写2333333
    	ebls.getEntraningReceipt(new ArrayList<EntrainingReceiptPO>());
    	ebls.updateEntraningReceipt(new ArrayList<EntrainingReceiptPO>());
    }
    
    public static void main(String[] args){
    	EntrainingDataService_driver driver = new EntrainingDataService_driver();
    	driver.drive(new EntrainingDataService_stub());
    }
}
