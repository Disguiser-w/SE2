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

	public LogDiaryVO getLogDiaryVO(String time) {
		// TODO Auto-generated method stub
		return logDiaryBL.getLogDiaryVO(time);
	}

	public ArrayList<LogDiaryVO> getAllLogDiaryVOs() {
		// TODO Auto-generated method stub
		return logDiaryBL.getAllLogDiaryVOs();
	}

}
