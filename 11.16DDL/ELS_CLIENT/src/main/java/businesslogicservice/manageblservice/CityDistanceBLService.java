package businesslogicservice.manageblservice;

import vo.CityDistanceVO;

public interface CityDistanceBLService {
	public int addCityDistance(CityDistanceVO citydistancevo);
	public int deleteCityDistance(CityDistanceVO citydistancevo);
	public int modifyCityDistance(CityDistanceVO citydistancevo);
	public double findCityDistance(String cityA, String cityB);
}
