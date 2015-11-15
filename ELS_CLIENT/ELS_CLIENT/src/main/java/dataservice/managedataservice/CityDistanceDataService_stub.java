package dataservice.managedataservice;

import java.rmi.RemoteException;

import po.CityDistancePO;

public class CityDistanceDataService_stub implements CityDistanceDataService{
	
	public int addCityDistance(CityDistancePO citydistancepo) throws RemoteException{
		System.out.println("Add city distance succeed!");
		return 0;
	}
	
	public int deleteCityDistance(CityDistancePO citydistancepo) throws RemoteException{
		System.out.println("Delete city distance succeed!");
		return 0;
	}
	
	public int modifyCityDistance(CityDistancePO citydistancepo) throws RemoteException{
		System.out.println("Modify city distance succeed!");
		return 0;
	}
	
	public double findCityDistance(String cityA, String cityB) throws RemoteException{
		System.out.println("Find city distance succeed!");
		return 0;
	}
	
}
