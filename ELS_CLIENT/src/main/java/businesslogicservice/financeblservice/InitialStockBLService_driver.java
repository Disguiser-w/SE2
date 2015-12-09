package businesslogicservice.financeblservice;

import java.rmi.RemoteException;

public class InitialStockBLService_driver {
	public void drive(InitialStockBLService ibs) throws RemoteException{
		String time="20150101";
		ibs.initInfo(time);
		ibs.getInitInfo(time);
		ibs.getAllInitInfo();		
	}
	
	public static void main() throws RemoteException{
		InitialStockBLService ibs=new InitialStockBLService_stub();
		InitialStockBLService_driver driver=new InitialStockBLService_driver();
		driver.drive(ibs);
	}

}
