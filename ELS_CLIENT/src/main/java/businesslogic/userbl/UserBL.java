package businesslogic.userbl;

//import java.net.MalformedURLException;
import java.rmi.Naming;
//import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import businesslogicservice.userblservice.UserBLService;
import dataservice.userdataservice.UserDataService;
import type.AuthorityType;
import type.ProfessionType;
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
	 * 登录
	 * @param String userID, String password
	 * @return LogVO
	 * @see UserPO
	 * 
	 * */
	public LogVO login(String userID, String password){
		try{
			UserPO userpo = udService.findUserByID(userID);
			if(userpo == null )   //找不到该用户，返回2
				return new LogVO("The user doesn't exist", null);
			else if(!(userpo.getPassword().equals(password)))	//用户密码错误，返回1
				return new LogVO("The userID and the password don't match", null);
			else{ 
				return new LogVO("Login succeed", userPOToVO(userpo));	//登录成功，返回0	
			}						
		}catch(RemoteException exception){
			exception.printStackTrace();
			return new LogVO("The server failed", null);
		}
	}
	
	
	/**
	 * 新增用户
	 * @param UserVO uservo
	 * @return int : 0(add succeed), 1(user with the ID has already existed), 2(server failed)
	 * @see UserPO
	 * 
	 * */
	public int addUser(UserVO uservo){
		try{
			UserPO newuserpo = userVOToPO(uservo);
			return(udService.addUser(newuserpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	
	/**
	 * 删除用户
	 * @param String userID
	 * @return int : 0(delete succeed), 1(delete failed), 2(server failed)
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
	 * 修改用户密码
	 * @param String userID, String newPassword
	 * @return 0(modify succeed), 1(modify failed), 2(server failed)
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
	 * 修改用户权限
	 * @param String userID, AuthorityType authority
	 * @return 0(modify succeed), 1(modify failed), 2(server failed)
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
	 * 修改用户机构
	 * @param String userID, String newOrganization
	 * @return 0(modify succeed), 1(modify failed), 2(server failed)
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
	 * 修改用户绩点
	 * @param String userID, int newGrade
	 * @return 0(modify succeed), 1(modify failed), 2(server failed)
	 * @see UserPO
	 * 
	 * */
	public int modifyUserGrades(String userID, int newGrade){
		try{
			return(udService.modifyUserGrades(userID, newGrade));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	
	/**
	 * 根据编号查找用户（精确搜索）
	 * @param String userID
	 * @return UserVO
	 * @see UserPO
	 * 
	 * */
	public UserVO findUser(String userID){
		try{
			UserPO userpo = udService.findUserByID(userID);
			if(userpo != null)
				return userPOToVO(userpo);
			else
				return null;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 根据关键字查找用户（模糊搜索）
	 * @param String keyword
	 * @return ArrayList<UserVO>
	 * @see UserPO
	 * 
	 * */
	public ArrayList<UserVO> findUserByKeyword(String keyword){
		try{
			ArrayList<UserPO> userpoList = udService.findUserByKeyword(keyword);
			if(userpoList==null)
				return null;
			else{
				ArrayList<UserVO> uservoList = new ArrayList<UserVO>();
				for(int i=0;i<userpoList.size();i++){
					uservoList.add(userPOToVO(userpoList.get(i)));
				}
				return uservoList;
			}
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 显示所有用户
	 * @return ArrayList<UserVO>
	 * @see UserPO
	 * 
	 * */
	public ArrayList<UserVO> showAllUsers(){
		try{
			ArrayList<UserPO> userpoList = udService.showAllUsers();
			ArrayList<UserVO> uservoList= new ArrayList<UserVO>();
			for(UserPO userpo:userpoList){
				uservoList.add(userPOToVO(userpo));
			}
			return uservoList;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获得某职业用户编号后缀
	 * @return String 
	 * 
	 * */
	public String getUserIDPost(ProfessionType profession){
		try{
			String IDPost = udService.getUserIDPost(profession); 
			return IDPost;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return "";
		}
	}
	
	
	
	/*-----------------------------------------------VO与PO的相互转换-----------------------------------------*/
	/**
	 * @param UserVO
	 * @return UserPO
	 * 
	 * */
	public static UserPO userVOToPO(UserVO uservo){
		UserPO userpo = new UserPO(uservo.userName,uservo.userID,uservo.password,uservo.profession,
					uservo.organization,uservo.salaryPlan,uservo.authority,uservo.grades);
		return userpo;
	}
	
	/**
	 * @param UserPO
	 * @return UserVO
	 * 
	 * */
	public static UserVO userPOToVO(UserPO userpo){
		UserVO uservo = new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
	
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	/*public static void main(String[] args){
		try {
			UserDataService userData = (UserDataService)Naming.lookup("rmi://172.25.132.40:6000/UserDataService");
			
			ArrayList<UserPO> userList0 = userData.showAllUsers();
			for(UserPO user:userList0)
				System.out.println("ID: "+user.getID()+", Name: "+user.getName()+", Profession: "+user.getProfession()+", Organization: "
				+user.getOrganization()+", SalaryPlan: "+user.getSalaryPlan()+", Authority: "+user.getAuthority()+", Grades: "+user.getGrades());

			userData.addUser(new UserPO("刘钦" ,"CK-00001","123456", ProfessionType.stockman, "南京中转中心",
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
	}*/
	
	
}
