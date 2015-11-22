package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;

public interface UserDataService extends Remote {
	
    public int addUser(UserPO userpo) throws RemoteException;
    public int deleteUser(String userID) throws RemoteException;
    public int modifyUser(UserPO userpo) throws RemoteException;
    public UserPO findUser(String userID) throws RemoteException;
    public ArrayList<UserPO> showAllUsers() throws RemoteException;	
    
}
