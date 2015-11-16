package financetest;

import businesslogic.financebl.AccountBL;
import vo.MockAccountVO;
import junit.framework.TestCase;

public class AccountBLTest extends TestCase{
	
	public void testAccount(){
	MockAccountVO mavo1=new MockAccountVO("汪狗",3000);
	MockAccountVO mavo2=new MockAccountVO("哈哈",5000);
	
	AccountBL abl=new AccountBL();
	int x=abl.addAccount(mavo1);
	double money=abl.getMoney(mavo1);
	String name=abl.getName(mavo2);
	
	assertEquals(0,x);
	assertEquals(3000.0, money);
	assertEquals("哈哈", name);
	}
	
	
}
