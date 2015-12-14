package dataservice.userdataservice;

import po.UserPO;
import type.AuthorityType;
import type.ProfessionType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserDataService extends Remote {
	
	public int addUser(UserPO userpo) throws RemoteException;
    public int deleteUser(String userID) throws RemoteException;
    public int modifyUserPassword(String userID, String newPassword) throws RemoteException;
    public int modifyUserAuthority(String userID, AuthorityType authority) throws RemoteException;
    public int modifyUserOrganization(String userID, String newOrganization) throws RemoteException;
    public UserPO findUser(String userID) throws RemoteException;
    public ArrayList<UserPO> findUserByKeyword(String keyword) throws RemoteException;
    public ArrayList<UserPO> showAllUsers() throws RemoteException;	
    public String getUserIDPost(ProfessionType profession) throws RemoteException;
    
}
