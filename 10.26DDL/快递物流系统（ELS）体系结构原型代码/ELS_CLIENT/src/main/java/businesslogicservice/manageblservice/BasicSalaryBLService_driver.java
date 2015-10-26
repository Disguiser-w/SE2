package businesslogicservice.manageblservice;

import vo.BasicSalaryVO;
import type.ProfessionType;

public class BasicSalaryBLService_driver {

	public void drive(BasicSalaryBLService_stub basicSalaryBLService){
		basicSalaryBLService.addBasicSalary(new BasicSalaryVO(ProfessionType.courier, 2000));
		basicSalaryBLService.deleteBasicSalary(new BasicSalaryVO(ProfessionType.courier, 2000));
		basicSalaryBLService.modifyBasicSalary(new BasicSalaryVO(ProfessionType.courier, 2000));
		basicSalaryBLService.findBasicSalary(ProfessionType.courier);
	}
	
	public static void main(String[] args){
		BasicSalaryBLService_stub basicSalaryBLService = new BasicSalaryBLService_stub();
		BasicSalaryBLService_driver driver = new BasicSalaryBLService_driver();
        driver.drive(basicSalaryBLService);
    }
	
}
