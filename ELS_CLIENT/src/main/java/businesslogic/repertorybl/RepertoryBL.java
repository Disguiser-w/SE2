package businesslogic.repertorybl;

import java.util.ArrayList;
import java.util.Date;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat; 

import po.GoodsPO;
import po.InventoryPO;
import po.RepertoryPO;
import businesslogicservice.repertoryblservice.RepertoryBLService;
import dataservice.repertorydataservice.RepertoryDataService;
import vo.GoodsVO;
import vo.InventoryVO;

public class RepertoryBL implements RepertoryBLService{

	public RepertoryDataService rdService;
	
	/**
	 * @param String repertoryID, int maxRow, int maxShelf, int maxDigit, int warningRatio
	 * @return 0(initialize succeed), 1(server failed)
	 * @see RepertoryPO
	 * 
	 * */
	public int inventoryInitialization(String repertoryID, int maxRow, int maxShelf, 
										int maxDigit, int warningRatio){
		try{
			RepertoryPO repertorypo = rdService.findRepertory(repertoryID);
			repertorypo.setMaxRow(maxRow);
			repertorypo.setMaxShelf(maxShelf);
			repertorypo.setMaxDigit(maxDigit);
			repertorypo.setWarningRatio(warningRatio);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * @param String repertoryID,, String JJD_ID, int blockNum, String date
	 * @return 0(enterRepertory succeed), 1(server failed)
	 * @see RepertoryPO
	 * 
	 * */
	public int enterRepertory(String repertoryID, String JJD_ID, int blockNum, String date){
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
				
				GoodsPO goodpo = rdService.findGoodsbyID(JJD_ID);
				InventoryPO inventorypo = new InventoryPO(goodpo, blockNum, rowNum, shelfNum, digitNum);
				rdService.addInventory(repertoryID, inventorypo);
				//在repertoryDataService中的addInventory方法中，除了要把InventoryPO加入列表中，还要把GoodsPO的一个未填写的enterDate补充为今天的时间
			}
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int leaveRepertory(String repertoryID, String JJD_ID, int transType, String date){
		try{
			InventoryPO inventorypo = rdService.findInventorybyID(repertoryID, JJD_ID);
			InventoryVO inventoryvo = inventoryPOToVO(inventorypo);
			if(admitLeaveRepertory(inventoryvo)){
				rdService.deleteInventory(repertoryID, inventorypo);
			}
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
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
	
	public ArrayList<InventoryVO> inventoryCheck(String repertoryID, String beginDate, String endDate){
		try{
			ArrayList<InventoryPO> inventoryPOList= rdService.findInventorybyDate(repertoryID, beginDate, endDate);
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
	
	public ArrayList<InventoryVO> inventoryStockTaking(String repertoryID){
		try{
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String time = dateFormat.format(now); 
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
	
	public GoodsVO GoodsPOToVO(GoodsPO goodspo){
		return new GoodsVO(goodspo.getOrder_ID(), goodspo.getDeparturePlace(), goodspo.getDestination(), goodspo.getEnterDate(), goodspo.getLeaveDate());
	}
	
	/*public GoodsPO goodsVPToPO(GoodsVO goodsvo){
		return new GoodsPO(goodsvo.getOrder_ID(), goodsvo.getDeparturePlace(), goodsvo.getDestination(), goodsvo.getEnterDate(), goodsvo.getLeaveDate());
	}*/
	
	public InventoryVO inventoryPOToVO(InventoryPO inventorypo){
		GoodsPO goodpo = inventorypo.getGood();
		GoodsVO goodvo = GoodsPOToVO(goodpo);
		return new InventoryVO(goodvo, inventorypo.getBlcokNum(), inventorypo.getRowNum(), inventorypo.getShelfNum(), inventorypo.getDigitNum());
	}
	
}
