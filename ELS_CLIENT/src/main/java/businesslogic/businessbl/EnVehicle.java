package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.expressbl.AddOrder;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.OrderPO;
import po.VehiclePO;
import type.TransferingState;
import vo.OrderVO;
import vo.VehicleVO;

public class EnVehicle {

	private BusinessDataService businessData;
	private ExpressDataService expressData;

	public EnVehicle() {
		businessData = new BusinessDataService_stub();
		expressData = new ExpressDataService_stub();
	}

	public ArrayList<String> autoTruckLoading(String OrganizationID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<VehicleVO> getFreeVehicles(String OrganizationID) {
		ArrayList<VehicleVO> vos = new ArrayList<VehicleVO>();
		ArrayList<VehiclePO> pos = null;
		try {
			pos = businessData.getVehicleInfos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (VehiclePO i : pos)
			if (i.getLocal().getOrganizationID().equals(OrganizationID))
				vos.add(VehicleManager.poToVO(i));

		return vos;
	}

	public ArrayList<OrderVO> getTransferOrders() {
		ArrayList<OrderVO> transferOrders = new ArrayList<OrderVO>();
		ArrayList<OrderPO> pos = null;
		try {
			pos = expressData.getOrderInfos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (OrderPO i : pos)
			if (i.getTransfer_state() == TransferingState.WAITING_ENVEHICLE)
				transferOrders.add(AddOrder.poToVO(i));

		return transferOrders;
	}

}
