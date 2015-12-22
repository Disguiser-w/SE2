package businesslogicservice.repertoryblservice;

import java.rmi.RemoteException;

import businesslogic.repertorybl.RepertoryBL;

public class RepertoryBLService_driver {
	
	public void drive(RepertoryBLService repertoryBLService) throws RemoteException{
    	repertoryBLService.inventoryInitialization(100, 10, 10, 90);
        repertoryBLService.enterRepertory("20151001-00001", 0, 0, 0, 0);
        repertoryBLService.leaveRepertory("20151001-00001");
        repertoryBLService.inventoryWarning(); 
        repertoryBLService.inventoryCheck("20151001", "20151003");
        repertoryBLService.inventoryStockTaking();
        repertoryBLService.getMaxRow();
        repertoryBLService.getMaxShelf();
        repertoryBLService.getMaxDigit();
        repertoryBLService.getMaxRatio();
        repertoryBLService.showGoodBasicMessage("20151001-00001");
        repertoryBLService.showGoodIntermidiateMessage("20151001-00001");
        repertoryBLService.repertoryName("025-0");
    }
 
    public static void main(String[] args) throws RemoteException{
        RepertoryBLService repertoryBLService = new RepertoryBL("CK-00001");
        RepertoryBLService_driver driver = new RepertoryBLService_driver();
        driver.drive(repertoryBLService);
    }
    
}
