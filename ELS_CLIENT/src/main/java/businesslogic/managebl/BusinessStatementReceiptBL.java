package businesslogic.managebl;

import java.util.ArrayList;

import po.BusinessStatementReceiptPO;
import vo.BusinessStatementReceiptVO;
import po.CollectionReceiptPO;
import vo.CollectionReceiptVO;
import po.PaymentReceiptPO;
import vo.PaymentReceiptVO;
import businesslogicservice.manageblservice.BusinessStatementReceiptBLService;
import dataservice.financedataservice.BusinessstatementReceiptDataService;

public class BusinessStatementReceiptBL implements BusinessStatementReceiptBLService{
	
	public BusinessstatementReceiptDataService bsrdService;
	
	public BusinessStatementReceiptVO showBSList(String beginTime,String endTime){
		return BSRPOToVO(bsrdService.showBSL(beginTime, endTime));
	}
	//输出所有的经营情况表
	public ArrayList<BusinessStatementReceiptVO> showAllBSList(){
		ArrayList<BusinessStatementReceiptPO> bsrpoList= bsrdService.showAllBSList();
		ArrayList<BusinessStatementReceiptVO> bsrvoList= new ArrayList<BusinessStatementReceiptVO>();
		for(BusinessStatementReceiptPO bsrpo: bsrpoList){
			bsrvoList.add(BSRPOToVO(bsrpo));
		}
		
	}
	//导出excel表格
	public int export(BusinessStatementReceiptVO vo){
		
	}
	
	public BusinessStatementReceiptVO BSRPOToVO(BusinessStatementReceiptPO bsrPO){
		return new BusinessStatementReceiptVO(bsrPO.getBeginTime(), bsrPO.getEndTime(), bsrPO.getCollectionPO(), bsrPO.getPaymentPO());
	}
	
	
}
