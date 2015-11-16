package businesslogic.businessbl.controller;

import java.util.ArrayList;

import businesslogic.businessbl.DistributeOrder;
import businesslogicservice.businessblservice.DistributeOrderBLService;
import vo.ExpressVO;
import vo.OrderVO;

public class DistributeOrderController implements DistributeOrderBLService{

	private DistributeOrder distributeOrder;

	public DistributeOrderController() {
		distributeOrder = new DistributeOrder();
	}

	public String distributeOrder() {
		// TODO Auto-generated method stub
		return distributeOrder.distributeOrder();
	}

	public ArrayList<ExpressVO> getExpressInfos() {
		// TODO Auto-generated method stub
		return distributeOrder.getExpressInfos();
	}

	public ArrayList<OrderVO> getSendOrder() {
		// TODO Auto-generated method stub
		return distributeOrder.getSendOrder();
	}

}
