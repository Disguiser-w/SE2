package dataservice.managedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CityDistancePO;

public interface CityDistanceDataService {

	public int addCityDistance(CityDistancePO cityDistancepo) throws RemoteException;
    public int deleteCityDistance(String cityA, String cityB) throws RemoteException;
    public int modifyCityDistance(CityDistancePO cityDistancepo) throws RemoteException;
    public CityDistancePO findCityDistance(String cityA, String cityB) throws RemoteException;
    public ArrayList<CityDistancePO> showAllCityDistances() throws RemoteException;
    
}
