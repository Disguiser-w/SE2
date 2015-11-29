package businesslogic.receiptbl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class getDate {
	/**
	 * 将获取日期的方法定义为静态方法
	 * */
	public static String getdate(){
		Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String sysDatetime = fmt.format(rightNow.getTime());   
return sysDatetime;
	}
	
/*	public static void main(String[] args){
		String temp=getData.getdata();
		System.out.println(temp);
	}
*/
}
