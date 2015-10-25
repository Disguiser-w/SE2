package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TranferingReceiptVO;

public class EnplaningBLService_stub implements EnplaningBLService {
	public ArrayList<PlaneVO> showPlaneList() {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return null;
	}
	public PlaneVO showPlane(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return null;
	}
	public ArrayList<OrderVO> updateWaitingList(TranferingReceiptVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}
	public EnplaningReceiptVO enplane(ArrayList<OrderVO> al) {
		// TODO 自动生成的方法存根
		System.out.println("enplane successfully!");
		return null;
	}
	public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(
			EnplaningReceiptVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return null;
	}
	public FareVO computeFare(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		System.out.println("compute successfully!");
		return null;
	}
	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return true;
	}
	public boolean showEnplaningReceipt(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		System.out.println("show successfully!");
		return true;
	}
	public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		System.out.println("update successfully!");
		return true;
	}
}
