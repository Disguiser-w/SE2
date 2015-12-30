package intermediatetest;

import businesslogic.intermediatebl.AllocateWaitingOrderBL;
import businesslogic.intermediatebl.TruckManagerBL;

public class EntruckingIntegration {
	public void testEntrucking(){
    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL(transferingReceipt);
//    	TruckManagerBL ebl = new TruckManagerBL(null, null, null);
    	
		// MockEntruckingReceipt entruckingReceipt = (MockEntruckingReceipt)
		// ebl.entruck(awo.updateWaitingList(transferingReceipt));
		// entruckingReceipt.ID = transferingReceipt.getID();
		//
		// if(entruckingReceipt.ID=="test")
		// System.out.println("equals");
		// else
		// System.out.println("error");
    }
}
