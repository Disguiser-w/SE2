package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PerWagePO;
import type.ProfessionType;
import file.JXCFile;
import dataservice.managedataservice.PerWageDataService;

public class PerWageData implements PerWageDataService{

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
    	
		if(objectList==null)
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			PerWagePO tempPerWagePO = (PerWagePO)(objectList.get(i));
			if(tempPerWagePO.getProfession().equals(profession)){
				objectList.remove(i);
				break;
			}
		}
		
		//perWageFile.clear();
		perWageFile.writeM(objectList);
		return 0;
    }
    
    public int modifyPerWage(PerWagePO perWagepo) throws RemoteException{
    	ArrayList<Object> objectList = perWageFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			PerWagePO tempPerWagePO = (PerWagePO)(objectList.get(i));
			if(tempPerWagePO.getProfession().equals(perWagepo.getProfession())){
				objectList.add(perWagepo);
				objectList.remove(i);
				break;
			}
		}
		
		perWageFile.writeM(objectList);
		return 0;
    }
    
    public PerWagePO findPerWage(ProfessionType profession) throws RemoteException{
    	ArrayList<Object> objectList = perWageFile.read();
    	
		if(objectList==null)	
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
    	
		if(objectList==null)
			return null;  	  
		
		ArrayList<PerWagePO> perWageList = new ArrayList<PerWagePO>();
		
		for(int i=0; i<objectList.size(); i++){
			PerWagePO tempPerWagePO = (PerWagePO)(objectList.get(i));
			perWageList.add(tempPerWagePO);
		}
		
		return perWageList;
    }
    
    
    
    /*public static void main(String[] args){
		PerWageData perWageData;
		try{
			perWageData = new PerWageData();
			try{
				perWageData.addPerWage(new PerWagePO(ProfessionType.courier, 0.5));
				perWageData.addPerWage(new PerWagePO(ProfessionType.driver, 50));
				
				PerWagePO perWagepo = perWageData.findPerWage(ProfessionType.driver);
				if(perWagepo != null)
					System.out.println(perWagepo.getProfession()+" "+perWagepo.getPerWage());
				else
					System.out.println("Cannot find the perWage");
				
				System.out.println("添加后:");
				ArrayList<PerWagePO> perWagepoList0 = perWageData.showAllPerWages();
				if(perWagepoList0 != null){
	    			for(int i=0;i<perWagepoList0.size();i++){
	    				PerWagePO tempPerWagepo = perWagepoList0.get(i);
	    				System.out.println(tempPerWagepo.getProfession()+"  "+tempPerWagepo.getPerWage());
	    			}
				}
				else 
					System.out.println("Cannot find the perWage");
					
				System.out.println("修改后:");
				perWageData.modifyPerWage(new PerWagePO(ProfessionType.courier, 1));
				ArrayList<PerWagePO> perWagepoList3 = perWageData.showAllPerWages();
				if(perWagepoList3 != null){
	    			for(int i=0;i<perWagepoList3.size();i++){
	    				PerWagePO tempPerWagepo = perWagepoList3.get(i);
	    				System.out.println(tempPerWagepo.getProfession()+"  "+tempPerWagepo.getPerWage());
	    			}
				}
				else 
					System.out.println("Cannot find the perWage");
				
				System.out.println("没有删除前:");
				ArrayList<PerWagePO> perWagepoList1 = perWageData.showAllPerWages();
				if(perWagepoList1 != null){
	    			for(int i=0;i<perWagepoList1.size();i++){
	    				PerWagePO tempPerWagepo = perWagepoList1.get(i);
	    				System.out.println(tempPerWagepo.getProfession()+"  "+tempPerWagepo.getPerWage());
	    			}
				}
				else 
					System.out.println("Cannot find the perWage");
				
				System.out.println("删除后:");
				perWageData.deletePerWage(ProfessionType.courier);
				ArrayList<PerWagePO> perWagepoList2 = perWageData.showAllPerWages();
				if(perWagepoList2 != null){
	    			for(int i=0;i<perWagepoList2.size();i++){
	    				PerWagePO tempPerWagepo = perWagepoList2.get(i);
	    				System.out.println(tempPerWagepo.getProfession()+"  "+tempPerWagepo.getPerWage());
	    			}
				}
				else 
					System.out.println("Cannot find the perWage");
				
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}*/
    
    
}

