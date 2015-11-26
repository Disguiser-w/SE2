package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.ExpressPO;
import po.OrderPO;
import type.OrderState;

public class DistributeOrder {
	private BusinessDataService businessData;
	private ExpressDataService expressData;

	public DistributeOrder() {
		businessData = new BusinessDataService_stub();
		expressData = new ExpressDataService_stub();

	}

	public ArrayList<String> distributeOrder(String OrganizationID) {
		ArrayList<ExpressPO> expressPOs = getExpressInfos(OrganizationID);

		ArrayList<OrderPO> pos = getSendOrder();

		int len = expressPOs.size();
		int n = 0;
		for (OrderPO i : pos) {
			try {
				expressData.addPendingOrder(i, expressPOs.get(n));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			n++;
			if (n == len)
				n = 0;
		}

		expressPOs = getExpressInfos(OrganizationID);

		ArrayList<String> result = new ArrayList<String>();
		for (ExpressPO i : expressPOs)
			for (OrderPO j : i.getPendingOrders())
				result.add(j.getID() + " " + i.getID());

		return result;
	}

	public ArrayList<ExpressPO> getExpressInfos(String OrganizationID) {
		ArrayList<ExpressPO> newPos = new ArrayList<ExpressPO>();
		ArrayList<ExpressPO> pos = null;
		try {
			pos = expressData.getExpressInfos(OrganizationID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (ExpressPO i : pos)
			if (i.getOrganization().getOrganizationID().equals(OrganizationID))
				newPos.add(i);
		return newPos;
	}

	// 每天早上8点派送之前一天接收（AcceptCargo）的所有快件
	public ArrayList<OrderPO> getSendOrder() {
		ArrayList<OrderPO> newPos = new ArrayList<OrderPO>();
		ArrayList<OrderPO> pos = null;
		try {
			Date dNow = new Date(); // 当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dNow);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			dBefore = calendar.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
			String timeStr = sdf.format(dBefore); // 格式化前一天

			pos = expressData.getOrderInfosByTime(timeStr);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (OrderPO i : pos)
			if (i.getOrder_state() == OrderState.WAITING_DISTRIBUTE) {
				newPos.add(i);
				i.setOrder_state(OrderState.DiSTRIBUEING);
			}

		return newPos;
	}

}
