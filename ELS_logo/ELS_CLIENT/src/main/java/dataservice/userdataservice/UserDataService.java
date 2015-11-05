package dataservice.userdataservice;

import po.UserPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserDataService extends Remote {
	
	public boolean check(String userID,String password) throws RemoteException;
    public int add(UserPO userpo) throws RemoteException;
    public int delete(UserPO userpo) throws RemoteException;
    public int modify(UserPO userpo) throws RemoteException;
    public UserPO find(String userID) throws RemoteException;
    public ArrayList<UserPO> showAll() throws RemoteException;	
    
}
