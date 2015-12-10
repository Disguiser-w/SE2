package dataservice.financedataservice;

import java.util.ArrayList;

import po.InitInfoPO;

public class InitialStockDataService_stub implements InitialStockDataService{

	public int initInfo(InitInfoPO po,String time) {
		// TODO Auto-generated method stub
		System.out.println("Init Info successfully!");
		return 0;
	}

	public InitInfoPO getInitInfo(String time) {
		// TODO Auto-generated method stub
		System.out.println("Show InitInfo successfully!");
		return null;
	}

	public ArrayList<InitInfoPO> getAllInitInfo() {
		// TODO Auto-generated method stub
		System.out.println("Show all InitInfo successfully!");
		return null;
	}

}
