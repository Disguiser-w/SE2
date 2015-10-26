package po;

import java.util.ArrayList;

public class ExpressPO {
	private String name;
	private String ID;
	private String serviceTime;
	private ArrayList<String> chargeCollection;
	private OrganizationPO organization;
	private ArrayList<OrderPO> pendingOrders;
	private ArrayList<OrderPO> finishedOrders;
	private ArrayList<OrderPO> submitedOrder;

	private ExpressPO() {
	}

	public ExpressPO(String name, String ID, String serviceTime, ArrayList<String> chargeCollection,
			OrganizationPO organization, ArrayList<OrderPO> pendingOrders, ArrayList<OrderPO> finishedOrders,
			ArrayList<OrderPO> submitedOrder) {
		super();
		this.name = name;
		this.ID = ID;
		this.serviceTime = serviceTime;
		this.chargeCollection = chargeCollection;
		this.organization = organization;
		this.pendingOrders = pendingOrders;
		this.finishedOrders = finishedOrders;
		this.submitedOrder = submitedOrder;
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

	public ArrayList<OrderPO> getPendingOrders() {
		return pendingOrders;
	}

	public void setPendingOrders(ArrayList<OrderPO> pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

	public ArrayList<OrderPO> getFinishedOrders() {
		return finishedOrders;
	}

	public void setFinishedOrders(ArrayList<OrderPO> finishedOrders) {
		this.finishedOrders = finishedOrders;
	}

	public ArrayList<OrderPO> getSubmitedOrder() {
		return submitedOrder;
	}

	public void setSubmitedOrder(ArrayList<OrderPO> submitedOrder) {
		this.submitedOrder = submitedOrder;
	}

}