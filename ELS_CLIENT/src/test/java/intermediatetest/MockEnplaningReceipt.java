package intermediatetest;

import java.util.ArrayList;

import vo.EnplaningReceiptVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.PlaneVO;

public class MockEnplaningReceipt extends EnplaningReceiptVO {
	String ID;

	public MockEnplaningReceipt(ArrayList<OrderVO> enplaningReceipt,
			String time, String ID, OrganizationVO intermediateCentre,
			PlaneVO plane) {
		super(intermediateCentre, plane, enplaningReceipt, 0, ID, ID, null);
		// TODO 自动生成的构造函数存根
	}

	public String getID() {
		return ID;
	}
}
