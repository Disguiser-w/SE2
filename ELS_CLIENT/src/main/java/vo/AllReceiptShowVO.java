package vo;

import java.util.ArrayList;

public class AllReceiptShowVO {

	private ArrayList<Object> allReceiptList;
	private int[] count;
	
	public AllReceiptShowVO(){
		allReceiptList = new ArrayList<Object>();
		count = new int[13];
	}
	
	public AllReceiptShowVO(ArrayList<GatheringReceiptVO> gatheringList,
												ArrayList<CollectionReceiptVO> collectionList,
												ArrayList<PaymentReceiptVO> paymentList,
												ArrayList<EnIntermediateReceiptVO> enIntermediateList,
												ArrayList<TransferingReceiptVO> transferingList,
												ArrayList<EnVehicleReceiptVO> enVehicleList,
												ArrayList<OrderAcceptReceiptVO> orderAcceptList,
												ArrayList<DistributeReceiptVO> distributeList){
		this.addGatheringReceipt(gatheringList);
		this.addCollectiongReceipt(collectionList);
		this.addPaymentReceipt(paymentList);
		this.addEnIntermediateReceipt(enIntermediateList);
		this.addTransferingReceipt(transferingList);
		this.addEnVehicleReceipt(enVehicleList);
		this.addOrderAcceptReceipt(orderAcceptList);
		this.addDistributeReceipt(distributeList);
	}
	
	public void addGatheringReceipt(ArrayList<GatheringReceiptVO> gatheringList){
		if(gatheringList != null){
			for(GatheringReceiptVO grVO : gatheringList){
				allReceiptList.add(grVO);
			}
			count[0] = gatheringList.size();
		}
		else{
			count[0] = 0;
		}
	}
	
	public void addCollectiongReceipt(ArrayList<CollectionReceiptVO> collectionList){
		if(collectionList != null){
			for(CollectionReceiptVO crVO : collectionList){
				allReceiptList.add(crVO);
			}
			count[1] = collectionList.size();
		}
		else{
			count[1] = 0;
		}
	}
	
	public void addPaymentReceipt(ArrayList<PaymentReceiptVO> paymentList){
		if(paymentList != null){
			for(PaymentReceiptVO prVO : paymentList){
				allReceiptList.add(prVO);
			}
			count[2] = paymentList.size();
		}
		else{
			count[2] = 0;
		}
	}
	
	public void addEnIntermediateReceipt(ArrayList<EnIntermediateReceiptVO> enIntermediateList){
		if(enIntermediateList != null){
			for(EnIntermediateReceiptVO erVO : enIntermediateList){
				allReceiptList.add(erVO);
			}
			count[3] = enIntermediateList.size();
		}
		else{
			count[3] = 0;
		}
	}
	
	public void addTransferingReceipt(ArrayList<TransferingReceiptVO> transferingList){
		if(transferingList != null){
			for(TransferingReceiptVO trVO : transferingList){
				allReceiptList.add(trVO);
			}
			count[4] = transferingList.size();
		}
		else{
			count[4] = 0;
		}
	}
	
	public void addEnVehicleReceipt(ArrayList<EnVehicleReceiptVO> enVehicleList){
		if(enVehicleList != null){
			for(EnVehicleReceiptVO evrVO : enVehicleList){
				allReceiptList.add(evrVO);
			}
			count[5] = enVehicleList.size();
		}
		else{
			count[5] = 0;
		}
	}
	
	public void addOrderAcceptReceipt(ArrayList<OrderAcceptReceiptVO> orderAcceptList){
		if(orderAcceptList != null){
			for(OrderAcceptReceiptVO oarVO : orderAcceptList){
				allReceiptList.add(oarVO);
			}
			count[6] = orderAcceptList.size();
		}
		else{
			count[6] = 0;
		}
	}
	
	public void addDistributeReceipt(ArrayList<DistributeReceiptVO> distributeList){
		if(distributeList != null){
			for(DistributeReceiptVO drVO : distributeList){
				allReceiptList.add(drVO);
			}
			count[7] = distributeList.size();
		}
		else{
			count[7] = 0;
		}
	}
	
	
}
