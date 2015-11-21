package businesslogicservice.financeblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.InitInfoVO;
/**
 * 期初建账
 * */
public interface InitialStockBLService {
	//需要每个信息都设置吗
	//设置期初信息
	public int initInfo(InitInfoVO vo,String Time ) throws RemoteException;
	//查询期初信息
	public InitInfoVO getInitInfo(String time) throws RemoteException;
	//获取所有期初信息
	public ArrayList<InitInfoVO> getAllInitInfo();
	
	
	

}
