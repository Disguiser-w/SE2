package data.financedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.FileGetter;
import po.AccountPO;
import po.DriverPO;
import po.ExpressPO;
import po.InitInfoPO;
import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import po.VehiclePO;
import type.AuthorityType;
import type.OrganizationType;
import type.ProfessionType;
import type.SalaryPlanType;
import type.Sexuality;
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
//		file=new JXCFile("initial.ser");
	}

	/**
	 * 将InitInfoPO中的信息写入InitInfo.ser
	 * 我好像把期初建账理解错了23333
	 * 是不是应该我这里添加之后，其他里面才会有，，，毕竟是期初
	 * */
	public int initInfo(InitInfoPO po, String time) throws RemoteException {
		// TODO Auto-generated method stub
		String path = "info/"+"initInfo/"+time+"-initInfo.ser";
		JXCFile file = new JXCFile(path);
		file.write(po);
		return 0;
	}


	/**
	 * 获取特定时间的初始化信息
	 * */
	public InitInfoPO getInitInfo(String time) throws RemoteException {
		// TODO Auto-generated method stub
//		 file=new JXCFile("initial.ser");
//		ArrayList<Object> os=file.read();
//		if(os==null){
//			System.out.println("读取InitInfo.ser失败或为空");
//			return null;
//		}
//		else{
//			for(Object ob:os){
//				InitInfoPO p=(InitInfoPO) ob;
//				if(p.getDate().equals(time)){
//					return p;
//				}
//			}
//		}
//		return null;
		String path ="initInfo/"+time+"-initInfo.ser";
		File file = FileGetter.getFile(path);
		try{
			if(!file.exists()){
				System.out.println("file is not exsit");
				return null;
			}
			else{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				InitInfoPO initInfoPO = (InitInfoPO) in.readObject();
				in.close();
				return initInfoPO;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("快递员信息读写失败");
			return null;
		}
		
	}

	
	/**
	 * 改：存入一个文件中读取时总是有错误
	 *         改为存到按时间的各个文件中
	 * */
	public ArrayList<InitInfoPO> getAllInitInfo() throws RemoteException {
		// TODO Auto-generated method stub
//		try {
//			ObjectInputStream in = new ObjectInputStream(new FileInputStream("initial.ser"));
//			ArrayList<InitInfoPO> pos = (ArrayList<InitInfoPO>) in.readObject();
//			in.close();
//			return pos;
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
		
		
//		JXCFile file=new JXCFile("initial.ser");
//		ArrayList<Object> o=file.read();
//	
//		if(o==null){
//			System.out.println("读取InitInfo.ser失败 或 为空");
//			return null;
//		}
//		ArrayList<InitInfoPO> initInfoPOs=new ArrayList<InitInfoPO>();	
//			for(Object obj:o){
//				InitInfoPO initInfoPO=(InitInfoPO) obj;
//				initInfoPOs.add(initInfoPO);
//			
//		}		
//		return initInfoPOs;
		
		File dir =FileGetter.getFile("initInfo");
		File[] files = dir.listFiles();
		if(files.length == 0){
			return null;
		}
		else{
			ArrayList<InitInfoPO> initInfoPOs = new ArrayList<InitInfoPO>();
		for(File i:files){
			try{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(i));
				InitInfoPO initInfoPO = (InitInfoPO) in.readObject();
				in.close();
				initInfoPOs.add(initInfoPO);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("期初信息读取失败");
			}
		}
		return initInfoPOs;
		}
		
	}
	
	public static void main(String[] args){
		try {
			InitialStockData data=new InitialStockData();
			ArrayList<UserPO> userPOs=new ArrayList<UserPO>();
			ArrayList<OrganizationPO> organizationPOs=new ArrayList<OrganizationPO>();
			ArrayList<VehiclePO> vehiclePOs=new ArrayList<VehiclePO>();
			ArrayList<RepertoryPO> repertoryPOs=new ArrayList<RepertoryPO>();
			ArrayList<AccountPO> accountPOs=new ArrayList<AccountPO>();

			UserPO user=new UserPO("ben", "CW-00001", ProfessionType.financialStaff, "总部",SalaryPlanType.basicStaffSalaryPlan	, AuthorityType.commonFianacialStaff,0);
			OrganizationPO org=new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼");
			VehiclePO vehicle=new VehiclePO("SJ", "10", "320", "10", "2011", "3", org, "鼓楼", org, new DriverPO("SJ", "haha", "1988", "32", "32", org, Sexuality.FEMALE, "hh", 0));
			RepertoryPO repertory=new RepertoryPO("0251", "SJ");
			AccountPO account=new AccountPO("CW", 100);
			userPOs.add(user);
			organizationPOs.add(org);
			vehiclePOs.add(vehicle);
			repertoryPOs.add(repertory);
			accountPOs.add(account);
			
			InitInfoPO po=new InitInfoPO("20151214", userPOs, organizationPOs, vehiclePOs, repertoryPOs, accountPOs);
			data.initInfo(po, "20151214");
//			InitInfoPO po1=data.getInitInfo("20151214");
//			ArrayList<InitInfoPO> infoPOs = data.getAllInitInfo();
//			System.out.println(infoPOs.get(1).getDate());
//			
// 			System.out.println(po1.getUserPOs().get(0).getName());
//			System.out.println(po1.getOrganizationPOs().get(0).getName());
//			System.out.println(po1.getVehiclePOs().get(0).getID());
//			System.out.println(po1.getVehiclePOs().get(0).getID());
//			System.out.println(po1.getAccountPOs().get(0).getName());
//			ArrayList<InitInfoPO> pos=data.getAllInitInfo();
//			System.out.println(pos.size());
					} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
