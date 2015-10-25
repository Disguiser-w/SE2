package dataservice.expressdataservice;
/**
 * 快递员数据
 * */
public interface ExpressDataService {
	/**
	 * 返回订单费用的基本信息CostBasePO
	 * */
	public CostBasePO getBaseCost();
	/**
	 * 汇总快递员的收费
	 * */
	public boolean chargeCollection(ExpressPO po);
	/**
	 * 返回快递员的信息ExpressPO
	 * */
	public ExpressPO getExpressInfos();
}
