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
	public IntermediatePO getIntermediateInfo(String intermediate_ID)
			throws RemoteException;

	public ArrayList<PlanePO> getPlaneList(String organization_ID)
			throws RemoteException;

	public ArrayList<TrainPO> getTrainList(String organization_ID)
			throws RemoteException;

	public ArrayList<TruckPO> getTruckList(String organization_ID)
			throws RemoteException;

	public OperationState savePlaneList(String organization_ID,
			ArrayList<PlanePO> planeList) throws RemoteException;

	public OperationState saveTrainList(String organization_ID,
			ArrayList<TrainPO> trainList) throws RemoteException;

	public OperationState saveTruckList(String organization_ID,
			ArrayList<TruckPO> trainList) throws RemoteException;

	public TransferingReceiptPO getTransferingReceiptInfo(
			String organization_ID, String date) throws RemoteException;

	public OperationState saveTransferingReceiptInfo(
			TransferingReceiptPO transferingReceipt) throws RemoteException;

	public EnIntermediateReceiptPO getEnIntermediateReceiptInfo(
			String organization_ID, String EnIntermediateReceipt_ID)
			throws RemoteException;

	public OperationState saveEnIntermediateReceiptInfo(
			EnIntermediateReceiptPO enIntermediateReceipt)
			throws RemoteException;

	public FarePO getFareInfo(String organization_ID, String date)
			throws RemoteException;

	public OperationState saveFareInfo(FarePO fare) throws RemoteException;
}
