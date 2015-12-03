package businesslogicservice.manageblservice;

public class ReviewReceiptBLService_driver {
	
	public void drive(ReviewReceiptBLService rrs){
		String[] str = {"20151001", "20151002","20151003"};
		rrs.batch(str);
		rrs.approve("20151001");
		rrs.reply("20151001");
		rrs.getReceiptList();
		rrs.refresh();
	}
	
	public void main(){
		ReviewReceiptBLService cbs=new ReviewReceiptBLService_stub();
		ReviewReceiptBLService_driver driver=new ReviewReceiptBLService_driver();
		driver.drive(cbs);
	}
}
