package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.CollectionReceiptBL;
import businesslogic.financebl.PaymentReceiptBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.repertorybl.EnterRepertoryReceiptBL;
import businesslogic.repertorybl.LeaveRepertoryReceiptBL;
import businesslogicservice.manageblservice.ReviewReceiptBLService;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.intermediatedataservice.IntermediateDataService;
import po.DistributeReceiptPO;
import po.EnIntermediateReceiptPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.TransferingReceiptPO;
import type.ReceiptState;
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

public class ReviewReceiptBL implements ReviewReceiptBLService {

	private BusinessDataService bdService;
	private IntermediateDataService itmdService;

	private CollectionReceiptBL collectionBL;
	private PaymentReceiptBL paymentBL;
	private EnterRepertoryReceiptBL enterReceiptBL;
	private LeaveRepertoryReceiptBL leaveReceiptBL;

	private ArrayList<GatheringReceiptVO> gatheringReceiptVOList;
	private ArrayList<CollectionReceiptVO> collectionReceiptVOList;
	private ArrayList<PaymentReceiptVO> paymentReceiptVOList;
	private ArrayList<EnIntermediateReceiptVO> enIntermediateReceiptVOList;
	private ArrayList<TransferingReceiptVO> transferingReceiptVOList;
	private ArrayList<EnVehicleReceiptVO> enVehicleReceiptVOList;
	private ArrayList<OrderAcceptReceiptVO> orderAcceptReceiptVOList;
	private ArrayList<DistributeReceiptVO> distributeReceiptVOList;

	private ArrayList<GatheringReceiptPO> gatheringReceiptPOList;
	private ArrayList<EnIntermediateReceiptPO> enIntermediateReceiptPOList;
	private ArrayList<TransferingReceiptPO> transferingReceiptPOList;
	private ArrayList<EnVehicleReceiptPO> enVehicleReceiptPOList;
	private ArrayList<OrderAcceptReceiptPO> orderAcceptReceiptPOList;
	private ArrayList<DistributeReceiptPO> distributeReceiptPOList;

	public ReviewReceiptBL() {
		try {
			bdService = DataFactory.getBusinessData();
			itmdService = DataFactory.getIntermediateData();

			collectionBL = new CollectionReceiptBL();
			paymentBL = new PaymentReceiptBL();
			enterReceiptBL = new EnterRepertoryReceiptBL();
			leaveReceiptBL = new LeaveRepertoryReceiptBL();

		} catch (RemoteException | MalformedURLException | NotBoundException ex) {
			ex.printStackTrace();
		}
	}

	public int approve(String receiptID, Object ob) {

		// 收款单 GatheringReceipt
		if (receiptID.startsWith("SKD")) {
			GatheringReceiptVO grvo = (GatheringReceiptVO) ob;
			return approveGatheringReceipt(grvo);
		}

		// 合计收款单 CollectionReceipt
		else if (receiptID.startsWith("HJSKD")) {
			CollectionReceiptVO crvo = (CollectionReceiptVO) ob;
			return approveCollectionReceipt(crvo);
		}

		// 付款单 PaymentReceipt
		else if (receiptID.startsWith("FKD")) {
			PaymentReceiptVO prvo = (PaymentReceiptVO) ob;
			return approvePaymentReceipt(prvo);
		}

		// 中转中心装车单 EnIntermediateReceipt
		else if (receiptID.startsWith("ZZZXZCD")) {
			EnIntermediateReceiptVO ervo = (EnIntermediateReceiptVO) ob;
			return approveEnIntermediateReceipt(ervo);
		}

		// 中转中心到达单 TransferingReceipt
		else if (receiptID.startsWith("ZZZXDDD")) {
			TransferingReceiptVO trvo = (TransferingReceiptVO) ob;
			return approveTransferingReceipt(trvo);
		}

		// 营业厅装车单 EnVehicleReceipt
		else if (receiptID.startsWith("YYTZCD")) {
			EnVehicleReceiptVO evrvo = (EnVehicleReceiptVO) ob;
			return approveEnVehicleReceipt(evrvo);
		}

		// 营业厅到达单 OrderAcceptReceipt
		else if (receiptID.startsWith("YYTDDD")) {
			OrderAcceptReceiptVO oarvo = (OrderAcceptReceiptVO) ob;
			return approveOrderAcceptReceipt(oarvo);
		}

		// 派件单 DistributeReceipt
		else if (receiptID.startsWith("PJD")) {
			DistributeReceiptVO drvo = (DistributeReceiptVO) ob;
			return approveDistributeReceipt(drvo);
		}

		// 入库单 EnterRepertoryReceipt
		else if (receiptID.startsWith("RKD")) {
			EnterRepertoryReceiptVO errvo = (EnterRepertoryReceiptVO) ob;
			return approveEnterRepertoryReceipt(errvo);
		}

		// 出库单 LeaveRepertoryReceipt
		else if (receiptID.startsWith("CKD")) {
			LeaveRepertoryReceiptVO lrrvo = (LeaveRepertoryReceiptVO) ob;
			return approveLeaveRepertoryReceipt(lrrvo);
		}

		else {
			return 0;
		}
	}

