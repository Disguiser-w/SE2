package businesslogicservice.expressblservice;

public class LogisticQueryBLService_driver {
	public void drive(LogisticQueryBLService logisticQueryBLService ) {
		logisticQueryBLService.query("");
	}

	public void main(String[] args){
		(new LogisticQueryBLService_driver()).drive(new LogisticQueryBLService_stub());
	}
}
