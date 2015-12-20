package businessbl;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.DistributeOrderController;
import businesslogic.businessbl.info.LogisticQueryInfo;
import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.managebl.OrganizationBL;
import po.OrganizationPO;
import po.RepertoryPO;
import type.OrganizationType;
import type.Sexuality;
import vo.BusinessVO;
import vo.DriverVO;
import vo.OrderVO;
import vo.VehicleVO;

public class DistributeOrderTest {

	private DistributeOrderController controller;

	@Before
	public void setUp() throws Exception {

		controller = new DistributeOrderController();
	}

	@Test
	public void testGetOrderInfos() {

//		try {
//			BusinessMainController.businessData = DataFactory.getBusinessData();
//		} catch (MalformedURLException | RemoteException | NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
		BusinessMainController.businessVO = new BusinessVO("doge", "YYT-00001", "2",
				OrganizationBL.organizationPOToVO(po));
		assertEquals(true, controller.distributeOrder());

	}
}
