package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.ExpressType;
import type.OperationState;
import type.OrderState;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;
import businesslogic.managebl.CityDistanceBL;
import dataservice.intermediatedataservice.envehicledataservice.EnvehicleBLService;

public class EnvehicleBL implements EnvehicleBLService {
	private AllocateWaitingOrderBL awobl;
	private CityDistanceBL cdbl;
	private EnplaningBL enplane;
	private EntrainingBL entrain;
	private EntruckingBL entruck;

	private TransferingReceiptVO transferingReceipt;
	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();
	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();

	private final double STANDARD_PLANE = 200;
	private final double ECONOMIC_TRAIN = 200;
	private final double ECONOMIC_PLANE = 500;

	public EnvehicleBL(TransferingReceiptVO transferingReceipt,
			ArrayList<PlaneVO> planeList, ArrayList<TrainVO> trainList,
			ArrayList<TruckVO> truckList) {
		this.transferingReceipt = transferingReceipt;
		this.planeList = planeList;
		this.trainList = trainList;
		this.truckList = truckList;
	}

	public OperationState envehicle() throws Exception {
		// TODO 自动生成的方法存根
		awobl = new AllocateWaitingOrderBL(transferingReceipt);
		cdbl = new CityDistanceBL();
		enplane = new EnplaningBL(planeList, transferingReceipt);
		entrain = new EntrainingBL(trainList, transferingReceipt);
		entruck = new EntruckingBL(truckList, transferingReceipt);
		waitingOrderList = awobl.updateWaitingList();

		for (OrderVO order : waitingOrderList) {
			String[] address_end = order.recipientAddress.split(" ");
			String[] address_start = order.senderAddress.split(" ");
			double distance = cdbl.findCityDistance(address_start[0],
					address_end[0]);
			if (order.expressType == ExpressType.FAST
					|| (order.expressType == ExpressType.STANDARD && distance > STANDARD_PLANE)
					|| (order.expressType == ExpressType.ECONOMIC && distance > ECONOMIC_PLANE)) {
				for (PlaneVO plane : planeList) {
					if ((enplane.showEnplaningReceipt(plane).orderList.size() <= 2000)
							&& address_end[0] == plane.destination) {
						enplane.showEnplaningReceipt(plane).orderList
								.add(order);
						order.order_state = OrderState.TRANSFERING;
						return OperationState.SUCCEED_OPERATION;
					}
				}
			} else if ((order.expressType == ExpressType.STANDARD && distance <= STANDARD_PLANE)
					|| (order.expressType == ExpressType.ECONOMIC
							&& distance <= ECONOMIC_PLANE && distance > ECONOMIC_TRAIN)) {
				for (TrainVO train : trainList) {
					if ((entrain.showEntrainingReceiptVO(train).orderList
							.size() <= 200000)
							&& address_end[0] == train.destination) {
						entrain.showEntrainingReceiptVO(train).orderList
								.add(order);
						order.order_state = OrderState.TRANSFERING;
						return OperationState.SUCCEED_OPERATION;
					}
				}
			} else if (order.expressType == ExpressType.ECONOMIC
					&& distance <= ECONOMIC_TRAIN) {
				for (TruckVO truck : truckList) {
					if (entruck.showEntruckingReceiptVO(truck).orderList.size() <= 1000
							&& address_end[0] == truck.destination) {
						entruck.showEntruckingReceiptVO(truck).orderList
								.add(order);
						order.order_state = OrderState.TRANSFERING;
						return OperationState.SUCCEED_OPERATION;
					}
				}
			}
		}

		return OperationState.FAIL_OPERATION;
	}
}
