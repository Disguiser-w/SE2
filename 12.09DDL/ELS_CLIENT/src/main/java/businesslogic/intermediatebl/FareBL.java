package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.intermediatedataservice.IntermediateDataService;
import type.OperationState;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.FareVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogicservice.intermediateblservice.FareBLService;

public class FareBL implements FareBLService {
	private IntermediateDataService intermediateData;

	private ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
	private ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
	private ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();

	private double fare_sum;

	public FareBL(ArrayList<EnplaningReceiptVO> enplaningReceiptList,
			ArrayList<EntrainingReceiptVO> entrainingReceiptList,
			ArrayList<EntruckingReceiptVO> entruckingReceiptList,
			IntermediateDataService intermediateData) {
		this.enplaningReceiptList = enplaningReceiptList;
		this.entrainingReceiptList = entrainingReceiptList;
		this.entruckingReceiptList = entruckingReceiptList;
		this.fare_sum = 0;
		this.intermediateData = intermediateData;
	}

	public OperationState computeFare() {
		// TODO 自动生成的方法存根
		for (EnplaningReceiptVO enplaningReceipt : enplaningReceiptList)
			fare_sum += enplaningReceipt.fare;
		for (EntrainingReceiptVO entrainingReceipt : entrainingReceiptList)
			fare_sum += entrainingReceipt.fare;
		for (EntruckingReceiptVO entruckingReceipt : entruckingReceiptList)
			fare_sum += entruckingReceipt.fare;
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState saveFare() throws RemoteException {
		// TODO 自动生成的方法存根
		intermediateData.saveFareInfo(IntermediateMainController
				.voToPO(new FareVO(
						enplaningReceiptList.get(0).intermediateCentre,
						enplaningReceiptList, entrainingReceiptList,
						entruckingReceiptList, fare_sum, null, null)));
		return OperationState.SUCCEED_OPERATION;
	}
}
