package businesslogicservice.manageblservice;

import java.util.ArrayList;

import type.ExpressType;
import vo.CostVO;

public class CostBLService_stub implements CostBLService{
	
	public int addCost(CostVO costvo) {
		// TODO 自动生成的方法存根
		System.out.println("Add cost succeed!");
		return 0;
	}

    public int deleteCost(CostVO costvo) {
        // TODO 自动生成的方法存根
        System.out.println("Delete cost succeed!");
        return 0;
    }
		
    public int modifyCost(CostVO costvo) {
        // TODO 自动生成的方法存根
        System.out.println("Modify cost succeed!");
        return 0;
    }
    
    public double findCost(ExpressType category) {
        System.out.println("Find cost succeed!");
        return 0;
    }
    
    public ArrayList<CostVO> showAllCosts(){
    	// TODO 自动生成的方法存根
        System.out.println("Show all cost succeed!");
        return null;
    }
    
}