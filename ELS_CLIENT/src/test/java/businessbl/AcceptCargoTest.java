package businessbl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.AcceptCargoController;
import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.OrganizationBL;
import po.OrganizationPO;
import po.RepertoryPO;
import type.ExpressType;
import type.OrderState;
import type.OrganizationType;
import type.PackType;
import vo.BusinessVO;
import vo.OrderVO;

public class AcceptCargoTest {
	private AcceptCargoController acceptCargo;

	@Before
	public void setUp() throws Exception {
		BusinessMainController.businessData = DataFactory.getBusinessData();
		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
		BusinessMainController.businessVO = new BusinessVO("doge", "YYT-00001", "2",
				OrganizationBL.organizationPOToVO(po));
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
