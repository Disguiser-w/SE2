package edu.nju.express.dataservice.impl.memory;

import edu.nju.express.dataservice.CommodityDataService;
import edu.nju.express.po.CommodityPO;

import java.util.HashMap;
import java.util.Map;

public class CommodityDSMemoryImpl implements CommodityDataService {

    private final Map<Integer, CommodityPO> commodityPOs;

    public CommodityDSMemoryImpl() {
        commodityPOs = new HashMap<>();
    }

    @Override
    public synchronized int addCommodity(CommodityPO commodityPO) {
        int newId = commodityPOs.size();
        commodityPO.setId(newId);
        commodityPOs.put(newId, commodityPO);
        return newId;
    }

    @Override
    public CommodityPO getCommodityById(int id) {
        return commodityPOs.get(id);
    }

}
