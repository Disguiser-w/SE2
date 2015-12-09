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


	private static final long serialVersionUID = 131250152L;

	JXCFile cityDistanceFile;

	public CityDistanceData() throws RemoteException {
		cityDistanceFile = new JXCFile("src/cityDistance.ser");
	}

	public int addCityDistance(CityDistancePO cityDistancepo) throws RemoteException {
		if (findCityDistanceByBoth(cityDistancepo.getCityA(), cityDistancepo.getCityB()) == null) {
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

	public ArrayList<CityDistancePO> findCityDistanceBySingle(String city) throws RemoteException {
		ArrayList<Object> objectList = cityDistanceFile.read();
		ArrayList<CityDistancePO> cityToFindDistanceList = new ArrayList<CityDistancePO>();
		
		if (objectList == null)
			return null;

		for (int i = 0; i < objectList.size(); i++) {
			CityDistancePO tempCityDistancePO = (CityDistancePO) (objectList.get(i));
			if ((tempCityDistancePO.getCityA().equals(city)) || (tempCityDistancePO.getCityB().equals(city)) ){
				cityToFindDistanceList.add(tempCityDistancePO);
			}
		}

		return cityToFindDistanceList;
	}
	
	public CityDistancePO findCityDistanceByBoth(String cityA, String cityB) throws RemoteException {
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
    }
    
    public ArrayList<String> getAllCitys(){
    	ArrayList<String> str = new ArrayList<String>();
		ArrayList<Object> objectList = cityDistanceFile.read();
	
		if (objectList == null)
			return null;
	
		for (int i = 0; i < objectList.size(); i++) {
			CityDistancePO tempCityDistancePO = (CityDistancePO) (objectList.get(i));
			String cityA = tempCityDistancePO.getCityA();
			String cityB = tempCityDistancePO.getCityB();
			
			boolean hasSame = false;
			for(int j=0;j<str.size();j++){
				hasSame = false;
				if(cityA.equals(str.get(j))){
					hasSame = true;
					break;
				}
			}
			if(!hasSame){
				str.add(cityA);
			}
			
			for(int j=0;j<str.size();j++){
				hasSame = false;
				if(cityB.equals(str.get(j))){
					hasSame = true;
					break;
				}
			}
			if(!hasSame){
				str.add(cityB);
			}
			
		}
		return str;
    }
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
    
    /*public static void main(String[] args){
		CityDistanceData cityDistanceData;
		try{
			cityDistanceData = new CityDistanceData();
			try{
				cityDistanceData.addCityDistance(new CityDistancePO("������", "������", 600));
				cityDistanceData.addCityDistance(new CityDistancePO("������", "������", 1000));
				cityDistanceData.addCityDistance(new CityDistancePO("������", "������", 1400));
				
				System.out.println("���������:");
				ArrayList<CityDistancePO> cityDistancepoList0 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList0 != null){
					for(int i=0;i<cityDistancepoList0.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList0.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+"  "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				CityDistancePO cityDistancepo = cityDistanceData.findCityDistanceByBoth("������", "������");
				if(cityDistancepo != null)
					System.out.println("Find the cityDistance: "+cityDistancepo.getCityA()+" "+cityDistancepo.getCityB()+" "+cityDistancepo.getDistance());
				else
					System.out.println("Cannot find the cityDistance");
				
				
				cityDistanceData.modifyCityDistance(new CityDistancePO("������", "������", 1800));
				System.out.println("���������:");
				ArrayList<CityDistancePO> cityDistancepoList1 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList1 != null){
					for(int i=0;i<cityDistancepoList1.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList1.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				
				System.out.println("���������������:");
				ArrayList<CityDistancePO> cityDistancepoList2 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList2 != null){
					for(int i=0;i<cityDistancepoList2.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList2.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				//cityDistanceData.deleteCityDistance("������", "������");
				System.out.println("���������:");
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

	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			CityDistanceDataService cityDistanceData = new CityDistanceData();
			LocateRegistry.createRegistry(6005);

			// ������RMI������������������
			Naming.rebind("rmi://172.25.132.40:6005/CityDistanceDataService", cityDistanceData);
			System.out.println("CityDistance Service start!");

			ArrayList<CityDistancePO> cityDistanceList0 = cityDistanceData.showAllCityDistances();
			for (CityDistancePO cityDistance : cityDistanceList0)
				System.out.println("CityA: " + cityDistance.getCityA() + ", CityB: " + cityDistance.getCityB()
						+ ", CityDistance: " + cityDistance.getDistance());

			CityDistancePO cityDistancepo = cityDistanceData.findCityDistanceByBoth("������", "������");
			System.out.println("CityA: " + cityDistancepo.getCityA() + ", CityB: " + cityDistancepo.getCityB()
					+ ", CityDistance: " + cityDistancepo.getDistance());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
