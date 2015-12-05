package data.userdata;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.UserPO;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import dataservice.userdataservice.UserDataService;
import file.JXCFile;

public class UserData extends UnicastRemoteObject implements UserDataService {	//extends UnicastRemoteObject???

	//我也不知道下面这句话有什么用，只是因为继承了UnicastRemoteObject所以要声明这样一个字段
	private static final long serialVersionUID = 131250147L;

    JXCFile userFile;
    
    public UserData() throws RemoteException {
		userFile = new JXCFile("src/user.ser");
	}
    
    public int addUser(UserPO userpo) throws RemoteException{
    	ArrayList<Object> objectList = userFile.read();
    	
		if(objectList==null){
			System.out.println("null qingkuang");
			objectList = new ArrayList<Object>();
			objectList.add(userpo);
			userFile.writeM(objectList);
			return 0;
		}
		
		else{
			for(int i=0; i<objectList.size(); i++){
				UserPO tempUserPO = (UserPO)(objectList.get(i));
				if(tempUserPO.getName().equals(userpo.getName())){
					System.out.println("chongmingle!!!");
					return 1;
				}
			}
			objectList.add(userpo);
			userFile.writeM(objectList);
			return 0;
		}
		
    }
    
    public int deleteUser(String userID) throws RemoteException{
    	ArrayList<Object> objectList = userFile.read();
    	
		if(objectList==null)
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
    	
		if(objectList==null)
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
    	
		if(objectList==null)
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
    	
		if(objectList==null)
			return null;  	  
		
		ArrayList<UserPO> userList = new ArrayList<UserPO>();
		
		for(int i=0; i<objectList.size(); i++){
			UserPO tempUserPO = (UserPO)(objectList.get(i));
			userList.add(tempUserPO);
		}
		
		return userList;
    }	
    
    public String getUserIDPost(ProfessionType profession) throws RemoteException{
    	ArrayList<Object> objectList = userFile.read();
    	
		if(objectList==null)
			return "00001";  	  
		
		int professionCount = 0; //用来计数，看该职业的用户已经有了多少个
		for(int i=0; i<objectList.size(); i++){
			UserPO tempUserPO = (UserPO)(objectList.get(i));
			if(tempUserPO.getProfession().equals(profession)){
				professionCount += 1;
			}
		}
		
		professionCount += 1;//得到新用户的编号是这种职业已有人数，再加上1
		//以5位数字形式返回
		if(professionCount<=9){
			return "0000"+professionCount;
		}
		else if(professionCount>=10 && professionCount<=100){
			return "000"+professionCount;
		}
		else{
			return "00"+professionCount;
		}
    }
    
    
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
    
    /*public static void main(String[] args){
    	UserData userData;
    	try{
    		userData = new UserData();
    		try{
    			userData.addUser(new UserPO("魏彦淑" ,"JL-01", "123456", ProfessionType.manager, "总部",
    					SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0));
    			userData.addUser(new UserPO("王丽莉" ,"CW-01", "123456", ProfessionType.financialStaff, "总部",
    					SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0));
    			userData.addUser(new UserPO("丁二玉" ,"GLY-01","123456", ProfessionType.administrator, "总部",
    					SalaryPlanType.basicStaffSalaryPlan, AuthorityType.administrator, 0));
    			userData.addUser(new UserPO("张家盛" ,"YYT-01","123456", ProfessionType.businessHallCounterman, "鼓楼营业厅",
    					SalaryPlanType.basicStaffSalaryPlan, AuthorityType.lowest, 0));
    			userData.addUser(new UserPO("张词校" ,"KD-01","123456", ProfessionType.courier, "鼓楼营业厅",
    					SalaryPlanType.courierSalaryPlan, AuthorityType.lowest, 0));
    			
    			System.out.println("添加后:");
				ArrayList<UserPO> userpoList0 = userData.showAllUsers();
				if(userpoList0 != null){
	    			for(int i=0;i<userpoList0.size();i++){
	    				UserPO tempUserpo = userpoList0.get(i);
	    				System.out.println(tempUserpo.getName()+"  "+tempUserpo.getUserID()+"  "+tempUserpo.getOrganization()+"  "+tempUserpo.getProfession());
	    			}
				}
				
    			UserPO userpo = userData.findUser("KD-01");
    			if(userpo != null)
    				System.out.println("Find the user: "+userpo.getName()+" "+userpo.getUserID()+" "+userpo.getOrganization()+" "+userpo.getProfession());
    			else
    				System.out.println("Cannot find the user");
    			
    			userData.modifyUser(new UserPO("张Doge" ,"KD-01","123456", ProfessionType.courier, "仙林营业厅",
    					SalaryPlanType.courierSalaryPlan, AuthorityType.lowest, 0));
    			System.out.println("修改后:");
    			ArrayList<UserPO> userpoList3 = userData.showAllUsers();
    			if(userpoList3 != null){
	    			for(int i=0;i<userpoList3.size();i++){
	    				UserPO tempUserpo = userpoList3.get(i);
	    				System.out.println(tempUserpo.getName()+"  "+tempUserpo.getUserID()+"  "+tempUserpo.getOrganization()+"  "+tempUserpo.getProfession());
	    			}
    			}
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
    			
    			userData.deleteUser("CW-01");
    			System.out.println("删除后:");
    			ArrayList<UserPO> userpoList2 = userData.showAllUsers();
    			if(userpoList2 != null){
	    			for(int i=0;i<userpoList2.size();i++){
	    				UserPO tempUserpo = userpoList2.get(i);
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
    
    /*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/
    
    public static void main(String[] args){
     	try{
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			UserDataService userData = new UserData();
			LocateRegistry.createRegistry(6000);
			
			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.25.132.40:6000/UserDataService", userData);
			System.out.println("User Service start!");
			
			ArrayList<UserPO> userList0 = userData.showAllUsers();
			for(UserPO user:userList0)
				System.out.println("ID: "+user.getUserID()+", Name: "+user.getName());
			
			
			UserPO userpo = userData.findUser("JL-01");
				System.out.println("ID: "+userpo.getUserID()+", Name: "+userpo.getName());
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
}
