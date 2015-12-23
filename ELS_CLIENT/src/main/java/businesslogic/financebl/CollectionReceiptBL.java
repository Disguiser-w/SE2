package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.controller.FinanceMainController;
import businesslogic.receiptbl.ReceiptBL;
import businesslogic.receiptbl.getDate;
/**
 * 论没有考虑账户变化的本宝宝要爆炸了！！！
 * 入款单：建立一个boss账户，所有钱累加到这个账户上
 * 审批状态通过后，账户发生变化
 * 
 * 还少一个最关键的方法：怎样把gathering中得到的ID，money存进collectionVO
 * */
public class CollectionReceiptBL extends ReceiptBL {

	CollectionReceiptDataService collectionData;
	BusinessDataService businessData;
	
	
	public CollectionReceiptBL(){
		super();
		try {
			collectionData=DataFactory.getCollectionReceiptData();
			businessData=DataFactory.getBusinessData();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将显示的CollectionVO转化为持久化对象PO
	 * 发送给总经理
	 * */
	public int creatCollection(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		CollectionReceiptPO po=FinanceMainController.cvoToPO(vo);
		try {
			return collectionData.createCollection(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("创建入款单异常");
			return 2;
		}
	}

	/**
	 * 账户金额的增减放在excute里
	 * 先把入款金额记到每个营业厅编号上------把帐户名记为营业厅编号
	 * 等一下，，，这里的getTotalMoney是什么，，，
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 * */
	public int excute(CollectionReceiptVO vo) {
		AccountBL a=new AccountBL();
		a.addMoney(vo.account, vo.totalMoney);
			System.out.println("执行成功！");
		
		return 0;
	}
	
	/**
	 * 获取符合时间条件的gatheringPO，需要显示
	 * 这个筛选应该在bdService中
	 * 频率：一天整理合计一次
	 * */
	public ArrayList<GatheringReceiptVO> getGatheringByTime(String Time){
		try {
			if(businessData.getGatheringReceipt(Time)!=null){
			 return FinanceMainController.gposToVOs(businessData.getGatheringReceipt(Time));
			}
			else{
				return new ArrayList<GatheringReceiptVO>();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 20151223
	 * 获取符合营业厅ID的gatheringPO
	 * doge:好像需要这个接口啊23333
	 * */
	public ArrayList<GatheringReceiptVO> getGatheringByHall(String hallID){
		try {
			if(businessData.getGatheringReceiptByHallID(hallID)!=null){
			 return FinanceMainController.gposToVOs(businessData.getGatheringReceiptByHallID(hallID));
			}
			else{
				return new ArrayList<GatheringReceiptVO>();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 20151223
	 * 获取符合时间和营业厅ID的gatheringPO
	 * doge : 还有这个
	 * */
	public ArrayList<GatheringReceiptVO> getGatheringByBoth(String time,String hallID){
		try {
			if(businessData.getGatheringReceiptByBoth(time,hallID)!=null){
			 return FinanceMainController.gposToVOs(businessData.getGatheringReceiptByBoth(time,hallID));
			}
			else{
				return new ArrayList<GatheringReceiptVO>();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
				double money=v.totalmoney;
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
		ArrayList<CollectionReceiptPO> collectionReceiptPOs;
		try {
			collectionReceiptPOs = collectionData.getAllCollection();
			return FinanceMainController.cposToVOs(collectionReceiptPOs);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取PO中的合计收款单失败");
			return null;
		}
	}

	
	/**
	 * 根据ID筛选出Collectionpo,先写在这来不及就不写了
	 * */
	public CollectionReceiptVO getCollection(String s) {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs;
		try {
			collectionReceiptPOs = collectionData.getAllCollection();
			if(collectionReceiptPOs==null){
				System.out.println("CollectionReceiptPO is null");
				return null;
			}
			else{
				CollectionReceiptVO vo=new CollectionReceiptVO();
				for(CollectionReceiptPO p:collectionReceiptPOs){
					if(p.getID()==s){
						vo=FinanceMainController.cpoToVO(p);
					}
				}
				return vo;
			}		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 获取编号——收款单一天只产生一张
	 * 所以编号可以只定义为HJSKD-20151129
	 * */
	public String getCollectionListID() {
		// TODO Auto-generated method stub
		return "HJSKD-"+getDate.getdate();
	}

	/**
	 * 获取未审批的合计收款单
	 * */
	public ArrayList<CollectionReceiptVO> getUnapprovedCollectionReceipt() {
		// TODO Auto-generated method stub
		try {
			return FinanceMainController.cposToVOs(collectionData.getUnapprovedCollectionReceipt());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取未审批的单据失败");
			return null;
		}
	}
	
	/**
	 * 获取总经理审批后的结果
	 * */
	public int saveSubmittedCollectionReceiptInfo(CollectionReceiptVO vo){
		try {
			return collectionData.saveSubmittedCollectionReceiptInfo(FinanceMainController.cvoToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取总经理审批后结果失败");
			return 0;
		}
		
	}
}


		


