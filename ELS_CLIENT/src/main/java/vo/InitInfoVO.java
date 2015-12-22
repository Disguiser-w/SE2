package vo;

import java.util.ArrayList;



public class InitInfoVO {
	public String time;
	public  ArrayList<AccountVO>  accoutVOs;
	public  ArrayList<OrganizationVO> organizationVOs;
	public ArrayList<VehicleVO> vehicleVOs;
	public ArrayList<RepertoryVO> repertoryVOs;
	public ArrayList<UserVO> userVOs;
	public String userID;
	public InitInfoVO(){
	}
	
	public InitInfoVO(String time,String userID,ArrayList<UserVO> userVOs,ArrayList<OrganizationVO> organizationVOs,ArrayList<VehicleVO> vehicleVOs,
			ArrayList<RepertoryVO> repertoryVOs,ArrayList<AccountVO>  accountVOs){
		super();
		this.time=time;
		this.userID=userID;
		this.userVOs=userVOs;
		this.organizationVOs=organizationVOs;
		this.vehicleVOs=vehicleVOs;
		this.repertoryVOs=repertoryVOs;
		this.accoutVOs=accountVOs;
	}
	

	
	}
