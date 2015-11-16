package businessbl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.EnVehicleController;
import businesslogic.businessbl.info.LogisticQueryInfo;

public class EnVehicleTest {
	private EnVehicleController controller;

	@Before
	public void setUp() throws Exception {
		controller = new EnVehicleController();
	}

	@Test
	public void testAutoTruckLoading() {
		assertEquals("分配完成", controller.autoTruckLoading());
	}

	@Test
	public void testGetTransferOrders() {
		LogisticQueryInfo mock = new MockLogisticQueryInfo();
		assertEquals(0, mock.getOrderInfos().size());
	}

}
