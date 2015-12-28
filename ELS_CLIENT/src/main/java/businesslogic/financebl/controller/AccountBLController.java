package businesslogic.financebl.controller;

import java.util.ArrayList;

import businesslogic.financebl.AccountBL;
import businesslogicservice.financeblservice.AccountBLService;
import vo.AccountVO;

public class AccountBLController implements AccountBLService{
	private AccountBL accountBL;
	
	public AccountBLController() {
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

	
	public static void main(String[] arg){
//		AccountBLController controller;
//		try {
//			controller = new AccountBLController();
////			AccountVO voo=new AccountVO("宝宝", 200);
////			int result=controller.addAccount(voo);
////			System.out.println(result);
//			ArrayList<AccountVO> vo=controller.showAll();
//			for(AccountVO v:vo){
//				System.out.println("Name "+v.getName());
//			}
//			AccountVO v1=controller.findbyName("这个要被删掉");
//			System.out.println(v1.getMoney());
//			controller.deleteAccount("这个要被删掉");
//			System.out.println("-------------------------------");
//			ArrayList<AccountVO> vo1=controller.showAll();
//			for(AccountVO v:vo1){
//				System.out.println("Name "+v.getName());
//			}
//			System.out.println("-------------------------------");
//			AccountVO test=controller.findbyName("这个要被删掉");
//			System.out.println("这个要被删掉："+test.getMoney());
//		} catch (MalformedURLException | RemoteException | NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}
}
