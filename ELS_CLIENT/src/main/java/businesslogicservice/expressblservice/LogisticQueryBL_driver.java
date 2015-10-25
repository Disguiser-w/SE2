package businesslogic.adminbl;

public class LogisticQueryBL_driver {
	public void drive(LogisticQueryBLService sogisticQueryBLService ) {
		LogisticQueryBLService.query(orderNum);
	}

	public void main(String[] args){
		(new LogisticQueryBLService_driver()).drive(new LogisticQueryBL_stub());
	}
}
