package businesslogic.userbl;

import java.rmi.Naming;
import java.rmi.RemoteException;
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
	 * @return 0(login succeed),1(password wrong),2(cannot find the user),3(server failed)
	 * @see UserPO
	 * 
	 * */
	public int login(String userID, String password){
		try{
			UserPO userpo = udService.findUser(userID);
			if(userpo.equals(null))   //找不到该用户，返回2
				return 2;
			else if(!(userpo.getPassword().equals(password)))	//用户密码错误，返回1
				return 1;
			else 
				return 0;	//登录成功，返回0
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 3;
		}
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0(add succeed), 1(user with the ID has already existed),2(server failed)
	 * @see UserPO
	 * 
	 * */
	public int addUser(UserVO uservo){
		try{
			String userID = uservo.getID();
			UserPO userpo = udService.findUser(userID);
			if(userpo.equals(null)){
				UserPO newuserpo = voToPO(uservo);
				udService.addUser(newuserpo);
				return 0;
			}
			else 
				return 1;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param String userID
	 * @return 0(delete succeed),1(server failed)
	 * @see UserPO
	 * 
	 * */
	public int deleteUser(String userID){
		try{
			udService.deleteUser(userID);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0(modify succeed),1(server failed)
	 * @see UserPO
	 * 
	 * */
	public int modifyUserPassword(UserVO uservo){
		try{
			UserPO userpo = voToPO(uservo);
			udService.modifyUser(userpo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0(modify succeed),1(server failed)
	 * @see UserPO
	 * 
	 * */
	public int modifyUserAuthority(UserVO uservo){
		try{
			UserPO userpo = voToPO(uservo);
			udService.modifyUser(userpo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * @param String userID
	 * @return uservo
	 * @see UserPO
	 * 
	 * */
	public UserVO findUser(String userID){
		try{
			UserPO userpo = udService.findUser(userID);
			if(userpo != null)
				return poToVO(userpo);
			else
				return null;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return ArrayList<UserVO>
	 * @see UserPO
	 * 
	 * */
	public ArrayList<UserVO> showAllUsers(){
		try{
			ArrayList<UserPO> userpoList = udService.showAllUsers();
			ArrayList<UserVO> uservoList= new ArrayList<UserVO>();
			for(UserPO userpo:userpoList){
				uservoList.add(poToVO(userpo));
			}
			return uservoList;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
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
