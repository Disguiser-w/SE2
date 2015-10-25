package businesslogic.userbl;

import businesslogicservice.userblservice.UserBLService;
import vo.UserVO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

import java.util.ArrayList;

public class UserBL implements UserBLService{
	//public class UserBLService_stub implements UserBLService{
	
	private String userName;
	private String userID;
	private String password;
	private ProfessionType profession;
	private String department;
	private SalaryPlanType salaryPlan;
	private AuthorityType authority;
	private int grades;
	
		public UserBL() throws Exception{
		//	System.setSecurityManager(new SecurityManager());
		//	String host=getServer.getServer();
		//	String url="rmi://"+host+"/userService";
		
		//	service=(UserDataService)Naming.lookup(url);
			
		}
		
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
	    
	//}
}
