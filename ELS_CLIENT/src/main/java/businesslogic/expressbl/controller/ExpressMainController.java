package businesslogic.expressbl.controller;

import java.util.ArrayList;

import businesslogic.managebl.OrganizationBL;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.ExpressPO;
import po.OrderPO;
import vo.ExpressVO;
import vo.OrderVO;

public class ExpressMainController {

	public static ExpressDataService expressData;
	public static ExpressVO expressVO;

	public static AddOrderController addOrderController;
	public static ChargeCollectionController chargeCollectionController;
	public static LogisticQueryController logisticQuery;
	public static ReceiptOrderController receiptOrderController;

	// ExpressData的初始化，ExpressVO的初始化在此进行
	public ExpressMainController(String expressID) {
		// RMI
		expressData = new ExpressDataService_stub();
		expressVO = expressPOToVO(expressData.getExpressInfo(null, expressID));
		// 初始化4个controller
		addOrderController = new AddOrderController();
		chargeCollectionController = new ChargeCollectionController();
		logisticQuery = new LogisticQueryController();
		receiptOrderController = new ReceiptOrderController();
	}

	// 最后此方法在此聚合
	public static void updateExpressInfo() {
		expressVO = expressPOToVO(expressData.getExpressInfo(expressVO.organization.organizationID, expressVO.ID));
	}

	// vo和po的转化都放在这里static
	public static OrderPO orderVOToPO(OrderVO vo) {
		OrderPO po = new OrderPO(vo.ID, vo.senderName, vo.senderAddress, vo.senderOrganization, vo.senderPhoneNumber,
				vo.senderMobilePhoneNumber, vo.recipientName, vo.recipientAddress, vo.recipientOrganization,
				vo.recipientPhoneNumber, vo.recipientMobilePhoneNumber, vo.numOfGoods, vo.weight, vo.volume,
				vo.goodsName, vo.expressType, vo.packType, vo.freight, vo.packingExpense, vo.builtDate, vo.tRecipient,
				vo.finishedDate, vo.finishedID, vo.transfer_state, vo.order_state);
		return po;

	}

	public static OrderVO orderPOToVO(OrderPO po) {

		OrderVO vo = new OrderVO(po.getID(), po.getSenderName(), po.getSenderAddress(), po.getSenderOrganization(),
				po.getSenderPhoneNumber(), po.getSenderMobilePhoneNumber(), po.getRecipientName(),
				po.getRecipientAddress(), po.getRecipientOrganization(), po.getRecipientPhoneNumber(),
				po.getRecipientMobilePhoneNumber(), po.getNumOfGoods(), po.getWeight(), po.getVolume(),
				po.getGoodsName(), po.getExpressType(), po.getPackType(), po.getFreight(), po.getPackingExpense(),
				po.getBuiltData(), po.gettRecipient(), po.getFinishedData(), po.getFinishedID(), po.getTransfer_state(),
				po.getOrder_state());
		return vo;
	}

	public static ExpressPO expressVOToPO(ExpressVO vo) {

		ArrayList<OrderPO> pendingOrderPOs = new ArrayList<OrderPO>();
		for (OrderVO i : vo.pendingOrders)
			pendingOrderPOs.add(orderVOToPO(i));

		ArrayList<OrderPO> finishedOrderPOs = new ArrayList<OrderPO>();
		for (OrderVO i : vo.finishedOrders)
			finishedOrderPOs.add(orderVOToPO(i));

		return new ExpressPO(vo.name, vo.ID, vo.serviceTime, vo.chargeCollection,
				OrganizationBL.organizationVOToPO(vo.organization), pendingOrderPOs, finishedOrderPOs,
				vo.submitedOrderID);

	}

	public static ExpressVO expressPOToVO(ExpressPO po) {
		ArrayList<OrderVO> pendingOrderVOs = new ArrayList<OrderVO>();
		for (OrderPO i : po.getPendingOrders())
			pendingOrderVOs.add(orderPOToVO(i));

		ArrayList<OrderVO> finishedOrderVOs = new ArrayList<OrderVO>();
		for (OrderPO i : po.getFinishedOrders())
			finishedOrderVOs.add(orderPOToVO(i));

		return new ExpressVO(po.getName(), po.getID(), po.getServiceTime(), po.getChargeCollection(),
				OrganizationBL.organizationPOToVO(po.getOrganization()), pendingOrderVOs, finishedOrderVOs,
				po.getSubmitedOrderID());
	}

}
