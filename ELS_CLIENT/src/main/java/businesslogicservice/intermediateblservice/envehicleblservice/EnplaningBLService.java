package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.OrderVO;
import vo.PlaneVO;
import vo.TranferingReceiptVO;

public interface EnplaningBLService {
    public ArrayList<PlaneVO> showPlaneList();
    
    public PlaneVO showPlane(String ID);
    
    public ArrayList<OrderVO> updateWaitingList(TranferingReceiptVO vo);
    
    public EnplaningReceiptVO enplane(ArrayList<OrderVO>);
    
    public ArrayList<EnplaningReceiptVO> updateEnplaningReceiptList(EnplaningReceiptVO);
    
    public fareVO computeFare(ArrayList<EnplaningReceiptVO>);
    
    public boolean updateFare(fareVO);
    
    public boolean showEnplaningReceipt(ArrayList<EnplaningReceiptVO>);
    
    public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptVO>);
}
