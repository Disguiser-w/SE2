package data.logdiarydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.LogDiaryPO;
import dataservice.logdiarydataservice.LogDiaryDataService;
import file.JXCFile;

public class LogDiaryData extends UnicastRemoteObject implements LogDiaryDataService{

	private static final long serialVersionUID = 8328995795183749150L;

	JXCFile file;
	public LogDiaryData() throws RemoteException {
		super();
		file=new JXCFile("info/logDiaryInfo/logDiary.ser");

	}
	
	public int addLogDiary(LogDiaryPO po,String time) {
		// TODO Auto-generated method stub
		file.write(po);
		return 0;
	}
	
	public ArrayList<LogDiaryPO> getLogDiaryPO(String time) {
		// TODO Auto-generated method stub
		file=new JXCFile("info/logDiaryInfo/logDiary.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件logDiary.ser失败");
			return null;
		}
		ArrayList<LogDiaryPO> right=new ArrayList<LogDiaryPO>();
		for(Object o:os){
			LogDiaryPO po=(LogDiaryPO) o;
			if(po.getDate().equals(time)){
				right.add(po);
			}
		}
		return right; 
	}
	
	public ArrayList<LogDiaryPO> getAllLogDiaryPOs(){
		// TODO Auto-generated method stub
		file=new JXCFile("info/logDiaryInfo/logDiary.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件logDiary.ser失败");
			return null;
		}
		ArrayList<LogDiaryPO> pos=new ArrayList<LogDiaryPO>();
		for(Object o:os){
			LogDiaryPO po=(LogDiaryPO) o;
			pos.add(po);
		}
		return pos;
	}
	
	

}
