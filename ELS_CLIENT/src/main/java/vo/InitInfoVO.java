package vo;

import java.util.ArrayList;



public class InitInfoVO {
	public String time;
	public  ArrayList<AccountVO>  accoutVOs;
	public  ArrayList<OrganizationVO> organizationVOs;
	public ArrayList<VehicleVO> vehicleVOs;
	public ArrayList<RepertoryVO> repertoryVOs;
	public ArrayList<UserVO> userVOs;
	public InitInfoVO(){
	}
	
	public InitInfoVO(String time,ArrayList<UserVO> userVOs,ArrayList<OrganizationVO> organizationVOs,ArrayList<VehicleVO> vehicleVOs,ArrayList<RepertoryVO> repertoryVOs,ArrayList<AccountVO>  accountVOs){
		super();
		this.time=time;
		this.userVOs=userVOs;
		this.organizationVOs=organizationVOs;
		this.vehicleVOs=vehicleVOs;
		this.repertoryVOs=repertoryVOs;
		this.accoutVOs=accountVOs;
	}
	
//	   //人员信息
//		public ArrayList<UserVO> getUserVOs(){
//			return userVOs;
//		}
//	    //机构信息
//	public ArrayList<OrganizationVO> getOrganizationVOs(){
//		return organizationVOs;		
//	}
//	
//		//车辆信息
//	public ArrayList<VehicleVO> getVehicleVOs(){
//		return vehicleVOs;
//		}
//		//库存信息
//	public ArrayList<RepertoryVO> getRepertoryVOs(){
//		return repertoryVOs;	
//	}
//		//账户信息
//		public ArrayList<AccountVO> getAccountVOs(){
//			return accoutVOs ;
//		}
//		
//		public String getTime(){
//			return time;
//		}
	
	}
