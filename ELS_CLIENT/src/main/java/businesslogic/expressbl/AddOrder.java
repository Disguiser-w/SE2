package businesslogic.expressbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.expressdataservice.ExpressDataService;
import po.OrderPO;
import type.OrderState;
import vo.ExpressVO;
import vo.OrderVO;

public class AddOrder {

	private ExpressDataService expressData;

	public AddOrder() {
		try {
			expressData = DataFactory.getExpressData();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean addOrder(OrderVO vo) {
		// 更新expressVO信息
		ExpressMainController.updateExpressInfo();
		ExpressVO expressVO = ExpressMainController.expressVO;
		boolean result = false;
		try {
			OrderPO po = ExpressMainController.orderVOToPO(vo);

			String[] addr1 = vo.senderAddress.split(" ");
			String[] addr2 = vo.recipientAddress.split(" ");

			if (addr1[0].equals(addr2[0])) {
				// 如果在同一城市，不需要经过中转中心转运

				if (addr1[1].equals(addr2[1])) {
					// 如果同一营业厅,直接派送
					po.setOrder_state(OrderState.WAITING_DISTRIBUTE);
					po.getHistory().add("快件已发出");
					po.getHistory().add("快件已到达" + expressVO.organization.name + ",正等待派送");

				} else {
					// 否则要经过一次转运,转运后状态变为等待派件
					po.setOrder_state(OrderState.WAITING_ENVEHICLE);
					po.getHistory().add("快件已发出");
					po.getHistory().add("订单已到达" + expressVO.organization.name + ",正等待转运");
				}
			} else {
				// 在不同城市,需要转运和中转

				po.setOrder_state(OrderState.WAITING_ENVEHICLE);
				po.getHistory().add("快件已发出");
				po.getHistory().add("订单已到达" + expressVO.organization.name + ",正等待中转");
			}

			po.setPackingExpense(vo.packingExpense);
			po.setFreight(vo.freight);
			// 增加订单到本营业厅当日订单列表中
			String organizationID = expressVO.organization.organizationID;


			result = expressData.addOrder(po, organizationID);
			// 增加此订单ID到此快递员的SubmitOrderID中
			expressData.addSubmitOrder(organizationID, expressVO.ID, vo.ID);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double fare = ((int) ((vo.freight + vo.packingExpense) * 10)) / 10;
		chargeCollection(vo.ID + " " + fare);

		return result;
	}

	public void calculateCost(OrderVO vo) {
		// TODO Auto-generated method stub
		String city1 = vo.senderAddress.split(" ")[0];
		String city2 = vo.recipientAddress.split(" ")[0];

		double distance = 0;
		try {
//			distance = DataFactory.getCityDistanceData().findCityDistance(city1, city2).getDistance();
			distance = 1000;

		} catch (Exception e) {
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

		float packExpense = 0f;
		switch (vo.packType) {
		case CARTONS:
			packExpense = 5f;
			break;
		case WOODCASE:
			packExpense = 10f;
			break;
		case COURIERBAGS:
			packExpense = 1f;
			break;
		}

		float freight = 0;
		if (city1.equals(city2))
			freight = (float) (tWeight * 23);
		else
			freight = (float) (distance * tWeight * 23);

		vo.freight = ((int) (freight * 10)) / 10;
		vo.packingExpense = ((int) (packExpense * 10)) / 10;

	}

	public boolean chargeCollection(String chargeInfo) {
		// 更新expressVO
		ExpressMainController.updateExpressInfo();

		ArrayList<String> chargeCollection = ExpressMainController.expressVO.chargeCollection;

		if (chargeCollection.isEmpty())
			chargeCollection.add("0");

		double total = Double.parseDouble(chargeCollection.get(0));
		double charge = Double.parseDouble(chargeInfo.split(" ")[1]);
		chargeCollection.set(0, (total + charge) + "");
		chargeCollection.add(chargeInfo);

		boolean result = false;
		try {
			result = expressData.updateChargeCollection(ExpressMainController.expressVO.organization.organizationID,
					ExpressMainController.expressVO.ID, chargeCollection);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
