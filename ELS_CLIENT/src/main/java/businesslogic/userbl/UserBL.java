package businesslogic.userbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import businesslogicservice.userblservice.UserBLService;
import dataservice.userdataservice.UserDataService;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;
import vo.LogVO;

public class UserBL implements UserBLService{

	public static UserDataService udService;
	
	
	public UserBL(){
		try{
			udService = (UserDataService)Naming.lookup("rmi://localhost:8888/UserDataService");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * @param String userID, String password
	 * @return LogVO
	 * @see UserPO
	 * 
	 * */
	public LogVO login(String userID, String password){
		try{
			UserPO userpo = udService.findUser(userID);
			if(userpo==null)   //找不到该用户，返回2
				return new LogVO("The user doesn't exist", null);
			else if(!(userpo.getPassword().equals(password)))	//用户密码错误，返回1
				return new LogVO("The userID and the password don't match", null);
			else{ 
				return new LogVO("Login succeed", poToVO(userpo));	//登录成功，返回0	
			}						
		}catch(RemoteException exception){
			exception.printStackTrace();
			return new LogVO("The server failed", null);
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
			UserPO newuserpo = voToPO(uservo);
			return(udService.addUser(newuserpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param String userID
	 * @return 0(delete succeed),1(delete failed),2(server failed)
	 * @see UserPO
	 * 
	 * */
	public int deleteUser(String userID){
		try{
			return(udService.deleteUser(userID));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0(modify succeed),1(modify failed),2(server failed)
	 * @see UserPO
	 * 
	 * */
	public int modifyUserPassword(String userID, String newPassword){
		try{
			return(udService.modifyUserPassword(userID, newPassword));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param UserVO uservo
	 * @return 0(modify succeed),1(modify failed),2(server failed)
	 * @see UserPO
	 * 
	 * */
	public int modifyUserAuthority(String userID, AuthorityType authority){
		try{
			return(udService.modifyUserAuthority(userID, authority));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param String 
	 * @return 0(modify succeed),1(modify failed),2(server failed)
	 * @see UserPO
	 * 
	 * */
	public int modifyUserOrganization(String userID, String newOrganization){
		try{
			return(udService.modifyUserOrganization(userID, newOrganization));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param String userID
	 * @return UserVO
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
	
	public String getUserIDPost(ProfessionType profession){
		try{
			String IDPost = udService.getUserIDPost(profession); 
			return IDPost;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @param UserVO
	 * @return UserPO
	 * 
	 * */
	public static UserPO voToPO(UserVO uservo){
		UserPO userpo = new UserPO(uservo.getName(),uservo.getID(),uservo.getPassword(),uservo.getProfession(),
					uservo.getOrganization(),uservo.getSalaryPlan(),uservo.getAuthority(),uservo.getGrades());
		return userpo;
	}
	
	/**
	 * @param UserPO
	 * @return UserVO
	 * 
	 * */
	public static UserVO poToVO(UserPO userpo){
		UserVO uservo = new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	public static void main(String[] args){
		try {
			UserDataService userData = (UserDataService)Naming.lookup("rmi://172.25.132.40:6000/UserDataService");
			
			ArrayList<UserPO> userList0 = userData.showAllUsers();
			for(UserPO user:userList0)
				System.out.println("ID: "+user.getID()+", Name: "+user.getName()+", Profession: "+user.getProfession()+", Organization: "
				+user.getOrganization()+", SalaryPlan: "+user.getSalaryPlan()+", Authority: "+user.getAuthority()+", Grades: "+user.getGrades());

			userData.addUser(new UserPO("刘钦" ,"CK-01","123456", ProfessionType.stockman, "南京中转中心",
    					SalaryPlanType.basicStaffSalaryPlan, AuthorityType.lowest, 0));
			
			ArrayList<UserPO> userList1 = userData.showAllUsers();
			for(UserPO user:userList1)
				System.out.println("ID: "+user.getID()+", Name: "+user.getName()+", Profession: "+user.getProfession()+", Organization: "
				+user.getOrganization()+", SalaryPlan: "+user.getSalaryPlan()+", Authority: "+user.getAuthority()+", Grades: "+user.getGrades());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
