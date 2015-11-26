package businesslogicservice.intermediateblservice;

import java.rmi.RemoteException;

import type.OperationState;
import vo.TransferingReceiptVO;

public interface TransferingBLService {
    public TransferingReceiptVO showTransferingReceipt();
    
    public OperationState addOrder(String ID) throws RemoteException;
    
    public OperationState deleteOrder(String ID) throws Exception;
    
    public OperationState modifyOrder(String ID);
    
    public TransferingReceiptVO updateTransferingReceipt(TransferingReceiptVO vo);
}
