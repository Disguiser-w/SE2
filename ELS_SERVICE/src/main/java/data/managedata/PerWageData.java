package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PerWagePO;
import type.ProfessionType;

public class PerWageData {

	JXCFile perWageFile;
    
    public PerWageData() throws RemoteException {
		perWageFile = new JXCFile("src/main/java/perWage.ser");
	}
    
    public int addPerWage(PerWagePO perWagepo) throws RemoteException{
    	if(findPerWage(perWagepo.getProfession())==null){
    		perWageFile.write(perWagepo);
    		return 0;
    	}
    	else 
    		return 1;
    }
    
    public int deletePerWage(ProfessionType profession) throws RemoteException{
    	ArrayList<Object> objectList = perWageFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			PerWagePO tempPerWagePO = (PerWagePO)(objectList.get(i));
			if(tempPerWagePO.getProfession().equals(profession)){
				objectList.remove(i);
				break;
			}
		}
		
		perWageFile.clear();
		perWageFile.write(objectList);
		return 0;
    }
    
    public int modifyPerWage(PerWagePO perWagepo) throws RemoteException{
    	ProfessionType profession = perWagepo.getProfession();
    	if(deletePerWage(profession)==0){
    		addPerWage(perWagepo);
    		return 0;
    	}
    	return 1;
    }
    
    public PerWagePO findPerWage(ProfessionType profession) throws RemoteException{
    	ArrayList<Object> objectList = perWageFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			PerWagePO tempPerWagePO = (PerWagePO)(objectList.get(i));
			if(tempPerWagePO.getProfession().equals(profession)){
				return tempPerWagePO;
			}
		}
		
		return null;
    }
    
    public ArrayList<PerWagePO> showAllPerWages() throws RemoteException{
    	ArrayList<Object> objectList = perWageFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		ArrayList<PerWagePO> perWageList = new ArrayList<PerWagePO>();
		
		for(int i=0; i<objectList.size(); i++){
			PerWagePO tempPerWagePO = (PerWagePO)(objectList.get(i));
			perWageList.add(tempPerWagePO);
		}
		
		return perWageList;
    }
    
}

