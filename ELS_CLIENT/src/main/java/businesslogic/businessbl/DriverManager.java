package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import po.DriverPO;
import vo.DriverVO;

public class DriverManager {

	private BusinessDataService businessData;

	public DriverManager() {
		businessData = new BusinessDataService_stub();
	}

	public ArrayList<DriverVO> getDriverInfo() {
		String organizationID = BusinessMainController.businessVO.organizationVO.organizationID;
		ArrayList<DriverVO> vos = new ArrayList<DriverVO>();
		ArrayList<DriverPO> pos = null;
		try {
			pos = businessData.getDriverInfos(organizationID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (DriverPO i : pos)
			vos.add(BusinessMainController.driverPOToVO(i));
		return vos;
	}

	public boolean addDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.addDriver(BusinessMainController.driverVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.deleteDriver(BusinessMainController.driverVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean modifyDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.modifyDriver(BusinessMainController.driverVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
