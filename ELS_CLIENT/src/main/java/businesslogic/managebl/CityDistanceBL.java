package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
			return(cddService.deleteCityDistance(cityDistancevo.getCityA(), cityDistancevo.getCityB()));
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
			return cddService.findCityDistance(cityA, cityB).getDistance();
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
	
	
/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	public static void main(String[] args){
		try {
			CityDistanceDataService cityDistanceData = (CityDistanceDataService)Naming.lookup("rmi://172.25.132.40:6005/CityDistanceDataService");
			
			ArrayList<CityDistancePO> cityDistanceList0 = cityDistanceData.showAllCityDistances();
			for(CityDistancePO cityDistance:cityDistanceList0)
				System.out.println("CityA: "+cityDistance.getCityA()+"CityB: "+cityDistance.getCityB()+", CityDistance: "+cityDistance.getDistance());

			cityDistanceData.addCityDistance(new CityDistancePO("南极", "北极", 40000));
			
			ArrayList<CityDistancePO> cityDistanceList1 = cityDistanceData.showAllCityDistances();
			for(CityDistancePO cityDistance:cityDistanceList1)
				System.out.println("CityA: "+cityDistance.getCityA()+"CityB: "+cityDistance.getCityB()+", CityDistance: "+cityDistance.getDistance());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
