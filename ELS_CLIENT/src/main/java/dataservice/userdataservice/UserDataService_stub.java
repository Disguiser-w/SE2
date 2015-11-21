package dataservice.userdataservice;

import dataservice.userdataservice.UserDataService;
import po.UserPO;

import java.util.ArrayList;

public class UserDataService_stub implements UserDataService{

    public int addUser(UserPO userpo){
        // TODO 自动生成的方法存根
        System.out.println("Add user in file succeed!");
        return 0;
    }
 
    public int deleteUser(String userID){
        // TODO 自动生成的方法存根
        System.out.println("Delete user in file succeed!");
        return 0;
    }
 
    public int modifyUser(UserPO userpo){
        // TODO 自动生成的方法存根
        System.out.println("Modify user in file succeed!");
        return 0;
    }
 
    public UserPO findUser(String userID){
        // TODO 自动生成的方法存根
        System.out.println("Find users in file succeed!");
        return null;
    }
    
    public ArrayList<UserPO> showAllUsers(){
        // TODO 自动生成的方法存根
        System.out.println("Show all users in file succeed!");
        return null;
    }
    
}
