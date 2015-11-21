package vo;

import vo.GoodsVO;

import java.util.ArrayList;

public class InventoryVO {
	private GoodsVO good;
	private int blockNum, rowNum, shelfNum, digitNum;
	
	public InventoryVO(GoodsVO good, int blockNum, int rowNum, int shelfNum, int digitNum){
		this.good = good;
		this.blockNum = blockNum;
		this.rowNum = rowNum;
		this.shelfNum  = shelfNum;
		this.digitNum = digitNum;
	}
	
}
