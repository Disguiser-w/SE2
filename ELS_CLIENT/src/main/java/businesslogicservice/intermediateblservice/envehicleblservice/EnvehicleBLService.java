package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import type.OperationState;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TruckVO;

public interface EnvehicleBLService {
	public OperationState envehicle() throws Exception;

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane)
			throws Exception;

	public OperationState saveEnplaningReceiptList();

	public ArrayList<EnplaningReceiptVO> showEnplaningReceiptList()
			throws Exception;

	public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train)
			throws Exception;

	public OperationState saveEntrainingReceiptList();

	public ArrayList<EntrainingReceiptVO> showEntrainingReceiptList()
			throws Exception;

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck)
			throws Exception;

	public OperationState saveEntruckingReceiptList();

	public ArrayList<EntruckingReceiptVO> showEntruckingReceiptList()
			throws Exception;

	public OperationState updateMessage();
}
