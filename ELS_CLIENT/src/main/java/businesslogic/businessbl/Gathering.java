package businesslogic.businessbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import po.ExpressPO;
import po.GatheringReceiptPO;
import type.ReceiptState;
import vo.OrganizationVO;

public class Gathering {
	private ExpressDataService expressData;
	private BusinessDataService businessData;

	public Gathering() {
		try {
			expressData = DataFactory.getExpressData();
			businessData = DataFactory.getBusinessData();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getChargeInfo() {
		// 获取收费信息
		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;

		ArrayList<ExpressPO> po = null;
		try {
			po = expressData.getExpressInfos(organizationVO.organizationID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (po == null) {
			return null;
		}

		double total = 0;

		ArrayList<String> chargeInfo = new ArrayList<String>();
		for (ExpressPO i : po) {

			double charge = 0;
			if (!i.getChargeCollection().isEmpty())
				charge = Double.parseDouble(i.getChargeCollection().get(0));
			total += charge;
			chargeInfo.add(i.getID() + " " + charge);
		}
		if (total == 0)
			return null;
		return chargeInfo;
	}

	public double gathering() {

		OrganizationVO organizationVO = BusinessMainController.businessVO.organizationVO;

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

			ArrayList<String> chargeCollection = new ArrayList<String>();
			chargeCollection.add("0");
			i.setChargeCollection(chargeCollection);
			try {
				expressData.updateChargeCollection(organizationVO.organizationID, i.getID(), chargeCollection);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
