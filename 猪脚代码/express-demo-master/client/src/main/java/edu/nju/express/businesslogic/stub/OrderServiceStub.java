package edu.nju.express.businesslogic.stub;

import edu.nju.express.businesslogic.OrderBL;
import edu.nju.express.common.ExpressType;
import edu.nju.express.vo.OrderReadVO;
import edu.nju.express.vo.OrderWriteVO;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceStub implements OrderBL {

    @Override
    public void addExpressOrder(OrderWriteVO order) {
        System.out.println("An Order has been added.");
    }

    @Override
    public List<OrderReadVO> getExpressOrders() {
        OrderReadVO orderReadVO = new OrderReadVO("0000000000", "Jay", "Zhou", "TNT",
                ExpressType.STANDARD,
                5, 10);

        List<OrderReadVO> expressOrders = new ArrayList<>();
        expressOrders.add(orderReadVO);
        return expressOrders;
    }
}
