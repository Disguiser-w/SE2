package data.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.InitInfoPO;
import dataservice.financedataservice.InitialStockDataService;
import file.JXCFile;

public class InitialStockData extends UnicastRemoteObject implements InitialStockDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JXCFile file;
	public InitialStockData() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		file=new JXCFile("initial.ser");
	}

	/**
	 * 将InitInfoPO中的信息写入InitInfo.ser
	 * 我好像把期初建账理解错了23333
	 * 是不是应该我这里添加之后，其他里面才会有，，，毕竟是期初
	 * */
	public int initInfo(InitInfoPO po, String time) throws RemoteException {
		// TODO Auto-generated method stub
		file.write(po);
		return 0;
	}


	/**
	 * 获取特定时间的初始化信息
	 * */
	public InitInfoPO getInitInfo(String time) throws RemoteException {
		// TODO Auto-generated method stub
		 file=new JXCFile("initial.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取InitInfo.ser失败或为空");
			return null;
		}
		else{
			for(Object ob:os){
				InitInfoPO p=(InitInfoPO) ob;
				if(p.getDate().equals(time)){
					return p;
				}
			}
		}
		return null;
	}

	public ArrayList<InitInfoPO> getAllInitInfo() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<InitInfoPO> initInfoPOs=new ArrayList<InitInfoPO>();
		JXCFile file=new JXCFile("initial.ser");
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
		return initInfoPOs;
	}
	
	public static void main(String[] args){
		try {
			InitialStockData data=new InitialStockData();
//			ArrayList<UserPO> userPOs=new ArrayList<UserPO>();
//			ArrayList<OrganizationPO> organizationPOs=new ArrayList<OrganizationPO>();
//			ArrayList<VehiclePO> vehiclePOs=new ArrayList<VehiclePO>();
//			ArrayList<RepertoryPO> repertoryPOs=new ArrayList<RepertoryPO>();
//			ArrayList<AccountPO> accountPOs=new ArrayList<AccountPO>();
//
//			UserPO user=new UserPO("ben", "CW", ProfessionType.financialStaff, "总部",SalaryPlanType.basicStaffSalaryPlan	, AuthorityType.commonFianacialStaff,0);
//			OrganizationPO org=new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼");
//			VehiclePO vehicle=new VehiclePO("00001", "2014", "2014", "20", "2013", "3", null, "南京", null, null);
//			RepertoryPO repertory=new RepertoryPO("0251", "SJ");
//			AccountPO account=new AccountPO("CW", 100);
//			userPOs.add(user);
//			organizationPOs.add(org);
//			vehiclePOs.add(vehicle);
//			repertoryPOs.add(repertory);
//			accountPOs.add(account);
//			
//			InitInfoPO po=new InitInfoPO("2015-12-11", userPOs, organizationPOs, vehiclePOs, repertoryPOs, accountPOs);
//			data.initInfo(po, "2015-12-11");
//			System.out.println(data.getInitInfo("2015-12-11").getAccountPOs());
			InitInfoPO po=data.getInitInfo("2015-12-11");
			System.out.println(po.getUserPOs().get(0).getName());
			System.out.println(po.getOrganizationPOs().get(0).getName());
			System.out.println(po.getVehiclePOs().get(0).getID());
			System.out.println(po.getVehiclePOs().get(0).getID());
			System.out.println(po.getAccountPOs().get(0).getName());
					} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
