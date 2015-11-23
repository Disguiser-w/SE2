package businesslogic.expressbl;

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

		return poToVO(expressData.getExpressInfo(ID));
	}

	public boolean chargeCollection(ExpressVO vo) {
		return (expressData.chargeCollection(voToPO(vo)));
	}

	public ExpressDataService getExpressData() {
		return expressData;
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
}
