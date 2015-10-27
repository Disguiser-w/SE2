package edu.nju.express.vo;

import java.io.Serializable;

public class CommodityVO implements Serializable {
    private static final long serialVersionUID = -437522043597232322L;

    public final int nums;
    public final int weight;
    public final int volume;
    public final String name;

    public CommodityVO(int nums, int weight, int volume, String name) {
        this.nums = nums;
        this.weight = weight;
        this.volume = volume;
        this.name = name;
    }
}
