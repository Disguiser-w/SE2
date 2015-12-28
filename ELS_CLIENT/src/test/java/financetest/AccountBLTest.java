package financetest;

import businesslogic.financebl.AccountBL;
import junit.framework.TestCase;
import vo.AccountVO;

public class AccountBLTest extends TestCase{
	AccountVO acc1,acc2,acc3;
	AccountBL accountBL;
	
	public void setUp(){
		acc1 = new AccountVO("张三", 100);
		acc2 = new AccountVO("", 100);
		acc3 = new AccountVO("张三", Double.parseDouble("233"));
		accountBL = new AccountBL();
	}
	
	//TUS1-1
	public void test1_1(){
		accountBL.addAccount(acc1);
		AccountVO vo = accountBL.findbyName("张三");
		assertEquals(100.0, vo.money);
	}
	
	//TUS1-2(本宝宝也不知道到底要返回什么了)
	public void test1_2(){
		boolean result = true;
		accountBL.deleteAccount("张三");
		if(accountBL.findByKeyword("张三")==null){
			result = false;
		}
		assertEquals(true, result);
	}
	
	//TUS1-3
	public void test1_3(){
		accountBL.addAccount(acc1);
		accountBL.modifyAccount(acc1,"李四");
		AccountVO vo = accountBL.findbyName("李四");
		assertEquals(100.0, vo.money);
	}
	
	//TUS1-4
	public void test1_4(){
		AccountVO vo = accountBL.findbyName("李四");
		assertEquals(100.0, vo.money);
	}
	
	
	
	
}


