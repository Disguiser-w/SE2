package dataservice.userdataservice;

import dataservice.userdataservice.UserDataService;
import po.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserDataService_stub implements UserDataService{

	public boolean check(String userID,String password) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Check user in file succeed!");
        return true;
    }
    
    public int add(UserPO userpo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Add user in file succeed!");
        return 0;
    }
 
    public int delete(UserPO userpo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Delete user in file succeed!");
        return 0;
    }
 
    public int modify(UserPO userpo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Modify user in file succeed!");
        return 0;
    }
 
    public UserPO find(String userID) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Find users in file succeed!");
        return null;
    }
    
    public ArrayList<UserPO> showAll() throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Show all users in file succeed!");
        return null;
    }
    
}
