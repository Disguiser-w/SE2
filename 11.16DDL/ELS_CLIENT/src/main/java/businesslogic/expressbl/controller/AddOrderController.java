package businesslogic.expressbl.controller;

import businesslogic.expressbl.AddOrder;
import businesslogicservice.expressblservice.AddOrderBLService;
import vo.OrderVO;

public class AddOrderController implements AddOrderBLService{

	private AddOrder addOrder;
	public AddOrderController(){
		addOrder=new AddOrder();
	}
	
	public boolean addOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		return addOrder.addOrder(vo);
	}

	public OrderVO calculateCost(OrderVO vo) {
		// TODO Auto-generated method stub
		return addOrder.calculateCost(vo);
	}

}
