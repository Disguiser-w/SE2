package businesslogicservice.repertoryblservice;

import vo.InventoryVO;
import vo.InventoryCheckVO;

import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RepertoryBLService {
	public int inventoryInitialization(int maxRow, int maxShelf, int maxDigit, int warningRatio) throws RemoteException;
	public int enterRepertory(String JJD_ID, int blockNum, int rowNum, int shelfNum, int digitNum) throws RemoteException;
	public int leaveRepertory(String JJD_ID) throws RemoteException;
	public String inventoryWarning() throws RemoteException;
	public InventoryCheckVO inventoryCheck(String beginDate, String endDate) throws RemoteException;
	public ArrayList<InventoryVO> inventoryStockTaking() throws RemoteException;
}
