package businesslogic.intermediatebl;

import java.util.ArrayList;

import type.OrderState;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.envehicleblservice.AllocateWaitingOrderBLService;

public class AllocateWaitingOrderBL implements AllocateWaitingOrderBLService {
	private TransferingReceiptVO transferingReceipt;
	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();

	public AllocateWaitingOrderBL(TransferingReceiptVO transferingReceipt) {
		this.transferingReceipt = transferingReceipt;
	}

	public ArrayList<OrderVO> updateWaitingList() {
		// TODO 自动生成的方法存根
		if (transferingReceipt.orderList == null) {
			System.out.println("jiji");
			return new ArrayList<OrderVO>();
		} else {
			for (OrderVO order : transferingReceipt.orderList) {
//				if (order.order_state == OrderState.TRANSFERING)
					waitingOrderList.add(order);
			}
			System.out.println("gaga");
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
