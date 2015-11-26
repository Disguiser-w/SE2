package businesslogic.intermediatebl.controller;

import java.util.ArrayList;

import po.EnplaningReceiptPO;
import po.EntrainingReceiptPO;
import po.EntruckingReceiptPO;
import po.IntermediatePO;
import po.OrderPO;
import po.OrganizationPO;
import po.PlanePO;
import po.RepertoryPO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.IntermediateVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.PlaneVO;
import vo.RepertoryVO;
import vo.TrainVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;
import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.intermediatedataservice.IntermediateDataService;
import dataservice.intermediatedataservice.envehicledataservice.EnplaningDataService;
import dataservice.intermediatedataservice.envehicledataservice.EnplaningDataService_stub;
import dataservice.intermediatedataservice.envehicledataservice.EntrainingDataService;
import dataservice.intermediatedataservice.envehicledataservice.EntrainingDataService_stub;
import dataservice.intermediatedataservice.envehicledataservice.EntruckingDataService;
import dataservice.intermediatedataservice.envehicledataservice.EntruckingDataService_stub;

public class IntermediateMainController {
	private IntermediateDataService intermediateDataService;
	private EnplaningDataService enplaningData;
	private EntrainingDataService entrainingData;
	private EntruckingDataService entruckingData;

	private IntermediateVO intermediate;

	public IntermediateMainController(String intermediateID) {
		enplaningData = new EnplaningDataService_stub();
		entrainingData = new EntrainingDataService_stub();
		entruckingData = new EntruckingDataService_stub();
		intermediate = IntermediateMainController
				.poToVO(intermediateDataService.getIntermediateInfo(null,
						intermediateID));
	}

	public void updateIntermediateInfo() {
		intermediate = IntermediateMainController
				.poToVO(intermediateDataService.getIntermediateInfo(
						intermediate.organization.organizationID,
						intermediate.ID));
	}

	public static OrderPO voToPO(OrderVO order) {
		return ExpressMainController.orderVOToPO(order);
	}

	public static PlanePO voToPO(PlaneVO plane) {
		return new PlanePO(plane.farePrice, plane.ID, plane.destination);
	}

	public static TrainPO voToPO(TrainVO train) {
		return new TrainPO(train.farePrice, train.ID, train.destination);
	}

	public static TruckPO voToPO(TruckVO truck) {
		return new TruckPO(truck.farePrice, truck.ID, truck.destination);
	}

	public static OrganizationPO voToPO(OrganizationVO intermediate) {
		return new OrganizationPO(intermediate.category,
				intermediate.organizationID, intermediate.name);
	}

	public static EnplaningReceiptPO voToPO(EnplaningReceiptVO enplaningReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : enplaningReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new EnplaningReceiptPO(
				IntermediateMainController
						.voToPO(enplaningReceipt.intermediateCentre),
				IntermediateMainController.voToPO(enplaningReceipt.plane),
				orderList, enplaningReceipt.fare, enplaningReceipt.ID);
	}

	public static EntrainingReceiptPO voToPO(
			EntrainingReceiptVO entrainingReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : entrainingReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new EntrainingReceiptPO(
				IntermediateMainController
						.voToPO(entrainingReceipt.intermediateCentre),
				IntermediateMainController.voToPO(entrainingReceipt.train),
				orderList, entrainingReceipt.fare, entrainingReceipt.ID);
	}

	public static EntruckingReceiptPO voToPO(
			EntruckingReceiptVO entruckingReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : entruckingReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new EntruckingReceiptPO(
				IntermediateMainController
						.voToPO(entruckingReceipt.intermediateCentre),
				IntermediateMainController.voToPO(entruckingReceipt.truck),
				orderList, entruckingReceipt.fare, entruckingReceipt.ID);
	}

	public static ArrayList<EnplaningReceiptPO> voToPO_EnplaningReceipt(
			ArrayList<EnplaningReceiptVO> list) {
		ArrayList<EnplaningReceiptPO> enplaningReceiptList = new ArrayList<EnplaningReceiptPO>();
		for (EnplaningReceiptVO receipt : list)
			enplaningReceiptList
					.add(IntermediateMainController.voToPO(receipt));

		return enplaningReceiptList;
	}

	public static ArrayList<EntrainingReceiptPO> voToPO_EntrainingReceipt(
			ArrayList<EntrainingReceiptVO> list) {
		ArrayList<EntrainingReceiptPO> entrainingReceiptList = new ArrayList<EntrainingReceiptPO>();
		for (EntrainingReceiptVO receipt : list)
			entrainingReceiptList.add(IntermediateMainController
					.voToPO(receipt));

		return entrainingReceiptList;
	}

	public static ArrayList<EntruckingReceiptPO> voToPO_EntruckingReceipt(
			ArrayList<EntruckingReceiptVO> list) {
		ArrayList<EntruckingReceiptPO> entruckingReceiptList = new ArrayList<EntruckingReceiptPO>();
		for (EntruckingReceiptVO receipt : list)
			entruckingReceiptList.add(IntermediateMainController
					.voToPO(receipt));

		return entruckingReceiptList;
	}

	public static OrderVO poToVO(OrderPO order) {
		return ExpressMainController.orderPOToVO(order);
	}

	public static TransferingReceiptPO voToPO(
			TransferingReceiptVO transferingReceipt) {
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		for (OrderVO order : transferingReceipt.orderList)
			orderList.add(IntermediateMainController.voToPO(order));

		return new TransferingReceiptPO(
				IntermediateMainController
						.voToPO(transferingReceipt.interdiateCentre),
				orderList, transferingReceipt.ID);
	}

	public static RepertoryVO poToVO(RepertoryPO repertory) {
		return new RepertoryVO(repertory.getOwnerID(), repertory.getOwnerID());
	}

	public static OrganizationVO poToVO(OrganizationPO organization) {
		return new OrganizationVO(organization.getCategory(),
				organization.getOrganizationID(), organization.getName(),
				IntermediateMainController.poToVO(organization.getRepertory()));
	}

	public static IntermediateVO poToVO(IntermediatePO intermediate) {
		return new IntermediateVO(
				IntermediateMainController.poToVO(intermediate
						.getOrganization()), intermediate.getName(),
				intermediate.getID());
	}
}
