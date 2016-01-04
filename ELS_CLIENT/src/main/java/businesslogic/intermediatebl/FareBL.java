package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogicservice.intermediateblservice.FareBLService;
import dataservice.intermediatedataservice.IntermediateDataService;
import type.OperationState;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.FareVO;
import vo.OrganizationVO;

public class FareBL implements FareBLService {
	private IntermediateDataService intermediateData;

	private OrganizationVO intermediateCenter;

	private ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
	private ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
	private ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();

	private double fare_sum;

	public FareBL(ArrayList<EnplaningReceiptVO> enplaningReceiptList,
			ArrayList<EntrainingReceiptVO> entrainingReceiptList,
			ArrayList<EntruckingReceiptVO> entruckingReceiptList,
			IntermediateDataService intermediateData,
			OrganizationVO intermediateCenter) {
		this.enplaningReceiptList = enplaningReceiptList;
		this.entrainingReceiptList = entrainingReceiptList;
		this.entruckingReceiptList = entruckingReceiptList;
		this.fare_sum = 0;
		this.intermediateData = intermediateData;
		this.intermediateCenter = intermediateCenter;
	}

	public OperationState computeFare() {
		// TODO 自动生成的方法存根
		for (EnplaningReceiptVO enplaningReceipt : enplaningReceiptList)
			fare_sum += enplaningReceipt.fare;
		for (EntrainingReceiptVO entrainingReceipt : entrainingReceiptList)
			fare_sum += entrainingReceipt.fare;
		for (EntruckingReceiptVO entruckingReceipt : entruckingReceiptList)
			fare_sum += entruckingReceipt.fare;
		try {
			saveFare();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState saveFare() throws RemoteException {
		// TODO 自动生成的方法存根
		System.out.println(fare_sum);
		System.out.println(intermediateCenter.organizationID);
		intermediateData.saveFareInfo(intermediateCenter.organizationID,
				IntermediateMainController.voToPO(new FareVO(
						enplaningReceiptList.get(0).intermediateCentre,
						enplaningReceiptList, entrainingReceiptList,
						entruckingReceiptList, fare_sum)));
		return OperationState.SUCCEED_OPERATION;
	}
}
