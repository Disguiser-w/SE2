package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.PlaneVO;
import businesslogicservice.intermediateblservice.envehicleblservice.EnplaningBLService;

public class EnplaningBL implements EnplaningBLService{

	public ArrayList<PlaneVO> showPlaneList() {
		// TODO 自动生成的方法存根
		return null;
	}

	public PlaneVO showPlane(String planeID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane) {
		// TODO 自动生成的方法存根
		return null;
	}

	public EnplaningReceiptVO enplane(ArrayList<OrderVO> al) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(
			EnplaningReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean showEnplaningReceiptList(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		return false;
	}

	public FareVO computeFare(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		return null;
	}

	public boolean updateFare(FareVO fareVO) {
		// TODO 自动生成的方法存根
		return false;
	}

	public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO> al) {
		// TODO 自动生成的方法存根
		return false;
	}

}
