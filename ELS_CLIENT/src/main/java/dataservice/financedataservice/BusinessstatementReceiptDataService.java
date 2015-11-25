package dataservice.financedataservice;

import java.util.ArrayList;

import po.BusinessstatementReceiptPO;
import po.CollectionReceiptPO;
import po.PaymentReceiptPO;

public interface BusinessstatementReceiptDataService {
	//获取所有入款单
    public ArrayList<CollectionReceiptPO> getCollection(String beginTime,String endTime);
	//获取所有付款单
	public ArrayList<PaymentReceiptPO>getPayment(String beginTime,String endTime);
	//显示经营情况表
	public BusinessstatementReceiptPO showBSL(String beginTime,String endTime) ;
	//显示所有经营情况表
	public ArrayList<BusinessstatementReceiptPO> showAllBSList();
	//导出
	public int export(BusinessstatementReceiptPO po);
	
	
}
