package intermediatetest;

import businesslogic.intermediatebl.envehiclebl.AllocateWaitingOrderBL;
import businesslogic.intermediatebl.envehiclebl.PlaneManagerBL;

public class EnplaningIntegration {
    public void testEnplaning(){
    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL();
    	PlaneManagerBL ebl = new PlaneManagerBL();
    	
    	MockEnplaningReceipt enplaningReceipt = (MockEnplaningReceipt) ebl.enplane(awo.updateWaitingList(transferingReceipt));
    	enplaningReceipt.ID = transferingReceipt.getID();
    	
    	if(enplaningReceipt.ID=="test")
    		System.out.println("equals");
    	else
    		System.out.println("error");
    }
}
