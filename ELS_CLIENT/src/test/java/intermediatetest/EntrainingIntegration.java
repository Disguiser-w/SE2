package intermediatetest;

import businesslogic.intermediatebl.AllocateWaitingOrderBL;
import businesslogic.intermediatebl.TrainManagerBL;

public class EntrainingIntegration {
	 public void testEntraining(){
	    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
	    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL(transferingReceipt);
//	    	TrainManagerBL ebl = new TrainManagerBL(null, null, null);
	    	
		// MockEntrainingReceipt entrainingReceipt = (MockEntrainingReceipt)
		// ebl.entrain(awo.updateWaitingList());
		// entrainingReceipt.ID = transferingReceipt.getID();
		//
		// if(entrainingReceipt.ID=="test")
		// System.out.println("equals");
		// else
		// System.out.println("error");
	    }
}
