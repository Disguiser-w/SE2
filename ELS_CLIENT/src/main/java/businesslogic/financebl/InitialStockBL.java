package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import po.InitInfoPO;
import vo.InitInfoVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.controller.FinanceMainController;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import dataservice.userdataservice.UserDataService;

public class InitialStockBL{
	
	 InitialStockDataService initData;
	 UserDataService userData;
	 OrganizationDataService organizationData;
	 BusinessDataService businessData;
	 RepertoryDataService repertoryData;
	 AccountDataService accontData;
	
	/**
	 * 不过这么多东西怎么用RMI链接
	 * */
	public InitialStockBL() throws MalformedURLException, RemoteException, NotBoundException{
		initData=DataFactory.getInitialStockData();
		userData=DataFactory.getUserData();
		organizationData=DataFactory.getOrganizationData();
		businessData=DataFactory.getBusinessData();
		repertoryData=DataFactory.getRepertoryData();
		accontData=DataFactory.getAccountData();
	}

	/**
	 * 期初建账
	 * */

	public int initInfo(String time) throws RemoteException {
		// TODO Auto-generated method stub
		InitInfoPO po=new InitInfoPO(time, userData.showAllUsers(), organizationData.showAllOrganizations(), businessData.getVehicleInfos(null), repertoryData.showAllRepertorys(), accontData.showAll());
//		InitInfoPO po=FinanceMainController.ivoToPO(vo);
		return initData.initInfo(po,time);
	}

	


	
	/**
	 * 按时间查找期初信息
	 * @throws RemoteException 
	 * */
	public InitInfoVO getInitInfo(String time) throws RemoteException {
		// TODO Auto-generated method stub
		InitInfoPO initInfoPO=initData.getInitInfo(time);
		InitInfoVO initInfoVO=FinanceMainController.ipoToVO(initInfoPO);
		return initInfoVO;
	}

	/**
	 * 从持久化数据中取出所有的期初信息
	 * 显示所有期初信息
	 * @throws RemoteException 
	 * */
	public ArrayList<InitInfoVO> getAllInitInfo() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<InitInfoPO> pos=initData.getAllInitInfo();
		ArrayList<InitInfoVO> vos=FinanceMainController.iposToVOs(pos);
		return vos;
	}

