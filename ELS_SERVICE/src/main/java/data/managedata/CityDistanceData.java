package data.managedata;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CityDistancePO;
import file.JXCFile;
import dataservice.managedataservice.CityDistanceDataService;

public class CityDistanceData extends UnicastRemoteObject implements CityDistanceDataService {

	// 我也不知道下面这句话有什么用，只是因为继承了UnicastRemoteObject所以要声明这样一个字段
	private static final long serialVersionUID = 131250152L;

	JXCFile cityDistanceFile;

	public CityDistanceData() throws RemoteException {
		cityDistanceFile = new JXCFile("src/cityDistance.ser");
	}

	public int addCityDistance(CityDistancePO cityDistancepo) throws RemoteException {
		if (findCityDistance(cityDistancepo.getCityA(), cityDistancepo.getCityB()) == null) {
			cityDistanceFile.write(cityDistancepo);
			return 0;
		} else
			return 1;
	}

	public int deleteCityDistance(String cityA, String cityB) throws RemoteException {
		ArrayList<Object> objectList = cityDistanceFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			CityDistancePO tempCityDistancePO = (CityDistancePO) (objectList.get(i));
			if (((tempCityDistancePO.getCityA().equals(cityA)) && (tempCityDistancePO.getCityB().equals(cityB)))
					|| (((tempCityDistancePO.getCityA().equals(cityB))
							&& (tempCityDistancePO.getCityB().equals(cityA))))) {
				objectList.remove(i);
				break;
			}
		}

