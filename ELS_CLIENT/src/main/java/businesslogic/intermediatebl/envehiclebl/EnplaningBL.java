package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.TransferingState;
import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.PlaneVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EnplaningBLService;

public class EnplaningBL implements EnplaningBLService {
	private TransferingReceiptVO transferingReceipt;
	private FareVO fare;
	private OrganizationVO intemediateCentre;

	private AllocateWaitingOrderBL awobl = new AllocateWaitingOrderBL(
			transferingReceipt);

	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();
	private ArrayList<EnplaningReceiptVO> enplaningReceipt_all = new ArrayList<EnplaningReceiptVO>();

	public EnplaningBL(ArrayList<PlaneVO> planeList,
			TransferingReceiptVO transferingReceipt) {
		this.planeList = planeList;
		this.transferingReceipt = transferingReceipt;
	}

	public ArrayList<PlaneVO> showPlaneList() {
		// TODO 自动生成的方法存根
		return planeList;
	}

	public PlaneVO showPlane(String planeID) throws Exception {
		// TODO 自动生成的方法存根
		int size = planeList.size();
		for (int i = 0; i < size; i++) {
			if (planeList.get(i).ID == planeID)
				return planeList.get(i);
		}

		throw new Exception("未找到该ID的飞机！");
	}

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane) {
		// TODO 自动生成的方法存根
		return plane.enplaningReceipt;
	}

	public void enplane(ArrayList<OrderVO> waitingOrderList) {
		// TODO 自动生成的方法存根
		waitingOrderList = awobl.updateWaitingList();

		int order_num = waitingOrderList.size();
		int plane_num = planeList.size();
		for (int i = 0; i < order_num; i++) {
			String[] address = waitingOrderList.get(i).recipientAddress
					.split(" ");
			for (int j = 0; j < plane_num; j++) {
				if (address[0] == planeList.get(j).destination) {
					planeList.get(j).enplaningReceipt.enplaningReceipt
							.add(waitingOrderList.get(i));
					waitingOrderList.get(i).transfer_state = TransferingState.FINISHED_ENVEHICLE;
					continue;
				}
			}
		}
	}

	public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(
			EnplaningReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<EnplaningReceiptVO> showEnplaningReceiptList() {
		// TODO 自动生成的方法存根
		int plane_num = planeList.size();
		for (int i = 0; i < plane_num; i++) {
			enplaningReceipt_all.add(planeList.get(i).enplaningReceipt);
		}
		return enplaningReceipt_all;
	}

	public FareVO computeFare(
			ArrayList<EnplaningReceiptVO> enplaningReceipt_all,
			OrganizationVO intermediateCentre) {
		// TODO 自动生成的方法存根
		int plane_num = enplaningReceipt_all.size();
		double fare_sum = 0;
		for(int i = 0;i<plane_num;i++)
			fare_sum+=enplaningReceipt_all.get(i).fare;
		fare = new FareVO(enplaningReceipt_all, null, null, fare_sum, null, null, intermediateCentre);
		return fare;
	}

	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		return false;
	}

	public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		return false;
	}
}
