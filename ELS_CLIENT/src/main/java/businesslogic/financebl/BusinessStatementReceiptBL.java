package businesslogic.financebl;

import java.util.ArrayList;

import po.BusinessstatementReceiptPO;
import po.CollectionReceiptPO;
import po.PaymentReceiptPO;
import dataservice.financedataservice.BusinessstatementReceiptDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;
import businesslogicservice.financeblservice.BusinessstatementReceiptBLService;
/**
 * 查看经营情况表：查看特定时间范围内的入款单和付款单
 * */
public class BusinessStatementReceiptBL implements BusinessstatementReceiptBLService{

	BusinessstatementReceiptDataService brdService;
	CollectionReceiptDataService crdService;
	PaymentReceiptDataService prdService;
	

	/**
	 * 筛选后的结果好像不写在这里
	 * */
	@Override
	public BusinessStatementReceiptVO showBSList(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<BusinessStatementReceiptVO> showAllBSList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 从Collectionpo和PaymentPo中取出需要的数据转化为vo
	 * 筛选不放在这里??
	 * */
	public BusinessStatementReceiptVO bpoToVO(BusinessstatementReceiptPO businessstatementReceiptPOs){

		BusinessStatementReceiptVO businessStatementReceiptVO;
		/**
		 * 筛选可以放在取数据的时候
		 * */
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		ArrayList<CollectionReceiptVO> collectionReceiptVOs;
		if(collectionReceiptPOs==null){
			System.out.println("CollectionReceiptPOs is null-------BusinessstatementReceiptBL");
			collectionReceiptVOs=null;
		}
		else{
			collectionReceiptVOs=new ArrayList<CollectionReceiptVO>();
			for(CollectionReceiptPO p:collectionReceiptPOs){
				CollectionReceiptVO vo=new CollectionReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.totalMoney(), p.getDate(), p.getAccount());
				collectionReceiptVOs.add(vo);
			}
		}		

		ArrayList<PaymentReceiptPO> paymentReceiptPOs=prdService.getAllPaymentReceipt();
		ArrayList<PaymentReceiptVO> paymentReceiptVOs;
		if(paymentReceiptPOs==null){
			System.out.println("ArrayList<PaymentReceiptPO> pos is null  ------------------BusinessstatementReceiptBL");
		    paymentReceiptVOs=null;
		}
		else{
			paymentReceiptVOs=new ArrayList<PaymentReceiptVO>();
			for(PaymentReceiptPO p:paymentReceiptPOs){
				PaymentReceiptVO vo=new PaymentReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getClause(), p.getMoney(), p.getDate() , p.getAccount(), p.getName());
				paymentReceiptVOs.add(vo);
			}
		}
		
		businessStatementReceiptVO=new BusinessStatementReceiptVO(businessstatementReceiptPOs.getBeginTime(), businessstatementReceiptPOs.getEndTime(), collectionReceiptVOs, paymentReceiptVOs);
		return businessStatementReceiptVO;
	}

	/**
	 * 将vo转化为po存入BusinessstatementReceiptPO
	 * */
	public BusinessstatementReceiptPO bvoToPO(BusinessStatementReceiptVO vo){
		ArrayList<CollectionReceiptVO> collectionReceiptVOs=vo.cvos;
		ArrayList<PaymentReceiptVO> paymentReceiptVOs=vo.pvos;
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=new ArrayList<CollectionReceiptPO>();
		ArrayList<PaymentReceiptPO> paymentReceiptPOs=new ArrayList<PaymentReceiptPO>();
		for(CollectionReceiptVO v1:collectionReceiptVOs){
			CollectionReceiptPO po=new CollectionReceiptPO(v1.getID()	,v1.getUserID(),v1.getType(), v1.getState(), v1.getIncome(),v1.getDate(),v1.getAccount());
			collectionReceiptPOs.add(po);
		}
		for(PaymentReceiptVO v2:paymentReceiptVOs){
			PaymentReceiptPO po=new PaymentReceiptPO(v2.getID(), v2.getUserID(),v2.getType(), v2.getState(), v2.getClause(), v2.getMoney(), v2.getDate(), v2.getAccount(), v2.getName());
			paymentReceiptPOs.add(po);
		}
		BusinessstatementReceiptPO po=new BusinessstatementReceiptPO(vo.beginTime, vo.endTime, collectionReceiptPOs, paymentReceiptPOs);
		return po;
	}
	
	/**
	 * 获取特定时间段的VO
	 * 怎么把时间限制在两个时间点之间???
	 * 筛选什么的还是写在数据层????
	 * */
	

	

	@Override
	public int export(BusinessStatementReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
