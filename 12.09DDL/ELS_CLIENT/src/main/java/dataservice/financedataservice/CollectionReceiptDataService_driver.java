package dataservice.financedataservice;

import java.rmi.RemoteException;

import po.CollectionReceiptPO;

public class CollectionReceiptDataService_driver {
	public void drive(CollectionReceiptDataService cds) throws RemoteException{
		cds.createCollection(new CollectionReceiptPO());
		cds.getAllCollection();
		cds.findByID(null);
//		cds.getGathering(null);
//		cds.getMoney(null);
//		cds.getTotalMoney(null);
		cds.getNum();
		cds.modify(new CollectionReceiptPO());
	}
	
	public static void main(String[] args) throws RemoteException{
		CollectionReceiptDataService cds=new CollectionReceiptDataService_stub();
		CollectionReceiptDataService_driver driver=new CollectionReceiptDataService_driver();
		driver.drive(cds);
	}
}
