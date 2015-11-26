package businesslogic.expressbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.managebl.OrganizationBL;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import po.ExpressPO;
import po.OrderPO;
import vo.ExpressVO;
import vo.OrderVO;

public class ChargeCollection {

	public boolean chargeCollection(String chargeInfo) {
		// 更新expressVO
		ExpressMainController.updateExpressInfo();

		ArrayList<String> chargeCollection = ExpressMainController.expressVO.chargeCollection;

		chargeCollection.add(chargeInfo);
		double total = Double.parseDouble(chargeCollection.get(0));
		double charge = Double.parseDouble(chargeInfo.split(" ")[1]);
		chargeCollection.set(0, (total + charge) + "");
		chargeCollection.add(chargeInfo);

		boolean result = false;
		try {
			result = ExpressMainController.expressData.updateChargeCollection(
					ExpressMainController.expressVO.organization.organizationID, ExpressMainController.expressVO.ID,
					chargeCollection);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
