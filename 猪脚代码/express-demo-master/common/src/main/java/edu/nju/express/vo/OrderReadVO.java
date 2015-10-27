package edu.nju.express.vo;

import edu.nju.express.common.ExpressType;

import java.io.Serializable;

public class OrderReadVO implements Serializable {
    private static final long serialVersionUID = -5301578828182567025L;

    public final String barcode;

    public final String senderName;
    public final String receiverName;
    public final String commodityName;

    public final ExpressType type;

    public final double packagingFee;
    public final double totalPrice;

    public OrderReadVO(String barcode, String senderName, String receiverName,
                       String commodityName, ExpressType type,
                       double packagingFee, double totalPrice) {
        this.barcode = barcode;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.commodityName = commodityName;
        this.type = type;
        this.packagingFee = packagingFee;
        this.totalPrice = totalPrice;
    }
}
