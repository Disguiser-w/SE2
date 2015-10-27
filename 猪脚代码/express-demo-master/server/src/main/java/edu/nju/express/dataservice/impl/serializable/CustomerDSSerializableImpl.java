package edu.nju.express.dataservice.impl.serializable;

import edu.nju.express.dataservice.CustomerDataService;
import edu.nju.express.po.CustomerPO;
import edu.nju.express.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDSSerializableImpl implements CustomerDataService {

    @Override
    public synchronized int addCustomer(CustomerPO customerPO) {
        Logger.info("add customer...");
        try {
            List<CustomerPO> customerPOs = getCustomers();
            int id = customerPOs.size();
            customerPO.setId(id);
            customerPOs.add(customerPO);

            File file = SerializableFileHelper.getCustomerFile();
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
                os.writeObject(customerPOs);
            }
            Logger.info("success with id=" + id);
            return id;
        } catch (IOException e) {
            Logger.error("fail");
            Logger.error(e);
        }

        return -1;
    }

    @Override
    public CustomerPO getCustomerById(int id) {
        try {
            List<CustomerPO> customerPOs = getCustomers();
            for (CustomerPO customerPO : customerPOs) {
                if (id == customerPO.getId()) {
                    return customerPO;
                }
            }
        } catch (IOException e) {
            Logger.error(e);
        }
        return null;
    }

    private List<CustomerPO> getCustomers() throws IOException {
        File file = new File(SerializableFileHelper.DIRECTORY_PATH,
                SerializableFileHelper.CUSTOMER_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<CustomerPO>) is.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}
