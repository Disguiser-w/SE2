package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BasicSalaryPO;
import type.ProfessionType;
import file.JXCFile;
import dataservice.managedataservice.BasicSalaryDataService;

public class BasicSalaryData implements BasicSalaryDataService{

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
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = (BasicSalaryPO)(objectList.get(i));
			if(tempBasicSalaryPO.getProfession().equals(profession)){
				objectList.remove(i);
				break;
			}
		}
		
		//basicSalaryFile.clear();
		basicSalaryFile.writeM(objectList);
		return 0;
    }
    
    public int modifyBasicSalary(BasicSalaryPO basicSalarypo) throws RemoteException{
    	ArrayList<Object> objectList = basicSalaryFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = (BasicSalaryPO)(objectList.get(i));
			if(tempBasicSalaryPO.getProfession().equals(basicSalarypo.getProfession())){
				objectList.add(basicSalarypo);
				objectList.remove(i);
				break;
			}
		}
		
		basicSalaryFile.writeM(objectList);
		return 0;
    }
    
    public BasicSalaryPO findBasicSalary(ProfessionType profession) throws RemoteException{
    	ArrayList<Object> objectList = basicSalaryFile.read();
    	
		if(objectList==null)	
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
    	
		if(objectList==null)
			return null;  	  
		
		ArrayList<BasicSalaryPO> basicSalaryList = new ArrayList<BasicSalaryPO>();
		
		for(int i=0; i<objectList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = (BasicSalaryPO)(objectList.get(i));
			basicSalaryList.add(tempBasicSalaryPO);
		}
		
		return basicSalaryList;
    }
    
    
    
     /*public static void main(String[] args){
		BasicSalaryData basicSalaryData;
		try{
			basicSalaryData = new BasicSalaryData();
			try{
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.manager, 5000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.financialStaff,  3000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.administrator,  3000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.counterman, 3000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.courier, 2000));
				
				System.out.println("添加后:");
				ArrayList<BasicSalaryPO> basicSalarypoList0 = basicSalaryData.showAllBasicSalarys();
				if(basicSalarypoList0 != null){
	    			for(int i=0;i<basicSalarypoList0.size();i++){
	    				BasicSalaryPO tempBasicSalarypo = basicSalarypoList0.get(i);
	    				System.out.println(tempBasicSalarypo.getProfession()+"  "+tempBasicSalarypo.getBasicSalary());
	    			}
				}
				
				BasicSalaryPO basicSalarypo = basicSalaryData.findBasicSalary(ProfessionType.counterman);
				if(basicSalarypo != null)
					System.out.println("Find the basicSalary: "+basicSalarypo.getProfession()+" "+basicSalarypo.getBasicSalary());
				else
					System.out.println("Cannot find the basicSalary");
				
				System.out.println("修改后:");
				basicSalaryData.modifyBasicSalary(new BasicSalaryPO(ProfessionType.courier, 2500));
				ArrayList<BasicSalaryPO> basicSalarypoList3 = basicSalaryData.showAllBasicSalarys();
				if(basicSalarypoList3 != null){
	    			for(int i=0;i<basicSalarypoList3.size();i++){
	    				BasicSalaryPO tempBasicSalarypo = basicSalarypoList3.get(i);
	    				System.out.println(tempBasicSalarypo.getProfession()+"  "+tempBasicSalarypo.getBasicSalary());
	    			}
				}
				else 
					System.out.println("Cannot find the basicSalary");
				
				System.out.println("没有删除前:");
				ArrayList<BasicSalaryPO> basicSalarypoList1 = basicSalaryData.showAllBasicSalarys();
				if(basicSalarypoList1 != null){
	    			for(int i=0;i<basicSalarypoList1.size();i++){
	    				BasicSalaryPO tempBasicSalarypo = basicSalarypoList1.get(i);
	    				System.out.println(tempBasicSalarypo.getProfession()+"  "+tempBasicSalarypo.getBasicSalary());
	    			}
				}
				else 
					System.out.println("Cannot find the basicSalary");
				
				System.out.println("删除后:");
				basicSalaryData.deleteBasicSalary(ProfessionType.administrator);
				ArrayList<BasicSalaryPO> basicSalarypoList2 = basicSalaryData.showAllBasicSalarys();
				if(basicSalarypoList2 != null){
	    			for(int i=0;i<basicSalarypoList2.size();i++){
	    				BasicSalaryPO tempBasicSalarypo = basicSalarypoList2.get(i);
	    				System.out.println(tempBasicSalarypo.getProfession()+"  "+tempBasicSalarypo.getBasicSalary());
	    			}
				}
				else 
					System.out.println("Cannot find the basicSalary");
				
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}*/
    
    
}
