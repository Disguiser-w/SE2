package businesslogicservice.manageblservice;

import java.rmi.RemoteException;

import vo.CityDistanceVO;

public interface CityDistanceBLService {
	public int addCityDistance(CityDistanceVO citydistancevo) throws RemoteException;
	public int deleteCityDistance(CityDistanceVO citydistancevo) throws RemoteException;
	public int modifyCityDistance(CityDistanceVO citydistancevo) throws RemoteException;
	public double findCityDistance(String cityA, String cityB) throws RemoteException;
}
