package intermediatetest;

import businesslogic.intermediatebl.envehiclebl.AllocateWaitingOrderBL;
import businesslogic.intermediatebl.envehiclebl.EntrainingBL;

public class EntrainingIntegration {
	 public void testEntraining(){
	    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
	    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL();
	    	EntrainingBL ebl = new EntrainingBL();
	    	
	    	MockEntrainingReceipt entrainingReceipt = (MockEntrainingReceipt) ebl.entrain(awo.updateWaitingList(transferingReceipt));
	    	entrainingReceipt.ID = transferingReceipt.getID();
	    	
	    	if(entrainingReceipt.ID=="test")
	    		System.out.println("equals");
	    	else
	    		System.out.println("error");
	    }
}
