package businesslogic.businessbl.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.businessbl.DistributeOrder;
import businesslogicservice.businessblservice.DistributeOrderBLService;

public class DistributeOrderController implements DistributeOrderBLService{

	private DistributeOrder distributeOrder;

	public DistributeOrderController() {
		distributeOrder = new DistributeOrder();
	}

	public ArrayList<String> distributeOrder(){
		// TODO Auto-generated method stub
		return distributeOrder.distributeOrder();
	}

}
