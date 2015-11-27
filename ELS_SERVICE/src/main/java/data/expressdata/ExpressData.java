package data.expressdata;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dataservice.expressdataservice.ExpressDataService;
import po.ExpressPO;
import po.OrderPO;
import type.OrderState;

public class ExpressData implements ExpressDataService {
	// 根据OrganizationID添加订单到今日订单文件中,时间自动生成，在total.dat(使用AVL树，（存放键值对，OrderID为键,路径为值）)中添加一个副本，来根据ID快速查找Organization和time
	public boolean addOrder(OrderPO po, String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public ExpressPO getExpressInfo(String organizationID, String expressID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ExpressPO> getExpressInfos(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateChargeCollection(String organizationID, String expressId, ArrayList<String> chargeCollection)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public OrderPO find(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean receiptOrder(String organizationID, String expressId, OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addSubmitOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addPendingOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean changeState(OrderState orderState, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addDistributingOrder(OrderPO po, String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<OrderPO> getOrderInfosByTime(String organizationID, String time) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addHistory(String process, String organizationID, String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	// 获得当前日期
	private String getTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}

}
