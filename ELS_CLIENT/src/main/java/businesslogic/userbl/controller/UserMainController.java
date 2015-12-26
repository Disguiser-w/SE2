package businesslogic.userbl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.ImageGetter;
import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.controller.OrganizationManageController;
import dataservice.userdataservice.UserDataService;
import vo.UserVO;
import po.UserPO;
import presentation.userui.AdminFrame;
import presentation.userui.UserMainPanel;

public class UserMainController {
	
	public static UserDataService userData;
	
	public static UserVO userVO;
	
	private UserManageController userManageController;
	private OrganizationManageController organizationManageController;
	
	private AdminFrame adminFrame;
	
	// UserData的初始化，UserVO的初始化在此进行
	public UserMainController(String userID){
	//RMI
		try{
			userData = DataFactory.getUserData();
			userVO = userPOToVO(userData.findUserByID(userID));
		}catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		userManageController = new UserManageController();
		organizationManageController = new OrganizationManageController();
		
		adminFrame = new AdminFrame(userVO);
		adminFrame.addFuncLabel(new UserMainPanel(adminFrame, userManageController, organizationManageController), "用户管理", ImageGetter.getImage("userManager.png").getImage());
		adminFrame.showFrame();
		
	}
	
	
	//vo和po的转化,static
	public static UserPO userVOToPO(UserVO uservo){
		UserPO userpo = new UserPO(uservo.userName,uservo.userID,uservo.password,uservo.profession,
				uservo.organization,uservo.salaryPlan,uservo.authority,uservo.grades);
		return userpo;
	}
	
	public static UserVO userPOToVO(UserPO userpo){
		UserVO uservo = new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
	
}
