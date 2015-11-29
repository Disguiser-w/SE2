package businesslogic.managebl;

import vo.BusinessStatementReceiptVO;
import businesslogicservice.manageblservice.CheckBusinessStatementReceiptBLService;
import businesslogic.financebl.BusinessStatementReceiptBL;

public class CheckBusinessStatementReceiptBL implements CheckBusinessStatementReceiptBLService{
	
	private BusinessStatementReceiptBL bsrBL;
	
	public BusinessStatementReceiptVO showBSList(String beginTime,String endTime){
		return bsrBL.showBSList(beginTime, endTime);
	}
	
	//导出excel表格
	public int export(BusinessStatementReceiptVO vo){
		return bsrBL.export(vo);
	}
	
}
