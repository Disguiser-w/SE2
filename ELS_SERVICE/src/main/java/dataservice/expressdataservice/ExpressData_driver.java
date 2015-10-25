package dataservice.expressdataservice;

public class ExpressData_driver {
	public void drive(ExpressDataService expressDataService) {
		expressDataService.getBaseCost();

		expressDataService.chargeCollection(new ExpressPO());

		expressDataService.getExpressInfos();
	}

	public void main(String[] args){
		(new ExpressData_driver()).drive(new ExpressData_stub());
	}
}
