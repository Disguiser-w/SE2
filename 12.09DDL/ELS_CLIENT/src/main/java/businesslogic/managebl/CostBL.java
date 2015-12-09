package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostPO;
import businesslogicservice.manageblservice.CostBLService;
import dataservice.managedataservice.CostDataService;
import type.ExpressType;
import vo.CostVO;

public class CostBL implements CostBLService{
	
	CostDataService codService;
	
	public CostBL(){
		try{
			codService = (CostDataService)Naming.lookup("rmi://localhost:8888/CostDataService");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public int addCost(CostVO costvo){
		try{
			CostPO costpo = costVOToPO(costvo);
			return(codService.addCost(costpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteCost(CostVO costvo){
		try{
			return(codService.deleteCost(costvo.getCategory()));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyCost(CostVO costvo){
		try{
			CostPO costpo = costVOToPO(costvo);
			return(codService.modifyCost(costpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public double findCost(ExpressType category){
		try{
			return codService.findCost(category).getCost();
		}catch(RemoteException exception){
			exception.printStackTrace();
			return -1.0;
		}
	}
	
	public ArrayList<CostVO> showAllCosts(){
		try{
			ArrayList<CostPO> costPOList =  codService.showAllCosts();
			ArrayList<CostVO> costVOList =  new ArrayList<CostVO>();
			for(CostPO cost: costPOList)
				costVOList.add(costPOToVO(cost));
			return costVOList;
		}
		catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public static CostPO costVOToPO(CostVO costvo){
		return new CostPO(costvo.getCategory(), costvo.getCost());
	}
	
	public static CostVO costPOToVO(CostPO costpo){
		return new CostVO(costpo.getExpressType(), costpo.getCost());
	}
	
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	public static void main(String[] args){
		try {
			CostDataService costData = (CostDataService)Naming.lookup("rmi://172.25.132.40:6004/CostDataService");
			
			ArrayList<CostPO> costList0 = costData.showAllCosts();
			for(CostPO cost:costList0)
				System.out.println("ExpressType: "+cost.getExpressType()+", Cost: "+cost.getCost());

			costData.addCost(new CostPO(ExpressType.STANDARD,24));
			
			ArrayList<CostPO> costList1 = costData.showAllCosts();
			for(CostPO cost:costList1)
				System.out.println("ExpressType: "+cost.getExpressType()+", Cost: "+cost.getCost());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
