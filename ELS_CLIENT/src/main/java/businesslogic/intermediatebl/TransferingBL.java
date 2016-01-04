package businesslogic.intermediatebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import type.OperationState;
import type.OrderState;
import type.ReceiptState;
import vo.LogDiaryVO;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import vo.UserVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.LogDiaryBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.receiptbl.GetDate;
import businesslogicservice.intermediateblservice.TransferingBLService;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.intermediatedataservice.IntermediateDataService;

public class TransferingBL implements TransferingBLService {
	private TransferingReceiptVO transferingReceipt;
	private IntermediateDataService intermediateData;

	private UserVO intermediate;
	private LogDiaryBL logDiary;

	private ExpressDataService expressData;

	public TransferingBL(TransferingReceiptVO transferingReceipt,
			IntermediateDataService intermediateData, UserVO intermediate) {
		this.transferingReceipt = transferingReceipt;
		this.intermediateData = intermediateData;
		this.intermediate = intermediate;

		try {
			expressData = DataFactory.getExpressData();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		logDiary = new LogDiaryBL();
	}

	public TransferingReceiptVO showTransferingReceipt() {
		// TODO 自动生成的方法存根
		return transferingReceipt;
	}

	public String[] addOrder(String ID) throws RemoteException {
		// TODO 自动生成的方法存根
		if (expressData.find(ID) == null)
			return null;

		OrderVO order = IntermediateMainController.poToVO(expressData.find(ID));
		order.order_state = OrderState.WAITING_ENVEHICLE;
		transferingReceipt.orderList.add(order);
		logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(), intermediate,
				"在本中转中心新接收了一个快件"), GetDate.getTime());
		expressData.addHistory("订单已到达"
				+ transferingReceipt.interdiateCentre.name + ",正等待中转", null,
				order.ID);
		String state = "";
		switch (order.order_state) {
		case WAITING_ENVEHICLE:
			state = "等待装车";
			break;
		case TRANSFERING:
			state = "中转中";
			break;
		case WAITING_DISTRIBUTE:
			state = "等待派件";
			break;
		case DISTRIBUEING:
			state = "派件中";
			break;
		case FINISHED:
			state = "已完成";
			break;
		}
		String type = "";
		switch (order.expressType) {
		case ECONOMIC:
			type = "经济型";
			break;
		case FAST:
			type = "特快型";
			break;
		case STANDARD:
			type = "标准型";
			break;
		}
		return new String[] { ID, order.senderAddress, order.recipientAddress,
				state, type };
	}

	public OperationState deleteOrder(String ID) throws Exception {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID.equals(ID)) {
				transferingReceipt.orderList.remove(order);
				order.order_state = OrderState.TRANSFERING;
				logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(),
						intermediate, "在中转中心到达单中删除了一个快件"), GetDate.getTime());
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
		transferingReceipt.receiptState = ReceiptState.SUBMIT;
		intermediateData.saveTransferingReceiptInfo(
				IntermediateMainController.voToPO(transferingReceipt),
				transferingReceipt.interdiateCentre.organizationID);
		logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(), intermediate,
				"进行了保存中转中心到达单的操作"), GetDate.getTime());
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState changeState(OrderVO temp) {
		for (OrderVO vo : transferingReceipt.orderList) {
			if (vo.ID.equals(temp.ID)) {
				transferingReceipt.orderList.set(
						transferingReceipt.orderList.indexOf(vo), temp);
				System.out.println(temp.order_state.toString() + " state");
			}
		}
		return OperationState.SUCCEED_OPERATION;
	}
}
