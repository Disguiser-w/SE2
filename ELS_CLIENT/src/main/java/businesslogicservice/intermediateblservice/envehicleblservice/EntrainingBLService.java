package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntrainingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.TrainVO;

public interface EntrainingBLService {
	public ArrayList<TrainVO> showTrainList();

	public TrainVO showTrain(String trainID) throws Exception;

	public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train) throws Exception;

	public void entrain(ArrayList<OrderVO> al) throws Exception;

	public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(
			EntrainingReceiptVO vo);

	public ArrayList<EntrainingReceiptVO> showEntrainingReceiptList() throws Exception;

	public FareVO computeFare(ArrayList<EntrainingReceiptVO> vo,OrganizationVO intermediate);

	public boolean updateFare(FareVO fareVO);

	public boolean updateEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo);
}
