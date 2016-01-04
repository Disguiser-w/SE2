package data.intermediatedata;

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

import po.EnIntermediateReceiptPO;
import po.FarePO;
import po.PlanePO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import po.UserPO;
import type.OperationState;
import type.ReceiptState;

import common.FileGetter;

import dataservice.intermediatedataservice.IntermediateDataService;

@SuppressWarnings("serial")
public class IntermediateData extends UnicastRemoteObject implements
		IntermediateDataService {

	public IntermediateData() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	public UserPO getIntermediateInfo(String intermediate_ID)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "userInfo/user.ser";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<UserPO> intermediatePOList = (ArrayList<UserPO>) in
					.readObject();
			in.close();
			for (UserPO intermediate : intermediatePOList) {
				if (intermediate.getUserID().equals(intermediate_ID)) {
					return intermediate;
				}
			}

			throw new Exception("找不到该ID的中转中心业务员！");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写中转中心业务员信息失败！");
		}

		return null;
	}

	public ArrayList<PlanePO> getPlaneList(String organization_ID)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/planeList/" + organization_ID
				+ "-plane.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<PlanePO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<PlanePO> planeList = (ArrayList<PlanePO>) in.readObject();
			in.close();
			return planeList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写飞机信息失败！");
		}

		return null;
	}

	public ArrayList<TrainPO> getTrainList(String organization_ID)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/trainList/" + organization_ID
				+ "-train.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<TrainPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<TrainPO> trainList = (ArrayList<TrainPO>) in.readObject();
			in.close();
			return trainList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写火车信息失败！");
		}

		return null;
	}

	public ArrayList<TruckPO> getTruckList(String organization_ID)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/truckList/" + organization_ID
				+ "-truck.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<TruckPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<TruckPO> truckList = (ArrayList<TruckPO>) in.readObject();
			in.close();
			return truckList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写汽车信息失败！");
		}

		return null;
	}

	public OperationState savePlaneList(String organization_ID,
			ArrayList<PlanePO> planeList) throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/planeList/" + organization_ID
				+ "-plane.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			FileGetter.createFile(file);
		}

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(planeList);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存飞机信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState saveTrainList(String organization_ID,
			ArrayList<TrainPO> trainList) throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/trainList/" + organization_ID
				+ "-train.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			FileGetter.createFile(file);
		}

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(trainList);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存火车信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState saveTruckList(String organization_ID,
			ArrayList<TruckPO> truckList) throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/truckList/" + organization_ID
				+ "-truck.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			FileGetter.createFile(file);
		}

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(truckList);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存汽车信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public TransferingReceiptPO getTransferingReceiptInfo(
			String organization_ID, String date) throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/transferingReceipt/"
				+ organization_ID + "-" + date + "-transferingReceipt.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return null;
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			ArrayList<TransferingReceiptPO> transferingReceiptList = (ArrayList<TransferingReceiptPO>) in
					.readObject();
			in.close();

			return transferingReceiptList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写中转中心到达单信息失败！");
		}

		return null;
	}

	public OperationState saveTransferingReceiptInfo(
			TransferingReceiptPO transferingReceipt, String organization_ID)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String date = getDate();
		String path = "intermediateCentreInfo/transferingReceipt/"
				+ organization_ID + "-" + date + "-transferingReceipt.dat";
		File file = FileGetter.getFile(path);
		ArrayList<TransferingReceiptPO> ope = new ArrayList<TransferingReceiptPO>();
		ope.add(transferingReceipt);
		try {
			if (!file.exists()) {
				FileGetter.createFile(file);
			}
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(ope);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存中转中心到达单信息失败！");
		}

		path = "receiptInfo/transferingReceiptInfo.dat";
		file = FileGetter.getFile(path);

		try {
			if (!file.exists()) {
				FileGetter.createFile(file);
			}
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file, true));
			out.writeObject(ope);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存中转中心到达单信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public EnIntermediateReceiptPO getEnIntermediateReceiptInfo(
			String organization_ID, String EnIntermediateReceipt_ID, String date)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/enIntermediateReceipt/"
				+ organization_ID + "-" + date + "-enIntermediateReceipt.dat";
		File file = FileGetter.getFile(path);

		if (!file.exists()) {
			return null;
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<EnIntermediateReceiptPO> enIntermediateRecepitList = (ArrayList<EnIntermediateReceiptPO>) in
					.readObject();
			in.close();
			for (EnIntermediateReceiptPO enIntermediateReceipt : enIntermediateRecepitList) {
				if (enIntermediateReceipt.getID().equals(
						EnIntermediateReceipt_ID))
					return enIntermediateReceipt;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取装车单信息失败！");
		}
		return null;
	}

	public OperationState saveEnIntermediateReceiptInfo(
			ArrayList<EnIntermediateReceiptPO> enList, String organization_ID)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String date = getDate();
		String path = "intermediateCentreInfo/enIntermediateReceipt/"
				+ organization_ID + "-" + date + "-enIntermediateReceipt.dat";
		File file = FileGetter.getFile(path);

		try {
			if (!file.exists()) {
				FileGetter.createFile(file);
			}

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(enList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存装车单信息失败！");
		}

		path = "receiptInfo/enIntermediateReceiptInfo.dat";
		file = FileGetter.getFile(path);

		try {
			if (!file.exists()) {
				FileGetter.createFile(file);
			}

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file, true));
			out.writeObject(enList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存装车单信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public ArrayList<FarePO> getFareInfo(String organization_ID, String date)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo/fare/" + organization_ID + "-"
				+ date + "-fare.dat";
		File file = FileGetter.getFile(path);

		if (!file.exists()) {
			return new ArrayList<FarePO>();
		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<FarePO> fareList = (ArrayList<FarePO>) in.readObject();
			in.close();
			return fareList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取运费成本信息失败！");
		}
		return null;
	}

	public OperationState saveFareInfo(String organization_ID, FarePO fare)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String date = getDate();
		String path = "intermediateCentreInfo/fare/" + organization_ID + "-"
				+ date + "-fare.dat";
		File file = FileGetter.getFile(path);

		ArrayList<FarePO> fareList = getFareInfo(organization_ID, date);
		System.out.println(fareList.size());
		fareList.add(fare);
		try {
			if (!file.exists()) {
				FileGetter.createFile(file);
			}

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(fareList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存运费成本信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public ArrayList<TransferingReceiptPO> getSubmittedTransferingReceiptInfo()
			throws RemoteException {
		String path = "receiptInfo/transferingReceiptInfo.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<TransferingReceiptPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<TransferingReceiptPO> transferingReceiptList = (ArrayList<TransferingReceiptPO>) in
					.readObject();
			in.close();
			ArrayList<TransferingReceiptPO> temp = new ArrayList<TransferingReceiptPO>();
			for (TransferingReceiptPO transferingReceipt : transferingReceiptList) {
				if (transferingReceipt.getReceiptState() == ReceiptState.SUBMIT) {
					temp.add(transferingReceipt);
				}
			}

			return temp;

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("读取中转中心到达单信息失败");
		}

		return null;
	}

	public OperationState saveSubmittedTransferingReceiptInfo(
			ArrayList<TransferingReceiptPO> transferingReceiptList)
			throws RemoteException {
		String path = "receiptInfo/transferingReceiptInfo.dat";
		File file = FileGetter.getFile(path);

		try {
			if (!file.exists()) {
				FileGetter.createFile(file);
			}

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));

			out.writeObject(transferingReceiptList);
			out.close();

			return OperationState.SUCCEED_OPERATION;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("保存中转中心到达单信息失败");
		}

		return OperationState.FAIL_OPERATION;
	}

	public OperationState saveSubmittedTransferingReceipt(
			TransferingReceiptPO transferingReceipt) {
		ArrayList<TransferingReceiptPO> transferingReceiptList = null;
		try {
			transferingReceiptList = getSubmittedTransferingReceiptInfo();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for (TransferingReceiptPO ope : transferingReceiptList) {
			if (ope.getID().equals(transferingReceipt.getID()))
				transferingReceiptList.set(transferingReceiptList.indexOf(ope),
						transferingReceipt);
		}
		try {
			saveSubmittedTransferingReceiptInfo(transferingReceiptList);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public ArrayList<EnIntermediateReceiptPO> getSubmittedEnIntermediateReceiptInfo()
			throws RemoteException {
		String path = "receiptInfo/enIntermediateReceiptInfo.dat";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<EnIntermediateReceiptPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<EnIntermediateReceiptPO> enIntermediateReceiptList = (ArrayList<EnIntermediateReceiptPO>) in
					.readObject();
			in.close();

			ArrayList<EnIntermediateReceiptPO> temp = new ArrayList<EnIntermediateReceiptPO>();
			for (EnIntermediateReceiptPO enIntermediateReceipt : enIntermediateReceiptList) {
				if (enIntermediateReceipt.getReceiptState() == ReceiptState.SUBMIT)
					temp.add(enIntermediateReceipt);
			}

			return temp;

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("读取装车单信息失败");
		}

		return null;
	}

	public OperationState saveSubmittedEnIntermediateReceiptInfo(
			ArrayList<EnIntermediateReceiptPO> enIntermeidiateReceiptList)
			throws RemoteException {
		String path = "receiptInfo/enIntermediateReceiptInfo.dat";
		File file = FileGetter.getFile(path);

		try {
			if (!file.exists()) {
				FileGetter.createFile(file);
			}

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));

			out.writeObject(enIntermeidiateReceiptList);
			out.close();

			return OperationState.SUCCEED_OPERATION;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("保存装车单信息失败");
		}

		return OperationState.FAIL_OPERATION;
	}

	public OperationState saveSubmittedEnIntermediateReceipt(
			EnIntermediateReceiptPO receipt) {
		ArrayList<EnIntermediateReceiptPO> receiptList = null;
		try {
			receiptList = getSubmittedEnIntermediateReceiptInfo();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for (EnIntermediateReceiptPO ope : receiptList) {
			if (ope.getID().equals(receipt.getID()))
				receiptList.set(receiptList.indexOf(ope), receipt);
		}
		try {
			saveSubmittedEnIntermediateReceiptInfo(receiptList);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return OperationState.SUCCEED_OPERATION;
	}

	private String getDate() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}

	/**************************** test **********************************************************/
	// public static void main(String[] args) {
	// // File file = FileGetter.getFile("intermediateInfo");
	// File file = new File(
	// "info/intermediateCentreInfo/intermediate/ZZZX-00185-intermediate.dat");
	// try {
	// if (!file.exists()) {
	// file.getParentFile().mkdirs();
	// file.createNewFile();
	// }
	// ObjectOutputStream out = new ObjectOutputStream(
	// new FileOutputStream(file));
	// OrganizationPO organization = new OrganizationPO(
	// OrganizationType.intermediateCenter, "141250", "软攻打作业");
	// UserPO intermediate = new UserPO(organization,
	// "痛苦的业务员", "ZZZX-00185");
	//
	// RepertoryPO repertory = new RepertoryPO("坑爹", "Lizi");
	//
	// organization.setRepertory(repertory);
	//
	// ArrayList<UserPO> list = new ArrayList<UserPO>();
	// list.add(intermediate);
	//
	// out.writeObject(list);
	// out.close();
	// } catch (Exception e) {
	// // TODO 自动生成的 catch 块
	// e.printStackTrace();
	// }
	//
	// }

}
