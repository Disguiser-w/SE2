package businesslogicservice.userblservice;

import java.rmi.RemoteException;

import businesslogic.userbl.UserBL;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;

public class UserBLService_driver {

	public void drive(UserBLService userBLService) throws RemoteException{
        //userBLService.login("KD-00001", "123456");
        userBLService.deleteUser("KD-00001");
        userBLService.addUser(new UserVO("张词校", "KD-00001", "123456", ProfessionType.courier, "", 
                SalaryPlanType.basicStaffSalaryPlan, AuthorityType.lowest, 0));
        userBLService.modifyUserPassword("KD-00001", "666666");
        userBLService.modifyUserPassword("KD-00001", "123456");
        userBLService.modifyUserAuthority("KD-00001",AuthorityType.lowest);
        userBLService.modifyUserOrganization("KD-00001","025001");
        userBLService.modifyUserGrades("KD-00001",66);
        userBLService.findUser("KD-00001");
        userBLService.findUserByKeyword("KD");
        userBLService.showAllUsers();
    }
 
    public static void main(String[] args) throws RemoteException{
        UserBLService userBLService = new UserBL();
        UserBLService_driver driver = new UserBLService_driver();
        driver.drive(userBLService);
    }
    
}
