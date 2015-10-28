package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransferingReceiptVO {
	public final String ID;
	public final String time;
	public final ArrayList<OrderVO> orderList;
	public final OrganizationVO interdiateCentre;
    
    public TransferingReceiptVO(ArrayList<OrderVO> orderList,String ID,String time,OrganizationVO intermediateCentre){
		this.orderList = orderList;
		this.ID =ID;
		this.time = time;
		this.interdiateCentre = intermediateCentre;
    }
}
