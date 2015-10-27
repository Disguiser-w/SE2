package edu.nju.express.dataservice.factory.impl;

import edu.nju.express.dataservice.CommodityDataService;
import edu.nju.express.dataservice.CustomerDataService;
import edu.nju.express.dataservice.OrderDataService;
import edu.nju.express.dataservice.factory.DataServiceFactory;
import edu.nju.express.dataservice.impl.memory.CommodityDSMemoryImpl;
import edu.nju.express.dataservice.impl.memory.CustomerDSMemoryImpl;
import edu.nju.express.dataservice.impl.memory.OrderDSMemoryImpl;

public class DataServiceMemoryFactory extends DataServiceFactory {

    @Override
    public CommodityDataService getCommodityDataService() {
        return new CommodityDSMemoryImpl();
    }

    @Override
    public CustomerDataService getCustomerDataService() {
        return new CustomerDSMemoryImpl();
    }

    @Override
    public OrderDataService getOrderDataService() {
        return new OrderDSMemoryImpl();
    }
}
