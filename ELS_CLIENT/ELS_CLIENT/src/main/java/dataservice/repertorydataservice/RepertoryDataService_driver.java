package dataservice.repertorydataservice;

import dataservice.repertorydataservice.RepertoryDataService_stub;
import po.RepertoryPO;
import po.GoodsPO;

import java.rmi.RemoteException;

public class RepertoryDataService_driver {

	public void drive(RepertoryDataService_stub repertoryDataService) throws RemoteException {
	    repertoryDataService.modifyRepertory(new RepertoryPO("025-0-CK","CK-00001", 100, 10, 10, 50));
		repertoryDataService.findRepertory("025-0-CK");
		repertoryDataService.addGoods("025-0-CK", null);
		repertoryDataService.deleteGoods("025-0-CK", null);
		repertoryDataService.modifyGoods("025-0-CK", null);
		repertoryDataService.findGoodsbyID("025-0-CK", "JJD-20151001-00001");
		repertoryDataService.findGoodsbyDate("025-0-CK", "20151001", "20151002");
		repertoryDataService.findGoodsbyTime("025-0-CK", "20151002 17:00:00");
    }
	
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		RepertoryDataService_stub repertory_data_service = new RepertoryDataService_stub();
		RepertoryDataService_driver driver = new RepertoryDataService_driver();
		driver.drive(repertory_data_service);
	}
	 
}
