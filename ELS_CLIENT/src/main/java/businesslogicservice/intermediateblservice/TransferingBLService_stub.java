package businesslogicservice.intermediateblservice;

import java.rmi.RemoteException;

import type.OperationState;
import vo.OrderVO;
import vo.TransferingReceiptVO;

public class TransferingBLService_stub implements TransferingBLService {

	public TransferingReceiptVO showTranferingReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return null;
	}

	public OperationState addOrder(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("add successfully!");
		return null;
	}

	public OperationState deleteOrder(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("delete successfully!");
		return null;
	}

	public boolean modifyOrder(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("modify successfully!");
		return true;
	}

	public void updateTranferingReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
	}

	public TransferingReceiptVO showTransferingReceipt() {
		// TODO Auto-generated method stub
		return null;
	}

	public TransferingReceiptVO updateTransferingReceipt(TransferingReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public OperationState modifyOrder(OrderVO order) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	public OperationState saveTransferingReceipt() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

}
