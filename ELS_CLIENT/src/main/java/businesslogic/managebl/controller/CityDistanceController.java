package businesslogic.managebl.controller;

import java.util.ArrayList;

import vo.CityDistanceVO;
import businesslogic.managebl.CityDistanceBL;

public class CityDistanceController {
	
	private CityDistanceBL cityDistance;
	
	public CityDistanceController(){
		cityDistance = new CityDistanceBL();
	}
	
	public int addCityDistance(CityDistanceVO cityDistancevo){
		return cityDistance.addCityDistance(cityDistancevo);
	}
	
	public int deleteCityDistance(CityDistanceVO cityDistancevo){
		return cityDistance.deleteCityDistance(cityDistancevo);
	}
	
	public int modifyCityDistance(CityDistanceVO cityDistancevo){
		return cityDistance.modifyCityDistance(cityDistancevo);
	}
	
	public ArrayList<CityDistanceVO> findCityDistanceBySingle(String city){
		return cityDistance.findCityDistanceBySingle(city);
	}
	
	public ArrayList<CityDistanceVO> findCityDistanceByBoth(String cityA, String cityB){
		return cityDistance.findCityDistanceByBoth(cityA, cityB);
	}
	
	public ArrayList<CityDistanceVO> showAllCityDistances(){
		return cityDistance.showAllCityDistances();
	}
	
	
}
