package businesslogic.repertorybl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businesslogic.datafactory.DataFactory;
import po.UserPO;
import presentation.repertoryui.RepertoryFrame;
import vo.UserVO;
import dataservice.userdataservice.UserDataService;

public class RepertoryMainController {

	public static UserDataService userData;
	public static UserVO stockManVO;
	
	// UserData的初始化，UserVO的初始化在此进行
	public RepertoryMainController(String stockManID){
	//RMI
		try{
			userData = DataFactory.getUserData();
		}catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		try{
			stockManVO = userPOToVO(userData.findUser(stockManID));
			new RepertoryFrame(stockManVO);
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
		
	}
	
	// vo和po的转化,static
	public static UserPO userVOToPO(UserVO uservo){
		UserPO userpo = new UserPO(uservo.userName, uservo.userID, uservo.password, uservo.profession,
					uservo.organization, uservo.salaryPlan, uservo.authority, uservo.grades);
		return userpo;
	}
	
	public static UserVO userPOToVO(UserPO userpo){
		UserVO uservo = new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
}
