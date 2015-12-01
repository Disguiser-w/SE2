package businesslogic.managebl.controller;

import vo.BusinessStatementReceiptVO;
import businesslogic.managebl.CheckBusinessStatementReceiptBL;

public class CheckBusinessStatementReceiptController {
	
	private CheckBusinessStatementReceiptBL cbsr;
	
	public BusinessStatementReceiptVO showBSList(String beginTime,String endTime){
		return cbsr.showBSList(beginTime, endTime);
	}
	
	public int export(BusinessStatementReceiptVO vo){
		return cbsr.export(vo);
	}
	
}
