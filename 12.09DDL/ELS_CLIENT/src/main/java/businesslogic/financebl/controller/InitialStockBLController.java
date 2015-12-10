package businesslogic.financebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.InitInfoVO;
import businesslogic.financebl.InitialStockBL;
import businesslogicservice.financeblservice.InitialStockBLService;

public class InitialStockBLController implements InitialStockBLService{
	
	private InitialStockBL initialStockBL;
	
	public InitialStockBLController() throws MalformedURLException, RemoteException, NotBoundException{
		initialStockBL=new InitialStockBL();
	}

	public int initInfo(String Time) throws RemoteException {
		// TODO Auto-generated method stub
		return initialStockBL.initInfo(Time);
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
