package dataservice.managedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PerWagePO;
import type.ProfessionType;

public class PerWageDataService_stub implements PerWageDataService{

	public int addPerWage(PerWagePO perWagepo) throws RemoteException{
		System.out.println("Add per wage succeed!");
		return 0;
	}
	
    public int deletePerWage(ProfessionType profession) throws RemoteException{
		System.out.println("Delete per wage succeed!");
		return 0;
	}
    
    public int modifyPerWage(PerWagePO perWagepo) throws RemoteException{
		System.out.println("Modify per wage succeed!");
		return 0;
	}
    
    public PerWagePO findPerWage(ProfessionType profession) throws RemoteException{
		System.out.println("Find per wage succeed!");
		return null;
	}
    
    public ArrayList<PerWagePO> showAllPerWages() throws RemoteException{
		System.out.println("Show all per wage succeed!");
		return null;
	}
    
}
