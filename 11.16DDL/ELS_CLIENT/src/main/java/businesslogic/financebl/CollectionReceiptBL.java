package businesslogic.financebl;

import java.util.ArrayList;

import po.GatheringReceiptPO;
import vo.GatheringReceiptVO;
import vo.MockGatheringReceiptVO;

public class CollectionReceiptBL {
	 String HallID;
		String Time;
		ArrayList<GatheringReceiptVO> AllGatheringReceiptVO;
		ArrayList<Double> money;
		ArrayList<String> AllGatheringID;
		
		GatheringReceiptVO gatheringReceiptBLVO;
		//此处的totalmoney是一个日期内的totalmoney
		double totalmoney;
		String CollectionId;
		String GatheringId;
		
		public CollectionReceiptBL(){			
		}

		public CollectionReceiptBL(GatheringReceiptVO grvo){
			gatheringReceiptBLVO=grvo;
		}
		
		public void addGatheringItem(GatheringReceiptVO mgrvo){
			//money.add(grpo.getTotalmoney());
			//AllGatheringID.add(grpo.getID());
			totalmoney=totalmoney+mgrvo.getTotalmoney();
			}
		
		public void deleteGatheringItem(String GatheringId){
			int i=AllGatheringID.indexOf(GatheringId);
			AllGatheringID.remove(i);
			this.totalmoney-=money.get(i);
			money.remove(i);
		}
		
		public String getGatheringIdByOrder(int i){
			return AllGatheringID.get(i);
		}
		
		public double getMoneyByOrder(int i){
			return money.get(i);
		}
				
		public String getTime(GatheringReceiptVO mgrvo){
			return mgrvo.getTime();
		}
		
		public double getTotalMoney(){
			return totalmoney;
		}
		
		

}
