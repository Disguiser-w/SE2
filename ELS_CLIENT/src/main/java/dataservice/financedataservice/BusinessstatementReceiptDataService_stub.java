package dataservice.financedataservice;

import java.util.ArrayList;

import po.BusinessstatementReceiptPO;
import po.CollectionReceiptPO;
import po.PaymentReceiptPO;

public class BusinessstatementReceiptDataService_stub implements BusinessstatementReceiptDataService{

	public BusinessstatementReceiptPO showBSL(String beginTime,String endTime) {
		// TODO Auto-generated method stub
		System.out.println("BusinessstatementList is shown successfully!");
		return null;
	}

	public ArrayList<BusinessstatementReceiptPO> showAllBSList() {
		// TODO Auto-generated method stub
		System.out.println("All is shown successfully!");
		return null;
	}

	public int export(BusinessstatementReceiptPO po) {
		// TODO Auto-generated method stub
		System.out.println("Export successfully!");
		return 0;
	}

	@Override
	public ArrayList<CollectionReceiptPO> getCollection(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PaymentReceiptPO> getPayment(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
