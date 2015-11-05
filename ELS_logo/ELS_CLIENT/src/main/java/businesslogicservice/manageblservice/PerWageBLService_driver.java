package businesslogicservice.manageblservice;

import type.ProfessionType;
import vo.PerWageVO;

public class PerWageBLService_driver {

	public void drive(PerWageBLService_stub perWageBLService){
		perWageBLService.addPerWage(new PerWageVO(ProfessionType.courier, 2000));
		perWageBLService.deletePerWage(new PerWageVO(ProfessionType.courier, 2000));
		perWageBLService.modifyPerWage(new PerWageVO(ProfessionType.courier, 2000));
		perWageBLService.findPerWage(ProfessionType.courier);
	}
	
	public static void main(String[] args){
		PerWageBLService_stub perWageBLService = new PerWageBLService_stub();
		PerWageBLService_driver driver = new PerWageBLService_driver();
        driver.drive(perWageBLService);
    }
	
}
