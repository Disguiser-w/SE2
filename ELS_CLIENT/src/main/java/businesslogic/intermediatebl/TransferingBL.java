package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.OperationState;
import type.OrderState;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.intermediatebl.envehiclebl.EnvehicleBL;
import businesslogicservice.intermediateblservice.TransferingBLService;

public class TransferingBL implements TransferingBLService {
	private EnvehicleBL envehicle;

	private TransferingReceiptVO transferingReceipt;

	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();

	public TransferingBL(ArrayList<PlaneVO> planeList,
			ArrayList<TrainVO> trainList, ArrayList<TruckVO> truckList) {
		this.planeList = planeList;
		this.trainList = trainList;
		this.truckList = truckList;
		envehicle = new EnvehicleBL(transferingReceipt, planeList, trainList,
				truckList);
	}

	// 显示（返回）中转中心到达单
	public TransferingReceiptVO showTransferingReceipt() {
		// TODO 自动生成的方法存根
		return transferingReceipt;
	}

	public OperationState addOrder(String ID) throws RemoteException {
		// TODO 自动生成的方法存根
		OrderVO order = IntermediateMainController
				.poToVO(ExpressMainController.expressData.find(ID));
		order.order_state = OrderState.WAITING_ENVEHICLE;
		transferingReceipt.orderList.add(order);
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deleteOrder(String ID) throws Exception {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID == ID) {
				transferingReceipt.orderList.remove(order);
				order.order_state = OrderState.TRANSFERING;
				// voToPO后，将po存入序列化文件中
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("删除失败!");
	}

	public OperationState modifyOrder(OrderVO order_temp) throws Exception {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID == order_temp.ID) {
				transferingReceipt.orderList
						.set(transferingReceipt.orderList.indexOf(order),
								order_temp);
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("修改失败!");
	}

	public OperationState updateTransferingReceipt(TransferingReceiptVO vo) {
		// TODO 自动生成的方法存根
		return OperationState.SUCCEED_OPERATION;
	}
}
