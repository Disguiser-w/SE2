package dataservice.intermediatedataservice;

import java.rmi.Remote;

import po.IntermediatePO;

public interface IntermediateDataService extends Remote {
    public IntermediatePO getIntermediateInfo(String organization_ID,String intermediate_ID);    
}
