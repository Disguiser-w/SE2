package businesslogic.financebl.driver;

import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import vo.BusinessStatementReceiptVO;

public class BusinessStatementBL_driver {
	public static void main(String[] args){
		BusinessStatementReceiptBLController businessStatementReceiptBLController= new BusinessStatementReceiptBLController();
		BusinessStatementReceiptVO bvo=businessStatementReceiptBLController.showBSList("20101010", "20160101");
		System.out.println(bvo.cvos.get(0));
		businessStatementReceiptBLController.export(bvo);
	}

}
