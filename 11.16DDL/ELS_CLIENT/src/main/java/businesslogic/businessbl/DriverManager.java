package businesslogic.businessbl;

import java.util.ArrayList;

import businesslogicservice.businessblservice.DriverManagerBLService;
import dataservice.businessdataservice.BusinessDataService;
import vo.DriverVO;

public class DriverManager {

	private BusinessDataService businessData;

	public DriverManager() {

	}

	public ArrayList<DriverVO> getDriverInfo() {
		// TODO Auto-generated method stub
		ArrayList<DriverVO> vos = new ArrayList<DriverVO>();
		vos.add(new DriverVO("025-000-111", null, null, null, null, null, null, null));
		return vos;
	}

	public boolean addDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean deleteDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean modifyDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

}
