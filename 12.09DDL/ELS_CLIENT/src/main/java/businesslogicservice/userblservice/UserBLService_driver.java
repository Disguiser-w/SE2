package businesslogicservice.userblservice;

import vo.UserVO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class UserBLService_driver {

	public void drive(UserBLService_stub userBLService){
        userBLService.login("KD-00001", "123456");
        userBLService.addUser(new UserVO("王梦娜", "KD-00001", "123456", ProfessionType.courier, "鼓楼营业厅", 
                SalaryPlanType.basicStaffSalaryPlan, AuthorityType.lowest, 0));
        userBLService.deleteUser("KD-00001");
        userBLService.modifyUserPassword("KD-00001", "123456");
        userBLService.modifyUserAuthority("KD-00001",AuthorityType.lowest);
        userBLService.modifyUserOrganization("KD-00001","仙林营业厅");
        userBLService.findUser("KD-00001");
        userBLService.showAllUsers();
    }
 
    public static void main(String[] args){
        UserBLService_stub userBLService = new UserBLService_stub();
        UserBLService_driver driver = new UserBLService_driver();
        driver.drive(userBLService);
    }
    
}
