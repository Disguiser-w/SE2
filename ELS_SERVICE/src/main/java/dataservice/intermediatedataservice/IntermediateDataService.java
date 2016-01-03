package dataservice.intermediatedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.EnIntermediateReceiptPO;
import po.FarePO;
import po.UserPO;
import po.PlanePO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import type.OperationState;

public interface IntermediateDataService extends Remote {
	public UserPO getIntermediateInfo(String intermediate_ID)
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
			TransferingReceiptPO transferingReceipt, String organization_ID)
			throws RemoteException;

	public EnIntermediateReceiptPO getEnIntermediateReceiptInfo(
			String organization_ID, String EnIntermediateReceipt_ID, String date)
			throws RemoteException;

	public OperationState saveEnIntermediateReceiptInfo(
			ArrayList<EnIntermediateReceiptPO> enIntermediateReceiptList,
			String organization_ID) throws RemoteException;

	public ArrayList<FarePO> getFareInfo(String organization_ID, String date)
			throws RemoteException;

	public OperationState saveFareInfo(String organization_ID, FarePO fare)
			throws RemoteException;

	public ArrayList<TransferingReceiptPO> getSubmittedTransferingReceiptInfo()
			throws RemoteException;

	public OperationState saveSubmittedTransferingReceiptInfo(
			ArrayList<TransferingReceiptPO> transferingReceiptList)
			throws RemoteException;
	
	public OperationState saveSubmittedTransferingReceipt(TransferingReceiptPO receipt) throws RemoteException;

	public ArrayList<EnIntermediateReceiptPO> getSubmittedEnIntermediateReceiptInfo()
			throws RemoteException;

	public OperationState saveSubmittedEnIntermediateReceiptInfo(
			ArrayList<EnIntermediateReceiptPO> enIntermeidiateReceiptList)
			throws RemoteException;
	
	public OperationState saveSubmittedEnIntermediateReceipt(
			EnIntermediateReceiptPO receipt) throws RemoteException;
	}
