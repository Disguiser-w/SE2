package po;

import java.io.Serializable;
import java.util.ArrayList;

public class ExpressPO implements Serializable{
	private String name;
	private String ID;
	private String serviceTime;
	private ArrayList<String> chargeCollection;
	private OrganizationPO organization;
	private ArrayList<String> pendingOrders;
	private ArrayList<String> finishedOrders;
	private ArrayList<String> submitedOrderID;

	public ExpressPO() {
		
	}

	public ExpressPO(String name, String ID, String serviceTime,
			ArrayList<String> chargeCollection, OrganizationPO organization,
			ArrayList<String> pendingOrders, ArrayList<String> finishedOrders,
			ArrayList<String> submitedOrderID) {
		super();
		this.name = name;
		this.ID = ID;
		this.serviceTime = serviceTime;
		this.chargeCollection = chargeCollection;
		this.organization = organization;
		this.pendingOrders = pendingOrders;
		this.finishedOrders = finishedOrders;
		this.submitedOrderID = submitedOrderID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
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

	public OrganizationPO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationPO organization) {
		this.organization = organization;
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