package dataservice.businessdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BusinessPO;
import po.DistributeReceiptPO;
import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.OrderPO;
import po.OrganizationPO;
import po.VehiclePO;

public class BusinessDataService_stub implements BusinessDataService {

	@Override
	public BusinessPO getBusinessInfo(String organizationID, String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehiclePO getVehicleInfo(String organizationID, String vehicleID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addReceipt(String organizationID, OrderAcceptReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<VehiclePO> getVehicleInfos(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEnVehicleReceipt(String organizationID, ArrayList<EnVehicleReceiptPO> pos)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addVehicle(String organizationID, VehiclePO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteVehicle(String organizationID, VehiclePO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyVehicle(String organizationID, VehiclePO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<DriverPO> getDriverInfos(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addGatheringReceipt(String organizationID, GatheringReceiptPO grp) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNumOfOrderAcceptReceipt(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<GatheringReceiptPO> getGatheringReceipt(String time) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addDistributeReceipt(String organizationID, DistributeReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DriverPO getDriverInfo(String organizationID, String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addDriver(String organizationID, DriverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDriver(String organizationID, DriverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyDriver(String organizationID, DriverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<GatheringReceiptPO> getSubmittedGatheringReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DistributeReceiptPO> getSubmittedDistributeReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EnVehicleReceiptPO> getSubmittedEnVehicleReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderAcceptReceiptPO> getSubmittedOrderAcceptReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDistributeReceiptInfo(DistributeReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrderAcceptReceiptInfo(OrderAcceptReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEnVehicleReceiptInfo(EnVehicleReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGatheringReceiptInfo(GatheringReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addDriverTime(String organizationID, String driverID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<OrganizationPO> getOrganizationInfos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfVehicles(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
