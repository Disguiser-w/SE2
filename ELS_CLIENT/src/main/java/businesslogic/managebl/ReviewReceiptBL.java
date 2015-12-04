package businesslogic.managebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.ReceiptState;
import vo.AllReceiptShowVO;
import vo.ReceiptVO;
import vo.CollectionReceiptVO;
import vo.DistributeReceiptVO;
import vo.EnIntermediateReceiptVO;
import vo.EnplaningReceiptVO;
import vo.EntrainingReceiptVO;
import vo.EntruckingReceiptVO;
import vo.EnVehicleReceiptVO;
import vo.GatheringReceiptVO;
import vo.OrderVO;
import vo.OrderAcceptReceiptVO;
import vo.PaymentReceiptVO;
import vo.TransferingReceiptVO;
import po.CollectionReceiptPO;
import po.PaymentReceiptPO;
import po.GatheringReceiptPO;
import po.TransferingReceiptPO;
import po.EnplaningReceiptPO;
import po.EnVehicleReceiptPO;
import po.OrderAcceptReceiptPO;
import po.DistributeReceiptPO;
import businesslogic.businessbl.DistributeOrder;
import businesslogic.businessbl.Gathering;
import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.financebl.controller.FinanceMainController;
import businesslogic.financebl.CollectionReceiptBL;
import businesslogic.financebl.PaymentReceiptBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.intermediatebl.envehiclebl.EnvehicleBL;
import businesslogic.receiptbl.ReceiptBL;
import businesslogicservice.manageblservice.ReviewReceiptBLService;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.intermediatedataservice.IntermediateDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.PaymentReceiptDataService;

public class ReviewReceiptBL implements ReviewReceiptBLService{

	private BusinessDataService bdService;
	private IntermediateDataService itmdService;
	private CollectionReceiptDataService crdService;
	private PaymentReceiptDataService prdService;
	
	private CollectionReceiptBL crBL;
	private PaymentReceiptBL prBL;
	
	public boolean approve(String receiptID, Object ob){
		
		//收款单	GatheringReceipt
		if(receiptID.startsWith("SKD")){
			GatheringReceiptVO grvo = (GatheringReceiptVO)ob;
			return approveGatheringReceipt(grvo);
		}
		
		//合计收款单	CollectionReceipt
		else if(receiptID.startsWith("HJSKD")){
			CollectionReceiptVO crvo = (CollectionReceiptVO)ob;
			return approveCollectionReceipt(crvo);
		}
		
		//付款单	PaymentReceipt
		else if(receiptID.startsWith("FKD")){
			PaymentReceiptVO prvo = (PaymentReceiptVO)ob;
			return approvePaymentReceipt(prvo);
		}
		
		//中转中心装车单	EnplaningReceipt
		if(receiptID.startsWith("ZZZXZCD")){
			EnplaningReceiptVO ervo = (EnplaningReceiptVO)ob;
			return approveEnplaningReceipt(ervo);
		}
		
		//中转中心到达单	TransferingReceipt
		else if(receiptID.startsWith("ZZZXDDD")){
			TransferingReceiptVO trvo = (TransferingReceiptVO)ob;
			return approveTransferingReceipt(trvo);
		}
		
		//营业厅装车单	EnVehicleReceipt
		else if(receiptID.startsWith("YYTZCD")){
			EnVehicleReceiptVO evrvo = (EnVehicleReceiptVO)ob;
			return approveEnVehicleReceipt(evrvo);
		}
		
		//营业厅到达单	OrderAcceptReceipt
		else if(receiptID.startsWith("YYTDDD")){
			OrderAcceptReceiptVO oarvo = (OrderAcceptReceiptVO)ob;
			return approveOrderAcceptReceipt(oarvo);
		}
		
		//派件单	DistributeReceipt
		else if(receiptID.startsWith("PJD")){
			DistributeReceiptVO drvo = (DistributeReceiptVO)ob;
			return approveDistributeReceipt(drvo);
		}

		else{
			return false;
		} 
			
	}

	public boolean batch(String[] receiptIDList, ArrayList<Object> obList){
		for(int i=0;i<receiptIDList.length;i++){
			approve(receiptIDList[i], obList.get(i));
		}
		return true;
	}
	
	/*public void reply(String userID){
		receiptBL.reply(userID);
	}*/
	
	public AllReceiptShowVO refresh(){
		return null;
	}
	
