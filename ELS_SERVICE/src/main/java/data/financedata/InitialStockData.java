package data.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import File.JXCFile;
import po.AccountPO;
import po.InitInfoPO;
import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import po.VehiclePO;
import dataservice.financedataservice.InitialStockDataService;

public class InitialStockData implements InitialStockDataService{

	/**
	 * 将InitInfoPO中的信息写入InitInfo.ser
	 * 我好像把期初建账理解错了23333
	 * 是不是应该我这里添加之后，其他里面才会有，，，毕竟是期初
	 * */
	public int initInfo(InitInfoPO po, String time) throws RemoteException {
		// TODO Auto-generated method stub
		JXCFile.init(time);
		ArrayList<UserPO> userPOs=po.getUserPOs();
		ArrayList<OrganizationPO> organizationPOs=po.getOrganizationPOs();
		ArrayList<VehiclePO> vehiclePOs=po.getVehiclePOs();
		ArrayList<RepertoryPO> repertoryPOs=po.getRepertoryPOs();
		ArrayList<AccountPO> accountPOs=po.getAccountPOs();
		
		add(po);
		
		return 0;
	}

	/**
	 * PO添加进文件的操作
	 * */
	public void add(InitInfoPO po) {
		// TODO Auto-generated method stub
		JXCFile file=new JXCFile("InitInfo.ser");
		file.write(po);
	}

	/**
	 * 获取特定时间的初始化信息
	 * */
	public InitInfoPO getInitInfo(String time) throws RemoteException {
		// TODO Auto-generated method stub
		
		return null;
	}

	public ArrayList<InitInfoPO> getAllInitInfo() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<InitInfoPO> initInfoPOs=new ArrayList<InitInfoPO>();
		JXCFile file=new JXCFile("InitInfo.ser");
		ArrayList<Object> o=file.read();
		
		if(o==null){
			System.out.println("读取InitInfo.ser失败 或 为空");
			return null;
		}
		
		else{
			for(Object obj:o){
				InitInfoPO initInfoPO=(InitInfoPO) obj;
				initInfoPOs.add(initInfoPO);
			}
		}
		
		
		return null;
	}

}
