package businesslogicservice.manageblservice;

public class ReviewReceiptBLService_driver {
	
	public void drive(ReviewReceiptBLService rrs){
		String[] str = {"20151001", "20151002","20151003"};
		//rrs.batch(str, null);
		rrs.approve("20151001", null);
		//rrs.reply("20151001");
		rrs.getAllReceiptList();
		rrs.refresh();
	}
	
	public void main(){
		ReviewReceiptBLService cbs=new ReviewReceiptBLService_stub();
		ReviewReceiptBLService_driver driver=new ReviewReceiptBLService_driver();
		driver.drive(cbs);
	}
}
