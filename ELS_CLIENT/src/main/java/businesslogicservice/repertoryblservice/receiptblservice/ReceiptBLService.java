package businesslogicservice.receiptblservice;

import java.rmi.RemoteException;

public interface ReceiptBLService {

	public int approve(String receiptID) throws RemoteException;
	public void see(Object receipt) throws RemoteException;
	
}
