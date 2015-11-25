package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.TransferingReceiptVO;
import vo.TruckVO;

public interface EntruckingBLService {
	public ArrayList<TruckVO> showTruckList();

	public TruckVO showTruck(String truckID) throws Exception;

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck);

	public void entruck(ArrayList<OrderVO> al);

	public ArrayList<EntruckingReceiptVO> updateEntruckingReceiptList(
			EntruckingReceiptVO vo);

	public ArrayList<EntruckingReceiptVO> showEntruckingReceiptList();

	public FareVO computeFare(ArrayList<EntruckingReceiptVO> vo,OrganizationVO intermediate);

	public boolean updateFare(FareVO fareVO);

	public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo);
}
