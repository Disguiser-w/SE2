package po;

import java.io.Serializable;

public class CityDistancePO implements Serializable {
	
	private static final long serialVersionUID = 141250153L;

	private String cityA;
	private String cityB;
	private double distance;
	
	public CityDistancePO(String cityA, String cityB, double distance){
		this.cityA = cityA;
		this.cityB = cityB;
		this.distance = distance;
	}
	
	public String getCityA(){
		return this.cityA;
	}
	
	public void setVCityA(String cityA){
		this.cityA  = cityA;
	}
	
	public String getCityB(){
		return this.cityB;
	}
	
	public void setVCityB(String cityB){
		this.cityB  = cityB;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
}
