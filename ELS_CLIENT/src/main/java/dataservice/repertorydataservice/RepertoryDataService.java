package dataservice.repertorydataservice;

import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;

import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;
import po.InventoryCheckPO;

public interface RepertoryDataService extends Remote{
	
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException;  //对仓库信息做修改，比如库存信息初始化工作
	public RepertoryPO findRepertory(String repertoryID) throws RemoteException;
	public ArrayList<RepertoryPO> showAllRepertorys() throws RemoteException;
	public int addInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException;//增加库存（某商品入库）
	public int deleteInventory(String repertoryID, String JJD_ID) throws RemoteException;//删除库存（某商品出库）
	public int modifyInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException;//修改库存信息（暂时还没用到这个方法，不过还是先留着他吧）
	public GoodsPO findGoodsbyID(String JJD_ID) throws RemoteException;//（通过寄件单号寻找商品）
	public InventoryPO findInventorybyID(String repertoryID, String JJD_ID) throws RemoteException;  //（通过寄件单号和仓库编号（因为有多个仓库）寻找库存信息）
	public InventoryCheckPO findInventorybyDate(String repertoryID, String beginDate, String endDate) throws RemoteException;  //（通过起始日期和仓库编号寻找库存信息）
	public ArrayList<InventoryPO> findInventorybyTime(String repertoryID, String time) throws RemoteException;  //（通过截止时间和仓库编号寻找库存信息）
		
}
