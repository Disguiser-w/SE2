package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GatheringReceiptPO {
	OrganizationPO businessShall;
	String time;
	ArrayList<ExpressPO> expressList;
	ArrayList<Double> money;
	double totalmoney;

	public GatheringReceiptPO() {
	}

	public GatheringReceiptPO(OrganizationPO businessShall, String time, ArrayList<ExpressPO> expressList,
			ArrayList<Double> money, double totalmoney) {
		this.expressList = expressList;
		this.money = money;
		this.businessShall = businessShall;
		this.totalmoney = totalmoney;
		this.time = time;
	}

	public OrganizationPO getBusinesShall() {
		return businessShall;
	}

	public void setBusinesshall(OrganizationPO businesShall) {
		this.businessShall = businesShall;
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
