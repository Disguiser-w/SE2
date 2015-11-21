package dataservice.repertorydataservice;

import java.util.ArrayList;

import dataservice.repertorydataservice.RepertoryDataService;
import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;

public class RepertoryDataService_stub implements RepertoryDataService{
	
	public int modifyRepertory(RepertoryPO repertorypo){
        // TODO 自动生成的方法存根
        System.out.println("Modify repertory in file succeed!");
        return 0;
    }
 
    public RepertoryPO findRepertory(String repertoryID){
        // TODO 自动生成的方法存根
        System.out.println("Find repertory in file succeed!");
        return null;
    }
    
    public int addInventory(String repertoryID, InventoryPO inventorypo){
        // TODO 自动生成的方法存根
        System.out.println("Add inventory to repertory in file succeed!");
        return 0;
    }
    
    public int deleteInventory(String repertoryID, InventoryPO inventorypo) {
        // TODO 自动生成的方法存根
        System.out.println("Delete inventory from repertory in file succeed!");
        return 0;
    }
    
    public int modifyInventory(String repertoryID, InventoryPO inventorypo){
        // TODO 自动生成的方法存根
        System.out.println("Modify inventory in repertory in file succeed!");
        return 0;
    }
    
    public GoodsPO findGoodsbyID(String JJD_ID){
        // TODO 自动生成的方法存根
        System.out.println("Find goods by ID in repertory in file succeed!");
        return null;
    }
    
    public InventoryPO findInventorybyID(String repertoryID, String JJD_ID){
    	// TODO 自动生成的方法存根
        System.out.println("Find inventory by ID in repertory in file succeed!");
        return null;
    }
    
   public ArrayList<InventoryPO> findInventorybyDate(String repertoryID, String beginDate, String endDate){
        // TODO 自动生成的方法存根
        System.out.println("Find goods by date in repertory in file succeed!");
        return null;
    }
    
    public ArrayList<InventoryPO> findInventorybyTime(String repertoryID, String time){
        // TODO 自动生成的方法存根
        System.out.println("Find goods by time in repertory in file succeed!");
        return null;
    }
    
}
