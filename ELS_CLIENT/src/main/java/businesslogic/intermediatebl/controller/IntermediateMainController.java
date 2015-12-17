package businesslogic.intermediatebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnIntermediateReceiptPO;
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
import presentation.intermediateui.EnvehiclePanel;
import presentation.intermediateui.IntermediateFrame;
import presentation.intermediateui.PlaneManagementPanel;
import presentation.intermediateui.TrainManagementPanel;
import presentation.intermediateui.TransferingPanel;
import presentation.intermediateui.TruckManagementPanel;
import type.ReceiptState;
import vo.EnIntermediateReceiptVO;
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
import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.intermediatebl.EnvehicleBL;
import businesslogic.intermediatebl.PlaneManagerBL;
import businesslogic.intermediatebl.TrainManagerBL;
import businesslogic.intermediatebl.TransferingBL;
import businesslogic.intermediatebl.TruckManagerBL;
import dataservice.intermediatedataservice.IntermediateDataService;

public class IntermediateMainController {
	private IntermediateFrame frame;
	private EnvehicleBL envehicle;
	private PlaneManagerBL planeManager;
	private TrainManagerBL trainManager;
	private TruckManagerBL truckManager;
	private TransferingBL transfering;
	private static IntermediateDataService intermediateData;

	private static IntermediateVO intermediate;
	private OrganizationVO intermediateCentre;
	private TransferingReceiptVO transferingReceipt;

	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();
	private ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

	private ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
	private ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
	private ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();

