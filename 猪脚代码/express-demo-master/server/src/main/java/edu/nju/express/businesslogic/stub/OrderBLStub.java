package edu.nju.express.businesslogic.stub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import edu.nju.express.common.ExpressType;
import edu.nju.express.businesslogic.OrderBL;
import edu.nju.express.vo.OrderReadVO;
import edu.nju.express.vo.OrderWriteVO;

public class OrderBLStub extends UnicastRemoteObject implements OrderBL {


    public OrderBLStub() throws RemoteException {
        super();
    }

    @Override
    public void addExpressOrder(OrderWriteVO orderVO) throws RemoteException {
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
