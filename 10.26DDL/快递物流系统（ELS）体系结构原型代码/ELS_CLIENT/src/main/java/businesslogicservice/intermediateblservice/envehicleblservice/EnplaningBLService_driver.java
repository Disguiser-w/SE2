package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import vo.TransferingReceiptVO;

public class EnplaningBLService_driver {
    public void drive(EnplaningBLService ebls){
    	ebls.showPlaneList();
    	ebls.showPlane(new String());
    	ebls.updateWaitingList(new TransferingReceiptVO());
    	ebls.enplane(new ArrayList<OrderVO>());
    	ebls.updateEnplaningReceiptList(new EnplaningReceiptVO());
    	ebls.computeFare(new ArrayList<EnplaningReceiptVO>());
    	ebls.updateFare(new FareVO());
    	ebls.showEnplaningReceipt(new ArrayList<EnplaningReceiptVO>());
    	ebls.updateEnplaningReceipt(new ArrayList<EnplaningReceiptVO>());
    }
    
    public static void main(String[] args){
    	EnplaningBLService_driver driver = new EnplaningBLService_driver();
    	driver.drive(new EnplaningBLService_stub());
    }
}
