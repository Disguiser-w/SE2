package businesslogic.managebl.controller;

import java.util.ArrayList;

import businesslogic.managebl.CityDistanceBL;
import businesslogicservice.manageblservice.CityDistanceBLService;
import vo.CityDistanceVO;

public class CityDistanceController implements CityDistanceBLService{
	
	private CityDistanceBL cityDistanceBL;
	
	public CityDistanceController(){
		cityDistanceBL = new CityDistanceBL();
	}
	
	public int addCityDistance(CityDistanceVO cityDistancevo){
		return cityDistanceBL.addCityDistance(cityDistancevo);
	}
	
	public int deleteCityDistance(CityDistanceVO cityDistancevo){
		return cityDistanceBL.deleteCityDistance(cityDistancevo);
	}
	
	public int modifyCityDistance(CityDistanceVO cityDistancevo){
		return cityDistanceBL.modifyCityDistance(cityDistancevo);
	}
	
	public ArrayList<CityDistanceVO> findCityDistanceBySingle(String city){
		return cityDistanceBL.findCityDistanceBySingle(city);
	}
	
	public ArrayList<CityDistanceVO> findCityDistanceByBoth(String cityA, String cityB){
		return cityDistanceBL.findCityDistanceByBoth(cityA, cityB);
	}
	
	public ArrayList<CityDistanceVO> showAllCityDistances(){
		return cityDistanceBL.showAllCityDistances();
	}
	
}
