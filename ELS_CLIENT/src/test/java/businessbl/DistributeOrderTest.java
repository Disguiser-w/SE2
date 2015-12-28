package businessbl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.DistributeOrderController;
import po.OrganizationPO;
import po.RepertoryPO;
import type.OrganizationType;

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
//		BusinessMainController.businessVO = new BusinessVO("doge", "YYT-00001", "2",
//				OrganizationBL.organizationPOToVO(po));
		assertEquals(true, controller.distributeOrder());

	}
}
