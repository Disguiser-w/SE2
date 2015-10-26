package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransferingReceiptVO {
    String ID;
    String time;
    ArrayList<OrderVO> orderList;
    OrganizationVO interdiatehall;
    
    public TransferingReceiptVO(){
    }
    
    public TransferingReceiptVO(ArrayList<OrderVO> orderList){
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

	public ArrayList<OrderVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderVO> orderList) {
		this.orderList = orderList;
	}

	public OrganizationVO getInterdiatehall() {
		return interdiatehall;
	}

	public void setInterdiatehall(OrganizationVO interdiatehall) {
		this.interdiatehall = interdiatehall;
	}
}
