package businessbl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.GatheringController;
import vo.GatheringReceiptVO;

public class GatheringTest {
	private GatheringController controller;

	@Before
	public void setUp() throws Exception {
		controller = new GatheringController();
	}

	@Test
	public void testGathering() {
//		GatheringReceiptVO vo = new GatheringReceiptVO(null, null, null, null, 0);
//		assertEquals(0, controller.gathering(vo));
	}

}
