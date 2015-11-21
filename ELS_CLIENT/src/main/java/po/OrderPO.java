package po;

import type.ExpressType;
import type.PackType;
import type.TransferingState;

public class OrderPO {
	// 寄件人姓名、住址、单位、电话、手机；收件人姓名、
	// 住址、单位、电话、手机；托运货物信息（原件数、实际重量、
	// 体积、内件品名）；经济快递，标准快递，特快专递；包装费
	// （纸箱（5元）、木箱（10元）、快递袋（1元）、其它）；费
	// 用合计（自动计算，运费+包装费）；
	// 订单条形码号（10位数）；
	
	// 装车状态（等待中，已装车）
	private String ID;

	private String senderName;
	private String senderAddress;
	private String senderOrganization;
	private String senderPhoneNumber;
	private String senderMobilePhoneNumber;

	private String recipientName;
	private String recipientAddress;
	private String recipientOrganization;
	private String recipientPhoneNumber;
	private String recipientMobilePhoneNumber;

	private int numOfGoods;
	private String weight;
	private String volume;
	private String goodsName;

	private ExpressType expressType;
	private PackType packType;

	private float freight;
	private float packingExpense;

	private String builtData;
	private String finishedData;
	private String finishedID;
	
	private TransferingState transfer_state;

	public OrderPO() {

	}

	public OrderPO(String ID, String senderName, String senderAddress, String senderOrganization,
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

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderOrganization() {
		return senderOrganization;
	}

	public void setSenderOrganization(String senderOrganization) {
		this.senderOrganization = senderOrganization;
	}

	public String getSenderPhoneNumber() {
		return senderPhoneNumber;
	}

	public void setSenderPhoneNumber(String senderPhoneNumber) {
		this.senderPhoneNumber = senderPhoneNumber;
	}

	public String getSenderMobilePhoneNumber() {
		return senderMobilePhoneNumber;
	}

	public void setSenderMobilePhoneNumber(String senderMobilePhoneNumber) {
		this.senderMobilePhoneNumber = senderMobilePhoneNumber;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getRecipientOrganization() {
		return recipientOrganization;
	}

	public void setRecipientOrganization(String recipientOrganization) {
		this.recipientOrganization = recipientOrganization;
	}

	public String getRecipientPhoneNumber() {
		return recipientPhoneNumber;
	}

	public void setRecipientPhoneNumber(String recipientPhoneNumber) {
		this.recipientPhoneNumber = recipientPhoneNumber;
	}

	public String getRecipientMobilePhoneNumber() {
		return recipientMobilePhoneNumber;
	}

	public void setRecipientMobilePhoneNumber(String recipientMobilePhoneNumber) {
		this.recipientMobilePhoneNumber = recipientMobilePhoneNumber;
	}

	public int getNumOfGoods() {
		return numOfGoods;
	}

	public void setNumOfGoods(int numOfGoods) {
		this.numOfGoods = numOfGoods;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public ExpressType getExpressType() {
		return expressType;
	}

	public void setExpressType(ExpressType expressType) {
		this.expressType = expressType;
	}

	public PackType getPackType() {
		return packType;
	}

	public void setPackType(PackType packType) {
		this.packType = packType;
	}

	public float getFreight() {
		return freight;
	}

	public void setFreight(float freight) {
		this.freight = freight;
	}

	public float getPackingExpense() {
		return packingExpense;
	}

	public void setPackingExpense(float packingExpense) {
		this.packingExpense = packingExpense;
	}

	public String getBuiltData() {
		return builtData;
	}

	public void setBuiltData(String builtData) {
		this.builtData = builtData;
	}

	public String getFinishedData() {
		return finishedData;
	}

	public void setFinishedData(String finishedData) {
		this.finishedData = finishedData;
	}

	public String getFinishedID() {
		return finishedID;
	}

	public void setFinishedID(String finishedID) {
		this.finishedID = finishedID;
	}
}
