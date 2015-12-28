package businesslogicservice.expressblservice;

/**
 * 快递员-收费信息汇总
 */
public interface ChargeCollectionBLService {

	/**
	 * 系统更新快递员信息（更新了其收费信息这个属性）
	 */
	public boolean chargeCollection();
}
