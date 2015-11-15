package vo;

public class MockBusinessStatementReceiptVO {

	MockCollectionReceiptVO cvo;
	MockPaymentReceiptVO pvo;

	public MockBusinessStatementReceiptVO(MockCollectionReceiptVO cvo, MockPaymentReceiptVO pvo) {
		this.cvo = cvo;
		this.pvo = pvo;
	}
	
	public MockCollectionReceiptVO getMockCollectionReceipt(){
		return this.cvo;
	}
	
	public MockPaymentReceiptVO getMockPaymentReceipt(){
		return this.pvo;
	}
	
}
