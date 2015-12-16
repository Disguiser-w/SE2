package vo;

public class RepertoryVO {

	public String repertoryID, ownerID;
	public int maxRow, maxShelf, maxDigit,warningRatio;
	public int stockNum[];

	public RepertoryVO(String repertoryID, String ownerID){
		this.repertoryID = repertoryID;
		this.ownerID = ownerID;
		this.maxRow = 10;
		this.maxShelf = 10;
		this.maxDigit = 10;
		this.warningRatio = 80;
		stockNum = new int [4];
		/*this.planeBlockStockNum = 0;
		this.trainBlockStockNum = 0;
		this.truckBlockStockNum = 0;
		this.defaultBlockStockNum = 0; */
	}
	
	public RepertoryVO(String repertoryID, String ownerID, int maxRow, int maxShelf, int maxDigit, int warningRatio, int stockNum[]) {
		// 仓库编号 对应仓库管理员编号 最多多少排 最多多少架 最多多少位 警戒比例
		// 025-0-CK CK-00001
		this.repertoryID = repertoryID;
		this.ownerID = ownerID;
		this.maxRow = maxRow;
		this.maxShelf = maxShelf;
		this.maxDigit = maxDigit;
		this.warningRatio = warningRatio;
		this.stockNum = stockNum;
	}

}
