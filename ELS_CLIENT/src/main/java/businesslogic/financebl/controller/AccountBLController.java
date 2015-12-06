package businesslogic.financebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.AccountVO;
import businesslogic.financebl.AccountBL;
import businesslogicservice.financeblservice.AccountBLService;

public class AccountBLController implements AccountBLService{
	private AccountBL accountBL;
	
	public AccountBLController() throws MalformedURLException, RemoteException, NotBoundException{
		accountBL=new AccountBL();
	}

	public int addAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		return accountBL.addAccount(vo);
	}

	public int deleteAccount(String name) {
		// TODO Auto-generated method stub
		return accountBL.deleteAccount(name);
	}

	public int modifyAccount(AccountVO vo, String name) {
		// TODO Auto-generated method stub
		return accountBL.modifyAccount(vo, name);
	}

	public AccountVO findbyName(String name) {
		// TODO Auto-generated method stub
		return accountBL.findbyName(name);
	}

	public ArrayList<AccountVO> findByKeyword(String s) {
		// TODO Auto-generated method stub
		return accountBL.findByKeyword(s);
	}

	public ArrayList<AccountVO> showAll() {
		// TODO Auto-generated method stub
		return accountBL.showAll();
	}

}
