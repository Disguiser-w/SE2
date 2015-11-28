package intermediatetest;

import businesslogic.intermediatebl.envehiclebl.AllocateWaitingOrderBL;
import businesslogic.intermediatebl.envehiclebl.TruckManageBL;

public class EntruckingIntegration {
	public void testEntrucking(){
    	MockTransferingReceipt transferingReceipt = new MockTransferingReceipt(null, "test", null, null);
    	AllocateWaitingOrderBL awo = new AllocateWaitingOrderBL();
    	TruckManageBL ebl = new TruckManageBL();
    	
    	MockEntruckingReceipt entruckingReceipt = (MockEntruckingReceipt) ebl.entruck(awo.updateWaitingList(transferingReceipt));
    	entruckingReceipt.ID = transferingReceipt.getID();
    	
    	if(entruckingReceipt.ID=="test")
    		System.out.println("equals");
    	else
    		System.out.println("error");
    }
}
