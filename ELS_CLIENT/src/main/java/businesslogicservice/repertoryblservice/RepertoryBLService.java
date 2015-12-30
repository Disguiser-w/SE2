package businesslogicservice.repertoryblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.InventoryCheckVO;
import vo.InventoryVO;

public interface RepertoryBLService {
	
	public int inventoryInitialization(int maxRow, int maxShelf, int maxDigit, int warningRatio) throws RemoteException;
	public int enterRepertory(String orderID, int blockNum, int rowNum, int shelfNum, int digitNum) throws RemoteException;
	public int leaveRepertory(String orderID) throws RemoteException;
	public InventoryCheckVO inventoryCheck(String beginDate, String endDate) throws RemoteException;
	public ArrayList<InventoryVO> inventoryStockTaking() throws RemoteException;
	public InventoryVO findInventory(String goodsID) throws RemoteException;
	public String repertoryName(String repertoryID) throws RemoteException;
	public String getLastEnterRepertoryTime() throws RemoteException;
	public String getLastLeaveRepertoryTime() throws RemoteException;
	
}
