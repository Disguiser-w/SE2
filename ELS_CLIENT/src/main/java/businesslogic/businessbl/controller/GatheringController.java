package businesslogic.businessbl.controller;

import java.util.ArrayList;

import businesslogic.businessbl.Gathering;
import businesslogicservice.businessblservice.GatheringBLService;

public class GatheringController implements GatheringBLService {
	private Gathering gathering;

	public GatheringController() {
		gathering = new Gathering();
	}

	public ArrayList<String[]> getChargeInfo() {
		// TODO Auto-generated method stub
		return gathering.getChargeInfo();
	}

	public double gathering() {
		// TODO Auto-generated method stub
		return gathering.gathering();
	}

}
