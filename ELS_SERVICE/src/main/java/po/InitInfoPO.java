package po;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class InitInfoPO extends UnicastRemoteObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private ArrayList<AccountPO>  accoutPOs;
	private ArrayList<OrganizationPO> organizationPOs;
	private ArrayList<VehiclePO> vehiclePOs;
	private ArrayList<RepertoryPO> repertoryPOs;
	private ArrayList<UserPO> userPOs;
	
	
	public InitInfoPO(String date,ArrayList<UserPO> userPOs,ArrayList<OrganizationPO> organizationPOs,ArrayList<VehiclePO> vehiclePOs,ArrayList<RepertoryPO> repertoryPOs,ArrayList<AccountPO>  accoutPOs) throws RemoteException{
		this.date=date;
		this.userPOs=userPOs;
		this.organizationPOs=organizationPOs;
		this.vehiclePOs=vehiclePOs;
		this.repertoryPOs=repertoryPOs;
		this.accoutPOs=accoutPOs;
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
}
