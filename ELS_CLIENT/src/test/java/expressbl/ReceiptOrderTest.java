package expressbl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import businesslogic.expressbl.controller.ReceiptOrderController;
import vo.OrderVO;

public class ReceiptOrderTest {
	private ReceiptOrderController controller;

	@Before
	public void setUp() throws Exception {
		controller = new ReceiptOrderController();
	}

	@Test
	public void testReceiptOrder() {
//		OrderVO vo = new OrderVO(null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null,
//				null, null, 0, 0, null, null, null);
//		assertEquals(true, controller.receiptOrder(vo));
	}

}
