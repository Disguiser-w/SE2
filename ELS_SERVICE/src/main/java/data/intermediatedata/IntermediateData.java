package data.intermediatedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.Operation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.EnIntermediateReceiptPO;
import po.FarePO;
import po.IntermediatePO;
import po.PlanePO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import type.OperationState;
import type.ReceiptState;
import common.FileGetter;
import dataservice.intermediatedataservice.IntermediateDataService;

public class IntermediateData implements IntermediateDataService {

	public IntermediatePO getIntermediateInfo(String organization_ID,
			String intermediate_ID) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID
				+ "/intermediate.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<IntermediatePO> intermediatePOList = (ArrayList<IntermediatePO>) in
					.readObject();
			in.close();
			for (IntermediatePO intermediate : intermediatePOList) {
				if (intermediate.getID().equals(intermediate_ID))
					return intermediate;
			}

			throw new Exception("找不到该ID的中转中心业务员！");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写中转中心业务员信息失败！");
		}

		return null;
	}

	public ArrayList<PlanePO> getPlaneList(String organization_ID) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID
				+ "/plane.dat";
		File file = FileGetter.getFile(path);
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

	public ArrayList<TrainPO> getTrainList(String organization_ID) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID
				+ "/train.dat";
		File file = FileGetter.getFile(path);
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

	public ArrayList<TruckPO> getTruckList(String organization_ID) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID
				+ "/truck.dat";
		File file = FileGetter.getFile(path);
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
		String path = "intermediateCentreInfo-" + organization_ID
				+ "/plane.dat";
		File file = FileGetter.getFile(path);

		for (PlanePO plane : planeList) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(file));
				out.writeObject(plane);
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("保存飞机信息失败！");
			}
		}
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState saveTrainList(String organization_ID,
			ArrayList<TrainPO> trainList) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID
				+ "/train.dat";
		File file = FileGetter.getFile(path);

		for (TrainPO train : trainList) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(file));
				out.writeObject(train);
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("保存火车信息失败！");
			}
		}
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState saveTruckList(String organization_ID,
			ArrayList<TruckPO> truckList) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID
				+ "/truck.dat";
		File file = FileGetter.getFile(path);

		for (TruckPO truck : truckList) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(file));
				out.writeObject(truck);
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("保存汽车信息失败！");
			}
		}
		return OperationState.SUCCEED_OPERATION;
	}

	public TransferingReceiptPO getTransferingReceiptInfo(
			String organization_ID, String date, String ID) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID + "/" + date
				+ "-transferingReceipt.dat";
		File file = FileGetter.getFile(path);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<TransferingReceiptPO> transferingReceiptPOList = (ArrayList<TransferingReceiptPO>) in
					.readObject();
			in.close();
			for (TransferingReceiptPO transferingReceipt : transferingReceiptPOList) {
				if (transferingReceipt.getID().equals(ID))
					return transferingReceipt;
			}

			throw new Exception("找不到该ID的中转中心到达单！");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读写中转中心到达单信息失败！");
		}

		return null;
	}

	public OperationState saveTransferingReceiptInfo(
			TransferingReceiptPO transferingReceipt, String organization_ID) {
		// TODO 自动生成的方法存根
		String date = getDate();
		String path = "intermediateCentreInfo-" + organization_ID + "/" + date
				+ "-transferingReceipt.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(transferingReceipt);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存中转中心到达单信息失败！");
		}

		path = "transferingReceiptInfo.dat";
		file = FileGetter.getFile(path);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file, true));
			out.writeObject(transferingReceipt);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存中转中心到达单信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public EnIntermediateReceiptPO getEnIntermediateReceiptInfo(
			String organization_ID, String EnIntermediateReceipt_ID, String date) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID + "/" + date
				+ "-enIntermediateReceipt.dat";
		File file = FileGetter.getFile(path);

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
			EnIntermediateReceiptPO enIntermediateReceipt,
			String organization_ID) {
		// TODO 自动生成的方法存根
		String date = getDate();
		String path = "intermediateCentreInfo-" + organization_ID + "/" + date
				+ "-enIntermediateReceipt.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(enIntermediateReceipt);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存装车单信息失败！");
		}

		path = "enIntermediateReceiptInfo.dat";
		file = FileGetter.getFile(path);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file, true));
			out.writeObject(enIntermediateReceipt);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存装车单信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public FarePO getFareInfo(String organization_ID, String fare_ID,
			String date) {
		// TODO 自动生成的方法存根
		String path = "intermediateCentreInfo-" + organization_ID + "/" + date
				+ "-fare.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<FarePO> fareList = (ArrayList<FarePO>) in.readObject();
			in.close();
			for (FarePO fare : fareList) {
				if (fare.getID().equals(fare_ID))
					return fare;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取运费成本信息失败！");
		}
		return null;
	}

	public OperationState saveFareInfo(String organization_ID, FarePO fare) {
		// TODO 自动生成的方法存根
		String date = getDate();
		String path = "intermediateCentreInfo-" + organization_ID + "/" + date
				+ "-fare.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(fare);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保存运费成本信息失败！");
		}

		return OperationState.SUCCEED_OPERATION;
	}

	public ArrayList<TransferingReceiptPO> getSubmittedTransferingReceiptInfo() {
		String path = "transferingReceiptInfo.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<TransferingReceiptPO> transferingReceiptList = (ArrayList<TransferingReceiptPO>) in
					.readObject();
			in.close();

			for (TransferingReceiptPO transferingReceipt : transferingReceiptList) {
				if (transferingReceipt.getReceiptState() != ReceiptState.SUBMIT)
					transferingReceiptList.remove(transferingReceipt);
			}

			return transferingReceiptList;

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("读取中转中心到达单信息失败");
		}

		return null;
	}

	public OperationState saveSubmittedTransferingReceiptInfo(
			ArrayList<TransferingReceiptPO> transferingReceiptList) {
		String path = "transferingReceiptInfo.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));

			for (TransferingReceiptPO transferingReceipt : transferingReceiptList)
				out.writeObject(transferingReceipt);
			out.close();

			return OperationState.SUCCEED_OPERATION;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("保存中转中心到达单信息失败");
		}

		return OperationState.FAIL_OPERATION;
	}

	public ArrayList<EnIntermediateReceiptPO> getSubmittedEnIntermediateReceiptInfo() {
		String path = "enIntermediateReceiptInfo.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			@SuppressWarnings("unchecked")
			ArrayList<EnIntermediateReceiptPO> enIntermediateReceiptList = (ArrayList<EnIntermediateReceiptPO>) in
					.readObject();
			in.close();

			for (EnIntermediateReceiptPO enIntermediateReceipt : enIntermediateReceiptList) {
				if (enIntermediateReceipt.getReceiptState() != ReceiptState.SUBMIT)
					enIntermediateReceiptList.remove(enIntermediateReceipt);
			}

			return enIntermediateReceiptList;

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("读取装车单信息失败");
		}

		return null;
	}

	public OperationState saveSubmittedEnIntermediateReceiptInfo(
			ArrayList<EnIntermediateReceiptPO> enIntermeidiateReceiptList) {
		String path = "enIntermediateReceiptInfo.dat";
		File file = FileGetter.getFile(path);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));

			for (EnIntermediateReceiptPO enIntermediateReceipt : enIntermeidiateReceiptList)
				out.writeObject(enIntermediateReceipt);
			out.close();

			return OperationState.SUCCEED_OPERATION;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("保存装车单信息失败");
		}

		return OperationState.FAIL_OPERATION;
	}

	private String getDate() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}
}
