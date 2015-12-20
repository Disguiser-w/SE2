package businesslogic.financebl.driver;

import java.util.ArrayList;

import vo.AccountVO;
import businesslogic.financebl.AccountBL;
import businesslogic.financebl.controller.AccountBLController;

public class AccountBL_driver {
	AccountBL controller ;

	public static void main(String[] args){
		AccountBL controller = new AccountBL();
		AccountVO test = new AccountVO("狗剩", 200);
		int result = controller.addAccount(test);
		System.out.println(result);
		ArrayList<AccountVO> vos=controller.findByKeyword("狗");
		System.out.println(vos.get(0).name);
		AccountVO vo=controller.findbyName("狗剩");
		
		System.out.println(vo.money);
		 controller.modifyAccount(vo, "doge");
		 controller.showAll();
		 
		 controller.addMoney("doge", 100);
		 controller.delMoney("doge", 100);
		controller.deleteAccount("doge");
		
		
	}
}
