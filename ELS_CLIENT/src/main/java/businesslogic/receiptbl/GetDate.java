package businesslogic.receiptbl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetDate {
	/**
	 * 将获取日期的方法定义为静态方法
	 * */
	public static String getdate(){
		Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String sysDatetime = fmt.format(rightNow.getTime()); 
        return sysDatetime;
	}
	
	/**
	 * 获取时间
	 * */
	public static String getTime(){
		Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String sysDatetime = fmt.format(rightNow.getTime()); 
        return sysDatetime;
	}
/*	public static void main(String[] args){
		String temp=getData.getdata();
		System.out.println(temp);
	}
*/
	
	
	
	
	public static void main(String[] args){
		String temp = getdate();
		System.out.println(temp);
	}
}
