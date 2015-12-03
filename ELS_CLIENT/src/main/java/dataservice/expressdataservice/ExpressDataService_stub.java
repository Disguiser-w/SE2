package dataservice.expressdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostBasePO;
import po.ExpressPO;
import po.OrderPO;
import type.OrderState;

public class ExpressDataService_stub implements ExpressDataService {

	@Override
	public boolean addOrder(OrderPO po, String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ExpressPO getExpressInfo(String organizationID, String expressID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ExpressPO> getExpressInfos(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateChargeCollection(String organizationID, String expressId, ArrayList<String> chargeCollection)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderPO find(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean receiptOrder(String organizationID, String expressId, OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addSubmitOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPendingOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeState(OrderState orderState, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDistributingOrder(ArrayList<OrderPO> orderPOs, String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<OrderPO> getOrderInfosByTime(String organizationID, String time) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addHistory(String process, String organizationID, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOrderNum(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteChargeInfos(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
