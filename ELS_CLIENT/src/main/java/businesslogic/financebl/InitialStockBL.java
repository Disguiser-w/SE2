package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.InitInfoPO;
import po.VehiclePO;
import vo.InitInfoVO;
import vo.VehicleVO;
import businesslogic.businessbl.controller.BusinessMainController;
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
	public InitialStockBL(){
		try {
			initData=DataFactory.getInitialStockData();
			userData=DataFactory.getUserData();
			organizationData=DataFactory.getOrganizationData();
			businessData=DataFactory.getBusinessData();
			repertoryData=DataFactory.getRepertoryData();
			accontData=DataFactory.getAccountData();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 期初建账
	 * */

	public int initInfo(InitInfoVO vo,String time)  {
		// TODO Auto-generated method stub
		try {
			InitInfoPO po=FinanceMainController.ivoToPO(vo);
			//test
			return initData.initInfo(po,time);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加期初信息异常");
			return -1;
		}
	}

	/**
	 * 按时间查找期初信息
	 * @throws RemoteException 
	 * */
	public InitInfoVO getInitInfo(String time) {
		// TODO Auto-generated method stub
		InitInfoPO initInfoPO;
		try {
			initInfoPO = initData.getInitInfo(time);
			InitInfoVO initInfoVO=FinanceMainController.ipoToVO(initInfoPO);
			return initInfoVO;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("不存在这个时间的套账！！！");
			return null;
		}
	}

	/**
	 * 从持久化数据中取出所有的期初信息
	 * 显示所有期初信息
	 * @throws RemoteException 
	 * */
	public ArrayList<InitInfoVO> getAllInitInfo()  {
		// TODO Auto-generated method stub
		
		try {
			if(initData.getAllInitInfo()!=null){
			return FinanceMainController.iposToVOs(initData.getAllInitInfo());
			}
			else{
				return null;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("不存在任何套账");
			return null;
		}
	}
	
	
	public ArrayList<VehicleVO> getVehicleInfo(){
		ArrayList<VehiclePO> pos;
		try {
			pos = businessData.getVehicleInfos(null);
			ArrayList<VehicleVO> vos = new ArrayList<VehicleVO>();
			for(VehiclePO p : pos){
				VehicleVO v = BusinessMainController.vehiclePOToVO(p);
				vos.add(v);
			}
			return vos;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取车辆信心失败");
			return null;
		}
		
	}
	
	/*public static void main(String[] args){
		InitialStockBL bl=new InitialStockBL();
		InitInfoVO v=bl.getInitInfo("2015-12-11");
		System.out.println(v.getTime());
	}
	*/

}