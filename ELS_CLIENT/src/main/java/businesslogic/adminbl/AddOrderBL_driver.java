package businesslogic.adminbl;

public class AddOrderBL_driver {
	public void drive(AddOrderBLService addOrderBLService) {
		addOrderBLService.addOrder(new OrderVO());
	}

	public void main(String[] args){
		(new AddOrderBL_driver()).drive(new AddOrderBLService_stub());
	}
}
