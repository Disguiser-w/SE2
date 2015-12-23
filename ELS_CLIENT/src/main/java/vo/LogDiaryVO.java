package vo;

public class LogDiaryVO {
	public String time;
	public UserVO userVO;
	public String info;
	
	public LogDiaryVO(String time,UserVO userVO,String info){
		this.time = time;
		this.userVO = userVO;
		this.info = info;
	}

}
