package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

public class EnplaningDataService_driver {
    public void drive(EnplaningDataService ebls){
    	ebls.getPlaneList();
    	ebls.getPlane(new String());
    	ebls.updateWaitingList(new TranferingReceiptPO());
    	ebls.enplane(new ArrayList<OrderPO>());
    	ebls.updateEnplaningReceiptList(new EnplaningReceiptPO());
    	ebls.computeFare(new ArrayList<EnplaningReceiptPO>());
    	ebls.updateFare(new FarePO());
    	ebls.getEnenplaningReceipt(new ArrayList<EnenplaningReceiptPO>());
    	ebls.updateEnplaningReceipt(new ArrayList<EnplaningReceiptPO>());
    }
    
    public static void main(String[] args){
    	EnplaningDataService_driver driver = new EnplaningDataService_driver();
    	driver.drive(new EnplaningDataService_stub());
    }
}
