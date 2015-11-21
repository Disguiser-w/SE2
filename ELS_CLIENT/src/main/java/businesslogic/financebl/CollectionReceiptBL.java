package businesslogic.financebl;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import businesslogicservice.financeblservice.CollectionReceiptBLService;

public class CollectionReceiptBL implements CollectionReceiptBLService{

	CollectionReceiptDataService crdService;
	BusinessDataService bdService;
	
	ArrayList<GatheringReceiptPO> gatheringReceiptPOs_Right;
	
	public GatheringReceiptPO gvoToPO(GatheringReceiptVO vo){
		return null;
	}
	public ArrayList<GatheringReceiptPO> gvosToPOs(ArrayList<GatheringReceiptVO> vos){
		return null;
	}
	
	public CollectionReceiptPO cvoToPO(CollectionReceiptVO vo){
		return null;
	}
	
	public ArrayList<CollectionReceiptPO> cvosToPOs(ArrayList<CollectionReceiptVO> vos){
		return null;
	}
	
	public GatheringReceiptVO gpoToVO(GatheringReceiptPO po){
		return null;
	}
	public ArrayList<GatheringReceiptVO> gposToVOs(ArrayList<GatheringReceiptPO> pos){
		return null;
	}
	
	public CollectionReceiptVO cpoToVO(CollectionReceiptPO po){
		return null;
	}
	public ArrayList<CollectionReceiptVO> cposToVOs(ArrayList<CollectionReceiptPO> pos){
		return null;
	}
	
	
	
	
	public ArrayList<GatheringReceiptPO> getGatheringPOs(){
		return bdService.getGatheringReceiptPOs();
	}
	
	@Override
	public ArrayList<GatheringReceiptVO> getGathering(String Time){
		// TODO Auto-generated method stub
		ArrayList<GatheringReceiptPO> gatheringReceiptPOs=bdService.getGatheringReceiptPOs();
		if(gatheringReceiptPOs==null){
			System.out.println("gatheringReceiptPOs==null");
			return null;
		}
		else{
			gatheringReceiptPOs_Right=new ArrayList<GatheringReceiptPO>();
		for(GatheringReceiptPO p:gatheringReceiptPOs){
			if(p.getTime()==Time){
				gatheringReceiptPOs_Right.add(p);
			}
		}
		return gposToVOs(gatheringReceiptPOs_Right);
		}
	}
	
	@Override
	public int creatCollection(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CollectionReceiptVO getCollection(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CollectionReceiptVO> getAllCollection() {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		
		return null;
	}

	

	@Override
	public double[] getMoney(GatheringReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalMoney(ArrayList<Double> money) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 获取编号
	 * */
	@Override
	public String getCollectionListID() {
		// TODO Auto-generated method stub
		return null;
	}
//	 String HallID;
//		String Time;
//		ArrayList<GatheringReceiptVO> AllGatheringReceiptVO;
//		ArrayList<Double> money;
//		ArrayList<String> AllGatheringID;
//		
//		GatheringReceiptVO gatheringReceiptBLVO;
//		//此处的totalmoney是一个日期内的totalmoney
//		double totalmoney;
//		String CollectionId;
//		String GatheringId;
//		
//		public CollectionReceiptBL(){			
//		}
//
//		public CollectionReceiptBL(GatheringReceiptVO grvo){
//			gatheringReceiptBLVO=grvo;
//		}
//		
//		public void addGatheringItem(GatheringReceiptVO mgrvo){
//			//money.add(grpo.getTotalmoney());
//			//AllGatheringID.add(grpo.getID());
//			totalmoney=totalmoney+mgrvo.getTotalmoney();
//			}
//		
//		public void deleteGatheringItem(String GatheringId){
//			int i=AllGatheringID.indexOf(GatheringId);
//			AllGatheringID.remove(i);
//			this.totalmoney-=money.get(i);
//			money.remove(i);
//		}
//		
//		public String getGatheringIdByOrder(int i){
//			return AllGatheringID.get(i);
//		}
//		
//		public double getMoneyByOrder(int i){
//			return money.get(i);
//		}
//				
//		public String getTime(GatheringReceiptVO mgrvo){
//			return mgrvo.getTime();
//		}
//		
//		public double getTotalMoney(){
//			return totalmoney;
//		}
	
	
		
		

}
