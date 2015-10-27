package edu.nju.express.businesslogic;

import java.rmi.Remote;
import java.util.List;

import edu.nju.express.vo.OrderReadVO;
import edu.nju.express.vo.OrderWriteVO;

public interface OrderBL extends Remote {

    void addExpressOrder(OrderWriteVO orderVO);

    List<OrderReadVO> getExpressOrders();
}
