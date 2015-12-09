package intermediatetest;

import java.util.ArrayList;

import vo.EntruckingReceiptVO;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.TruckVO;

public class MockEntruckingReceipt extends EntruckingReceiptVO {
	String ID;

	public MockEntruckingReceipt(ArrayList<OrderVO> enplaningReceipt,
			String time, String ID, OrganizationVO intermediateCentre,
			TruckVO truck) {
		super(intermediateCentre, truck, enplaningReceipt, 0, ID, ID, null);
		// TODO 自动生成的构造函数存根
	}

	public String getID() {
		return ID;
	}
}
