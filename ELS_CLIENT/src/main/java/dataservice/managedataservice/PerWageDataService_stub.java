package dataservice.managedataservice;

import dataservice.managedataservice.PerWageDataService;
import po.PerWagePO;
import type.ProfessionType;

import java.rmi.RemoteException;

public class PerWageDataService_stub implements PerWageDataService{

	public int addPerWage(PerWagePO perwagepo) throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println("Add perwage succeed!");
		return 0;
		}

    public int deletePerWage(PerWagePO perwagepo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Delete perwage succeed!");
        return 0;
    }
		
    public int modifyPerWage(PerWagePO perwagepo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Modify perwage succeed!");
        return 0;
    }
    
    public double findPerWage(ProfessionType profession) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Find perwage succeed!");
        return 0;
    }
    
}
