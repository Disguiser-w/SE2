package businessbl;

import org.junit.Before;
import org.junit.Test;

import businesslogic.businessbl.controller.AcceptCargoController;
import type.OrganizationType;
import vo.DriverVO;
import vo.OrderAcceptReceiptVO;
import vo.OrganizationVO;
import vo.RepertoryVO;

public class AcceptCargoTest {
	private AcceptCargoController acceptCargo;

	@Before
	public void setUp() throws Exception {
		acceptCargo = new AcceptCargoController();
	}

	@Test
	public void testAcceptCargo() {
		
//		public final OrganizationVO local;
//		public final String time;
//		public final VehicleVO vehicleVO;
//		public final ArrayList<String> orderIDs;
//		public final String receiptID;
//		public final ReceiptState receiptState;
		
		
//		public final String ID;
//		public final String engineNumber;
//		public final String licensePlateNumber;
//		public final String lowNumberPlate;
//		public final String buyTime;
//		public final String serviceTime;
//		public final OrganizationVO destination;
//		public final String destinationCity;
//		public final OrganizationVO local;
//		public final DriverVO driver;
		
		RepertoryVO repertory = new RepertoryVO("204-1112","025000");
		OrganizationVO organizationVO = new OrganizationVO(OrganizationType.businessHall,"025000","鼓楼营业厅",repertory);
		DriverVO driver = new DriverVO();
		VehicleVO vehicleVO = new VehicleVO("金二胖","233","233","2015-12-18","2",organizationVO,"鼓楼营业厅",organizationVO,);
		
		OrderAcceptReceiptVO vo = new OrderAcceptReceptVO(organizationVO,"20151218",);
		assertEquals(true, acceptCargo.acceptCargo(vo));
	}

}
