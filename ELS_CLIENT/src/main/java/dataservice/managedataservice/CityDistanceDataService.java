package dataservice.managedataservice;

import java.rmi.RemoteException;

import po.CityDistancePO;

public interface CityDistanceDataService {
	
	public int addCityDistance(CityDistancePO citydistancepo) throws RemoteException;
	public int deleteCityDistance(CityDistancePO citydistancepo) throws RemoteException;
	public int modifyCityDistance(CityDistancePO citydistancepo) throws RemoteException;
	public double findCityDistance(String cityA, String cityB) throws RemoteException;
	
}
