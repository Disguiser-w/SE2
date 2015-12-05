package data.businessdata;

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

import common.FileGetter;
import dataservice.businessdataservice.BusinessDataService;
import po.BusinessPO;
import po.DistributeReceiptPO;
import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.OrganizationPO;
import po.RepertoryPO;
import po.VehiclePO;
import type.OrganizationType;

public class BusinessData extends UnicastRemoteObject implements BusinessDataService {
	public BusinessData() throws RemoteException {
	}

	public BusinessPO getBusinessInfo(String organizationID, String businessID) throws RemoteException {
		if (organizationID != null) {
			String path = "businessInfo/" + organizationID + "-business.dat";
			File file = FileGetter.getFile(path);
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				ArrayList<BusinessPO> businessPOs = (ArrayList<BusinessPO>) in.readObject();
				in.close();
				for (BusinessPO i : businessPOs)
					if (i.getID().equals(businessID))
						return i;
				System.out.println("不存在此营业厅业务员，你见鬼了");

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("营业厅业务员信息读取失败");
			}
		} else {

			// 查找所有营业厅文件
			File dir = FileGetter.getFile("businessInfo");
			File[] files = dir.listFiles();
			for (File i : files) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(i));
					ArrayList<BusinessPO> businessPOs = (ArrayList<BusinessPO>) in.readObject();
					in.close();
					
