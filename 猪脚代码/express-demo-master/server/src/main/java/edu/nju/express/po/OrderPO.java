package edu.nju.express.po;

import edu.nju.express.common.ExpressType;

import java.io.Serializable;

public class OrderPO implements Serializable {

    private static final long serialVersionUID = 554535963678753905L;

    private String barcode;

    private int senderId;
    private int receiverId;
    private int commodityId;

    private ExpressType type;

    private double packagingFee;
    private double totalPrice;

    public OrderPO(String barcode,
                   int senderId, int receiverId, int commodityId,
                   ExpressType type, double packagingFee, double totalPrice) {
        this.barcode = barcode;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.commodityId = commodityId;
        this.type = type;
        this.packagingFee = packagingFee;
        this.totalPrice = totalPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public ExpressType getType() {
        return type;
    }

    public double getPackagingFee() {
        return packagingFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
