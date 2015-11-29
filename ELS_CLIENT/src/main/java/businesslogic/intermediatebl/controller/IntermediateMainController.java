package businesslogic.intermediatebl.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnplaningReceiptPO;
import po.EntrainingReceiptPO;
import po.EntruckingReceiptPO;
import po.FarePO;
import po.IntermediatePO;
import po.OrderPO;
import po.OrganizationPO;
import po.PlanePO;
import po.RepertoryPO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.IntermediateVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.PlaneVO;
import vo.RepertoryVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.intermediatebl.TransferingBL;
import businesslogic.intermediatebl.envehiclebl.EnvehicleBL;
import businesslogic.intermediatebl.envehiclebl.PlaneManagerBL;
import businesslogic.intermediatebl.envehiclebl.TrainManagerBL;
import businesslogic.intermediatebl.envehiclebl.TruckManagerBL;
import dataservice.intermediatedataservice.IntermediateDataService;

public class IntermediateMainController {
	private EnvehicleBL envehicle;
	private PlaneManagerBL planeManager;
	private TrainManagerBL trainManager;
	private TruckManagerBL truckManager;
	private TransferingBL transfering;
	private IntermediateDataService intermediateData;
	private ExpressMainController expressMainController;

	private IntermediateVO intermediate;
	private OrganizationVO intermediateCentre;
	private TransferingReceiptVO transferingReceipt;

	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();
	private ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

	private ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
	private ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
	private ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();

	public IntermediateMainController(String intermediateID)
			throws MalformedURLException, RemoteException, NotBoundException {
		// intermediateDataService
		expressMainController = new ExpressMainController(null);
		intermediateData = (IntermediateDataService) Naming
				.lookup("rmi://172.25.133.95:8888/IntermediateDataService");
		intermediate = IntermediateMainController.poToVO(intermediateData
				.getIntermediateInfo("", intermediateID));
		intermediateCentre = intermediate.organization;

		planeList = intermediateCentre.planeList;
		trainList = intermediateCentre.trainList;
		truckList = intermediateCentre.truckList;
		planeManager = new PlaneManagerBL(planeList, intermediateCentre,
				intermediateData);
		trainManager = new TrainManagerBL(trainList, intermediateCentre,
				intermediateData);
		truckManager = new TruckManagerBL(truckList, intermediateCentre,
				intermediateData);

		transferingReceipt = new TransferingReceiptVO(intermediateCentre,
				orderList, "", "");
		transfering = new TransferingBL(transferingReceipt, intermediateData);

		envehicle = new EnvehicleBL(transfering, planeManager, trainManager,
				truckManager, enplaningReceiptList, entrainingReceiptList,
				entruckingReceiptList, intermediateData);
	}

	public void updateIntermediateInfo() {
		intermediate = IntermediateMainController.poToVO(intermediateData
				.getIntermediateInfo(intermediate.organization.organizationID,
						intermediate.ID));
	}

	public static OrderPO voToPO(OrderVO order) {
		return ExpressMainController.orderVOToPO(order);
	}

	public static FarePO voToPO(FareVO fare) {
		ArrayList<EnplaningReceiptPO> enplaningReceiptList = new ArrayList<EnplaningReceiptPO>();
		ArrayList<EntrainingReceiptPO> entrainingReceiptList = new ArrayList<EntrainingReceiptPO>();
		ArrayList<EntruckingReceiptPO> entruckingReceiptList = new ArrayList<EntruckingReceiptPO>();

		for (EnplaningReceiptVO enplaningReceipt : fare.enplaningReceiptVOList)
			enplaningReceiptList.add(IntermediateMainController
					.voToPO(enplaningReceipt));
		for (EntrainingReceiptVO entrainingReceipt : fare.entrainingReceiptVOList)
			entrainingReceiptList.add(IntermediateMainController
					.voToPO(entrainingReceipt));
		for (EntruckingReceiptVO entruckingReceipt : fare.entruckingReceiptVOList)
			entruckingReceiptList.add(IntermediateMainController
					.voToPO(entruckingReceipt));

		return new FarePO(
				IntermediateMainController.voToPO(fare.intermediateCentre),
				enplaningReceiptList, entrainingReceiptList,
				entruckingReceiptList, fare.fare_sum);
	}

