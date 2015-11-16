package businesslogicservice.userblservice;

import businesslogicservice.userblservice.UserBLService;
import vo.UserVO;

import java.util.ArrayList;

public class UserBLService_stub implements UserBLService{

	    public int login(String userID, String password) {
	        // TODO 自动生成的方法存根
	        System.out.println("Login succeed!");
	        return 0;
	    }
	 	
		public int addUser(UserVO uservo) {
    		// TODO 自动生成的方法存根
    		System.out.println("Add user succeed!");
    		return 0;
		}

	    public int deleteUser(UserVO uservo) {
	        // TODO 自动生成的方法存根
	        System.out.println("Delete user succeed!");
	        return 0;
	    }
			
	    public int modifyUserPassword(UserVO uservo) {
	        // TODO 自动生成的方法存根
	        System.out.println("Modify user password succeed!");
	        return 0;
	    }
	    
	    public int modifyUserAuthority(UserVO uservo) {
	        // TODO 自动生成的方法存根
	        System.out.println("Modify user authority succeed!");
	        return 0;
	    }
	 
	    public UserVO findUser(String userID) {
	        // TODO 自动生成的方法存根
	        System.out.println("Find user succeed!");
	        return null;
	    }
	    
	    public ArrayList<UserVO> showAllUsers() {
	        // TODO 自动生成的方法存根
	        System.out.println("Show all users succeed!");
	        return null;
	    }
	    
}
