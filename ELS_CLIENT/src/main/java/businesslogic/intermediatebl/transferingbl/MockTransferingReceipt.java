package businesslogic.intermediatebl.transferingbl;

import java.util.ArrayList;

import vo.OrderVO;
import vo.OrganizationVO;
import vo.TransferingReceiptVO;

public class MockTransferingReceipt extends TransferingReceiptVO {
	String ID;

	public MockTransferingReceipt(ArrayList<OrderVO> orderList, String ID,
			String time, OrganizationVO intermediateCentre) {
		super(orderList, ID, time, intermediateCentre);
		// TODO 自动生成的构造函数存根
	}

	public String getID() {
		return ID;
	}
}
