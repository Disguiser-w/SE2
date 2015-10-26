package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TransferingReceiptVO;

public interface EnplaningBLService {
    public ArrayList<PlaneVO> showPlaneList();
    
    public PlaneVO showPlane(String ID);
    
    public ArrayList<OrderVO> updateWaitingList(TransferingReceiptVO vo);
    
    public EnplaningReceiptVO enplane(ArrayList<OrderVO> al);
    
    public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(EnplaningReceiptVO vo);
    
    public FareVO computeFare(ArrayList<EnplaningReceiptVO> al);
    
    public boolean updateFare(FareVO fareVO);
    
    public boolean showEnplaningReceipt(ArrayList<EnplaningReceiptVO> al);
    
    public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO> al);
}
