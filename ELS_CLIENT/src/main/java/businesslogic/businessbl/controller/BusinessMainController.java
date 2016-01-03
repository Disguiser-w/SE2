package businesslogic.businessbl.controller;

import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.OrganizationBL;
import common.ImageGetter;
import dataservice.businessdataservice.BusinessDataService;
import po.BusinessPO;
import po.DistributeReceiptPO;
import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.VehiclePO;
import presentation.businessui.BusinessFrame;
import presentation.businessui.ChargeCollectionPanel;
import presentation.businessui.DriverManagerPanel;
import presentation.businessui.EnVehiclePanel;
import presentation.businessui.OrderDistributePanel;
import presentation.businessui.OrderReceiveManagerPanel;
import presentation.businessui.VehicleManagerPanel;
import vo.BusinessVO;
import vo.DistributeReceiptVO;
import vo.DriverVO;
import vo.EnVehicleReceiptVO;
import vo.GatheringReceiptVO;
import vo.OrderAcceptReceiptVO;
import vo.VehicleVO;

public class BusinessMainController {
	public static BusinessDataService businessData;
	public static BusinessVO businessVO;

	// controller
	public static GatheringController gatheringController;
	public static AcceptCargoController acceptCargoController;
	public static EnVehicleController enVehicleController;
	public static DistributeOrderController distributeorderController;
	public static DriverManagerController driverManagerController;
	public static VehicleManagerController vehicleManagerController;

	private BusinessFrame businessFrame;

	public BusinessMainController(String businessID) {
		try {
			businessData = DataFactory.getBusinessData();
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

		businessFrame = new BusinessFrame(businessVO);

		businessFrame.addFuncLabel(new EnVehiclePanel(enVehicleController), "装车管理",
				ImageGetter.getImage("装车管理.png").getImage());
		businessFrame.addFuncLabel(new OrderDistributePanel(distributeorderController), "派件",
				ImageGetter.getImage("distribute.png").getImage());

		businessFrame.addFuncLabel(new OrderReceiveManagerPanel(acceptCargoController), "货物接收",
				ImageGetter.getImage("acceptCargo.png").getImage());

		businessFrame.addFuncLabel(new DriverManagerPanel(driverManagerController, businessFrame), "司机管理",
				ImageGetter.getImage("driverManager.png").getImage());
		businessFrame.addFuncLabel(new VehicleManagerPanel(vehicleManagerController, businessFrame), "车辆管理",
				ImageGetter.getImage("vehicleManager.png").getImage());
		businessFrame.addFuncLabel(new ChargeCollectionPanel(gatheringController), "收款汇总",
				ImageGetter.getImage("gathering.png").getImage());

		businessFrame.showFrame();

	}

	public static void updateBusinessVO() {
		try {

			businessVO = businessPOToVO(
					businessData.getBusinessInfo(businessVO.organizationVO.organizationID, businessVO.userID));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BusinessVO businessPOToVO(BusinessPO po) {
		// super(name, ID, password, professionType, organizationID,
		// salaryPlanType, authority, grade);
		//
		// this.serviceTime = serviceTime;
		// this.organizationPO = or,
		return new BusinessVO(po.getName(), po.getID(), po.getPassword(), po.getProfession(), po.getOrganization(),
				po.getSalaryPlan(), po.getAuthority(), po.getGrades(), po.getServiceTime(),
				OrganizationBL.organizationPOToVO(po.getOrganizationPO()));
	}

	public static BusinessPO businessVOToPO(BusinessVO vo) {
		return new BusinessPO(vo.userName, vo.userID, vo.password, vo.profession, vo.organization, vo.salaryPlan,
				vo.authority, vo.grades, vo.serviceTime, OrganizationBL.organizationVOToPO(vo.organizationVO));
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
				po.getRegistrationDeadline(), po.getTime(), po.isUsing());
	}

	public static DriverPO driverVOToPO(DriverVO vo) {
		return new DriverPO(vo.ID, vo.name, vo.DateOfBirth, vo.IdCardNumber, vo.phoneNumber,
				OrganizationBL.organizationVOToPO(vo.vehicleOrganization), vo.sexuality, vo.registrationDeadline,
				vo.time, vo.isUsing);
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

	public static void main(String[] args) {
		new BusinessMainController("YYT-00001");
	}
}
