package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;

public class EntruckingBLService_stub implements TruckManageBLService {

	public ArrayList<TruckVO> showTruckList() {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return null;
	}

	public TruckVO showTruck(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return null;
	}

	public ArrayList<OrderVO> updateWaitingList(TransferingReceiptVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}

	public EntruckingReceiptVO entruck(ArrayList<OrderVO> al) {
		// TODO 自动生成的方法存根
		System.out.println("entruck successfully!");
		return null;
	}
	public ArrayList<EntruckingReceiptVO> updateEntruckingReceiptList(
			EntruckingReceiptVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}
	public FareVO computeFare(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		System.out.println("compute successfully!");
		return null;
	}
	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return false;
	}
	public boolean showEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return false;
	}
	public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return false;
	}

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck) {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean showEntruckingReceiptList(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return false;
	}
}