	public IntermediateMainController(String intermediate_ID)
			throws MalformedURLException, RemoteException, NotBoundException {
		try {
			intermediateData = DataFactory.getIntermediateData();
			intermediate = poToVO((IntermediatePO) (intermediateData
					.getIntermediateInfo(intermediate_ID)));
			// System.out.println(intermediate.organization.planeList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		intermediateCentre = intermediate.organization;
		// System.out.println(intermediateCentre.getName());
		planeList = intermediateCentre.planeList;
		trainList = intermediateCentre.trainList;
		truckList = intermediateCentre.truckList;
		planeManager = new PlaneManagerBL(planeList, intermediateCentre,
				intermediateData);
		trainManager = new TrainManagerBL(trainList, intermediateCentre,
				intermediateData);
		truckManager = new TruckManagerBL(truckList, intermediateCentre,
				intermediateData);
		// System.out.println(orderList.size());
		transferingReceipt = new TransferingReceiptVO(intermediateCentre,
				orderList, "", "", ReceiptState.DRAFT);
		transfering = new TransferingBL(transferingReceipt, intermediateData);
		envehicle = new EnvehicleBL(transfering, planeManager, trainManager,
				truckManager, enplaningReceiptList, entrainingReceiptList,
				entruckingReceiptList, intermediateData);

		frame = new IntermediateFrame(intermediate);
		frame.addFuncLabel(new TransferingPanel(this, frame));
		frame.addFuncLabel(new EnvehiclePanel(this, frame));
		frame.addFuncLabel(new PlaneManagementPanel(this, frame));
		frame.addFuncLabel(new TrainManagementPanel(this, frame));
		frame.addFuncLabel(new TruckManagementPanel(this, frame));

		frame.showFrame();
	}

	public void updateIntermediateInfo() throws RemoteException {
		intermediate = IntermediateMainController.poToVO(intermediateData
				.getIntermediateInfo(intermediate.ID));
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

	public static PlaneVO poToVO(PlanePO plane) {
		return new PlaneVO(plane.getID(), plane.getDestination());
	}

	public static TrainVO poToVO(TrainPO train) {
		return new TrainVO(train.getID(), train.getDestination());
	}

	public static TruckVO poToVO(TruckPO truck) {
		return new TruckVO(truck.getID(), truck.getDestination());
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

	public static ArrayList<OrderVO> poToVO_OrderList(
			ArrayList<OrderPO> orderList) {
		ArrayList<OrderVO> orderList_VO = new ArrayList<OrderVO>();
		for (OrderPO order : orderList)
			orderList_VO.add(IntermediateMainController.poToVO(order));

		return orderList_VO;
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

	public static TransferingReceiptVO poToVO(
			TransferingReceiptPO transferingReceipt) {
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		for (OrderPO order : transferingReceipt.getOrderList())
			orderList.add(IntermediateMainController.poToVO(order));

		return new TransferingReceiptVO(
				IntermediateMainController.poToVO(transferingReceipt
						.getInterdiateCentre()), orderList,
				transferingReceipt.getID(), transferingReceipt.getDate(),
				transferingReceipt.getReceiptState());
	}

	public static ArrayList<TransferingReceiptVO> poToVO_TransferingReceiptList(
			ArrayList<TransferingReceiptPO> transferingReceiptList) {
		ArrayList<TransferingReceiptVO> transferingReceiptList_VO = new ArrayList<TransferingReceiptVO>();
		for (TransferingReceiptPO transferingReceipt : transferingReceiptList)
			transferingReceiptList_VO.add(IntermediateMainController
					.poToVO(transferingReceipt));

		return transferingReceiptList_VO;
	}

	public static RepertoryVO poToVO(RepertoryPO repertory) {
		return new RepertoryVO(repertory.getRepertoryID(),
				repertory.getOwnerID());
	}

	public static OrganizationVO poToVO(OrganizationPO organization) {
		OrganizationVO intermediateCenter = new OrganizationVO(
				organization.getCategory(), organization.getOrganizationID(),
				organization.getName());
		ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
		ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
		ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();
		for (PlanePO plane : organization.getPlaneList())
			planeList.add(IntermediateMainController.poToVO(plane));
		for (TrainPO train : organization.getTrainList())
			trainList.add(IntermediateMainController.poToVO(train));
		for (TruckPO truck : organization.getTruckList())
			truckList.add(IntermediateMainController.poToVO(truck));

		intermediateCenter.planeList = planeList;
		intermediateCenter.trainList = trainList;
		intermediateCenter.truckList = truckList;

		return intermediateCenter;
	}

	public static IntermediateVO poToVO(IntermediatePO intermediate) {
		// System.out.println(intermediate.getName());
		return new IntermediateVO(
				IntermediateMainController.poToVO(intermediate
						.getOrganization()), intermediate.getName(),
				intermediate.getID());
	}

	public static EnIntermediateReceiptVO poToVO(
			EnIntermediateReceiptPO enIntermediateReceipt) {
		return new EnIntermediateReceiptVO(
				IntermediateMainController.poToVO(enIntermediateReceipt
						.getIntermediateCentre()),
				IntermediateMainController
						.poToVO_OrderList(enIntermediateReceipt.getOrderList()),
				enIntermediateReceipt.getFare(), enIntermediateReceipt.getID(),
				enIntermediateReceipt.getDate(), enIntermediateReceipt
						.getReceiptState());
	}

	public static EnplaningReceiptVO poToVO(EnplaningReceiptPO enplaningReceipt) {
		return new EnplaningReceiptVO(
				IntermediateMainController.poToVO(enplaningReceipt
						.getIntermediateCentre()),
				IntermediateMainController.poToVO(enplaningReceipt.getPlane()),
				IntermediateMainController.poToVO_OrderList(enplaningReceipt
						.getOrderList()), enplaningReceipt.getFare(),
				enplaningReceipt.getID(), enplaningReceipt.getDate(),
				enplaningReceipt.getReceiptState());
	}

	public static EntrainingReceiptVO poToVO(
			EntrainingReceiptPO entrainingReceipt) {
		return new EntrainingReceiptVO(
				IntermediateMainController.poToVO(entrainingReceipt
						.getIntermediateCentre()),
				IntermediateMainController.poToVO(entrainingReceipt.getTrain()),
				IntermediateMainController.poToVO_OrderList(entrainingReceipt
						.getOrderList()), entrainingReceipt.getFare(),
				entrainingReceipt.getID(), entrainingReceipt.getDate(),
				entrainingReceipt.getReceiptState());
	}

	public static EntruckingReceiptVO poToVO(
			EntruckingReceiptPO entruckingReceipt) {
		return new EntruckingReceiptVO(
				IntermediateMainController.poToVO(entruckingReceipt
						.getIntermediateCentre()),
				IntermediateMainController.poToVO(entruckingReceipt.getTruck()),
				IntermediateMainController.poToVO_OrderList(entruckingReceipt
						.getOrderList()), entruckingReceipt.getFare(),
				entruckingReceipt.getID(), entruckingReceipt.getDate(),
				entruckingReceipt.getReceiptState());
	}

	public static ArrayList<EnplaningReceiptVO> poToVO_EnplaningReceipt(
			ArrayList<EnplaningReceiptPO> list) {
		ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
		for (EnplaningReceiptPO receipt : list)
			enplaningReceiptList
					.add(IntermediateMainController.poToVO(receipt));

		return enplaningReceiptList;
	}

	public static ArrayList<EntrainingReceiptVO> poToVO_EntrainingReceipt(
			ArrayList<EntrainingReceiptPO> list) {
		ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
		for (EntrainingReceiptPO receipt : list)
			entrainingReceiptList.add(IntermediateMainController
					.poToVO(receipt));

		return entrainingReceiptList;
	}

	public static ArrayList<EntruckingReceiptVO> poToVO_EntruckingReceipt(
			ArrayList<EntruckingReceiptPO> list) {
		ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();
		for (EntruckingReceiptPO receipt : list)
			entruckingReceiptList.add(IntermediateMainController
					.poToVO(receipt));

		return entruckingReceiptList;
	}

	public ArrayList<PlaneVO> getPlaneList() {
		return planeList;
	}

	public ArrayList<TrainVO> getTrainList() {
		return trainList;
	}

	public ArrayList<TruckVO> getTruckList() {
		return truckList;
	}

	public OrganizationVO getIntermediateCentre() {
		return intermediateCentre;
	}

	public EnvehicleBL getEnvehicleBL() {
		return envehicle;
	}

	public TransferingReceiptVO getTransferingReceipt() {
		return transferingReceipt;
	}

	public TransferingBL getTransferingBL() {
		return transfering;
	}

	public PlaneManagerBL getPlaneManagerBL() {
		return planeManager;
	}

	public TrainManagerBL getTrainManagerBL() {
		return trainManager;
	}

	public TruckManagerBL getTruckManagerBL() {
		return truckManager;
	}

	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException {
		IntermediateMainController controller = new IntermediateMainController(
				"141250185");
	}
}
