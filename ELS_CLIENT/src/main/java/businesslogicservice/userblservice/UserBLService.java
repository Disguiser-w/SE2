package businesslogicservice.userblservice;

import vo.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserBLService {
	
	public int login(String userID, String password) throws RemoteException;
	public int addUser(UserVO uservo) throws RemoteException;
	public int deleteUser(String userID) throws RemoteException;
	public int modifyUserPassword(UserVO uservo) throws RemoteException;
	public int modifyUserAuthority(UserVO uservo) throws RemoteException;
	public UserVO findUser(String userID) throws RemoteException;
	public ArrayList<UserVO> showAllUsers() throws RemoteException;
	
}
