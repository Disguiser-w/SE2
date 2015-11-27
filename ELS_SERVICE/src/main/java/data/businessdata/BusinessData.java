package data.businessdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.businessdataservice.BusinessDataService;
import po.BusinessPO;
import po.DistributeReceiptPO;
import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.VehiclePO;

public class BusinessData implements BusinessDataService{

	public BusinessPO getBusinessInfo(String organizationID, String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public VehiclePO getVehicleInfo(String organizationID, String vehicleID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addReceipt(String organizationID, OrderAcceptReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<VehiclePO> getVehicleInfos(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addEnVehicleReceipt(String organizationID, ArrayList<EnVehicleReceiptPO> pos)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addVehicle(String organizationID, VehiclePO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteVehicle(String organizationID, VehiclePO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modifyVehicle(String organizationID, VehiclePO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<DriverPO> getDriverInfos(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addGatheringReceipt(String organizationID, GatheringReceiptPO grp) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public int getNumOfOrderAcceptReceipt(String organizationID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<GatheringReceiptPO> getGatheringReceipt(String time) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addDistributeReceipt(String organizationID, DistributeReceiptPO po) {
		// TODO Auto-generated method stub
		return false;
	}

	public DriverPO getDriverInfo(String organizationID, String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addDriver(String organizationID, DriverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteDriver(String organizationID, DriverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modifyDriver(String organizationID, DriverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
