package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import po.BusinessStatementReceiptPO;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import vo.BusinessStatementReceiptVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.controller.FinanceMainController;
/**
 * 查看经营情况表：查看特定时间范围内的入款单和付款单
 * */
public class BusinessStatementReceiptBL {

	CollectionReceiptDataService collectionData;
	PaymentReceiptDataService paymentData;
	
	public BusinessStatementReceiptBL() throws MalformedURLException, RemoteException, NotBoundException{
//		collectionData=(CollectionReceiptDataService) Naming.lookup("rmi://172.26.209.182:8888/CollectionReceiptDataService");
//		paymentData=(PaymentReceiptDataService) Naming.lookup("rmi://172.26.209.182:8888/PaymentReceiptDataService");
		collectionData=DataFactory.getCollectionReceiptData();
		paymentData=DataFactory.getPaymentReceiptData();
	}

	/**
	 * 筛选后的结果好像不写在这里
	 * */
	public BusinessStatementReceiptVO showBSList(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		try {
			BusinessStatementReceiptPO po=new BusinessStatementReceiptPO(beginTime, endTime, collectionData.getCollection_right(beginTime, endTime), paymentData.getPayment_right(beginTime, endTime));
			BusinessStatementReceiptVO vo=FinanceMainController.bpoToVO(po);
			return vo;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取经营情况表失败");
			return null;
		}
		
	}
	
	/**
	 * 因为是即时查看，不需要保存的序列化文件，所以这个貌似不需要了
	 * */
/*	public ArrayList<BusinessStatementReceiptVO> showAllBSList() {
		// TODO Auto-generated method stub
       return null;
		}
	*/

	
	/**
	 * 获取特定时间段的VO
	 * 怎么把时间限制在两个时间点之间???
	 * 筛选什么的还是写在数据层????
	 * */

	public int export(BusinessStatementReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args){
		
		
	}
//		try {
//			CollectionReceiptDataService collectionData=(CollectionReceiptDataService)Naming.lookup("rmi://172.26.209.182:8888/CollectionReceiptDataService");
//			PaymentReceiptDataService paymentData=(PaymentReceiptDataService)Naming.lookup("rmi://172.26.209.182:8800/PaymentReceiptDataService");
//			ArrayList<CollectionReceiptPO> po1=collectionData.getCollection_right("20110101", "20151129");
//			ArrayList<PaymentReceiptPO> po2=paymentData.getPayment_right("20110101", "20151129");
//			System.out.println("CollectionReceipt: ");
//			for(CollectionReceiptPO p1:po1){
//				System.out.println("ID: "+p1.getID());
//			}
//			System.out.println("PaymentReceipt:");
//			for(PaymentReceiptPO p2:po2){
//				System.out.println("ID: "+p2.getID());
//			}
//
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	/**
//	 * 从Collectionpo和PaymentPo中取出需要的数据转化为vo
//	 * 这里有比较多的代码重复——可以写成controller(静态方法)
//	 * */
//	public BusinessStatementReceiptVO bpoToVO(BusinessStatementReceiptPO businessstatementReceiptPO){
//
//		BusinessStatementReceiptVO businessStatementReceiptVO;
//	
////		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
//		ArrayList<CollectionReceiptPO> collectionReceiptPOs=businessstatementReceiptPO.getCollectionPOs();
//		ArrayList<CollectionReceiptVO> collectionReceiptVOs;
//		if(collectionReceiptPOs==null){
//			System.out.println("CollectionReceiptPOs is null-------BusinessstatementReceiptBL");
//			collectionReceiptVOs=null;
//		}
//		else{
//			collectionReceiptVOs=new ArrayList<CollectionReceiptVO>();
//			for(CollectionReceiptPO p:collectionReceiptPOs){
//				CollectionReceiptVO vo=new CollectionReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getIncome(), p.getDate(), p.getAccount());
//				collectionReceiptVOs.add(vo);
//			}
//		}		
//
////		ArrayList<PaymentReceiptPO> paymentReceiptPOs=prdService.getAllPaymentReceipt();
//		ArrayList<PaymentReceiptPO> paymentReceiptPOs=businessstatementReceiptPO.getPaymentPOs();
//		ArrayList<PaymentReceiptVO> paymentReceiptVOs;
//		if(paymentReceiptPOs==null){
//			System.out.println("ArrayList<PaymentReceiptPO> pos is null  ------------------BusinessstatementReceiptBL");
//		    paymentReceiptVOs=null;
//		}
//		else{
//			paymentReceiptVOs=new ArrayList<PaymentReceiptVO>();
//			for(PaymentReceiptPO p:paymentReceiptPOs){
//				PaymentReceiptVO vo=new PaymentReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getRent(), p.getFare(),p.getSalary(), p.getDate() , p.getAccount(), p.getName());
//				paymentReceiptVOs.add(vo);
//			}
//		}
//		
//		businessStatementReceiptVO=new BusinessStatementReceiptVO(businessstatementReceiptPO.getBeginTime(), businessstatementReceiptPO.getEndTime(), collectionReceiptVOs, paymentReceiptVOs);
//		return businessStatementReceiptVO;
//	}
//
//	/**
//	 * 将vo转化为po存入BusinessstatementReceiptPO
//	 * */
//	public BusinessStatementReceiptPO bvoToPO(BusinessStatementReceiptVO vo){
//		ArrayList<CollectionReceiptVO> collectionReceiptVOs=vo.cvos;
//		ArrayList<PaymentReceiptVO> paymentReceiptVOs=vo.pvos;
//		ArrayList<CollectionReceiptPO> collectionReceiptPOs=new ArrayList<CollectionReceiptPO>();
//		ArrayList<PaymentReceiptPO> paymentReceiptPOs=new ArrayList<PaymentReceiptPO>();
//		for(CollectionReceiptVO v1:collectionReceiptVOs){
//			CollectionReceiptPO po=new CollectionReceiptPO(v1.getID()	,v1.getUserID(),v1.getType(), v1.getState(), v1.getIncome(),v1.getDate(),v1.getAccount());
//			collectionReceiptPOs.add(po);
//		}
//		for(PaymentReceiptVO v2:paymentReceiptVOs){
//			PaymentReceiptPO po=new PaymentReceiptPO(v2.getID(), v2.getUserID(),v2.getType(), v2.getState(), v2.getRent(), v2.getFare(),v2.getSalary(), v2.getDate(), v2.getAccount(), v2.getName());
//			paymentReceiptPOs.add(po);
//		}
//		BusinessStatementReceiptPO po=new BusinessStatementReceiptPO(vo.beginTime, vo.endTime, collectionReceiptPOs, paymentReceiptPOs);
//		return po;
//	}

}
