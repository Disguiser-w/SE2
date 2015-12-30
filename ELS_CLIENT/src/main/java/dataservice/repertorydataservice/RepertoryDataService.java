package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;
import po.InventoryCheckPO;
import po.InventoryPO;
import po.RepertoryPO;

public interface RepertoryDataService extends Remote {
	
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException;  //对仓库信息做修改，比如库存信息初始化工作
	public RepertoryPO findRepertory(String repertoryID) throws RemoteException;
	public RepertoryPO findRepertoryByOwnerID(String ownerID) throws RemoteException;
	public ArrayList<RepertoryPO> showAllRepertorys() throws RemoteException;
	public int addInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException;//增加库存（某商品入库）
	public int deleteInventory(String repertoryID, String orderID) throws RemoteException;//删除库存（某商品出库）
	public int modifyInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException;//修改库存信息（暂时还没用到这个方法，不过还是先留着他吧）
	public InventoryPO findInventorybyID(String repertoryID, String orderID) throws RemoteException;  //（通过寄件单号和仓库编号（因为有多个仓库）寻找库存信息）
	public InventoryCheckPO findInventorybyDate(String repertoryID, String beginDate, String endDate) throws RemoteException;  //（通过起始日期和仓库编号寻找库存信息）
	public ArrayList<InventoryPO> findInventorybyTime(String repertoryID, String time) throws RemoteException;  //（通过截止时间和仓库编号寻找库存信息）
	public String getLastCreateEnterReceiptTime(String repertoryID) throws RemoteException;
	public String getLastCreateLeaveReceiptTime(String repertoryID) throws RemoteException;
	public int setLastCreateEnterReceiptTime(String repertoryID, String newEnterTime) throws RemoteException;
	public int setLastCreateLeaveReceiptTime(String repertoryID, String newLeaveTime) throws RemoteException;
	public ArrayList<GoodsPO> getEnterRepertoryGoods(String repertoryID) throws RemoteException;
	public ArrayList<GoodsPO> getLeaveRepertoryGoods(String repertoryID) throws RemoteException;
	
}
