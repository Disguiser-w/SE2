package businesslogic.repertorybl;

import java.util.ArrayList;
import java.util.Date;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat; 

import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;
import po.InventoryCheckPO;
import businesslogicservice.repertoryblservice.RepertoryBLService;
import dataservice.repertorydataservice.RepertoryDataService;
import vo.GoodsVO;
import vo.InventoryVO;
import vo.InventoryCheckVO;

public class RepertoryBL implements RepertoryBLService{

	public RepertoryDataService rdService;
	
	/**
	 * @param String repertoryID, int maxRow, int maxShelf, int maxDigit, int warningRatio
	 * @return 0(initialize succeed), 1(initialize failed), 2(server failed)
	 * @see RepertoryPO
	 * 
	 * */
	public int inventoryInitialization(String repertoryID, int maxRow, int maxShelf, 
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
	public int enterRepertory(String repertoryID, String JJD_ID, int blockNum, String time){
		try{
			String warningStr = inventoryWarning(repertoryID);
			if((warningStr.contains("0")) || (warningStr.contains("1")) || (warningStr.contains("2")))
				blockNum = 3;
			
			String vacantLocation = searchVacantLocation(repertoryID, blockNum);
			if(admitEnterRepertory(vacantLocation)){
				
				String locationParts[] = warningStr.split(" ");
				int rowNum = Integer.parseInt(locationParts[0]);
				int shelfNum = Integer.parseInt(locationParts[1]);
				int digitNum = Integer.parseInt(locationParts[2]);
				
				GoodsPO goodspo = rdService.findGoodsbyID(JJD_ID);
				
				//把GoodsPO的一个未填写的enterTime补充为现在的时间，进入的仓库编号中增加该仓库编号
				/*time = getTimeNow();
				goodspo.setEnterRepertoryID(repertoryID);
				goodspo.setEnterTime(time);*/
				
				//把InventoryPO加入仓库库存列表中
				InventoryPO inventorypo = new InventoryPO(goodspo, blockNum, rowNum, shelfNum, digitNum);
				return(rdService.addInventory(repertoryID, inventorypo));
			}
			else 
				return 1;
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
	public int leaveRepertory(String repertoryID, String JJD_ID, String time){
		try{
			InventoryPO inventorypo = rdService.findInventorybyID(repertoryID, JJD_ID);
			InventoryVO inventoryvo = inventoryPOToVO(inventorypo);
			if(admitLeaveRepertory(inventoryvo)){
				
				//把GoodsPO的一个未填写的leaveTime补充为现在的时间，离开的仓库编号中增加该仓库编号
				/*GoodsPO goodspo = rdService.findGoodsbyID(JJD_ID);
				time = getTimeNow();
				goodspo.setLeaveRepertoryID(repertoryID);
				goodspo.setLeaveTime(time);*/
				
				//把InventoryPO从仓库库存列表中删除
				return(rdService.deleteInventory(repertoryID, JJD_ID));
			}
			else
				return 1;
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
	public String inventoryWarning(String repertoryID){
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
	public InventoryCheckVO inventoryCheck(String repertoryID, String beginDate, String endDate){
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
	public ArrayList<InventoryVO> inventoryStockTaking(String repertoryID){
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
	
	public String searchVacantLocation(String repertoryID, int blockNum){
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
	
	public boolean admitEnterRepertory(String vacantLocation){
		/*if()
			return true;
		else */
			return false;
	}
	
	public boolean admitLeaveRepertory(InventoryVO inventoryvo){
		/*if()
			return true;
		else */
			return false;
	}
	
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
	public GoodsVO GoodsPOToVO(GoodsPO goodspo){
		return new GoodsVO(goodspo.getOrder_ID(), goodspo.getDeparturePlace(), goodspo.getDestination(), goodspo.getEnterDate(), goodspo.getLeaveDate());
	}

	public InventoryVO inventoryPOToVO(InventoryPO inventorypo){
		GoodsPO goodpo = inventorypo.getGood();
		GoodsVO goodvo = GoodsPOToVO(goodpo);
		return new InventoryVO(goodvo, inventorypo.getBlockNum(), inventorypo.getRowNum(), inventorypo.getShelfNum(), inventorypo.getDigitNum());
	}
	
	public InventoryCheckVO inventoryCheckPOToVO(InventoryCheckPO inventorycheckpo){
		return new InventoryCheckVO(inventorycheckpo.getEnterTotal(), inventorycheckpo.getLeaveTotal(), inventorycheckpo.getEnterFeeTotal(),inventorycheckpo.getLeaveFeeTotal(), inventorycheckpo.getStockNumArray());
	}
	
}
