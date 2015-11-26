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

	public EntruckingReceiptVO showEntruckingReceiptVO(TruckVO truck) throws Exception;

	public void entruck(ArrayList<OrderVO> al) throws Exception;

	public ArrayList<EntruckingReceiptVO> updateEntruckingReceiptList(
			EntruckingReceiptVO vo);

	public ArrayList<EntruckingReceiptVO> showEntruckingReceiptList() throws Exception;

//	public FareVO computeFare(ArrayList<EntruckingReceiptVO> vo,OrganizationVO intermediate);
//
//	public boolean updateFare(FareVO fareVO);

	public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo);
}
