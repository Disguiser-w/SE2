package businesslogic.logdiarybl.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.LogDiaryVO;
import vo.UserVO;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogic.receiptbl.getDate;
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

	public LogDiaryVO getLogDiaryVO(String time) {
		// TODO Auto-generated method stub
		return logDiaryBL.getLogDiaryVO(time);
	}

	public ArrayList<LogDiaryVO> getAllLogDiaryVOs() {
		// TODO Auto-generated method stub
		return logDiaryBL.getAllLogDiaryVOs();
	}
	
/*	public static void main(String[] args){
		LogDiaryBLController controller = new LogDiaryBLController();
		LogDiaryVO vo = new LogDiaryVO("2015-12-22", new UserVO("王丽莉", "CW-00001", "", ProfessionType.financialStaff,
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "新增加一个账户");
//		try {
//			controller.addLogDiary(vo, "2015-12-22");
			LogDiaryVO test=controller.getLogDiaryVO("2015-12-22");
			ArrayList<LogDiaryVO> vos = controller.getAllLogDiaryVOs();
			System.out.println(test.info);
			System.out.println(vos.get(0).userVO.userID);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	*/

}
