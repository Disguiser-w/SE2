package businesslogic.financebl;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import businesslogic.receiptbl.ReceiptBL;
import businesslogicservice.financeblservice.CollectionReceiptBLService;
/**
 * 论没有考虑账户变化的本宝宝要爆炸了！！！
 * 入款单：建立一个boss账户，所有钱累加到这个账户上
 * 审批状态通过后，账户发生变化
 * 
 * 还少一个最关键的方法：怎样把gathering中得到的ID，money存进collectionVO
 * */
public class CollectionReceiptBL extends ReceiptBL implements CollectionReceiptBLService{

	CollectionReceiptDataService crdService;
	BusinessDataService bdService;
	
	ArrayList<GatheringReceiptPO> gatheringReceiptPOs_Right;
	
	
	/**
	 * 将显示的CollectionVO转化为持久化对象PO
	 * 发送给总经理
	 * */
	public int creatCollection(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		CollectionReceiptPO po=cvoToPO(vo);
		update(vo);
		return crdService.createCollection(po);
	}

	/**
	 * 账户金额的增减放在excute里
	 * 先把入款金额记到每个营业厅编号上
	 * */
	public int excute(CollectionReceiptVO vo){
		AccountBL a=new AccountBL();
		ArrayList<GatheringReceiptVO> grvo=vo.getGathering();
		for(GatheringReceiptVO v:grvo){
			a.addMoney(v.getHallId(), v.getTotalmoney());
		}
		System.out.println("执行成功！");
		
		return 0;
	}
	/**
	 * gatheringVO to PO
	 * */
	public GatheringReceiptPO gvoToPO(GatheringReceiptVO vo){
		return null;
	}
	/**
	 * ArrayList<GaheringVO> to PO
	 * */
	public ArrayList<GatheringReceiptPO> gvosToPOs(ArrayList<GatheringReceiptVO> vos){
		return null;
	}
	/**
	 * CollectionVO to PO
	 * */
	public CollectionReceiptPO cvoToPO(CollectionReceiptVO vo){
		return null;
	}
	/**
	 * ArrayList<CollectionVO> to PO 
	 * */
	public ArrayList<CollectionReceiptPO> cvosToPOs(ArrayList<CollectionReceiptVO> vos){
		return null;
	}
	/**
	 * GatheringPO to VO,显示所有的vo
	 * */
	public GatheringReceiptVO gpoToVO(GatheringReceiptPO po){
		if(po==null){
			return null;
		}
		else{
			GatheringReceiptVO gatheringReceiptVO;
			gatheringReceiptVO=new GatheringReceiptVO(po.getTime(), po.getHallID(), po.getTotalmoney());
		return gatheringReceiptVO;
		}
	}
	/**
	 * ArrayList<GatheringPO> to  VO
	 * */
	public ArrayList<GatheringReceiptVO> gposToVOs(ArrayList<GatheringReceiptPO> pos){
		ArrayList<GatheringReceiptVO> gatheringReceiptVOs;
		if(pos==null){
			return null;
		}
		else{
			gatheringReceiptVOs=new ArrayList<GatheringReceiptVO>();
			for(GatheringReceiptPO p:pos){
				GatheringReceiptVO vo=gpoToVO(p);
				gatheringReceiptVOs.add(vo);
			}
		}
		return null;
	}
	/**
	 * ColletionPO to VO
	 * */
	public CollectionReceiptVO cpoToVO(CollectionReceiptPO po){
		CollectionReceiptVO collectionReceiptVO;
		if(po==null){
			return null;
		}
		else{
			collectionReceiptVO=new CollectionReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(), po.totalMoney() , po.getDate(), po.getAccount());
			return collectionReceiptVO;
		}
	}
	/**
	 * CollectionPO to VO
	 * */
	public ArrayList<CollectionReceiptVO> cposToVOs(ArrayList<CollectionReceiptPO> pos){
		ArrayList<CollectionReceiptVO> collectionReceiptVOs;
		if(pos==null){
			return null;
		}
		else{
			collectionReceiptVOs=new ArrayList<CollectionReceiptVO>();
			for(CollectionReceiptPO p:pos){
				CollectionReceiptVO vo=new CollectionReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(),p.totalMoney() , p.getDate(), p.getAccount());
				collectionReceiptVOs.add(vo);
			}
			return collectionReceiptVOs;
		}
	}
	
	
	
	/**
	 * 获取所有的gatheringPO,虽然好像并木有什么卵用=。=
	 * */
	public ArrayList<GatheringReceiptPO> getGatheringPOs(){
		return bdService.getGatheringReceiptPOs();
	}
	
	/**
	 * 获取符合时间条件的gatheringPO
	 * */
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
	
	/**
	 * 获得符合时间条件的gatheringPO的money
	 * */
	public ArrayList<Double> getMoney(ArrayList<GatheringReceiptVO> vo) {
		// TODO Auto-generated method stub
		ArrayList<Double> moneys ;
		if(vo==null){
			return null;
		}
		else{
			moneys=new ArrayList<Double>();
			for(GatheringReceiptVO v:vo){
				Double money=v.getTotalmoney();
				moneys.add(money);
			}
			return moneys;
		}
	}
	
	/**
	 * 获取符合条件的总钱数
	 * */
	public double getTotalMoney(ArrayList<Double> money) {
		// TODO Auto-generated method stub
		double totalMoney=0;
		if(money==null){
			System.out.println("fail to get ArrayList<Double> money");
			return 0;
		}
		else{
			for(int i=0;i<money.size();i++){
				totalMoney+=money.get(i);
			}
			return totalMoney;
		}
	}

	
	/**
	 * 根据ID筛选出Collectionpo
	 * */
	public CollectionReceiptVO getCollection(String s) {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		if(collectionReceiptPOs==null){
			System.out.println("CollectionReceiptPO is null");
			return null;
		}
		else{
			CollectionReceiptVO vo=new CollectionReceiptVO();
			for(CollectionReceiptPO p:collectionReceiptPOs){
				if(p.getID()==s){
					vo=cpoToVO(p);
				}
			}
			return vo;
		}		
	}

	/**
	 * 显示Po中存在的所有Collection记录
	 * */
	public ArrayList<CollectionReceiptVO> getAllCollection() {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		return cposToVOs(collectionReceiptPOs);
	}

	


	

	/**
	 * 获取编号
	 * */
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
