package dataservice.managedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BasicSalaryPO;
import type.ProfessionType;

public class BasicSalaryDataService_stub implements BasicSalaryDataService{
	
	public int addBasicSalary(BasicSalaryPO basicSalarypo) throws RemoteException{
		System.out.println("Add basic salary succeed!");
		return 0;
	}
	
    public int deleteBasicSalary(ProfessionType profession) throws RemoteException{
		System.out.println("Delete basic salary succeed!");
		return 0;
	}
    
    public int modifyBasicSalary(BasicSalaryPO basicSalarypo) throws RemoteException{
		System.out.println("Modify basic salary succeed!");
		return 0;
	}
    
    public BasicSalaryPO findBasicSalary(ProfessionType profession) throws RemoteException{
		System.out.println("Find basic salary succeed!");
		return null;
	}
    
    public ArrayList<BasicSalaryPO> showAllBasicSalarys() throws RemoteException{
		System.out.println("Show all basic salary succeed!");
		return null;
	}
    
}
