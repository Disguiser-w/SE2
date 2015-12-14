package data.repertorydata;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import dataservice.repertorydataservice.RepertoryDataService;
import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;
import po.InventoryCheckPO;
import po.OrganizationPO;
import file.JXCFile;

public class RepertoryData extends UnicastRemoteObject implements RepertoryDataService{

	//我也不知道下面这句话有什么用，只是因为继承了UnicastRemoteObject所以要声明这样一个字段
	private static final long serialVersionUID = 131250148L;
	
	JXCFile organizationFile;
	JXCFile goodsFile;
	GoodsData goodsData;
	
	public RepertoryData() throws RemoteException{
		organizationFile = new JXCFile("src/organization.ser");
		goodsFile = new JXCFile("src/goods.ser");
		goodsData = new GoodsData();
	}
	
	public int modifyRepertoryOwner(String repertoryID, String ownerID){
		ArrayList<Object> organizationList = organizationFile.read();

		int returnNum = 1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				repertory.setOwnerID(ownerID);
				returnNum = 0;
				break;
			}
		}
		
		organizationFile.writeM(organizationList);
		return returnNum;
	}
	
	
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException{
		ArrayList<Object> organizationList = organizationFile.read();

		int returnNum = 1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertorypo.getRepertoryID())){
				repertory.setOwnerID(repertorypo.getOwnerID());
				repertory.setMaxRow(repertorypo.getMaxRow());
				repertory.setMaxShelf(repertorypo.getMaxShelf());
				repertory.setMaxDigit(repertorypo.getMaxDigit());
				repertory.setWarningRatio(repertorypo.getWarningRatio());
				/*int []a = repertorypo.getStockNumArray();
				if(a != null)
					System.out.println(a[0]+" "+a[1]+" "+a[2]+" "+a[3]);
				else 
					System.out.println("a = null!!!");*/
				repertory.setStockNumArray(repertorypo.getStockNumArray());
				returnNum = 0;
				break;
			}
		}
		
		organizationFile.writeM(organizationList);
		return returnNum;
	}
	
	public RepertoryPO findRepertory(String repertoryID) throws RemoteException{
		ArrayList<Object> organizationList = organizationFile.read();

		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				return repertory;
			}
		}
		return null;
	}
	
	public RepertoryPO findRepertoryByOwnerID(String ownerID) throws RemoteException{
		ArrayList<Object> organizationList = organizationFile.read();

		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getOwnerID().equals(ownerID)){
				return repertory;
			}
		}
		return null;
	}
	
	public ArrayList<RepertoryPO> showAllRepertorys() throws RemoteException{
		ArrayList<Object> organizationList = organizationFile.read();

		if(organizationList==null)
			return null;
		
		ArrayList<RepertoryPO> repertoryList = new ArrayList<RepertoryPO>();
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null)
				repertoryList.add(repertory);
		}
		return repertoryList;
	}
	
	public int addInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException{
		ArrayList<Object> organizationList = organizationFile.read();

		int returnNum =1;
		for(int i=0;i<organizationList.size();i++){
			OrganizationPO organization = (OrganizationPO)organizationList.get(i);
			RepertoryPO repertory = organization.getRepertory();
			if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
				ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
				inventoryList.add(inventorypo);
				repertory.stockNumPlus(inventorypo.getBlockNum());
				
				//把GoodsPO的一个未填写的enterTime补充为现在的时间，进入的仓库编号中增加该仓库编号
				GoodsPO goodspo = inventorypo.getGood();
				String time = getTimeNow();
				goodspo.setEnterRepertoryID(repertoryID);
				goodspo.setEnterTime(time);
				goodsData.modifyGoods(goodspo);
				
				returnNum = 0;
				break;
			}
		}
		
		organizationFile.writeM(organizationList);
		return returnNum;
	}
	
	public int deleteInventory(String repertoryID, String JJD_ID) throws RemoteException{
		ArrayList<Object> organizationList = organizationFile.read();

		int returnNum =1;
loop1:		for(int i=0;i<organizationList.size();i++){
				OrganizationPO organization = (OrganizationPO)organizationList.get(i);
				RepertoryPO repertory = organization.getRepertory();
				if(repertory!=null && repertory.getRepertoryID().equals(repertoryID)){
					ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
	loop2:				for(int j=0;j<inventoryList.size();j++){
							InventoryPO tempInventory = (InventoryPO)inventoryList.get(j);
							if(tempInventory.getGood().getOrder_ID().equals(JJD_ID)){
								inventoryList.remove(tempInventory);
								repertory.stockNumSub(tempInventory.getBlockNum());
								
								//把GoodsPO的一个未填写的leaveTime补充为现在的时间，离开的仓库编号中增加该仓库编号
								GoodsPO goodspo = tempInventory.getGood();
								String time = getTimeNow();
								goodspo.setLeaveRepertoryID(repertoryID);
								goodspo.setLeaveTime(time);
								goodsData.modifyGoods(goodspo);
								
								returnNum = 0;
								break loop2;
							}
						}
					break loop1;
				}
		}
		
		organizationFile.writeM(organizationList);
		return returnNum;
	}
	
	public int modifyInventory(String repertoryID, InventoryPO inventorypo) throws RemoteException{
		RepertoryPO repertory = findRepertory(repertoryID);
		ArrayList<InventoryPO> inventoryList = repertory.getInventoryList();
		for(int i=0;i<inventoryList.size();i++){
			InventoryPO tempInventory = (InventoryPO)inventoryList.get(i);
			if(tempInventory.getGood().getOrder_ID().equals(inventorypo.getGood().getOrder_ID())){
				tempInventory.setBlockNum(inventorypo.getBlockNum());
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
		return goodsData.findGoods(JJD_ID);
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
		
		ArrayList<Object> goodsList = goodsFile.read();
		for(int i=0;i<goodsList.size();i++){
			GoodsPO tempGoods = (GoodsPO)goodsList.get(i);
			String[] enterRepertory = tempGoods.getEnterRepertoryID();
			String[] leaveRepertory = tempGoods.getLeaveRepertoryID();
			String[] enterDate = tempGoods.getEnterDate();
			String[] leaveDate = tempGoods.getLeaveDate();
			for(int j=0;j<4;j++){
				if((enterDate[j]!=null) && (leaveDate[j]!=null)){
					/*System.out.println("ID: "+tempGoods.getOrder_ID()+"  Enter:"+enterDate[j]+"  Leave: "+leaveDate[j]);
					System.out.println((enterDate[j].compareTo(beginDate)>=0));
					System.out.println((enterDate[j].compareTo(endDate)<=0));
					System.out.println((leaveDate[j].compareTo(beginDate)>=0));
					System.out.println((leaveDate[j].compareTo(endDate)<=0));*/
					
					if((enterDate[j]!=null) && (enterRepertory[j].equals(repertoryID)) && (enterDate[j].compareTo(beginDate)>=0) && (enterDate[j].compareTo(endDate)<=0)){
						inventoryCheckPO.enterTotalPlus();
						inventoryCheckPO.enterFeeTotalPlus(tempGoods.getFee());
					}
					if((leaveDate[j]!=null) && (leaveRepertory[j].equals(repertoryID)) && (leaveDate[j].compareTo(beginDate)>=0) && (leaveDate[j].compareTo(endDate)<=0)){
						inventoryCheckPO.leaveTotalPlus();
						inventoryCheckPO.leaveFeeTotalPlus(tempGoods.getFee());
					}
				}
			}
		}
		
		RepertoryPO repertoryPO = findRepertory(repertoryID);
		inventoryCheckPO.setStockNumArray(repertoryPO.getStockNumArray());
		
		return inventoryCheckPO;
		
	} 
	
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
	
	//给inventoryList排序：先排区号，如果区号一样排行号，如果行号一样排架号，如果架号一样排位号
	//这么恶心的排序还是用了Comparator方法，我也是醉了（觉得本宝宝越来越聪明了呢！！！）
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
	
	//获取现在的时间，便于出入库登记操作
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
	
	/*public static void main(String[] args){
		RepertoryData repertoryData;
		try{
			repertoryData = new RepertoryData();
			try{
				ArrayList<RepertoryPO> repertoryList0 = repertoryData.showAllRepertorys();
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
					System.out.println("Cannot find the reperory");
				
				RepertoryPO repertory = repertoryData.findRepertory("040-CK");
				if(repertory != null)
					System.out.println("Find the repertory: "+repertory.getRepertoryID()+" "+repertory.getOwnerID()+" "+repertory.getMaxRow()
							+" "+repertory.getMaxShelf()+" "+repertory.getMaxDigit()+" "
							+repertory.getWarningRatio()+" "+repertory.getStockNum(0)+" "+repertory.getStockNum(1)
							+" "+repertory.getStockNum(2)+" "+repertory.getStockNum(3));
				else
					System.out.println("Cannot find the repertory");
				
				repertoryData.modifyRepertory(new RepertoryPO("030-CK", "CK-01", 100,10,10,50,new int[4]));
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
					System.out.println("Cannot find the reperory");
				
				System.out.println("入库：");
				repertoryData.addInventory("030-CK", new InventoryPO(new GoodsPO("20151001-00001", 12, "南京", "上海"),3,0,0,0));
				repertoryData.addInventory("030-CK", new InventoryPO(new GoodsPO("20151002-00003", 15, "北京", "上海"),2,0,1,0));
				repertoryData.addInventory("040-CK", new InventoryPO(new GoodsPO("20151101-00012", 120, "洛杉矶", "北京"),1,0,6,0));
				repertoryData.addInventory("030-CK", new InventoryPO(new GoodsPO("20151024-00007", 250, "南京", "北极"),0,5,0,0));
				repertoryData.addInventory("030-CK", new InventoryPO(new GoodsPO("20151124-00004", 30, "铁岭", "海南"),2,5,0,0));
				
				System.out.println("查找库存");
				InventoryPO inventorypo1 = repertoryData.findInventorybyID("030-CK", "20151001-00001");
				if(inventorypo1 != null)
					System.out.println("Find the inventory: "+inventorypo1.getGood().getOrder_ID()+" "+inventorypo1.getGood().getFee()+" "+inventorypo1.getGood().getDeparturePlace()
							+" "+inventorypo1.getGood().getDestination()+" "+inventorypo1.getGood().getLatestEnterTime()+" "+inventorypo1.getGood().getLatestLeaveTime()+" "
							+inventorypo1.getBlockNum()+" "+inventorypo1.getRowNum()+" "+inventorypo1.getShelfNum()+" "+inventorypo1.getDigitNum());
				else
					System.out.println("Cannot find the inventory");
				
				System.out.println("查找库存");
				InventoryPO inventorypo2 = repertoryData.findInventorybyID("030-CK", "20151001-00005");
				if(inventorypo2 != null)
					System.out.println("Find the inventory: "+inventorypo2.getGood().getOrder_ID()+" "+inventorypo2.getGood().getFee()+" "+inventorypo2.getGood().getDeparturePlace()
							+" "+inventorypo2.getGood().getDestination()+" "+inventorypo2.getGood().getLatestEnterTime()+" "+inventorypo2.getGood().getLatestLeaveTime()+" "
							+inventorypo2.getBlockNum()+" "+inventorypo2.getRowNum()+" "+inventorypo2.getShelfNum()+" "+inventorypo2.getDigitNum());
				else
					System.out.println("Cannot find the inventory");
				
				System.out.println("库存盘点");
				ArrayList<InventoryPO> inventoryList1 = repertoryData.findInventorybyTime("030-CK", "now");
				if(inventoryList1 != null){
					for(int i=0;i<inventoryList1.size();i++){
						InventoryPO tempInventory = inventoryList1.get(i);
						System.out.println(tempInventory.getGood().getOrder_ID()+" "+tempInventory.getGood().getFee()+" "+tempInventory.getGood().getDeparturePlace()+" "
								+tempInventory.getGood().getDestination()+" "+tempInventory.getBlockNum()+" "+tempInventory.getRowNum()+" "+tempInventory.getShelfNum()+" "
								+tempInventory.getDigitNum());
					}
				}
				else 
					System.out.println("Cannot find the inventory");
				
				System.out.println("出库：");
				repertoryData.deleteInventory("030-CK", "20151002-00003");
				
				System.out.println("库存盘点");
				ArrayList<InventoryPO> inventoryList2 = repertoryData.findInventorybyTime("030-CK", "now");
				if(inventoryList2 != null){
					for(int i=0;i<inventoryList2.size();i++){
						InventoryPO tempInventory = inventoryList2.get(i);
						System.out.println(tempInventory.getGood().getOrder_ID()+" "+tempInventory.getGood().getFee()+" "+tempInventory.getGood().getDeparturePlace()+" "
								+tempInventory.getGood().getDestination()+" "+tempInventory.getBlockNum()+" "+tempInventory.getRowNum()+" "+tempInventory.getShelfNum()+" "
								+tempInventory.getDigitNum());
					}
				}
				else 
					System.out.println("Cannot find the inventory");
				
				InventoryCheckPO inventoryCheck = repertoryData.findInventorybyDate("030-CK", "2015-11-01", "2015-12-01");
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
	}*/
	
	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/
	public static void main(String[] args){
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
	}
	
	
}
