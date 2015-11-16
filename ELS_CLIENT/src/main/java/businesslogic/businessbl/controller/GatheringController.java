package businesslogic.businessbl.controller;

import java.util.ArrayList;

import businesslogic.businessbl.Gathering;
import businesslogicservice.businessblservice.GatheringBLService;
import vo.ExpressVO;
import vo.GatheringReceiptVO;

public class GatheringController implements GatheringBLService{
	private Gathering gathering;
	
	public GatheringController(){
		gathering=new Gathering();
	}
	public ArrayList<ExpressVO> getChargeInfo() {
		// TODO Auto-generated method stub
		return gathering.getChargeInfo();
	}

	public double gathering(GatheringReceiptVO vo) {
		// TODO Auto-generated method stub
		return gathering.gathering(vo);
	}

}
