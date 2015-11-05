package businesslogicservice.financeblservice;

import vo.InitInfoVO;

public class InitialStockBLService_driver {
	public void drive(InitialStockBLService ibs){
		String time="20150101";
		ibs.initInfo(new InitInfoVO(), time);
		ibs.getInitInfo(time);
		ibs.getAllInitInfo();		
	}
	
	public static void main(){
		InitialStockBLService ibs=new InitialStockBLService_stub();
		InitialStockBLService_driver driver=new InitialStockBLService_driver();
		driver.drive(ibs);
	}

}
