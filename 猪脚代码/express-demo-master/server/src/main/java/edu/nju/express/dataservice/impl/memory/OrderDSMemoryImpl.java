package edu.nju.express.dataservice.impl.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nju.express.dataservice.OrderDataService;
import edu.nju.express.po.OrderPO;

public class OrderDSMemoryImpl implements OrderDataService {

    private final List<OrderPO> orders;

    public OrderDSMemoryImpl() {
        orders = new ArrayList<>();
    }

    @Override
    public void addOrder(OrderPO orderPO) {
        orders.add(orderPO);
    }

    @Override
    public List<OrderPO> getOrders() {
        return new ArrayList<>(orders);
    }
}
