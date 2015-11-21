package businesslogicservice.userblservice;

import vo.UserVO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class UserBLService_driver {

	public void drive(UserBLService_stub userBLService){
        userBLService.login("KD-00001", "123456");
        userBLService.addUser(new UserVO("王梦娜", "KD-00001", "123456", ProfessionType.courier, "鼓楼营业厅", 
                SalaryPlanType.countermanSalaryPlan, AuthorityType.lowest, 0));
        userBLService.deleteUser("KD-00001");
        userBLService.modifyUserPassword(new UserVO("王梦娜", "KD-00001", "123456", ProfessionType.courier, "鼓楼营业厅", 
                SalaryPlanType.countermanSalaryPlan, AuthorityType.lowest, 0));
        userBLService.modifyUserAuthority(new UserVO("王梦娜", "KD-00001", "123456", ProfessionType.courier, "鼓楼营业厅", 
                SalaryPlanType.countermanSalaryPlan, AuthorityType.lowest, 0));
        userBLService.findUser("KD-00001");
        userBLService.showAllUsers();
    }
 
    public static void main(String[] args){
        UserBLService_stub userBLService = new UserBLService_stub();
        UserBLService_driver driver = new UserBLService_driver();
        driver.drive(userBLService);
    }
    
}
