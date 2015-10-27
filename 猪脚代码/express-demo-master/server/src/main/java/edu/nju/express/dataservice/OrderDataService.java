package edu.nju.express.dataservice;

import java.util.List;

import edu.nju.express.po.OrderPO;

public interface OrderDataService {

    void addOrder(OrderPO orderPO);

    List<OrderPO> getOrders();
}
