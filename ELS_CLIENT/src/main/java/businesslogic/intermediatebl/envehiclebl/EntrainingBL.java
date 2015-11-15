package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import vo.EntrainingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TrainVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EntrainingBLService;

public class EntrainingBL implements EntrainingBLService {

	public ArrayList<TrainVO> showTrainList() {
		// TODO 自动生成的方法存根
		return null;
	}

	public TrainVO showTrain(String trainID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train) {
		// TODO 自动生成的方法存根
		return null;
	}

	public EntrainingReceiptVO entrain(ArrayList<OrderVO> al) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(
			EntrainingReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean showEntrainingReceiptList(ArrayList<EntrainingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return false;
	}

	public FareVO computeFare(ArrayList<EntrainingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		return false;
	}

	public boolean updateEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return false;
	}

}
