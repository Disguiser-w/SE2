package businesslogic.managebl;

import java.rmi.RemoteException;

import po.CityDistancePO;
import vo.CityDistanceVO;
import businesslogicservice.manageblservice.CityDistanceBLService;
import dataservice.managedataservice.CityDistanceDataService;

public class CityDistanceBL implements CityDistanceBLService{
	CityDistanceDataService cddService;
	
	public int addCityDistance(CityDistanceVO cityDistancevo){
		try{
			CityDistancePO cityDistancepo = voToPO(cityDistancevo);
			cddService.addCityDistance(cityDistancepo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int deleteCityDistance(CityDistanceVO cityDistancevo){
		try{
			CityDistancePO cityDistancepo = voToPO(cityDistancevo);
			cddService.deleteCityDistance(cityDistancepo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int modifyCityDistance(CityDistanceVO cityDistancevo){
		try{
			CityDistancePO cityDistancepo = voToPO(cityDistancevo);
			cddService.modifyCityDistance(cityDistancepo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public double findCityDistance(String cityA, String cityB){
		try{
			return cddService.findCityDistance(cityA, cityB);
		}catch(RemoteException exception){
			exception.printStackTrace();
			return -1.0;
		}
	}
	
	public CityDistancePO voToPO(CityDistanceVO cityDistancevo){
		return new CityDistancePO(cityDistancevo.getCityA(),cityDistancevo.getCityB(), cityDistancevo.getDistance());
	}
	
	public CityDistanceVO poToVO(CityDistancePO cityDistancepo){
		return new CityDistanceVO(cityDistancepo.getCityA(),cityDistancepo.getCityB(), cityDistancepo.getDistance());
	}
	
}
