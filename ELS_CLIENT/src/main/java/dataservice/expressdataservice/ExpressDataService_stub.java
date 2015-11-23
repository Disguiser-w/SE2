package dataservice.expressdataservice;

import po.CostBasePO;
import po.ExpressPO;
import po.OrderPO;

public class ExpressDataService_stub implements ExpressDataService{

	public CostBasePO getBaseCost() {
		// TODO Auto-generated method stub
		System.out.println("Show CostBasePO!");
		return null;
	}

	public boolean chargeCollection(ExpressPO po) {
		// TODO Auto-generated method stub
		System.out.println("ChargeCollection successfully!");
		return false;
	}

	public ExpressPO getExpressInfos() {
		// TODO Auto-generated method stub
		System.out.println("Show ExpressPO!");
		return null;
	}

	@Override
	public int getOrderNum() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public boolean addOrder(OrderPO po) {
		// TODO Auto-generated method stub
		System.out.println("Add Successful!");
		return true;
	}

	@Override
	public ExpressPO getExpressInfo(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
