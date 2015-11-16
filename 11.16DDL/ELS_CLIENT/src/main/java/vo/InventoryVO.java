package vo;

import vo.GoodsVO;

import java.util.ArrayList;

public class InventoryVO {
	public ArrayList<GoodsVO> Goods_List;
	
	public InventoryVO(){
		Goods_List = new ArrayList<GoodsVO> ();
	}
	
	public void addGoods(GoodsVO goods){
		Goods_List.add(goods);
	}
	
	public void deleteGoods(GoodsVO goods){
		Goods_List.remove(goods);
	}
	
	public ArrayList<GoodsVO> showInventory(){
		return this.Goods_List;
	}
	
}
