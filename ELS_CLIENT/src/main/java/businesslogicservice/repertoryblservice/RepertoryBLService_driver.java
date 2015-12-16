package businesslogicservice.repertoryblservice;

public class RepertoryBLService_driver {
	
	public void drive(RepertoryBLService_stub repertoryBLService){
    	
    	repertoryBLService.inventoryInitialization(100, 10, 10, 90);
        repertoryBLService.enterRepertory("JJD-20151001-00001", 0, 0, 0, 0);
        repertoryBLService.leaveRepertory("JJD-20151001-00001");
        repertoryBLService.inventoryWarning(); 
        repertoryBLService.inventoryCheck("20151001", "20151003");
        repertoryBLService.inventoryStockTaking();
    }
 
    public static void main(String[] args){
        RepertoryBLService_stub repertoryBLService = new RepertoryBLService_stub();
        RepertoryBLService_driver driver = new RepertoryBLService_driver();
        driver.drive(repertoryBLService);
    }
    
}
