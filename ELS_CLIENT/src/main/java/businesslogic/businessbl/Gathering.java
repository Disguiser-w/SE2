package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.expressbl.ChargeCollection;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.ExpressPO;
import vo.ExpressVO;

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

	public double gathering() {
		double total = 0;
		ArrayList<String> infos = getChargeInfo(organizationID);
//		for()
		
		return 0;
	}

}
