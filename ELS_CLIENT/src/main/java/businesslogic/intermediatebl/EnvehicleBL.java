package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnIntermediateReceiptPO;
import po.EnplaningReceiptPO;
import po.EntrainingReceiptPO;
import po.EntruckingReceiptPO;
import type.ExpressType;
import type.OperationState;
import type.OrderState;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.IntermediateVO;
import vo.LogDiaryVO;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogic.managebl.CityDistanceBL;
import businesslogic.receiptbl.getDate;
import businesslogicservice.intermediateblservice.envehicleblservice.EnvehicleBLService;
import dataservice.intermediatedataservice.IntermediateDataService;

public class EnvehicleBL implements EnvehicleBLService {
	private IntermediateVO intermediate;
	private IntermediateDataService intermediateData;
	private AllocateWaitingOrderBL awobl;
	private CityDistanceBL cdbl;
	private TransferingBL transfering;
	private PlaneManagerBL planeManager;
	private TrainManagerBL trainManeger;
	private TruckManagerBL truckManager;
	private LogDiaryBL logDiary;

	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();
	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();

	private ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
	private ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
	private ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();

	private ArrayList<EnIntermediateReceiptPO> enList = new ArrayList<EnIntermediateReceiptPO>();
	private TransferingReceiptVO transferingReceipt;

	private final double STANDARD_PLANE = 200;
	private final double ECONOMIC_TRAIN = 200;
	private final double ECONOMIC_PLANE = 500;

	public EnvehicleBL(TransferingBL transfering, PlaneManagerBL planeManager,
			TrainManagerBL trainManeger, TruckManagerBL truckManager,
			ArrayList<EnplaningReceiptVO> enplaningReceiptList,
			ArrayList<EntrainingReceiptVO> entrainingReceiptList,
			ArrayList<EntruckingReceiptVO> entruckingReceiptList,
			IntermediateDataService intermediateData,
			IntermediateVO intermediate) {
		// updateMessage();
		this.transfering = transfering;
		this.planeManager = planeManager;
		this.trainManeger = trainManeger;
		this.truckManager = truckManager;
		this.transferingReceipt = transfering.showTransferingReceipt();
		this.planeList = planeManager.showPlaneList();
		this.trainList = trainManeger.showTrainList();
		this.truckList = truckManager.showTruckList();
		this.enplaningReceiptList = enplaningReceiptList;
		this.entrainingReceiptList = entrainingReceiptList;
		this.entruckingReceiptList = entruckingReceiptList;
		this.intermediateData = intermediateData;
		this.intermediate = intermediate;
	}

