package businesslogicservice.intermediateblservice.envehicleblservice;


public class EntrainingBLService_driver {
	public void drive(TrainManagerBLService ebls) {
		ebls.showTrainList();
		// ebls.showTrain(new String());
		// ebls.entrain(new ArrayList<OrderVO>());
		// ebls.updateEntrainingReceiptList(new
		// EntrainingReceiptVO(null,null,null,null,null));
		// ebls.computeFare(new ArrayList<EntrainingReceiptVO>());
		// ebls.updateFare(null);
		// ebls.showEntrainingReceiptList(new ArrayList<EntrainingReceiptVO>());
		// ebls.updateEntrainingReceipt(new ArrayList<EntrainingReceiptVO>());
	}

	public static void main(String[] args) {
		EntrainingBLService_driver driver = new EntrainingBLService_driver();
		driver.drive(new EntrainingBLService_stub());
	}
}
