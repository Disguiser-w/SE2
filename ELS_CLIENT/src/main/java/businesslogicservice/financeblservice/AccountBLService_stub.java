package businesslogicservice.financeblservice;

import java.util.ArrayList;

import vo.AccountVO;

public class AccountBLService_stub implements AccountBLService{

	public int addAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Add successfully!");
		return 0;
	}

	public int deleteAccount(String name) {
		// TODO Auto-generated method stub
		System.out.println("Delete successfully!");
		return 0;
	}

	public int modifyAccount(AccountVO vo,String name) {
		// TODO Auto-generated method stub
		System.out.println("Modify successfully!");
		return 0;
	}
	
	public AccountVO findbyName(String s) {
		// TODO Auto-generated method stub
		System.out.println("Find successfully!");
		return null;
	}

	public ArrayList<AccountVO> findByKeyword(String s) {
		// TODO Auto-generated method stub
		System.out.println("Find successfully!");
		return null;
	}

	public ArrayList<AccountVO> showAll() {
		// TODO Auto-generated method stub
		System.out.println("Show account successfully!");
		return null;
	}


	

}
