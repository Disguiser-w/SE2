package businesslogic.businessbl.controller;

import java.rmi.Naming;

import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import po.BusinessPO;
import po.DistributeReceiptPO;
import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.VehiclePO;
import vo.BusinessVO;
import vo.DistributeReceiptVO;
import vo.DriverVO;
import vo.EnVehicleReceiptVO;
import vo.GatheringReceiptVO;
import vo.OrderAcceptReceiptVO;
import vo.VehicleVO;

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
		// businessData = new BusinessDataService_stub();

		try {
			businessData = (BusinessDataService) Naming.lookup("//localhost:8888/BusinessDataService");
			businessVO = businessPOToVO(businessData.getBusinessInfo(null, businessID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gatheringController = new GatheringController();
		acceptCargoController = new AcceptCargoController();
		enVehicleController = new EnVehicleController();
		distributeorderController = new DistributeOrderController();
		driverManagerController = new DriverManagerController();
		vehicleManagerController = new VehicleManagerController();

	}

	public static void updateBusinessVO() {
		try {
			businessVO = businessPOToVO(
					businessData.getBusinessInfo(businessVO.organizationVO.organizationID, businessVO.ID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BusinessVO businessPOToVO(BusinessPO po) {
		return new BusinessVO(po.getID(), po.getName(), po.getServiceTime(),
				OrganizationBL.organizationPOToVO(po.getOrganizationPO()));
	}

	public static BusinessPO businessVOToPO(BusinessVO vo) {
		return new BusinessPO(vo.ID, vo.name, vo.serviceTime, OrganizationBL.organizationVOToPO(vo.organizationVO));
	}

	public static OrderAcceptReceiptPO orderAcceptReceiptVOToPO(OrderAcceptReceiptVO vo) {

		VehiclePO vpo = vehicleVOToPO(vo.vehicleVO);
		OrderAcceptReceiptPO opo = new OrderAcceptReceiptPO(OrganizationBL.organizationVOToPO(vo.local), vo.time, vpo,
				vo.orderIDs, vo.receiptID, vo.receiptState);
		return opo;

	}

	public static OrderAcceptReceiptVO orderAcceptReceiptPOToVO(OrderAcceptReceiptPO po) {
		VehicleVO vvo = vehiclePOToVO(po.getVehiclePO());
		OrderAcceptReceiptVO Vpo = new OrderAcceptReceiptVO(OrganizationBL.organizationPOToVO(po.getLocal()),
				po.getTime(), vvo, po.getOrderIDs(), po.getReceiptID(), po.getReceiptState());
		return Vpo;
	}

	public static VehiclePO vehicleVOToPO(VehicleVO vo) {

		return new VehiclePO(vo.ID, vo.engineNumber, vo.licensePlateNumber, vo.lowNumberPlate, vo.buyTime,
				vo.serviceTime, OrganizationBL.organizationVOToPO(vo.destination), vo.destinationCity,
				OrganizationBL.organizationVOToPO(vo.local), driverVOToPO(vo.driver));
	}

	public static VehicleVO vehiclePOToVO(VehiclePO po) {
		return new VehicleVO(po.getID(), po.getEngineNumber(), po.getLicensePlateNumber(), po.getLowNumberPlate(),
				po.getBuyTime(), po.getServiceTime(), OrganizationBL.organizationPOToVO(po.getDestination()),
				po.getDestinationCity(), OrganizationBL.organizationPOToVO(po.getLocal()),
				driverPOToVO(po.getDriver()));
	}

	public static DriverVO driverPOToVO(DriverPO po) {

		return new DriverVO(po.getID(), po.getName(), po.getDateOfBirth(), po.getIdCardNumber(), po.getPhoneNumber(),
				OrganizationBL.organizationPOToVO(po.getVehicleOrganization()), po.getSexuality(),
				po.getRegistrationDeadline());
	}

	public static DriverPO driverVOToPO(DriverVO vo) {
		return new DriverPO(vo.ID, vo.name, vo.DateOfBirth, vo.IdCardNumber, vo.phoneNumber,
				OrganizationBL.organizationVOToPO(vo.vehicleOrganization), vo.sexuality, vo.registrationDeadline);
	}

	public static EnVehicleReceiptVO enVehicleReceiptPOToVO(EnVehicleReceiptPO po) {


		return new EnVehicleReceiptVO(OrganizationBL.organizationPOToVO(po.getPlaceOfDeparture()), po.getTime(),
				vehiclePOToVO(po.getVehiclePO()), po.getOrderPOList(), po.getReceiptID(), po.getReceiptState());
	}

	public static EnVehicleReceiptPO enVehicleReceiptVOToPO(EnVehicleReceiptVO vo) {
		return new EnVehicleReceiptPO(OrganizationBL.organizationVOToPO(vo.placeOfDeparture), vo.time,
				vehicleVOToPO(vo.vehicleVO), vo.OrderVOList, vo.receiptID, vo.receiptState);
	}

	public static DistributeReceiptVO distributePOToVO(DistributeReceiptPO po) {
		return new DistributeReceiptVO(po.getID(), po.getDistributeInfo(), po.getTime(), po.getReceiptState());
	}

	public static DistributeReceiptPO distributeVOToPO(DistributeReceiptVO vo) {

		return new DistributeReceiptPO(vo.ID, vo.distributeInfo, vo.time, vo.receiptState);
	}

	public static GatheringReceiptVO gatheringPOToVO(GatheringReceiptPO po) {
		return new GatheringReceiptVO(OrganizationBL.organizationPOToVO(po.getBusinesShall()), po.getTime(),
				po.getExpressIDs(), po.getMoney(), po.getTotalmoney(), po.getReceiptID(), po.getReceiptState());
	}

	public static GatheringReceiptPO gatheringVOToPO(GatheringReceiptVO vo) {
		return new GatheringReceiptPO(OrganizationBL.organizationVOToPO(vo.businesshall), vo.time, vo.expressList,
				vo.money, vo.totalmoney, vo.receiptID, vo.receiptState);
	}
}
