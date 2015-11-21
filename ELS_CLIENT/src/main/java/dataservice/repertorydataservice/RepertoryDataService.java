package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;

public interface RepertoryDataService extends Remote{
	
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException;  //对仓库信息做修改，比如库存信息初始化工作
	public RepertoryPO findRepertory(String repertoryID) throws RemoteException;
	public int addInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException;
	public int deleteInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException;
	public int modifyInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException;
	//public GoodsPO findGoodsbyID(String repertoryID, String JJD_ID);
	public GoodsPO findGoodsbyID(String JJD_ID) throws RemoteException;
	public InventoryPO findInventorybyID(String repertoryID, String JJD_ID) throws RemoteException;
	public ArrayList<InventoryPO> findInventorybyDate(String repertoryID, String beginDate, String endDate) throws RemoteException;
	public ArrayList<InventoryPO> findInventorybyTime(String repertoryID, String time) throws RemoteException;
	
}
