package data.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountPO;
import dataservice.financedataservice.AccountDataService;

public class AccountData implements AccountDataService{

	//本宝宝yy了一个读写文件的类
	JXCFile file;
	public AccountData(){
		super();
		file=new JXCFile();
	}
	public int addAccount(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
		return 0;
	}

	public int deleteAccount(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int modifyAccount(AccountPO po, String name) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public AccountPO findbyName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<AccountPO> findByKeyword(String s) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<AccountPO> showAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
