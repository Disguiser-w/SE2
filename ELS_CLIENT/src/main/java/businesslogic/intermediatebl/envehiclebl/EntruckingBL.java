package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TruckVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EntruckingBLService;

public class EntruckingBL implements EntruckingBLService {

	public ArrayList<TruckVO> showTruckList() {
		// TODO 自动生成的方法存根
		return null;
	}

	public TruckVO showTruck(String truckID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck) {
		// TODO 自动生成的方法存根
		return null;
	}

	public EntruckingReceiptVO entruck(ArrayList<OrderVO> al) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<EntruckingReceiptVO> updateEntruckingReceiptList(
			EntruckingReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean showEntruckingReceiptList(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return false;
	}

	public FareVO computeFare(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		return false;
	}

	public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo) {
		// TODO 自动生成的方法存根
		return false;
	}

}
