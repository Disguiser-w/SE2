package dataservice.financedataservice;

import java.rmi.RemoteException;

import po.InitInfoPO;

public class InitialStockDataService_driver {
	public void drive(InitialStockDataService ids) throws RemoteException{
		ids.initInfo(new InitInfoPO());
		ids.getInitInfo(null);
		ids.getAllInitInfo();
	}
	public static void main(String[] args) throws RemoteException{
		InitialStockDataService ids=new InitialStockDataService_stub();
		InitialStockDataService_driver driver=new InitialStockDataService_driver();
		driver.drive(ids);
	}
	
	

}
