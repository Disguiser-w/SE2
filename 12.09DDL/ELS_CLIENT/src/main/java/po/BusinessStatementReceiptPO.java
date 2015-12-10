package po;

import java.util.ArrayList;



public class BusinessStatementReceiptPO {
	String beginTime;
	String endTime;
	
	ArrayList<CollectionReceiptPO> cpos;
	ArrayList<PaymentReceiptPO> ppos;

	public BusinessStatementReceiptPO() {
		this(null, null, null, null);
	}

	public BusinessStatementReceiptPO(String beginTime, String endTime, ArrayList<CollectionReceiptPO> cpos,
			ArrayList<PaymentReceiptPO> ppos) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.cpos = cpos;
		this.ppos = ppos;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public ArrayList<CollectionReceiptPO> getCollectionPOs() {
		return cpos;
	}

	public ArrayList<PaymentReceiptPO> getPaymentPOs() {
		return ppos;
	}

	public void setBeginTime(String a) {
		beginTime = a;
	}

	public void setEndTime(String b) {
		endTime = b;
	}

}
