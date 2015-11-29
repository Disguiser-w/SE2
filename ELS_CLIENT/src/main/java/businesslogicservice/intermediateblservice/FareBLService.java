package businesslogicservice.intermediateblservice;

import type.OperationState;
import vo.FareVO;

public interface FareBLService {
	public OperationState computeFare();

	public OperationState saveFare();
}
