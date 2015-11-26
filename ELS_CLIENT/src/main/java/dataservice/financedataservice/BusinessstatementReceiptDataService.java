package dataservice.financedataservice;

import java.util.ArrayList;

import po.BusinessStatementReceiptPO;
import po.CollectionReceiptPO;
import po.PaymentReceiptPO;

public interface BusinessstatementReceiptDataService {
	//获取所有入款单
    public ArrayList<CollectionReceiptPO> getCollection(String beginTime,String endTime);
	//获取所有付款单
	public ArrayList<PaymentReceiptPO>getPayment(String beginTime,String endTime);
	//显示经营情况表
	public BusinessStatementReceiptPO showBSL(String beginTime,String endTime) ;
	//显示所有经营情况表
	public ArrayList<BusinessStatementReceiptPO> showAllBSList();
	//导出
	public int export(BusinessStatementReceiptPO po);
	
	
}
