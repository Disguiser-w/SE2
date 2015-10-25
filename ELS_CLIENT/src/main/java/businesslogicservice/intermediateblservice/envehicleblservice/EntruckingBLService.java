package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

public interface EntruckingBLService {
    public ArrayList<TruckVO> showTruckList();
    
    public TruckVO showTrain(String ID);
    
    public ArrayList<OrderVO> updateWaitingList(TranferingReceiptVO);
    
    public EntruckingReceiptVO entruck(ArrayList<OrderVO>);
    
    public ArrayList<EntruckingReceiptVO> updateEntruckingReceiptList(EntruckingReceiptVO);
    
    public fareVO computeFare(ArrayList<EntruckingReceiptVO>);
    
    public boolean updateFare(fareVO);
    
    public boolean showEntruckingReceipt(ArrayList<EntruckingReceiptVO>);
    
    public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptVO>);
}
