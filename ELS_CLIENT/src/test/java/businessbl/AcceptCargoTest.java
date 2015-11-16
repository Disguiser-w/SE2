package businessbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.AcceptCargoController;
import vo.OrderAcceptReceiptVO;

public class AcceptCargoTest {
	private AcceptCargoController acceptCargo;

	@Before
	public void setUp() throws Exception {
		acceptCargo = new AcceptCargoController();
	}

	@Test
	public void testAcceptCargo() {
		OrderAcceptReceiptVO vo = new OrderAcceptReceiptVO(null, "2015-11-11", null, null);
		assertEquals(true, acceptCargo.acceptCargo(vo));
	}

}