//	/**
//	 * 取出各个PO中的数据并po变vo，添加到InitInfoVO中
//	 * Q：这里取PO是通过每个DataService去调吗
//	 * @throws RemoteException 
//	 * */
//	public InitInfoVO poToVO(InitInfoPO po) throws RemoteException{
//		if(po==null)
//			return null;
//		
////		ArrayList<UserPO> userPOs=po.getUserPOs();
//		ArrayList<UserPO> userPOs=userData.showAllUsers();
//		ArrayList<UserVO> userVOs;
//		if(userPOs==null){
//			userVOs=null;
//		}
//		else{
//			userVOs=new ArrayList<UserVO>();
//			for(UserPO p:userPOs){
//				UserVO vo=new UserVO(p.getName(), p.getID(), p.getPassword(), p.getProfession(), p.getOrganization(), p.getSalaryPlan(), p.getAuthority(), p.getGrades());
//				userVOs.add(vo);
//			}
//		}
//		
////		ArrayList<OrganizationPO> organizationPOs=po.getOrganizationPOs();
//		ArrayList<OrganizationPO> organizationPOs=organizationData.showAllOrganizations();
//		ArrayList<OrganizationVO> organizationVOs;
//		if(organizationPOs==null){
//			organizationVOs=null;
//		}
//		else{
//			organizationVOs=new ArrayList<OrganizationVO>();
//			for(OrganizationPO p:organizationPOs){
//				OrganizationVO vo=new OrganizationVO(p.getCategory(), p.getOrganizationID(), p.getName());
//				organizationVOs.add(vo);
//			}
//		}
//		
////		ArrayList<VehiclePO> vehiclePOs=po.getVehiclePOs();
//		ArrayList<VehiclePO> vehiclePOs=businessData.getVehicleInfos(null);
//		ArrayList<VehicleVO> vehicleVOs;
//		if(vehiclePOs==null){
//			vehicleVOs=null;
//		}
//		else{
//		vehicleVOs=new ArrayList<VehicleVO>();
//		for(VehiclePO p:vehiclePOs){
//			VehicleVO vo=new VehicleVO(p.getID(), p.getEngineNumber(), p.getLicensePlateNumber(), p.getLowNumberPlate(), p.getBuyTime(), p.getBuyTime(),IntermediateMainController.poToVO(p.getDestination()), p.getDestinationCity(),IntermediateMainController.poToVO(p.getLocal()), BusinessMainController.driverPOToVO(p.getDriver()));
//			vehicleVOs.add(vo);
//		}
//		}
//		
//	
//		
////		ArrayList<RepertoryPO> repertoryPOs=po.getRepertoryPOs();
//		ArrayList<RepertoryPO> repertoryPOs=repertoryData.showAllRepertorys();
//		ArrayList<RepertoryVO> repertoryVOs;
//		if(repertoryPOs==null){
//			repertoryVOs=null;
//		}
//		else{
//			repertoryVOs=new ArrayList<RepertoryVO>();
//			for(RepertoryPO p:repertoryPOs){
//				RepertoryVO vo=new RepertoryVO(p.getRepertoryID(), p.getOwnerID());
//				repertoryVOs.add(vo);
//			}
//		}
//		
////		ArrayList<AccountPO> accountPOs=po.getAccountPOs();
//		ArrayList<AccountPO> accountPOs=accontData.showAll();
//		ArrayList<AccountVO> accountVOs;
//		if(accountPOs==null){
//			accountVOs=null;
//		}
//		else{
//			accountVOs=new ArrayList<AccountVO>();
//			for(AccountPO p:accountPOs){
//				AccountVO vo=new AccountVO(p.getName(), p.getMoney());
//				accountVOs.add(vo);
//			}
//		}
//		InitInfoVO initInfoVO=new InitInfoVO(po.getDate(), userVOs, organizationVOs, vehicleVOs, repertoryVOs, accountVOs);
//		return initInfoVO;
//	}
//	
//	/**
//	 * pos变vos
//	 * @throws RemoteException 
//	 * */
//	public ArrayList<InitInfoVO> posToVOs(ArrayList<InitInfoPO> pos) throws RemoteException{
//		ArrayList<InitInfoVO> initInfoVOs;
//		if(pos==null){
//			initInfoVOs=null;
//		}
//		else{
//		initInfoVOs=new ArrayList<InitInfoVO>();
//		for(InitInfoPO p:pos){
//			InitInfoVO vo=poToVO(p);
//			initInfoVOs.add(vo);
//		}
//		}
//		return initInfoVOs;
//	}
//	
//	/**
//	 * 将已获取的vo变po，存入持久化变量
//	 * */
//	private InitInfoPO voToPO(InitInfoVO vo) {
//		// TODO Auto-generated method stub
//		if(vo==null)
//			return null;
//		
//		//-----------------------------------------需要的参数------------------------------------------------------------
//		ArrayList<UserVO> userVOs=vo.getUserVOs();
//		ArrayList<UserPO> userPOs;
//		if(userVOs==null){
//			userPOs=null;
//		}
//		else{
//			userPOs=new ArrayList<UserPO>();
//			for(UserVO v:userVOs){
//				UserPO po=new UserPO(v.getName(), v.getID(), v.getProfession(), v.getOrganization(), v.getSalaryPlan(), v.getAuthority(), v.getGrades());
//				userPOs.add(po);
//			}
//		}
//		
//		
//		ArrayList<OrganizationVO>organizationVOs=vo.getOrganizationVOs();
//		ArrayList<OrganizationPO> organizationPOs;
//		if(organizationVOs==null){
//			organizationPOs=null;
//		}
//		else{
//			organizationPOs=new ArrayList<OrganizationPO>();
//			for(OrganizationVO v:organizationVOs){
//				OrganizationPO po=new OrganizationPO(v.getCategory(), v.getOrganizationID(), v.getName());
//				organizationPOs.add(po);
//			}
//		}
//		
//		ArrayList<VehicleVO> vehicleVOs=vo.getVehicleVOs();
//		ArrayList<VehiclePO> vehiclePOs;
//		if(vehicleVOs==null){
//			vehiclePOs=null;
//		}
//		else{
//			vehiclePOs=new ArrayList<VehiclePO>();
//			for(VehicleVO v:vehicleVOs){
//				VehiclePO po=new VehiclePO(v.ID, v.engineNumber, v.licensePlateNumber, v.lowNumberPlate, v.buyTime, v.serviceTime, IntermediateMainController.voToPO(v.destination), v.destinationCity, IntermediateMainController.voToPO(v.local), BusinessMainController.driverVOToPO(v.driver));
//				vehiclePOs.add(po);
//			}
//		}
//		
//		ArrayList<RepertoryVO> repertoryVOs=vo.getRepertoryVOs();
//		ArrayList<RepertoryPO> repertoryPOs;
//		if(repertoryVOs==null){
//			repertoryPOs=null;
//		}
//		else{
//			repertoryPOs=new ArrayList<RepertoryPO>();
//			for(RepertoryVO v:repertoryVOs){
//				RepertoryPO po=new RepertoryPO(v.getRepertoryID(), v.getOwnerID(), v.getMaxRow(), v.getMaxShelf(), v.getMaxDigit(), v.getWarningRatio(), v.getStockNumArray());
//				repertoryPOs.add(po);
//			}
//		}
//		
//		ArrayList<AccountVO> accountVOs=vo.getAccountVOs();
//		ArrayList<AccountPO> accountPOs;
//		if(accountVOs==null){
//			accountPOs=null;
//		}
//		else{
//			accountPOs=new ArrayList<AccountPO>();
//			for(AccountVO v:accountVOs){
//				AccountPO po=new AccountPO(v.getName(), v.getMoney());
//				accountPOs.add(po);
//			}
//		}
//		
//		InitInfoPO initPO=new InitInfoPO(vo.getTime(), userPOs, organizationPOs, vehiclePOs, repertoryPOs, accountPOs);
//		return initPO;
//	}
//	
//	/**
//	 * vos to pos
//	 * */
//	public ArrayList<InitInfoPO> vosToPOs(ArrayList<InitInfoVO> vo){
//		if(vo==null){
//			return null;
//		}
//		else{
//			ArrayList<InitInfoPO> pos=new ArrayList<InitInfoPO>();
//			for(InitInfoVO v:vo){
//				InitInfoPO po=voToPO(v);
//				pos.add(po);
//		}
//			return pos;
//		}
//	
//	}

}
