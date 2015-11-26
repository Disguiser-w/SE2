package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.TransferingState;
import vo.EntruckingReceiptVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EntruckingBLService;

public class EntruckingBL implements EntruckingBLService {
	private TransferingReceiptVO transferingReceipt;
//	private FareVO fare;
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

	public TruckVO showTruck(String truckID) throws Exception {
		// TODO 自动生成的方法存根
		for (TruckVO truck : truckList) {
			if (truck.ID == truckID)
				return truck;
		}

		throw new Exception("未找到该ID的汽车！");
	}

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck)
			throws Exception {
		// TODO 自动生成的方法存根
		for (EntruckingReceiptVO entruckingReceipt : entruckingReceipt_all) {
			if (entruckingReceipt.truck == truck)
				return entruckingReceipt;
		}
		throw new Exception("未找到该汽车！");
	}

	public void entruck(ArrayList<OrderVO> waitingOrderList) throws Exception {
		// TODO 自动生成的方法存根
		waitingOrderList = awobl.updateWaitingList();

		for (OrderVO order : waitingOrderList) {
			String[] address = order.recipientAddress.split(" ");
			for (TruckVO truck : truckList) {
				if (address[0] == truck.destination) {
					showEntruckingReceiptVO(truck).orderList.add(order);
					order.transfer_state = TransferingState.FINISHED_ENVEHICLE;
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

	public ArrayList<EntruckingReceiptVO> showEntruckingReceiptList() throws Exception {
		// TODO 自动生成的方法存根
		for (TruckVO truck:truckList) {
			entruckingReceipt_all.add(showEntruckingReceiptVO(truck));
		}
		return entruckingReceipt_all;
	}

//	public FareVO computeFare(ArrayList<EntruckingReceiptVO> vo,
//			OrganizationVO intermediateCentre) {
//		// TODO 自动生成的方法存根
//		int truck_num = entruckingReceipt_all.size();
//		double fare_sum = 0;
//		for (int i = 0; i < truck_num; i++)
//			fare_sum += entruckingReceipt_all.get(i).fare;
//		fare = new FareVO(null, null, entruckingReceipt_all, fare_sum, null,
//				null, intermediateCentre);
//		return fare;
//	}
//
//	public boolean updateFare(FareVO fareVO) {
//		// TODO 自动生成的方法存根
//		return false;
//	}

	public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo) {
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
