package businesslogic.repertorybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.repertorydataservice.GoodsDataService;
import po.GoodsPO;
import vo.GoodsVO;

public class GoodsBL {

	public static GoodsDataService gdService;
	public static OrganizationDataService odService;
	
	public GoodsBL(){
		try{
			gdService = DataFactory.getGoodsData();
			odService = DataFactory.getOrganizationData();
		}catch(RemoteException | MalformedURLException | NotBoundException ex){
			ex.printStackTrace();
		}
	}
	
	
	public ArrayList<GoodsVO> getAllFreeGoods(){
		try{
			ArrayList<GoodsPO> freeGoodsPOList = gdService.getAllFreeGoods();
			ArrayList<GoodsVO> freeGoodsVOList = new ArrayList<GoodsVO>();
			
			for(GoodsPO tmpGoodsPO : freeGoodsPOList){
				freeGoodsVOList.add(goodsPOToVO(tmpGoodsPO));
			}
			return freeGoodsVOList;
			
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public GoodsVO findGoodsByID(String orderID){
		try{
			return goodsPOToVO(gdService.findGoodsByID(orderID));
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<GoodsVO> findGoodsBykeyword(String keyword){
		try{
			ArrayList<GoodsPO> goodsPOList = gdService.findGoodsByKeyword(keyword);
			ArrayList<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
			
			for(GoodsPO tmpGoodsPO : goodsPOList){
				goodsVOList.add(goodsPOToVO(tmpGoodsPO));
			}
			return goodsVOList;
			
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}
	public ArrayList<GoodsVO> findFreeGoodsByKeyword(String keyword) {
		try{
			ArrayList<GoodsPO> goodsPOList = gdService.findFreeGoodsByKeyword(keyword);
			ArrayList<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
			
			for(GoodsPO tmpGoodsPO : goodsPOList){
				goodsVOList.add(goodsPOToVO(tmpGoodsPO));
			}
			return goodsVOList;
			
		}catch(RemoteException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public String getEnterSpecificRepertoryDate(String goodsID, String repertoryID){
		try{
			String date = gdService.findGoodsByID(goodsID).getThisRepertoryEnterTime(repertoryID);
			return date;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "服务器崩了，爽不爽！！！";
		}
	}
	
	public String getLeaveSpecificRepertoryDate(String goodsID, String repertoryID){
		try{
			String date = gdService.findGoodsByID(goodsID).getThisRepertoryLeaveTime(repertoryID);
			return date;
		}catch(RemoteException ex){
			ex.printStackTrace();
			return "服务器崩了，爽不爽！！！";
		}
	}
	
	public String showGoodBasicMessage(String goodID){
		try{
			String basicMessageStr = "";
			GoodsPO goods = gdService.findGoodsByID(goodID);
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
			GoodsPO goods = gdService.findGoodsByID(goodID);
			if(goods != null){
				for(int i = 0; i < 4;i++){
					if(goods.getEnterTime()[i].equals("无")){
						
					}
					else{
						intermediateMessageStr += goods.getEnterTime()[i] + "进入" + repertoryName(goods.getEnterRepertoryID()[i]) + "\n";
					}
				}
				for(int i=0;i<4 ;i++){
					if(goods.getLeaveTime()[i].equals("无")){
						
					}
					else{
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
		String intermediateID = repertoryID.substring(0,5);
		String repertoryName = "";
		try {
			repertoryName = odService.findOrganizationByID(intermediateID).getName() + "仓库";
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return repertoryName;
	}
	
	public static GoodsVO goodsPOToVO(GoodsPO goodspo){
		return new GoodsVO(goodspo.getOrder_ID(), goodspo.getFee(), goodspo.getDeparturePlace(), goodspo.getDestination(), goodspo.getEnterDate(), goodspo.getLeaveDate());
	}
	
}
