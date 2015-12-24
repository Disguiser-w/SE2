package businesslogicservice.logdiaryblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.LogDiaryVO;

public interface LogDiaryBLService {
	
	/**
	 * 添加日志信息
	 * */
	public int addLogDiary(LogDiaryVO vo,String time) throws RemoteException;
	
	/**
	 * 查找特定时间的日志信息
	 * */
	public ArrayList<LogDiaryVO> getLogDiaryVO(String time);
	
	/**
	 * 获取所有的日志信息
	 * */
	public ArrayList<LogDiaryVO> getAllLogDiaryVOs();

}
