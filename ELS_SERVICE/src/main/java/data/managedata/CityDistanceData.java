package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CityDistancePO;

public class CityDistanceData {

	JXCFile cityDistanceFile;
    
    public CityDistanceData() throws RemoteException {
		cityDistanceFile = new JXCFile("src/main/java/cityDistance.ser");
	}
    
    public int addCityDistance(CityDistancePO cityDistancepo) throws RemoteException{
    	if(findCityDistance(cityDistancepo.getCityA(), cityDistancepo.getCityB())==null){
    		cityDistanceFile.write(cityDistancepo);
    		return 0;
    	}
    	else 
    		return 1;
    }
    
    public int deleteCityDistance(String cityA, String cityB) throws RemoteException{
    	ArrayList<Object> objectList = cityDistanceFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CityDistancePO tempCityDistancePO = (CityDistancePO)(objectList.get(i));
			if(((tempCityDistancePO.getCityA().equals(cityA))&&(tempCityDistancePO.getCityB().equals(cityB))) 
			|| (((tempCityDistancePO.getCityA().equals(cityB))&&(tempCityDistancePO.getCityB().equals(cityA))))){
				objectList.remove(i);
				break;
			}
		}
		
		cityDistanceFile.clear();
		cityDistanceFile.write(objectList);
		return 0;
    }
    
    public int modifyCityDistance(CityDistancePO cityDistancepo) throws RemoteException{
    	String cityA = cityDistancepo.getCityA();
    	String cityB = cityDistancepo.getCityB();
    	if(deleteCityDistance(cityA, cityB)==0){
    		addCityDistance(cityDistancepo);
    		return 0;
    	}
    	return 1;
    }
    
    public CityDistancePO findCityDistance(String cityA, String cityB) throws RemoteException{
    	ArrayList<Object> objectList = cityDistanceFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CityDistancePO tempCityDistancePO = (CityDistancePO)(objectList.get(i));
			if(((tempCityDistancePO.getCityA().equals(cityA))&&(tempCityDistancePO.getCityB().equals(cityB))) 
			|| (((tempCityDistancePO.getCityA().equals(cityB))&&(tempCityDistancePO.getCityB().equals(cityA))))){
				return tempCityDistancePO;
			}
		}
		
		return null;
    }
    
    public ArrayList<CityDistancePO> showAllCityDistances() throws RemoteException{
    	ArrayList<Object> objectList = cityDistanceFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		ArrayList<CityDistancePO> cityDistanceList = new ArrayList<CityDistancePO>();
		
		for(int i=0; i<objectList.size(); i++){
			CityDistancePO tempCityDistancePO = (CityDistancePO)(objectList.get(i));
			cityDistanceList.add(tempCityDistancePO);
		}
		
		return cityDistanceList;
    }
    
}
