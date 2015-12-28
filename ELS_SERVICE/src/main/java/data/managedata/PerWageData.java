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

import po.PerWagePO;
import type.ProfessionType;
import dataservice.managedataservice.PerWageDataService;

public class PerWageData extends UnicastRemoteObject implements PerWageDataService{

	private static final long serialVersionUID = 131250150L;
	
	public PerWageData() throws RemoteException {
		super();
	}
	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<PerWagePO> getPerWageList() throws RemoteException{
		String path = "basicDataInfo/perWage.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<PerWagePO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<PerWagePO> perWageList = (ArrayList<PerWagePO>) in.readObject();
			in.close();
			return perWageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 写文件（增删改查统一调用它）
	 * 
	 * */
	public int savePerWageList(ArrayList<PerWagePO> perWageList) throws RemoteException {
		String path = "basicDataInfo/perWage.ser";
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
			out.writeObject(perWageList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public int addPerWage(PerWagePO perWagepo) throws RemoteException{
		ArrayList<PerWagePO> perWageList = getPerWageList();
    	
		for(int i=0; i<perWageList.size(); i++){
			PerWagePO tempPerWagePO = perWageList.get(i);
			if(tempPerWagePO.getProfession().equals(perWagepo.getProfession())){
				return 1;
			}
		}
		
		perWageList.add(perWagepo);
		savePerWageList(perWageList);
		return 0;
	}
	/**
	 * 修改每次工资信息
	 * @param PerWagePO perWagepo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
    public int modifyPerWage(PerWagePO perWagepo) throws RemoteException{
    	ArrayList<PerWagePO> perWageList = getPerWageList();
    	
		boolean hasExist = false;
		
		for(int i=0; i<perWageList.size(); i++){
			PerWagePO tempPerWagePO = perWageList.get(i);
			if(tempPerWagePO.getProfession().equals(perWagepo.getProfession())){
				tempPerWagePO.setPerWage(perWagepo.getPerWage());
				hasExist = true;
				break;
			}
		}
		
		savePerWageList(perWageList);
		if(hasExist)
			return 0;
		else
			return 1;
    }
    
    
    /**
	 * 根据职业类型查找每次工资（精确搜索）
	 * @param ProfessionType profession
	 * @return PerWagePO
	 * 
	 * */
    public PerWagePO findPerWage(ProfessionType profession) throws RemoteException{
    	ArrayList<PerWagePO> perWageList = getPerWageList();
    	
		if(perWageList==null)	
			return null;  	  
		
		for(int i=0; i<perWageList.size(); i++){
			PerWagePO tempPerWagePO = perWageList.get(i);
			if(tempPerWagePO.getProfession().equals(profession)){
				return tempPerWagePO;
			}
		}
		
		return null;
    }
    
    
    /**
	 * 显示所有每次工资信息
	 * @return ArrayList<PerWagePO>
	 * 
	 * */
    public ArrayList<PerWagePO> showAllPerWages() throws RemoteException{
    	ArrayList<PerWagePO> perWageList = getPerWageList();
		return perWageList;
    }
    
    
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
    
    public static void main(String[] args){
		PerWageData perWageData;
		try{
			perWageData = new PerWageData();
			try{
				perWageData.addPerWage(new PerWagePO(ProfessionType.courier, 0.5));
				perWageData.addPerWage(new PerWagePO(ProfessionType.driver, 50));
				
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
					
				PerWagePO perWagepo = perWageData.findPerWage(ProfessionType.driver);
				if(perWagepo != null)
					System.out.println(perWagepo.getProfession()+" "+perWagepo.getPerWage());
				else
					System.out.println("Cannot find the perWage");
						
				perWageData.modifyPerWage(new PerWagePO(ProfessionType.courier, 1));
				System.out.println("修改后:");
				ArrayList<PerWagePO> perWagepoList3 = perWageData.showAllPerWages();
				if(perWagepoList3 != null){
	    			for(int i=0;i<perWagepoList3.size();i++){
	    				PerWagePO tempPerWagepo = perWagepoList3.get(i);
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
	}
    
    /*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/
    
    /*public static void main(String[] args){
     	try{
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			PerWageDataService perWageData = new PerWageData();
			LocateRegistry.createRegistry(6002);
			
			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.25.132.40:6002/PerWageDataService", perWageData);
			System.out.println("PerWage Service start!");
			
			ArrayList<PerWagePO> perWageList0 = perWageData.showAllPerWages();
			for(PerWagePO perWage:perWageList0)
				System.out.println("Profession: "+perWage.getProfession()+", PerWage: "+perWage.getPerWage());
			
			
			PerWagePO perWagepo = perWageData.findPerWage(ProfessionType.driver);
				System.out.println("Profession: "+perWagepo.getProfession()+", PerWage: "+perWagepo.getPerWage());
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
    
    
}

