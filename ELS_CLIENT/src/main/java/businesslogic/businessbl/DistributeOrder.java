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
import vo.OrderVO;

public class DistributeOrder {
	private BusinessDataService businessData;
	private ExpressDataService expressData;

	public DistributeOrder() {
		businessData = new BusinessDataService_stub();
		expressData = new ExpressDataService_stub();
	}

	public String distributeOrder() {

		return "狗剩 2015-11-11";
	}

	public ArrayList<ExpressVO> getExpressInfos() {
		ArrayList<ExpressVO> vos = new ArrayList<ExpressVO>();
		ArrayList<ExpressPO> pos = null;
		try {
			pos = expressData.getExpressInfos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (ExpressPO i : pos)
			vos.add(ChargeCollection.poToVO(i));
		return vos;
	}

	public ArrayList<OrderVO> getSendOrder() {
		ArrayList<OrderVO> vos = new ArrayList<OrderVO>();
		ArrayList<OrderPO> pos = expressData.get
	}

}
