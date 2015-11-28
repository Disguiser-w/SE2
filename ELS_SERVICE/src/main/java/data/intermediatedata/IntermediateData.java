package data.intermediatedata;

import java.util.ArrayList;

import po.EnIntermediateReceiptPO;
import po.FarePO;
import po.IntermediatePO;
import po.PlanePO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import type.OperationState;
import dataservice.intermediatedataservice.IntermediateDataService;

public class IntermediateData implements IntermediateDataService{

	public IntermediatePO getIntermediateInfo(String organization_ID,
			String intermediate_ID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<PlanePO> getPlaneList(String organization_ID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<TrainPO> getTrainList(String organization_ID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<TruckPO> getTruckList(String organization_ID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public OperationState savePlaneList(String organization_ID,
			ArrayList<PlanePO> planeList) {
		// TODO 自动生成的方法存根
		return null;
	}

	public OperationState saveTrainList(String organization_ID,
			ArrayList<TrainPO> trainList) {
		// TODO 自动生成的方法存根
		return null;
	}

	public OperationState saveTruckList(String organization_ID,
			ArrayList<TruckPO> trainList) {
		// TODO 自动生成的方法存根
		return null;
	}

	public TransferingReceiptPO getTransferingReceiptInfo(
			String organization_ID, String date) {
		// TODO 自动生成的方法存根
		return null;
	}

	public OperationState saveTransferingReceiptInfo(
			TransferingReceiptPO transferingReceipt) {
		// TODO 自动生成的方法存根
		return null;
	}

	public EnIntermediateReceiptPO getEnIntermediateReceiptInfo(
			String organization_ID, String EnIntermediateReceipt_ID) {
		// TODO 自动生成的方法存根
		return null;
	}

	public OperationState saveTransferingReceiptInfo(
			EnIntermediateReceiptPO enIntermediateReceipt) {
		// TODO 自动生成的方法存根
		return null;
	}

	public FarePO getFareInfo(String organization_ID, String date) {
		// TODO 自动生成的方法存根
		return null;
	}

	public OperationState saveFareInfo(FarePO fare) {
		// TODO 自动生成的方法存根
		return null;
	}

}
