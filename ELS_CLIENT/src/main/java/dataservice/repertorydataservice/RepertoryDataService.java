package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;

public interface RepertoryDataService extends Remote{
	
	public int modifyRepertory(RepertoryPO repertorypo);  //对仓库信息做修改，比如库存信息初始化工作
	public RepertoryPO findRepertory(String repertoryID);
	public int addInventory(String repertoryID, InventoryPO inventorypo);
	public int deleteInventory(String repertoryID, InventoryPO inventorypo);
	public int modifyInventory(String repertoryID, InventoryPO inventorypo);
	//public GoodsPO findGoodsbyID(String repertoryID, String JJD_ID);
	public GoodsPO findGoodsbyID(String JJD_ID);
	public InventoryPO findInventorybyID(String repertoryID, String JJD_ID);
	public ArrayList<InventoryPO> findInventorybyDate(String repertoryID, String beginDate, String endDate);
	public ArrayList<InventoryPO> findInventorybyTime(String repertoryID, String time);
	
}
