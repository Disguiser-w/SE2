package businesslogicservice.businessblservice;

import java.util.ArrayList;

/**
 * 营业厅业务员-收款汇总
 */
public interface GatheringBLService {
	/**
	 * 系统返回营业厅所有快递员的信息
	 */
	public ArrayList<String> getChargeInfo();

	/**
	 * 系统自动汇总所有快递员的收费，并生成收款单,返回收款总额
	 */
	public double gathering();
}
