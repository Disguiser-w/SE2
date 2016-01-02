package businesslogicservice.userblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.AuthorityType;
import vo.LogVO;
import vo.UserVO;

public interface UserBLService {
	
	public LogVO login(String userName, String password) throws RemoteException;
	public int addUser(UserVO uservo) throws RemoteException;
	public int deleteUser(String userID) throws RemoteException;
	public int modifyUserPassword(String userID, String newPassword) throws RemoteException;
	public int modifyUserAuthority(String userID, AuthorityType authority) throws RemoteException;
	public int modifyUserOrganization(String userID, String newOrganization) throws RemoteException;
	public int modifyUserGrades(String userID, int newGrade) throws RemoteException;
	public int clearGrades() throws RemoteException;
	public UserVO findUser(String userID) throws RemoteException;
	public ArrayList<UserVO> findUserByKeyword(String keyword) throws RemoteException;
	public ArrayList<UserVO> showAllUsers() throws RemoteException;
	
}
