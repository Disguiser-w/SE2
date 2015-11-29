package businesslogicservice.manageblservice;

import vo.BusinessStatementReceiptVO;

public interface CheckBusinessStatementReceiptBLService {
		//输出符合条件的经营情况表
		public BusinessStatementReceiptVO showBSList(String beginTime,String endTime);
		
		//导出excel表格
		public int export(BusinessStatementReceiptVO vo);
}
