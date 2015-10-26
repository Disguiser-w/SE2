package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntruckingReceiptPO;
import po.OrderPO;
import po.TranferingReceiptPO;
import po.farePO;

public class EntruckingDataService_driver {
    public void drive(EntruckingDataService ebls){
    	ebls.getTruckList();
    	ebls.getTruck(new String());
    	ebls.updateWaitingList(new TranferingReceiptPO());
    	ebls.entruck(new ArrayList<OrderPO>());
    	ebls.updateEntruckingReceiptList(new EntruckingReceiptPO());
    	ebls.computeFare(new ArrayList<EntruckingReceiptPO>());
    	ebls.updateFare(new farePO());
    	ebls.getEntruckingReceipt(new ArrayList<EntruckingReceiptPO>());
    	ebls.updateEntruckingReceipt(new ArrayList<EntruckingReceiptPO>());
    }
    
    public static void main(String[] args){
    	EntruckingDataService_driver driver = new EntruckingDataService_driver();
    	driver.drive(new EntruckingDataService_stub());
    }
}
