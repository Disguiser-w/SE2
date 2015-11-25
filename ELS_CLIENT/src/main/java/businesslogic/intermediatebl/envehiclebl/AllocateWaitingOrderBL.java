package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.TransferingState;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.envehicleblservice.AllocateWaitingOrderBLService;

public class AllocateWaitingOrderBL implements AllocateWaitingOrderBLService {
	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();
	private TransferingReceiptVO transferingReceipt;

	public AllocateWaitingOrderBL(TransferingReceiptVO transferingReceipt) {
		this.transferingReceipt = transferingReceipt;
	}

	public ArrayList<OrderVO> updateWaitingList() {
		// TODO 自动生成的方法存根
		int size = transferingReceipt.orderList.size();
		for (int i = 0; i < size; i++) {
			if (transferingReceipt.orderList.get(i).transfer_state == TransferingState.WAITING_ENVEHICLE)
				waitingOrderList.add(transferingReceipt.orderList.get(i));
		}
		return waitingOrderList;
	}

	public OrderVO showOrder(String orderID)
			throws Exception {
		// TODO 自动生成的方法存根
		int size = transferingReceipt.orderList.size();
		for (int i = 0; i < size; i++) {
			if (transferingReceipt.orderList.get(i).ID == orderID)
				return transferingReceipt.orderList.get(i);
		}

		throw new Exception("未找到该ID的订单！");
	}

}
