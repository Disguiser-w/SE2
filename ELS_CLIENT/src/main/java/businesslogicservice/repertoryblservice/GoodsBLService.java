package businesslogicservice.repertoryblservice;

import java.util.ArrayList;

import vo.GoodsVO;

public interface GoodsBLService {

	public GoodsVO findGoodsByID(String orderID);
	public ArrayList<GoodsVO> findGoodsBykeyword(String keyword);
	public ArrayList<GoodsVO> findFreeGoodsByKeyword(String keyword);
	public String getEnterSpecificRepertoryDate(String goodsID, String repertoryID);
	public String getLeaveSpecificRepertoryDate(String goodsID, String repertoryID);
	public String showGoodBasicMessage(String goodID);
	public String showGoodIntermidiateMessage(String goodID);
	public ArrayList<GoodsVO> getAllFreeGoods();
	
}
