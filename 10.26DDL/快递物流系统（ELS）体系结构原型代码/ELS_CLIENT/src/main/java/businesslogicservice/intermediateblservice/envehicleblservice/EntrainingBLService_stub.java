package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntrainingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;

public class EntrainingBLService_stub implements EntrainingBLService {
	public ArrayList<TrainVO> showTrainList() {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return null;
	}
	public TrainVO showTrain(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return null;
	}
	public ArrayList<OrderVO> updateWaitingList(TransferingReceiptVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}
	public EntrainingReceiptVO entrain(ArrayList<OrderVO> al) {
		// TODO 自动生成的方法存根
		System.out.println("entrain successfully!");
		return null;
	}
	public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(
			EntrainingReceiptVO vo) {
		System.out.println("update successfully!");
		// TODO 自动生成的方法存根
		return null;
	}
	public FareVO computeFare(ArrayList<EntrainingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		System.out.println("compute successfully!");
		return null;
	}
	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return false;
	}
	public boolean showEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return false;
	}
	public boolean updateEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return false;
	}
}
