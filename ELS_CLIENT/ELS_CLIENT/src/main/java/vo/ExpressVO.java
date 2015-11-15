package vo;

import java.util.ArrayList;

public class ExpressVO {
	public final String name;
	public final String ID;
	public final String serviceTime;
	public final ArrayList<String> chargeCollection;
	public final OrganizationVO organization;
	public final ArrayList<OrderVO> pendingOrders;
	public final ArrayList<OrderVO> finishedOrders;
	public final ArrayList<OrderVO> submitedOrder;

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

}