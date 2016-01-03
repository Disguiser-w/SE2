package testConnection;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestConnection extends Remote {
	public boolean getConnectionInfo()throws RemoteException	;
}
