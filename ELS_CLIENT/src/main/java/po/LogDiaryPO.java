package po;

import java.io.Serializable;

/**
 * 系统日志的序列化文件
 * 要在这里添加业绩吗，，，还是直接忽略
 * */
public class LogDiaryPO implements Serializable{

	private static final long serialVersionUID = -5033948963240199526L;
	
	private String date;
	private UserPO userPO;
	private String info;
	
	public LogDiaryPO(String date,UserPO userPO,String info){
		this.date = date ;
		this.userPO = userPO;
		this.info = info;
	}
	
	public String getDate(){
		return date;
	}
	
	public UserPO getUserpo(){
		return userPO;
	}

	public String getInfo(){
		return info;
	}
}
