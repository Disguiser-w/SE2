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
	
	public double findCityDistance(String cityA, String cityB){
		return cityDistance.findCityDistance(cityA, cityB);
	}
	
	public ArrayList<CityDistanceVO> showAllCityDistances(){
		return cityDistance.showAllCityDistances();
	}
	
}
