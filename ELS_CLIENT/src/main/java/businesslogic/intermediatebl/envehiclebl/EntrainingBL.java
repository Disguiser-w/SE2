package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.TransferingState;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EntrainingBLService;

public class EntrainingBL implements EntrainingBLService {
	private TransferingReceiptVO transferingReceipt;
	private FareVO fare;
	private OrganizationVO intemediateCentre;

	private AllocateWaitingOrderBL awobl = new AllocateWaitingOrderBL(
			transferingReceipt);

	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();
	private ArrayList<EntrainingReceiptVO> entrainingReceipt_all = new ArrayList<EntrainingReceiptVO>();

	public EntrainingBL(ArrayList<TrainVO> trainList,
			TransferingReceiptVO transferingReceipt) {
		this.trainList = trainList;
		this.transferingReceipt = transferingReceipt;
	}

	public ArrayList<TrainVO> showTrainList() {
		// TODO 自动生成的方法存根
		return trainList;
	}

	public TrainVO showTrain(String trainID) throws Exception {
		// TODO 自动生成的方法存根
		int size = trainList.size();
		for (int i = 0; i < size; i++) {
			if (trainList.get(i).ID == trainID)
				return trainList.get(i);
		}

		throw new Exception("未找到该ID的火车！");
	}

	public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train) {
		// TODO 自动生成的方法存根
		return train.entrainingReceipt;
	}

	public void entrain(ArrayList<OrderVO> waitingOrderList) {
		// TODO 自动生成的方法存根
		waitingOrderList = awobl.updateWaitingList();

		int order_num = waitingOrderList.size();
		int train_num = trainList.size();
		for (int i = 0; i < order_num; i++) {
			String[] address = waitingOrderList.get(i).recipientAddress
					.split(" ");
			for (int j = 0; j < train_num; j++) {
				if (address[0] == trainList.get(j).destination) {
					trainList.get(j).entrainingReceipt.entrainingReceipt
							.add(waitingOrderList.get(i));
					waitingOrderList.get(i).transfer_state = TransferingState.FINISHED_ENVEHICLE;
					continue;
				}
			}
		}
	}

	public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(
			EntrainingReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<EntrainingReceiptVO> showEntrainingReceiptList() {
		// TODO 自动生成的方法存根
		int train_num = trainList.size();
		for (int i = 0; i < train_num; i++) {
			entrainingReceipt_all.add(trainList.get(i).entrainingReceipt);
		}
		return entrainingReceipt_all;
	}

	public FareVO computeFare(
			ArrayList<EntrainingReceiptVO> entrainingReceipt_all,
			OrganizationVO intermediateCentre) {
		// TODO 自动生成的方法存根
		int train_num = entrainingReceipt_all.size();
		double fare_sum = 0;
		for(int i = 0;i<train_num;i++)
			fare_sum+=entrainingReceipt_all.get(i).fare;
		fare = new FareVO(null, entrainingReceipt_all, null, fare_sum, null, null, intermediateCentre);
		return fare;
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
