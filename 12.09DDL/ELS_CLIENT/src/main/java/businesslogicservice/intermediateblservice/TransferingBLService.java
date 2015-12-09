package businesslogicservice.intermediateblservice;

import java.rmi.RemoteException;

import type.OperationState;
import vo.OrderVO;
import vo.TransferingReceiptVO;

public interface TransferingBLService {
    public TransferingReceiptVO showTransferingReceipt();
    
    public OperationState addOrder(String ID) throws RemoteException;
    
    public OperationState deleteOrder(String ID) throws Exception;
    
    public OperationState modifyOrder(OrderVO order) throws Exception;
    
    public OperationState saveTransferingReceipt() throws RemoteException;
}
