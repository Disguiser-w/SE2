package businesslogicservice.repertoryblservice;

import vo.InventoryVO;
import vo.InventoryCheckVO;

import java.rmi.RemoteException;
import java.util.ArrayList;


public interface RepertoryBLService {
	public int inventoryInitialization(String repertoryID, int maxRow, int maxShelf, int maxDigit, int warningRatio) throws RemoteException;
//  										   仓库编号                    最多多少排     最多多少架          最多多少位         警戒比例
	public int enterRepertory(String repertoryID, String JJD_ID, int blockNum, String date) throws RemoteException;
//								 	 仓库编号                          寄件单号          区号                      排号               架号                    位号           入库日期
//                                   0 飞机区
//                                   1 火车区
//                                   2 汽车区
//                                   3 机动区;

	public int leaveRepertory(String repertoryID, String JJD_ID, String date) throws RemoteException;
//                               仓库编号                          寄件单号       出库日期

	public String inventoryWarning(String repertoryID) throws RemoteException;
	public InventoryCheckVO inventoryCheck(String repertoryID, String beginDate, String endDate) throws RemoteException;
	public ArrayList<InventoryVO> inventoryStockTaking(String repertoryID) throws RemoteException;
}
