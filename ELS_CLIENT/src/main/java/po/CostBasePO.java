package po;

/**
 * @author danian
 *
 */
public class CostBasePO {
	private int[] proportion;
	private int unitPrice;
	private int distanceBetweenBusinessHalls;
	private String[] place;
	private int[][] distanceBetweenCountrys;

	public CostBasePO() {
	}

	public CostBasePO(int[] proportion, int unitPrice, int distanceBetweenBusinessHalls, String[] place,
			int[][] distanceBetweenCountrys) {
		this.proportion = proportion;
		this.distanceBetweenBusinessHalls = distanceBetweenBusinessHalls;
		this.place = place;
		this.distanceBetweenCountrys = distanceBetweenCountrys;
	}

	public int[] getProportion() {
		return proportion;
	}

	public void setProportion(int[] proportion) {
		this.proportion = proportion;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getDistanceBetweenBusinessHalls() {
		return distanceBetweenBusinessHalls;
	}

	public void setDistanceBetweenBusinessHalls(int distanceBetweenBusinessHalls) {
		this.distanceBetweenBusinessHalls = distanceBetweenBusinessHalls;
	}

	public String[] getPlace() {
		return place;
	}

	public void setPlace(String[] place) {
		this.place = place;
	}

	public int[][] getDistanceBetweenCountrys() {
		return distanceBetweenCountrys;
	}

	public void setDistanceBetweenCountrys(int[][] distanceBetweenCountrys) {
		this.distanceBetweenCountrys = distanceBetweenCountrys;
	}

}
