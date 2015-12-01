package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.CheckState;

public class GatheringReceiptPO implements Serializable{
	private OrganizationPO businessShall;
	private String time;
	private ArrayList<String> expressIDs;
	private ArrayList<Double> money;
	private double totalmoney;
	private String receiptID;
	private CheckState checkState;

	public GatheringReceiptPO() {
	}

	public GatheringReceiptPO(OrganizationPO businessShall, String time,
			ArrayList<String> expressList, ArrayList<Double> money,
			double totalmoney, String receiptID) {
		this.expressIDs = expressList;
		this.money = money;
		this.businessShall = businessShall;
		this.totalmoney = totalmoney;
		this.time = time;
		this.receiptID = receiptID;
		this.checkState = CheckState.UNCHECKED;
	}

	public CheckState getCheckState() {
		return checkState;
	}

	public void setCheckState(CheckState checkState) {
		this.checkState = checkState;
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

	public ArrayList<String> getExpressIDs() {
		return expressIDs;
	}

	public void setExpressIDs(ArrayList<String> expressList) {
		this.expressIDs = expressList;
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

	public OrganizationPO getBusinessShall() {
		return businessShall;
	}

	public void setBusinessShall(OrganizationPO businessShall) {
		this.businessShall = businessShall;
	}

	public String getReceiptID() {
		return receiptID;
	}

	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}

	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}

}
