package edu.nju.express.businesslogic.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import edu.nju.express.businesslogic.OrderBL;
import edu.nju.express.dataservice.CommodityDataService;
import edu.nju.express.dataservice.CustomerDataService;
import edu.nju.express.dataservice.OrderDataService;
import edu.nju.express.dataservice.factory.DataServiceFactory;
import edu.nju.express.po.CommodityPO;
import edu.nju.express.po.CustomerPO;
import edu.nju.express.po.OrderPO;
import edu.nju.express.vo.OrderReadVO;
import edu.nju.express.vo.OrderWriteVO;

public class OrderBLImpl extends UnicastRemoteObject implements OrderBL {

    private OrderDataService orderDS;
    private CustomerDataService customerDS;
    private CommodityDataService commodityDS;

    public OrderBLImpl() throws RemoteException {
        super();

        DataServiceFactory dataServiceFactory = DataServiceFactory.createFactory();
        orderDS = dataServiceFactory.getOrderDataService();
        customerDS = dataServiceFactory.getCustomerDataService();
        commodityDS = dataServiceFactory.getCommodityDataService();
    }

    @Override
    public void addExpressOrder(OrderWriteVO orderVO) throws RemoteException {
        CustomerPO senderPO = new CustomerPO(orderVO.sender.name,
                orderVO.sender.address, orderVO.sender.company,
                orderVO.sender.telephone, orderVO.sender.mobile);
        int senderId = customerDS.addCustomer(senderPO);

        CustomerPO receiverPO = new CustomerPO(orderVO.receiver.name,
                orderVO.receiver.address, orderVO.receiver.company,
                orderVO.receiver.telephone, orderVO.receiver.mobile);
        int receiverId = customerDS.addCustomer(receiverPO);

        CommodityPO commodityPO = new CommodityPO(orderVO.commodity.nums,
                orderVO.commodity.weight, orderVO.commodity.volume,
                orderVO.commodity.name);
        int commodityId = commodityDS.addCommodity(commodityPO);

        OrderPO orderPO = new OrderPO(orderVO.barcode, senderId, receiverId, commodityId,
                orderVO.type, orderVO.packagingFee, orderVO.packagingFee);
        orderDS.addOrder(orderPO);
    }

    @Override
    public List<OrderReadVO> getExpressOrders() throws RemoteException {
        List<OrderPO> orderPOs = orderDS.getOrders();

        List<OrderReadVO> orderReadVOs = new ArrayList<>();
        for (OrderPO orderPO : orderPOs) {
            CustomerPO senderPO = customerDS.getCustomerById(orderPO.getSenderId());
            CustomerPO receiverPO = customerDS.getCustomerById(orderPO.getReceiverId());
            CommodityPO commodityPO = commodityDS.getCommodityById(orderPO.getCommodityId());

            OrderReadVO orderReadVO = new OrderReadVO(orderPO.getBarcode(),
                    senderPO.getName(), receiverPO.getName(), commodityPO.getName(),
                    orderPO.getType(), orderPO.getPackagingFee(), orderPO.getTotalPrice());
            orderReadVOs.add(orderReadVO);
        }
        return orderReadVOs;
    }
}
