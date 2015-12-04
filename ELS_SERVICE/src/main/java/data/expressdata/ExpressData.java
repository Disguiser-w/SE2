package data.expressdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import common.AVLTree;
import common.FileGetter;
import dataservice.expressdataservice.ExpressDataService;
import po.ExpressPO;
import po.OrderPO;
import po.OrganizationPO;
import po.RepertoryPO;
import type.OrderState;
import type.OrganizationType;

public class ExpressData extends UnicastRemoteObject implements ExpressDataService {
	public ExpressData() throws RemoteException {
		super();
	}

	// 清空当日收费信息
	public boolean deleteChargeInfos(String organizationID) throws RemoteException {
		String path = "expressInfo/" + organizationID + "-express.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
			in.close();

			for (ExpressPO i : expressPOs) {
				i.setChargeCollection(new ArrayList<String>());
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(expressPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("快递员信息读写失败");
		}
		return false;
	}

	// 根据OrganizationID添加订单到今日订单文件中,时间自动生成，在total.dat(使用AVL树，（存放键值对，OrderID为键,路径为值）)中添加一个副本，来根据ID快速查找Organization和time
	public boolean addOrder(OrderPO po, String organizationID) throws RemoteException {

		// 定位文件路径

		String path = "orderInfo/total.dat";
		File file = FileGetter.getFile(path);

		AVLTree<String, String> t = null;
		if (file.exists())
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				t = (AVLTree<String, String>) in.readObject();
				in.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("订单路径文件读取失败");
				return false;
			}
		else// 首次生成
		{
			t = new AVLTree<String, String>();
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 获取今日时间
		String time = getTime();

		String orderPath = "orderInfo/" + organizationID + "/" + time + "-order.dat";

		if (!t.add(po.getID(), orderPath)) {
			System.out.println("订单已存在");
			return false;
		}

		try {
			// 覆盖之前记录
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(t);
			out.close();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("订单路径文件写入失败");
			return false;
		} // 至此完成路径存储工作

		// 开始增加订单到营业厅目录下
		File orderFile = FileGetter.getFile(orderPath);

		try {
			ArrayList<OrderPO> orderPOs = null;
			if (!orderFile.exists()) {
				orderFile.getParentFile().mkdirs();
				orderFile.createNewFile();
				orderPOs = new ArrayList<OrderPO>();
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(orderFile));
				orderPOs = (ArrayList<OrderPO>) in.readObject();
				in.close();

			}

			if (!orderPOs.add(po)) {
				System.out.println("无法将订单文件添加到列表中");
				return false;
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(orderFile));
			out.writeObject(orderPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读写失败");
			return false;
		}

		return true;
	}

	public ExpressPO getExpressInfo(String organizationID, String expressID) throws RemoteException {

		if (organizationID != null) {
			String path = "expressInfo/" + organizationID + "-express.dat";
			File file = FileGetter.getFile(path);
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
				in.close();
				for (ExpressPO i : expressPOs)
					if (i.getID().equals(expressID))
						return i;
				System.out.println("不存在此快递员，你见鬼了");

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("快递员信息读取失败");
			}
		} else {

			// 查找所有营业厅文件
			File dir = FileGetter.getFile("expressInfo");
			File[] files = dir.listFiles();
			for (File i : files) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(i));
					ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
					in.close();
					for (ExpressPO po : expressPOs)
						if (po.getID().equals(expressID))
							return po;

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("快递员信息读取失败");
				}
			}
			System.out.println("不存在此快递员，你见鬼了");
		}
		return null;
	}

	public ArrayList<ExpressPO> getExpressInfos(String organizationID) throws RemoteException {
		String path = "expressInfo/" + organizationID + "-express.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
			in.close();
			return expressPOs;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("快递员信息读写失败");
		}
		return null;
	}

	public boolean updateChargeCollection(String organizationID, String expressID, ArrayList<String> chargeCollection)
			throws RemoteException {
		// 退出前如果没手动清空就询问是否清空
		String path = "expressInfo/" + organizationID + "-express.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
			in.close();
			for (ExpressPO i : expressPOs)
				if (i.getID().equals(expressID))
					i.setChargeCollection(chargeCollection);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(expressPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("快递员信息读写失败，你这是在作死！");
			return false;
		}

		return true;
	}

	public OrderPO find(String ID) throws RemoteException {
		String orderPath = getPath(ID);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(orderPath));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();
			in.close();

			for (OrderPO i : orderPOs)
				if (i.getID().equals(ID))
					return i;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读取失败");
			return null;
		}

		return null;

	}

	public boolean receiptOrder(String organizationID, String expressId, OrderPO po) throws RemoteException {
		String path = "expressInfo/" + organizationID + "-express.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
			in.close();
			for (ExpressPO i : expressPOs) {
				if (i.getID().equals(expressId)) {
					i.getPendingOrders().remove(po.getID());
					i.getFinishedOrders().add(po.getID());
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(expressPOs);
			out.close();

			String time = getTime();
			String orderPath = "orderInfo/" + organizationID + "/" + time + "-order.dat";
			File orderFile = FileGetter.getFile(orderPath);

			ObjectInputStream orderIn = new ObjectInputStream(new FileInputStream(orderPath));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) orderIn.readObject();
			in.close();
			for (OrderPO i : orderPOs)
				if (i.getID().equals(po.getID())) {
					i.setFinishedData(po.getFinishedData());
					i.setFinishedID(po.getFinishedID());
					i.settRecipient(po.gettRecipient());
				}

			ObjectOutputStream orderOut = new ObjectOutputStream(new FileOutputStream(orderPath));
			out.writeObject(orderPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("快递员信息读写失败");
			return false;
		}
		return true;

	}

	public boolean addSubmitOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		String path = "expressInfo/" + organizationID + "-express.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
			in.close();
			for (ExpressPO i : expressPOs) {
				if (i.getID().equals(expressId)) {
					i.getSubmitedOrderID().add(orderID);
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(expressPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写快递员信息失败");
			return false;
		}
		return true;
	}

	public boolean addPendingOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		String path = "expressInfo/" + organizationID + "-express.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>) in.readObject();
			in.close();
			for (ExpressPO i : expressPOs) {
				if (i.getID().equals(expressId)) {
					i.getPendingOrders().add(orderID);
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(expressPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写快递员信息失败");
			return false;
		}
		return true;
	}

	public boolean changeState(OrderState orderState, String orderID) throws RemoteException {
		String path = getPath(orderID);
		File file = FileGetter.getFile(path);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();
			in.close();
			for (OrderPO i : orderPOs)
				if (i.getID().equals(orderID))
					i.setOrder_state(orderState);
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(orderPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读写失败");
			return false;
		}

		return true;
	}

	public boolean addDistributingOrder(ArrayList<OrderPO> orderPOs, String organizationID) throws RemoteException {

		String time = getTime();
		File file = FileGetter.getFile("orderInfo/" + organizationID + "/" + time + "-order.dat");

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> totalOrderPOs = (ArrayList<OrderPO>) in.readObject();
			in.close();
			for (OrderPO i : totalOrderPOs)
				orderPOs.add(i);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(totalOrderPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读写失败");
			return false;
		}

		return true;
	}

	public ArrayList<OrderPO> getOrderInfosByTime(String organizationID, String time) throws RemoteException {

		String path = "orderInfo/" + organizationID + "/" + time + "-order.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();
			in.close();

			return orderPOs;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读写失败");
		}

		return null;
	}

	// 注意organizationID为null
	public boolean addHistory(String process, String organizationID, String orderID) throws RemoteException {

		String path = getPath(orderID);
		File file = FileGetter.getFile(path);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();
			for (OrderPO i : orderPOs) {
				if (i.getID().equals(orderID))
					i.getHistory().add(process);
			}
			in.close();

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(orderPOs);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读写失败");
			return false;
		}

		return true;
	}

	// 获得当日订单数
	public int getOrderNum(String organizationID) throws RemoteException {
		// TODO Auto-generated method stub
		String time = getTime();
		String orderPath = "orderInfo/" + organizationID + "/" + time + "-order.dat";
		File file = FileGetter.getFile(orderPath);

		if (!file.exists()) {
			System.out.println("当日订单数为0");
			return 0;

		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();

			in.close();

			return orderPOs.size();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读写失败");
		}

		return -1;
	}

	// 获得当前日期
	private String getTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}

	// 获取路径
	private String getPath(String id) {
		String path = "orderInfo/total.dat";
		File file = FileGetter.getFile(path);
		AVLTree<String, String> t = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			t = (AVLTree<String, String>) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单路径文件读取失败");
			return null;
		}
		return t.find(id);
	}

	/***************************************************************** test ***************************************************/
	public static void main(String[] args) {
		try {

			File file = FileGetter.getFile("businessInfo/025001-business.dat");
			if (!file.exists()) {

				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
					new RepertoryPO("pig", "wo"));
			ArrayList<OrganizationPO> pos = new ArrayList<OrganizationPO>();
			pos.add(po);

			out.writeObject(pos);
			out.close();

			File file2 = FileGetter.getFile("expressInfo/025001-express.dat");
			if (!file2.exists()) {
				file2.getParentFile().mkdirs();
				file2.createNewFile();
			}
			ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(file2));
			ExpressPO epo = new ExpressPO("狗剩", "KDY-00001", "2.5", new ArrayList<String>(), po,
					new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());

			ArrayList<ExpressPO> epos = new ArrayList<ExpressPO>();
			epos.add(epo);
			out2.writeObject(epos);
			out2.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
