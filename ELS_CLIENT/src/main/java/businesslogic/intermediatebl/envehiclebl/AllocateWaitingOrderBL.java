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
		for(OrderVO order:transferingReceipt.orderList) {
			if (order.transfer_state == TransferingState.WAITING_ENVEHICLE)
				waitingOrderList.add(order);
		}
		return waitingOrderList;
	}

	public OrderVO showOrder(String orderID)
			throws Exception {
		// TODO 自动生成的方法存根
		for(OrderVO order:transferingReceipt.orderList){
			if (order.ID == orderID)
				return order;
		}

		throw new Exception("未找到该ID的订单！");
	}

}
