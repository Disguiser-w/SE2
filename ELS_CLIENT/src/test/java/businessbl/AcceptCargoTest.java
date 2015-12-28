package businessbl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.AcceptCargoController;
import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import po.OrganizationPO;
import po.RepertoryPO;
import type.OrganizationType;

public class AcceptCargoTest {
	private AcceptCargoController acceptCargo;

	@Before
	public void setUp() throws Exception {
		BusinessMainController.businessData = DataFactory.getBusinessData();
		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
//		BusinessMainController.businessVO = new BusinessVO("doge", "YYT-00001", "2",
//				OrganizationBL.organizationPOToVO(po));
		acceptCargo = new AcceptCargoController();
	}

	@Test
	public void testAcceptCargo() {

		ArrayList<String> vos = new ArrayList<String>();
		vos.add("DD-20151220-0");
		vos.add("DD-20151220-1");
		vos.add("DD-20151220-2");
		vos.add("DD-20151220-3");
		vos.add("DD-20151220-4");

		assertEquals(false, acceptCargo.acceptCargo("VEH-025000-00001", vos));
	}

}
