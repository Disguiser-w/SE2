package businesslogicservice.financeblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.InitInfoVO;

public class InitialStockBLService_stub implements InitialStockBLService {

	public int initInfo(InitInfoVO vo,String Time) {
		// TODO Auto-generated method stub
		System.out.println("Initialize successfully!");
		return 0;
	}

	public InitInfoVO getInitInfo(String time) {
		// TODO Auto-generated method stub
		System.out.println("Get initialInfo successfully!");
		return null;
	}

	public ArrayList<InitInfoVO> getAllInitInfo() {
		// TODO Auto-generated method stub
		System.out.println("Get all initialInfo successfully!");
		return null;
	}

	@Override
	public int initInfo(String Time) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
