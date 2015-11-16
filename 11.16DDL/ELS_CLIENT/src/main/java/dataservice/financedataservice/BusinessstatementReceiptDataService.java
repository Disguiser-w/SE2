package dataservice.financedataservice;

import java.util.ArrayList;

import po.BusinessstatementReceiptPO;

public interface BusinessstatementReceiptDataService {
	public BusinessstatementReceiptPO showBSL(String beginTime,String endTime) ;
	public ArrayList<BusinessstatementReceiptPO> showAllBSList();
	public int export(BusinessstatementReceiptPO po);
}
