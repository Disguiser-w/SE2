package data.managedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.FileGetter;

import po.CostPO;
import type.ExpressType;
import dataservice.managedataservice.CostDataService;

public class CostData extends UnicastRemoteObject implements CostDataService{

	private static final long serialVersionUID = 141250153L;


	public CostData() throws RemoteException {
		super();
	}
	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<CostPO> getCostList() throws RemoteException{
		String path = "basicDataInfo/cost.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<CostPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<CostPO> costList = (ArrayList<CostPO>) in.readObject();
			in.close();
			return costList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 写文件（增删改查统一调用它）
	 * 
	 * */
	public int saveCostList(ArrayList<CostPO> costList) throws RemoteException {
		String path = "basicDataInfo/cost.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(costList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public int addCost(CostPO costpo) throws RemoteException{
		ArrayList<CostPO> costList = getCostList();
    	
		for(int i=0; i<costList.size(); i++){
			CostPO tempCostPO = costList.get(i);
			if(tempCostPO.getExpressType().equals(costpo.getExpressType())){
				return 1;
			}
		}
		
		costList.add(costpo);
		saveCostList(costList);
		return 0;
	}
	/**
	 * 修改运费系数信息
	 * @param CostPO costpo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
    public int modifyCost(CostPO costpo) throws RemoteException{
    	ArrayList<CostPO> costList = getCostList();
    	
		boolean hasExist = false;
		
		for(int i=0; i<costList.size(); i++){
			CostPO tempCostPO = costList.get(i);
			if(tempCostPO.getExpressType().equals(costpo.getExpressType())){
				tempCostPO.setCost(costpo.getCost());
				hasExist = true;
				break;
			}
		}
		
		saveCostList(costList);
		if(hasExist)
			return 0;
		else
			return 1;
    }
    
    
    /**
	 * 根据职业类型查找运费系数（精确搜索）
	 * @param ProfessionType profession
	 * @return CostPO
	 * 
	 * */
    public CostPO findCost(ExpressType express) throws RemoteException{
    	ArrayList<CostPO> costList = getCostList();
    	
		if(costList==null)	
			return null;  	  
		
		for(int i=0; i<costList.size(); i++){
			CostPO tempCostPO = costList.get(i);
			if(tempCostPO.getExpressType().equals(express)){
				return tempCostPO;
			}
		}
		
		return null;
    }
    
    
    /**
	 * 显示所有运费系数信息
	 * @return ArrayList<CostPO>
	 * 
	 * */
    public ArrayList<CostPO> showAllCosts() throws RemoteException{
    	ArrayList<CostPO> costList = getCostList();
		return costList;
    }
    
    
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
    
    public static void main(String[] args){
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
				
				costData.modifyCost(new CostPO(ExpressType.FAST, 28));
				System.out.println("修改后:");
				ArrayList<CostPO> costpoList1 = costData.showAllCosts();
				if(costpoList1 != null){
	    			for(int i=0;i<costpoList1.size();i++){
	    				CostPO tempCostpo = costpoList1.get(i);
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
	}
    
    /*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/
    
    /*public static void main(String[] args){
     	try{
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			CostDataService costData = new CostData();
			LocateRegistry.createRegistry(6004);
			
			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.25.132.40:6004/CostDataService", costData);
			System.out.println("Cost Service start!");
			
			ArrayList<CostPO> costList0 = costData.showAllCosts();
			for(CostPO cost:costList0)
				System.out.println("ExpressType: "+cost.getExpressType()+", Cost: "+cost.getCost());
			
			
			CostPO costpo = costData.findCost(ExpressType.FAST);
				System.out.println("ExpressType: "+costpo.getExpressType()+", Cost: "+costpo.getCost());
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
    
    
}
