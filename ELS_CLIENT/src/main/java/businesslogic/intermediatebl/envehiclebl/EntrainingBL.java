package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.OrderState;
import vo.EntrainingReceiptVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EntrainingBLService;

public class EntrainingBL implements EntrainingBLService {
	private TransferingReceiptVO transferingReceipt;
//	private FareVO fare;
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
		for (TrainVO train : trainList) {
			if (train.ID == trainID)
				return train;
		}

		throw new Exception("未找到该ID的火车！");
	}

	public EntrainingReceiptVO showEntrainingReceiptVO(TrainVO train)
			throws Exception {
		// TODO 自动生成的方法存根
		for (EntrainingReceiptVO entrainingReceipt : entrainingReceipt_all) {
			if (entrainingReceipt.train == train)
				return entrainingReceipt;
		}
		throw new Exception("未找到该火车！");
	}

	public void entrain(ArrayList<OrderVO> waitingOrderList) throws Exception {
		// TODO 自动生成的方法存根
		waitingOrderList = awobl.updateWaitingList();

		for (OrderVO order : waitingOrderList) {
			String[] address = order.recipientAddress.split(" ");
			for (TrainVO train:trainList) {
				if (address[0] ==train.destination) {
					showEntrainingReceiptVO(train).orderList
							.add(order);
					order.order_state = OrderState.WAITING_ENVEHICLE;
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

	public ArrayList<EntrainingReceiptVO> showEntrainingReceiptList() throws Exception {
		// TODO 自动生成的方法存根
		for (TrainVO train:trainList) {
			entrainingReceipt_all.add(showEntrainingReceiptVO(train));
		}
		return entrainingReceipt_all;
	}

//	public FareVO computeFare(
//			ArrayList<EntrainingReceiptVO> entrainingReceipt_all,
//			OrganizationVO intermediateCentre) {
//		// TODO 自动生成的方法存根
//		int train_num = entrainingReceipt_all.size();
//		double fare_sum = 0;
//		for (int i = 0; i < train_num; i++)
//			fare_sum += entrainingReceipt_all.get(i).fare;
//		fare = new FareVO(null, entrainingReceipt_all, null, fare_sum, null,
//				null, intermediateCentre);
//		return fare;
//	}
//
//	public boolean updateFare(FareVO fareVO) {
//		// TODO 自动生成的方法存根
//		return false;
//	}

	public boolean updateEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return false;
	}

	public OrganizationVO getIntemediateCentre() {
		return intemediateCentre;
	}

	public void setIntemediateCentre(OrganizationVO intemediateCentre) {
		this.intemediateCentre = intemediateCentre;
	}

	public ArrayList<OrderVO> getWaitingOrderList() {
		return waitingOrderList;
	}

	public void setWaitingOrderList(ArrayList<OrderVO> waitingOrderList) {
		this.waitingOrderList = waitingOrderList;
	}

}
