package vo;

import java.util.ArrayList;

import po.OrganizationPO;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class ExpressVO extends UserVO {
	public final String serviceTime;
	public final ArrayList<String> chargeCollection;
	public final OrganizationVO organizationVO;

	public final ArrayList<String> pendingOrders;
	public final ArrayList<String> finishedOrders;
	public final ArrayList<String> submitedOrderID;

	public ExpressVO(String name, String ID, String password, ProfessionType professionType, String organizationID,
			SalaryPlanType salaryPlanType, AuthorityType authority, int grade, String serviceTime,
			ArrayList<String> chargeCollection, OrganizationVO organizationVO, ArrayList<String> pendingOrders,
			ArrayList<String> finishedOrders, ArrayList<String> submitedOrderID) {
		super(name, ID, password, professionType, organizationID, salaryPlanType, authority, grade);
		this.serviceTime = serviceTime;
		this.chargeCollection = chargeCollection;
		if (chargeCollection.isEmpty())
			chargeCollection.add("0.0");
		this.organizationVO = organizationVO;
		this.pendingOrders = pendingOrders;
		this.finishedOrders = finishedOrders;
		this.submitedOrderID = submitedOrderID;
	}

}