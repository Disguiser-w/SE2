package businesslogicservice.manageblservice;

import java.util.ArrayList;

import vo.BusinessStatementReceiptVO;

public interface BusinessStatementReceiptBLService {
		//输出符合时间的经营情况表
		public BusinessStatementReceiptVO showBSList(String beginTime,String endTime);
		//输出所有的经营情况表
		public ArrayList<BusinessStatementReceiptVO> showAllBSList();
		//导出excel表格
		public int export(BusinessStatementReceiptVO vo);
}
