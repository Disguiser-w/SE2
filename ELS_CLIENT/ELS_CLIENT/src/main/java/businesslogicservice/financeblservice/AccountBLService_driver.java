package businesslogicservice.financeblservice;

import vo.AccountVO;

public class AccountBLService_driver {
	public void drive(AccountBLService abs){
		abs.addAccount(new AccountVO(null, 0));
		abs.deleteAccount(new AccountVO(null, 0));
		String name="金三胖";
		abs.modifyAccount(new AccountVO(name, 0),name);
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
