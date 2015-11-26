package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.OrderAcceptReceiptPO;
import po.OrderPO;
import po.VehiclePO;
import type.OrderState;
import vo.OrderAcceptReceiptVO;
import vo.VehicleVO;

public class AcceptCargo {

	private BusinessDataService businessData;
	private ExpressDataService expressData;

	public AcceptCargo() {
		// 使用RMI初始化businessDataService
		businessData = new BusinessDataService_stub();
		expressData = new ExpressDataService_stub();
	}

	public boolean acceptCargo(ArrayList<String> orderIDs, String driverName) {
		// 根据以下两项可以到对应的营业厅文件夹中查找OrderPO

		ArrayList<OrderPO> orderPOs = new ArrayList<OrderPO>();

		try {
			for (String i : orderIDs)
				orderPOs.add(ExpressMainController.expressData.find(i));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 改变订单的状态
		for (OrderPO i : orderPOs)
			i.setOrder_state(OrderState.WAITING_DISTRIBUTE);
		//将此状态更新到

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(d);

		// 要将订单信息从原营业厅orderID中删除（还是不要了），只是加到本营业厅的当日订单中

		boolean result = false;
		return result;
	}

	public static OrderAcceptReceiptPO voToPO(OrderAcceptReceiptVO vo) {

		VehiclePO vpo = VehicleManager.voToPO(vo.vehicleVO);
		OrderAcceptReceiptPO opo = new OrderAcceptReceiptPO(vo.local, vo.time, vpo, vo.orderIDs);
		return opo;

	}

	public static OrderAcceptReceiptVO poToVO(OrderAcceptReceiptPO po) {
		VehicleVO vvo = VehicleManager.poToVO(po.getVehiclePO());
		OrderAcceptReceiptVO Vpo = new OrderAcceptReceiptVO(po.getLocal(), po.getTime(), vvo, po.getOrderIDs());
		return Vpo;
	}

}
