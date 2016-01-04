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
import data.managedata.OrganizationData;
import dataservice.businessdataservice.BusinessDataService;
import po.BusinessPO;
import po.DistributeReceiptPO;
import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import po.VehiclePO;
import type.OrganizationType;
import type.ReceiptState;
import type.Sexuality;

public class BusinessData extends UnicastRemoteObject implements BusinessDataService {
	private static final long serialVersionUID = 1189857824410417884L;

	public BusinessData() throws RemoteException {
	}

	public BusinessPO getBusinessInfo(String organizationID, String businessID) throws RemoteException {
		File file = FileGetter.getFile("userInfo/user.ser");
		if (!file.exists()) {
			return null;
		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<UserPO> users = (ArrayList<UserPO>) in.readObject();
			in.close();
			for (UserPO i : users) {
				if (i.getUserID().equals(businessID)) {
					return (BusinessPO) i;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public VehiclePO getVehicleInfo(String organizationID, String vehicleID) throws RemoteException {

		String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
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
	public int getNumOfOrderReceipt(String organizationID) throws RemoteException {
		String time = getTime();
		String path = "orderAcceptInfo/" + organizationID + "/" + time + "-orderAccept.dat";
		File file = FileGetter.getFile(path);
		try {
			ArrayList<OrderAcceptReceiptPO> orderAcceptReceiptPOs = null;
			if (!file.exists()) {
				return 0;
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				orderAcceptReceiptPOs = (ArrayList<OrderAcceptReceiptPO>) in.readObject();

				in.close();
			}
			return orderAcceptReceiptPOs.size();
		} catch (

		Exception e) {
			e.printStackTrace();
			System.out.println("读写收货单信息失败");
		}

		return 0;
	}

	public boolean addReceipt(String organizationID, OrderAcceptReceiptPO po) throws RemoteException {
		String time = getTime();
		String path = "orderAcceptInfo/" + organizationID + "/" + time + "-orderAccept.dat";
		File file = FileGetter.getFile(path);
		try {
			ArrayList<OrderAcceptReceiptPO> orderAcceptReceiptPOs = null;
			if (!file.exists()) {
				FileGetter.createFile(file);
				orderAcceptReceiptPOs = new ArrayList<OrderAcceptReceiptPO>();
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				orderAcceptReceiptPOs = (ArrayList<OrderAcceptReceiptPO>) in.readObject();

				in.close();
			}
			orderAcceptReceiptPOs.add(po);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(orderAcceptReceiptPOs);
			out.close();

		} catch (

		Exception e)

		{
			e.printStackTrace();
			System.out.println("读写收货单信息失败");
			return false;
		}

		return true;

	}

	public ArrayList<VehiclePO> getVehicleInfos(String organizationID) throws RemoteException {
		if (organizationID != null) {
			String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
			File file = FileGetter.getFile(path);

			if (!file.exists()) {
				return new ArrayList<VehiclePO>();
			}

			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
				in.close();

				return vehiclePOs;

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("读写车辆信息失败");
			}

			return null;
		} else {
			String path = "vehicleInfo";
			File[] files = FileGetter.getFile(path).listFiles();

			ArrayList<VehiclePO> vehiclePOs = new ArrayList<VehiclePO>();
			for (File i : files) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(i));
					ArrayList<VehiclePO> vehiclePO = (ArrayList<VehiclePO>) in.readObject();
					in.close();

					for (VehiclePO po : vehiclePO) {
						vehiclePOs.add(po);
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("读写车辆信息失败");
				}

			}
			return vehiclePOs;
		}

	}

	public int getNumOfEnVechileReceipt(String organizationID) throws RemoteException {
		String time = getTime();
		String path = "enVehicleInfo/" + organizationID + "/" + time + "-enVehicle.dat";
		File file = FileGetter.getFile(path);
		try {

			if (!file.exists()) {
				return 0;
			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<EnVehicleReceiptPO> enVehicleReceiptPOs = (ArrayList<EnVehicleReceiptPO>) in.readObject();
			in.close();

			return enVehicleReceiptPOs.size();

		} catch (Exception e)

		{
			e.printStackTrace();
			System.out.println("读写装车单信息失败");
		}

		return 0;

	}

	public boolean addEnVehicleReceipt(String organizationID, ArrayList<EnVehicleReceiptPO> pos)
			throws RemoteException {
		String time = getTime();
		String path = "enVehicleInfo/" + organizationID + "/" + time + "-enVehicle.dat";
		File file = FileGetter.getFile(path);
		try {

			ArrayList<EnVehicleReceiptPO> enVehicleReceiptPOs = null;
			if (!file.exists()) {
				FileGetter.createFile(file);
				enVehicleReceiptPOs = new ArrayList<EnVehicleReceiptPO>();
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				enVehicleReceiptPOs = (ArrayList<EnVehicleReceiptPO>) in.readObject();
				in.close();
			}
			for (EnVehicleReceiptPO i : pos)
				enVehicleReceiptPOs.add(i);

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
			ArrayList<VehiclePO> vehiclePOs = null;
			if (!file.exists()) {
				FileGetter.createFile(file);
				vehiclePOs = new ArrayList<VehiclePO>();
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
				in.close();
			}
			vehiclePOs.add(po);

			DriverPO dpo = po.getDriver();

			modifyDriver(organizationID, dpo);

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
			for (int i = 0; i < vehiclePOs.size(); i++)
				if (vehiclePOs.get(i).getID().equals(po.getID())) {

					DriverPO dpo = vehiclePOs.get(i).getDriver();

					dpo.setUsing(false);
					modifyDriver(organizationID, dpo);

					vehiclePOs.remove(i);
					break;
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

	public boolean modifyVehicle(String organizationID, VehiclePO po) throws RemoteException {

		String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
			in.close();

			for (VehiclePO i : vehiclePOs)
				if (i.getID().equals(po.getID())) {
					// 修改司机isUsing属性

					DriverPO dpo = i.getDriver();
					dpo.setUsing(false);
					modifyDriver(organizationID, dpo);

					DriverPO vpo = po.getDriver();
					vpo.setUsing(true);
					modifyDriver(organizationID, vpo);

					i.setEngineNumber(po.getEngineNumber());
					i.setLicensePlateNumber(po.getLicensePlateNumber());
					i.setLowNumberPlate(po.getLowNumberPlate());
					i.setBuyTime(po.getBuyTime());
					i.setServiceTime(po.getServiceTime());
					i.setDestination(po.getDestination());
					i.setDestinationCity(po.getDestinationCity());
					i.setLocal(po.getLocal());
					i.setDriver(po.getDriver());

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
		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<DriverPO>();
		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

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

			if (!file.exists()) {
				FileGetter.createFile(file);
			}
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
		if (!file.exists())
			return 0;
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

	public ArrayList<GatheringReceiptPO> getGatheringReceiptByHallID(String organizationID) throws RemoteException {

		ArrayList<GatheringReceiptPO> pos = new ArrayList<GatheringReceiptPO>();

		File dir = FileGetter.getFile("gatheringInfo/" + organizationID);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			for (File i : dir.listFiles()) {

				ObjectInputStream in = new ObjectInputStream(new FileInputStream(i));
				GatheringReceiptPO po = (GatheringReceiptPO) in.readObject();
				in.close();
				pos.add(po);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取收款汇总文件失败");
			return null;
		}

		return pos;
	}

	public ArrayList<GatheringReceiptPO> getGatheringReceiptByBoth(String organizationID, String time)
			throws RemoteException {
		ArrayList<GatheringReceiptPO> pos = new ArrayList<GatheringReceiptPO>();

		File file = FileGetter.getFile("gatheringInfo/" + organizationID + "/" + time + "-gathering.dat");
		try {
			if (!file.exists()) {
				return new ArrayList<GatheringReceiptPO>();
			}

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			GatheringReceiptPO po = (GatheringReceiptPO) in.readObject();
			in.close();
			pos.add(po);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取收款汇总文件失败");
			return null;
		}
		return pos;
	}

	/**
	 * Lili在这！！日期格式 yyyy-MM-dd
	 */
	public ArrayList<GatheringReceiptPO> getGatheringReceipt(String time) throws RemoteException {

		ArrayList<GatheringReceiptPO> pos = new ArrayList<GatheringReceiptPO>();

		File dir = FileGetter.getFile("gatheringInfo/");
		if (!dir.exists())
			return pos;
		for (File i : dir.listFiles()) {
			File[] dirs = i.listFiles();
			for (int j = 0; j < dirs.length; j++) {

				if (dirs[j].getName().contains(time)) {
					try {
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirs[j]));
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

		}

		return pos;
	}

	public int getNumOfOrderDistributeReceipt(String organizationID) throws RemoteException {
		String time = getTime();
		String path = "distributeInfo/" + organizationID + "/" + time + "-distribute.dat";
		File file = FileGetter.getFile(path);
		try {
			ArrayList<DistributeReceiptPO> distributeReceiptPOs = null;
			if (!file.exists()) {
				return 0;
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

				distributeReceiptPOs = (ArrayList<DistributeReceiptPO>) in.readObject();
				in.close();

			}
			return distributeReceiptPOs.size();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写派件单信息失败");
		}

		return 0;
	}

	public boolean addDistributeReceipt(String organizationID, DistributeReceiptPO po) throws RemoteException {
		String time = getTime();
		String path = "distributeInfo/" + organizationID + "/" + time + "-distribute.dat";
		File file = FileGetter.getFile(path);
		try {
			ArrayList<DistributeReceiptPO> distributeReceiptPOs = null;
			if (!file.exists()) {
				FileGetter.createFile(file);
				distributeReceiptPOs = new ArrayList<DistributeReceiptPO>();
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

				distributeReceiptPOs = (ArrayList<DistributeReceiptPO>) in.readObject();
				in.close();
			}
			distributeReceiptPOs.add(po);

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
		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			ArrayList<DriverPO> driverPOs = (ArrayList<DriverPO>) in.readObject();
			in.close();
			for (DriverPO i : driverPOs) {
				if (i.getID().equals(ID))
					return i;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写司机信息失败");
		}

		return null;
	}

	public boolean addDriver(String organizationID, DriverPO po) throws RemoteException {

		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		try {
			ArrayList<DriverPO> driverPOs = null;

			if (!file.exists()) {
				FileGetter.createFile(file);
				driverPOs = new ArrayList<DriverPO>();
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				driverPOs = (ArrayList<DriverPO>) in.readObject();
				in.close();
			}

			driverPOs.add(po);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(driverPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写司机信息失败");
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
			for (int i = 0; i < driverPOs.size(); i++)
				if (driverPOs.get(i).getID().equals(po.getID())) {
					driverPOs.remove(i);
					i--;
				}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(driverPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写司机信息失败");
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

					i.setName(po.getName());
					i.setDateOfBirth(po.getDateOfBirth());
					i.setIdCardNumber(po.getIdCardNumber());
					i.setPhoneNumber(po.getPhoneNumber());
					i.setRegistrationDeadline(po.getRegistrationDeadline());
					i.setTime(po.getTime());
					i.setSexuality(po.getSexuality());
					i.setUsing(po.isUsing());

					break;
				}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(driverPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("司机信息读写失败");
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
		File file = FileGetter.getFile("gatheringInfo");
		if (!file.exists())
			return new ArrayList<GatheringReceiptPO>();
		ArrayList<GatheringReceiptPO> gatheringReceiptPO = new ArrayList<GatheringReceiptPO>();
		File[] dirs = file.listFiles();
		try {
			for (File i : dirs) {
				File[] files = i.listFiles();
				for (File j : files) {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(j));
					GatheringReceiptPO po = (GatheringReceiptPO) in.readObject();
					in.close();
					if (po.getReceiptState() == ReceiptState.SUBMIT)
						gatheringReceiptPO.add(po);

				}
			}

			return gatheringReceiptPO;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<DistributeReceiptPO> getSubmittedDistributeReceiptInfo() throws RemoteException {
		File file = FileGetter.getFile("distributeInfo");
		if (!file.exists())
			return new ArrayList<DistributeReceiptPO>();
		ArrayList<DistributeReceiptPO> distributeReceiptPO = new ArrayList<DistributeReceiptPO>();
		File[] dirs = file.listFiles();
		try {
			for (File i : dirs) {
				File[] files = i.listFiles();
				for (File j : files) {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(j));
					ArrayList<DistributeReceiptPO> pos = (ArrayList<DistributeReceiptPO>) in.readObject();
					in.close();

					for (DistributeReceiptPO onePO : pos) {
						if (onePO.getReceiptState() == ReceiptState.SUBMIT)
							distributeReceiptPO.add(onePO);
					}
				}
			}

			return distributeReceiptPO;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<EnVehicleReceiptPO> getSubmittedEnVehicleReceiptInfo() throws RemoteException {
		File file = FileGetter.getFile("enVehicleInfo");
		if (!file.exists())
			return new ArrayList<EnVehicleReceiptPO>();
		ArrayList<EnVehicleReceiptPO> enVehicleReceiptPO = new ArrayList<EnVehicleReceiptPO>();
		File[] dirs = file.listFiles();
		try {
			for (File i : dirs) {
				File[] files = i.listFiles();
				for (File j : files) {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(j));
					ArrayList<EnVehicleReceiptPO> pos = (ArrayList<EnVehicleReceiptPO>) in.readObject();
					in.close();

					for (EnVehicleReceiptPO onePO : pos) {
						if (onePO.getReceiptState() == ReceiptState.SUBMIT)
							enVehicleReceiptPO.add(onePO);
					}
				}
			}

			return enVehicleReceiptPO;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<OrderAcceptReceiptPO> getSubmittedOrderAcceptReceiptInfo() throws RemoteException {
		File file = FileGetter.getFile("orderAcceptInfo");
		if (!file.exists())
			return new ArrayList<OrderAcceptReceiptPO>();
		ArrayList<OrderAcceptReceiptPO> orderAcceptReceiptPO = new ArrayList<OrderAcceptReceiptPO>();
		File[] dirs = file.listFiles();
		try {
			for (File i : dirs) {
				File[] files = i.listFiles();
				for (File j : files) {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(j));
					ArrayList<OrderAcceptReceiptPO> pos = (ArrayList<OrderAcceptReceiptPO>) in.readObject();
					in.close();

					for (OrderAcceptReceiptPO onePO : pos) {
						if (onePO.getReceiptState() == ReceiptState.SUBMIT)
							orderAcceptReceiptPO.add(onePO);

					}
				}
			}

			return orderAcceptReceiptPO;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void saveDistributeReceiptInfo(DistributeReceiptPO po) throws RemoteException {
		String organizationID = po.getID().split("-")[1];
		String time = po.getTime();
		File file = FileGetter.getFile("distributeInfo/" + organizationID + "/" + time + "-distribute.dat");
		if (!file.exists())
			return;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<DistributeReceiptPO> pos = (ArrayList<DistributeReceiptPO>) in.readObject();
			for (DistributeReceiptPO i : pos) {
				if (po.getID().equals(i.getID())) {
					i.setReceiptState(ReceiptState.APPROVE);
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveOrderAcceptReceiptInfo(OrderAcceptReceiptPO po) throws RemoteException {
		String organizationID = po.getReceiptID().split("-")[1];
		String time = po.getTime();
		File file = FileGetter.getFile("orderAcceptInfo/" + organizationID + "/" + time + "-orderAccept.dat");
		if (!file.exists())
			return;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<OrderAcceptReceiptPO> pos = (ArrayList<OrderAcceptReceiptPO>) in.readObject();
			for (OrderAcceptReceiptPO i : pos) {
				if (po.getReceiptID().equals(i.getReceiptID())) {
					i.setReceiptState(ReceiptState.APPROVE);
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveEnVehicleReceiptInfo(EnVehicleReceiptPO po) throws RemoteException {
		String organizationID = po.getReceiptID().split("-")[1];
		String time = po.getTime();
		File file = FileGetter.getFile("enVehicleInfo/" + organizationID + "/" + time + "-enVehicle.dat");
		if (!file.exists())
			return;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<EnVehicleReceiptPO> pos = (ArrayList<EnVehicleReceiptPO>) in.readObject();
			for (EnVehicleReceiptPO i : pos) {
				if (po.getReceiptID().equals(i.getReceiptID())) {
					i.setReceiptState(ReceiptState.APPROVE);
				}
			}

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveGatheringReceiptInfo(GatheringReceiptPO po) throws RemoteException {
		String organizationID = po.getReceiptID().split("-")[1];
		String time = po.getTime();
		File file = FileGetter.getFile("gatheringInfo/" + organizationID + "/" + time + "-gathering.dat");
		if (!file.exists())
			return;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			GatheringReceiptPO pos = (GatheringReceiptPO) in.readObject();
			pos.setReceiptState(ReceiptState.APPROVE);
			pos.setReceiptState(po.getReceiptState());

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean addDriverTime(String organizationID, String driverID) throws RemoteException {
		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);
		try {
			ArrayList<DriverPO> driverPOs = null;

			if (!file.exists()) {
				return false;
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				driverPOs = (ArrayList<DriverPO>) in.readObject();
				in.close();
			}

			for (DriverPO i : driverPOs)
				if (i.getID().equals(driverID))
					i.setTime(i.getTime() + 1);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(driverPOs);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写车辆司机失败");
			return false;
		}

		return true;
	}

	public int getNumOfVehicles(String organizationID) throws RemoteException {
		String path = "vehicleInfo/" + organizationID + "-vehicle.dat";
		File file = FileGetter.getFile(path);

		if (!file.exists())
			return 0;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			ArrayList<VehiclePO> vehiclePOs = (ArrayList<VehiclePO>) in.readObject();
			in.close();

			return vehiclePOs.size();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("车辆信息读写失败");
		}

		return 0;
	}

	public int getNumOfDrivers(String organizationID) throws RemoteException {
		String path = "driverInfo/" + organizationID + "-driver.dat";
		File file = FileGetter.getFile(path);

		if (!file.exists())
			return 0;

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			ArrayList<DriverPO> driverPOs = (ArrayList<DriverPO>) in.readObject();
			in.close();

			return driverPOs.size();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("车辆信息读写失败");
		}

		return 0;
	}

	public ArrayList<OrganizationPO> getOrganizationInfos() throws RemoteException {
		File file = FileGetter.getFile("organizationInfo/organization.ser");

		if (!file.exists()) {
			return new ArrayList<OrganizationPO>();

		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			ArrayList<OrganizationPO> organizationPOs = (ArrayList<OrganizationPO>) (new OrganizationData())
					.showAllOrganizations();
			in.close();

			return organizationPOs;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写司机信息失败");
		}

		return null;
	}

	public static void main(String[] args) {
		try {
			File file = FileGetter.getFile("driverInfo/025-0-driver.dat");
			if (!file.exists()) {
				FileGetter.createFile(file);
			}
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
					new RepertoryPO("pig", "wo"));
			ArrayList<DriverPO> vpo = new ArrayList<DriverPO>();

			//
			// private String ID;
			// private String name;
			// private String DateOfBirth;
			// private String IdCardNumber;
			// private String phoneNumber;
			// private OrganizationPO vehicleOrganization;
			// private Sexuality sexuality;
			// private String registrationDeadline;
			// private int time;
			// private boolean isUsing;
			for (int i = 0; i < 50; i++) {
				DriverPO dpo = new DriverPO(i + "", "pig" + i, "2014-12-14", "123456789123456789", "12345678900", po,
						Sexuality.MALE, "3", 5);
				vpo.add(dpo);
			}
			out.writeObject(vpo);
			out.close();

		} catch (Exception e) {

		}
	}

}
