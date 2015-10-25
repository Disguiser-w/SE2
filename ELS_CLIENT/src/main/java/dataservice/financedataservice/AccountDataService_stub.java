package dataservice.financedataservice;

import java.util.ArrayList;

import po.AccountPO;

public class AccountDataService_stub implements AccountDataService{

	public int addAccount(AccountPO po) {
		// TODO Auto-generated method stub
		System.out.println("Add account successfully!");
		return 0;
	}

	public int deleteAccount(AccountPO po) {
		// TODO Auto-generated method stub
		System.out.println("Delete account successfully!");
		return 0;
	}

	public int modifyAccount(AccountPO po,String name) {
		// TODO Auto-generated method stub
		System.out.println("Modify account successfully!");
		return 0;
	}

	public AccountPO findbyName(String name) {
		// TODO Auto-generated method stub
		System.out.println("Find Account successfully! ");
		return null;
	}

	public ArrayList<AccountPO> findByKeyword(String s) {
		// TODO Auto-generated method stub
		System.out.println("Find Account successfully! ");
		return null;
	}

	public ArrayList<AccountPO> showAll() {
		// TODO Auto-generated method stub
		System.out.println("Show Account succssfully!");
		return null;
	}

}