					for (BusinessPO po : businessPOs)
						if (po.getID().equals(businessID))
							return po;

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("营业厅业务员信息读取失败");
				}
			}
			System.out.println("不存在此营业厅业务员，你见鬼了");
		}
		return null;
	}

	public VehiclePO getVehicleInfo(String organizationID, String vehicleID) throws RemoteException {
		String path = "vehilceInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
			in.close();
			for (VehiclePO i : vehiclePOs) {
				if (i.getID().equals(vehicleID))
					return i;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
		}

		return null;

	}

	// OrderAcceptReceipt
	public boolean addReceipt(String organizationID, OrderAcceptReceiptPO po) throws RemoteException {
		String time = getTime();
		String path = "orderAcceptInfo/" + organizationID + "/" + time + "-orderAccept.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<OrderAcceptReceiptPO> orderAcceptReceiptPOs = (ArrayList<OrderAcceptReceiptPO>) in.readObject();
			orderAcceptReceiptPOs.add(po);
			in.close();

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(orderAcceptReceiptPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写收货单信息失败");
			return false;
		}

		return true;

	}

	public ArrayList<VehiclePO> getVehicleInfos(String organizationID) throws RemoteException {
		String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
			in.close();

			return vehiclePOs;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
		}

		return null;
	}

	public boolean addEnVehicleReceipt(String organizationID, ArrayList<EnVehicleReceiptPO> pos)
			throws RemoteException {
		String time = getTime();
		String path = "enVehicleInfo/" + organizationID + "/" + time + "-enVehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<EnVehicleReceiptPO> enVehicleReceiptPOs = (ArrayList<EnVehicleReceiptPO>) in.readObject();
			for (EnVehicleReceiptPO i : pos)
				enVehicleReceiptPOs.add(i);
			in.close();

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(enVehicleReceiptPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写装车单信息失败");
			return false;
		}

		return true;
	}

	public boolean addVehicle(String organizationID, VehiclePO po) throws RemoteException {
		String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
			in.close();
			vehiclePOs.add(po);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(vehiclePOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
			return false;
		}

		return true;

	}

	public boolean deleteVehicle(String organizationID, VehiclePO po) throws RemoteException {
		String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
			in.close();
			int len = vehiclePOs.size();
			for (VehiclePO i : vehiclePOs)
				if (i.getID().equals(po.getID()))
					vehiclePOs.remove(i);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(vehiclePOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
			return false;
		}

		return true;

	}

	public boolean modifyVehicle(String organizationID, VehiclePO po) throws RemoteException {

		String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
			in.close();
			int len = vehiclePOs.size();
			for (VehiclePO i : vehiclePOs)
				if (i.getID().equals(po.getID())) {
					vehiclePOs.remove(i);
					vehiclePOs.add(po);
				}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(vehiclePOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
			return false;
		}
		return true;
	}

	public ArrayList<DriverPO> getDriverInfos(String organizationID) throws RemoteException {
		String path = "driverInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<DriverPO> driverPOs = (ArrayList<DriverPO>) in.readObject();
			in.close();

			return driverPOs;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
		}

		return null;
	}

	public boolean addGatheringReceipt(String organizationID, GatheringReceiptPO grp) throws RemoteException {
		String time = getTime();
		String path = "gatheringInfo/" + organizationID + "/" + time + "-gathering.dat";
		File file = FileGetter.getFile(path);
		try {

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(grp);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写收款汇总单信息失败");
			return false;
		}

		return true;
	}

	public int getNumOfOrderAcceptReceipt(String organizationID) throws RemoteException {
		String time = getTime();
		String path = "orderAcceptInfo/" + organizationID + "/" + time + "-orderAccept.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<OrderAcceptReceiptPO> orderAcceptReceiptPOs = (ArrayList<OrderAcceptReceiptPO>) in.readObject();
			in.close();
			return orderAcceptReceiptPOs.size();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写收货单信息失败");

		}
		return 0;
	}

	/**
	 * Lili在这！！日期格式 yyyy-MM-dd
	 */
	public ArrayList<GatheringReceiptPO> getGatheringReceipt(String time) throws RemoteException {
		ArrayList<GatheringReceiptPO> pos = new ArrayList<GatheringReceiptPO>();

		File dir = FileGetter.getFile("gathering/");
		for (File i : dir.listFiles()) {
			File[] dirs = i.listFiles();
			for (File j : dirs) {
				if (j.getName().contains(time))

					try {
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(j));
						GatheringReceiptPO po = (GatheringReceiptPO) in.readObject();
						in.close();
						pos.add(po);

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("读取收款汇总文件失败");
						return null;
					}

			}

		}

		return pos;
	}

	public boolean addDistributeReceipt(String organizationID, DistributeReceiptPO po) throws RemoteException {
		String time = getTime();
		String path = "distribute/" + organizationID + "/" + time + "-distribute.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<DistributeReceiptPO> distributeReceiptPOs = (ArrayList<DistributeReceiptPO>) in.readObject();
			distributeReceiptPOs.add(po);
			in.close();

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(distributeReceiptPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写派件单信息失败");
			return false;
		}

		return true;
	}

	public DriverPO getDriverInfo(String organizationID, String ID) throws RemoteException {
		String path = "driver/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<DriverPO> driverPOs = (ArrayList<DriverPO>) in.readObject();
			in.close();
			for (DriverPO i : driverPOs) {
				if (i.getID().equals(ID))
					return i;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
		}

		return null;
	}

	public boolean addDriver(String organizationID, DriverPO po) throws RemoteException {
		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<DriverPO> driverPOs = (ArrayList<DriverPO>) in.readObject();
			in.close();
			driverPOs.add(po);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(driverPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
			return false;
		}

		return true;
	}

	public boolean deleteDriver(String organizationID, DriverPO po) throws RemoteException {
		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<DriverPO> driverPOs = (ArrayList<DriverPO>) in.readObject();
			in.close();
			int len = driverPOs.size();
			for (DriverPO i : driverPOs)
				if (i.getID().equals(po.getID()))
					driverPOs.remove(i);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(driverPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
			return false;
		}

		return true;
	}

	public boolean modifyDriver(String organizationID, DriverPO po) throws RemoteException {
		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<DriverPO> driverPOs = (ArrayList<DriverPO>) in.readObject();
			in.close();
			int len = driverPOs.size();
			for (DriverPO i : driverPOs)
				if (i.getID().equals(po.getID())) {
					driverPOs.remove(i);
					driverPOs.add(po);
				}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(driverPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆信息失败");
			return false;
		}
		return true;
	}

	private String getTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}

	public ArrayList<GatheringReceiptPO> getSubmittedGatheringReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<DistributeReceiptPO> getSubmittedDistributeReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<EnVehicleReceiptPO> getSubmittedEnVehicleReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<OrderAcceptReceiptPO> getSubmittedOrderAcceptReceiptInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveDistributeReceiptInfo(DistributeReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void saveOrderAcceptReceiptInfo(OrderAcceptReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void saveEnVehicleReceiptInfo(EnVehicleReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void saveGatheringReceiptInfo(GatheringReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

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
			ArrayList<BusinessPO> bpo = new ArrayList<BusinessPO>();
			BusinessPO b = new BusinessPO("doge", "YYT-00001", "2年", po);
			bpo.add(b);

			out.writeObject(bpo);
			out.close();
		} catch (Exception e) {

		}
	}
}
