package vo;

import java.util.ArrayList;

public class InitInfoVO {
	//机构信息
	//人员信息
	//车辆信息
	//库存信息
	//账户信息
	ArrayList<AccountVO>  accout;
	
	public InitInfoVO(){
	}
	public ArrayList<AccountVO> getAccount(){
		return accout;
	}
	}
