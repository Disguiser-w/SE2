package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import po.DistributeReceiptPO;
import po.ExpressPO;
import po.OrderPO;
import type.OrderState;
import type.ReceiptState;
import vo.OrganizationVO;
import businesslogic.businessbl.controller.BusinessMainController;
import dataservice.expressdataservice.ExpressDataService;

public class DistributeOrder {

	// 从昨天的订单中搜索，如果状态是WAITING_DISTRIBUTE就去出来准备分发
	// 取出本营业的所有快递员的po，按照顺序增加到快递员的pendingOrder中
	public ArrayList<String> distributeOrder() throws RemoteException {
		BusinessMainController.updateBusinessVO();
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;
		ExpressDataService expressData = BusinessMainController.expressData;
		ArrayList<ExpressPO> expressPOs = null;
		try {
			expressPOs = expressData.getExpressInfos(organizationVO.organizationID);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<OrderPO> pos = new ArrayList<OrderPO>();

		// 获得改营业厅前一天接收的准备拍派件的订单
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(date);

		ArrayList<String> result = new ArrayList<String>();
		ArrayList<OrderPO> distributingOrders = null;
		try {
			distributingOrders = expressData.getOrderInfosByTime(organizationVO.organizationID, time);
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

					result.add(i.getID() + " " + expressPOs.get(j).getID());
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

		DistributeReceiptPO po = new DistributeReceiptPO("PJD-" + organizationVO.organizationID + "-" + nowTime, result,
				nowTime, ReceiptState.SUBMIT);
		// 增加派件单，一天一天
		BusinessMainController.businessData.addDistributeReceipt(organizationVO.organizationID, po);

		return result;
	}

}
