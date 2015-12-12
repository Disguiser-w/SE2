package businesslogic.financebl.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.InitInfoVO;
import businesslogic.financebl.InitialStockBL;
import businesslogicservice.financeblservice.InitialStockBLService;

public class InitialStockBLController implements InitialStockBLService{
	
	private InitialStockBL initialStockBL;
	
	public InitialStockBLController() {
		initialStockBL=new InitialStockBL();
	}

	public int initInfo(String Time,InitInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return initialStockBL.initInfo(Time,vo);
	}

	public InitInfoVO getInitInfo(String time) throws RemoteException {
		// TODO Auto-generated method stub
		return initialStockBL.getInitInfo(time);
	}

	public ArrayList<InitInfoVO> getAllInitInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return initialStockBL.getAllInitInfo();
	}


}
