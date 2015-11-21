package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.InitInfoPO;

public interface InitialStockDataService {
	//初始化信息
	public int initInfo(InitInfoPO po,String time) throws RemoteException;
	//获得初始信息
	public InitInfoPO getInitInfo(String time) throws RemoteException;
	//获取所有初始信息
	public ArrayList<InitInfoPO> getAllInitInfo();
}
