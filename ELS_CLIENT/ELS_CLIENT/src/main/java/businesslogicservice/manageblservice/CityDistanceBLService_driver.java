package businesslogicservice.manageblservice;


import vo.CityDistanceVO;

public class CityDistanceBLService_driver {

	public void drive(CityDistanceBLService_stub cityDistanceBLService){
		cityDistanceBLService.addCityDistance(new CityDistanceVO("南京", "上海", 266));
        cityDistanceBLService.deleteCityDistance(new CityDistanceVO("南京", "上海", 266));
        cityDistanceBLService.modifyCityDistance(new CityDistanceVO("南京", "上海", 266));
        cityDistanceBLService.findCityDistance("南京", "上海");
	}
	
	public static void main(String[] args){
		CityDistanceBLService_stub cityDistanceBLService = new CityDistanceBLService_stub();
		CityDistanceBLService_driver driver = new CityDistanceBLService_driver();
        driver.drive(cityDistanceBLService);
    }
	
}
