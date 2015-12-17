package vo;

public class InventoryVO {
	
	public GoodsVO good;
	public int blockNum, rowNum, shelfNum, digitNum;

	public InventoryVO(GoodsVO good, int blockNum, int rowNum, int shelfNum,
			int digitNum) {
		this.good = good;
		this.blockNum = blockNum;
		this.rowNum = rowNum;
		this.shelfNum = shelfNum;
		this.digitNum = digitNum;
	}

}
