/*
 * 感觉这个我目前只能直接手动输入了
 * 
 */
package businesslogicservice.financeblservice;

import java.util.ArrayList;

import type.OperationState;
import vo.PaymentReceiptVO;
/**
 * 创建付款单
 * */
public interface PaymentReceiptBLService {
	//创建付款单
	public int creatPaymentReceipt(PaymentReceiptVO vo);
	//获取付款单(这里是时间点还是时间段？？？...暂定时间是年月份)
	public PaymentReceiptVO getPaymentReceipt(String s);
	//获取所有付款单
	public ArrayList<PaymentReceiptVO> getAllPaymentReceipt();
	//自动获取付款单编号
	public String getPaymentReceiptListID();
	
	//获取薪水：关于业绩清0的事的处理
	public double getSalary(String time);
	//获取运费
	public double getFare(String time);
	//获取租金
	public double getRent(String time);
	
	//获取未审批的入款单
	public ArrayList<PaymentReceiptVO> getUnapprovedPaymentReceipt();
	
	//获取总经理审批后的结果
	public int saveSubmittedPaymentReceiptInfo(PaymentReceiptVO vo);
	
	//账户金额的增减
	public int excute(PaymentReceiptVO vo);
	
	
//	//获取日期
//	public String getDate();
//	//获取条目名称
//	public String getClause();
//	//获取金额
//	public String getMoney();

	
	

}
