package businesslogic.logdiarybl.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.LogDiaryVO;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogicservice.logdiaryblservice.LogDiaryBLService;

public class LogDiaryBLController implements LogDiaryBLService{
	private LogDiaryBL logDiaryBL;
	
	public LogDiaryBLController(){
		logDiaryBL = new LogDiaryBL();
	}
	public int addLogDiary(LogDiaryVO vo, String time) throws RemoteException {
		// TODO Auto-generated method stub
		return logDiaryBL.addLogDiary(vo, time);
	}

	public ArrayList<LogDiaryVO> getLogDiaryVO(String time) {
		// TODO Auto-generated method stub
		return logDiaryBL.getLogDiaryVO(time);
	}

	public ArrayList<LogDiaryVO> getAllLogDiaryVOs() {
		// TODO Auto-generated method stub
		return logDiaryBL.getAllLogDiaryVOs();
	}
	
/*	public static void main(String[] args){
		LogDiaryBLController controller = new LogDiaryBLController();
		LogDiaryVO vo1 = new LogDiaryVO("2015-12-22", new UserVO("王丽莉", "CW-00001", "", ProfessionType.financialStaff, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "新增加一个账户");
//		LogDiaryVO vo = new LogDiaryVO("2015-12-22", new UserVO("王丽莉", "CW-00001", "", ProfessionType.financialStaff,
//				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "新增加一个账户");
		LogDiaryVO vo2 = new LogDiaryVO("2015-12-22", new UserVO("王丽莉", "CW-00001", "", ProfessionType.financialStaff, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "新修改一个账户");
		LogDiaryVO vo3 = new LogDiaryVO("2015-12-23", new UserVO("王丽莉", "CW-00001", "", ProfessionType.financialStaff, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "新修改一个账户");
		try {
			controller.addLogDiary(vo1, "2015-12-22");
			controller.addLogDiary(vo2, "2015-12-22");
			controller.addLogDiary(vo3, "2015-12-23");

			ArrayList<LogDiaryVO> test=controller.getLogDiaryVO("2015-12-22");
			ArrayList<LogDiaryVO> vos = controller.getAllLogDiaryVOs();
			System.out.println(test.get(1).info);
			System.out.println(vos.get(2).userVO.userID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	

}
