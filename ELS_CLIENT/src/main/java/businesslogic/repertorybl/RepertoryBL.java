package businesslogic.repertorybl;

import businesslogicservice.repertoryblservice.RepertoryBLService;
import vo.GoodsVO;

import java.util.ArrayList;

public class RepertoryBL implements RepertoryBLService{
	
	public int inventoryInitialization(String repertoryID, int maxRow, int maxShelf, int Maxdigit, int warningRatio) {
		// TODO 自动生成的方法存根
		System.out.println("Initialize inventory succeed!");
		return 0;
	}

    public int enterRepertory(String repertoryID, String JJD_ID, int blockNum, int rowNum, int shelfNum, int digitNum, String date) {
        // TODO 自动生成的方法存根
        System.out.println("Enter repertory succeed!");
        return 0;
    }
		
    public int leaveRepertory(String repertoryID, String JJD_ID, int transType, String date) {
        // TODO 自动生成的方法存根
        System.out.println("Leave repertory succeed!");
        return 0;
    }
 
 	public boolean inventoryWarning() {
        // TODO 自动生成的方法存根
        System.out.println("Inventory warning succeed!");
        return true;
    }
    
    public ArrayList<GoodsVO> inventoryCheck(String repertoryID, String beginDate, String endDate) {
        // TODO 自动生成的方法存根
        System.out.println("Inventory check succeed!");
        return null;
    }
    
    public ArrayList<GoodsVO> inventoryStockTaking(String repertoryID) {
			// TODO 自动生成的方法存根
        System.out.println("Inventory stock taking succeed!");
        return null;  	       	    
    }
    
}
