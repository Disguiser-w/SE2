package businesslogicservice.businessblservice;

import java.util.ArrayList;

import vo.DriverVO;

public class DriverManagerBLService_stub implements DriverManagerBLService {
	public DriverVO getDriverInfo(String ID) {
		// TODO Auto-generated method stub
		System.out.println("Show DriverVOs!");
		return null;
	}
	public boolean addDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		System.out.println("AddDriver successfully!");
		return false;
	}
	public boolean deleteDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		System.out.println("DeleteDriver successfully!");
		return false;
	}
	public boolean modifyDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		System.out.println("ModifyDriver successfully!");
		return false;
	}
	@Override
	public ArrayList<DriverVO> getDriverInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getNumOfDriver() {
		// TODO Auto-generated method stub
		return 0;
	}
}
