package businesslogicservice.financeblservice;

import java.util.ArrayList;

import vo.BusinessstatementReceiptVO;
/**
 * 查看经营情况表
 * */
public interface BusinessstatementReceiptBLService {
	//输出符合时间的经营情况表
	public BusinessstatementReceiptVO showBSList(String beginTime,String endTime);
	//输出所有的经营情况表
	public ArrayList<BusinessstatementReceiptVO> showAllBSList();
	//导出excel表格
	public int export(BusinessstatementReceiptVO vo);

}
