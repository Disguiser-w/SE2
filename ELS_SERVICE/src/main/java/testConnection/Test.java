package testConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Test extends UnicastRemoteObject implements TestConnection {
	public static boolean isConnection;

	public Test() throws RemoteException {
		super();
		isConnection = false;
	}

	public boolean getConnectionInfo() throws RemoteException {
		return isConnection;
	}

}
