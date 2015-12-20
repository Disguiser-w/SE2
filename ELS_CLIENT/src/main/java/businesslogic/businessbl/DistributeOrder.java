package businesslogic.businessbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import po.DistributeReceiptPO;
import po.ExpressPO;
import po.OrderPO;
import type.OrderState;
import type.ReceiptState;
import vo.OrganizationVO;

public class DistributeOrder {

	private ExpressDataService expressData;
	private BusinessDataService businessData;

	public DistributeOrder() {

		try {
			expressData = DataFactory.getExpressData();
			businessData = DataFactory.getBusinessData();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 从昨天的订单中搜索，如果状态是WAITING_DISTRIBUTE就去出来准备分发
	// 取出本营业的所有快递员的po，按照顺序增加到快递员的pendingOrder中
	public ArrayList<String[]> distributeOrder() {
		BusinessMainController.updateBusinessVO();

		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;
		ArrayList<ExpressPO> expressPOs = null;
		try {
			expressPOs = expressData.getExpressInfos(organizationVO.organizationID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<OrderPO> pos = new ArrayList<OrderPO>();

		// 获得改营业厅前一天接收的准备拍派件的订单
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DATE, -1); // 得到前一天还有今天
		Date yestoday = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time1 = df.format(yestoday);
		String time2 = df.format(today);

		ArrayList<String[]> result = new ArrayList<String[]>();
		ArrayList<String> result1 = new ArrayList<String>();
		ArrayList<OrderPO> distributingOrders = null;
		try {
			distributingOrders = expressData.getOrderInfosByTime(organizationVO.organizationID, time1);
			ArrayList<OrderPO> pos2 = expressData.getOrderInfosByTime(organizationVO.organizationID, time2);
			for (OrderPO i : pos2)
				distributingOrders.add(i);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {

			int j = 0;
			int size = expressPOs.size();
			for (OrderPO i : distributingOrders)
				if (i.getOrder_state() == OrderState.WAITING_DISTRIBUTE) {
					expressData.changeState(OrderState.DISTRIBUEING, i.getID());
					expressData.addHistory("正在派件", null, i.getID());
					expressData.addPendingOrder(organizationVO.organizationID, expressPOs.get(j).getID(), i.getID());
					String str = expressPOs.get(j).getID() + " " + i.getRecipientAddress().split(" ")[2] + " "
							+ i.getID();
					result.add(str.split(" "));
					result1.add(str);
					j++;
					if (j == size)
						j = 0;
				}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
		String nowTime = df.format(new Date());

		DistributeReceiptPO po = new DistributeReceiptPO("PJD-" + organizationVO.organizationID + "-" + nowTime,
				result1, nowTime, ReceiptState.SUBMIT);
		// 增加派件单，一天一个
		try {
			businessData.addDistributeReceipt(organizationVO.organizationID, po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
