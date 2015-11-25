package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.PlaneVO;
import vo.TransferingReceiptVO;

public interface EnplaningBLService {
	public ArrayList<PlaneVO> showPlaneList();

	public PlaneVO showPlane(String planeID) throws Exception;

	public EnplaningReceiptVO showEnplaningReceipt(PlaneVO plane);

	public void enplane(ArrayList<OrderVO> al);

	public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(
			EnplaningReceiptVO vo);

	public ArrayList<EnplaningReceiptVO> showEnplaningReceiptList();

	public FareVO computeFare(ArrayList<EnplaningReceiptVO> al,OrganizationVO intemediateCenter);

	public boolean updateFare(FareVO fareVO);

	public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO> al);
}