		// cityDistanceFile.clear();
		cityDistanceFile.writeM(objectList);
		return 0;
	}

	public int modifyCityDistance(CityDistancePO cityDistancepo) throws RemoteException {
		ArrayList<Object> objectList = cityDistanceFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			CityDistancePO tempCityDistancePO = (CityDistancePO) (objectList.get(i));
			if (((tempCityDistancePO.getCityA().equals(cityDistancepo.getCityA()))
					&& (tempCityDistancePO.getCityB().equals(cityDistancepo.getCityB())))
					|| (((tempCityDistancePO.getCityA().equals(cityDistancepo.getCityB()))
							&& (tempCityDistancePO.getCityB().equals(cityDistancepo.getCityA()))))) {
				objectList.add(cityDistancepo);
				objectList.remove(i);
				break;
			}
		}

		cityDistanceFile.writeM(objectList);
		return 0;
	}

	public CityDistancePO findCityDistance(String cityA, String cityB) throws RemoteException {
		ArrayList<Object> objectList = cityDistanceFile.read();

		if (objectList == null)
			return null;

		for (int i = 0; i < objectList.size(); i++) {
			CityDistancePO tempCityDistancePO = (CityDistancePO) (objectList.get(i));
			if (((tempCityDistancePO.getCityA().equals(cityA)) && (tempCityDistancePO.getCityB().equals(cityB)))
					|| (((tempCityDistancePO.getCityA().equals(cityB))
							&& (tempCityDistancePO.getCityB().equals(cityA))))) {
				return tempCityDistancePO;
			}
		}

		return null;
	}

	public ArrayList<CityDistancePO> showAllCityDistances() throws RemoteException {
		ArrayList<Object> objectList = cityDistanceFile.read();

		if (objectList == null)
			return null;

		ArrayList<CityDistancePO> cityDistanceList = new ArrayList<CityDistancePO>();

		for (int i = 0; i < objectList.size(); i++) {
			CityDistancePO tempCityDistancePO = (CityDistancePO) (objectList.get(i));
			cityDistanceList.add(tempCityDistancePO);
		}

		return cityDistanceList;
<<<<<<< HEAD
    }
    
    public String[] getAllCitys(){
	String[] str;
	
	return null;
    }
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
    
    /*public static void main(String[] args){
		CityDistanceData cityDistanceData;
		try{
			cityDistanceData = new CityDistanceData();
			try{
				cityDistanceData.addCityDistance(new CityDistancePO("上海", "南京", 600));
				cityDistanceData.addCityDistance(new CityDistancePO("上海", "北京", 1000));
				cityDistanceData.addCityDistance(new CityDistancePO("南京", "北京", 1400));
				
				System.out.println("添加后:");
				ArrayList<CityDistancePO> cityDistancepoList0 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList0 != null){
					for(int i=0;i<cityDistancepoList0.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList0.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+"  "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				CityDistancePO cityDistancepo = cityDistanceData.findCityDistance("北京", "南京");
				if(cityDistancepo != null)
					System.out.println("Find the cityDistance: "+cityDistancepo.getCityA()+" "+cityDistancepo.getCityB()+" "+cityDistancepo.getDistance());
				else
					System.out.println("Cannot find the cityDistance");
				
				
				cityDistanceData.modifyCityDistance(new CityDistancePO("北京", "上海", 1800));
				System.out.println("修改后:");
				ArrayList<CityDistancePO> cityDistancepoList1 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList1 != null){
					for(int i=0;i<cityDistancepoList1.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList1.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				
				System.out.println("没有删除前:");
				ArrayList<CityDistancePO> cityDistancepoList2 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList2 != null){
					for(int i=0;i<cityDistancepoList2.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList2.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				cityDistanceData.deleteCityDistance("上海", "南京");
				System.out.println("删除后:");
				ArrayList<CityDistancePO> cityDistancepoList3 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList3 != null){
					for(int i=0;i<cityDistancepoList3.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList3.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
					
				}catch(RemoteException exception){
					exception.printStackTrace();
				}
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}*/
    
    /*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/
    
    public static void main(String[] args){
     	try{
=======
	}

	/*--------------------------------------------------Test Part---------------------------------------------------*/

	/*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/

	/*
	 * public static void main(String[] args){ CityDistanceData
	 * cityDistanceData; try{ cityDistanceData = new CityDistanceData(); try{
	 * cityDistanceData.addCityDistance(new CityDistancePO("上海", "南京", 600));
	 * cityDistanceData.addCityDistance(new CityDistancePO("上海", "北京", 1000));
	 * cityDistanceData.addCityDistance(new CityDistancePO("南京", "北京", 1400));
	 * 
	 * System.out.println("添加后:"); ArrayList<CityDistancePO> cityDistancepoList0
	 * = cityDistanceData.showAllCityDistances(); if(cityDistancepoList0 !=
	 * null){ for(int i=0;i<cityDistancepoList0.size();i++){ CityDistancePO
	 * tempCityDistancepo = cityDistancepoList0.get(i);
	 * System.out.println(tempCityDistancepo.getCityA()+"  "
	 * +tempCityDistancepo.getCityB()+"  "+tempCityDistancepo.getDistance()); }
	 * } else System.out.println("Cannot find the cityDistance");
	 * 
	 * CityDistancePO cityDistancepo = cityDistanceData.findCityDistance("北京",
	 * "南京"); if(cityDistancepo != null) System.out.println(
	 * "Find the cityDistance: "+cityDistancepo.getCityA()+" "
	 * +cityDistancepo.getCityB()+" "+cityDistancepo.getDistance()); else
	 * System.out.println("Cannot find the cityDistance");
	 * 
	 * 
	 * cityDistanceData.modifyCityDistance(new CityDistancePO("北京", "上海",
	 * 1800)); System.out.println("修改后:"); ArrayList<CityDistancePO>
	 * cityDistancepoList1 = cityDistanceData.showAllCityDistances();
	 * if(cityDistancepoList1 != null){ for(int
	 * i=0;i<cityDistancepoList1.size();i++){ CityDistancePO tempCityDistancepo
	 * = cityDistancepoList1.get(i);
	 * System.out.println(tempCityDistancepo.getCityA()+"  "
	 * +tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance()); } }
	 * else System.out.println("Cannot find the cityDistance");
	 * 
	 * 
	 * System.out.println("没有删除前:"); ArrayList<CityDistancePO>
	 * cityDistancepoList2 = cityDistanceData.showAllCityDistances();
	 * if(cityDistancepoList2 != null){ for(int
	 * i=0;i<cityDistancepoList2.size();i++){ CityDistancePO tempCityDistancepo
	 * = cityDistancepoList2.get(i);
	 * System.out.println(tempCityDistancepo.getCityA()+"  "
	 * +tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance()); } }
	 * else System.out.println("Cannot find the cityDistance");
	 * 
	 * cityDistanceData.deleteCityDistance("上海", "南京");
	 * System.out.println("删除后:"); ArrayList<CityDistancePO> cityDistancepoList3
	 * = cityDistanceData.showAllCityDistances(); if(cityDistancepoList3 !=
	 * null){ for(int i=0;i<cityDistancepoList3.size();i++){ CityDistancePO
	 * tempCityDistancepo = cityDistancepoList3.get(i);
	 * System.out.println(tempCityDistancepo.getCityA()+"  "
	 * +tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance()); } }
	 * else System.out.println("Cannot find the cityDistance");
	 * 
	 * }catch(RemoteException exception){ exception.printStackTrace(); }
	 * }catch(RemoteException exception){ exception.printStackTrace(); } }
	 */

	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/

	public static void main(String[] args) {
		try {
>>>>>>> origin/master
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			CityDistanceDataService cityDistanceData = new CityDistanceData();
			LocateRegistry.createRegistry(6005);

			// 绑定RMI名称进行发布
			Naming.rebind("rmi://172.25.132.40:6005/CityDistanceDataService", cityDistanceData);
			System.out.println("CityDistance Service start!");

			ArrayList<CityDistancePO> cityDistanceList0 = cityDistanceData.showAllCityDistances();
			for (CityDistancePO cityDistance : cityDistanceList0)
				System.out.println("CityA: " + cityDistance.getCityA() + ", CityB: " + cityDistance.getCityB()
						+ ", CityDistance: " + cityDistance.getDistance());

			CityDistancePO cityDistancepo = cityDistanceData.findCityDistance("北京", "上海");
			System.out.println("CityA: " + cityDistancepo.getCityA() + ", CityB: " + cityDistancepo.getCityB()
					+ ", CityDistance: " + cityDistancepo.getDistance());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * doge的方法
	 */
	public ArrayList<String> getAllCitys() throws RemoteException {
		//
		ArrayList<String> citys = new ArrayList<String>();

		citys.add("北京");
		citys.add("上海");
		citys.add("南京");
		citys.add("广州");
		
		return citys;
	}

}
