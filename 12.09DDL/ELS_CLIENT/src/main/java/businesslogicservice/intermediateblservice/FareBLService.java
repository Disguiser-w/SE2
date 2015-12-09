package businesslogicservice.intermediateblservice;

import java.rmi.RemoteException;

import type.OperationState;

public interface FareBLService {
	public OperationState computeFare();

	public OperationState saveFare() throws RemoteException;
}
