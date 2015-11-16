package businesslogic.businessbl;

import java.util.ArrayList;

import businesslogicservice.businessblservice.DistributeOrderBLService;
import dataservice.businessdataservice.BusinessDataService;
import vo.ExpressVO;
import vo.OrderVO;

public class DistributeOrder{
	private BusinessDataService businessData;
	
	public DistributeOrder(){
		
	}
	
	public String distributeOrder() {
		// TODO Auto-generated method stub
		return "狗剩 2015-11-11";
	}

	public ArrayList<ExpressVO> getExpressInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<OrderVO> getSendOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
