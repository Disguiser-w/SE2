package businesslogicservice.manageblservice;

import vo.CostIncomeReceiptVO;

public interface CheckCostIncomeReceiptBLService {
	// 获取成本收益表
	public CostIncomeReceiptVO getCostIncomeList(String s);

}
