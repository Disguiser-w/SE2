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
			return(cddService.addCityDistance(cityDistancepo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteCityDistance(CityDistanceVO cityDistancevo){
		try{
			CityDistancePO cityDistancepo = voToPO(cityDistancevo);
			return(cddService.deleteCityDistance(cityDistancepo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyCityDistance(CityDistanceVO cityDistancevo){
		try{
			CityDistancePO cityDistancepo = voToPO(cityDistancevo);
			return(cddService.modifyCityDistance(cityDistancepo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
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
