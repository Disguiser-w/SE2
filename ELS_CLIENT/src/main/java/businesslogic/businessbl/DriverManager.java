package businesslogic.businessbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import po.DriverPO;
import vo.DriverVO;

public class DriverManager {

	private BusinessDataService businessData;

	public DriverManager() {
		try {
			businessData = DataFactory.getBusinessData();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			result = businessData.addDriver(BusinessMainController.businessVO.organizationVO.organizationID,
					BusinessMainController.driverVOToPO(vo));
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
			result = businessData.deleteDriver(BusinessMainController.businessVO.organizationVO.organizationID,
					BusinessMainController.driverVOToPO(vo));
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
			result = businessData.modifyDriver(BusinessMainController.businessVO.organizationVO.organizationID,
					BusinessMainController.driverVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int getNumOfDriver() {
		int num = 0;
		try {
			num = businessData.getNumOfDrivers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return num;
	}

}
