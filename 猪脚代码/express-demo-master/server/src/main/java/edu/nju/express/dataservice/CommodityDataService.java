package edu.nju.express.dataservice;

import edu.nju.express.po.CommodityPO;

public interface CommodityDataService {

    /**
     * @return commodity id;
     */
    int addCommodity(CommodityPO commodityPO);

    CommodityPO getCommodityById(int id);

}
