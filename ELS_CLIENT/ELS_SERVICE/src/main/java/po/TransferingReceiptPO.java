package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransferingReceiptPO {
	 String ID;
	    String time;
	    ArrayList<OrderPO> orderList;
	    OrganizationPO interdiatehall;
	    
	    public TransferingReceiptPO(){
	    }
	    
	    public TransferingReceiptPO(ArrayList<OrderPO> orderList){
	    	Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.time = format.format(date);
			this.orderList = orderList;
	    }

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public ArrayList<OrderPO> getOrderList() {
			return orderList;
		}

		public void setOrderList(ArrayList<OrderPO> orderList) {
			this.orderList = orderList;
		}

		public OrganizationPO getInterdiatehall() {
			return interdiatehall;
		}

		public void setInterdiatehall(OrganizationPO interdiatehall) {
			this.interdiatehall = interdiatehall;
		}
}
