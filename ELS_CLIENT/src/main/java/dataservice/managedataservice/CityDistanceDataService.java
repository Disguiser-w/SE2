package dataservice.managedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CityDistancePO;

public interface CityDistanceDataService {

	public int addCityDistance(CityDistancePO citydistancepo) throws RemoteException;

	public int deleteCityDistance(CityDistancePO citydistancepo) throws RemoteException;

	public int modifyCityDistance(CityDistancePO citydistancepo) throws RemoteException;

	public double findCityDistance(String cityA, String cityB) throws RemoteException;

	/**
	 * 返回所有城市列表
	 */
	public ArrayList<String> getCitys();

	/**
	 * 平均时间,完成订单时快递员更新 
	 */
	public boolean meanTime(String cityA, String cityB);

}
