package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.TransferingState;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EntruckingBLService;

public class EntruckingBL implements EntruckingBLService {
	private TransferingReceiptVO transferingReceipt;
	private FareVO fare;
	private OrganizationVO intemediateCentre;

	private AllocateWaitingOrderBL awobl = new AllocateWaitingOrderBL(
			transferingReceipt);

	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();
	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();
	private ArrayList<EntruckingReceiptVO> entruckingReceipt_all = new ArrayList<EntruckingReceiptVO>();

	public EntruckingBL(ArrayList<TruckVO> truckList,
			TransferingReceiptVO transferingReceipt) {
		this.truckList = truckList;
		this.transferingReceipt = transferingReceipt;
	}

	public ArrayList<TruckVO> showTruckList() {
		// TODO 自动生成的方法存根
		return truckList;
	}

	public TruckVO showTruck(String truckID) throws Exception{
		// TODO 自动生成的方法存根
		int size = truckList.size();
		for (int i = 0; i < size; i++) {
			if (truckList.get(i).ID == truckID)
				return truckList.get(i);
		}

		throw new Exception("未找到该ID的汽车！");
	}

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck) {
		// TODO 自动生成的方法存根
		return truck.entruckingReceipt;
	}

	public void entruck(ArrayList<OrderVO> waitingOrderList) {
		// TODO 自动生成的方法存根
		waitingOrderList = awobl.updateWaitingList();

		int order_num = waitingOrderList.size();
		int truck_num = truckList.size();
		for (int i = 0; i < order_num; i++) {
			String[] address = waitingOrderList.get(i).recipientAddress
					.split(" ");
			for (int j = 0; j < truck_num; j++) {
				if (address[0] == truckList.get(j).destination) {
					truckList.get(j).entruckingReceipt.entruckingReceipt
							.add(waitingOrderList.get(i));
					waitingOrderList.get(i).transfer_state = TransferingState.FINISHED_ENVEHICLE;
					continue;
				}
			}
		}
	}

	public ArrayList<EntruckingReceiptVO> updateEntruckingReceiptList(
			EntruckingReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<EntruckingReceiptVO> showEntruckingReceiptList() {
		// TODO 自动生成的方法存根
		int truck_num = truckList.size();
		for (int i = 0; i < truck_num; i++) {
			entruckingReceipt_all.add(truckList.get(i).entruckingReceipt);
		}
		return entruckingReceipt_all;
	}

	public FareVO computeFare(ArrayList<EntruckingReceiptVO> vo,
			OrganizationVO intermediateCentre) {
		// TODO 自动生成的方法存根
		int truck_num = entruckingReceipt_all.size();
		double fare_sum = 0;
		for(int i = 0;i<truck_num;i++)
			fare_sum+=entruckingReceipt_all.get(i).fare;
		fare = new FareVO(null, null, entruckingReceipt_all, fare_sum, null, null, intermediateCentre);
		return fare;
	}

	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		return false;
	}

	public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return false;
	}

}
