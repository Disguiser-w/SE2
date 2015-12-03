package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import po.ExpressPO;
import po.GatheringReceiptPO;
import type.ReceiptState;
import vo.OrganizationVO;

public class Gathering {

	public ArrayList<String> getChargeInfo() {
		// 获取收费信息
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;
		ExpressDataService expressData = BusinessMainController.expressData;

		ArrayList<ExpressPO> po = null;
		try {
			po = expressData.getExpressInfos(organizationVO.organizationID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		double total = 0;

		ArrayList<String> chargeInfo = new ArrayList<String>();

		for (ExpressPO i : po) {
			double charge = Double.parseDouble(i.getChargeCollection().get(0));
			chargeInfo.add(i.getID() + " " + charge);
		}
		return chargeInfo;
	}

	public double gathering() {

		BusinessMainController.updateBusinessVO();
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;
		ExpressDataService expressData = BusinessMainController.expressData;
		BusinessDataService businessData = BusinessMainController.businessData;

		// 获取收费信息
		ArrayList<ExpressPO> po = null;
		try {
			po = expressData.getExpressInfos(organizationVO.organizationID);
			expressData.deleteChargeInfos(organizationVO.organizationID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		double total = 0;
		ArrayList<Double> charges = new ArrayList<Double>();
		ArrayList<String> expressIDs = new ArrayList<String>();

		for (ExpressPO i : po) {
			double charge = Double.parseDouble(i.getChargeCollection().get(0));
			total += charge;

			charges.add(charge);
			expressIDs.add(i.getID());
		}

		Date d = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		String time = fm.format(d);

		// public OrganizationVO businesshall;
		// public String time;
		// public ArrayList<String> expressIDs;
		// public ArrayList<Double> money;
		// public double totalmoney;
		String receiptID = "SKD-" + organizationVO.organizationID + "-" + time;

		GatheringReceiptPO grp = new GatheringReceiptPO(OrganizationBL.organizationVOToPO(organizationVO), time,
				expressIDs, charges, total, receiptID, ReceiptState.SUBMIT);

		try {
			businessData.addGatheringReceipt(organizationVO.organizationID, grp);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

}
