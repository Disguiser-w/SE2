package dataservice.financedataservice;

import java.util.ArrayList;

import po.BusinessStatementReceiptPO;

public interface BusinessstatementReceiptDataService {
	public BusinessStatementReceiptPO showBSL(String beginTime,String endTime) ;
	public ArrayList<BusinessStatementReceiptPO> showAllBSList();
	public int export(BusinessStatementReceiptPO po);
}
