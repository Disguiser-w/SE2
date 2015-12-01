package businesslogic.financebl.controller;

import vo.BusinessStatementReceiptVO;
import businesslogic.financebl.BusinessStatementReceiptBL;
import businesslogicservice.financeblservice.BusinessstatementReceiptBLService;

public class BusinessStatementReceiptBLController implements BusinessstatementReceiptBLService{

	private BusinessStatementReceiptBL businessStatementReceiptBL;
	public BusinessStatementReceiptVO showBSList(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		return businessStatementReceiptBL.showBSList(beginTime, endTime);
	}

	public int export(BusinessStatementReceiptVO vo) {
		// TODO Auto-generated method stub
		return businessStatementReceiptBL.export(vo);
	}

}
