package businesslogicservice.manageblservice;

import vo.CostVO;
import type.ExpressType;

public class CostBLService_driver {

	public void drive(CostBLService_stub costBLService){
		costBLService.addCost(new CostVO(ExpressType.standard, 23));
        costBLService.deleteCost(new CostVO(ExpressType.standard, 23));
        costBLService.modifyCost(new CostVO(ExpressType.standard, 23));
        costBLService.findCost(ExpressType.standard);
	}
	
	public static void main(String[] args){
		CostBLService_stub costBLService = new CostBLService_stub();
		CostBLService_driver driver = new CostBLService_driver();
        driver.drive(costBLService);
    }
	
}
