package businesslogic.businessbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	public ArrayList<String[]> distributeOrder() {
		BusinessMainController.updateBusinessVO();

		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;
		ArrayList<ExpressPO> expressPOs = new ArrayList<ExpressPO>();
		try {
			ArrayList<ExpressPO> allExpressPOs = expressData.getExpressInfos(organizationVO.organizationID);
			for (ExpressPO p : allExpressPOs) {
				if (p.getOrganizationPO().getOrganizationID()
						.equals(BusinessMainController.businessVO.organizationVO.organizationID)) {
					expressPOs.add(p);
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<OrderPO> pos = new ArrayList<OrderPO>();

		ArrayList<String[]> result = new ArrayList<String[]>();
		ArrayList<String> result1 = new ArrayList<String>();
		ArrayList<OrderPO> distributingOrders = null;
		try {
			distributingOrders = expressData.getDistributingOrder(organizationVO.name);
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
		String nowTime = fm.format(new Date());
		int num = 0;
		try {
			num = businessData.getNumOfOrderDistributeReceipt(organizationVO.organizationID);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DistributeReceiptPO po = new DistributeReceiptPO(
				"PJD-" + organizationVO.organizationID + "-" + nowTime + "-" + num, result1, nowTime,
				ReceiptState.SUBMIT);
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
