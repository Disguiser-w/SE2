package businessbl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.DistributeOrderController;
import businesslogic.businessbl.info.LogisticQueryInfo;
import vo.OrderVO;

public class DistributeOrderTest {

	private DistributeOrderController controller;

	@Before
	public void setUp() throws Exception {
		controller = new DistributeOrderController();
	}

	@Test
	public void testDistributeOrder() {
//		assertEquals("狗剩 2015-11-11", controller.distributeOrder());
	}

	@Test
	public void testGetOrderInfos() {
		LogisticQueryInfo mock = new MockLogisticQueryInfo();
		ArrayList<OrderVO> infos = mock.getOrderInfos();
		assertEquals(0, infos.size());
	}
}
