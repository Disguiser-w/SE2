package dataservice.financedataservice;

import java.rmi.RemoteException;

import po.BusinessstatementReceiptPO;

public class BusinessstatementReceiptDataService_driver {
	public void drive(BusinessstatementReceiptDataService bds) throws RemoteException{
		String beginTime="20150101";
		String endTime="20151001";
		bds.showBSL(beginTime,endTime);
		bds.showAllBSList();
		bds.export(new BusinessstatementReceiptPO());
	}
	
	public static void main(String[] args) throws RemoteException{
		BusinessstatementReceiptDataService bds=new BusinessstatementReceiptDataService_stub();
		BusinessstatementReceiptDataService_driver driver=new BusinessstatementReceiptDataService_driver();
		driver.drive(bds);
		
	}

}