	public static PlanePO voToPO(PlaneVO plane) {
		return new PlanePO(plane.ID, plane.destination);
	}

	public static TrainPO voToPO(TrainVO train) {
		return new TrainPO(train.ID, train.destination);
	}

	public static TruckPO voToPO(TruckVO truck) {
		return new TruckPO(truck.ID, truck.destination);
	}

	public static OrganizationPO voToPO(OrganizationVO intermediate) {
		return new OrganizationPO(intermediate.category,
				intermediate.organizationID, intermediate.name);
	}

	public static EnplaningReceiptPO voToPO(EnplaningReceiptVO enplaningReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : enplaningReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new EnplaningReceiptPO(
				IntermediateMainController
						.voToPO(enplaningReceipt.intermediateCentre),
				IntermediateMainController.voToPO(enplaningReceipt.plane),
				orderList, enplaningReceipt.ID);
	}

	public static EntrainingReceiptPO voToPO(
			EntrainingReceiptVO entrainingReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : entrainingReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new EntrainingReceiptPO(
				IntermediateMainController
						.voToPO(entrainingReceipt.intermediateCentre),
				IntermediateMainController.voToPO(entrainingReceipt.train),
				orderList, entrainingReceipt.ID);
	}

	public static EntruckingReceiptPO voToPO(
			EntruckingReceiptVO entruckingReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : entruckingReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new EntruckingReceiptPO(
				IntermediateMainController
						.voToPO(entruckingReceipt.intermediateCentre),
				IntermediateMainController.voToPO(entruckingReceipt.truck),
				orderList, entruckingReceipt.fare, entruckingReceipt.ID);
	}

	public static ArrayList<EnplaningReceiptPO> voToPO_EnplaningReceipt(
			ArrayList<EnplaningReceiptVO> list) {
		ArrayList<EnplaningReceiptPO> enplaningReceiptList = new ArrayList<EnplaningReceiptPO>();
		for (EnplaningReceiptVO receipt : list)
			enplaningReceiptList
					.add(IntermediateMainController.voToPO(receipt));

		return enplaningReceiptList;
	}

	public static ArrayList<EntrainingReceiptPO> voToPO_EntrainingReceipt(
			ArrayList<EntrainingReceiptVO> list) {
		ArrayList<EntrainingReceiptPO> entrainingReceiptList = new ArrayList<EntrainingReceiptPO>();
		for (EntrainingReceiptVO receipt : list)
			entrainingReceiptList.add(IntermediateMainController
					.voToPO(receipt));

		return entrainingReceiptList;
	}

	public static ArrayList<EntruckingReceiptPO> voToPO_EntruckingReceipt(
			ArrayList<EntruckingReceiptVO> list) {
		ArrayList<EntruckingReceiptPO> entruckingReceiptList = new ArrayList<EntruckingReceiptPO>();
		for (EntruckingReceiptVO receipt : list)
			entruckingReceiptList.add(IntermediateMainController
					.voToPO(receipt));

		return entruckingReceiptList;
	}

	public static OrderVO poToVO(OrderPO order) {
		return ExpressMainController.orderPOToVO(order);
	}

	public static TransferingReceiptPO voToPO(
			TransferingReceiptVO transferingReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : transferingReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new TransferingReceiptPO(
				IntermediateMainController
						.voToPO(transferingReceipt.interdiateCentre),
				orderList, transferingReceipt.ID);
	}

	public static RepertoryVO poToVO(RepertoryPO repertory) {
		return new RepertoryVO(repertory.getOwnerID(), repertory.getOwnerID());
	}

	public static OrganizationVO poToVO(OrganizationPO organization) {
		return new OrganizationVO(organization.getCategory(),
				organization.getOrganizationID(), organization.getName(),
				IntermediateMainController.poToVO(organization.getRepertory()));
	}

	public static IntermediateVO poToVO(IntermediatePO intermediate) {
		return new IntermediateVO(
				IntermediateMainController.poToVO(intermediate
						.getOrganization()), intermediate.getName(),
				intermediate.getID());
	}
}
