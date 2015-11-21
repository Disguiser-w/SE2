package businesslogic.financebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.financedataservice.InitialStockDataService;
import businesslogicservice.financeblservice.InitialStockBLService;
import po.AccountPO;
import po.InitInfoPO;
import po.OrganizationPO;
import po.UserPO;
import po.VehiclePO;
import vo.AccountVO;
import vo.InitInfoVO;
import vo.InventoryVO;
import vo.OrganizationVO;
import vo.UserVO;
import vo.VehicleVO;

public class InitialStockController implements InitialStockBLService{
	
	private InitialStockDataService initData;
	
	public InitialStockController(){
		
	}

	/**
	 * 期初建账
	 * @author wll
	 * @version Nov,20,2015 21:12
	 * @throws RemoteException 
	 * */

	@Override
	public int initInfo(InitInfoVO vo,String time) throws RemoteException {
		// TODO Auto-generated method stub
		InitInfoPO po=voToPO(vo);
		return initData.initInfo(po,time);
	}

	/**
	 * vo变po，存入持久化变量
	 * */
	private InitInfoPO voToPO(InitInfoVO vo) {
		// TODO Auto-generated method stub
		if(vo==null)
			return null;
		
		//-----------------------------------------需要的参数------------------------------------------------------------
		ArrayList<UserVO> userVOs=vo.getUserVOs();
		ArrayList<UserPO> userPOs;
		if(userVOs==null){
			userPOs=null;
		}
		else{
			userPOs=new ArrayList<UserPO>();
			for(UserVO v:userVOs){
				UserPO po=new UserPO(v.getName(), v.getID(), v.getPassword(), v.getProfession(), v.getOrganization(), v.getSalaryPlan(), v.getAuthority(), v.getGrades());
				userPOs.add(po);
			}
		}
		
		
		ArrayList<OrganizationVO>organizationVOs=vo.getOrganizationVOs();
		ArrayList<OrganizationPO> organizationPOs;
		if(organizationVOs==null){
			organizationPOs=null;
		}
		else{
			organizationPOs=new ArrayList<OrganizationPO>();
			for(OrganizationVO v:organizationVOs){
				OrganizationPO po=new OrganizationPO(v.getCategory(), v.getOrganizationID(), v.getName(), v.getRepertory());
				organizationPOs.add(po);
			}
		}
		
		ArrayList<VehicleVO> vehicleVOs=vo.getVehicleVOs();
		ArrayList<VehiclePO> vehiclePOs;
		if(vehicleVOs==null){
			vehiclePOs=null;
		}
		else{
			vehiclePOs=new ArrayList<VehiclePO>();
			for(VehicleVO v:vehicleVOs){
				VehiclePO po=new VehiclePO(ID, engineNumber, licensePlateNumber, lowNumberPlate, buyTime, serviceTime);
				vehiclePOs.add(po);
			}
		}
		
		ArrayList<InventoryVO>  inventoryVOs=vo.getInventoryVOs();
		ArrayList<InventoryPO> inventoryPOs;
		if(inventoryVOs==null){
			inventoryPOs=null;
		}
		else{
			inventoryPOs=new ArrayList<InventoryPO>();
			for(InventoryVO v:inventoryVOs){
				InventoryPO po=new InventoryPO(v.getID());
				inventoryPOs.add(po);
			}
		}
		
		ArrayList<AccountVO> accountVOs=vo.getAccountVOs();
		ArrayList<AccountPO> accountPOs;
		if(accountVOs==null){
			accountPOs=null;
		}
		else{
			accountPOs=new ArrayList<AccountPO>();
			for(AccountVO v:accountVOs){
				AccountPO po=new AccountPO(v.getName(), v.getMoney());
				accountPOs.add(po);
			}
		}
		
		InitInfoPO initPO=new InitInfoPO(vo.getTime(), userPOs, organizationPOs, vehiclePOs, inventoryPOs, accountPOs);
		return initPO;
	}
	
