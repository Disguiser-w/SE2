package businesslogicservice.manageblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CityDistanceVO;

public interface CityDistanceBLService {
	public int addCityDistance(CityDistanceVO citydistancevo) throws RemoteException;
	public int deleteCityDistance(CityDistanceVO citydistancevo) throws RemoteException;
	public int modifyCityDistance(CityDistanceVO citydistancevo) throws RemoteException;
	public ArrayList<CityDistanceVO> findCityDistanceBySingle(String city) throws RemoteException;
	public CityDistanceVO findCityDistanceByBoth(String cityA, String cityB) throws RemoteException;
	public ArrayList<CityDistanceVO> showAllCityDistances() throws RemoteException;
}
