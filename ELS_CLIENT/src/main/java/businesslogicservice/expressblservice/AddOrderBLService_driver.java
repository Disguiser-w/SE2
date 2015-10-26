package businesslogicservice.expressblservice;

import vo.OrderVO;

public class AddOrderBLService_driver {
	public void drive(AddOrderBLService addOrderBLService) {
		addOrderBLService.addOrder(new OrderVO());
	}

	public void main(String[] args){
		(new AddOrderBLService_driver()).drive(new AddOrderBLService_stub());
	}
}