	/**
	 * vos to pos
	 * */
	public ArrayList<InitInfoPO> vosToPOs(ArrayList<InitInfoVO> vo){
		if(vo==null){
			return null;
		}
		else{
			ArrayList<InitInfoPO> pos=new ArrayList<InitInfoPO>();
			for(InitInfoVO v:vo){
				InitInfoPO po=voToPO(v);
				pos.add(po);
		}
			return pos;
		}
	
	}
	
	
	/**
	 * po变vo,转化为显示
	 * */
	public InitInfoVO poToVO(InitInfoPO po){
		if(po==null)
			return null;
		
		ArrayList<UserPO> userPOs=po.getUserPOs();
		ArrayList<UserVO> userVOs;
		if(userPOs==null){
			userVOs=null;
		}
		else{
			userVOs=new ArrayList<UserVO>();
			for(UserPO p:userPOs){
				UserVO vo=new UserVO(p.getName(), p.getID(), p.getPassword(), p.getProfession(), p.getOrganization(), p.getSalaryPlan(), p.getAuthority(), p.getGrades());
				userVOs.add(vo);
			}
		}
		
		ArrayList<OrganizationPO> organizationPOs=po.getOrganizationPOs();
		ArrayList<OrganizationVO> organizationVOs;
		if(organizationPOs==null){
			organizationVOs=null;
		}
		else{
			organizationVOs=new ArrayList<OrganizationVO>();
			for(OrganizationPO p:organizationPOs){
				OrganizationVO vo=new OrganizationVO(p.getCategory(), p.getOrganizationID(), p.getName(), p.getRepertory());
				organizationVOs.add(vo);
			}
		}
		
		ArrayList<VehiclePO> vehiclePOs=po.getVehiclePOs();
		ArrayList<VehicleVO> vehicleVOs;
		if(vehiclePOs==null){
			vehicleVOs=null;
		}
		else{
		vehicleVOs=new ArrayList<VehicleVO>();
		for(VehiclePO p:vehiclePOs){
			VehicleVO vo=new VehicleVO(ID, engineNumber, licensePlateNumber, lowNumberPlate, buyTime, serviceTime);
			vehicleVOs.add(vo);
		}
		}
		
		ArrayList<InventoryPO> inventoryPOs=po.getInventoryPOs();
		ArrayList<InventoryVO> inventoryVOs;
		if(inventoryPOs==null){
			inventoryVOs=null;
		}
		else{
			inventoryVOs=new ArrayList<InventoryVO>();
			for(InventoryPO p:inventoryPOs){
				InventoryVO vo=new InventoryVO(p.getID());
				inventoryVOs.add(vo);
			}
		}
		
		ArrayList<AccountPO> accountPOs=po.getAccountPOs();
		ArrayList<AccountVO> accountVOs;
		if(accountPOs==null){
			accountVOs=null;
		}
		else{
			accountVOs=new ArrayList<AccountVO>();
			for(AccountPO p:accountPOs){
				AccountVO vo=new AccountVO(p.getName(), p.getMoney());
				accountVOs.add(vo);
			}
		}
		InitInfoVO initInfoVO=new InitInfoVO(po.getDate(), userVOs, organizationVOs, vehicleVOs, inventoryVOs, accountVOs);
		return initInfoVO;
	}
	
	/**
	 * pos变vos
	 * */
	public ArrayList<InitInfoVO> posToVOs(ArrayList<InitInfoPO> pos){
		ArrayList<InitInfoVO> initInfoVOs;
		if(pos==null){
			initInfoVOs=null;
		}
		else{
		initInfoVOs=new ArrayList<InitInfoVO>();
		for(InitInfoPO p:pos){
			InitInfoVO vo=poToVO(p);
			initInfoVOs.add(vo);
		}
		}
		return initInfoVOs;
	}

	
	/**
	 * 按时间查找期初信息
	 * @throws RemoteException 
	 * */
	@Override
	public InitInfoVO getInitInfo(String time) throws RemoteException {
		// TODO Auto-generated method stub
		InitInfoPO initInfoPO=initData.getInitInfo(time);
		InitInfoVO initInfoVO=poToVO(initInfoPO);
		return initInfoVO;
	}

	/**
	 * 从持久化数据中取出所有的期初信息
	 * 显示所有期初信息
	 * */
	@Override
	public ArrayList<InitInfoVO> getAllInitInfo() {
		// TODO Auto-generated method stub
		ArrayList<InitInfoPO> pos=initData.getAllInitInfo();
		ArrayList<InitInfoVO> vos=posToVOs(pos);
		return vos;
	}

	
	
	
	

}
