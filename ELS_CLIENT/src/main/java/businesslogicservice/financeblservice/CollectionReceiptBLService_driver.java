package businesslogicservice.financeblservice;

import vo.CollectionReceiptVO;

public class CollectionReceiptBLService_driver {
	public void drive(CollectionReceiptBLService cbs){
		cbs.creatCollection(new CollectionReceiptVO(null, null, null, null, null, 0, null, null));
//		String HallID="0250001";
		String time="20151022";
		String s="HJSKD-20151010-00001";
		cbs.getCollection(s);
		cbs.getAllCollection();
		cbs.getGathering(time);
		cbs.getMoney(null);
		cbs.getTotalMoney(null);
	}
	
	public static void main(String[] args){
		CollectionReceiptBLService cbs=new CollectionReceiptBLService_stub();
		CollectionReceiptBLService_driver driver=new CollectionReceiptBLService_driver();
		driver.drive(cbs);
	}

}
