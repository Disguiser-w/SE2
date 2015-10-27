package edu.nju.express.dataservice.factory.impl;

import edu.nju.express.dataservice.CommodityDataService;
import edu.nju.express.dataservice.CustomerDataService;
import edu.nju.express.dataservice.OrderDataService;
import edu.nju.express.dataservice.factory.DataServiceFactory;
import edu.nju.express.dataservice.impl.serializable.CommodityDSSerializableImpl;
import edu.nju.express.dataservice.impl.serializable.CustomerDSSerializableImpl;
import edu.nju.express.dataservice.impl.serializable.OrderDSSerializableImpl;

public class DataServiceSerializableFactory extends DataServiceFactory {

    @Override
    public CommodityDataService getCommodityDataService() {
        return new CommodityDSSerializableImpl();
    }

    @Override
    public CustomerDataService getCustomerDataService() {
        return new CustomerDSSerializableImpl();
    }

    @Override
    public OrderDataService getOrderDataService() {
        return new OrderDSSerializableImpl();
    }
}
