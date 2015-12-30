package intermediatetest;

import businesslogic.intermediatebl.AllocateWaitingOrderBL;
import businesslogic.intermediatebl.PlaneManagerBL;

public class EnplaningIntegration {
    public void testEnplaning(){
    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL(transferingReceipt);
//    	PlaneManagerBL ebl = new PlaneManagerBL(null, null, null);
    	
		// MockEnplaningReceipt enplaningReceipt = (MockEnplaningReceipt)
		// ebl.enplane(awo.updateWaitingList());
		// enplaningReceipt.ID = transferingReceipt.getID();
		//
		// if(enplaningReceipt.ID=="test")
		// System.out.println("equals");
		// else
		// System.out.println("error");
    }
}
