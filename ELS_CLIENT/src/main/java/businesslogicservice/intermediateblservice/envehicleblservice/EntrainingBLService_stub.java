package businesslogicservice.intermediateblservice.envehicleblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.OperationState;
import vo.EntrainingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;

public class EntrainingBLService_stub implements TrainManagerBLService {

	@Override
	public ArrayList<TrainVO> showTrainList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationState addTrain(String ID, String destination) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationState deleteTrain(String train_ID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationState modifyTrain(TrainVO train_modify) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrainVO showTrain(String train_ID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationState saveTrainList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	// public ArrayList<TrainVO> showTrainList() {
	// // TODO 自动生成的方法存根
	// System.out.println("show successfully!");
	// return null;
	// }
	//
	// public TrainVO showTrain(String ID) {
	// // TODO 自动生成的方法存根
	// System.out.println("show successfully!");
	// return null;
	// }
	//
	// public ArrayList<OrderVO> updateWaitingList(TransferingReceiptVO vo) {
	// // TODO 自动生成的方法存根
	// System.out.println("update successfully!");
	// return null;
	// }
	//
	// public EntrainingReceiptVO entrain(ArrayList<OrderVO> al) {
	// // TODO 自动生成的方法存根
	// System.out.println("entrain successfully!");
	// return null;
	// }
	//
	// public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(
	// EntrainingReceiptVO vo) {
	// System.out.println("update successfully!");
	// // TODO 自动生成的方法存根
	// return null;
	// }
	//
	// public FareVO computeFare(ArrayList<EntrainingReceiptVO> vo) {
	// // TODO 自动生成的方法存根
	// System.out.println("compute successfully!");
	// return null;
	// }
	//
	// public boolean updateFare(FareVO fareVO) {
	// // TODO 自动生成的方法存根
	// System.out.println("update successfully!");
	// return false;
	// }
	//
	// public boolean showEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo) {
	// // TODO 自动生成的方法存根
	// System.out.println("show successfully!");
	// return false;
	// }
	//
	// public boolean updateEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo)
	// {
	// // TODO 自动生成的方法存根
	// System.out.println("update successfully!");
	// return false;
	// }
	//
	// public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train) {
	// // TODO 自动生成的方法存根
	// return null;
	// }
	//
	// public boolean showEntrainingReceiptList(ArrayList<EntrainingReceiptVO>
	// vo) {
	// // TODO 自动生成的方法存根
	// return false;
	// }
	//
	// @Override
	// public OperationState addTrain(String ID, String destination) {
	// // TODO 自动生成的方法存根
	// return null;
	// }
	//
	// @Override
	// public OperationState deleteTrain(TrainVO train_delete) throws Exception
	// {
	// // TODO 自动生成的方法存根
	// return null;
	// }
	//
	// @Override
	// public OperationState modifyTrain(TrainVO train_modify) throws Exception
	// {
	// // TODO 自动生成的方法存根
	// return null;
	// }
	//
	// @Override
	// public OperationState saveTrainList() throws RemoteException {
	// // TODO 自动生成的方法存根
	// return null;
	// }
}
