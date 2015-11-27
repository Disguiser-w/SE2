package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BasicSalaryPO;
import type.ProfessionType;

public class BasicSalaryData {

	JXCFile basicSalaryFile;
    
    public BasicSalaryData() throws RemoteException {
		basicSalaryFile = new JXCFile("src/main/java/basicSalary.ser");
	}
    
    public int addBasicSalary(BasicSalaryPO basicSalarypo) throws RemoteException{
    	if(findBasicSalary(basicSalarypo.getProfession())==null){
    		basicSalaryFile.write(basicSalarypo);
    		return 0;
    	}
    	else 
    		return 1;
    }
    
    public int deleteBasicSalary(ProfessionType profession) throws RemoteException{
    	ArrayList<Object> objectList = basicSalaryFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = (BasicSalaryPO)(objectList.get(i));
			if(tempBasicSalaryPO.getProfession().equals(profession)){
				objectList.remove(i);
				break;
			}
		}
		
		basicSalaryFile.clear();
		basicSalaryFile.write(objectList);
		return 0;
    }
    
    public int modifyBasicSalary(BasicSalaryPO basicSalarypo) throws RemoteException{
    	ProfessionType profession = basicSalarypo.getProfession();
    	if(deleteBasicSalary(profession)==0){
    		addBasicSalary(basicSalarypo);
    		return 0;
    	}
    	return 1;
    }
    
    public BasicSalaryPO findBasicSalary(ProfessionType profession) throws RemoteException{
    	ArrayList<Object> objectList = basicSalaryFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = (BasicSalaryPO)(objectList.get(i));
			if(tempBasicSalaryPO.getProfession().equals(profession)){
				return tempBasicSalaryPO;
			}
		}
		
		return null;
    }
    
    public ArrayList<BasicSalaryPO> showAllBasicSalarys() throws RemoteException{
    	ArrayList<Object> objectList = basicSalaryFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		ArrayList<BasicSalaryPO> basicSalaryList = new ArrayList<BasicSalaryPO>();
		
		for(int i=0; i<objectList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = (BasicSalaryPO)(objectList.get(i));
			basicSalaryList.add(tempBasicSalaryPO);
		}
		
		return basicSalaryList;
    }
    
}
