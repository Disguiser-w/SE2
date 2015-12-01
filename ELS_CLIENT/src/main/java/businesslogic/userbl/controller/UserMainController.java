package businesslogic.userbl.controller;

import java.rmi.RemoteException;

import dataservice.userdataservice.UserDataService;
import dataservice.userdataservice.UserDataService_stub;
import vo.UserVO;
import po.UserPO;

public class UserMainController {
	
	public static UserDataService userData;
	public static UserVO userVO;
	
	// UserData的初始化，UserVO的初始化在此进行
	public UserMainController(String userID){
	//RMI
		userData = new UserDataService_stub();
		try{
			userVO = userPOToVO(userData.findUser(userID));
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}
	
	// vo和po的转化,static
	public static UserPO userVOToPO(UserVO uservo){
		UserPO userpo = new UserPO(uservo.getName(),uservo.getID(),uservo.getPassword(),uservo.getProfession(),
					uservo.getOrganization(),uservo.getSalaryPlan(),uservo.getAuthority(),uservo.getGrades());
		return userpo;
	}
	
	public static UserVO userPOToVO(UserPO userpo){
		UserVO uservo = new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
	
}
