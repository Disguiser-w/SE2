package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PerWagePO;
import vo.PerWageVO;
import type.ProfessionType;
import businesslogicservice.manageblservice.PerWageBLService;
import dataservice.managedataservice.PerWageDataService;

public class PerWageBL implements PerWageBLService{
	
	PerWageDataService pwdService;
	
	public PerWageBL(){
		try{
			pwdService = (PerWageDataService)Naming.lookup("rmi://localhost:8888/PerWageDataService");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public int addPerWage(PerWageVO perWagevo){
		try{
			PerWagePO perWagepo = perWageVOToPO(perWagevo);
			return(pwdService.addPerWage(perWagepo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deletePerWage(PerWageVO perWagevo){
		try{
			return(pwdService.deletePerWage(perWagevo.getProfession()));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyPerWage(PerWageVO perWagevo){
		try{
			PerWagePO perWagepo = perWageVOToPO(perWagevo);
			return(pwdService.modifyPerWage(perWagepo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public double findPerWage(ProfessionType profession){
		try{
			return pwdService.findPerWage(profession).getPerWage();
		}catch(RemoteException exception){
			exception.printStackTrace();
			return -1.0;
		}
	}
	
	public ArrayList<PerWageVO> showAllPerWages(){
		try{
			ArrayList<PerWagePO> perWagePOList =  pwdService.showAllPerWages();
			ArrayList<PerWageVO> perWageVOList =  new ArrayList<PerWageVO>();
			for(PerWagePO perWage: perWagePOList)
				perWageVOList.add(perWagePOToVO(perWage));
			return perWageVOList;
		}
		catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public static PerWagePO perWageVOToPO(PerWageVO perWagevo){
		return new PerWagePO(perWagevo.getProfession(),perWagevo.getPerWage());
	}
	
	public static PerWageVO perWagePOToVO(PerWagePO perWagepo){
		return new PerWageVO(perWagepo.getProfession(),perWagepo.getPerWage());
	}
	
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	public static void main(String[] args){
		try {
			PerWageDataService perWageData = (PerWageDataService)Naming.lookup("rmi://172.25.132.40:6002/PerWageDataService");
			
			ArrayList<PerWagePO> perWageList0 = perWageData.showAllPerWages();
			for(PerWagePO perWage:perWageList0)
				System.out.println("Profession: "+perWage.getProfession()+", PerWage: "+perWage.getPerWage());

			perWageData.addPerWage(new PerWagePO(ProfessionType.courier,4));
			
			ArrayList<PerWagePO> perWageList1 = perWageData.showAllPerWages();
			for(PerWagePO perWage:perWageList1)
				System.out.println("Profession: "+perWage.getProfession()+", PerWage: "+perWage.getPerWage());

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
	
	
}
