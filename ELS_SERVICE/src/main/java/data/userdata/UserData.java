package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.UserPO;
import dataservice.userdataservice.UserDataService;

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
		
		userFile.clear();
		userFile.write(objectList);
		return 0;
    }
    
    public int modifyUser(UserPO userpo) throws RemoteException{
    	String userID = userpo.getUserID();
    	if(deleteUser(userID)==0){
    		addUser(userpo);
    		return 0;
    	}
    	return 1;
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
    
}
