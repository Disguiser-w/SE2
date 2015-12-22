package businesslogic.repertorybl;

import java.util.ArrayList;
import java.util.Date;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat; 

import po.GoodsPO;
import po.InventoryPO;
import po.OrganizationPO;
import po.RepertoryPO;
import po.InventoryCheckPO;
import businesslogicservice.repertoryblservice.RepertoryBLService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.repertorydataservice.GoodsDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import vo.GoodsVO;
import vo.InventoryVO;
import vo.InventoryCheckVO;
import vo.RepertoryVO;

public class RepertoryBL implements RepertoryBLService{

	public static OrganizationDataService odService;
	public static RepertoryDataService rdService;
	public static GoodsDataService gdService;
	private String repertoryID;
	
	public RepertoryBL(String stockManID){
		try{
			odService = (OrganizationDataService)Naming.lookup("rmi://localhost:8888/OrganizationDataService");
			rdService = (RepertoryDataService)Naming.lookup("rmi://localhost:8888/RepertoryDataService");
			gdService = (GoodsDataService)Naming.lookup("rmi://localhost:8888/GoodsDataService");
			this.repertoryID = rdService.findRepertoryByOwnerID(stockManID).getRepertoryID();
		}catch(RemoteException | MalformedURLException | NotBoundException ex){
			ex.printStackTrace();
			repertoryID = "";
		}
	}
	
