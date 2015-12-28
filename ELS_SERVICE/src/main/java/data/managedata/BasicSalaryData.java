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

import po.BasicSalaryPO;
import type.ProfessionType;
import dataservice.managedataservice.BasicSalaryDataService;

public class BasicSalaryData extends UnicastRemoteObject implements BasicSalaryDataService{

	private static final long serialVersionUID = 131250151L;
		
	public BasicSalaryData() throws RemoteException {
		super();
	}
	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<BasicSalaryPO> getBasicSalaryList() throws RemoteException{
		String path = "basicDataInfo/basicSalary.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<BasicSalaryPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<BasicSalaryPO> basicSalaryList = (ArrayList<BasicSalaryPO>) in.readObject();
			in.close();
			return basicSalaryList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 写文件（增删改查统一调用它）
	 * 
	 * */
	public int saveBasicSalaryList(ArrayList<BasicSalaryPO> basicSalaryList) throws RemoteException {
		String path = "basicDataInfo/basicSalary.ser";
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
			out.writeObject(basicSalaryList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public int addBasicSalary(BasicSalaryPO basicSalarypo) throws RemoteException{
		ArrayList<BasicSalaryPO> basicSalaryList = getBasicSalaryList();
    	
		for(int i=0; i<basicSalaryList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = basicSalaryList.get(i);
			if(tempBasicSalaryPO.getProfession().equals(basicSalarypo.getProfession())){
				return 1;
			}
		}
		
		basicSalaryList.add(basicSalarypo);
		saveBasicSalaryList(basicSalaryList);
		return 0;
	}
	/**
	 * 修改基础月薪信息
	 * @param BasicSalaryPO basicSalarypo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
    public int modifyBasicSalary(BasicSalaryPO basicSalarypo) throws RemoteException{
    	ArrayList<BasicSalaryPO> basicSalaryList = getBasicSalaryList();
    	
		boolean hasExist = false;
		
		for(int i=0; i<basicSalaryList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = basicSalaryList.get(i);
			if(tempBasicSalaryPO.getProfession().equals(basicSalarypo.getProfession())){
				tempBasicSalaryPO.setBasicSalary(basicSalarypo.getBasicSalary());
				hasExist = true;
				break;
			}
		}
		
		saveBasicSalaryList(basicSalaryList);
		if(hasExist)
			return 0;
		else
			return 1;
    }
    
    
    /**
	 * 根据职业类型查找基础月薪（精确搜索）
	 * @param ProfessionType profession
	 * @return BasicSalaryPO
	 * 
	 * */
    public BasicSalaryPO findBasicSalary(ProfessionType profession) throws RemoteException{
    	ArrayList<BasicSalaryPO> basicSalaryList = getBasicSalaryList();
    	
		if(basicSalaryList==null)	
			return null;  	  
		
		for(int i=0; i<basicSalaryList.size(); i++){
			BasicSalaryPO tempBasicSalaryPO = basicSalaryList.get(i);
			if(tempBasicSalaryPO.getProfession().equals(profession)){
				return tempBasicSalaryPO;
			}
		}
		
		return null;
    }
    
    
    /**
	 * 显示所有基础月薪信息
	 * @return ArrayList<BasicSalaryPO>
	 * 
	 * */
    public ArrayList<BasicSalaryPO> showAllBasicSalarys() throws RemoteException{
    	ArrayList<BasicSalaryPO> basicSalaryList = getBasicSalaryList();
		return basicSalaryList;
    }
    
    
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
    
     public static void main(String[] args){
		BasicSalaryData basicSalaryData;
		try{
			basicSalaryData = new BasicSalaryData();
			try{
				/*basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.manager, 5000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.financialStaff,  3000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.administrator,  3000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.intermediateCenterCounterman, 3000));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.businessHallCounterman, 2500));
				basicSalaryData.addBasicSalary(new BasicSalaryPO(ProfessionType.courier, 2000));*/
				
				System.out.println("添加后:");
				ArrayList<BasicSalaryPO> basicSalarypoList0 = basicSalaryData.showAllBasicSalarys();
				if(basicSalarypoList0 != null){
	    			for(int i=0;i<basicSalarypoList0.size();i++){
	    				BasicSalaryPO tempBasicSalarypo = basicSalarypoList0.get(i);
	    				System.out.println(tempBasicSalarypo.getProfession()+"  "+tempBasicSalarypo.getBasicSalary());
	    			}
				}
				
				BasicSalaryPO basicSalarypo = basicSalaryData.findBasicSalary(ProfessionType.intermediateCenterCounterman);
				if(basicSalarypo != null)
					System.out.println("Find the basicSalary: "+basicSalarypo.getProfession()+" "+basicSalarypo.getBasicSalary());
				else
					System.out.println("Cannot find the basicSalary");
				
				basicSalaryData.modifyBasicSalary(new BasicSalaryPO(ProfessionType.courier, 3000));
				System.out.println("修改后:");
				ArrayList<BasicSalaryPO> basicSalarypoList3 = basicSalaryData.showAllBasicSalarys();
				if(basicSalarypoList3 != null){
	    			for(int i=0;i<basicSalarypoList3.size();i++){
	    				BasicSalaryPO tempBasicSalarypo = basicSalarypoList3.get(i);
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
	}
    
    /*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/
    
    /*public static void main(String[] args){
     	try{
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			BasicSalaryDataService basicSalaryData = new BasicSalaryData();
			LocateRegistry.createRegistry(6003);
			
			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.25.132.40:6003/BasicSalaryDataService", basicSalaryData);
			System.out.println("BasicSalary Service start!");
			
			ArrayList<BasicSalaryPO> basicSalaryList0 = basicSalaryData.showAllBasicSalarys();
			for(BasicSalaryPO basicSalary:basicSalaryList0)
				System.out.println("Profession: "+basicSalary.getProfession()+", BasicSalary: "+basicSalary.getBasicSalary());
			
			
			BasicSalaryPO basicSalarypo = basicSalaryData.findBasicSalary(ProfessionType.manager);
				System.out.println("Profession: "+basicSalarypo.getProfession()+", BasicSalary: "+basicSalarypo.getBasicSalary());
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
    
    
}
