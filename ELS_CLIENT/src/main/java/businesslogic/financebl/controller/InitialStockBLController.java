package businesslogic.financebl.controller;

import java.util.ArrayList;

import vo.InitInfoVO;
import vo.VehicleVO;
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
	
	public ArrayList<VehicleVO> getVehicleInfo(){
		return initialStockBL.getVehicleInfo();
	}
	
	public static void main(String[] args){
		InitialStockBLController controller = new InitialStockBLController();
		ArrayList<InitInfoVO> vos=controller.getAllInitInfo();
		System.out.println(vos.size());
		System.out.println(vos.get(0).userVOs);
		System.out.println(vos.get(0).time);
	}

}
