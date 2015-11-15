package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntrainingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;

public interface EntrainingBLService {
	public ArrayList<TrainVO> showTrainList();

	public TrainVO showTrain(String trainID);

	public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train);

	public EntrainingReceiptVO entrain(ArrayList<OrderVO> al);

	public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(
			EntrainingReceiptVO vo);

	public boolean showEntrainingReceiptList(ArrayList<EntrainingReceiptVO> vo);

	public FareVO computeFare(ArrayList<EntrainingReceiptVO> vo);

	public boolean updateFare(FareVO fareVO);

	public boolean updateEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo);
}
