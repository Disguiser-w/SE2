package dataservice.expressdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostBasePO;
import po.ExpressPO;
import po.OrderPO;

public class ExpressDataService_stub implements ExpressDataService {

	@Override
	public int getOrderNum() throws RemoteException {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public boolean addOrder(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Add Successful!");
		return true;
	}

	@Override
	public ExpressPO getExpressInfo(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderPO find(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chargeCollection(ExpressPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ExpressPO> getExpressInfos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostBasePO getBaseCost() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderPO> getOrderInfos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ExpressPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
