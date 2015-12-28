package businessbl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.VehicleManagerController;
import businesslogic.datafactory.DataFactory;
import businesslogic.managebl.OrganizationBL;
import po.OrganizationPO;
import po.RepertoryPO;
import type.OrganizationType;
import type.Sexuality;
import vo.DriverVO;
import vo.VehicleVO;

public class VehicleManagerTest {

	private VehicleManagerController controller;	

	@Before
	public void setUp() throws Exception {
		BusinessMainController.businessData = DataFactory.getBusinessData();
		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
//		BusinessMainController.businessVO = new BusinessVO("doge", "YYT-00001", "2",
//				OrganizationBL.organizationPOToVO(po));
		controller = new VehicleManagerController();
	}

	@Test
	public void testAddVehicle() {

		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
		DriverVO vo1 = new DriverVO("SJ-025000-00000", "d", "2", "2", "12345678900",
				OrganizationBL.organizationPOToVO(po), Sexuality.MALE, "1", 2, false);
		VehicleVO vo = new VehicleVO("VEH-025000-00000", "1", "1", "1", "1", "1", OrganizationBL.organizationPOToVO(po),
				"啊南京", OrganizationBL.organizationPOToVO(po), vo1);
		assertEquals(true, controller.addVehicle(vo));
	}

	@Test
	public void testModifyVehicle() {
		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
		DriverVO vo1 = new DriverVO("SJ-025000-00000", "d", "2", "2", "12345678900",
				OrganizationBL.organizationPOToVO(po), Sexuality.MALE, "1", 2, false);
		VehicleVO vo = new VehicleVO("VEH-025000-00000", "1", "1", "1", "1", "1", OrganizationBL.organizationPOToVO(po),
				"南京啊", OrganizationBL.organizationPOToVO(po), vo1);

		assertEquals(true, controller.modifyVehicle(vo));
	}

	@Test
	public void testZDeleteVehicle() {
		OrganizationPO po = new OrganizationPO(OrganizationType.businessHall, "025001", "鼓楼营业厅",
				new RepertoryPO("pig", "wo"));
		DriverVO vo1 = new DriverVO("SJ-025000-00000", "d", "2", "2", "12345678900",
				OrganizationBL.organizationPOToVO(po), Sexuality.MALE, "1", 2, false);
		VehicleVO vo = new VehicleVO("VEH-025000-00000", "1", "1", "1", "1", "1", OrganizationBL.organizationPOToVO(po),
				"啊南京", OrganizationBL.organizationPOToVO(po), vo1);

		assertEquals(true, controller.deleteVehicle(vo));
	}

}
