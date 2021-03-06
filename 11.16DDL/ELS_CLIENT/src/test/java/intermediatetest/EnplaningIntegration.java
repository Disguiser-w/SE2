package intermediatetest;

import businesslogic.intermediatebl.envehiclebl.AllocateWaitingOrderBL;
import businesslogic.intermediatebl.envehiclebl.EnplaningBL;

public class EnplaningIntegration {
    public void testEnplaning(){
    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL();
    	EnplaningBL ebl = new EnplaningBL();
    	
    	MockEnplaningReceipt enplaningReceipt = (MockEnplaningReceipt) ebl.enplane(awo.updateWaitingList(transferingReceipt));
    	enplaningReceipt.ID = transferingReceipt.getID();
    	
    	if(enplaningReceipt.ID=="test")
    		System.out.println("equals");
    	else
    		System.out.println("error");
    }
}
