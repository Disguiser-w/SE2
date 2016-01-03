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
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import po.EnVehicleReceiptPO;
import po.OrderPO;
import po.VehiclePO;
import type.OrderState;
import type.OrganizationType;
import type.ReceiptState;
import vo.OrganizationVO;

public class EnVehicle {
	private ExpressDataService expressData;
	private BusinessDataService businessData;

	public EnVehicle() {

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

	// 从前一天的订单列表中找出待转运的订单(今天和昨天的)，按目的地分配给车辆
	public ArrayList<String[]> autoTruckLoading() {
		BusinessMainController.updateBusinessVO();
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date yesTaday = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time1 = df.format(yesTaday);
		String time2 = df.format(today);
		// 获取订单
		ArrayList<OrderPO> distributingPo = new ArrayList<OrderPO>();
		ArrayList<OrderPO> po = null;
		try {
			po = expressData.getOrderInfosByTime(organizationVO.organizationID, time1);
			ArrayList<OrderPO> po2 = expressData.getOrderInfosByTime(organizationVO.organizationID, time2);
			for (OrderPO i : po2)
				po.add(i);
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

		String nowTime = df.format(Calendar.getInstance().getTime());

		ArrayList<EnVehicleReceiptPO> enVehicleReceiptPOs = new ArrayList<EnVehicleReceiptPO>();
		ArrayList<String[]> result = new ArrayList<String[]>();

		int j = 0;
		for (VehiclePO i : vehiclePO) {

			// 对每一辆车,生成一个装车单，放在装车单的ArrayList中
			EnVehicleReceiptPO enVehicle = new EnVehicleReceiptPO();

			enVehicle.setReceiptState(ReceiptState.SUBMIT);
			enVehicle.setPlaceOfDeparture(OrganizationBL.organizationVOToPO(organizationVO));
			enVehicle.setTime(nowTime);
			enVehicle.setVehiclePO(i);
			ArrayList<String> orderIDs = new ArrayList<String>();

			boolean hasOrder = false;
			// 情况1,到同一城市的中转中心(判断后缀)
			// 情况2,到同一城市营业厅
			int num = 0;
			for (; num < distributingPo.size(); num++) {
				OrderPO o = distributingPo.get(num);
				// 中转中心车辆
				if (i.getDestination().getCategory() == OrganizationType.intermediateCenter) {
					// 判断该订单是否要中转
					if (!o.getSenderAddress().split("-")[0].equals(o.getRecipientAddress().split("-")[0])) {
						orderIDs.add(o.getID());
						result.add(new String[] { i.getID(), BusinessMainController.businessVO.organizationVO.name,
								i.getDestinationCity(), o.getID() });
						hasOrder = true;
						distributingPo.remove(num);
						num--;
					}
					// 营业厅车辆,例如：南京鼓楼 到 南京仙林
				} else if (i.getDestinationCity().contains(o.getRecipientAddress().split(" ")[1])) {
					orderIDs.add(o.getID());

					result.add(new String[] { i.getID(), BusinessMainController.businessVO.organizationVO.name,
							i.getDestinationCity(), o.getID() });
					hasOrder = true;
					distributingPo.remove(num);
					num--;
				}
			}
			if (hasOrder) {
				try {
					businessData.addDriverTime(organizationVO.organizationID, i.getDriver().getID());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			enVehicle.setOrderPOList(orderIDs);

			String newTime = (new SimpleDateFormat("yyyMMdd")).format(new Date());

			enVehicle.setReceiptID("YYTZCD-" + organizationVO.organizationID + "-" + newTime + "-" + j);
			j += 1;

			enVehicleReceiptPOs.add(enVehicle);

		}

		// 存在今日文件中
		if (result.size() != 0)
			try {
				businessData.addEnVehicleReceipt(organizationVO.organizationID, enVehicleReceiptPOs);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return result;
	}

}
