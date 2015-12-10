package dataservice.financedataservice;

import java.rmi.RemoteException;

import po.AccountPO;


public class AccountDataService_driver {
	public void drive(AccountDataService ads) throws RemoteException{
		ads.addAccount(new AccountPO());
		ads.deleteAccount(new AccountPO());
		String name="金三胖";
		String s="胖";
		ads.modifyAccount(new AccountPO(),name);
		ads.findbyName(name);
		ads.findByKeyword(s);
		ads.showAll();
		
	}
	public static void main(String[] args) throws RemoteException{
		AccountDataService ads=new AccountDataService_stub();
		AccountDataService_driver driver=new AccountDataService_driver();
		driver.drive(ads);
	}

}
