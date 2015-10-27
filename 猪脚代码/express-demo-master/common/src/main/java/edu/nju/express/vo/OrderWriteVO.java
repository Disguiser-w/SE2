package edu.nju.express.vo;

import edu.nju.express.common.ExpressType;

import java.io.Serializable;

public class OrderWriteVO implements Serializable {
    private static final long serialVersionUID = -8838707921979816041L;

    public final String barcode;

    public final CustomerVO sender;
    public final CustomerVO receiver;
    public final CommodityVO commodity;

    public final ExpressType type;

    public final double packagingFee;
    public final double totalPrice;

    public OrderWriteVO(String barcode, CustomerVO sender,
                        CustomerVO receiver, CommodityVO commodity,
                        ExpressType type, double packagingFee, double totalPrice) {
        this.barcode = barcode;
        this.sender = sender;
        this.receiver = receiver;
        this.commodity = commodity;
        this.type = type;
        this.packagingFee = packagingFee;
        this.totalPrice = totalPrice;
    }
}
