package businesslogicservice.intermediateblservice;

import type.OperationState;
import vo.FareVO;

public interface FareBLService {
    public FareVO computeFare();
    
    public OperationState update(FareVO fare);
}
