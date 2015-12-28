package expressbl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.expressbl.controller.ReceiptOrderController;
import type.ExpressType;
import type.OrderState;
import type.PackType;
import vo.OrderVO;

public class ReceiptOrderTest {
	private ReceiptOrderController controller;

	@Before
	public void setUp() throws Exception {
		controller = new ReceiptOrderController();

		try {
			ExpressMainController.expressData = DataFactory.getExpressData();
			ExpressMainController.expressVO = ExpressMainController
					.expressPOToVO((DataFactory.getExpressData().getExpressInfo(null, "KD-00001")));
		} catch (Exception e) {
			System.out.println(123);
		}
	}

	@Test
	public void testReceiptOrder() {

		OrderVO vo = new OrderVO("DD-20171210-1", "-", "南京 鼓楼", "", "-", "12345678900", "狗剩", "上海 浦东", "-", "123",
				"12345678901", 1, "1", "1", "-", ExpressType.ECONOMIC, PackType.COURIERBAGS, 0, 0, "", "", "狗剩", "",
				OrderState.WAITING_ENVEHICLE, new ArrayList<String>());

		assertEquals(false, controller.receiptOrder(vo));
		

	}

}
