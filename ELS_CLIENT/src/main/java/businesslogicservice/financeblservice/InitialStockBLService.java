package businesslogicservice.financeblservice;

import java.util.ArrayList;

import vo.InitInfoVO;

public interface InitialStockBLService {
	//设置期初信息
	public int initInfo(InitInfoVO vo,String Time );
	//查询期初信息
	public InitInfoVO getInitInfo(String time);
	//获取所有期初信息
	public ArrayList<InitInfoVO> getAllInitInfo();
	
	
	

}
