package vo;

import java.util.ArrayList;

public class ExpressVO {
	private String name;
	private String ID;
	private String serviceTime;
	private ArrayList<String> chargeCollection;
	private OrganizationVO organization;
	private ArrayList<OrderVO> pendingOrders;
	private ArrayList<OrderVO> finishedOrders;
	private ArrayList<OrderVO> submitedOrder;
	private ExpressVO(){}
	public ExpressVO(String name, String ID, String serviceTime, ArrayList<String> chargeCollection,
			OrganizationVO organization, ArrayList<OrderVO> pendingOrders, ArrayList<OrderVO> finishedOrders,
			ArrayList<OrderVO> submitedOrder) {
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
	public void setID(String iD) {
		ID = iD;
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
	public OrganizationVO getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationVO organization) {
		this.organization = organization;
	}
	public ArrayList<OrderVO> getPendingOrders() {
		return pendingOrders;
	}
	public void setPendingOrders(ArrayList<OrderVO> pendingOrders) {
		this.pendingOrders = pendingOrders;
	}
	public ArrayList<OrderVO> getFinishedOrders() {
		return finishedOrders;
	}
	public void setFinishedOrders(ArrayList<OrderVO> finishedOrders) {
		this.finishedOrders = finishedOrders;
	}
	public ArrayList<OrderVO> getSubmitedOrder() {
		return submitedOrder;
	}
	public void setSubmitedOrder(ArrayList<OrderVO> submitedOrder) {
		this.submitedOrder = submitedOrder;
	}
	

}