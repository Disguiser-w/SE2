package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TransferingReceiptVO;

public interface EnplaningBLService {
	public ArrayList<PlaneVO> showPlaneList();

	public PlaneVO showPlane(String planeID);

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane);

	public EnplaningReceiptVO enplane(ArrayList<OrderVO> al);

	public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(
			EnplaningReceiptVO vo);

	public boolean showEnplaningReceiptList(ArrayList<EnplaningReceiptVO> al);

	public FareVO computeFare(ArrayList<EnplaningReceiptVO> al);

	public boolean updateFare(FareVO fareVO);

	public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO> al);
}
