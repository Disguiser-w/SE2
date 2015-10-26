package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GatheringReceiptPO {
	 OrganizationPO businesshall;
	    String time;
	    ArrayList<ExpressPO> expressList;
	    ArrayList<Double> money;
	    double totalmoney;
	    
	    public GatheringReceiptPO(){
	    }
	    
	    public GatheringReceiptPO(ArrayList<ExpressPO> expressList, ArrayList<Double> money){
	    	this.expressList = expressList;
	    	this.money = money;
	    	Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.time = format.format(date);
	    }

		public OrganizationPO getBusinesshall() {
			return businesshall;
		}

		public void setBusinesshall(OrganizationPO businesshall) {
			this.businesshall = businesshall;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public ArrayList<ExpressPO> getExpressList() {
			return expressList;
		}

		public void setExpressList(ArrayList<ExpressPO> expressList) {
			this.expressList = expressList;
		}

		public ArrayList<Double> getMoney() {
			return money;
		}

		public void setMoney(ArrayList<Double> money) {
			this.money = money;
		}

		public double getTotalmoney() {
			return totalmoney;
		}

		public void setTotalmoney(double totalmoney) {
			this.totalmoney = totalmoney;
		}
	    
	    
}
