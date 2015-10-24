package businesslogic.businessbl;

public class DistributeOrderBL_driver {
	public void drive(DistributeOrderBLService distributeOrdeBLService) {
		distributeOrdeBLService.distributeOrder();

		distributeOrdeBLService.getExpressInfos();

		distributeOrdeBLService.getSendOrder();
	}

	public void main(String[] args) {
		(new DistributeOrderBL_driver()).drive(new DistributeOrdeBL_stub());
	}
}
