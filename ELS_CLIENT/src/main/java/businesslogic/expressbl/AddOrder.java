package businesslogic.expressbl;

import java.rmi.RemoteException;

import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import dataservice.managedataservice.CityDistanceDataService;
import dataservice.managedataservice.CityDistanceDataService_stub;
import dataservice.managedataservice.CostDataService;
import dataservice.managedataservice.CostDataService_stub;
import po.OrderPO;
import type.OrderState;
import type.TransferingState;
import vo.ExpressVO;
import vo.OrderVO;

public class AddOrder {

	private CostDataService costData;
	private CityDistanceDataService cityDistanceData;

	public AddOrder() {
		// vo存着本营业厅的信息，此处一个BL实例对应一个营业厅

		// OrderPO订单按营业厅ID和时间来生成文件，每个营业厅每日一个，放在orderInfo文件夹里如orderInfo/YYT-12345/2015-11-10-order.dat
		// 营业厅，营业厅加时间，所有,ID

		// ExpressPO快递员按营业厅ID来存，每个营业厅一个，放在expressInfo文件夹里如expressInfo/YYT-12345-express.dat
		// 营业厅，所有，ID

		// VehiclePO和DriverPO全部按营业厅，如vehicleInfo/YYT-12345-Vehicle.dat,driverInfo/YYT-12345-driver.dat
		// 营业厅，所有

		// EnVehicleReceiptPO 装车单，每个营业厅每日多个如
		// enVehicleInfo/YYT-12345/2015-11-10/001-enVehicle.dat
		// 营业厅，营业厅加时间，营业厅加时间段，所有

		// GatheringReceiptPO 收款汇总单,每个营业厅每日一个如
		// gatheringInfo/YYT-12345/2015-11-10-gathering.dat
		// 营业厅，营业厅加时间段，营业厅加时间，所有

		// OrderAcceptReceiptPO 货物接收单，每个营业厅每日多个如
		// orderAccept/YYT-YYT-12345/2015-11-10/001-orderAccept.dat
		// 营业厅，营业厅加时间，营业厅加时间段，所有

		// 此处将就
		costData = new CostDataService_stub();
		cityDistanceData = new CityDistanceDataService_stub();
	}

	public boolean addOrder(OrderVO vo) {
		// 更新expressVO信息
		ExpressMainController.updateExpressInfo();

		boolean result = false;
		try {
			OrderPO po = ExpressMainController.orderVOToPO(vo);

			String[] addr1 = vo.senderAddress.split(" ");
			String[] addr2 = vo.recipientAddress.split(" ");

			if (addr1[0].equals(addr2[0])) {
				// 如果在同一城市，不需要经过中转中心转运
				po.setTransfer_state(TransferingState.FINISHED_ENVEHICLE);
				if (addr1[1].equals(addr2[1])) {
					// 如果同一营业厅,直接派送
					po.setOrder_state(OrderState.WAITING_DISTRIBUTE);
					po.getHistory().add("快件已发出");
					po.getHistory().add("快件已到达" + ExpressMainController.expressVO.organization.name + ",正等待派送");

				} else {
					// 否则要经过一次转运,转运后状态变为等待派件
					po.setOrder_state(OrderState.WAITING_ENVEHICLE);
					po.getHistory().add("快件已发出");
					po.getHistory().add("订单已到达" + ExpressMainController.expressVO.organization.name + ",正等待转运");
				}
			} else {
				// 在不同城市,需要转运和中转
				po.setTransfer_state(TransferingState.WAITING_ENVEHICLE);
				po.setOrder_state(OrderState.WAITING_ENVEHICLE);
				po.getHistory().add("快件已发出");
				po.getHistory().add("订单已到达" + ExpressMainController.expressVO.organization.name + ",正等待中转");
			}

			// 增加订单到本营业厅当日订单列表中
			String organizationID = ExpressMainController.expressVO.organization.organizationID;

			result = ExpressMainController.expressData.addOrder(po, organizationID);
			// 增加此订单ID到此快递员的SubmitOrderID中
			ExpressMainController.expressData.addSubmitOrder(organizationID, ExpressMainController.expressVO.ID, vo.ID);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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

	public CostDataService getCostData() {
		return costData;
	}

	public CityDistanceDataService getCityDistanceData() {
		return cityDistanceData;
	}

}