	public OperationState envehicle() throws Exception {
		// TODO 自动生成的方法存根
		awobl = new AllocateWaitingOrderBL(transferingReceipt);
		cdbl = new CityDistanceBL();
		logDiary = new LogDiaryBL();
		waitingOrderList = awobl.updateWaitingList();

		for (OrderVO order : waitingOrderList) {
			String[] address_end = order.recipientAddress.split(" ");
			String[] address_start = order.senderAddress.split(" ");
			double distance = cdbl.findCityDistanceByBoth(address_start[0],
					address_end[0]).get(0).distance;
			if (order.expressType == ExpressType.FAST
					|| (order.expressType == ExpressType.STANDARD && distance > STANDARD_PLANE)
					|| (order.expressType == ExpressType.ECONOMIC && distance > ECONOMIC_PLANE)) {
				for (PlaneVO plane : planeList) {
					if ((this.showEnplaningReceipt(plane).orderList.size() <= 2000)
							&& address_end[0] == plane.destination) {
						this.showEnplaningReceipt(plane).orderList.add(order);
						order.order_state = OrderState.TRANSFERING;
						return OperationState.SUCCEED_OPERATION;
					}
				}
			} else if ((order.expressType == ExpressType.STANDARD && distance <= STANDARD_PLANE)
					|| (order.expressType == ExpressType.ECONOMIC
							&& distance <= ECONOMIC_PLANE && distance > ECONOMIC_TRAIN)) {
				for (TrainVO train : trainList) {
					if ((this.showEntrainingReceiptVO(train).orderList.size() <= 200000)
							&& address_end[0] == train.destination) {
						this.showEntrainingReceiptVO(train).orderList
								.add(order);
						order.order_state = OrderState.TRANSFERING;
						return OperationState.SUCCEED_OPERATION;
					}
				}
			} else if (order.expressType == ExpressType.ECONOMIC
					&& distance <= ECONOMIC_TRAIN) {
				for (TruckVO truck : truckList) {
					if (this.showEntruckingReceiptVO(truck).orderList.size() <= 1000
							&& address_end[0] == truck.destination) {
						this.showEntruckingReceiptVO(truck).orderList
								.add(order);
						order.order_state = OrderState.TRANSFERING;
						return OperationState.SUCCEED_OPERATION;
					}
				}
			}
		}
		waitingOrderList = awobl.updateWaitingList();
		logDiary.addLogDiary(new LogDiaryVO(getDate.getdate(), intermediate,
				"进行了装车分配操作"), getDate.getdate());

		return OperationState.FAIL_OPERATION;
	}

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane)
			throws Exception {
		// TODO 自动生成的方法存根
		for (EnplaningReceiptVO enplaningReceipt : enplaningReceiptList) {
			if (enplaningReceipt.plane.equals(plane))
				return enplaningReceipt;
		}
		throw new Exception("未找到该飞机的装车单！");
	}

	public ArrayList<EnplaningReceiptVO> showEnplaningReceiptList()
			throws Exception {
		// TODO 自动生成的方法存根
		return enplaningReceiptList;
	}

	public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train)
			throws Exception {
		// TODO 自动生成的方法存根
		for (EntrainingReceiptVO entrainingReceipt : entrainingReceiptList) {
			if (entrainingReceipt.train == train)
				return entrainingReceipt;
		}
		throw new Exception("未找到该火车的装车单！");
	}

	public ArrayList<EntrainingReceiptVO> showEntrainingReceiptList()
			throws Exception {
		// TODO 自动生成的方法存根
		return entrainingReceiptList;
	}

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck)
			throws Exception {
		// TODO 自动生成的方法存根
		for (EntruckingReceiptVO entruckingReceipt : entruckingReceiptList) {
			if (entruckingReceipt.truck == truck)
				return entruckingReceipt;
		}
		throw new Exception("未找到该汽车！");
	}

	public ArrayList<EntruckingReceiptVO> showEntruckingReceiptList()
			throws Exception {
		// TODO 自动生成的方法存根
		return entruckingReceiptList;
	}

	public OperationState updateMessage() {
		this.transferingReceipt = transfering.showTransferingReceipt();
		this.planeList = planeManager.showPlaneList();
		this.trainList = trainManeger.showTrainList();
		this.truckList = truckManager.showTruckList();

		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState saveEnIntermediateReceiptList()
			throws RemoteException {
		// TODO 自动生成的方法存根
		for (EnplaningReceiptPO enplaningReceipt : IntermediateMainController
				.voToPO_enplaningReceiptList(enplaningReceiptList))
			enList.add(enplaningReceipt);
		for (EntrainingReceiptPO entrainingReceipt : IntermediateMainController
				.voToPO_entrainingReceiptList(entrainingReceiptList))
			enList.add(entrainingReceipt);
		for (EntruckingReceiptPO entruckingReceipt : IntermediateMainController
				.voToPO_entruckingReceiptList(entruckingReceiptList))
			enList.add(entruckingReceipt);
		intermediateData.saveEnIntermediateReceiptInfo(enList,
				transferingReceipt.interdiateCentre.organizationID);
		return OperationState.SUCCEED_OPERATION;
	}

	public AllocateWaitingOrderBL getAllocateWwaitingOrderBL() {
		return awobl;
	}

	public ArrayList<OrderVO> getWaitingOrderList() {
		return waitingOrderList;
	}
}
