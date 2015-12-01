package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;

public class UserDataService_stub implements UserDataService{
	
	public int addUser(UserPO userpo) throws RemoteException{
		System.out.println("Add user succeed!");
		return 0;
	}
	
    public int deleteUser(String userID) throws RemoteException{
		System.out.println("Delete user succeed!");
		return 0;
	}
    
    public int modifyUser(UserPO userpo) throws RemoteException{
		System.out.println("Modify user succeed!");
		return 0;
	}
    
    public UserPO findUser(String userID) throws RemoteException{
		System.out.println("Find user succeed!");
		return null;
	}
    
    public ArrayList<UserPO> showAllUsers() throws RemoteException{
		System.out.println("Show all users succeed!");
		return null;
	}
    
}
