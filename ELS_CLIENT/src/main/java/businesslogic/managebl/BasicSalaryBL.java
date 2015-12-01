package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BasicSalaryPO;
import vo.BasicSalaryVO;
import type.ProfessionType;
import businesslogicservice.manageblservice.BasicSalaryBLService;
import dataservice.managedataservice.BasicSalaryDataService;

public class BasicSalaryBL implements BasicSalaryBLService{
	BasicSalaryDataService bsdService;
	
	public int addBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			BasicSalaryPO basicSalarypo = basicSalaryVOToPO(basicSalaryvo);
			return(bsdService.addBasicSalary(basicSalarypo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			return(bsdService.deleteBasicSalary(basicSalaryvo.getProfession()));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			BasicSalaryPO basicSalarypo = basicSalaryVOToPO(basicSalaryvo);
			return(bsdService.modifyBasicSalary(basicSalarypo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public double findBasicSalary(ProfessionType profession){
		try{
			return bsdService.findBasicSalary(profession).getBasicSalary();
		}catch(RemoteException exception){
			exception.printStackTrace();
			return -1.0;
		}
	}
	
	public ArrayList<BasicSalaryVO> showAllBasicSalarys(){
		try{
			ArrayList<BasicSalaryPO> basicSalaryPOList =  bsdService.showAllBasicSalarys();
			ArrayList<BasicSalaryVO> basicSalaryVOList =  new ArrayList<BasicSalaryVO>();
			for(BasicSalaryPO basicSalary: basicSalaryPOList)
				basicSalaryVOList.add(basicSalaryPOToVO(basicSalary));
			return basicSalaryVOList;
		}
		catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public static BasicSalaryPO basicSalaryVOToPO(BasicSalaryVO basicSalaryvo){
		return new BasicSalaryPO(basicSalaryvo.getProfession(),basicSalaryvo.getBasicSalary());
	}
	
	public static BasicSalaryVO basicSalaryPOToVO(BasicSalaryPO basicSalarypo){
		return new BasicSalaryVO(basicSalarypo.getProfession(),basicSalarypo.getBasicSalary());
	}
	
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	public static void main(String[] args){
		try {
			BasicSalaryDataService basicSalaryData = (BasicSalaryDataService)Naming.lookup("rmi://172.25.132.40:6003/BasicSalaryDataService");
			
			ArrayList<BasicSalaryPO> basicSalaryList0 = basicSalaryData.showAllBasicSalarys();
			for(BasicSalaryPO basicSalary:basicSalaryList0)
				System.out.println("Profession: "+basicSalary.getProfession()+", BasicSalary: "+basicSalary.getBasicSalary());

			basicSalaryData.modifyBasicSalary(new BasicSalaryPO(ProfessionType.courier,4500));
			
			ArrayList<BasicSalaryPO> basicSalaryList1 = basicSalaryData.showAllBasicSalarys();
			for(BasicSalaryPO basicSalary:basicSalaryList1)
				System.out.println("Profession: "+basicSalary.getProfession()+", BasicSalary: "+basicSalary.getBasicSalary());

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
