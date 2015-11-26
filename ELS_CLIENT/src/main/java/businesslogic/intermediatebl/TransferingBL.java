package businesslogic.intermediatebl;

import java.rmi.RemoteException;

import type.OperationState;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogicservice.intermediateblservice.TransferingBLService;

public class TransferingBL implements TransferingBLService {
	private TransferingReceiptVO transferingReceipt;

	// 显示（返回）中转中心到达单
	public TransferingReceiptVO showTransferingReceipt() {
		// TODO 自动生成的方法存根
		return transferingReceipt;
	}

	public OperationState addOrder(String ID) throws RemoteException {
		// TODO 自动生成的方法存根
		transferingReceipt.orderList.add(IntermediateMainController
				.poToVO(ExpressMainController.expressData.find(ID)));
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deleteOrder(String ID) throws Exception {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID == ID) {
				transferingReceipt.orderList.remove(order);
				// 货物中转接收状态是否需要设置
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("删除失败!");
	}

	public OperationState modifyOrder(String ID) {
		// TODO 自动生成的方法存根
		for (OrderVO order : transferingReceipt.orderList) {
			if (order.ID == ID) {
				OrderVO modifyOrder = order;
				// 修改信息传入
			}
		}
		return OperationState.SUCCEED_OPERATION;
	}

	public TransferingReceiptVO updateTransferingReceipt(TransferingReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

}
