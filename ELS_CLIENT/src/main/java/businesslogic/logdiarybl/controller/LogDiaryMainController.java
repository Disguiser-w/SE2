package businesslogic.logdiarybl.controller;

import java.util.ArrayList;

import po.LogDiaryPO;
import po.UserPO;
import vo.LogDiaryVO;
import vo.UserVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.userbl.controller.UserMainController;
import dataservice.logdiarydataservice.LogDiaryDataService;

public class LogDiaryMainController {
	public static LogDiaryDataService logDiaryData;
	
	private LogDiaryBLController logDiaryBLController;
	
	public LogDiaryMainController(){
		//dataFactory
		logDiaryData = DataFactory.getLogDiaryData();
		
		logDiaryBLController = new LogDiaryBLController();
	}
	
	
	/**
	 * logDiary PO to VO
	 * */
	public static LogDiaryVO logDiaryPOToVO(LogDiaryPO po){
		String time = po.getDate();
		UserVO userVO = UserMainController.userPOToVO(po.getUserpo());
		String info = po.getInfo();
		LogDiaryVO logDiaryVO = new LogDiaryVO(time, userVO, info);
		return logDiaryVO;
	}
	
	/**
	 * logDiaryVO to PO
	 * */
	public static LogDiaryPO logDiaryVOToPO(LogDiaryVO vo){
		String time = vo.time;
		UserPO userPO = UserMainController.userVOToPO(vo.userVO);
		String info = vo.info;
		LogDiaryPO logDiaryPO = new LogDiaryPO(time, userPO, info);
		return logDiaryPO;
	}
	
	/**
	 * logDiaryPOs to VOs
	 * */
	public static ArrayList<LogDiaryVO> logDiaryPOsToVOs(ArrayList<LogDiaryPO> pos){
		ArrayList<LogDiaryVO> vos = new ArrayList<LogDiaryVO>();
		for(LogDiaryPO p : pos){
			LogDiaryVO v = logDiaryPOToVO(p);
			vos.add(v);
		}
		return vos;
	}
}
