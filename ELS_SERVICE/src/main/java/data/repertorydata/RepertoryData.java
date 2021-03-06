package data.repertorydata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import common.FileGetter;

import dataservice.repertorydataservice.RepertoryDataService;
import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;
import po.InventoryCheckPO;
import po.OrganizationPO;

public class RepertoryData extends UnicastRemoteObject implements RepertoryDataService{

	private static final long serialVersionUID = 131250148L;
	
	private GoodsData goodsData;
	
	public RepertoryData() throws RemoteException{
		super();
		goodsData = new GoodsData();
	}
	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<OrganizationPO> getOrganizationList() throws RemoteException{
		String path = "organizationInfo/organization.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<OrganizationPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<OrganizationPO> repertoryList = (ArrayList<OrganizationPO>) in.readObject();
			in.close();
			return repertoryList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 写文件（增删改查统一调用它）
	 * 
	 * */
	public int saveOrganizationList(ArrayList<OrganizationPO> repertoryList) throws RemoteException {
		String path = "organizationInfo/organization.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(repertoryList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * 修改仓库对应的仓库管理员信息（为仓库分配仓库管理员）
	 * 
	 * @param String repertoryID, String ownerID
	 * @return 0(modify succeed), 1(modify failed)
	 * @throws RemoteException 
	 * 
	 * */
	public int modifyRepertoryOwner(String repertoryID, String ownerID) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		int returnNum = 1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				repertory.setOwnerID(ownerID);
				returnNum = 0;
				break;
			}
		}
		
		saveOrganizationList(organizationList);
		return returnNum;
	}
	
	
	/**
	 * 修改仓库信息
	 * 
	 * @param RepertoryPO repertorypo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		int returnNum = 1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertorypo.getRepertoryID())){
				repertory.setOwnerID(repertorypo.getOwnerID());
				repertory.setMaxRow(repertorypo.getMaxRow());
				repertory.setMaxShelf(repertorypo.getMaxShelf());
				repertory.setMaxDigit(repertorypo.getMaxDigit());
				repertory.setWarningRatio(repertorypo.getWarningRatio());
				repertory.setStockNumArray(repertorypo.getStockNumArray());
				returnNum = 0;
				break;
			}
		}
		
		saveOrganizationList(organizationList);
		return returnNum;
	}
	
	
	/**
	 * 根据仓库查找仓库（精确搜索）
	 * 
	 * @param String repertoryID
	 * @return RepertoryPO
	 * 
	 * */
	public RepertoryPO findRepertory(String repertoryID) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				return repertory;
			}
		}
		return null;
	}
	
	
	/**
	 * 根据仓库管理员编号查找仓库（精确搜索）
	 * 
	 * @param String ownerID
	 * @return RepertoryPO
	 * 
	 * */
	public RepertoryPO findRepertoryByOwnerID(String ownerID) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getOwnerID().equals(ownerID)){
				return repertory;
			}
		}
		return null;
	}
	
	
	/**
	 * 显示所有库存信息
	 * 
	 * @return ArrayList<RepertoryPO>
	 * 
	 * */
	public ArrayList<RepertoryPO> showAllRepertorys() throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		ArrayList<RepertoryPO> repertoryList = new ArrayList<RepertoryPO>();
		
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null)
				repertoryList.add(repertory);
		}
		return repertoryList;
	}
	
	
	/**
	 * 增加库存
	 * 
	 * @param String repertoryID, InventoryPO inventorypo
	 * @return 0(add succeed), 1(add failed)
	 * 
	 * */
	public int addInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		int returnNum =1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				
				ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
				inventoryList.add(inventorypo);
				repertory.stockNumPlus(inventorypo.getBlockNum());
				
				ArrayList<String> inventoryHistoryList = repertory.getInventoryHistoryList();
				inventoryHistoryList.add(inventorypo.getGood().getOrder_ID());
				
				returnNum = 0;
				break;
			}
		}
		
		saveOrganizationList(organizationList);
		return returnNum;
	}
	
	
	/**
	 * 删除库存
	 * 
	 * @param String repertoryID, InventoryPO inventorypo
	 * @return 0(add succeed), 1(add failed)
	 * 
	 * */
	public int deleteInventory(String repertoryID, String orderID) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		int returnNum =1;
