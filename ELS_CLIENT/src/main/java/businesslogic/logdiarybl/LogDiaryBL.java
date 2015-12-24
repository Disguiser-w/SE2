package businesslogic.logdiarybl;

import java.util.ArrayList;

import businesslogic.logdiarybl.controller.LogDiaryMainController;
import businesslogicservice.logdiaryblservice.LogDiaryBLService;
import po.LogDiaryPO;
import vo.LogDiaryVO;
import dataservice.logdiarydataservice.LogDiaryDataService;

public class LogDiaryBL implements LogDiaryBLService{
	
	private LogDiaryDataService logDiaryData;

	public int addLogDiary(LogDiaryVO vo, String time) {
		// TODO Auto-generated method stub
		LogDiaryPO po = LogDiaryMainController.logDiaryVOToPO(vo);
		return logDiaryData.addLogDiary(po, time);
	}

	public LogDiaryVO getLogDiaryVO(String time) {
		// TODO Auto-generated method stub
		LogDiaryVO vo = LogDiaryMainController.logDiaryPOToVO(logDiaryData.getLogDiaryPO(time));
		return vo;
	}

	public ArrayList<LogDiaryVO> getAllLogDiaryVOs() {
		// TODO Auto-generated method stub
		ArrayList<LogDiaryVO> vos = LogDiaryMainController.logDiaryPOsToVOs(logDiaryData.getAllLogDiaryPOs());
		return vos;
	}

}
