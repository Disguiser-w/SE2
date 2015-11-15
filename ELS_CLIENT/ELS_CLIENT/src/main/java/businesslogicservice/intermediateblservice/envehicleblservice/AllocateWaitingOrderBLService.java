package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.OrderVO;
import vo.TransferingReceiptVO;

public interface AllocateWaitingOrderBLService {
	public ArrayList<OrderVO> updateWaitingList(TransferingReceiptVO vo);

	public OrderVO showOrder(String orderID);
}
