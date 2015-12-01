package businesslogic.repertorybl.controller;

import java.rmi.RemoteException;

import po.UserPO;
import vo.UserVO;
import dataservice.userdataservice.UserDataService;
import dataservice.userdataservice.UserDataService_stub;

public class RepertoryMainController {

	public static UserDataService stockManData;
	public static UserVO stockManVO;
	
	// UserData的初始化，UserVO的初始化在此进行
	public RepertoryMainController(String stockManID){
	//RMI
		stockManData = new UserDataService_stub();
		try{
			stockManVO = userPOToVO(stockManData.findUser(stockManID));
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
