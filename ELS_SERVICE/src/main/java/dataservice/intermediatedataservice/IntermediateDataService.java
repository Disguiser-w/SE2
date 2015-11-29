package dataservice.intermediatedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnIntermediateReceiptPO;
import po.FarePO;
import po.IntermediatePO;
import po.PlanePO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import type.OperationState;

public interface IntermediateDataService extends Remote {
	public IntermediatePO getIntermediateInfo(String organization_ID,
			String intermediate_ID);

	public ArrayList<PlanePO> getPlaneList(String organization_ID);

	public ArrayList<TrainPO> getTrainList(String organization_ID);

	public ArrayList<TruckPO> getTruckList(String organization_ID);

	public OperationState savePlaneList(String organization_ID,
			ArrayList<PlanePO> planeList) throws RemoteException;

	public OperationState saveTrainList(String organization_ID,
			ArrayList<TrainPO> trainList);

	public OperationState saveTruckList(String organization_ID,
			ArrayList<TruckPO> trainList);

	public TransferingReceiptPO getTransferingReceiptInfo(
			String organization_ID, String date, String ID);

	public OperationState saveTransferingReceiptInfo(
			TransferingReceiptPO transferingReceipt, String organization_ID);

	public EnIntermediateReceiptPO getEnIntermediateReceiptInfo(
			String organization_ID, String EnIntermediateReceipt_ID, String date);

	public OperationState saveEnIntermediateReceiptInfo(
			EnIntermediateReceiptPO enIntermediateReceipt,
			String organization_ID);

	public FarePO getFareInfo(String organization_ID, String fare_ID,
			String date);

	public OperationState saveFareInfo(String organization_ID, FarePO fare);
}