loop1:		for(int i=0;i<organizationList.size();i++){
				OrganizationPO organization = organizationList.get(i);
				RepertoryPO repertory = organization.getRepertory();
				
				if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
					ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
					
	loop2:				for(int j=0;j<inventoryList.size();j++){
							InventoryPO tmpInventory = (InventoryPO)inventoryList.get(j);
							if(tmpInventory.getGood().getOrder_ID().equals(orderID)){
								inventoryList.remove(tmpInventory);
								repertory.stockNumSub(tmpInventory.getBlockNum());

								returnNum = 0;
								break loop2;
							}
						}
					break loop1;
				}
		}
		
		saveOrganizationList(organizationList);
		return returnNum;
	}
	
	
	/**
	 * 修改库存信息
	 * 
	 * @param String repertoryID, InventoryPO inventorypo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		
		int returnNum =1;
			for(int i=0; i<organizationList.size(); i++){
				OrganizationPO organization = organizationList.get(i);
				RepertoryPO repertory = organization.getRepertory();
				ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
				for(int j=0;j<inventoryList.size();j++){
					InventoryPO tmpInventory = (InventoryPO)inventoryList.get(j);
					if(tmpInventory.getGood().getOrder_ID().equals(inventorypo.getGood().getOrder_ID())){
						tmpInventory.setBlockNum(inventorypo.getBlockNum());
						tmpInventory.setRowNum(inventorypo.getRowNum());
						tmpInventory.setShelfNum(inventorypo.getShelfNum());
						tmpInventory.setDigitNum(inventorypo.getDigitNum());
						tmpInventory.getGood().setEnterTime(inventorypo.getGood().getLatestEnterTime());
						tmpInventory.getGood().setLeaveTime(inventorypo.getGood().getLatestLeaveTime());
						tmpInventory.getGood().setEnterRepertoryID(inventorypo.getGood().getLatestEnterRepertoryID());
						tmpInventory.getGood().setLeaveRepertoryID(inventorypo.getGood().getLatestLeaveRepertoryID());
						returnNum = 0;
						break;
					}
				}
		}
		saveOrganizationList(organizationList);
		return returnNum;
	}
	
	
	/**
	 * 根据仓库编号、订单号查找库存（精确搜索）
	 * 
	 * @param String repertoryID, String orderID
	 * @return InventoryPO
	 * 
	 * */
	public InventoryPO findInventorybyID(String repertoryID, String orderID) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		for(int i=0;i<inventoryList.size();i++){
			InventoryPO tmpInventory = (InventoryPO)inventoryList.get(i);
			if(tmpInventory.getGood().getOrder_ID().equals(orderID)){
				return tmpInventory;
			}
		}
		return null;
	} 
	
	
	/**
	 * 根据仓库编号、起始日期盘点库存信息
	 * 
	 * @param String repertoryID, String beginDate, String endDate
	 * @return InventoryCheckPO
	 * 
	 * */
	public InventoryCheckPO findInventorybyDate(String repertoryID, String beginDate, String endDate) throws RemoteException{
		//beginDate和endDate参数的标准形式为yyyy-mm-dd，goodsPO里面enterTime和leaveTime的标准形式为yyyy-mm-dd hh:mm:ss;
		InventoryCheckPO inventoryCheckPO = new InventoryCheckPO();
		
		ArrayList<GoodsPO> goodsList = goodsData.showAllGoods();
		for(int i=0;i<goodsList.size();i++){
			GoodsPO tmpGoods = goodsList.get(i);
			String[] enterRepertory = tmpGoods.getEnterRepertoryID();
			String[] leaveRepertory = tmpGoods.getLeaveRepertoryID();
			String[] enterDate = tmpGoods.getEnterDate();
			String[] leaveDate = tmpGoods.getLeaveDate();
			for(int j=0;j<4;j++){
				if((enterDate[j]!=null) && (leaveDate[j]!=null)){
					if((enterDate[j]!=null) && (enterRepertory[j].equals(repertoryID)) && (enterDate[j].compareTo(beginDate)>=0) && (enterDate[j].compareTo(endDate)<=0)){
						inventoryCheckPO.enterTotalPlus();
						inventoryCheckPO.enterFeeTotalPlus(tmpGoods.getFee());
					}
					if((leaveDate[j]!=null) && (leaveRepertory[j].equals(repertoryID)) && (leaveDate[j].compareTo(beginDate)>=0) && (leaveDate[j].compareTo(endDate)<=0)){
						inventoryCheckPO.leaveTotalPlus();
						inventoryCheckPO.leaveFeeTotalPlus(tmpGoods.getFee());
					}
				}
			}
		}
		
		RepertoryPO repertoryPO = findRepertory(repertoryID);
		inventoryCheckPO.setStockNumArray(repertoryPO.getStockNumArray());
		
		return inventoryCheckPO;
		
	} 
	
	
	/**
	 * 根据仓库编号、截止时间查看库存
	 * 
	 * @param String repertoryID, String time
	 * @return ArrayList<InventoryPO>
	 * 
	 * */
	public ArrayList<InventoryPO> findInventorybyTime(String repertoryID, String time) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		if(inventoryList != null){
			sortInventory(inventoryList);
			return inventoryList;
		}
		else
			return null;
	}
	
	
	/**
	 * 给当前的库存信息按照区号、排号、架号、位号的顺序去排序，便于查看(先排区号，如果区号一样排行号，如果行号一样排架号，如果架号一样排位号)
	 * 
	 * @param ArrayList<InventoryPO> inventoryList
	 * 
	 * */
	//用了Comparator方法，觉得本宝宝越来越聪明了呢
	public static void sortInventory(ArrayList<InventoryPO> inventoryList){
		Collections.sort(inventoryList, new Comparator<InventoryPO>(){
			public int compare(InventoryPO first, InventoryPO second){
				int cr= 0;
				int a = first.getBlockNum() - second.getBlockNum();
				if(a != 0)
					cr = (a>0)? 4:-1;
				else{
					a = first.getRowNum() - second.getRowNum(); 
					if(a != 0)
						cr = (a>0)? 3:-2;
					else{
						a = first.getShelfNum() - second.getShelfNum(); 
						if(a != 0)
							cr = (a>0)? 2:-3;
						else{
							a = first.getDigitNum() - second.getDigitNum();
							if(a != 0)
								cr = (a>0)? 4:-1;
						}
					}
				}
			return cr;
			}
		});
	}
	
	
	/**
	 * 获取从上次制定入库单之后进入过仓库的货物记录
	 * 
	 * @param String repertoryID
	 * @return ArrayList<GoodsPO>
	 * 
	 * */
	public ArrayList<GoodsPO> getEnterRepertoryGoods(String repertoryID) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<String> inventoryHistoryList = repertory.getInventoryHistoryList();
		ArrayList<GoodsPO> enterRepertoryGoodsList = new ArrayList<GoodsPO> ();
		
		if(inventoryHistoryList != null){
			for(String IDStr : inventoryHistoryList){
				GoodsPO goodspo = goodsData.findGoodsByID(IDStr);
				if(goodspo.getThisRepertoryEnterTime(repertoryID).compareTo(repertory.getLastCreateEnterReceiptTime()) > 0)
					enterRepertoryGoodsList.add(goodspo);
			}
		}
		return enterRepertoryGoodsList;
	}
	
	
	/**
	 * 获取从上次制定出库单之后离开过仓库的货物记录
	 * 
	 * @param String repertoryID
	 * @return ArrayList<GoodsPO>
	 * 
	 * */
	public ArrayList<GoodsPO> getLeaveRepertoryGoods(String repertoryID) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<String> inventoryHistoryList = repertory.getInventoryHistoryList();
		ArrayList<GoodsPO> leaveRepertoryGoodsList = new ArrayList<GoodsPO> ();
		
		if(inventoryHistoryList != null){
			for(String IDStr : inventoryHistoryList){
				GoodsPO goodspo = goodsData.findGoodsByID(IDStr);
				if(!goodspo.getThisRepertoryLeaveTime(repertoryID).equals("has not left this repertory")){
					if(goodspo.getThisRepertoryLeaveTime(repertoryID).compareTo(repertory.getLastCreateLeaveReceiptTime()) > 0)
						leaveRepertoryGoodsList.add(goodspo);
				}
			}
		}
		return leaveRepertoryGoodsList;
	}
	
	
	
	/**
	 * 获取编号为repertoryID的仓库上一次制定入库单的时间
	 * 
	 * @param String repertoryID
	 * @return String
	 * 
	 * */
	public String getLastCreateEnterReceiptTime(String repertoryID) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		return repertory.getLastCreateEnterReceiptTime();
	}
	
	
	/**
	 * 获取编号为repertoryID的仓库上一次制定出库单的时间
	 * 
	 * @param String repertoryID
	 * @return String
	 * 
	 * */
	public String getLastCreateLeaveReceiptTime(String repertoryID) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		return repertory.getLastCreateLeaveReceiptTime();
	}
	
	
	/**
	 * 更改编号为repertoryID的仓库上一次制定入库单的时间
	 * 
	 * @param String repertoryID, String newEnterTime
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int setLastCreateEnterReceiptTime(String repertoryID, String newEnterTime) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		
		int returnNum = 1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				repertory.setLastCreateEnterReceiptTime(newEnterTime);
				returnNum = 0;
				break;
			}
		}
		
		saveOrganizationList(organizationList);
		return returnNum;
	}
	
	
	/**
	 * 更改编号为repertoryID的仓库上一次制定出库单的时间
	 * 
	 * @param String repertoryID, String newLeaveTime
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int setLastCreateLeaveReceiptTime(String repertoryID, String newLeaveTime) throws RemoteException{
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		
		int returnNum = 1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				repertory.setLastCreateLeaveReceiptTime(newLeaveTime);
				returnNum = 0;
				break;
			}
		}
		
		saveOrganizationList(organizationList);
		return returnNum;
	}
	
	
	/**
	 * 获取现在的时间，便于出入库登记操作
	 * 
	 * */
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	

	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
	
	public static void main(String[] args){
		RepertoryData repertoryData;
		try{
			repertoryData = new RepertoryData();
			try{
				/*ArrayList<RepertoryPO> repertoryList0 = repertoryData.showAllRepertorys();
				if(repertoryList0 != null){
					for(int i=0;i<repertoryList0.size();i++){
						RepertoryPO repertorypo = repertoryList0.get(i);
						System.out.println(repertorypo.getRepertoryID()+" "+repertorypo.getOwnerID()+" "+repertorypo.getMaxRow()
								+" "+repertorypo.getMaxShelf()+" "+repertorypo.getMaxDigit()+" "
								+repertorypo.getWarningRatio()+" "+repertorypo.getStockNum(0)+" "+repertorypo.getStockNum(1)
								+" "+repertorypo.getStockNum(2)+" "+repertorypo.getStockNum(3));
					}
				}
				else
					System.out.println("Cannot find the repetory");
				
				RepertoryPO repertoryByOwner = repertoryData.findRepertoryByOwnerID("CK-00001");
				if(repertoryByOwner != null)
					System.out.println("Find the repertoryByOwner: "+repertoryByOwner.getRepertoryID()+" "+repertoryByOwner.getOwnerID()+" "+repertoryByOwner.getMaxRow()
							+" "+repertoryByOwner.getMaxShelf()+" "+repertoryByOwner.getMaxDigit()+" "
							+repertoryByOwner.getWarningRatio()+" "+repertoryByOwner.getStockNum(0)+" "+repertoryByOwner.getStockNum(1)
							+" "+repertoryByOwner.getStockNum(2)+" "+repertoryByOwner.getStockNum(3));
				else
					System.out.println("Cannot find the repertoryByOwner");*/
				
				RepertoryPO repertory = repertoryData.findRepertory("025-0-CK");
				if(repertory != null)
					System.out.println("Find the repertory: "+repertory.getRepertoryID()+" "+repertory.getOwnerID()+" "+repertory.getMaxRow()
							+" "+repertory.getMaxShelf()+" "+repertory.getMaxDigit()+" "
							+repertory.getWarningRatio()+" "+repertory.getStockNum(0)+" "+repertory.getStockNum(1)
							+" "+repertory.getStockNum(2)+" "+repertory.getStockNum(3)
							+"上次生成入库单时间："+repertory.getLastCreateEnterReceiptTime()+" 上次生成出库单时间："+repertory.getLastCreateLeaveReceiptTime());
				else
					System.out.println("Cannot find the repertory");
				
				/*repertoryData.modifyRepertory(new RepertoryPO("030-CK", "CK-01", 100,10,10,50,new int[4]));
				System.out.println("修改后:");
				ArrayList<RepertoryPO> repertoryList1 = repertoryData.showAllRepertorys();
				if(repertoryList1 != null){
					for(int i=0;i<repertoryList1.size();i++){
						RepertoryPO repertorypo = repertoryList1.get(i);
						System.out.println(repertorypo.getRepertoryID()+" "+repertorypo.getOwnerID()+" "+repertorypo.getMaxRow()
								+" "+repertorypo.getMaxShelf()+" "+repertorypo.getMaxDigit()+" "
								+repertorypo.getWarningRatio()+" "+repertorypo.getStockNum(0)+" "+repertorypo.getStockNum(1)
								+" "+repertorypo.getStockNum(2)+" "+repertorypo.getStockNum(3));
					}
				}
				else
					System.out.println("Cannot find the reperory");*/
				
				//System.out.println("入库：");
				//repertoryData.addInventory("025-0-CK", new InventoryPO(new GoodsPO("20151001-00001", 12, "南京", "上海"),3,0,0,0));
				//repertoryData.addInventory("025-0-CK", new InventoryPO(new GoodsPO("20151002-00003", 15, "北京", "上海"),2,0,1,0));
				//repertoryData.addInventory("040-0-CK", new InventoryPO(new GoodsPO("20151101-00012", 120, "洛杉矶", "北京"),1,0,6,0));
				//repertoryData.addInventory("030-0-CK", new InventoryPO(new GoodsPO("20151024-00007", 250, "南京", "北极"),0,5,0,0));
				//repertoryData.addInventory("030-0-CK", new InventoryPO(new GoodsPO("20151124-00004", 30, "铁岭", "海南"),2,5,0,0));
				
				/*System.out.println("查找库存");
				InventoryPO inventorypo1 = repertoryData.findInventorybyID("025-0-CK", "20151001-00001");
				if(inventorypo1 != null)
					System.out.println("Find the inventory: "+inventorypo1.getGood().getOrder_ID()+" "+inventorypo1.getGood().getFee()+" "+inventorypo1.getGood().getDeparturePlace()
							+" "+inventorypo1.getGood().getDestination()+" "+inventorypo1.getGood().getLatestEnterTime()+" "+inventorypo1.getGood().getLatestLeaveTime()+" "
							+inventorypo1.getBlockNum()+" "+inventorypo1.getRowNum()+" "+inventorypo1.getShelfNum()+" "+inventorypo1.getDigitNum());
				else
					System.out.println("Cannot find the inventory");
				
				System.out.println("查找库存");
				InventoryPO inventorypo2 = repertoryData.findInventorybyID("025-0-CK", "20151002-00003");
				if(inventorypo2 != null)
					System.out.println("Find the inventory: "+inventorypo2.getGood().getOrder_ID()+" "+inventorypo2.getGood().getFee()+" "+inventorypo2.getGood().getDeparturePlace()
							+" "+inventorypo2.getGood().getDestination()+" "+inventorypo2.getGood().getLatestEnterTime()+" "+inventorypo2.getGood().getLatestLeaveTime()+" "
							+inventorypo2.getBlockNum()+" "+inventorypo2.getRowNum()+" "+inventorypo2.getShelfNum()+" "+inventorypo2.getDigitNum());
				else
					System.out.println("Cannot find the inventory");*/
				System.out.println("查看从上次制定入库单到现在入库货物");
				ArrayList<GoodsPO> enterRepertoryGoodsList = repertoryData.getEnterRepertoryGoods("025-0-CK");
				if(enterRepertoryGoodsList != null){
					int size = enterRepertoryGoodsList.size();
					for(int i=0; i<size;i++){
						GoodsPO tempGoodspo = enterRepertoryGoodsList.get(i);
						System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
			    				+tempGoodspo.getEnterTime()[0]+" "+tempGoodspo.getEnterTime()[1]+" "+tempGoodspo.getEnterTime()[2]+" "+tempGoodspo.getEnterTime()[3]+" "
			    				+tempGoodspo.getEnterRepertoryID()[0]+" "+tempGoodspo.getEnterRepertoryID()[1]+" "+tempGoodspo.getEnterRepertoryID()[2]+" "+tempGoodspo.getEnterRepertoryID()[3]+" "
			    				+tempGoodspo.getLeaveTime()[0]+" " +tempGoodspo.getLeaveTime()[1]+" "+tempGoodspo.getLeaveTime()[2]+" "+tempGoodspo.getLeaveTime()[3]+ " "
			    				+tempGoodspo.getLeaveRepertoryID()[0]+" " +tempGoodspo.getLeaveRepertoryID()[1]+" "+tempGoodspo.getLeaveRepertoryID()[2]+" "+tempGoodspo.getLeaveRepertoryID()[3]+" "
			    				+tempGoodspo.isInRepertory());
						System.out.println("进入本仓库的时间为"+tempGoodspo.getThisRepertoryEnterTime("025-0-CK"));
					}
				}
				
				System.out.println("查看从上次制定出库单到现在出库货物");
				ArrayList<GoodsPO> leaveRepertoryGoodsList = repertoryData.getLeaveRepertoryGoods("025-0-CK");
				if(leaveRepertoryGoodsList != null){
					int size = leaveRepertoryGoodsList.size();
					for(int i=0; i<size;i++){
						GoodsPO tempGoodspo = leaveRepertoryGoodsList.get(i);
						System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
			    				+tempGoodspo.getLeaveTime()[0]+" "+tempGoodspo.getLeaveTime()[1]+" "+tempGoodspo.getLeaveTime()[2]+" "+tempGoodspo.getLeaveTime()[3]+" "
			    				+tempGoodspo.getLeaveRepertoryID()[0]+" "+tempGoodspo.getLeaveRepertoryID()[1]+" "+tempGoodspo.getLeaveRepertoryID()[2]+" "+tempGoodspo.getLeaveRepertoryID()[3]+" "
			    				+tempGoodspo.getLeaveTime()[0]+" " +tempGoodspo.getLeaveTime()[1]+" "+tempGoodspo.getLeaveTime()[2]+" "+tempGoodspo.getLeaveTime()[3]+ " "
			    				+tempGoodspo.getLeaveRepertoryID()[0]+" " +tempGoodspo.getLeaveRepertoryID()[1]+" "+tempGoodspo.getLeaveRepertoryID()[2]+" "+tempGoodspo.getLeaveRepertoryID()[3]+" "
			    				+tempGoodspo.isInRepertory());
						System.out.println("离开本仓库的时间为"+tempGoodspo.getThisRepertoryLeaveTime("025-0-CK"));
					}
				}
				
				
				System.out.println("库存盘点");
				String time1 = getTimeNow();
				ArrayList<InventoryPO> inventoryList1 = repertoryData.findInventorybyTime("025-0-CK", time1);
				if(inventoryList1 != null){
					System.out.println(inventoryList1.size());
					for(int i=0;i<inventoryList1.size();i++){
						InventoryPO tempInventory = inventoryList1.get(i);
						System.out.println(tempInventory.getGood().getOrder_ID()+" "+tempInventory.getGood().getFee()+" "+tempInventory.getGood().getDeparturePlace()+" "
								+tempInventory.getGood().getDestination()+" "+tempInventory.getBlockNum()+" "+tempInventory.getRowNum()+" "+tempInventory.getShelfNum()+" "
								+tempInventory.getDigitNum());
					}
				}
				else 
					System.out.println("Cannot find the inventory");
				
				//System.out.println("出库：");
				
				InventoryCheckPO inventoryCheck = repertoryData.findInventorybyDate("025-0-CK", "2015-11-01", "2015-12-25");
				if(inventoryCheck != null){
					int stockNum[] = inventoryCheck.getStockNumArray();
					System.out.println("入库数量："+inventoryCheck.getEnterTotal()+"  出库数量："+inventoryCheck.getLeaveTotal()+
					" 入库金额总和："+inventoryCheck.getEnterFeeTotal()+" 出库金额总和： "+inventoryCheck.getLeaveFeeTotal()
					+" 库存数量统计："+" 飞机区： "+stockNum[0]+" 火车区： "+stockNum[1]+" 汽车区： "+stockNum[2]+" 机动区： "+stockNum[3]);
				}
				else 
					System.out.println("Cannot find the inventory");
				
			}catch(RemoteException exception){
				exception.printStackTrace();
				}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}
	
	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/
	
	/*public static void main(String[] args){
     	try{
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			RepertoryDataService repertoryData = new RepertoryData();
			LocateRegistry.createRegistry(6001);
			
			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.25.132.40:6001/RepertoryDataService", repertoryData);
			System.out.println("Repertory Service start!");
			
			ArrayList<RepertoryPO> repertoryList0 = repertoryData.showAllRepertorys();
			for(RepertoryPO repertory:repertoryList0)
				System.out.println("ID: "+repertory.getRepertoryID()+", Owner: "+repertory.getOwnerID());
			
			
			RepertoryPO repertorypo = repertoryData.findRepertory("030-CK");
				System.out.println("ID: "+repertorypo.getRepertoryID()+", Owner: "+repertorypo.getOwnerID());
				
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
}
