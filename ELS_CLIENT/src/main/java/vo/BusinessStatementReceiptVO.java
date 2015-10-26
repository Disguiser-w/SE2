package vo;

public class BusinessStatementReceiptVO {
	String beginTime;
	String endTime;
	//可以有这种包含关系吗,还是要分到每个具体属性
	CollectionReceiptVO cvo;
	PaymentReceiptVO pvo;
	public BusinessStatementReceiptVO(){
		this(null,null,null,null);
	}
	public BusinessStatementReceiptVO(String a,String b,	CollectionReceiptVO c,PaymentReceiptVO d){
		beginTime=a;
		endTime=b;
		cvo=c;
		pvo=d;
	}
	
	public String getBeginTime(){
		return beginTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public CollectionReceiptVO getCollectionVO(){
		return cvo;	
	}
	
	public PaymentReceiptVO getPaymentVO(){
		return pvo;	
	}

}
