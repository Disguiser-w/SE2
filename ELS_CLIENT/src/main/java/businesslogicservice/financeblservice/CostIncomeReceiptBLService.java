package businesslogicservice.financeblservice;

import java.util.ArrayList;

import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.PaymentReceiptVO;

public interface CostIncomeReceiptBLService {
	//创建成本收益表
	public int creatCostIncomeList(CostIncomeReceiptVO vo);
	//获取成本收益表
	public CostIncomeReceiptVO getCostIncomeList(String s);
	//获取所有成本收益表
	public ArrayList<CostIncomeReceiptVO> getAllCostIncomeList();
	//获取入款单
	public ArrayList<CollectionReceiptVO> getCollection();
	//获取付款单
	public ArrayList<PaymentReceiptVO>getPayment();
	//获取总收入
	public double getIncome(CollectionReceiptVO vo);
	//获取总支出
	public double getCost(PaymentReceiptVO vo);
	//获取总利润
	public double getProfit(double income,double cost);
	//自动生成ID
	public String getCostIncomeListID();
	
	
//	//获取时间
//	public String getDate();
//	//获取金额
//	public double getMoney();
//	//获取总金额
//	public double getTotalMoney();

	

}
