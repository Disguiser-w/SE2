package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.expressbl.ChargeCollection;
import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.ExpressPO;
import po.GatheringReceiptPO;
import vo.ExpressVO;
import vo.OrganizationVO;

public class Gathering {
	private BusinessDataService businessData;
	private ExpressDataService expressData;

	private String organizationID;

	public Gathering() {
		businessData = new BusinessDataService_stub();
		expressData = new ExpressDataService_stub();
	}

	// 返回所有该营业厅快递员的收费信息
	public ArrayList<String> getChargeInfo(String organizationID) {
		ArrayList<String> infos = new ArrayList<String>();
		ArrayList<ExpressPO> pos = null;
		try {
			pos = expressData.getExpressInfos();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (ExpressPO i : pos)
			if (i.getOrganization().getOrganizationID().equals(organizationID))
				infos.add(i.getID() + " " + i.getChargeCollection().get(0));
		this.organizationID = organizationID;
		return infos;
	}

	public double gathering(OrganizationVO vo) {
		double total = 0;
		ArrayList<String> infos = getChargeInfo(vo.getOrganizationID());
		for (String i : infos) {
			total += Double.parseDouble(i.split(" ")[0]);
		}

		// public OrganizationVO businesshall;
		// public String time;
		// public ArrayList<ExpressVO> expressList;
		// public ArrayList<Double> money;
		// public double totalmoney;
		// 生成收款汇总单
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(d);
		ArrayList<ExpressPO> sentExpressPOs = null;
		try {
			ArrayList<ExpressPO> expressPOs = expressData.getExpressInfos();
			for (ExpressPO i : expressPOs)
				if (i.getOrganization().getOrganizationID().equals(organizationID))
					sentExpressPOs.add(i);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Double> moneys = new ArrayList<Double>();
		for (ExpressPO i : sentExpressPOs)
			moneys.add(Double.parseDouble(i.getChargeCollection().get(0)));

		GatheringReceiptPO po = new GatheringReceiptPO(OrganizationBL.organizationVOToPO(vo), time, sentExpressPOs,
				moneys, total);
		businessData.addGatheringReceipt(po);

		return total;
	}

}
