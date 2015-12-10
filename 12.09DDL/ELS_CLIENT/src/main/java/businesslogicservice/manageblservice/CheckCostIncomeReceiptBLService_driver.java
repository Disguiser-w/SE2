package businesslogicservice.manageblservice;

public class CheckCostIncomeReceiptBLService_driver {
	public void drive(CheckCostIncomeReceiptBLService cbs) {
		String s = "CBSYB-20151024-00001";
		cbs.getCostIncomeList(s);
	}

	public void main() {
		CheckCostIncomeReceiptBLService cbs = new CheckCostIncomeReceiptBLService_stub();
		CheckCostIncomeReceiptBLService_driver driver = new CheckCostIncomeReceiptBLService_driver();
		driver.drive(cbs);
	}

}
