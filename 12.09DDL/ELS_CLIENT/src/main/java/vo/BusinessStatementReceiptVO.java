package vo;

import java.util.ArrayList;

public class BusinessStatementReceiptVO {
	public final String beginTime;
	public final String endTime;
	// 可以有这种包含关系吗,还是要分到每个具体属性
	public final ArrayList<CollectionReceiptVO> cvos;
	public final ArrayList<PaymentReceiptVO> pvos;

	public BusinessStatementReceiptVO(String beginTime, String endTime,
			ArrayList<CollectionReceiptVO> cvos, ArrayList<PaymentReceiptVO> pvos) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.cvos = cvos;
		this.pvos= pvos;
	}
}
