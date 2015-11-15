package businesslogicservice.manageblservice;

public class ReviewReceiptBLService_driver {
	
	public void drive(ReviewReceiptBLService rrs){
		rrs.reviewReceipt("20151001-00001");
	}
	
	public void main(){
		ReviewReceiptBLService cbs=new ReviewReceiptBLService_stub();
		ReviewReceiptBLService_driver driver=new ReviewReceiptBLService_driver();
		driver.drive(cbs);
	}
}
