package businesslogicservice.manageblservice;

import vo.BusinessStatementReceiptVO;

public class BusinessStatementReceiptBLService_driver {
	public void drive(BusinessStatementReceiptBLService bsls){
		//这里难道都用null吗
		String beginTime="20150901";
		String endTime="20151001";
		bsls.showBSList(beginTime , endTime);	
		bsls.showAllBSList();
		bsls.export(new BusinessStatementReceiptVO(null,null,null,null));
	}
	
	public static void main(String[] args){
		BusinessStatementReceiptBLService bsls=new BusinessStatementReceiptBLService_stub();
		BusinessStatementReceiptBLService_driver driver=new BusinessStatementReceiptBLService_driver();
		driver.drive(bsls);
	}

}
