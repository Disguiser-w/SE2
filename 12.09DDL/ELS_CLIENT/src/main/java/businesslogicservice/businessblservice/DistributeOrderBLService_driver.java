package businesslogicservice.businessblservice;

public class DistributeOrderBLService_driver {
	public void drive(DistributeOrderBLService distributeOrdeBLService) {
//		distributeOrdeBLService.distributeOrder();

//		distributeOrdeBLService.getExpressInfos();
//
//		distributeOrdeBLService.getSendOrder();
	}

	public void main(String[] args) {
		(new DistributeOrderBLService_driver()).drive(new DistributeOrderBLService_stub());
	}
}
