package businesslogic.intermediatebl;

import java.util.ArrayList;

import type.OperationState;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.FareVO;
import businesslogicservice.intermediateblservice.FareBLService;

public class FareBL implements FareBLService {
	private ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
	private ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
	private ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();

	private double fare_sum;

	public FareBL(ArrayList<EnplaningReceiptVO> enplaningReceiptList,
			ArrayList<EntrainingReceiptVO> entrainingReceiptList,
			ArrayList<EntruckingReceiptVO> entruckingReceiptList) {
		this.enplaningReceiptList = enplaningReceiptList;
		this.entrainingReceiptList = entrainingReceiptList;
		this.entruckingReceiptList = entruckingReceiptList;
		this.fare_sum = 0;
	}

	public FareVO computeFare() {
		// TODO 自动生成的方法存根
		for (EnplaningReceiptVO enplaningReceipt : enplaningReceiptList)
			fare_sum += enplaningReceipt.fare;
		for (EntrainingReceiptVO entrainingReceipt : entrainingReceiptList)
			fare_sum += entrainingReceipt.fare;
		for (EntruckingReceiptVO entruckingReceipt : entruckingReceiptList)
			fare_sum += entruckingReceipt.fare;
		return new FareVO(enplaningReceiptList.get(0).intermediateCentre,
				enplaningReceiptList, entrainingReceiptList,
				entruckingReceiptList, fare_sum, null, null);
	}

	public OperationState saveFare() {
		// TODO 自动生成的方法存根
		//
		return OperationState.SUCCEED_OPERATION;
	}
}
