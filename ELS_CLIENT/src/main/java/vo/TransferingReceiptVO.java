package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransferingReceiptVO extends ReceiptVO {
	public final OrganizationVO interdiateCentre;

	public final ArrayList<OrderVO> orderList;

	public final String ID;
	public final String date;

	public TransferingReceiptVO(OrganizationVO intermediateCentre,
			ArrayList<OrderVO> orderList, String ID, String date) {
		this.orderList = orderList;
		this.ID = ID;
		this.date = date;
		this.interdiateCentre = intermediateCentre;
	}
}
