package businesslogic.expressbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.ExpressPO;
import po.OrderPO;
import vo.ExpressVO;
import vo.OrderVO;

public class ChargeCollection {
	private ExpressDataService expressData;

	public ChargeCollection() {
		expressData = new ExpressDataService_stub();
	}

	public ExpressVO getChargeInfo(String ID) {

		ExpressVO vo = null;
		try {
			vo = poToVO(expressData.getExpressInfo(ID));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	public boolean chargeCollection(String ID, String chargeInfo) {
		ExpressVO vo = getChargeInfo(ID);
		ArrayList<String> chargeCollection = vo.chargeCollection;
		chargeCollection.add(chargeInfo);
		double total = Double.parseDouble(chargeCollection.get(0));
		double charge = Double.parseDouble(chargeInfo.split(" ")[1]);
		chargeCollection.set(0, (total + charge) + "");

		boolean result = false;
		try {
			result = expressData.update(voToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static ExpressPO voToPO(ExpressVO vo) {

		ArrayList<OrderPO> pendingOrderPOs = new ArrayList<OrderPO>();
		for (OrderVO i : vo.pendingOrders)
			pendingOrderPOs.add(AddOrder.voToPO(i));

		ArrayList<OrderPO> finishedOrderPOs = new ArrayList<OrderPO>();
		for (OrderVO i : vo.finishedOrders)
			finishedOrderPOs.add(AddOrder.voToPO(i));

		ArrayList<OrderPO> submitedOrderPOs = new ArrayList<OrderPO>();
		for (OrderVO i : vo.submitedOrder)
			submitedOrderPOs.add(AddOrder.voToPO(i));

		return new ExpressPO(vo.name, vo.ID, vo.serviceTime, vo.chargeCollection, vo.organization, pendingOrderPOs,
				finishedOrderPOs, submitedOrderPOs);

	}

	public static ExpressVO poToVO(ExpressPO po) {
		ArrayList<OrderVO> pendingOrderVOs = new ArrayList<OrderVO>();
		for (OrderPO i : po.getPendingOrders())
			pendingOrderVOs.add(AddOrder.poToVO(i));

		ArrayList<OrderVO> finishedOrderVOs = new ArrayList<OrderVO>();
		for (OrderPO i : po.getFinishedOrders())
			finishedOrderVOs.add(AddOrder.poToVO(i));

		ArrayList<OrderVO> submitedOrderVOs = new ArrayList<OrderVO>();
		for (OrderPO i : po.getSubmitedOrder())
			submitedOrderVOs.add(AddOrder.poToVO(i));

		return new ExpressVO(po.getName(), po.getID(), po.getServiceTime(), po.getChargeCollection(),
				po.getOrganization(), pendingOrderVOs, finishedOrderVOs, submitedOrderVOs);
	}

	public ExpressDataService getExpressData() {
		return expressData;
	}
}
