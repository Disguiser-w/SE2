package vo;

import java.util.ArrayList;

public class ExpressVO {
	public final String name;
	public final String ID;
	public final String serviceTime;
	public final ArrayList<String> chargeCollection;
	public final OrganizationVO organization;
	
	public final ArrayList<String> pendingOrders;
	public final ArrayList<String> finishedOrders;
	public final ArrayList<String> submitedOrderID;

	public ExpressVO(String name, String ID, String serviceTime, ArrayList<String> chargeCollection,
			OrganizationVO organization, ArrayList<String> pendingOrders, ArrayList<String> finishedOrders,
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

}