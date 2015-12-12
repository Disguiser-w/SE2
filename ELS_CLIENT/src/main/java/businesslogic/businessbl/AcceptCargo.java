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
import po.OrderAcceptReceiptPO;
import po.OrderPO;
import po.OrganizationPO;
import type.OrderState;
import type.ReceiptState;
import vo.OrganizationVO;

public class AcceptCargo {

	private BusinessDataService businessData;
	private ExpressDataService expressData;

	public AcceptCargo() { // 使用RMI初始化businessDataService
		try {
			expressData = DataFactory.getExpressData();
			businessData = DataFactory.getBusinessData();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean acceptCargo(String vehicleID, ArrayList<String> orderIDs) {

		BusinessMainController.updateBusinessVO();
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;

		// 根据以下两项可以到对应的营业厅文件夹中查找OrderPO
		// 将此状态更新到原营业厅文件中

		try {
			for (String i : orderIDs) {
				expressData.changeState(OrderState.WAITING_DISTRIBUTE, i);
				expressData.addHistory("快件已到达" + organizationVO.name + ",正准备派送", organizationVO.organizationID, i);
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 获取更新后的OrderPOs
		ArrayList<OrderPO> orderPOs = new ArrayList<OrderPO>();

		try {
			for (String i : orderIDs)
				orderPOs.add(expressData.find(i));

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将OrderPOs添加到本营业厅的当日订单文件中,第二天派件

		try {
			expressData.addDistributingOrder(orderPOs, organizationVO.organizationID);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(d);

		boolean result = false;

		OrganizationPO po = null;
		try {
			po = businessData.getBusinessInfo(organizationVO.organizationID, null).getOrganizationPO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String time2 = sdf2.format(d);
			int num = businessData.getNumOfOrderAcceptReceipt(organizationVO.organizationID);
			String receiptID = "YYTDDD-" + organizationVO.organizationID + "-" + time2 + "-" + num;
			OrderAcceptReceiptPO newPO = new OrderAcceptReceiptPO(po, time,
					businessData.getVehicleInfo(organizationVO.organizationID, vehicleID), orderIDs, receiptID,
					ReceiptState.SUBMIT);

			result = businessData.addReceipt(organizationVO.organizationID, newPO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean orderExist(String id) {
		try {
			if (expressData.find(id) == null)
				return false;
			else
				return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean vehicleExist(String vehicleID) {
		try {
			if (businessData.getVehicleInfo(null, vehicleID) == null)
				return false;
			else
				return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
