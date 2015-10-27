package edu.nju.express.dataservice;

import edu.nju.express.po.CustomerPO;

public interface CustomerDataService {

    /**
     * @return id
     */
    int addCustomer(CustomerPO customerPO);

    CustomerPO getCustomerById(int id);

}
