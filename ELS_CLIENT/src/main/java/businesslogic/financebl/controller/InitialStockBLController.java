package businesslogic.financebl.controller;

import java.util.ArrayList;

import vo.InitInfoVO;
import businesslogic.financebl.InitialStockBL;
import businesslogicservice.financeblservice.InitialStockBLService;

public class InitialStockBLController implements InitialStockBLService{
	
	private InitialStockBL initialStockBL;
	
	public InitialStockBLController() {
		initialStockBL=new InitialStockBL();
	}

	public int initInfo(InitInfoVO vo,String Time)  {
		// TODO Auto-generated method stub
		return initialStockBL.initInfo(vo,Time);
	}

	public InitInfoVO getInitInfo(String time) {
		// TODO Auto-generated method stub
		return initialStockBL.getInitInfo(time);
	}

	public ArrayList<InitInfoVO> getAllInitInfo()  {
		// TODO Auto-generated method stub
		return initialStockBL.getAllInitInfo();
	}


}
