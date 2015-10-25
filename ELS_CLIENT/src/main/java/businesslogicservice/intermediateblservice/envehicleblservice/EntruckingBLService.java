package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TranferingReceiptVO;
import vo.TruckVO;

public interface EntruckingBLService {
    public ArrayList<TruckVO> showTruckList();
    
    public TruckVO showTruck(String ID);
    
    public ArrayList<OrderVO> updateWaitingList(TranferingReceiptVO vo);
    
    public EntruckingReceiptVO entruck(ArrayList<OrderVO> al);
    
    public ArrayList<EntruckingReceiptVO> updateEntruckingReceiptList(EntruckingReceiptVO vo);
    
    public FareVO computeFare(ArrayList<EntruckingReceiptVO> vo);
    
    public boolean updateFare(FareVO fareVO);
    
    public boolean showEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo);
    
    public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO> vo);
}
