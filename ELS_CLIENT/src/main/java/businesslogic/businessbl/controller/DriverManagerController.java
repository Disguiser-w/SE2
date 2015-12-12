package businesslogic.businessbl.controller;

import java.util.ArrayList;

import businesslogic.businessbl.DriverManager;
import businesslogicservice.businessblservice.DriverManagerBLService;
import vo.DriverVO;

public class DriverManagerController implements DriverManagerBLService {

	private DriverManager driverManager;

	public DriverManagerController() {
		driverManager = new DriverManager();
	}

	public ArrayList<DriverVO> getDriverInfo() {
		// TODO Auto-generated method stub
		return driverManager.getDriverInfo();
	}

	public boolean addDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		return driverManager.addDriver(vo);
	}

	public boolean deleteDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		return driverManager.deleteDriver(vo);
	}

	public boolean modifyDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		return driverManager.modifyDriver(vo);
	}
	
	public int getNumOfDriver(){
		return driverManager.getNumOfDriver();
	}

}
