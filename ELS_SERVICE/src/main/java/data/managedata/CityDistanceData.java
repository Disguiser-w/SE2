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
import po.CityDistancePO;
import dataservice.managedataservice.CityDistanceDataService;

public class CityDistanceData extends UnicastRemoteObject implements CityDistanceDataService {

	private static final long serialVersionUID = 131250151L;
	
	public CityDistanceData() throws RemoteException {
		super();
	}
	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<CityDistancePO> getCityDistanceList() throws RemoteException{
		String path = "basicDataInfo/cityDistance.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<CityDistancePO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<CityDistancePO> cityDistanceList = (ArrayList<CityDistancePO>) in.readObject();
			in.close();
			return cityDistanceList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 写文件（增删改查统一调用它）
	 * 
	 * */
	public int saveCityDistanceList(ArrayList<CityDistancePO> cityDistanceList) throws RemoteException {
		String path = "basicDataInfo/cityDistance.ser";
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
			out.writeObject(cityDistanceList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * 新增城市间距离信息
	 * @param CityDistancePO cityDistancepo
	 * @return 0(add succeed), 1(add failed)
	 * 
	 * */
	public int addCityDistance(CityDistancePO cityDistancepo) throws RemoteException{
		ArrayList<CityDistancePO> cityDistanceList = getCityDistanceList();
    	
		for(int i=0; i<cityDistanceList.size(); i++){
			CityDistancePO tempCityDistancePO = cityDistanceList.get(i);
			if( (tempCityDistancePO.getCityA().equals(cityDistancepo.getCityA() ) && ( (tempCityDistancePO.getCityB().equals(cityDistancepo.getCityB()) ) )
			|| (tempCityDistancePO.getCityA().equals(cityDistancepo.getCityB() ) && ( (tempCityDistancePO.getCityB().equals(cityDistancepo.getCityA()) ) )))){
				return 1;
			}
		}
		
		cityDistanceList.add(cityDistancepo);
		saveCityDistanceList(cityDistanceList);
		return 0;
	}
	
	
	/**
	 * 删除城市间距离信息
	 * @param String cityA, String cityB
	 * @return 0(delete succeed), 1(delete failed)
	 * 
	 * */
	public int deleteCityDistance(String cityA, String cityB) throws RemoteException {
		ArrayList<CityDistancePO> cityDistanceList = getCityDistanceList();

		boolean hasExist = false;
		
		for(int i=0; i<cityDistanceList.size(); i++){
			CityDistancePO tempCityDistancePO = cityDistanceList.get(i);
			if ( ( tempCityDistancePO.getCityA().equals(cityA) && tempCityDistancePO.getCityB().equals(cityB) )
				|| (tempCityDistancePO.getCityA().equals(cityB) && tempCityDistancePO.getCityB().equals(cityA)) ){
				hasExist = true;
				cityDistanceList.remove(i);
				break;
			}
		}
		
		saveCityDistanceList(cityDistanceList);
		
		if(hasExist)
			return 0;
		else
			return 1;
	}
	
	
	/**
	 * 修改城市间距离信息
	 * @param CityDistancePO cityDistancepo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
    public int modifyCityDistance(CityDistancePO cityDistancepo) throws RemoteException{
    	ArrayList<CityDistancePO> cityDistanceList = getCityDistanceList();
    	
		boolean hasExist = false;
		
		for(int i=0; i<cityDistanceList.size(); i++){
			CityDistancePO tempCityDistancePO = cityDistanceList.get(i);
			if( (tempCityDistancePO.getCityA().equals(cityDistancepo.getCityA() ) && ( (tempCityDistancePO.getCityB().equals(cityDistancepo.getCityB()) ) )
					|| (tempCityDistancePO.getCityA().equals(cityDistancepo.getCityB() ) && ( (tempCityDistancePO.getCityB().equals(cityDistancepo.getCityA()) ) )))){
				tempCityDistancePO.setDistance(cityDistancepo.getDistance());
				hasExist = true;
				break;
			}
		}
		
		saveCityDistanceList(cityDistanceList);
		if(hasExist)
			return 0;
		else
			return 1;
    }
    
    
    /**
	 * 单城市模式下查找城市间距离信息（模糊搜索）
	 * @param String city
	 * @return ArrayList<CityDistancePO>
	 * 
	 * */
	public ArrayList<CityDistancePO> findCityDistanceBySingle(String city) throws RemoteException {
		ArrayList<CityDistancePO> cityDistanceList = getCityDistanceList();
    	ArrayList<CityDistancePO> cityDistancepoList = new ArrayList<CityDistancePO>();
    	
		if(cityDistanceList==null)	
			return null;  	  
		
		for(int i=0; i<cityDistanceList.size(); i++){
			CityDistancePO tempCityDistancePO = cityDistanceList.get(i);
			if( (tempCityDistancePO.getCityA().equals(city) ) 
					|| (tempCityDistancePO.getCityB().equals(city) ) ){
				cityDistancepoList.add(tempCityDistancePO);
			}
		}
		
		return cityDistancepoList;
	}

    
	/**
	 * 双城市模式下查找城市间距离信息（精确搜索）
	 * @param String cityA, String cityB
	 * @return ArrayList<CityDistancePO>
	 * 
	 * */
    public ArrayList<CityDistancePO> findCityDistanceByBoth(String cityA, String cityB) throws RemoteException{
    	ArrayList<CityDistancePO> cityDistanceList = getCityDistanceList();
    	ArrayList<CityDistancePO> cityDistancepoList = new ArrayList<CityDistancePO>();
    	
		if(cityDistanceList==null)	
			return null;  	  
		
		for(int i=0; i<cityDistanceList.size(); i++){
			CityDistancePO tempCityDistancePO = cityDistanceList.get(i);
			if((tempCityDistancePO.getCityA().equals(cityA) && ( (tempCityDistancePO.getCityB().equals(cityB) ) )
					|| (tempCityDistancePO.getCityA().equals(cityB) && ( (tempCityDistancePO.getCityB().equals(cityA) ) )))){
				cityDistancepoList.add(tempCityDistancePO);
			}
		}
		
		return cityDistancepoList;
    }
    
    
    /**
	 * 显示所有城市间距离信息
	 * @return ArrayList<CityDistancePO>
	 * 
	 * */
    public ArrayList<CityDistancePO> showAllCityDistances() throws RemoteException{
    	ArrayList<CityDistancePO> cityDistanceList = getCityDistanceList();
		return cityDistanceList;
	}
    
    
    /**
	 * 显示所有已有城市（供快递员新建订单时使用）
	 * @return ArrayList<CityDistancePO>
	 * 
	 * */
	public ArrayList<String> getAllCitys() throws RemoteException {
		ArrayList<CityDistancePO> cityDistanceList = getCityDistanceList();
		ArrayList<String> str = new ArrayList<String>();

		for (int i = 0; i < cityDistanceList.size(); i++) {
			CityDistancePO tempCityDistancePO = cityDistanceList.get(i);
			String cityA = tempCityDistancePO.getCityA();
			String cityB = tempCityDistancePO.getCityB();

			boolean hasSame = false;
			for (int j = 0; j < str.size(); j++) {
				hasSame = false;
				if (cityA.equals(str.get(j))) {
					hasSame = true;
					break;
				}
			}
			if (!hasSame) {
				str.add(cityA);
			}

			for (int j = 0; j < str.size(); j++) {
				hasSame = false;
				if (cityB.equals(str.get(j))) {
					hasSame = true;
					break;
				}
			}
			if (!hasSame) {
				str.add(cityB);
			}

		}
		return str;
	}

	
	/*--------------------------------------------------Test Part---------------------------------------------------*/

	/*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/

	public static void main(String[] args) {
		CityDistanceData cityDistanceData;
		try {
			cityDistanceData = new CityDistanceData();
			cityDistanceData.addCityDistance(new CityDistancePO("北京", "上海", 1800));
			cityDistanceData.addCityDistance(new CityDistancePO("北京", "广州", 1888.8));
			cityDistanceData.addCityDistance(new CityDistancePO("北京", "南京", 900));
			cityDistanceData.addCityDistance(new CityDistancePO("上海", "广州", 1213));
			cityDistanceData.addCityDistance(new CityDistancePO("上海", "南京", 266));
			cityDistanceData.addCityDistance(new CityDistancePO("广州", "南京", 1132));
			
			ArrayList<CityDistancePO> cityDistancepo = cityDistanceData.findCityDistanceByBoth( "南京", "北京");
			 if(cityDistancepo != null)
				 System.out.println("Find the cityDistance: "+cityDistancepo.get(0).getCityA()+""+cityDistancepo.get(0).getCityB()+" "+cityDistancepo.get(0).getDistance());
			 else
				 System.out.println("Cannot find the cityDistance");
			 
			 System.out.println("添加后:");
			 ArrayList<CityDistancePO> cityDistancepoList0 = cityDistanceData.showAllCityDistances();
			 if(cityDistancepoList0 != null){
				 for(int i=0;i<cityDistancepoList0.size();i++){
					 CityDistancePO tempCityDistancepo = cityDistancepoList0.get(i);
					 System.out.println(tempCityDistancepo.getCityA()+""+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
				 }
			 }
			 else
				 System.out.println("Cannot find the cityDistance");
			 
			cityDistanceData.modifyCityDistance(new CityDistancePO("北京", "上海",1064.7));
			System.out.println("修改后:");
			ArrayList<CityDistancePO> cityDistancepoList1 = cityDistanceData.showAllCityDistances();
			if(cityDistancepoList1 != null){
				for(int i=0;i<cityDistancepoList1.size();i++){
					 CityDistancePO tempCityDistancepo = cityDistancepoList1.get(i);
					 System.out.println(tempCityDistancepo.getCityA()+" "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
				}
			}
			else
				System.out.println("Cannot find the cityDistance");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	 /* CityDistancePO cityDistancepo = cityDistanceData.findCityDistanceByBoth("北京", "南京");
	 if(cityDistancepo != null)
		 System.out.println("Find the cityDistance: "+cityDistancepo.getCityA()+""+cityDistancepo.getCityB()+" "+cityDistancepo.getDistance());
	 else
		 System.out.println("Cannot find the cityDistance");
	
	
	 cityDistanceData.modifyCityDistance(new CityDistancePO("北京", "上海",
	 1800));
	 System.out.println("修改后:");
	 ArrayList<CityDistancePO> cityDistancepoList1 =
	 cityDistanceData.showAllCityDistances();
	 if(cityDistancepoList1 != null){
	 for(int i=0;i<cityDistancepoList1.size();i++){
	 CityDistancePO tempCityDistancepo = cityDistancepoList1.get(i);
	 System.out.println(tempCityDistancepo.getCityA()+"
	 "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
	 }
	 }
	 else
	 System.out.println("Cannot find the cityDistance");
	
	
	 System.out.println("没有删除前:");
	 ArrayList<CityDistancePO> cityDistancepoList2 =
	 cityDistanceData.showAllCityDistances();
	 if(cityDistancepoList2 != null){
	 for(int i=0;i<cityDistancepoList2.size();i++){
	 CityDistancePO tempCityDistancepo = cityDistancepoList2.get(i);
	 System.out.println(tempCityDistancepo.getCityA()+"
	 "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
	 }
	 }
	 else
	 System.out.println("Cannot find the cityDistance");
	
	 //cityDistanceData.deleteCityDistance("上海", "南京");
	 System.out.println("删除后:");
	 ArrayList<CityDistancePO> cityDistancepoList3 =
	 cityDistanceData.showAllCityDistances();
	 if(cityDistancepoList3 != null){
	 for(int i=0;i<cityDistancepoList3.size();i++){
	 CityDistancePO tempCityDistancepo = cityDistancepoList3.get(i);
	 System.out.println(tempCityDistancepo.getCityA()+"
	 "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
	 }
	 }
	 else
	 System.out.println("Cannot find the cityDistance");
	
	 }catch(RemoteException exception){
	 exception.printStackTrace();
	 }
	 }catch(RemoteException exception){
	 exception.printStackTrace();
	 }*/

	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/

	// public static void main(String[] args) {
	// try {
	// System.setProperty("java.rmi.server.hostname", "172.25.132.40");
	// CityDistanceDataService cityDistanceData = new CityDistanceData();
	// LocateRegistry.createRegistry(6005);
	//
	//// 绑定RMI名称进行发布
	// Naming.rebind("rmi://172.25.132.40:6005/CityDistanceDataService",
	// cityDistanceData);
	// System.out.println("CityDistance Service start!");
	//
	// ArrayList<CityDistancePO> cityDistanceList0 =
	// cityDistanceData.showAllCityDistances();
	// for (CityDistancePO cityDistance : cityDistanceList0)
	// System.out.println("CityA: " + cityDistance.getCityA() + ", CityB: " +
	// cityDistance.getCityB()
	// + ", CityDistance: " + cityDistance.getDistance());
	//
	// CityDistancePO cityDistancepo =
	// cityDistanceData.findCityDistanceByBoth("南京", "上海");
	// System.out.println("CityA: " + cityDistancepo.getCityA() + ", CityB: " +
	// cityDistancepo.getCityB()
	// + ", CityDistance: " + cityDistancepo.getDistance());
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
