package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EnplaningReceiptPO;
import po.OrderPO;
import po.PlanePO;
import po.TranferingReceiptPO;
import po.farePO;

public class EnplaningDataService_stub implements EnplaningDataService{

	public ArrayList<PlanePO> getPlaneList() {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return null;
	}

	public PlanePO getPlane(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return null;
	}

	public ArrayList<OrderPO> updateWaitingList() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}

	public EnplaningReceiptPO enplane() {
		// TODO 自动生成的方法存根
		System.out.println("enplane successfully!");
		return null;
	}

	public ArrayList<EnplaningReceiptPO> updateEnplaningReceiptList() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}

	public farePO computeFare() {
		// TODO 自动生成的方法存根
		System.out.println("compute successfully!");
		return null;
	}

	public boolean updateFare() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return true;
	}

	public boolean getEnplaningReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return true;
	}

	public boolean updateEnplaningReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return true;
	}

	public ArrayList<OrderPO> updateWaitingList(TranferingReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public EnplaningReceiptPO enplane(ArrayList<OrderPO> po) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<EnplaningReceiptPO> updateEnplaningReceiptList(
			EnplaningReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public farePO computeFare(ArrayList<EnplaningReceiptPO> po) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateFare(farePO po) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getEnplaningReceipt(ArrayList<EnplaningReceiptPO> po) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptPO> po) {
		// TODO Auto-generated method stub
		return false;
	}

}
