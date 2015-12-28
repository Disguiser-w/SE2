package expressbl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.expressbl.controller.LogisticQueryController;
import vo.OrderVO;

public class LogisticQueryTest {
	private LogisticQueryController controller;

	@Before
	public void setUp() throws Exception {
		try {
			ExpressMainController.expressData = DataFactory.getExpressData();
			ExpressMainController.expressVO = ExpressMainController
					.expressPOToVO((DataFactory.getExpressData().getExpressInfo(null, "KD-00001")));
		} catch (Exception e) {
			System.out.println(123);
		}

		controller = new LogisticQueryController();
	}

	@Test
	public void testQuery() {
		
		ArrayList<OrderVO> orderVOs = controller.query();
		assertEquals("1", orderVOs.get(orderVOs.size() - 1).senderName);
	}

}
