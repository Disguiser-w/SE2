package businesslogic.intermediatebl.driver;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PlanePO;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.PlaneVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.EnvehicleBL;
import businesslogic.intermediatebl.PlaneManagerBL;
import businesslogic.intermediatebl.TransferingBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import dataservice.intermediatedataservice.IntermediateDataService;

public class Envehicle_driver {
	static IntermediateDataService intermediateData;

	public static void main(String[] args) throws Exception {
		intermediateData = DataFactory.getIntermediateData();

		ArrayList<EnplaningReceiptVO> enplaningReceiptList = new ArrayList<EnplaningReceiptVO>();
		ArrayList<EntrainingReceiptVO> entrainingReceiptList = new ArrayList<EntrainingReceiptVO>();
		ArrayList<EntruckingReceiptVO> entruckingReceiptList = new ArrayList<EntruckingReceiptVO>();

		TransferingBL tbl = new TransferingBL(
				IntermediateMainController.poToVO(intermediateData
						.getTransferingReceiptInfo("025001", "2015-12-18")),
				intermediateData);

		ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
		for (PlanePO plane : intermediateData.getPlaneList("025001")) {
			planeList.add(IntermediateMainController.poToVO(plane));
		}

		PlaneManagerBL pmbl = new PlaneManagerBL(planeList,
				tbl.showTransferingReceipt().interdiateCentre, intermediateData);
		EnvehicleBL ebl = new EnvehicleBL(tbl, pmbl, null, null,
				enplaningReceiptList, entrainingReceiptList,
				entruckingReceiptList, intermediateData);
	}
}
