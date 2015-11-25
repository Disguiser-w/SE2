package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import po.EnplaningReceiptPO;
import po.OrderPO;
import po.OrganizationPO;
import po.PlanePO;
import type.TransferingState;
import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.PlaneVO;
import vo.TransferingReceiptVO;
import businesslogic.expressbl.AddOrder;
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
		for (PlaneVO plane : planeList) {
			if (plane.ID == planeID)
				return plane;
		}

		throw new Exception("未找到该ID的飞机！");
	}

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane) throws Exception {
		// TODO 自动生成的方法存根
		for(EnplaningReceiptVO enplaningReceipt:enplaningReceipt_all){
			if(enplaningReceipt.plane == plane)
				return enplaningReceipt;
		}
		throw new Exception("未找到该飞机！");
	}

	public void enplane(ArrayList<OrderVO> waitingOrderList) throws Exception {
		// TODO 自动生成的方法存根
		waitingOrderList = awobl.updateWaitingList();

		for (OrderVO order : waitingOrderList) {
			String[] address_city = order.recipientAddress.split(" ");
			for (PlaneVO plane : planeList) {
				if (address_city[0] == plane.destination) {
					showEnplaningReceipt(plane).orderList.add(order);
					order.transfer_state = TransferingState.FINISHED_ENVEHICLE;
					continue;
				}
			}
		}
	}

	public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(
			EnplaningReceiptVO vo) {
		// TODO 自动生成的方法存根
		enplaningReceipt_all.add(vo);
		return enplaningReceipt_all;
	}

	public ArrayList<EnplaningReceiptVO> showEnplaningReceiptList() throws Exception {
		// TODO 自动生成的方法存根
		int plane_num = planeList.size();
		for (PlaneVO plane : planeList) {
			enplaningReceipt_all.add(showEnplaningReceipt(plane));
		}
		return enplaningReceipt_all;
	}

	public FareVO computeFare(
			ArrayList<EnplaningReceiptVO> enplaningReceipt_all,
			OrganizationVO intermediateCentre) {
		// TODO 自动生成的方法存根
		int plane_num = enplaningReceipt_all.size();
		double fare_sum = 0;
		for (int i = 0; i < plane_num; i++)
			fare_sum += enplaningReceipt_all.get(i).fare;
		fare = new FareVO(enplaningReceipt_all, null, null, fare_sum, null,
				null, intermediateCentre);
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

	public static OrderPO voToPO(OrderVO order) {
		return AddOrder.voToPO(order);
	}

	public static PlanePO voToPO(PlaneVO planeVO) {
		return new PlanePO(planeVO.farePrice, planeVO.ID, planeVO.destination);
	}

	public static OrganizationPO voToPO(OrganizationVO intermediate) {
		return new OrganizationPO(intermediate.category,
				intermediate.organizationID, intermediate.name);
	}

	public static EnplaningReceiptPO voToPO(EnplaningReceiptVO enplaningReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : enplaningReceipt.orderList)
			orderList.add(EnplaningBL.voToPO(order));

		return new EnplaningReceiptPO(orderList, enplaningReceipt.ID,
				EnplaningBL.voToPO(enplaningReceipt.intermediateCentre),
				EnplaningBL.voToPO(enplaningReceipt.plane));
	}

	public static ArrayList<EnplaningReceiptPO> voToPO(ArrayList<EnplaningReceiptVO> list){
		ArrayList<EnplaningReceiptPO> enplaningReceiptList = new ArrayList<EnplaningReceiptPO>();
		for(EnplaningReceiptVO receipt:list)
			enplaningReceiptList.add(EnplaningBL.voToPO(receipt));
		
		return enplaningReceiptList;
	}
}
