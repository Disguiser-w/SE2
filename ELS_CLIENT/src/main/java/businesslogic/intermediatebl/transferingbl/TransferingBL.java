package businesslogic.intermediatebl.transferingbl;

import type.OperationState;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.transferingblservice.TransferingBLService;

public class TransferingBL implements TransferingBLService{
	private TransferingReceiptVO transferingReceipt;

	public TransferingReceiptVO showTransferingReceipt() {
		// TODO 自动生成的方法存根
		return transferingReceipt;
	}

	public boolean addOrder(String ID) {
		// TODO 自动生成的方法存根
		// 从序列化文件中得到需要加载的Ordervo信息的方法
		return false;
	}

	public OperationState deleteOrder(String ID) throws Exception {
		// TODO 自动生成的方法存根
		int size = transferingReceipt.orderList.size();
		for(int i = 0;i<size;i++){
			if(transferingReceipt.orderList.get(i).ID == ID){
				OrderVO deleteOrder = transferingReceipt.orderList.get(i);
			    transferingReceipt.orderList.remove(i);
			    // 货物中转接收状态是否需要设置
			    return OperationState.SUCCEED_OPERATION; 
			}
		}
		
		throw new Exception("删除失败!");
	}

	public boolean modifyOrder(String ID) {
		// TODO 自动生成的方法存根
		for(OrderVO order:transferingReceipt.orderList){
			if(order.ID == ID){
				OrderVO deleteOrder = order;
				//修改信息传入
			}
		}
		return false;
	}

	public TransferingReceiptVO updateTransferingReceipt(TransferingReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

}
