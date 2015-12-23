package dataservice.logdiarydataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.LogDiaryPO;

public interface LogDiaryDataService extends Remote{
	
	/**
	 * 添加日志信息
	 * */
	public int addLogDiary(LogDiaryPO po,String time);
	
	/**
	 * 依据时间获取日志信息
	 * */
	public LogDiaryPO getLogDiaryPO(String time);
	
	/**
	 * 读取所有日志信息
	 * */
	public ArrayList<LogDiaryPO> getAllLogDiaryPOs();
	
}
