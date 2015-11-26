package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TransferingReceiptVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EnplaningBLService;

public class EnplaningBL implements EnplaningBLService {
	private TransferingReceiptVO transferingReceipt;
	// private OrganizationVO intemediateCentre;

//	private AllocateWaitingOrderBL awobl = new AllocateWaitingOrderBL(
//			transferingReceipt);

	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	// private ArrayList<OrderVO> waitingOrderList = new ArrayList<OrderVO>();
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
		for (PlaneVO plane : planeList) {
			if (plane.ID == planeID)
				return plane;
		}

		throw new Exception("未找到该ID的飞机！");
	}

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane)
			throws Exception {
		// TODO 自动生成的方法存根
		for (EnplaningReceiptVO enplaningReceipt : enplaningReceipt_all) {
			if (enplaningReceipt.plane == plane)
				return enplaningReceipt;
		}
		throw new Exception("未找到该飞机的装车单！");
	}

	public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(
			EnplaningReceiptVO vo) {
		// TODO 自动生成的方法存根
		enplaningReceipt_all.add(vo);
		return enplaningReceipt_all;
	}

	public ArrayList<EnplaningReceiptVO> showEnplaningReceiptList()
			throws Exception {
		// TODO 自动生成的方法存根
		for (PlaneVO plane : planeList) {
			enplaningReceipt_all.add(showEnplaningReceipt(plane));
		}
		return enplaningReceipt_all;
	}

//	public FareVO computeFare(
//			ArrayList<EnplaningReceiptVO> enplaningReceipt_all,
//			OrganizationVO intermediateCentre) {
//		// TODO 自动生成的方法存根
//		int plane_num = enplaningReceipt_all.size();
//		double fare_sum = 0;
//		for (int i = 0; i < plane_num; i++)
//			fare_sum += enplaningReceipt_all.get(i).fare;
//		fare = new FareVO(enplaningReceipt_all, null, null, fare_sum, null,
//				null, intermediateCentre);
//		return fare;
//	}
//
//	public boolean updateFare(FareVO fareVO) {
//		// TODO 自动生成的方法存根
//		
//		return false;
//	}

	public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void enplane(ArrayList<OrderVO> al) throws Exception {
		// TODO 自动生成的方法存根
		
	}

}
