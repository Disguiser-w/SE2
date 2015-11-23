package po;

import java.util.ArrayList;

public class InitInfoPO {

	// 机构信息
	// 人员信息
	// 车辆信息
	// 库存信息
	// 账户信息
	ArrayList<AccountPO> accout;
	String date;
	private ArrayList<AccountPO>  accoutPOs;
	private ArrayList<OrganizationPO> organizationPOs;
	private ArrayList<VehiclePO> vehiclePOs;
	private ArrayList<RepertoryPO> repertoryPOs;
	private ArrayList<UserPO> userPOs;
	public InitInfoPO(){
	}
	
	public InitInfoPO(String date,ArrayList<UserPO> userPOs,ArrayList<OrganizationPO> organizationPOs,ArrayList<VehiclePO> vehiclePOs,ArrayList<RepertoryPO> repertoryPOs,ArrayList<AccountPO>  accoutPOs){
		super();
		this.date=date;
		this.userPOs=userPOs;
		this.organizationPOs=organizationPOs;
		this.vehiclePOs=vehiclePOs;
		this.repertoryPOs=repertoryPOs;
	}
	
	   //人员信息
		public ArrayList<UserPO> getUserPOs(){
			return userPOs;
		}
	    //机构信息
	public ArrayList<OrganizationPO> getOrganizationPOs(){
		return organizationPOs;		
	}
	
		//车辆信息
	public ArrayList<VehiclePO> getVehiclePOs(){
		return vehiclePOs;
		}
		//库存信息
	public ArrayList<RepertoryPO> getRepertoryPOs(){
		return repertoryPOs;	
	}
		//账户信息
		public ArrayList<AccountPO> getAccountPOs(){
			return accoutPOs ;
		}
		
		public String getDate(){
			return date;
		}
		
	public ArrayList<AccountPO> getAccount() {
		return accout;
	}
}
