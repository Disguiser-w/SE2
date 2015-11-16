package businesslogic.expressbl;

import dataservice.expressdataservice.ExpressDataService;
import po.OrderPO;
import type.ExpressType;
import type.PackType;
import vo.OrderVO;

public class AddOrder {

	private ExpressDataService expressData;

	public AddOrder() {

	}

	public boolean addOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

	public OrderVO calculateCost(OrderVO vo) {
		// TODO Auto-generated method stub
		return new OrderVO(null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null,
				null, 10, 10, null, null, null);
	}

	public static OrderPO voToPO(OrderVO vo) {
		OrderPO po = new OrderPO(vo.ID, vo.senderName, vo.senderAddress, vo.senderOrganization, vo.senderPhoneNumber,
				vo.senderMobilePhoneNumber, vo.recipientName, vo.recipientAddress, vo.recipientOrganization,
				vo.recipientPhoneNumber, vo.recipientMobilePhoneNumber, vo.numOfGoods, vo.weight, vo.volume,
				vo.goodsName, vo.expressType, vo.packType, vo.freight, vo.packingExpense, vo.builtData, vo.finishedData,
				vo.finishedID);
		return po;

	}

	public static OrderVO poToVO(OrderPO po) {

		OrderVO vo = new OrderVO(po.getID(), po.getSenderName(), po.getSenderAddress(), po.getSenderOrganization(),
				po.getSenderPhoneNumber(), po.getSenderMobilePhoneNumber(), po.getRecipientName(),
				po.getRecipientAddress(), po.getRecipientOrganization(), po.getRecipientPhoneNumber(),
				po.getRecipientMobilePhoneNumber(), po.getNumOfGoods(), po.getWeight(), po.getVolume(),
				po.getGoodsName(), po.getExpressType(), po.getPackType(), po.getFreight(), po.getPackingExpense(),
				po.getBuiltData(), po.getFinishedData(), po.getFinishedID());
		return vo;
	}
}
