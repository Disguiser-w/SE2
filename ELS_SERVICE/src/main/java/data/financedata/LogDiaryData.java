package data.financedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.FileGetter;
import po.LogDiaryPO;
import dataservice.financedataservice.LogDiaryDataService;

public class LogDiaryData extends UnicastRemoteObject implements LogDiaryDataService{

	private static final long serialVersionUID = 8328995795183749150L;

	public LogDiaryData() throws RemoteException {
		super();

	}
	
	/**
	 * 读
	 * */
	public ArrayList<LogDiaryPO> getLogDiaryPOs(){
		String path = "logDiaryInfo/logDiary.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<LogDiaryPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<LogDiaryPO> logDiaryPOs = (ArrayList<LogDiaryPO>) in.readObject();
			in.close();
			return logDiaryPOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 写
	 * */
	public int saveLogDiary(ArrayList<LogDiaryPO> pos){
		String path = "logDiaryInfo/logDiary.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int addLogDiary(LogDiaryPO po,String time) {
		// TODO Auto-generated method stub
       ArrayList<LogDiaryPO> logDiaryPOs = getLogDiaryPOs();
		logDiaryPOs.add(po);
		saveLogDiary(logDiaryPOs);
		return 0;
	}
	
	public ArrayList<LogDiaryPO> getLogDiaryPO(String time) {
		// TODO Auto-generated method stub
		
		ArrayList<LogDiaryPO> right=new ArrayList<LogDiaryPO>();
		ArrayList<LogDiaryPO> logDiaryPOs = getLogDiaryPOs();
		for(int i=0;i<logDiaryPOs.size();i++){
			if(logDiaryPOs.get(i).getDate().substring(0, 10).equals(time)){
			right.add(logDiaryPOs.get(i));
			}
		}
		return right; 
	}
	
	public ArrayList<LogDiaryPO> getAllLogDiaryPOs(){
		// TODO Auto-generated method stub
		ArrayList<LogDiaryPO> logDiaryPOs = getLogDiaryPOs();
		if(logDiaryPOs!=null){
		return logDiaryPOs;
		}
		else{
			return null;
		}
	}
	
	

}
