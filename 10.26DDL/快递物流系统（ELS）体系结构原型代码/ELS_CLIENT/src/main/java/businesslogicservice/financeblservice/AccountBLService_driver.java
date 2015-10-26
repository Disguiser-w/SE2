package businesslogicservice.financeblservice;

import vo.AccountVO;

public class AccountBLService_driver {
	public void drive(AccountBLService abs){
		abs.addAccount(new AccountVO());
		abs.deleteAccount(new AccountVO());
		String name="金三胖";
		abs.modifyAccount(new AccountVO(),name);
		abs.findbyName(name);
		String keyword="胖";
		abs.findByKeyword(keyword);
	}
	
	public static void main(String[] args){
		AccountBLService abs=new AccountBLService_stub();
		AccountBLService_driver driver=new AccountBLService_driver();
		driver.drive(abs);
		
	}

}
