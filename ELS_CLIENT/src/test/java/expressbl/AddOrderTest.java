package expressbl;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.AddOrderController;
import businesslogic.expressbl.controller.ExpressMainController;
import junit.framework.TestCase;
import type.ExpressType;
import type.OrderState;
import type.PackType;
import vo.OrderVO;

/**
 * 与本模块交互的其他模块的类ExpressVO和OrderVO由同一人完成，所以不再为其编写mock，而是直接使用
 */
public class AddOrderTest extends TestCase {

	private AddOrderController controller;
	private OrderVO vo;

	@Before
	public void setUp() throws Exception {
		ExpressMainController.expressVO = ExpressMainController
				.expressPOToVO((DataFactory.getExpressData().getExpressInfo(null, "KD-00001")));

		controller = new AddOrderController();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testCalculateCost() {

		vo = new OrderVO("DD-20171210-1", "-", "南京 鼓楼", "", "-", "12345678900", "狗剩", "上海 浦东", "-", "123",
				"12345678901", 1, "1", "1", "-", ExpressType.ECONOMIC, PackType.COURIERBAGS, 0, 0, "2015-12-14", "", "",
				"", OrderState.WAITING_ENVEHICLE, new ArrayList<String>());
		controller.calculateCost(vo);

		assertEquals(true, 7.0 <= vo.freight + vo.packingExpense && vo.freight + vo.packingExpense <= 7.1);

	}

}
