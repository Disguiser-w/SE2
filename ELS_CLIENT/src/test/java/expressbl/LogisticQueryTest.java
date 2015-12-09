package expressbl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import businesslogic.expressbl.controller.LogisticQueryController;

public class LogisticQueryTest {
	private LogisticQueryController controller;

	@Before
	public void setUp() throws Exception {
		controller = new LogisticQueryController();
	}

	@Test
	public void testQuery() {
		// fail("Not yet implemented");
//		assertEquals("狗剩", controller.query("1008610086").senderName);
	}

}
