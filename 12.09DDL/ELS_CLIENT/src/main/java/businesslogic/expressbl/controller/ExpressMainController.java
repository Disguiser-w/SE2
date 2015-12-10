package businesslogic.expressbl.controller;

import java.rmi.RemoteException;

import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.OrganizationBL;
import dataservice.expressdataservice.ExpressDataService;
import po.ExpressPO;
import po.OrderPO;
import presentation.expressui.AddOrderPanel;
import presentation.expressui.ChargeMessageCollectionPanel;
import presentation.expressui.ExpressFrame;
import presentation.expressui.FinishedOrderPanel;
import presentation.expressui.QueryPanel;
import vo.ExpressVO;
import vo.OrderVO;

public class ExpressMainController {

	public static ExpressDataService expressData;
	public static ExpressVO expressVO;

	private AddOrderController addOrderController;
	private ChargeCollectionController chargeCollectionController;
	private LogisticQueryController logisticQuery;
	private ReceiptOrderController receiptOrderController;

	private ExpressFrame expressFrame;

	// ExpressData的初始化，ExpressVO的初始化在此进行
	public ExpressMainController(String expressID) {
		// RMI

		try {
			expressData = DataFactory.getExpressData();
			expressVO = expressPOToVO((ExpressPO) (expressData.getExpressInfo(null, expressID)));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 初始化4个controller
		addOrderController = new AddOrderController();
		chargeCollectionController = new ChargeCollectionController();
		logisticQuery = new LogisticQueryController();
		receiptOrderController = new ReceiptOrderController();

		expressFrame = new ExpressFrame(expressVO);
		expressFrame.addFuncLabel(new AddOrderPanel(addOrderController));
		expressFrame.addFuncLabel(new ChargeMessageCollectionPanel(chargeCollectionController));
		expressFrame.addFuncLabel(new QueryPanel(logisticQuery));
		expressFrame.addFuncLabel(new FinishedOrderPanel(receiptOrderController));

		expressFrame.showFrame();

	}

	// 最后此方法在此聚合
	public static void updateExpressInfo() {
		try {
			expressVO = expressPOToVO(expressData.getExpressInfo(expressVO.organization.organizationID, expressVO.ID));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// vo和po的转化都放在这里static
	public static OrderPO orderVOToPO(OrderVO vo) {
		OrderPO po = new OrderPO(vo.ID, vo.senderName, vo.senderAddress, vo.senderOrganization, vo.senderPhoneNumber,
				vo.senderMobilePhoneNumber, vo.recipientName, vo.recipientAddress, vo.recipientOrganization,
				vo.recipientPhoneNumber, vo.recipientMobilePhoneNumber, vo.numOfGoods, vo.weight, vo.volume,
				vo.goodsName, vo.expressType, vo.packType, vo.freight, vo.packingExpense, vo.builtDate, vo.tRecipient,
				vo.finishedDate, vo.finishedID, vo.order_state, vo.history);
		po.settPhoneNumber(vo.tPhoneNumber);
		return po;

	}

	public static OrderVO orderPOToVO(OrderPO po) {

		OrderVO vo = new OrderVO(po.getID(), po.getSenderName(), po.getSenderAddress(), po.getSenderOrganization(),
				po.getSenderPhoneNumber(), po.getSenderMobilePhoneNumber(), po.getRecipientName(),
				po.getRecipientAddress(), po.getRecipientOrganization(), po.getRecipientPhoneNumber(),
				po.getRecipientMobilePhoneNumber(), po.getNumOfGoods(), po.getWeight(), po.getVolume(),
				po.getGoodsName(), po.getExpressType(), po.getPackType(), po.getFreight(), po.getPackingExpense(),
				po.getBuiltData(), po.gettRecipient(), po.getFinishedData(), po.getFinishedID(), po.getOrder_state(),
				po.getHistory());

		vo.tPhoneNumber = po.gettPhoneNumber();
		return vo;
	}

	public static ExpressPO expressVOToPO(ExpressVO vo) {

		return new ExpressPO(vo.name, vo.ID, vo.serviceTime, vo.chargeCollection,
				OrganizationBL.organizationVOToPO(vo.organization), vo.pendingOrders, vo.finishedOrders,
				vo.submitedOrderID);

	}

	public static ExpressVO expressPOToVO(ExpressPO po) {

		return new ExpressVO(po.getName(), po.getID(), po.getServiceTime(), po.getChargeCollection(),
				OrganizationBL.organizationPOToVO(po.getOrganization()), po.getPendingOrders(), po.getFinishedOrders(),
				po.getSubmitedOrderID());
	}

	// test
	public static void main(String[] args) {
		new ExpressMainController("KDY-00001");
	}
}
