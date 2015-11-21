package dataservice.userdataservice;

import po.UserPO;

import java.rmi.Remote;
import java.util.ArrayList;

public interface UserDataService extends Remote {
	
	public boolean check(String userID,String password);
    public int addUser(UserPO userpo);
    public int deleteUser(String userID);
    public int modifyUser(UserPO userpo);
    public UserPO findUser(String userID);
    public ArrayList<UserPO> showAllUsers();	
    
}
