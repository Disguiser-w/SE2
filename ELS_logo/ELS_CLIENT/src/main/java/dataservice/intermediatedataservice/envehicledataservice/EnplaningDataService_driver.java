package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EnplaningReceiptPO;
import po.OrderPO;
import po.TransferingReceiptPO;
import po.FarePO;

public class EnplaningDataService_driver {
    public void drive(EnplaningDataService ebls){
    	ebls.getPlaneList();
    	ebls.getPlane(new String());
    	ebls.updateWaitingList(new TransferingReceiptPO());
    	ebls.enplane(new ArrayList<OrderPO>());
    	ebls.updateEnplaningReceiptList(new EnplaningReceiptPO());
    	ebls.computeFare(new ArrayList<EnplaningReceiptPO>());
    	ebls.updateFare(new FarePO());
    	ebls.getEnplaningReceipt(new ArrayList<EnplaningReceiptPO>());
    	ebls.updateEnplaningReceipt(new ArrayList<EnplaningReceiptPO>());
    }
    
    public static void main(String[] args){
    	EnplaningDataService_driver driver = new EnplaningDataService_driver();
    	driver.drive(new EnplaningDataService_stub());
    }
}
