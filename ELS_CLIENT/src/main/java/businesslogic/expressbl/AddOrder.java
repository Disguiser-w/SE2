package businesslogic.expressbl;

import java.rmi.RemoteException;

import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import dataservice.managedataservice.CityDistanceDataService;
import dataservice.managedataservice.CityDistanceDataService_stub;
import dataservice.managedataservice.CostDataService;
import dataservice.managedataservice.CostDataService_stub;
import po.OrderPO;
import vo.OrderVO;

public class AddOrder {

	private ExpressDataService_stub expressData;
	private CostDataService costData;
	private CityDistanceDataService cityDistanceData;

	public AddOrder() {
		// RMI
		expressData = new ExpressDataService_stub();
		costData = new CostDataService_stub();
		cityDistanceData = new CityDistanceDataService_stub();
	}

	public boolean addOrder(OrderVO vo) {
		OrderPO po = voToPO(vo);
		return expressData.addOrder(po);
	}

	public void calculateCost(OrderVO vo) {
		// TODO Auto-generated method stub
		String city1 = vo.senderAddress.split(" ")[0];
		String city2 = vo.recipientAddress.split(" ")[0];
		double distance = 0;
		try {
			distance = cityDistanceData.findCityDistance(city1, city2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double weight = Double.parseDouble(vo.weight);
		double volumn = Double.parseDouble(vo.volume);

		double tWeight = 0;
		if (weight > volumn / 5000)
			tWeight = weight;
		else
			tWeight = volumn;

		float freight = 0f;
		switch (vo.packType) {
		case CARTONS:
			freight = 5f;
			break;
		case WOODCASE:
			freight = 10f;
			break;
		case COURIERBAGS:
			freight = 1f;
			break;
		}

		float packExpense = 0;
		if (city1.equals(city2))
			packExpense = (float) (30 * tWeight * 23);
		else
			packExpense = (float) (distance * tWeight * 23);

		vo.freight = freight;
		vo.packingExpense = packExpense;

	}

	public static OrderPO voToPO(OrderVO vo) {
		OrderPO po = new OrderPO(vo.ID, vo.senderName, vo.senderAddress, vo.senderOrganization, vo.senderPhoneNumber,
				vo.senderMobilePhoneNumber, vo.recipientName, vo.recipientAddress, vo.recipientOrganization,
				vo.recipientPhoneNumber, vo.recipientMobilePhoneNumber, vo.numOfGoods, vo.weight, vo.volume,
				vo.goodsName, vo.expressType, vo.packType, vo.freight, vo.packingExpense, vo.builtDate, vo.finishedDate,
				vo.finishedID, vo.transfer_state, vo.order_state);
		return po;

	}

	public static OrderVO poToVO(OrderPO po) {

		OrderVO vo = new OrderVO(po.getID(), po.getSenderName(), po.getSenderAddress(), po.getSenderOrganization(),
				po.getSenderPhoneNumber(), po.getSenderMobilePhoneNumber(), po.getRecipientName(),
				po.getRecipientAddress(), po.getRecipientOrganization(), po.getRecipientPhoneNumber(),
				po.getRecipientMobilePhoneNumber(), po.getNumOfGoods(), po.getWeight(), po.getVolume(),
				po.getGoodsName(), po.getExpressType(), po.getPackType(), po.getFreight(), po.getPackingExpense(),
				po.getBuiltData(), po.getFinishedData(), po.getFinishedID(), po.getTransfer_state(),
				po.getOrder_state());
		return vo;
	}

	public ExpressDataService getExpressData() {
		return expressData;
	}

	public CostDataService getCostData() {
		return costData;
	}

	public CityDistanceDataService getCityDistanceData() {
		return cityDistanceData;
	}

}
