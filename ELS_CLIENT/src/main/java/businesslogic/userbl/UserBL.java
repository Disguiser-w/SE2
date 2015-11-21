package businesslogic.userbl;

import java.rmi.Naming;
import java.util.ArrayList;

import businesslogicservice.userblservice.UserBLService;
import dataservice.userdataservice.UserDataService;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;
import po.UserPO;

public class UserBL implements UserBLService{

	public UserDataService udService;
	
	/*public User() throws Exception{
		String host=getServer.getServer();
		String url="rmi://"+host+"/userService";
		udService=(UserDataService)Naming.lookup(url);
	}*/
	
	/**
	 * @param String userID, String password
	 * @return 0(login succeed),1(password wrong),2(cannot find the user)
	 * @see UserPO
	 * 
	 * */
	public int login(String userID, String password){
		UserPO userpo = udService.findUser(userID);
		if(userpo.equals(null))   //找不到该用户，返回2
			return 2;
		else if(!(userpo.getPassword().equals(password)))	//用户密码错误，返回1
			return 1;
		else 
			return 0;	//登录成功，返回0
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0
	 * @see UserPO
	 * 
	 * */
	public int addUser(UserVO uservo){
		UserPO userpo = voToPO(uservo);
		udService.addUser(userpo);
		return 0;
	}
	
	/**
	 * @param String userID
	 * @return 0
	 * @see UserPO
	 * 
	 * */
	public int deleteUser(String userID){
		udService.deleteUser(userID);
		return 0;
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0
	 * @see UserPO
	 * 
	 * */
	public int modifyUserPassword(UserVO uservo){
		UserPO userpo = voToPO(uservo);
		udService.modifyUser(userpo);
		return 0;
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0
	 * @see UserPO
	 * 
	 * */
	public int modifyUserAuthority(UserVO uservo){
		UserPO userpo = voToPO(uservo);
		udService.modifyUser(userpo);
		return 0;
	}
	
	/**
	 * @param String userID
	 * @return 0
	 * @see UserPO
	 * 
	 * */
	public UserVO findUser(String userID){
		UserPO userpo = udService.findUser(userID);
		if(userpo != null)
			return poToVO(userpo);
		else
			return null;
	}
	
	/**
	 * @return ArrayList<UserVO>
	 * @see UserPO
	 * 
	 * */
	public ArrayList<UserVO> showAllUsers(){
		ArrayList<UserPO> userpoList = udService.showAllUsers();
		ArrayList<UserVO> uservoList= new ArrayList<UserVO>();
		for(UserPO userpo:userpoList){
			uservoList.add(poToVO(userpo));
		}
		return uservoList;
	}
	
	/**
	 * @param UserVO
	 * @return UserPO
	 * 
	 * */
	public UserPO voToPO(UserVO uservo){
		UserPO userpo=new UserPO(uservo.getName(),uservo.getID(),uservo.getPassword(),uservo.getProfession(),
					uservo.getOrganization(),uservo.getSalaryPlan(),uservo.getAuthority(),uservo.getGrades());
		return userpo;
	}
	
	/**
	 * @param UserPO
	 * @return UserVO
	 * 
	 * */
	public UserVO poToVO(UserPO userpo){
		UserVO uservo=new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
}
