package expressbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.GatheringController;
import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ChargeCollectionController;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.managebl.OrganizationBL;
import junit.framework.TestCase;
import po.OrganizationPO;
import po.RepertoryPO;
import type.OrganizationType;
import vo.BusinessVO;

public class ChargeCollectionTest extends TestCase {
	public ChargeCollectionController controller;

	@Before
	public void setUp() {

		try {
			ExpressMainController.expressVO = ExpressMainController
					.expressPOToVO((DataFactory.getExpressData().getExpressInfo(null, "KD-00001")));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testChargeCollectionTest() {controller = new ChargeCollectionController();
		assertEquals(true,controller.chargeCollection());
	}
}
