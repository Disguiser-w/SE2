package businesslogic.businessbl;

import java.util.ArrayList;

import vo.ExpressVO;
import vo.GatheringReceiptVO;

public class GatheringBL_stub implements GatheringBLService{

	public ArrayList<ExpressVO> getChargeInfo() {
		// TODO Auto-generated method stub
		System.out.println("Show ExpressVOs");
		return null;
	}

	public double gathering(GatheringReceiptVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Gathering successfully!");
		return 0;
	}

}