	/**
	 * @param String repertoryID, int maxRow, int maxShelf, int maxDigit, int warningRatio
	 * @return 0(initialize succeed), 1(initialize failed), 2(server failed)
	 * @see RepertoryPO
	 * 
	 * */
	public int inventoryInitialization(int maxRow, int maxShelf, 
										int maxDigit, int warningRatio){
		try{
			RepertoryPO repertorypo = rdService.findRepertory(repertoryID);
			RepertoryPO modifiedRepertoryPO = new RepertoryPO(repertoryID,repertorypo.getOwnerID(), maxRow, maxShelf, 
					 maxDigit, warningRatio, repertorypo.getStockNumArray());
			return(rdService.modifyRepertory(modifiedRepertoryPO));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param String repertoryID,, String JJD_ID, int blockNum, String time
	 * @return 0(enter succeed), 1(enter failed), 2(server failed)
	 * @see RepertoryPO,GoodsPO,InventoryPO
	 * 
	 * */
	public int enterRepertory(String JJD_ID, int blockNum, int rowNum, int shelfNum, int digitNum){
		try{
			/*String warningStr = inventoryWarning();
			if((warningStr.contains("0")) || (warningStr.contains("1")) || (warningStr.contains("2")))
				blockNum = 3;
			String vacantLocation = searchVacantLocation(blockNum);
			String locationParts[] = vacantLocation.split(" ");
			int rowNum = Integer.parseInt(locationParts[0]);
			int shelfNum = Integer.parseInt(locationParts[1]);
			int digitNum = Integer.parseInt(locationParts[2]);*/
			
			GoodsPO goodspo = rdService.findGoodsbyID(JJD_ID);
			
			//把GoodsPO的一个未填写的enterTime补充为现在的时间，进入的仓库编号中增加该仓库编号
			String time = getTimeNow();
			gdService.modifyGoodsEnterTime(JJD_ID, time);
			gdService.modifyGoodsEnterRepertoryID(JJD_ID, repertoryID);
			
			//把InventoryPO加入仓库库存列表中
			InventoryPO inventorypo = new InventoryPO(goodspo, blockNum, rowNum, shelfNum, digitNum);
			return(rdService.addInventory(repertoryID, inventorypo));
			
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param String repertoryID,, String JJD_ID, int transType, String time
	 * @return 0(leave succeed), 1(leave failed), 2(server failed)
	 * @see RepertoryPO,GoodsPO,InventoryPO
	 * 
	 * */
	public int leaveRepertory(String JJD_ID){
		try{
				
			//把GoodsPO的一个未填写的leaveTime补充为现在的时间，离开的仓库编号中增加该仓库编号
			String time = getTimeNow();
			gdService.modifyGoodsLeaveTime(JJD_ID, time);
			gdService.modifyGoodsLeaveRepertoryID(JJD_ID, repertoryID);
			
			//把InventoryPO从仓库库存列表中删除
			return(rdService.deleteInventory(repertoryID, JJD_ID));

		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	/**
	 * @param String repertoryID
	 * @return String warningStr(contains"0",planeBlock will warn;the same with"1"-trainBlock,"2"-truckBlock)
	 * @see RepertoryPO
	 * 
	 * */
	public String inventoryWarning(){
		try{
			RepertoryPO repertorypo = rdService.findRepertory(repertoryID);
			int blockMaxStockNum = repertorypo.getMaxRow() * repertorypo.getMaxShelf() * repertorypo.getMaxDigit();
			int warningRatio = repertorypo.getWarningRatio();
			double warningNumDouble = blockMaxStockNum * (warningRatio *1.0/100);
			int warningNumInt = (int)warningNumDouble;
			
			String warningStr = "";
			for(int i=0;i<3;i++){
				if(repertorypo.getStockNum(i) >= warningNumInt)
				warningStr += "i";
			}
			
			return warningStr;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return "Server failed!!";
		}
	}
	
	/**
	 * @param String repertoryID,String beginDate, String endDate
	 * @return InventoryCheckPO	(该时间段内的出/入库数量、金额，库存数量合计)
	 * 
	 * */
	public InventoryCheckVO inventoryCheck(String beginDate, String endDate){
		// 系统根据输入的时间段，显示该时间段内的出/入库数量、金额，库存数量合计
		try{
			InventoryCheckPO inventoryCheckPO = rdService.findInventorybyDate(repertoryID, beginDate, endDate);
			return inventoryCheckPOToVO(inventoryCheckPO);
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param String repertoryID
	 * @return ArrayList<InventoryVO>
	 * @see InventoryPO
	 * 
	 * */
	public ArrayList<InventoryVO> inventoryStockTaking(){
		try{
			String time = getTimeNow(); 
			ArrayList<InventoryPO> inventoryPOList= rdService.findInventorybyTime(repertoryID, time);
			ArrayList<InventoryVO> inventoryVOList = new ArrayList<InventoryVO>();
			for(InventoryPO inventorypo: inventoryPOList){
				inventoryVOList.add(inventoryPOToVO(inventorypo));
			}
			return inventoryVOList;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public String searchVacantLocation(int blockNum){
		try{
			RepertoryPO repertorypo = rdService.findRepertory(repertoryID);
			int rowNum = repertorypo.getStockNum(blockNum) / (repertorypo.getMaxShelf() * repertorypo.getMaxDigit());
			int shelfNum = repertorypo.getStockNum(blockNum) / repertorypo.getMaxDigit();
			int digitNum = repertorypo.getStockNum(blockNum) % repertorypo.getMaxDigit();
			return rowNum+" "+shelfNum+" "+digitNum;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return "Server failed!!";
		}
	}

	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
	public GoodsVO GoodsPOToVO(GoodsPO goodspo){
		return new GoodsVO(goodspo.getOrder_ID(), goodspo.getFee(), goodspo.getDeparturePlace(), goodspo.getDestination(), goodspo.getEnterDate(), goodspo.getLeaveDate());
	}

	public InventoryVO inventoryPOToVO(InventoryPO inventorypo){
		GoodsPO goodpo = inventorypo.getGood();
		GoodsVO goodvo = GoodsPOToVO(goodpo);
		return new InventoryVO(goodvo, inventorypo.getBlockNum(), inventorypo.getRowNum(), inventorypo.getShelfNum(), inventorypo.getDigitNum());
	}
	
	public InventoryCheckVO inventoryCheckPOToVO(InventoryCheckPO inventorycheckpo){
		return new InventoryCheckVO(inventorycheckpo.getEnterTotal(), inventorycheckpo.getLeaveTotal(), inventorycheckpo.getEnterFeeTotal(),inventorycheckpo.getLeaveFeeTotal(), inventorycheckpo.getStockNumArray());
	}
	
	public RepertoryVO repertoryPOToVO(RepertoryPO repertorypo){
		return new RepertoryVO(repertorypo.getRepertoryID(), repertorypo.getOwnerID(), 
				repertorypo.getMaxRow(), repertorypo.getMaxShelf(), repertorypo.getMaxDigit(), 
				repertorypo.getWarningRatio(), repertorypo.getStockNumArray());
	}
	
	public ArrayList<RepertoryVO> showAllRepertorys(){
		try{
			ArrayList<RepertoryPO> repertoryPOList = rdService.showAllRepertorys();
			ArrayList<RepertoryVO> repertoryVOList = new ArrayList<RepertoryVO>();
			
			for(int i=0;i<repertoryPOList.size();i++){
				repertoryVOList.add(repertoryPOToVO(repertoryPOList.get(i)));
			}
			return repertoryVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public InventoryVO findInventory(String goodsID){
		try{
			return inventoryPOToVO(rdService.findInventorybyID(repertoryID, goodsID));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public int getMaxRow(){
		try{
			return rdService.findRepertory(repertoryID).getMaxRow();
		}catch(RemoteException ex){
			ex.printStackTrace();
			return -1;
		}
	}
	
	public int getMaxShelf(){
		try{
			return rdService.findRepertory(repertoryID).getMaxShelf();
		}catch(RemoteException ex){
			ex.printStackTrace();
			return -1;
		}
	}
	
	public int getMaxDigit(){
		try{
			return rdService.findRepertory(repertoryID).getMaxDigit();
		}catch(RemoteException ex){
			ex.printStackTrace();
			return -1;
		}
	}
	public int getMaxRatio(){
		try{
			return rdService.findRepertory(repertoryID).getWarningRatio();
		}catch(RemoteException ex){
			ex.printStackTrace();
			return -1;
		}
	}
	
	public String showGoodBasicMessage(String goodID){
		try{
			String basicMessageStr = "";
			GoodsPO goods = rdService.findGoodsbyID(goodID);
			if(goods != null){
				basicMessageStr += "订单号：" + goods.getOrder_ID() + "\n";
				basicMessageStr += "费用：" + goods.getFee() + "\n";
				basicMessageStr += "出发地：" + goods.getDeparturePlace() + "          目的地：" + goods.getDestination() + "\n";
				return basicMessageStr;
			}
			else
				return "该订单号不存在";
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "服务器崩了，爽不爽！！！";
		}
	}
	
	public String showGoodIntermidiateMessage(String goodID){
		try{
			String intermediateMessageStr = "";
			GoodsPO goods = rdService.findGoodsbyID(goodID);
			if(goods != null){
				for(int i=0; i<4 ;i++){
					if(!goods.getEnterTime()[i].equals("无")){
						intermediateMessageStr += goods.getEnterTime()[i] + "进入" + repertoryName(goods.getEnterRepertoryID()[i]) + "\n";
					}
				}
				for(int i=0;i<4 ;i++){
					if(!goods.getLeaveTime()[i].equals("无")){
						intermediateMessageStr += goods.getLeaveTime()[i] + "离开" + repertoryName(goods.getLeaveRepertoryID()[i]) + "\n";
					}
				}
				if(intermediateMessageStr != "")
					return intermediateMessageStr;
				else
					return "暂无该订单号对应的物流中转信息";
			}
			else
				return "该订单号不存在";
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "服务器崩了，爽不爽！！！";
		}
	}
	
	public String repertoryName(String repertoryID){
		try{
			String organizationID = repertoryID.substring(0,5);
			OrganizationPO organizationpo = odService.findOrganizationByID(organizationID);
			return organizationpo.getName()+"仓库";
		}catch(RemoteException ex){
			ex.printStackTrace();
			return " ";
		}
	}
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	public static void main(String[] args){
		try {
			RepertoryDataService repertoryData = (RepertoryDataService)Naming.lookup("rmi://172.25.132.40:6001/RepertoryDataService");
			
			ArrayList<RepertoryPO> repertoryList0 = repertoryData.showAllRepertorys();
			for(RepertoryPO repertorypo:repertoryList0)
				System.out.println("ID: "+repertorypo.getRepertoryID()+", Owner: "+repertorypo.getOwnerID()+", maxRow: "+repertorypo.getMaxRow()
						+", maxShelf: "+repertorypo.getMaxShelf()+", maxDigit: "+repertorypo.getMaxDigit()+", warningRatio: "
						+repertorypo.getWarningRatio()+", PlaneBlockStock: "+repertorypo.getStockNum(0)+", TrainBlockStock: "+repertorypo.getStockNum(1)
						+", TruckBlockStock: "+repertorypo.getStockNum(2)+", DefaultBlockStock: "+repertorypo.getStockNum(3));

			int a[] = {21,22,23,24}; 
			repertoryData.modifyRepertory(new RepertoryPO("030-CK", "CK-09", 100, 150, 200, 60, a));
			
			ArrayList<RepertoryPO> repertoryList1 = repertoryData.showAllRepertorys();
			for(RepertoryPO repertorypo:repertoryList1)
				System.out.println("ID: "+repertorypo.getRepertoryID()+", Owner: "+repertorypo.getOwnerID()+", maxRow: "+repertorypo.getMaxRow()
						+", maxShelf: "+repertorypo.getMaxShelf()+", maxDigit: "+repertorypo.getMaxDigit()+", warningRatio: "
						+repertorypo.getWarningRatio()+", PlaneBlockStock: "+repertorypo.getStockNum(0)+", TrainBlockStock: "+repertorypo.getStockNum(1)
						+", TruckBlockStock: "+repertorypo.getStockNum(2)+", DefaultBlockStock: "+repertorypo.getStockNum(3));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
