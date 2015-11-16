package businesslogic.intermediatebl.envehiclebl;

import businesslogic.intermediatebl.transferingbl.MockTransferingReceipt;

public class EntruckingIntegration {
	public void testEntrucking(){
    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL();
    	EntruckingBL ebl = new EntruckingBL();
    	
    	MockEntruckingReceipt entruckingReceipt = (MockEntruckingReceipt) ebl.entruck(awo.updateWaitingList(transferingReceipt));
    	entruckingReceipt.ID = transferingReceipt.getID();
    	
    	if(entruckingReceipt.ID=="test")
    		System.out.println("equals");
    	else
    		System.out.println("error");
    }
}
