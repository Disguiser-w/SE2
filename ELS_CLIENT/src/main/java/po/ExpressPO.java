package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class ExpressPO extends UserPO implements Serializable {
	private String serviceTime;
	private ArrayList<String> chargeCollection;
	private OrganizationPO organizationPO;
	private ArrayList<String> pendingOrders;
	private ArrayList<String> finishedOrders;
	private ArrayList<String> submitedOrderID;

	public ExpressPO(String name, String ID, String password, ProfessionType professionType, String organizationID,
			SalaryPlanType salaryPlanType, AuthorityType authority, int grade, String serviceTime,
			ArrayList<String> chargeCollection, OrganizationPO organizationPO, ArrayList<String> pendingOrders,
			ArrayList<String> finishedOrders, ArrayList<String> submitedOrderID) {
		super(name, ID, password, professionType, organizationID, salaryPlanType, authority, grade);
		this.serviceTime = serviceTime;
		this.chargeCollection = chargeCollection;
		if (chargeCollection.isEmpty())
			chargeCollection.add("0.0");
		this.organizationPO = organizationPO;
		this.pendingOrders = pendingOrders;
		this.finishedOrders = finishedOrders;
		this.submitedOrderID = submitedOrderID;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public ArrayList<String> getChargeCollection() {
		return chargeCollection;
	}

	public void setChargeCollection(ArrayList<String> chargeCollection) {
		this.chargeCollection = chargeCollection;
	}

	public OrganizationPO getOrganizationPO() {
		return organizationPO;
	}

	public void setOrganization(OrganizationPO organizationPO) {
		this.organizationPO = organizationPO;
	}

	public ArrayList<String> getPendingOrders() {
		return pendingOrders;
	}

	public void setPendingOrders(ArrayList<String> pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

	public ArrayList<String> getFinishedOrders() {
		return finishedOrders;
	}

	public void setFinishedOrders(ArrayList<String> finishedOrders) {
		this.finishedOrders = finishedOrders;
	}

	public ArrayList<String> getSubmitedOrderID() {
		return submitedOrderID;
	}

	public void setSubmitedOrderID(ArrayList<String> submitedOrderID) {
		this.submitedOrderID = submitedOrderID;
	}

}