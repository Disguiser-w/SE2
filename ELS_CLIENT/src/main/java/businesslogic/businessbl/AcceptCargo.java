package businesslogic.businessbl;

import java.util.ArrayList;

import businesslogic.expressbl.AddOrder;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import po.OrderAcceptReceiptPO;
import po.OrderPO;
import po.VehiclePO;
import vo.OrderAcceptReceiptVO;
import vo.OrderVO;

public class AcceptCargo {

	private BusinessDataService businessDataService;

	public AcceptCargo() {
		// 使用RMI初始化businessDataService
		businessDataService = new BusinessDataService_stub();
	}

	public boolean acceptCargo(OrderAcceptReceiptVO vo) {
		boolean result = false;

		try {

			businessDataService.addReceipt(voToPO(vo));
		} catch (Exception e) {
			// 网络连接错误处理
			e.printStackTrace();
		}

		return result;
	}

	public static OrderAcceptReceiptPO voToPO(OrderAcceptReceiptVO vo) {

		VehiclePO vpo = VehicleManager.voToPO(vo.vehicleVO);

		ArrayList<OrderPO> pos = new ArrayList<OrderPO>();

		for (OrderVO i : vo.orderVOList) {
			pos.add(AddOrder.voToPO(i));
		}

		OrderAcceptReceiptPO opo = new OrderAcceptReceiptPO(vo.local, vo.time, vpo, pos);
		return opo;

	}

}
