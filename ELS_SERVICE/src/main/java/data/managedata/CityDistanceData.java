package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CityDistancePO;
import file.JXCFile;
import dataservice.managedataservice.CityDistanceDataService;

public class CityDistanceData implements CityDistanceDataService{

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
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CityDistancePO tempCityDistancePO = (CityDistancePO)(objectList.get(i));
			if(((tempCityDistancePO.getCityA().equals(cityA))&&(tempCityDistancePO.getCityB().equals(cityB))) 
			|| (((tempCityDistancePO.getCityA().equals(cityB))&&(tempCityDistancePO.getCityB().equals(cityA))))){
				objectList.remove(i);
				break;
			}
		}
		
		//cityDistanceFile.clear();
		cityDistanceFile.writeM(objectList);
		return 0;
    }
    
    public int modifyCityDistance(CityDistancePO cityDistancepo) throws RemoteException{
    	ArrayList<Object> objectList = cityDistanceFile.read();
    	
		if(objectList==null)
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			CityDistancePO tempCityDistancePO = (CityDistancePO)(objectList.get(i));
			if(((tempCityDistancePO.getCityA().equals(cityDistancepo.getCityA()))&&(tempCityDistancePO.getCityB().equals(cityDistancepo.getCityB()))) 
			|| (((tempCityDistancePO.getCityA().equals(cityDistancepo.getCityB()))&&(tempCityDistancePO.getCityB().equals(cityDistancepo.getCityA()))))){
				objectList.add(cityDistancepo);
				objectList.remove(i);
				break;
			}
		}
		
		cityDistanceFile.writeM(objectList);
		return 0;
    }
    
    public CityDistancePO findCityDistance(String cityA, String cityB) throws RemoteException{
    	ArrayList<Object> objectList = cityDistanceFile.read();
    	
		if(objectList==null)
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
    	
		if(objectList==null)
			return null;  	  
		
		ArrayList<CityDistancePO> cityDistanceList = new ArrayList<CityDistancePO>();
		
		for(int i=0; i<objectList.size(); i++){
			CityDistancePO tempCityDistancePO = (CityDistancePO)(objectList.get(i));
			cityDistanceList.add(tempCityDistancePO);
		}
		
		return cityDistanceList;
    }
    
    
    
    /*public static void main(String[] args){
		CityDistanceData cityDistanceData;
		try{
			cityDistanceData = new CityDistanceData();
			try{
				cityDistanceData.addCityDistance(new CityDistancePO("上海", "南京", 600));
				cityDistanceData.addCityDistance(new CityDistancePO("上海", "北京", 1000));
				cityDistanceData.addCityDistance(new CityDistancePO("南京", "北京", 1400));
				
				System.out.println("添加后:");
				ArrayList<CityDistancePO> cityDistancepoList0 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList0 != null){
					for(int i=0;i<cityDistancepoList0.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList0.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+"  "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				CityDistancePO cityDistancepo = cityDistanceData.findCityDistance("北京", "南京");
				if(cityDistancepo != null)
					System.out.println("Find the cityDistance: "+cityDistancepo.getCityA()+" "+cityDistancepo.getCityB()+" "+cityDistancepo.getDistance());
				else
					System.out.println("Cannot find the cityDistance");
				
				
				System.out.println("修改后:");
				cityDistanceData.modifyCityDistance(new CityDistancePO("北京", "上海", 1800));
				ArrayList<CityDistancePO> cityDistancepoList1 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList1 != null){
					for(int i=0;i<cityDistancepoList1.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList1.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				
				System.out.println("没有删除前:");
				ArrayList<CityDistancePO> cityDistancepoList2 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList2 != null){
					for(int i=0;i<cityDistancepoList2.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList2.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
				
				System.out.println("删除后:");
				cityDistanceData.deleteCityDistance("上海", "南京");
				ArrayList<CityDistancePO> cityDistancepoList3 = cityDistanceData.showAllCityDistances();
				if(cityDistancepoList3 != null){
					for(int i=0;i<cityDistancepoList3.size();i++){
						CityDistancePO tempCityDistancepo = cityDistancepoList3.get(i);
						System.out.println(tempCityDistancepo.getCityA()+"  "+tempCityDistancepo.getCityB()+" "+tempCityDistancepo.getDistance());
					}
				}
				else 
					System.out.println("Cannot find the cityDistance");
					
				}catch(RemoteException exception){
					exception.printStackTrace();
				}
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}*/
    
    
}
