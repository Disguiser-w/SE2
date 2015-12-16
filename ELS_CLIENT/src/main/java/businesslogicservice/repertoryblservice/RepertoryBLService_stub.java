package businesslogicservice.repertoryblservice;

import businesslogicservice.repertoryblservice.RepertoryBLService;
import vo.InventoryVO;
import vo.InventoryCheckVO;

import java.util.ArrayList;

public class RepertoryBLService_stub implements RepertoryBLService{

	public int inventoryInitialization(int maxRow, int maxShelf, int maxDigit, int warningRatio) {
		// TODO 自动生成的方法存根
		System.out.println("Initialize inventory succeed!");
		return 0;
	}

    public int enterRepertory(String JJD_ID, int blockNum, int rowNum, int shelfNum, int digitNum) {
        // TODO 自动生成的方法存根
        System.out.println("Enter repertory succeed!");
        return 0;
    }
		
    public int leaveRepertory(String JJD_ID) {
        // TODO 自动生成的方法存根
        System.out.println("Leave repertory succeed!");
        return 0;
    }
 
 	public String inventoryWarning() {
        // TODO 自动生成的方法存根
        System.out.println("Inventory warning succeed!");
        return "";
    }
    
    public InventoryCheckVO inventoryCheck(String beginDate, String endDate) {
        // TODO 自动生成的方法存根
        System.out.println("Inventory check succeed!");
        return null;
    }
    
    public ArrayList<InventoryVO> inventoryStockTaking() {
			// TODO 自动生成的方法存根
        System.out.println("Inventory stock taking succeed!");
        return null;  	       	    
    }
    
}
