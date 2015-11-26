package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostPO;
import type.ExpressType;

public class CostData {

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
    	
		if(objectList==null)	//不存在该机构	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CostPO tempCostPO = (CostPO)(objectList.get(i));
			if(tempCostPO.getExpressType().equals(expressType)){
				objectList.remove(i);
				break;
			}
		}
		
		costFile.clear();
		costFile.write(objectList);
		return 0;
    }
    
    public int modifyCost(CostPO costpo) throws RemoteException{
    	ExpressType expressType = costpo.getExpressType();
    	if(deleteCost(expressType)==0){
    		addCost(costpo);
    		return 0;
    	}
    	return 1;
    }
    
    public CostPO findCost(ExpressType expressType) throws RemoteException{
    	ArrayList<Object> objectList = costFile.read();
    	
		if(objectList==null)	//不存在该机构	
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
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		ArrayList<CostPO> costList = new ArrayList<CostPO>();
		
		for(int i=0; i<objectList.size(); i++){
			CostPO tempCostPO = (CostPO)(objectList.get(i));
			costList.add(tempCostPO);
		}
		
		return costList;
    }
    
}
