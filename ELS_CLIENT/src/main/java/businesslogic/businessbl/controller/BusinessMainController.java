package businesslogic.businessbl.controller;

import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.BusinessPO;
import vo.BusinessVO;

public class BusinessMainController {
	public static BusinessDataService businessData;
	public static ExpressDataService expressData;
	public static BusinessVO businessVO;

	// controller
	public static GatheringController gatheringController;
	public static AcceptCargoController acceptCargoController;
	public static EnVehicleController enVehicleController;
	public static DistributeOrderController distributeorderController;
	public static DriverManagerController driverManagerController;
	public static VehicleManagerController vehicleManagerController;

	public BusinessMainController(String businessID) {
		// RMI
		businessData = new BusinessDataService_stub();
		expressData = new ExpressDataService_stub();

		businessVO = businessPOToVO(businessData.getBusinessInfo(null, businessID));
		gatheringController = new GatheringController();
		acceptCargoController = new AcceptCargoController();
		enVehicleController = new EnVehicleController();
		distributeorderController = new DistributeOrderController();
		driverManagerController = new DriverManagerController();
		vehicleManagerController = new VehicleManagerController();

	}

	public static void updateBusinessVO() {
		businessVO = businessPOToVO(
				businessData.getBusinessInfo(businessVO.organizationVO.organizationID, businessVO.ID));
	}

	public static BusinessVO businessPOToVO(BusinessPO po) {
		return new BusinessVO(po.getID(), po.getName(), po.getServiceTime(),
				OrganizationBL.organizationPOToVO(po.getOrganizationPO()));
	}

	public static BusinessPO businessVOToPO(BusinessVO vo) {
		return new BusinessPO(vo.ID, vo.name, vo.serviceTime, OrganizationBL.organizationVOToPO(vo.organizationVO));
	}
}
