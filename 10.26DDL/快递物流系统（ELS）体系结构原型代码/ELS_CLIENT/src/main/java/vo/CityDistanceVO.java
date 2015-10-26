package vo;

public class CityDistanceVO {
	private String cityA,cityB;
	private double distance;
	
	public CityDistanceVO(String cityA, String cityB, double distance){
		this.cityA = cityA;
		this.cityB = cityB;
		this.distance = distance;
	}
	
	public String getCityA(){
		return this.cityA;
	}
	
	public void setCityA(String cityA){
		this.cityA = cityA;
	}
	
	public String getCityB(){
		return this.cityB;
	}
	
	public void setCityB(String cityB){
		this.cityB = cityB;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
	
}
