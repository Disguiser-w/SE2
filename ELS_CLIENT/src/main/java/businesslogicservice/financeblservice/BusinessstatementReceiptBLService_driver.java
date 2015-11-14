package businesslogicservice.financeblservice;

import vo.BusinessStatementReceiptVO;

public class BusinessstatementReceiptBLService_driver {
	public void drive(BusinessstatementReceiptBLService bsls){
		//这里难道都用null吗
		String beginTime="20150901";
		String endTime="20151001";
		bsls.showBSList(beginTime , endTime);	
		bsls.showAllBSList();
		bsls.export(new BusinessStatementReceiptVO(endTime, endTime, null, null));
	}
	
	public static void main(String[] args){
		BusinessstatementReceiptBLService bsls=new BusinessstatementReceiptBLService_stub();
		BusinessstatementReceiptBLService_driver driver=new BusinessstatementReceiptBLService_driver();
		driver.drive(bsls);
		
	}

}
