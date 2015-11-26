package businesslogic.financebl;

import java.rmi.RemoteException;
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
	 * 先把入款金额记到每个营业厅编号上------把帐户名记为营业厅编号
	 * 等一下，，，这里的getTotalMoney是什么，，，
	 * */
	public int excute(CollectionReceiptVO vo){
		AccountBL a=new AccountBL();
//		ArrayList<GatheringReceiptVO> grvo=vo.getGathering();
//		for(GatheringReceiptVO v:grvo){
//			a.addMoney(v.getHallId(), v.getTotalmoney());
//		}
		a.addMoney(vo.getAccount(), vo.getIncome());
			System.out.println("执行成功！");
		
		return 0;
	}
	
	/**
	 * 获取符合时间条件的gatheringPO，需要显示
	 * 这个筛选应该在bdService中
	 * */
	public ArrayList<GatheringReceiptVO> getGathering(String Time){
		ArrayList<GatheringReceiptPO> gatheringReceiptPOs= bdService.getGatheringReceiptPO(Time);
		 return gposToVOs(gatheringReceiptPOs);
	
		// TODO Auto-generated method stub
//		ArrayList<GatheringReceiptPO> gatheringReceiptPOs = null;
//		try {
////			gatheringReceiptPOs=bdService.getGatheringReceiptPOs(Time);
//			gatheringReceiptPOs = bdService.getGatheringReceiptPOs();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(gatheringReceiptPOs==null){
//			System.out.println("gatheringReceiptPOs==null");
//			return null;
//		}
//		else{
//			gatheringReceiptPOs_Right=new ArrayList<GatheringReceiptPO>();
//		for(GatheringReceiptPO p:gatheringReceiptPOs){
//			if(p.getTime()==Time){
//				gatheringReceiptPOs_Right.add(p);
//			}
//		}
//		return gposToVOs(gatheringReceiptPOs_Right);
//		}
		
		
	}
	
	/**
	 * 获取符合条件的总钱数
	 * 参数：符合条件的收款单列表
	 * */
	public double getTotalMoney(ArrayList<GatheringReceiptVO> vo){
		// TODO Auto-generated method stub
		double totalMoney=0;
		
		if(vo==null){
			System.out.println("获取收款单金额失败");
			return 0;
		}
		else{
			for(GatheringReceiptVO v:vo){
				double money=v.getTotalmoney();
				totalMoney+=money;
			}
			return totalMoney;
		}
	}
	
	/**
	 * 显示Po中存在的所有Collection记录
	 * */
	public ArrayList<CollectionReceiptVO> getAllCollection(){
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		return cposToVOs(collectionReceiptPOs);
	}

	
	/**
	 * gatheringVO to PO
	 * */
//	public GatheringReceiptPO gvoToPO(GatheringReceiptVO vo){
//		return null;
//	}
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
		CollectionReceiptPO collectionReceiptPO=new CollectionReceiptPO(vo.getID(), vo.getUserID(), vo.getType(), vo.getState(), vo.getIncome(), vo.getDate(), vo.getAccount());
		return collectionReceiptPO;
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
			gatheringReceiptVO=new GatheringReceiptVO(po.getTime(), po.getBusinesShall().getName(), po.getTotalmoney());
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
			collectionReceiptVO=new CollectionReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(), po.getIncome() , po.getDate(), po.getAccount());
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
				CollectionReceiptVO vo=new CollectionReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(),p.getIncome() , p.getDate(), p.getAccount());
				collectionReceiptVOs.add(vo);
			}
			return collectionReceiptVOs;
		}
	}
	
	
	
	/**
	 * 获取所有的gatheringPO,虽然好像并木有什么卵用=。=
	 * */
//	public ArrayList<GatheringReceiptPO> getGatheringPOs(){
//		try {
//			return bdService.getGatheringReceiptPOs();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	

	
//	/**
//	 * 获得符合时间条件的gatheringPO的money
//	 * */
//	public ArrayList<Double> getMoney(ArrayList<GatheringReceiptVO> vo) {
//		// TODO Auto-generated method stub
//		ArrayList<Double> moneys ;
//		if(vo==null){
//			return null;
//		}
//		else{
//			moneys=new ArrayList<Double>();
//			for(GatheringReceiptVO v:vo){
//				Double money=v.getTotalmoney();
//				moneys.add(money);
//			}
//			return moneys;
//		}
//	}
	
	
	
	/**
	 * 根据ID筛选出Collectionpo,先写在这来不及就不写了
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
