package businesslogic.logdiarybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogic.logdiarybl.controller.LogDiaryMainController;
import businesslogicservice.logdiaryblservice.LogDiaryBLService;
import dataservice.logdiarydataservice.LogDiaryDataService;
import po.LogDiaryPO;
import vo.LogDiaryVO;

public class LogDiaryBL implements LogDiaryBLService{
	
	private LogDiaryDataService logDiaryData;
	
	public LogDiaryBL(){
		try {
			logDiaryData = DataFactory.getLogDiaryData();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int addLogDiary(LogDiaryVO vo, String time) {
		// TODO Auto-generated method stub
		LogDiaryPO po = LogDiaryMainController.logDiaryVOToPO(vo);
		try {
			return logDiaryData.addLogDiary(po, time);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加日志信息异常BL");
			return -1;
		}
	}

	public ArrayList<LogDiaryVO> getLogDiaryVO(String time) {
		// TODO Auto-generated method stub
		ArrayList<LogDiaryVO> vos;
		try {
			if(logDiaryData.getLogDiaryPO(time)==null){
				System.out.println("不存在该时间的日志信息");
				return null;
			}
			vos = LogDiaryMainController.logDiaryPOsToVOs(logDiaryData.getLogDiaryPO(time));
			return vos;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取日志信息异常BL");
			return null;
		}
	}

	public ArrayList<LogDiaryVO> getAllLogDiaryVOs() {
		// TODO Auto-generated method stub
		ArrayList<LogDiaryVO> vos;
		try {
			if(logDiaryData.getAllLogDiaryPOs()==null){
				System.out.println("不存在任何日志信息");
				return null;
			}
			else{
			vos = LogDiaryMainController.logDiaryPOsToVOs(logDiaryData.getAllLogDiaryPOs());
			return vos;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
