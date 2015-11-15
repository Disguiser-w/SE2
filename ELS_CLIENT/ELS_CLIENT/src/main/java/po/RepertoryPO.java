package po;

public class RepertoryPO {

	private String repertoryID, ownerID;
	private int maxRow,  maxShelf, maxDigit;
	private double warningRadio;
	
	public RepertoryPO(String repertoryID, String ownerID, int maxRow, int maxShelf, int maxDigit, int warningRatio){
		this.repertoryID = repertoryID;
		this.ownerID = ownerID;
		this.maxRow = maxRow;
		this.maxShelf = maxShelf;
		this.maxDigit = maxDigit;
		this.warningRadio = warningRatio;
	}
	
	public String getRepertoryID(){
		return this.repertoryID;
	}
	
	public void setRepertoryID(String repertoryID){
		this.repertoryID = repertoryID;
	}
	
	public String getOwnerID(){
		return this.ownerID;
	}
	
	public void setOwnerID(String ownerID){
		this.ownerID = ownerID;
	}
	
	public int getMaxRow(){
		return this.maxRow;
	}
	
	public void setMaxRow(int maxRow){
		this.maxRow = maxRow;
	}
	
	public int getMaxShelf(){
		return this.maxShelf;
	}
	
	public void setMaxShelf(int maxShelf){
		this.maxShelf = maxShelf;
	}
	
	public int getMaxDigit(){
		return this.maxDigit;
	}
	
	public void setMaxDigit(int maxDigit){
		this.maxDigit = maxDigit;
	}
	
	public double getWarningRatio(){
		return this.warningRadio;
	}
	
	public void setWarningRatio(double warningRatio){
		this.warningRadio = warningRatio;
	}
	
}
