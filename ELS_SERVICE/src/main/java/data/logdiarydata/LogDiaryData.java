package data.logdiarydata;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.FileGetter;
import po.LogDiaryPO;
import dataservice.logdiarydataservice.LogDiaryDataService;
import file.JXCFile;

public class LogDiaryData extends UnicastRemoteObject implements LogDiaryDataService{

	private static final long serialVersionUID = 8328995795183749150L;

	JXCFile file;
	public LogDiaryData() throws RemoteException {
		super();
	}
	
	public int addLogDiary(LogDiaryPO po,String time) {
		// TODO Auto-generated method stub
		String path = "info/"+"logDiaryInfo/"+time+"-logDiaryInfo.ser";
		JXCFile file = new JXCFile(path);
		file.write(po);
		return 0;
	}
	
	public LogDiaryPO getLogDiaryPO(String time) {
		// TODO Auto-generated method stub
		String path ="logDiaryInfo/"+time+"-logDiaryInfo.ser";
		File file = FileGetter.getFile(path);
		try{
			if(!file.exists()){
				System.out.println("file is not exsit");
				return null;
			}
			else{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				LogDiaryPO logDiaryPO = (LogDiaryPO) in.readObject();
				in.close();
				return logDiaryPO;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("日志信息读写失败");
			return null;
		}
	}
	
	public ArrayList<LogDiaryPO> getAllLogDiaryPOs() {
		// TODO Auto-generated method stub
		File dir =FileGetter.getFile("logDiaryInfo");
		File[] files = dir.listFiles();
		if(files.length == 0){
			return null;
		}
		else{
			ArrayList<LogDiaryPO> logDiaryPOs = new ArrayList<LogDiaryPO>();
		for(File i:files){
			try{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(i));
				LogDiaryPO logDiaryPO = (LogDiaryPO) in.readObject();
				in.close();
				logDiaryPOs.add(logDiaryPO);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("日志信息读取失败");
			}
		}
		return logDiaryPOs;
		}
	}
	
	

}
