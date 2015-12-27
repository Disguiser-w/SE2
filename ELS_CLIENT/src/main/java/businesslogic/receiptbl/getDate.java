package businesslogic.receiptbl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetDate {
	/**
	 * 将获取日期的方法定义为静态方法
	 * */
	public static String getdate(){
		Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String sysDatetime = fmt.format(rightNow.getTime()); 
        String date = sysDatetime.substring(0, 4)+"-"+sysDatetime.substring(4, 6)+"-"+sysDatetime.substring(6);
        return date;
	}
	
/*	public static void main(String[] args){
		String temp=getData.getdata();
		System.out.println(temp);
	}
*/
}
