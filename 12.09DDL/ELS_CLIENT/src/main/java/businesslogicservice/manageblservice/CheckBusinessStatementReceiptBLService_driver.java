package businesslogicservice.manageblservice;

import vo.BusinessStatementReceiptVO;

public class CheckBusinessStatementReceiptBLService_driver {
	public void drive(CheckBusinessStatementReceiptBLService bsls){
		//这里难道都用null吗
		String beginTime="20150901";
		String endTime="20151001";
		bsls.showBSList(beginTime , endTime);	
		bsls.export(new BusinessStatementReceiptVO(null,null,null,null));
	}
	
	public static void main(String[] args){
		CheckBusinessStatementReceiptBLService bsls=new CheckBusinessStatementReceiptBLService_stub();
		CheckBusinessStatementReceiptBLService_driver driver=new CheckBusinessStatementReceiptBLService_driver();
		driver.drive(bsls);
	}

}
