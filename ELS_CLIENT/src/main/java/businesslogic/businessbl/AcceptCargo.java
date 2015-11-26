package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.OrderAcceptReceiptPO;
import po.OrderPO;
import po.OrganizationPO;
import type.OrderState;
import vo.OrganizationVO;

public class AcceptCargo {

	private BusinessDataService businessData;
	private ExpressDataService expressData;

	public AcceptCargo() {
		// 使用RMI初始化businessDataService
		businessData = new BusinessDataService_stub();
		expressData = new ExpressDataService_stub();
	}

	public boolean acceptCargo(String organizationID, String vehicleID, ArrayList<String> orderIDs) {

		BusinessMainController.updateBusinessVO();
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;
		ExpressDataService expressData = BusinessMainController.expressData;
		BusinessDataService businessData = BusinessMainController.businessData;
		// 根据以下两项可以到对应的营业厅文件夹中查找OrderPO
		// 将此状态更新到原营业厅文件中

		try {
			for (String i : orderIDs){
				expressData.changeState(OrderState.WAITING_DISTRIBUTE, i);
				expressData.addHistory("快件已到达"+organizationVO.name+",正准备派送", organizationID, i);
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
			for (OrderPO i : orderPOs)
				expressData.addDistributingOrder(i, organizationVO.organizationID);
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
			po = businessData.getBusinessInfo(organizationID, null).getOrganizationPO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			此处增加ID
			OrderAcceptReceiptPO newPO = new OrderAcceptReceiptPO(po, time,
					businessData.getVehicleInfo(organizationID, vehicleID), orderIDs);
			
			result = businessData.addReceipt(organizationVO.organizationID, newPO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
