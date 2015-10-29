package vo;

import type.ExpressType;
import type.PackType;

public class OrderVO {
	// 寄件人姓名、住址、单位、电话、手机；收件人姓名、
	// 住址、单位、电话、手机；托运货物信息（原件数、实际重量、
	// 体积、内件品名）；经济快递，标准快递，特快专递；包装费
	// （纸箱（5元）、木箱（10元）、快递袋（1元）、其它）；费
	// 用合计（自动计算，运费+包装费）；
	// 订单条形码号（10位数）；
	public final String ID;

	public final String senderName;
	public final String senderAddress;
	public final String senderOrganization;
	public final String senderPhoneNumber;
	public final String senderMobilePhoneNumber;

	public final String recipientName;
	public final String recipientAddress;
	public final String recipientOrganization;
	public final String recipientPhoneNumber;
	public final String recipientMobilePhoneNumber;

	public final int numOfGoods;
	public final String weight;
	public final String volume;
	public final String goodsName;

	public final ExpressType expressType;
	public final PackType packType;

	public final float freight;
	public final float packingExpense;

	public final String builtData;
	public final String finishedData;
	public final String finishedID;

	public OrderVO(String ID, String senderName, String senderAddress, String senderOrganization,
			String senderPhoneNumber, String senderMobilePhoneNumber, String recipientName, String recipientAddress,
			String recipientOrganization, String recipientPhoneNumber, String recipientMobilePhoneNumber,
			int numOfGoods, String weight, String volume, String goodsName, ExpressType expressType, PackType packType,
			float freight, float packingExpense, String builtData, String finishedData, String finishedID) {
		super();
		this.ID = ID;
		this.senderName = senderName;
		this.senderAddress = senderAddress;
		this.senderOrganization = senderOrganization;
		this.senderPhoneNumber = senderPhoneNumber;
		this.senderMobilePhoneNumber = senderMobilePhoneNumber;
		this.recipientName = recipientName;
		this.recipientAddress = recipientAddress;
		this.recipientOrganization = recipientOrganization;
		this.recipientPhoneNumber = recipientPhoneNumber;
		this.recipientMobilePhoneNumber = recipientMobilePhoneNumber;
		this.numOfGoods = numOfGoods;
		this.weight = weight;
		this.volume = volume;
		this.goodsName = goodsName;
		this.expressType = expressType;
		this.packType = packType;
		this.freight = freight;
		this.packingExpense = packingExpense;
		this.builtData = builtData;
		this.finishedData = finishedData;
		this.finishedID = finishedID;
	}
}
