package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntrainingReceiptPO;
import po.OrderPO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.farePO;

public class EntrainingDataService_stub implements EntrainingDataService {

	public ArrayList<TrainPO> getTrainList() {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return null;
	}

	public TrainPO getTrain(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return null;
	}

	public ArrayList<OrderPO> updateWaitingList() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}

	public EntrainingReceiptPO entrain() {
		// TODO 自动生成的方法存根
		System.out.println("entrain successfully!");
		return null;
	}

	public ArrayList<EntrainingReceiptPO> updateEntrainingReceiptList() {
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

	public boolean getEntraningReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("get successfully!");
		return true;
	}

	public boolean updateEntraningReceipt() {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return true;
	}

	public ArrayList<OrderPO> updateWaitingList(TransferingReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public EntrainingReceiptPO entrain(ArrayList<OrderPO> po) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<EntrainingReceiptPO> updateEntrainingReceiptList(
			EntrainingReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public farePO computeFare(ArrayList<EntrainingReceiptPO> po) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateFare(farePO po) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getEntraningReceipt(ArrayList<EntrainingReceiptPO> po) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateEntraningReceipt(ArrayList<EntrainingReceiptPO> po) {
		// TODO Auto-generated method stub
		return false;
	}

}
