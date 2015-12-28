package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogicservice.manageblservice.CityDistanceBLService;
import dataservice.managedataservice.CityDistanceDataService;
import po.CityDistancePO;
import vo.CityDistanceVO;

public class CityDistanceBL implements CityDistanceBLService{
	
	CityDistanceDataService cddService;
	
	public CityDistanceBL(){
		try{
			cddService = DataFactory.getCityDistanceData();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public int addCityDistance(CityDistanceVO cityDistancevo){
		try{
			CityDistancePO cityDistancepo = cityDistanceVOToPO(cityDistancevo);
			return(cddService.addCityDistance(cityDistancepo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteCityDistance(CityDistanceVO cityDistancevo){
		try{
			return(cddService.deleteCityDistance(cityDistancevo.cityA, cityDistancevo.cityB));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyCityDistance(CityDistanceVO cityDistancevo){
		try{
			CityDistancePO cityDistancepo = cityDistanceVOToPO(cityDistancevo);
			return(cddService.modifyCityDistance(cityDistancepo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public ArrayList<CityDistanceVO> findCityDistanceBySingle(String city){
		try{
			ArrayList<CityDistancePO> cityDistancePOList =  cddService.findCityDistanceBySingle(city);
			ArrayList<CityDistanceVO> cityDistanceVOList =  new ArrayList<CityDistanceVO>();
			
			if(cityDistancePOList != null){
				for(CityDistancePO cityDistance: cityDistancePOList)
					cityDistanceVOList.add(cityDistancePOToVO(cityDistance));
				return cityDistanceVOList;
			}
			else
				return null;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CityDistanceVO> findCityDistanceByBoth(String cityA, String cityB){
		try{
			ArrayList<CityDistancePO> cityDistancePOList =  cddService.findCityDistanceByBoth(cityA, cityB);
			ArrayList<CityDistanceVO> cityDistanceVOList =  new ArrayList<CityDistanceVO>();
			if(cityDistancePOList != null){
				for(CityDistancePO cityDistance: cityDistancePOList)
					cityDistanceVOList.add(cityDistancePOToVO(cityDistance));
				return cityDistanceVOList;
			}
			else
				return null;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CityDistanceVO> showAllCityDistances(){
		try{
			ArrayList<CityDistancePO> cityDistancePOList =  cddService.showAllCityDistances();
			ArrayList<CityDistanceVO> cityDistanceVOList =  new ArrayList<CityDistanceVO>();
			
			if(cityDistancePOList != null){
				for(CityDistancePO cityDistance: cityDistancePOList)
					cityDistanceVOList.add(cityDistancePOToVO(cityDistance));
				return cityDistanceVOList;
			}
			else
				return null;
		}
		catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public static CityDistancePO cityDistanceVOToPO(CityDistanceVO cityDistancevo){
		return new CityDistancePO(cityDistancevo.cityA,cityDistancevo.cityB, cityDistancevo.distance);
	}
	
	public static CityDistanceVO cityDistancePOToVO(CityDistancePO cityDistancepo){
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

			//cityDistanceData.addCityDistance(new CityDistancePO("南极", "北极", 40000));
			
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
