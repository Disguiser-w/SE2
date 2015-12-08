package businesslogicservice.intermediateblservice;

import java.rmi.RemoteException;

import type.OperationState;
import vo.FareVO;

public interface FareBLService {
	public OperationState computeFare();

	public OperationState saveFare() throws RemoteException;
}
