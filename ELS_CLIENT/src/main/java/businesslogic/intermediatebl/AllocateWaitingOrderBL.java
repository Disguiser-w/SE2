package businesslogic.intermediatebl;

import java.util.ArrayList;

import type.OrderState;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.AllocateWaitingOrderBLService;

public class AllocateWaitingOrderBL implements AllocateWaitingOrderBLService {
	private TransferingReceiptVO transferingReceipt;
	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();

	public AllocateWaitingOrderBL(TransferingReceiptVO transferingReceipt) {
		this.transferingReceipt = transferingReceipt;
	}

	public ArrayList<OrderVO> updateWaitingList() {
		// TODO 自动生成的方法存根
		if (transferingReceipt.orderList == null) {
			return new ArrayList<OrderVO>();
		} else {
			waitingOrderList = new ArrayList<OrderVO>();
			for (OrderVO order : transferingReceipt.orderList) {
				if (order.order_state == OrderState.WAITING_ENVEHICLE){
					waitingOrderList.add(order);
					System.out.println("what!?");
				}
			}
			return waitingOrderList;
		}
	}

	public OrderVO showOrder(String orderID) throws Exception {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID == orderID)
				return order;
		}

		throw new Exception("未找到该ID的订单！");
	}

	public void setTransferingReceipt(TransferingReceiptVO transferingReceipt) {
		this.transferingReceipt = transferingReceipt;
	}
}
