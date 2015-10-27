package edu.nju.express.dataservice.impl.serializable;

import edu.nju.express.dataservice.OrderDataService;
import edu.nju.express.po.OrderPO;
import edu.nju.express.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDSSerializableImpl implements OrderDataService {

    @Override
    public synchronized void addOrder(OrderPO orderPO) {
        Logger.info("add order...");
        try {
            List<OrderPO> orderPOs = getOrders();
            orderPOs.add(orderPO);

            File file = SerializableFileHelper.getOrderFile();
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
                os.writeObject(orderPOs);
            }
            Logger.info("success");
        } catch (IOException e) {
            Logger.error("fail");
            Logger.error(e);
        }
    }

    @Override
    public List<OrderPO> getOrders() {
        File file = new File(
                SerializableFileHelper.DIRECTORY_PATH,
                SerializableFileHelper.ORDER_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<OrderPO>) is.readObject();
        } catch (Exception e) {
            Logger.error(e);
            return new ArrayList<>();
        }

    }

}
