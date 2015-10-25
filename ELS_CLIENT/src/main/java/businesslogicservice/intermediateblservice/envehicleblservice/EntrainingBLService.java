package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import vo.EntrainingReceiptVO;
import vo.FareVO;
import vo.OrderVO;
import vo.TrainVO;
import vo.TranferingReceiptVO;

public interface EntrainingBLService {
    public ArrayList<TrainVO> showTrainList();
    
    public TrainVO showTrain(String ID);
    
    public ArrayList<OrderVO> updateWaitingList(TranferingReceiptVO vo);
    
    public EntrainingReceiptVO entrain(ArrayList<OrderVO> al);
    
    public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(EntrainingReceiptVO vo);
    
    public FareVO computeFare(ArrayList<EntrainingReceiptVO> vo);
    
    public boolean updateFare(FareVO fareVO);
    
    public boolean showEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo);
    
    public boolean updateEntrainingReceipt(ArrayList<EntrainingReceiptVO> vo);
}