	public AllReceiptShowVO refresh() {
		return null;
	}

	public AllReceiptShowVO getAllReceiptList() {
		gatheringReceiptVOList = getAllSubmittedGatheringReceipt();
		collectionReceiptVOList = getAllSubmittedCollectionReceipt();
		paymentReceiptVOList = getAllSubmittedPaymentReceipt();
		enIntermediateReceiptVOList = getAllSubmittedEnIntermediateReceipt();
		transferingReceiptVOList = getAllSubmittedTransferingReceipt();
		enVehicleReceiptVOList = getAllSubmittedEnVehicleReceipt();
		orderAcceptReceiptVOList = getAllSubmittedOrderAcceptReceipt();
		distributeReceiptVOList = getAllSubmittedDistributeReceipt();
		AllReceiptShowVO allReceiptVO = new AllReceiptShowVO(gatheringReceiptVOList, collectionReceiptVOList,
				paymentReceiptVOList, enIntermediateReceiptVOList, transferingReceiptVOList, enVehicleReceiptVOList,
				orderAcceptReceiptVOList, distributeReceiptVOList);
		return allReceiptVO;
	}

	// 获取全部提交的收款单
	public ArrayList<GatheringReceiptVO> getAllSubmittedGatheringReceipt() {
		try {
			gatheringReceiptPOList = bdService.getSubmittedGatheringReceiptInfo();
			gatheringReceiptVOList = new ArrayList<GatheringReceiptVO>();

			if (gatheringReceiptPOList == null)
				return gatheringReceiptVOList;
			for (int i = 0; i < gatheringReceiptPOList.size(); i++) {
				gatheringReceiptVOList.add(BusinessMainController.gatheringPOToVO(gatheringReceiptPOList.get(i)));
			}
			return gatheringReceiptVOList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 审批一个收款单
	public int approveGatheringReceipt(GatheringReceiptVO grVO) {
		GatheringReceiptPO grPO = BusinessMainController.gatheringVOToPO(grVO);
		grPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveGatheringReceiptInfo(grPO);
			return 0;
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}

	// 获取全部提交的、待审批的合计收款单
	public ArrayList<CollectionReceiptVO> getAllSubmittedCollectionReceipt() {
		collectionReceiptVOList = collectionBL.getUnapprovedCollectionReceipt();
		return collectionReceiptVOList;
	}

	// 审批一个合计收款单
	public int approveCollectionReceipt(CollectionReceiptVO crVO) {
		return collectionBL.saveSubmittedCollectionReceiptInfo(crVO);
	}

	// 获取全部提交的、待审批的付款单
	public ArrayList<PaymentReceiptVO> getAllSubmittedPaymentReceipt() {
		paymentReceiptVOList = paymentBL.getUnapprovedPaymentReceipt();
		return paymentReceiptVOList;
	}

	// 审批一个付款单
	public int approvePaymentReceipt(PaymentReceiptVO prVO) {
		return paymentBL.saveSubmittedPaymentReceiptInfo(prVO);
	}

	// 获取全部提交的中转中心装车单
	public ArrayList<EnIntermediateReceiptVO> getAllSubmittedEnIntermediateReceipt() {
		try {
			enIntermediateReceiptPOList = itmdService.getSubmittedEnIntermediateReceiptInfo();
			enIntermediateReceiptVOList = new ArrayList<EnIntermediateReceiptVO>();

			if (enIntermediateReceiptPOList == null)
				return enIntermediateReceiptVOList;
			for (int i = 0; i < enIntermediateReceiptPOList.size(); i++) {
				if (IntermediateMainController.poToVO(enIntermediateReceiptPOList.get(i)) != null)
					enIntermediateReceiptVOList
							.add(IntermediateMainController.poToVO(enIntermediateReceiptPOList.get(i)));
			}
			return enIntermediateReceiptVOList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 审批一个中转中心装车单
	public int approveEnIntermediateReceipt(EnIntermediateReceiptVO erVO) {
		EnIntermediateReceiptPO erPO = IntermediateMainController.voToPO(erVO);
		erPO.setReceiptState(ReceiptState.APPROVE);
		try {
			itmdService.saveSubmittedEnIntermediateReceipt(erPO);
			return 0;
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}

	// 获取全部提交的中转中心到达单
	public ArrayList<TransferingReceiptVO> getAllSubmittedTransferingReceipt() {
		transferingReceiptVOList = new ArrayList<TransferingReceiptVO>();
		try {
			transferingReceiptPOList = itmdService.getSubmittedTransferingReceiptInfo();
			if (transferingReceiptPOList != null) {
				for (int i = 0; i < transferingReceiptPOList.size(); i++) {
					transferingReceiptVOList.add(IntermediateMainController.poToVO(transferingReceiptPOList.get(i)));
				}
			}
			return transferingReceiptVOList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return transferingReceiptVOList;
		}
	}

	// 审批一个中转中心到达单
	public int approveTransferingReceipt(TransferingReceiptVO trVO) {
		TransferingReceiptPO trPO = IntermediateMainController.voToPO(trVO);
		trPO.setReceiptState(ReceiptState.APPROVE);
		try {
			itmdService.saveSubmittedTransferingReceipt(trPO);
			return 0;
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}

	// 获取全部提交的营业厅装车单
	public ArrayList<EnVehicleReceiptVO> getAllSubmittedEnVehicleReceipt() {
		try {
			enVehicleReceiptPOList = bdService.getSubmittedEnVehicleReceiptInfo();
			enVehicleReceiptVOList = new ArrayList<EnVehicleReceiptVO>();

			for (int i = 0; i < enVehicleReceiptPOList.size(); i++) {
				enVehicleReceiptVOList
						.add(BusinessMainController.enVehicleReceiptPOToVO(enVehicleReceiptPOList.get(i)));
			}
			return enVehicleReceiptVOList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 审批一个营业厅装车单
	public int approveEnVehicleReceipt(EnVehicleReceiptVO evrVO) {
		EnVehicleReceiptPO evrPO = BusinessMainController.enVehicleReceiptVOToPO(evrVO);
		evrPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveEnVehicleReceiptInfo(evrPO);
			return 0;
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}

	// 获取全部提交的营业厅到达单
	public ArrayList<OrderAcceptReceiptVO> getAllSubmittedOrderAcceptReceipt() {
		try {
			orderAcceptReceiptPOList = bdService.getSubmittedOrderAcceptReceiptInfo();

			orderAcceptReceiptVOList = new ArrayList<OrderAcceptReceiptVO>();

			for (int i = 0; i < orderAcceptReceiptPOList.size(); i++) {
				
				orderAcceptReceiptVOList
						.add(BusinessMainController.orderAcceptReceiptPOToVO(orderAcceptReceiptPOList.get(i)));
			}
			return orderAcceptReceiptVOList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 审批一个营业厅到达单
	public int approveOrderAcceptReceipt(OrderAcceptReceiptVO oarVO) {
		OrderAcceptReceiptPO oarPO = BusinessMainController.orderAcceptReceiptVOToPO(oarVO);
		oarPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveOrderAcceptReceiptInfo(oarPO);
			return 0;
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}

	// 获取全部提交的派件单
	public ArrayList<DistributeReceiptVO> getAllSubmittedDistributeReceipt() {
		try {
			distributeReceiptPOList = bdService.getSubmittedDistributeReceiptInfo();
			distributeReceiptVOList = new ArrayList<DistributeReceiptVO>();

			for (int i = 0; i < distributeReceiptPOList.size(); i++) {
				distributeReceiptVOList.add(BusinessMainController.distributePOToVO(distributeReceiptPOList.get(i)));
			}
			return distributeReceiptVOList;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 审批一个派件单
	public int approveDistributeReceipt(DistributeReceiptVO drVO) {
		DistributeReceiptPO drPO = BusinessMainController.distributeVOToPO(drVO);
		drPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveDistributeReceiptInfo(drPO);
			return 0;
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}

	// 获取全部提交的入库单
	public ArrayList<EnterRepertoryReceiptVO> getAllSubmittedEnterRepertoryReceipt() {
		return enterReceiptBL.getAllSubmitedEnterReceipts();
	}

	// 审批一个入库单
	public int approveEnterRepertoryReceipt(EnterRepertoryReceiptVO errVO) {
		System.out.println("BL   shenpi" + errVO.receiptID);
		return enterReceiptBL.approveEnterReceipt(errVO.receiptID);
	}

	// 获取全部提交的出库单
	public ArrayList<LeaveRepertoryReceiptVO> getAllSubmittedLeaveRepertoryReceipt() {
		return leaveReceiptBL.getAllSubmitedLeaveReceipts();
	}

	// 审批一个出库单
	public int approveLeaveRepertoryReceipt(LeaveRepertoryReceiptVO lrrVO) {
		return leaveReceiptBL.approveLeaveReceipt(lrrVO.receiptID);
	}

}
