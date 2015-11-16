package financetest;


import businesslogic.financebl.PaymentReceiptBL;
import vo.MockFinanceCostVO;
import vo.MockFareVO;
import junit.framework.TestCase;

public class PaymentReceiptBLTest extends TestCase{
	
	public void testMoney(){
	MockFareVO mfvo=new MockFareVO("2015/10",3000);
	//租金
	MockFinanceCostVO mcvo1=new MockFinanceCostVO("rent",20000,"2015/10");
	//运费
	MockFinanceCostVO mcvo2=new MockFinanceCostVO("fare",mfvo,"2015/10");
	//快递员工资
	MockFinanceCostVO mcvo3=new MockFinanceCostVO("express",300,1500.0,0.5,"2015/10");
	
	PaymentReceiptBL prbl=new PaymentReceiptBL();
	double money1=prbl.getMoney(mcvo1);
	double money2=prbl.getMoney(mcvo2);
	double money3=prbl.getMoney(mcvo3);
	
	assertEquals(20000.0,money1);
	assertEquals(3000.0,money2);
	assertEquals(1650.0,money3);
	}
}
