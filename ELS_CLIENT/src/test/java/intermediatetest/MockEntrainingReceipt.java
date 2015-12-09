package intermediatetest;

import java.util.ArrayList;

import vo.EntrainingReceiptVO;
import vo.OrderVO;
import vo.TrainVO;

public class MockEntrainingReceipt extends EntrainingReceiptVO {
	String ID;

	public MockEntrainingReceipt(ArrayList<OrderVO> enplaningReceipt,
			String time, String ID, String intermediateCentre, TrainVO train) {
		super(null, train, enplaningReceipt, 0, intermediateCentre,
				intermediateCentre, null);
		// TODO 自动生成的构造函数存根
	}

	public String getID() {
		return ID;
	}
}
