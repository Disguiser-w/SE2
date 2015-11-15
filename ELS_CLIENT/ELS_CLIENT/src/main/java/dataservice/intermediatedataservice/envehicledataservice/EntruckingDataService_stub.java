package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntruckingReceiptPO;
import po.OrderPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import po.FarePO;

public class EntruckingDataService_stub implements EntruckingDataService {

	public ArrayList<TruckPO> getTruckList() {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return null;
	}

	public TruckPO getTrain(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return null;
	}

	public ArrayList<OrderPO> updateWaitingList() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}

	public EntruckingReceiptPO entruck() {
		// TODO 自动生成的方法存根
		System.out.println("entruck successfully!");
		return null;
	}

	public ArrayList<EntruckingReceiptPO> updateEntruckingReceiptList() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}

	public FarePO computeFare() {
		// TODO 自动生成的方法存根
		System.out.println("compute successfully!");
		return null;
	}

	public boolean updateFare() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return true;
	}

	public boolean getEntruckingReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return true;
	}

	public boolean updateEntruckingReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return true;
	}

	public ArrayList<OrderPO> updateWaitingList(TransferingReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public EntruckingReceiptPO entruck(ArrayList<OrderPO> po) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<EntruckingReceiptPO> updateEntruckingReceiptList(
			EntruckingReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public FarePO computeFare(ArrayList<EntruckingReceiptPO> po) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateFare(FarePO po) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getEntruckingReceipt(ArrayList<EntruckingReceiptPO> po) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptPO> po) {
		// TODO Auto-generated method stub
		return false;
	}

	public TruckPO getTruck(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
