package businesslogic.userbl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businesslogic.datafactory.DataFactory;
import dataservice.userdataservice.UserDataService;
import vo.UserVO;
import po.UserPO;
import presentation.userui.AdminFrame;
import presentation.userui.UserMainPanel;

public class UserMainController {
	
	public static UserDataService userData;
	public static UserVO userVO;
	
	// UserData的初始化，UserVO的初始化在此进行
	public UserMainController(String userID){
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
			userVO = userPOToVO(userData.findUser(userID));
			AdminFrame adminFrame = new AdminFrame(userVO);
			adminFrame.addFuncLabel(new UserMainPanel(adminFrame));
			adminFrame.showFrame();
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
