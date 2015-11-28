package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import file.JXCFile;
import po.CostPO;
import type.ExpressType;
import dataservice.managedataservice.CostDataService;

public class CostData implements CostDataService{

	JXCFile costFile;
    
    public CostData() throws RemoteException {
		costFile = new JXCFile("src/main/java/cost.ser");
	}
    
    public int addCost(CostPO costpo) throws RemoteException{
    	if(findCost(costpo.getExpressType())==null){
    		costFile.write(costpo);
    		return 0;
    	}
    	else 
    		return 1;
    }
    
    public int deleteCost(ExpressType expressType) throws RemoteException{
    	ArrayList<Object> objectList = costFile.read();
    	
		if(objectList==null)
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CostPO tempCostPO = (CostPO)(objectList.get(i));
			if(tempCostPO.getExpressType().equals(expressType)){
				objectList.remove(i);
				break;
			}
		}
		
		//costFile.clear();
		costFile.writeM(objectList);
		return 0;
    }
    
    public int modifyCost(CostPO costpo) throws RemoteException{
    	ArrayList<Object> objectList = costFile.read();
    	
		if(objectList==null)
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CostPO tempCostPO = (CostPO)(objectList.get(i));
			if(tempCostPO.getExpressType().equals(costpo.getExpressType())){
				objectList.add(costpo);
				objectList.remove(i);
				break;
			}
		}
		
		costFile.writeM(objectList);
		return 0;
    }
    
    public CostPO findCost(ExpressType expressType) throws RemoteException{
    	ArrayList<Object> objectList = costFile.read();
    	
		if(objectList==null)
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CostPO tempCostPO = (CostPO)(objectList.get(i));
			if(tempCostPO.getExpressType().equals(expressType)){
				return tempCostPO;
			}
		}
		
		return null;
    }
    
    public ArrayList<CostPO> showAllCosts() throws RemoteException{
    	ArrayList<Object> objectList = costFile.read();
    	
		if(objectList==null)
			return null;  	  
		
		ArrayList<CostPO> costList = new ArrayList<CostPO>();
		
		for(int i=0; i<objectList.size(); i++){
			CostPO tempCostPO = (CostPO)(objectList.get(i));
			costList.add(tempCostPO);
		}
		
		return costList;
    }
    
    
    
    /*public static void main(String[] args){
		CostData costData;
		try{
			costData = new CostData();
			try{
				costData.addCost(new CostPO(ExpressType.ECONOMIC, 18));
				costData.addCost(new CostPO(ExpressType.STANDARD, 23));
				costData.addCost(new CostPO(ExpressType.FAST, 26));
				
				System.out.println("添加后:");
				ArrayList<CostPO> costpoList0 = costData.showAllCosts();
				if(costpoList0 != null){
	    			for(int i=0;i<costpoList0.size();i++){
	    				CostPO tempCostpo = costpoList0.get(i);
	    				System.out.println(tempCostpo.getExpressType()+"  "+tempCostpo.getCost());
	    			}
				}
				else 
					System.out.println("Cannot find the cost");
				
				CostPO costpo = costData.findCost(ExpressType.FAST);
				if(costpo != null)
					System.out.println("Find the cost: "+costpo.getExpressType()+" "+costpo.getCost());
				else
					System.out.println("Cannot find the cost");
				
				
				System.out.println("修改后:");
				costData.modifyCost(new CostPO(ExpressType.FAST, 28));
				ArrayList<CostPO> costpoList1 = costData.showAllCosts();
				if(costpoList1 != null){
	    			for(int i=0;i<costpoList1.size();i++){
	    				CostPO tempCostpo = costpoList1.get(i);
	    				System.out.println(tempCostpo.getExpressType()+"  "+tempCostpo.getCost());
	    			}
				}
				else 
					System.out.println("Cannot find the cost");
				
				
				System.out.println("没有删除前:");
				ArrayList<CostPO> costpoList2 = costData.showAllCosts();
				if(costpoList2 != null){
	    			for(int i=0;i<costpoList2.size();i++){
	    				CostPO tempCostpo = costpoList2.get(i);
	    				System.out.println(tempCostpo.getExpressType()+"  "+tempCostpo.getCost());
	    			}
				}
				else 
					System.out.println("Cannot find the cost");
				
				System.out.println("删除后:");
				costData.deleteCost(ExpressType.STANDARD);
				ArrayList<CostPO> costpoList3 = costData.showAllCosts();
				if(costpoList3 != null){
	    			for(int i=0;i<costpoList3.size();i++){
	    				CostPO tempCostpo = costpoList3.get(i);
	    				System.out.println(tempCostpo.getExpressType()+"  "+tempCostpo.getCost());
	    			}
				}
				else 
					System.out.println("Cannot find the cost");
				
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}*/
    
    
}
