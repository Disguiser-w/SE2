package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

public class EntrainingBLService_driver {
    public void drive(EntrainingBLService ebls){
    	ebls.showTrainList();
    	ebls.showTrain(new String());
    	ebls.updateWaitingList(new TranferingReceiptVO());
    	ebls.entrain(new ArrayList<OrderVO>());
    	ebls.updateEntrainingReceiptList(new EntrainingReceiptVO());
    	ebls.computeFare(new ArrayList<EntrainingReceiptVO>());
    	ebls.updateFare(new FareVO());
    	ebls.showEntrainingReceipt(new ArrayList<EntrainingReceiptVO>());
    	ebls.updateEntrainingReceipt(new ArrayList<EntrainingReceiptVO>());
    }
    
    public static void main(String[] args){
    	EntrainingBLService_driver driver = new EntrainingBLService_driver();
    	driver.drive(new EntrainingBLService_stub());
    }
}
