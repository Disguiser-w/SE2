package dataservice.managedataservice;

import java.rmi.RemoteException;

import po.CityDistancePO;

public class CityDistanceDataService_driver {
	
	public void drive(CityDistanceDataService_stub CityDistanceDataService) throws  RemoteException{
		CityDistanceDataService.addCityDistance(new CityDistancePO("南京", "上海", 266));
		CityDistanceDataService.deleteCityDistance(new CityDistancePO("南京", "上海", 266));
		CityDistanceDataService.modifyCityDistance(new CityDistancePO("南京", "上海", 266));
		CityDistanceDataService.findCityDistance("南京", "上海");
	}
	
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		CityDistanceDataService_stub citydistance_data_service = new CityDistanceDataService_stub();
		CityDistanceDataService_driver driver = new CityDistanceDataService_driver();
		driver.drive(citydistance_data_service);
	}
	
}
