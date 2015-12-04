package dataservice.managedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CityDistancePO;

public class CityDistanceDataService_stub implements CityDistanceDataService {

	public int addCityDistance(CityDistancePO cityDistancepo) throws RemoteException{
		System.out.println("Add city distance succeed!");
		return 0;
	}
	
    public int deleteCityDistance(String cityA, String cityB) throws RemoteException{
		System.out.println("Delete city distance succeed!");
		return 0;
	}
    
    public int modifyCityDistance(CityDistancePO cityDistancepo) throws RemoteException{
		System.out.println("Modify city distance succeed!");
		return 0;
	}
    
    public CityDistancePO findCityDistance(String cityA, String cityB) throws RemoteException{
		System.out.println("Find city distance succeed!");
		return null;
	}
    
    public ArrayList<CityDistancePO> showAllCityDistances() throws RemoteException{
		System.out.println("Show all city distance succeed!");
		return null;
	}
    
    public ArrayList<String> getAllCitys() throws RemoteException{
    	System.out.println("Get all city succeed!");
		return null;
    }
    
}
