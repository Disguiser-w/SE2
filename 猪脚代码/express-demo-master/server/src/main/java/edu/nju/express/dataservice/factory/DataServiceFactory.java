package edu.nju.express.dataservice.factory;

import edu.nju.express.dataservice.CommodityDataService;
import edu.nju.express.dataservice.CustomerDataService;
import edu.nju.express.dataservice.OrderDataService;
import edu.nju.express.dataservice.factory.impl.DataServiceSerializableFactory;

public abstract class DataServiceFactory {

    public abstract CommodityDataService getCommodityDataService();

    public abstract CustomerDataService getCustomerDataService();

    public abstract OrderDataService getOrderDataService();

    protected DataServiceFactory(){}

    public static DataServiceFactory createFactory(){
    	return new DataServiceSerializableFactory();
    }

}
