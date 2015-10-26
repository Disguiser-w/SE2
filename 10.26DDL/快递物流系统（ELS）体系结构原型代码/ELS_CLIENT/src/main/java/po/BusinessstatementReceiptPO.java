package po;

public class BusinessstatementReceiptPO {
	String beginTime;
	String endTime;
	//可以有这种包含关系吗,还是要分到每个具体属性
	CollectionReceiptPO cpo;
	PaymentReceiptPO ppo;
	public BusinessstatementReceiptPO(){
		this(null,null,null,null);
	}
	public BusinessstatementReceiptPO(String a,String b,	CollectionReceiptPO c,PaymentReceiptPO d){
		beginTime=a;
		endTime=b;
		cpo=c;
		ppo=d;
	}
	
	public String getBeginTime(){
		return beginTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public CollectionReceiptPO getCollectionPO(){
		return cpo;	
	}
	
	public PaymentReceiptPO getPaymentPO(){
		return ppo;	
	}
	
	public void setBeginTime(String a){
		beginTime=a;
	}
	
	public void setEndTime(String b){
		endTime=b;
	}

}
