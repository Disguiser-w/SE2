package businesslogic.repertorybl.controller;

import java.util.ArrayList;

import vo.GoodsVO;
import businesslogic.repertorybl.GoodsBL;
import businesslogicservice.repertoryblservice.GoodsBLService;

public class GoodsController implements GoodsBLService{

	private GoodsBL goodsBL;
	
	public GoodsController(){
		goodsBL = new GoodsBL();
	}
	
	public GoodsVO findGoodsByID(String goodsID){
		return goodsBL.findGoodsByID(goodsID);
	}
	
	public ArrayList<GoodsVO> findGoodsBykeyword(String keyword){
		return goodsBL.findFreeGoodsByKeyword(keyword);
	}
	
	public ArrayList<GoodsVO> findFreeGoodsByKeyword(String keyword){
		return goodsBL.findFreeGoodsByKeyword(keyword);
	}
	
	public String getEnterSpecificRepertoryDate(String goodsID, String repertoryID){
		return goodsBL.getEnterSpecificRepertoryDate(goodsID, repertoryID);
	}
	
	public String getLeaveSpecificRepertoryDate(String goodsID, String repertoryID){
		return goodsBL.getLeaveSpecificRepertoryDate(goodsID, repertoryID);
	}
	
	public String showGoodBasicMessage(String goodsID){
		return goodsBL.showGoodBasicMessage(goodsID);
	}
	
	public String showGoodIntermidiateMessage(String goodsID){
		return goodsBL.showGoodIntermidiateMessage(goodsID);
	}
	
	public ArrayList<GoodsVO> getAllFreeGoods(){
		return goodsBL.getAllFreeGoods();
	}
	
}
