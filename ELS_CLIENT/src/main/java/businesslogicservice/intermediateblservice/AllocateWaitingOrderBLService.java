package businesslogicservice.intermediateblservice;

import java.util.ArrayList;

import vo.OrderVO;

public interface AllocateWaitingOrderBLService {
	public ArrayList<OrderVO> updateWaitingList();

	public OrderVO showOrder(String orderID) throws Exception;
}
