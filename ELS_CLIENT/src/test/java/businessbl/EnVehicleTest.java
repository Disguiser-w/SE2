package businessbl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.EnVehicleController;
import businesslogic.datafactory.DataFactory;
import po.OrganizationPO;
import po.RepertoryPO;
import type.OrganizationType;

public class EnVehicleTest {
	private EnVehicleController controller;

	public EnVehicleTest() {
		try {
			BusinessMainController.businessData = DataFactory.getBusinessData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025000", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
//		BusinessMainController.businessVO = new BusinessVO("doge", "YYT-00001", "2",
//				OrganizationBL.organizationPOToVO(po));
		controller = new EnVehicleController();

	}

	@Test
	public void testAutoTruckLoading() {

		assertEquals(0, controller.autoTruckLoading().size());

	}

}
