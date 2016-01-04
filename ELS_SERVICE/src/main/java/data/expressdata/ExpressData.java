package data.expressdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import common.AVLTree;
import common.FileGetter;
import data.repertorydata.GoodsData;
import dataservice.expressdataservice.ExpressDataService;
import po.ExpressPO;
import po.GoodsPO;
import po.OrderPO;
import po.UserPO;
import type.OrderState;
import type.ProfessionType;

public class ExpressData extends UnicastRemoteObject implements ExpressDataService {

	private GoodsData goodsData;

	public ExpressData() throws RemoteException {
		super();
		goodsData = new GoodsData();
	}

	// 清空当日收费信息
	public boolean deleteChargeInfos(String organizationID) throws RemoteException {
		String path = "userInfo/user.ser";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<UserPO> userPOs = (ArrayList<UserPO>) in.readObject();
			in.close();

			for (UserPO i : userPOs) {
				if (i.getProfession() == ProfessionType.courier) {
					if (((ExpressPO) i).getOrganizationPO().getOrganizationID().equals(organizationID)) {
						((ExpressPO) i).setChargeCollection(new ArrayList<String>());
					}
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(userPOs);
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
			FileGetter.createFile(file);
		}

		// 新建一个Goods记录以便仓库管理员使用
		String[] leavePlaceAll = po.getSenderAddress().split(" ");
		String leavePlaceStr = leavePlaceAll[0] + leavePlaceAll[1];
		String[] destinationAll = po.getRecipientAddress().split(" ");
		String destinationStr = destinationAll[0] + destinationAll[1];
		GoodsPO newGood = new GoodsPO(po.getID(), po.getFreight() + po.getPackingExpense(), leavePlaceStr,
				destinationStr);
		goodsData.addGoods(newGood);

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
				FileGetter.createFile(orderFile);
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
		//
		// if (organizationID != null) {
		// String path = "expressInfo/" + organizationID + "-express.dat";
		// File file = FileGetter.getFile(path);
		// try {
		// ObjectInputStream in = new ObjectInputStream(new
		// FileInputStream(file));
		// ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>)
		// in.readObject();
		// in.close();
		// for (ExpressPO i : expressPOs)
		// if (i.getUserID().equals(expressID))
		// return i;
		// System.out.println("不存在此快递员，你见鬼了");
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println("快递员信息读取失败");
		// }
		// } else {
		//
		// // 查找所有营业厅文件
		// File dir = FileGetter.getFile("expressInfo");
		// File[] files = dir.listFiles();
		// for (File i : files) {
		// try {
		// ObjectInputStream in = new ObjectInputStream(new FileInputStream(i));
		// ArrayList<ExpressPO> expressPOs = (ArrayList<ExpressPO>)
		// in.readObject();
		// in.close();
		// for (ExpressPO po : expressPOs)
		// if (po.getUserID().equals(expressID))
		// return po;
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println("快递员信息读取失败");
		// }
		// }
		// System.out.println("不存在此快递员，你见鬼了");
		// }
		// return null;
		File file = FileGetter.getFile("userInfo/user.ser");
		if (!file.exists()) {
			return null;
		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<UserPO> users = (ArrayList<UserPO>) in.readObject();
			in.close();
			for (UserPO i : users) {
				if (i.getUserID().equals(expressID)) {

					return (ExpressPO) i;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<ExpressPO> getExpressInfos(String organizationID) throws RemoteException {

		File file = FileGetter.getFile("userInfo/user.ser");

		try {
			if (!file.exists())
				return new ArrayList<ExpressPO>();

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<ExpressPO> expressPOs = new ArrayList<ExpressPO>();
			ArrayList<UserPO> users = (ArrayList<UserPO>) in.readObject();
			in.close();
			for (UserPO i : users) {
				if (i.getProfession() == ProfessionType.courier && !i.getOrganization().equals("")) {
					expressPOs.add((ExpressPO) i);
				}
			}
			return expressPOs;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("快递员信息读写失败");
		}
		return new ArrayList<ExpressPO>();
	}

	public boolean updateChargeCollection(String organizationID, String expressID, ArrayList<String> chargeCollection)
			throws RemoteException {
		// 退出前如果没手动清空就询问是否清空
		String path = "userInfo/user.ser";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<UserPO> userPOs = (ArrayList<UserPO>) in.readObject();
			in.close();
			for (UserPO i : userPOs)
				if (i.getUserID().equals(expressID))
					((ExpressPO) i).setChargeCollection(chargeCollection);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(userPOs);
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

		if (orderPath == null)
			return null;

		File file = FileGetter.getFile(orderPath);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();
			in.close();

			for (OrderPO i : orderPOs)
				if (i.getID().equals(ID)) {
					return i;
				}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("订单数据文件读取失败");
			return null;
		}

		return null;

	}

	public boolean receiptOrder(String organizationID, String expressId, OrderPO po) throws RemoteException {
		boolean result = false;
		String path = "userInfo/user.ser";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<UserPO> userpo = (ArrayList<UserPO>) in.readObject();
			in.close();

			for (UserPO i : userpo) {

				if (i.getUserID().equals(expressId)) {
					((ExpressPO) i).getPendingOrders().remove(po.getID());
					((ExpressPO) i).getFinishedOrders().add(po.getID());
					result = true;
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(userpo);
			out.close();

			String time = getTime();
			String orderPath = getPath(po.getID());
			File orderFile = FileGetter.getFile(orderPath);
			if (!orderFile.exists())
				return false;
			ObjectInputStream orderIn = new ObjectInputStream(new FileInputStream(orderFile));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) orderIn.readObject();
			orderIn.close();
			for (OrderPO i : orderPOs)
				if (i.getID().equals(po.getID())) {
					i.setFinishedData(po.getFinishedData());
					i.setFinishedID(po.getFinishedID());
					i.settRecipient(po.gettRecipient());
					result = true;
				}

			ObjectOutputStream orderOut = new ObjectOutputStream(new FileOutputStream(orderFile));
			orderOut.writeObject(orderPOs);
			orderOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("快递员信息读写失败");
			result = false;
		}
		System.out.println(result);
		return result;

	}

	public boolean addSubmitOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		String path = "userInfo/user.ser";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<UserPO> userPOs = (ArrayList<UserPO>) in.readObject();
			in.close();
			for (UserPO i : userPOs) {
				if (i.getUserID().equals(expressId)) {
					((ExpressPO) i).getSubmitedOrderID().add(orderID);
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(userPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写快递员信息失败");
			return false;
		}
		return true;
	}

	public boolean addPendingOrder(String organizationID, String expressId, String orderID) throws RemoteException {
		String path = "userInfo/user.ser";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<UserPO> userPOs = (ArrayList<UserPO>) in.readObject();
			in.close();
			for (UserPO i : userPOs) {
				if (i.getUserID().equals(expressId)) {
					((ExpressPO) i).getPendingOrders().add(orderID);
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(userPOs);
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

		if (!file.exists())
			return false;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();
			in.close();
			for (OrderPO i : orderPOs) {
				if (i.getID().equals(orderID))
					i.setOrder_state(orderState);
			}
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
		if (!file.exists()) {
			FileGetter.createFile(file);
		}

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

		if (!file.exists())
			return new ArrayList<OrderPO>();
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

		if (!file.exists())
			return false;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderPO> orderPOs = (ArrayList<OrderPO>) in.readObject();
			for (OrderPO i : orderPOs) {
				if (i.getID().equals(orderID))
					i.getHistory().add(getTimes() + "  " + process);
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

		String orderPath = "orderInfo/total.dat";
		File file = FileGetter.getFile(orderPath);

		if (!file.exists()) {
			return 0;
		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			AVLTree<String, String> tree = (AVLTree<String, String>) in.readObject();

			in.close();

			return tree.getInfoByKeyword(getTime().replace("-", ""));

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
			if (!file.exists()) {
				FileGetter.createFile(file);
			}
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

	@Override
	public ArrayList<OrderPO> getDistributingOrder(String organizationName) throws RemoteException {
		ArrayList<OrderPO> result = new ArrayList<OrderPO>();
		String path = "orderInfo/";
		File dirs = FileGetter.getFile(path);
		for (File i : dirs.listFiles()) {
			if (i.isDirectory()) {
				for (File j : i.listFiles()) {
					try {
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(j));
						ArrayList<OrderPO> po = (ArrayList<OrderPO>) in.readObject();
						in.close();
						for (OrderPO p : po) {
							String[] receiverAddress = p.getRecipientAddress().split(" ");
							if (organizationName.contains(receiverAddress[0])
									&& organizationName.contains(receiverAddress[1])
									&& p.getOrder_state() == OrderState.WAITING_DISTRIBUTE) {
								result.add(p);
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return result;
	}

	private String getTimes() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fm.format(new Date());
	}

	/***************************************************************** test ***************************************************/
	public static void main(String[] args) {

	}

}
