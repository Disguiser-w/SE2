package businesslogic.userbl.controller;

import java.util.ArrayList;

import businesslogic.userbl.UserBL;
import businesslogicservice.userblservice.UserBLService;
import type.AuthorityType;
import vo.LogVO;
import vo.UserVO;

public class UserManageController implements UserBLService{

	private UserBL userBL;
	
	public UserManageController(){
		try {
			userBL = new UserBL();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LogVO login(String userID, String password){
		return userBL.login(userID, password);
	}
	
	public int addUser(UserVO uservo){
		return userBL.addUser(uservo);
	}
	
	public int deleteUser(String userID){
		return userBL.deleteUser(userID);
	}
	
	public int modifyUserPassword(String userID, String newPassword){
		return userBL.modifyUserPassword(userID, newPassword);
	}
	
	public int modifyUserAuthority(String userID, AuthorityType authority){
		return userBL.modifyUserAuthority(userID, authority);
	}
	
	public int modifyUserOrganization(String userID, String newOrganization){
		return userBL.modifyUserOrganization(userID, newOrganization);
	}
	
	public int modifyUserGrades(String userID, int newGrade){
		return userBL.modifyUserGrades(userID, newGrade);
	}
	public int clearGrades(){
		return userBL.clearGrades();
	}
	
	public UserVO findUser(String userID){
		return userBL.findUser(userID);
	}
	
	public ArrayList<UserVO> findUserByKeyword(String keyword){
		return userBL.findUserByKeyword(keyword);
	}
	
	public ArrayList<UserVO> showAllUsers(){
		return userBL.showAllUsers();
	}
	
}
