package vo;

public class BusinessStatementReceiptVO {
	public final String beginTime;
	public final String endTime;
	// 可以有这种包含关系吗,还是要分到每个具体属性
	public final CollectionReceiptVO cvo;
	public final PaymentReceiptVO pvo;

	public BusinessStatementReceiptVO(String beginTime, String endTime,
			CollectionReceiptVO cvo, PaymentReceiptVO pvo) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.cvo = cvo;
		this.pvo = pvo;
	}
}