	public AllReceiptShowVO getAllReceiptList(){
		ArrayList<GatheringReceiptVO> grvoList = BusinessMainController.gatheringReceiptPOToVO(grPO);
		ArrayList<CollectionReceiptVO> crvoList = crBL.getAllCollection();
		ArrayList<PaymentReceiptVO> prvoList = prBL.getAllPaymentReceipt();
		ArrayList<EnplaningReceiptVO> ervoList = IntermediateMainController.poToVO_EnplaningReceipt(getAllSubmittedEnplaningReceipt());
		ArrayList<TransferingReceiptVO> trvoList = IntermediateMainController.poToVO_TransferingReceiptList(getAllSubmittedTransferingReceipt());
		ArrayList<EnVehicleReceiptVO> evrvoList = BusinessMainController.enVehicleReceiptPOToVO(evrPO);
		ArrayList<OrderAcceptReceiptVO> oarvoList = BusinessMainController.orderAcceptReceiptPOToVO(oarPO);
		ArrayList<DistributeReceiptVO> drvoList = BusinessMainController.distributeReceiptPOToVO(drPO);
		AllReceiptShowVO allReceiptVO = new AllReceiptShowVO(grvoList, crvoList, prvoList, ervoList, trvoList, evrvoList, oarvoList, drvoList);
		return allReceiptVO;
	}
	
	//获取全部提交的收款单
	public ArrayList<GatheringReceiptPO> getAllSubmittedGatheringReceipt(){
		try{
			return bdService.getSubmittedGatheringReceiptInfo();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个收款单
	public boolean approveGatheringReceipt(GatheringReceiptVO grVO){
		GatheringReceiptPO grPO = BusinessMainController.gatheringVOToPO(grVO);
		grPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveGatheringReceiptInfo(grPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
	
	//获取全部提交的合计收款单
	public ArrayList<CollectionReceiptPO> getAllSubmittedCollectionReceipt(){
		try{
			return crdService.getUnapprovedCollectionReceipt();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个合计收款单
	public boolean approveCollectionReceipt(CollectionReceiptVO crVO){
		CollectionReceiptPO crPO = FinanceMainController.cvoToPO(crVO);
		crPO.setState(ReceiptState.APPROVE);
		crdService.saveCollectionReceiptInfo(crPO);
		return true;
	}
	
	//获取全部提交的付款单
	public ArrayList<PaymentReceiptPO> getAllSubmittedPaymentReceipt(){
		try{
			return prdService.getUnapprovedPaymentReceipt();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个付款单
	public boolean approvePaymentReceipt(PaymentReceiptVO crVO){
		PaymentReceiptPO prPO = FinanceMainController.pvoToPO(crVO);
		prPO.setState(ReceiptState.APPROVE);
		prdService.savePaymentReceiptInfo(prPO);
		return true;
	}

	//获取全部提交的中转中心装车单
	public ArrayList<EnplaningReceiptPO> getAllSubmittedEnplaningReceipt(){
		return itmdService.getSubmittedEnIntermediateReceiptInfo();
	}
	
	//审批一个中转中心装车单
	public boolean approveEnplaningReceipt(EnplaningReceiptVO erVO){
		EnplaningReceiptPO erPO = IntermediateMainController.voToPO(erVO);
		erPO.setReceiptState(ReceiptState.APPROVE);
		itmdService.saveEnIntermediateReceiptInfo(erPO);
		return true;
	}
	
	//获取全部提交的中转中心到达单
	public ArrayList<TransferingReceiptPO> getAllSubmittedTransferingReceipt(){
		return itmdService.getSubmittedTransferingReceiptInfo();
	}
	
	//审批一个中转中心到达单
	public boolean approveTransferingReceipt(TransferingReceiptVO trVO){
		TransferingReceiptPO trPO = IntermediateMainController.voToPO(trVO);
		trPO.setReceiptState(ReceiptState.APPROVE);
		itmdService.saveTransferingReceiptInfo(trPO);
		return true;
	}
	
	//获取全部提交的营业厅装车单
	public ArrayList<EnVehicleReceiptPO> getAllSubmittedEnVehicleReceipt(){
		try{
			return bdService.getSubmittedEnVehicleReceiptInfo();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个营业厅装车单
	public boolean approveEnVehicleReceipt(EnVehicleReceiptVO evrVO){
		EnVehicleReceiptPO evrPO = BusinessMainController.enVehicleReceiptVOToPO(evrVO);
		evrPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveEnVehicleReceiptInfo(evrPO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//获取全部提交的营业厅到达单
	public ArrayList<OrderAcceptReceiptPO> getAllSubmittedOrderAcceptReceipt(){
		try{
			return bdService.getSubmittedOrderAcceptReceiptInfo();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个营业厅到达单
	public boolean approveOrderAcceptReceipt(OrderAcceptReceiptVO oarVO){
		OrderAcceptReceiptPO oarPO = BusinessMainController.orderAcceptReceiptVOToPO(oarVO);
		oarPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveOrderAcceptReceiptInfo(oarPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//获取全部提交的派件单
	public ArrayList<DistributeReceiptPO> getAllSubmittedDistributeReceipt(){
		try{
			return bdService.getSubmittedDistributeReceiptInfo();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个派件单
	public boolean approveDistributeReceipt(DistributeReceiptVO drVO){
		DistributeReceiptPO drPO = BusinessMainController.distributeVOToPO(drVO);
		drPO.setReceiptState(ReceiptState.APPROVE);
		try {
			bdService.saveDistributeReceiptInfo(drPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}
