package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.UserPO;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import dataservice.userdataservice.UserDataService;
import file.JXCFile;

public class UserData implements UserDataService {	//extends UnicastRemoteObject???

	//我也不知道下面这句话有什么用？？？
	//private static final long serialVersionUID = 1L;

    JXCFile userFile;
    
    public UserData() throws RemoteException {
		userFile = new JXCFile("src/main/java/user.ser");
	}
    
    public int addUser(UserPO userpo) throws RemoteException{
    	if(findUser(userpo.getUserID())==null){
    		userFile.write(userpo);
    		return 0;
    	}
    	else 
    		return 1;
    }
    
    public int deleteUser(String userID) throws RemoteException{
    	ArrayList<Object> objectList = userFile.read();
    	
		if(objectList==null)	//不存在该用户	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			UserPO tempUserPO = (UserPO)(objectList.get(i));
			if(tempUserPO.getUserID().equals(userID)){
				objectList.remove(i);
				break;
			}
		}
		
		//userFile.clear();
		userFile.writeM(objectList);
		return 0;
    }
    
    public int modifyUser(UserPO userpo) throws RemoteException{
    	ArrayList<Object> objectList = userFile.read();
    	
		if(objectList==null)	//不存在该用户	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			UserPO tempUserPO = (UserPO)(objectList.get(i));
			if(tempUserPO.getUserID().equals(userpo.getUserID())){
				objectList.add(userpo);
				objectList.remove(i);
				break;
			}
		}
		
		userFile.writeM(objectList);
		return 0;
    }
    
    public UserPO findUser(String userID) throws RemoteException{
    	ArrayList<Object> objectList = userFile.read();
    	
		if(objectList==null)	//不存在该用户	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			UserPO tempUserPO = (UserPO)(objectList.get(i));
			if(tempUserPO.getUserID().equals(userID)){
				return tempUserPO;
			}
		}
		
		return null;
    }
    
    public ArrayList<UserPO> showAllUsers() throws RemoteException{
    	ArrayList<Object> objectList = userFile.read();
    	
		if(objectList==null)	//不存在该用户	
			return null;  	  
		
		ArrayList<UserPO> userList = new ArrayList<UserPO>();
		
		for(int i=0; i<objectList.size(); i++){
			UserPO tempUserPO = (UserPO)(objectList.get(i));
			userList.add(tempUserPO);
		}
		
		return userList;
    }	
    
    
    
   /* public static void main(String[] args){
    	UserData userData;
    	try{
    		userData = new UserData();
    		try{
    			userData.addUser(new UserPO("魏彦淑" ,"JL-01", "123456", ProfessionType.manager, "总部",
    					SalaryPlanType.managerSalaryPlan, AuthorityType.highest, 0));
    			userData.addUser(new UserPO("王丽莉" ,"CW-01", "123456", ProfessionType.financialStaff, "总部",
    					SalaryPlanType.financialStaffSalaryPlan, AuthorityType.highest, 0));
    			userData.addUser(new UserPO("丁二玉" ,"GLY-01","123456", ProfessionType.administrator, "总部",
    					SalaryPlanType.managerSalaryPlan, AuthorityType.administrator, 0));
    			userData.addUser(new UserPO("张家盛" ,"YYT-01","123456", ProfessionType.counterman, "鼓楼营业厅",
    					SalaryPlanType.countermanSalaryPlan, AuthorityType.lowest, 0));
    			userData.addUser(new UserPO("张词校" ,"KD-01","123456", ProfessionType.courier, "鼓楼营业厅",
    					SalaryPlanType.courierSalaryPlan, AuthorityType.lowest, 0));
    			
    			UserPO userpo = userData.findUser("KD-01");
    			if(userpo != null)
    				System.out.println(userpo.getName()+" "+userpo.getUserID()+" "+userpo.getOrganization()+" "+userpo.getProfession());
    			else
    				System.out.println("Cannot find the user");
    			
    			System.out.println("没有删除前:");
    			ArrayList<UserPO> userpoList1 = userData.showAllUsers();
    			if(userpoList1 != null){
	    			for(int i=0;i<userpoList1.size();i++){
	    				UserPO tempUserpo = userpoList1.get(i);
	    				System.out.println(tempUserpo.getName()+"  "+tempUserpo.getUserID()+"  "+tempUserpo.getOrganization()+"  "+tempUserpo.getProfession());
	    			}
    			}
    			else 
    				System.out.println("Cannot find the user");
    			
    			System.out.println("删除后:");
    			userData.deleteUser("CW-01");
    			ArrayList<UserPO> userpoList2 = userData.showAllUsers();
    			if(userpoList2 != null){
	    			for(int i=0;i<userpoList2.size();i++){
	    				UserPO tempUserpo = userpoList2.get(i);
	    				System.out.println(tempUserpo.getName()+"  "+tempUserpo.getUserID()+"  "+tempUserpo.getOrganization()+"  "+tempUserpo.getProfession());
	    			}
    			}
    			else 
    				System.out.println("Cannot find the user");
    			
    			System.out.println("修改后:");
    			userData.modifyUser(new UserPO("张Doge" ,"KD-01","123456", ProfessionType.courier, "仙林营业厅",
    					SalaryPlanType.courierSalaryPlan, AuthorityType.lowest, 0));
    			ArrayList<UserPO> userpoList3 = userData.showAllUsers();
    			if(userpoList3 != null){
	    			for(int i=0;i<userpoList3.size();i++){
	    				UserPO tempUserpo = userpoList3.get(i);
	    				System.out.println(tempUserpo.getName()+"  "+tempUserpo.getUserID()+"  "+tempUserpo.getOrganization()+"  "+tempUserpo.getProfession());
	    			}
    			}
    			else 
    				System.out.println("Cannot find the user");
    			
    		}catch(RemoteException exception){
    			exception.printStackTrace();
    		}
    	}catch(RemoteException exception){
			exception.printStackTrace();
    	}
    }*/
    
    
}
