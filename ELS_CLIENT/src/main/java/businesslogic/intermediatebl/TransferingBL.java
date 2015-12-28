package businesslogic.intermediatebl;

import java.rmi.RemoteException;

import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogicservice.intermediateblservice.TransferingBLService;
import dataservice.intermediatedataservice.IntermediateDataService;
import type.OperationState;
import type.OrderState;
import vo.IntermediateVO;
import vo.LogDiaryVO;
import vo.OrderVO;
import vo.TransferingReceiptVO;

public class TransferingBL implements TransferingBLService {
	private TransferingReceiptVO transferingReceipt;
	private IntermediateDataService intermediateData;

	private IntermediateVO intermediate;
	private LogDiaryBL logDiary;

	public TransferingBL(TransferingReceiptVO transferingReceipt,
			IntermediateDataService intermediateData,
			IntermediateVO intermediate) {
		this.transferingReceipt = transferingReceipt;
		this.intermediateData = intermediateData;
		this.intermediate = intermediate;

		logDiary = new LogDiaryBL();
	}

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
		logDiary.addLogDiary(new LogDiaryVO(getDate.getdate(), intermediate,
				"在本中转中心新接收了一个快件"), getDate.getdate());
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deleteOrder(String ID) throws Exception {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID.equals(ID)) {
				transferingReceipt.orderList.remove(order);
				order.order_state = OrderState.TRANSFERING;
				logDiary.addLogDiary(new LogDiaryVO(getDate.getdate(),
						intermediate, "在中转中心到达单中删除了一个快件"), getDate.getdate());
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("删除失败!");
	}

	public OperationState modifyOrder(OrderVO order_temp) throws Exception {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID.equals(order_temp.ID)) {
				transferingReceipt.orderList
						.set(transferingReceipt.orderList.indexOf(order),
								order_temp);
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("修改失败!");
	}

	public OperationState saveTransferingReceipt() throws RemoteException {
		// TODO 自动生成的方法存根
		intermediateData.saveTransferingReceiptInfo(
				IntermediateMainController.voToPO(transferingReceipt),
				transferingReceipt.interdiateCentre.organizationID);
		logDiary.addLogDiary(new LogDiaryVO(getDate.getdate(), intermediate,
				"进行了保存中转中心到达单的操作"), getDate.getdate());
		return OperationState.SUCCEED_OPERATION;
	}
}
