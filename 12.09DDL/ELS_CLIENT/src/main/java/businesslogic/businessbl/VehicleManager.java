package businesslogic.businessbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import po.DriverPO;
import po.OrganizationPO;
import po.VehiclePO;
import vo.DriverVO;
import vo.OrganizationVO;
import vo.VehicleVO;

public class VehicleManager {

	private BusinessDataService businessData;

	public VehicleManager() {
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

	public ArrayList<VehicleVO> getVehicleInfo() {
		// TODO Auto-generated method stub
		ArrayList<VehicleVO> vos = new ArrayList<VehicleVO>();
		ArrayList<VehiclePO> pos = null;
		try {
			pos = businessData.getVehicleInfos(BusinessMainController.businessVO.organizationVO.organizationID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (VehiclePO i : pos)
			vos.add(BusinessMainController.vehiclePOToVO(i));

		return vos;
	}

	public boolean addVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.addVehicle(BusinessMainController.businessVO.organizationVO.organizationID,
					BusinessMainController.vehicleVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.deleteVehicle(BusinessMainController.businessVO.organizationVO.organizationID,
					BusinessMainController.vehicleVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public boolean modifyVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.modifyVehicle(BusinessMainController.businessVO.organizationVO.organizationID,
					BusinessMainController.vehicleVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<OrganizationVO> getOrganizationInfos() {
		ArrayList<OrganizationVO> vos = new ArrayList<OrganizationVO>();
		try {
			ArrayList<OrganizationPO> pos = businessData.getOrganizationInfos();
			if (pos != null)
				for (OrganizationPO i : pos)
					vos.add(OrganizationBL.organizationPOToVO(i));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vos;
	}

	public ArrayList<DriverVO> getDriverInfos() {
		ArrayList<DriverVO> vos = new ArrayList<DriverVO>();
		try {
			ArrayList<DriverPO> pos = businessData
					.getDriverInfos(BusinessMainController.businessVO.organizationVO.organizationID);
			if (pos != null)
				for (DriverPO i : pos)
					vos.add(BusinessMainController.driverPOToVO(i));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vos;
	}

	public int getNumOfVehicle() {
		try {
			int num = businessData.getNumOfVehicles(BusinessMainController.businessVO.organizationVO.organizationID);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
