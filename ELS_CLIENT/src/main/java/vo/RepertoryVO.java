package vo;

public class RepertoryVO {

	private String repertoryID, ownerID;
	private int maxRow, maxShelf, maxDigit,warningRadio;
	private int stockNum[];

	public RepertoryVO(String repertoryID, String ownerID){
		this.repertoryID = repertoryID;
		this.ownerID = ownerID;
		this.maxRow = 10;
		this.maxShelf = 10;
		this.maxDigit = 10;
		this.warningRadio = 80;
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
		this.warningRadio = warningRatio;
		this.stockNum = stockNum;
	}

	public String getRepertoryID() {
		return this.repertoryID;
	}

	public void setRepertoryID(String repertoryID) {
		this.repertoryID = repertoryID;
	}

	public String getOwnerID() {
		return this.ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public int getMaxRow() {
		return this.maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxShelf() {
		return this.maxShelf;
	}

	public void setMaxShelf(int maxShelf) {
		this.maxShelf = maxShelf;
	}

	public int getMaxDigit() {
		return this.maxDigit;
	}

	public void setMaxDigit(int maxDigit) {
		this.maxDigit = maxDigit;
	}

	public int getWarningRatio() {
		return this.warningRadio;
	}

	public void setWarningRatio(int warningRatio) {
		this.warningRadio = warningRatio;
	}

	public int[] getStockNumArray(){
		return this.stockNum;
	}
	
}
