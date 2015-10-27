package edu.nju.express.dataservice.impl.serializable;

import edu.nju.express.dataservice.CommodityDataService;
import edu.nju.express.po.CommodityPO;
import edu.nju.express.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommodityDSSerializableImpl implements CommodityDataService {

    @Override
    public synchronized int addCommodity(CommodityPO commodityPO) {
        Logger.info("add commodity...");
        try {
            List<CommodityPO> commodityPOs = getCommodities();
            int id = commodityPOs.size();
            commodityPO.setId(id);
            commodityPOs.add(commodityPO);

            File file = SerializableFileHelper.getCommodityFile();
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
                os.writeObject(commodityPOs);
                Logger.info("success with id=" + id);
                return id;
            }
        } catch (IOException e) {
            Logger.error("fail");
            Logger.error(e);
        }

        return -1;
    }

    @Override
    public CommodityPO getCommodityById(int id) {
        try {
            List<CommodityPO> commodityPOs = getCommodities();
            for (CommodityPO commodityPO : commodityPOs) {
                if (id == commodityPO.getId()) {
                    return commodityPO;
                }
            }
        } catch (IOException e) {
            Logger.error(e);
        }
        return null;
    }

    private List<CommodityPO> getCommodities() throws IOException {
        File file = new File(SerializableFileHelper.DIRECTORY_PATH,
                SerializableFileHelper.COMMODITY_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<CommodityPO>) is.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }

    }
}
