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
	 * 从po中获取入款单并转化为vo
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
	
	public BusinessStatementReceiptVO bpoToVO(BusinessstatementReceiptPO businessstatementReceiptPOs){

		BusinessStatementReceiptVO businessStatementReceiptVO;

		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		ArrayList<CollectionReceiptVO> collectionReceiptVOs;
		if(collectionReceiptPOs==null){
			System.out.println("CollectionReceiptPOs is null-------BusinessstatementReceiptBL");
			collectionReceiptVOs=null;
		}
		else{
			collectionReceiptVOs=new ArrayList<CollectionReceiptVO>();
			for(CollectionReceiptPO p:collectionReceiptPOs){
				CollectionReceiptVO vo=new CollectionReceiptVO(p.getID(), p.getUserID(), p.getState(), p.totalMoney(), p.getDate());
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
