package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import type.AuthorityType;
import type.ProfessionType;

public class UserDataService_stub implements UserDataService{
	
	public int addUser(UserPO userpo) throws RemoteException{
		System.out.println("Add user succeed!");
		return 0;
	}
	
    public int deleteUser(String userID) throws RemoteException{
		System.out.println("Delete user succeed!");
		return 0;
	}
    
    public int modifyUserPassword(String userID, String newPassword) throws RemoteException{
		System.out.println("Modify user password succeed!");
		return 0;
	}
    
    public int modifyUserAuthority(String userID, AuthorityType authority) throws RemoteException{
		System.out.println("Modify user authority succeed!");
		return 0;
	}
    
    public int modifyUserOrganization(String userID, String newOrganization) throws RemoteException{
		System.out.println("Modify user organization succeed!");
		return 0;
	}
    
    public int modifyUserGrades(String userID, int newGrade) throws RemoteException{
		System.out.println("Modify user grades succeed!");
		return 0;
	}
    
    public UserPO findUserByID(String userID) throws RemoteException{
		System.out.println("Find user succeed!");
		return null;
	}
    
    public ArrayList<UserPO> findUserByKeyword(String keyword) throws RemoteException{
    	System.out.println("Find user by keyword succeed!");
		return null;
    }
    
    public ArrayList<UserPO> showAllUsers() throws RemoteException{
		System.out.println("Show all users succeed!");
		return null;
	}
    
    public String getUserIDPost(ProfessionType profession) throws RemoteException{
    	System.out.println("Get user ID post succeed!");
    	return "";
    }
    
}
