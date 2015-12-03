package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import po.EnVehicleReceiptPO;
import po.OrderPO;
import po.VehiclePO;
import type.OrderState;
import vo.OrganizationVO;

public class EnVehicle {

	// 从前一天的订单列表中找出待转运的订单，按目的地分配给车辆
	public ArrayList<String> autoTruckLoading(String OrganizationID) {
		BusinessMainController.updateBusinessVO();
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;
		ExpressDataService expressData = BusinessMainController.expressData;
		BusinessDataService businessData = BusinessMainController.businessData;

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(date);
		// 获取订单
		ArrayList<OrderPO> distributingPo = new ArrayList<OrderPO>();
		ArrayList<OrderPO> po = null;
		try {
			po = expressData.getOrderInfosByTime(organizationVO.organizationID, time);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (OrderPO i : po)
			if (i.getOrder_state() == OrderState.WAITING_ENVEHICLE) {
				distributingPo.add(i);
				// 更新状态,增加历史
				try {
					expressData.changeState(OrderState.TRANSFERING, i.getID());
					expressData.addHistory("快件已从" + organizationVO.name + "发出", organizationVO.organizationID,
							i.getID());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		// 获取车辆

		ArrayList<VehiclePO> vehiclePO = null;
		try {
			vehiclePO = businessData.getVehicleInfos(organizationVO.organizationID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 开始根据车辆目的地和订单目的地来分配订单

		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = df.format(Calendar.getInstance().getTime());

		ArrayList<EnVehicleReceiptPO> enVehicleReceiptPOs = new ArrayList<EnVehicleReceiptPO>();
		ArrayList<String> result = new ArrayList<String>();

		int j = 0;
		for (VehiclePO i : vehiclePO) {
			// 对每一辆车,生成一个装车单，放在装车单的ArrayList中
			EnVehicleReceiptPO enVehicle = new EnVehicleReceiptPO();

			enVehicle.setPlaceOfDeparture(OrganizationBL.organizationVOToPO(organizationVO));
			enVehicle.setTime(nowTime);
			enVehicle.setVehiclePO(i);
			ArrayList<String> orderIDs = new ArrayList<String>();

			for (OrderPO o : distributingPo)
				if (o.getRecipientAddress().split(" ")[1].equals(i.getDestinationCity())) {
					orderIDs.add(o.getID());
					result.add(o.getID() + " " + o.getSenderAddress().split(" ")[1] + " " + i.getDestinationCity() + " "
							+ i.getID());
				}
			enVehicle.setOrderPOList(orderIDs);

			String newTime = (new SimpleDateFormat("yyyMMdd")).format(new Date());

			enVehicle.setReceiptID("YYTZCD-"+organizationVO.organizationID + "-" + newTime + "-" + j);
			j += 1;

			enVehicleReceiptPOs.add(enVehicle);

		}

		// 存在今日文件中
		try {
			businessData.addEnVehicleReceipt(organizationVO.organizationID, enVehicleReceiptPOs);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
