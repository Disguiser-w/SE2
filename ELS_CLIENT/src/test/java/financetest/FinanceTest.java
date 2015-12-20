package financetest;

import junit.framework.TestCase;

public class FinanceTest extends TestCase{
	AccountBLTest test1;
	BusinessStatementReceiptBLTest test2;
	CollectionReceiptBLTest test3;
	CostIncomeReceiptBLTest test4;
	InitialStockBLTest test5;
	PaymentReceiptBLTest test6;
	
	public void test(){
		test1 = new AccountBLTest();
		test2 = new BusinessStatementReceiptBLTest();
		test3 = new CollectionReceiptBLTest();
		test4 = new CostIncomeReceiptBLTest();
		test5 = new InitialStockBLTest();
		test6 = new PaymentReceiptBLTest();
	}
}


