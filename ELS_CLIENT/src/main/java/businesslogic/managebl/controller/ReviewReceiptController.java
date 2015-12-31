package businesslogic.managebl.controller;

import java.util.ArrayList;

import businesslogic.managebl.ReviewReceiptBL;
import businesslogicservice.manageblservice.ReviewReceiptBLService;
import vo.AllReceiptShowVO;
import vo.CollectionReceiptVO;
import vo.DistributeReceiptVO;
import vo.EnIntermediateReceiptVO;
import vo.EnVehicleReceiptVO;
import vo.EnterRepertoryReceiptVO;
import vo.GatheringReceiptVO;
import vo.LeaveRepertoryReceiptVO;
import vo.OrderAcceptReceiptVO;
import vo.PaymentReceiptVO;
import vo.TransferingReceiptVO;

public class ReviewReceiptController implements ReviewReceiptBLService{

	private ReviewReceiptBL reviewReceiptBL;
	
	public ReviewReceiptController(){
		reviewReceiptBL = new ReviewReceiptBL();
	}
	
	public int approve(String ID, Object ob){
		return reviewReceiptBL.approve(ID, ob);
	}
	
	public AllReceiptShowVO getAllReceiptList() {
		return reviewReceiptBL.getAllReceiptList();
	}
	
	public ArrayList<GatheringReceiptVO> getAllSubmittedGatheringReceipt(){
		return reviewReceiptBL.getAllSubmittedGatheringReceipt();
	}
	
	public ArrayList<CollectionReceiptVO> getAllSubmittedCollectionReceipt(){
		return reviewReceiptBL.getAllSubmittedCollectionReceipt();
	}
	
	public ArrayList<PaymentReceiptVO> getAllSubmittedPaymentReceipt(){
		return reviewReceiptBL.getAllSubmittedPaymentReceipt();
	}

	public ArrayList<EnIntermediateReceiptVO> getAllSubmittedEnIntermediateReceipt(){
		return reviewReceiptBL.getAllSubmittedEnIntermediateReceipt();
	}
	
	
	public ArrayList<TransferingReceiptVO> getAllSubmittedTransferingReceipt(){
		return reviewReceiptBL.getAllSubmittedTransferingReceipt();
	}

	public ArrayList<EnVehicleReceiptVO> getAllSubmittedEnVehicleReceipt(){
		return reviewReceiptBL.getAllSubmittedEnVehicleReceipt();
	}
	
	public ArrayList<OrderAcceptReceiptVO> getAllSubmittedOrderAcceptReceipt(){
		return reviewReceiptBL.getAllSubmittedOrderAcceptReceipt();
	}
	
	public ArrayList<DistributeReceiptVO> getAllSubmittedDistributeReceipt(){
		return reviewReceiptBL.getAllSubmittedDistributeReceipt();
	}
	
	public ArrayList<EnterRepertoryReceiptVO> getAllSubmittedEnterRepertoryReceipt(){
		return reviewReceiptBL.getAllSubmittedEnterRepertoryReceipt();
	}
	
	public ArrayList<LeaveRepertoryReceiptVO> getAllSubmittedLeaveRepertoryReceipt(){
		return reviewReceiptBL.getAllSubmittedLeaveRepertoryReceipt();
	}
	
}
