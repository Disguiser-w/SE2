package businesslogicservice.repertoryblservice;

public class RepertoryBLService_driver {
	
	public void drive(RepertoryBLService_stub repertoryBLService){
    	
    	repertoryBLService.inventoryInitialization("025-0-CK", 100, 10, 10, 90);
        repertoryBLService.enterRepertory("025-0-CK", "JJD-20151001-00001", 0, 0, 0, 0, "20151002");
        repertoryBLService.leaveRepertory("025-0-CK", "JJD-20151001-00001", 0, "20151003");
        repertoryBLService.inventoryWarning(); 
        repertoryBLService.inventoryCheck("025-0-CK", "20151001", "20151003");
        repertoryBLService.inventoryStockTaking("025-0-CK");
    }
 
    public static void main(String[] args){
        RepertoryBLService_stub repertoryBLService = new RepertoryBLService_stub();
        RepertoryBLService_driver driver = new RepertoryBLService_driver();
        driver.drive(repertoryBLService);
    }
    
}
