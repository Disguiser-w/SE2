package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TransferingReceiptVO;

public class EntruckingBLService_driver {
    public void drive(EntruckingBLService ebls){
    	ebls.showTruckList();
    	ebls.showTruck(new String());
    	ebls.updateWaitingList(new TransferingReceiptVO());
    	ebls.entruck(new ArrayList<OrderVO>());
    	ebls.updateEntruckingReceiptList(new EntruckingReceiptVO());
    	ebls.computeFare(new ArrayList<EntruckingReceiptVO>());
    	ebls.updateFare(new FareVO());
    	ebls.showEntruckingReceipt(new ArrayList<EntruckingReceiptVO>());
    	ebls.updateEntruckingReceipt(new ArrayList<EntruckingReceiptVO>());
    }
    
    public static void main(String[] args){
    	EntruckingBLService_driver driver = new EntruckingBLService_driver();
    	driver.drive(new EntruckingBLService_stub());
    }
}
