package businesslogicservice.expressblservice;

public class LogisticQueryBL_driver {
	public void drive(LogisticQueryBLService logisticQueryBLService ) {
		logisticQueryBLService.query(1000);
	}

	public void main(String[] args){
		(new LogisticQueryBL_driver()).drive(new LogisticQueryBL_stub());
	}
}
