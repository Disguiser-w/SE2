package businesslogic.financebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;
import businesslogic.financebl.BusinessStatementReceiptBL;
import businesslogicservice.financeblservice.BusinessstatementReceiptBLService;

public class BusinessStatementReceiptBLController implements BusinessstatementReceiptBLService{

	private BusinessStatementReceiptBL businessStatementReceiptBL;
	public BusinessStatementReceiptBLController() throws MalformedURLException, RemoteException, NotBoundException{
		 businessStatementReceiptBL=new BusinessStatementReceiptBL();
	}
	public BusinessStatementReceiptVO showBSList(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		return businessStatementReceiptBL.showBSList(beginTime, endTime);
	}

	public int export(BusinessStatementReceiptVO vo) {
		// TODO Auto-generated method stub
		return businessStatementReceiptBL.export(vo);
	}
	
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		BusinessStatementReceiptBLController controller=new BusinessStatementReceiptBLController();
		BusinessStatementReceiptVO vo=controller.showBSList("20121010", "20151206");
		ArrayList<CollectionReceiptVO> cvos=vo.cvos;
		ArrayList<PaymentReceiptVO> pvos=vo.pvos;
		for(CollectionReceiptVO v:cvos){
			System.out.println(v.getID());
		}
		for(PaymentReceiptVO v2:pvos){
			System.out.println(v2.getID()+" "+v2.getUserID()+" "+v2.getDate()+" "+v2.getCost());
		}
	}

}
