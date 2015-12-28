package businesslogic.repertorybl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.datafactory.DataFactory;
import businesslogicservice.repertoryblservice.RepertoryBLService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.repertorydataservice.EnterRepertoryReceiptDataService;
import dataservice.repertorydataservice.GoodsDataService;
import dataservice.repertorydataservice.LeaveRepertoryReceiptDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import po.EnterRepertoryReceiptPO;
import po.GoodsPO;
import po.InventoryCheckPO;
import po.InventoryPO;
import po.LeaveRepertoryReceiptPO;
import po.OrganizationPO;
import po.RepertoryPO;
import vo.EnterRepertoryReceiptVO;
import vo.GoodsVO;
import vo.InventoryCheckVO;
import vo.InventoryVO;
import vo.LeaveRepertoryReceiptVO;
import vo.RepertoryVO;

public class RepertoryBL implements RepertoryBLService{

	public static OrganizationDataService odService;
	public static RepertoryDataService rdService;
	public static EnterRepertoryReceiptDataService errdService;
	public static LeaveRepertoryReceiptDataService lrrdService;
	public static GoodsDataService gdService;
	
	private String repertoryID;
	
	public RepertoryBL(String stockManID){
		try{
			odService = DataFactory.getOrganizationData();
			rdService = DataFactory.getRepertoryData();
			errdService = DataFactory.getEnterRepertoryReceiptData();
			lrrdService = DataFactory.getLeaveRepertoryReceiptData();
			gdService = DataFactory.getGoodsData();
			
			repertoryID = rdService.findRepertoryByOwnerID(stockManID).getRepertoryID();
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
			
			GoodsPO goodspo = gdService.findGoodsByID(JJD_ID);
			
			//把GoodsPO的一个未填写的enterTime补充为现在的时间，进入的仓库编号中增加该仓库编号
			String time = getTimeNow();
			gdService.modifyGoodsEnterTime(JJD_ID, time);
			gdService.modifyGoodsEnterRepertoryID(JJD_ID, repertoryID);
			gdService.modifyGoodsState(JJD_ID, true);
			
			//上两句改的是数据层的数据，goodspo里的入库记录没有被修改，直接以此新建inventorypo的话入库记录全是空的
			//所以增加以下三句
			goodspo.setEnterTime(time);
			goodspo.setEnterRepertoryID(repertoryID);
			goodspo.setInRepertory(true);
			
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
			gdService.modifyGoodsState(JJD_ID, false);
			
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
	
	
	public static GoodsVO goodsPOToVO(GoodsPO goodspo){
		return new GoodsVO(goodspo.getOrder_ID(), goodspo.getFee(), goodspo.getDeparturePlace(), goodspo.getDestination(), goodspo.getEnterDate(), goodspo.getLeaveDate());
	}

	public static InventoryVO inventoryPOToVO(InventoryPO inventorypo){
		GoodsPO goodpo = inventorypo.getGood();
		GoodsVO goodvo = goodsPOToVO(goodpo);
		return new InventoryVO(goodvo, inventorypo.getBlockNum(), inventorypo.getRowNum(), inventorypo.getShelfNum(), inventorypo.getDigitNum());
	}
	
	public static InventoryCheckVO inventoryCheckPOToVO(InventoryCheckPO inventorycheckpo){
		return new InventoryCheckVO(inventorycheckpo.getEnterTotal(), inventorycheckpo.getLeaveTotal(), inventorycheckpo.getEnterFeeTotal(),inventorycheckpo.getLeaveFeeTotal(), inventorycheckpo.getStockNumArray());
	}
	
	public static RepertoryVO repertoryPOToVO(RepertoryPO repertorypo){
		return new RepertoryVO(repertorypo.getRepertoryID(), repertorypo.getOwnerID(), 
				repertorypo.getMaxRow(), repertorypo.getMaxShelf(), repertorypo.getMaxDigit(), 
				repertorypo.getWarningRatio(), repertorypo.getStockNumArray());
	}
	
	public static EnterRepertoryReceiptPO enterRepertoryReceiptVOToPO(EnterRepertoryReceiptVO errvo){
		return new EnterRepertoryReceiptPO(errvo.receiptID, errvo.userID, errvo.createTime, errvo.state, errvo.repertoryID, errvo.expressIDList, errvo.timeList);
	}
	
	public static EnterRepertoryReceiptVO enterRepertoryReceiptPOToVO(EnterRepertoryReceiptPO errpo){
		return new EnterRepertoryReceiptVO(errpo.getReceiptID(), errpo.getUserID(), errpo.getCreateTime(), errpo.getState(), errpo.getReceiptID(), errpo.getExpressIDList(), errpo.getTimeList());
	}
	
	public static LeaveRepertoryReceiptPO leaveRepertoryReceiptVOToPO(LeaveRepertoryReceiptVO lrrvo){
		return new LeaveRepertoryReceiptPO(lrrvo.receiptID, lrrvo.userID, lrrvo.createTime, lrrvo.state, lrrvo.repertoryID, lrrvo.expressIDList, lrrvo.timeList);
	}
	
	public static LeaveRepertoryReceiptVO leaveRepertoryReceiptPOToVO(LeaveRepertoryReceiptPO lrrpo){
		return new LeaveRepertoryReceiptVO(lrrpo.getReceiptID(), lrrpo.getUserID(), lrrpo.getCreateTime(), lrrpo.getState(), lrrpo.getReceiptID(), lrrpo.getExpressIDList(), lrrpo.getTimeList());
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
	
	
	/**
	 * 获取从上次制定入库单之后进入过仓库的货物记录
	 * 
	 * @param String repertoryID
	 * @return ArrayList<GoodsVO>
	 * 
	 * */
	public ArrayList<GoodsVO> getEnterRepertoryGoods(){
		ArrayList<GoodsVO> enterRepertoryGoodsVOList = new ArrayList<GoodsVO>();
		try{
			
			ArrayList<GoodsPO> enterRepertoryGoodsPOList = rdService.getEnterRepertoryGoods(repertoryID);
			
			if(enterRepertoryGoodsPOList != null){
				for(GoodsPO goodspo : enterRepertoryGoodsPOList){
					enterRepertoryGoodsVOList.add(goodsPOToVO(goodspo));
				}
			}
			return enterRepertoryGoodsVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return enterRepertoryGoodsVOList;
		}
	}
	
	
	/**
	 * 获取从上次制定出库单之后离开过仓库的货物记录
	 * 
	 * @param String repertoryID
	 * @return ArrayList<GoodsVO>
	 * 
	 * */
	public ArrayList<GoodsVO> getLeaveRepertoryGoods(){
		ArrayList<GoodsVO> leaveRepertoryGoodsVOList = new ArrayList<GoodsVO>();
		try{
			
			ArrayList<GoodsPO> leaveRepertoryGoodsPOList = rdService.getLeaveRepertoryGoods(repertoryID);
			
			if(leaveRepertoryGoodsPOList != null){
				for(GoodsPO goodspo : leaveRepertoryGoodsPOList){
					leaveRepertoryGoodsVOList.add(goodsPOToVO(goodspo));
				}
			}
			return leaveRepertoryGoodsVOList;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return leaveRepertoryGoodsVOList;
		}
	}
	
	
	public String getLastEnterRepertoryTime(){
		try{
			return rdService.getLastCreateEnterReceiptTime(repertoryID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "wrong";
		}
	}
	
	
	public String getLastLeaveRepertoryTime(){
		try{
			return rdService.getLastCreateLeaveReceiptTime(repertoryID);
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "wrong";
		}
	}
	
	
	/*public int AddEnterRepertoryReceipt(String[] goodsIDList, String[] timeList){
		String now = getTimeNow();
		try{
			String receiptPost = errdService.getEnterReceiptPost();
			EnterRepertoryReceiptVO errvo = new EnterRepertoryReceiptVO("RKD-"+receiptPost, userID, now, ReceiptState.DRAFT, repertoryID, goodsIDList, timeList);
			EnterRepertoryReceiptPO errpo = enterRepertoryReceiptVOToPO(errvo);
			
			//更新仓库的最近一次生成入库单的时间
			int returnNum1 = rdService.setLastCreateEnterReceiptTime(repertoryID, now);
			//添加入库单
			int returnNum2 = errdService.addEnterRepertoryReceipt(errpo);
			
			return returnNum1+returnNum2;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}
	
	
	public int AddLeaveRepertoryReceipt(String[] goodsIDList, String[] timeList){
		String now = getTimeNow();
		try{
			String receiptPost = lrrdService.getLeaveReceiptPost();
			LeaveRepertoryReceiptVO errvo = new LeaveRepertoryReceiptVO("CKD-"+receiptPost, userID, now, ReceiptState.DRAFT, repertoryID, goodsIDList, timeList);
			LeaveRepertoryReceiptPO errpo = leaveRepertoryReceiptVOToPO(errvo);
			
			//更新仓库的最近一次生成出库单的时间
			int returnNum1 = rdService.setLastCreateLeaveReceiptTime(repertoryID, now);
			//添加出库单
			int returnNum2 = lrrdService.addLeaveRepertoryReceipt(errpo);
			
			return returnNum1+returnNum2;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return 2;
		}
	}*/
	
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
