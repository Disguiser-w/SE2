package data.repertorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dataservice.repertorydataservice.RepertoryDataService;
import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;
import po.UserPO;
import po.InventoryCheckPO;

public class RepertoryData implements RepertoryDataService{

	private static final long serialVersionUID = 141250148L;
	
	JXCFile repertoryFile;
	JXCFile goodsFile;
	
	public RepertoryData(){
		repertoryFile = new JXCFile("src/main/java/repertory.ser");
		goodsFile = new JXCFile("src/main/java/goods.ser");
	}
	
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException{
		ArrayList<Object> objectList = repertoryFile.read();

		for(int i=0;i<objectList.size();i++){
			RepertoryPO repertory = (RepertoryPO)objectList.get(i);
			if(repertory.getRepertoryID().equals(repertorypo.getRepertoryID())){
				repertory.setOwnerID(repertorypo.getOwnerID());
				repertory.setMaxRow(repertorypo.getMaxRow());
				repertory.setMaxShelf(repertorypo.getMaxShelf());
				repertory.setMaxDigit(repertorypo.getMaxDigit());
				repertory.setWarningRatio(repertorypo.getWarningRatio());
				return 0;
			}
		}
		return 1;
	}
	
	public RepertoryPO findRepertory(String repertoryID) throws RemoteException{
		ArrayList<Object> objectList = repertoryFile.read();

		for(int i=0;i<objectList.size();i++){
			RepertoryPO repertory = (RepertoryPO)objectList.get(i);
			if(repertory.getRepertoryID().equals(repertoryID)){
				return repertory;
			}
		}
		return null;
	}
	
	public int addInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		inventoryList.add(inventorypo);
	}
	
	public int deleteInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		inventoryList.remove(inventorypo);
	}
	
	public int modifyInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		for(int i=0;i<inventoryList.size();i++){
			InventoryPO tempInventory = (InventoryPO)inventoryList.get(i);
			if(tempInventory.getGood().getOrder_ID().equals(inventorypo.getGood().getOrder_ID())){
				tempInventory.setBlockNum(inventorypo.getBlcokNum());
				tempInventory.setRowNum(inventorypo.getRowNum());
				tempInventory.setShelfNum(inventorypo.getShelfNum());
				tempInventory.setDigitNum(inventorypo.getDigitNum());
				tempInventory.getGood().setEnterTime(inventorypo.getGood().getLatestEnterTime());
				tempInventory.getGood().setLeaveTime(inventorypo.getGood().getLatestLeaveTime());
				tempInventory.getGood().setEnterRepertoryID(inventorypo.getGood().getLatestEnterRepertoryID());
				tempInventory.getGood().setLeaveRepertoryID(inventorypo.getGood().getLatestLeaveRepertoryID());
				return 0;
			}
		}
		return 1;
	}
	
	public GoodsPO findGoodsbyID(String JJD_ID) throws RemoteException{
		ArrayList<Object> objectList = goodsFile.read();
		for(int i=0;i<objectList.size();i++){
			GoodsPO tempGoods = (GoodsPO)objectList.get(i);
			if(tempGoods.getOrder_ID().equals(JJD_ID))
				return tempGoods;
		}
		return null;
	}
	
	public InventoryPO findInventorybyID(String repertoryID, String JJD_ID) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		for(int i=0;i<inventoryList.size();i++){
			InventoryPO tempInventory = (InventoryPO)inventoryList.get(i);
			if(tempInventory.getGood().getOrder_ID().equals(JJD_ID)){
				return tempInventory;
			}
		}
		return null;
	} 
	
	
	public InventoryCheckPO findInventorybyDate(String repertoryID, String beginDate, String endDate) throws RemoteException{
		//beginDate和endDate参数的标准形式为yyyy-mm-dd，goodsPO里面enterTime和leaveTime的标准形式为yyyy-mm-dd hh:mm:ss;
		InventoryCheckPO inventoryCheckPO = new InventoryCheckPO();
		
		ArrayList<Object> objectList = goodsFile.read();
		for(int i=0;i<objectList.size();i++){
			GoodsPO tempGoods = (GoodsPO)objectList.get(i);
			String[] enterRepertory = tempGoods.getEnterRepertoryID();
			String[] leaveRepertory = tempGoods.getLeaveRepertoryID();
			String[] enterDate = tempGoods.getEnterDate();
			String[] leaveDate = tempGoods.getLeaveDate();
			for(int j=0;j<4;i++){
				if((enterRepertory[j].equals(repertoryID)) && (enterDate[j].compareTo(beginDate)>=1) && (enterDate[j].compareTo(endDate)<=1)){
					inventoryCheckPO.enterTotalPlus();
					inventoryCheckPO.enterFeeTotalPlus(tempGoods.getFee());
				}
				if((leaveRepertory[j].equals(repertoryID)) && (leaveDate[j].compareTo(beginDate)>=1) && (leaveDate[j].compareTo(endDate)<=1)){
					inventoryCheckPO.leaveTotalPlus();
					inventoryCheckPO.leaveFeeTotalPlus(tempGoods.getFee());
				}
			}
		}
		
		RepertoryPO repertoryPO = findRepertory(repertoryID);
		inventoryCheckPO.setStockNumArray(repertoryPO.getStockNumArray());
		
		return inventoryCheckPO;
		
	} 
	
	/*我觉得这里有问题，我这样四轮排序下来相当于只是按位数去排序……怎么样按照区数、排数、架数、位数去排序*/
	public ArrayList<InventoryPO> findInventorybyTime(String repertoryID, String time) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		sortInventoryByBlock(inventoryList);
		sortInventoryByRow(inventoryList);
		sortInventoryByShelf(inventoryList);
		sortInventoryByDigit(inventoryList);
		return inventoryList;
	}
	
	
	public static void sortInventoryByBlock (ArrayList<InventoryPO> inventoryList){
		Collections.sort(inventoryList, new Comparator<InventoryPO>(){
			public int compare(InventoryPO first, InventoryPO second){
				if(first.getBlcokNum()>second.getBlcokNum())
					return 1;
				else 
					return -1;
			}
		});
	}
	
	public static void sortInventoryByRow (ArrayList<InventoryPO> inventoryList){
		Collections.sort(inventoryList, new Comparator<InventoryPO>(){
			public int compare(InventoryPO first, InventoryPO second){
				if(first.getRowNum()>second.getRowNum())
					return 1;
				else 
					return -1;
			}
		});
	}
	
	public static void sortInventoryByShelf (ArrayList<InventoryPO> inventoryList){
		Collections.sort(inventoryList, new Comparator<InventoryPO>(){
			public int compare(InventoryPO first, InventoryPO second){
				if(first.getShelfNum()>second.getShelfNum())
					return 1;
				else 
					return -1;
			}
		});
	}
	
	public static void sortInventoryByDigit (ArrayList<InventoryPO> inventoryList){
		Collections.sort(inventoryList, new Comparator<InventoryPO>(){
			public int compare(InventoryPO first, InventoryPO second){
				if(first.getDigitNum()>second.getDigitNum())
					return 1;
				else 
					return -1;
			}
		});
	}
	
}
