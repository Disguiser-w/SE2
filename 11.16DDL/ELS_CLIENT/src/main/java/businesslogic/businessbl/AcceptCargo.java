package businesslogic.businessbl;

import dataservice.businessdataservice.BusinessDataService;
import po.OrderAcceptReceiptPO;
import vo.OrderAcceptReceiptVO;

public class AcceptCargo {

	private BusinessDataService businessDataService;

	public AcceptCargo() {
		// 使用RMI初始化businessDataService
	}

	public boolean acceptCargo(OrderAcceptReceiptVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	public static OrderAcceptReceiptPO voTOPO(OrderAcceptReceiptVO vo) {
		OrderAcceptReceiptPO po = new OrderAcceptReceiptPO();
		return po;

	}

}
