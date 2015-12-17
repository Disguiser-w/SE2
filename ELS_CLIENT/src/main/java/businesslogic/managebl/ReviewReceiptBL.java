package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import type.OperationState;
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
import po.EnIntermediateReceiptPO;
import po.GatheringReceiptPO;
import po.TransferingReceiptPO;
import po.EnplaningReceiptPO;
import po.EnVehicleReceiptPO;
import po.OrderAcceptReceiptPO;
import po.DistributeReceiptPO;
import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.financebl.CollectionReceiptBL;
import businesslogic.financebl.PaymentReceiptBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogicservice.manageblservice.ReviewReceiptBLService;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.intermediatedataservice.IntermediateDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.PaymentReceiptDataService;

public class ReviewReceiptBL implements ReviewReceiptBLService{

	private BusinessDataService bdService;
	private IntermediateDataService itmdService;
	
	private CollectionReceiptBL collectionBL;
	private PaymentReceiptBL paymentBL;
	
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
	
	public ReviewReceiptBL(){
		try{
			bdService = (BusinessDataService)Naming.lookup("rmi://localhost:8888/BusinessDataService");
			itmdService = (IntermediateDataService)Naming.lookup("rmi://localhost:8888/IntermediateDataService");
			
			collectionBL = new CollectionReceiptBL();
			paymentBL = new PaymentReceiptBL();

		}catch(RemoteException | MalformedURLException | NotBoundException ex){
			ex.printStackTrace();
		}
	}
	
	public int approve(String receiptID, Object ob){
		
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
			return 0;
		} 
			
	}

	public int batch(String[] receiptIDList, ArrayList<Object> obList){
		for(int i=0;i<receiptIDList.length;i++){
			approve(receiptIDList[i], obList.get(i));
		}
		return 0;
	}
	
	public void reply(String userID){
		//receiptBL.reply(userID);
	}
	
	public AllReceiptShowVO refresh(){
		return null;
	}
	
	public AllReceiptShowVO getAllReceiptList(){
		ArrayList<GatheringReceiptVO> gatheringReceiptVOList = getAllSubmittedGatheringReceipt();
		collectionReceiptVOList = collectionBL.getAllCollection();
		paymentReceiptVOList = paymentBL.getAllPaymentReceipt();
		//ArrayList<EnplaningReceiptVO> ervoList = IntermediateMainController.getAllSubmittedEnplaningReceipt();
		//ArrayList<TransferingReceiptVO> trvoList = IntermediateMainController.poToVO_TransferingReceiptList(getAllSubmittedTransferingReceipt());
		/*ArrayList<EnVehicleReceiptVO> evrvoList = BusinessMainController.enVehicleReceiptPOToVO(evrPO);
		ArrayList<OrderAcceptReceiptVO> oarvoList = BusinessMainController.orderAcceptReceiptPOToVO(oarPO);
		ArrayList<DistributeReceiptVO> drvoList = BusinessMainController.distributeReceiptPOToVO(drPO);
		AllReceiptShowVO allReceiptVO = new AllReceiptShowVO(grvoList, crvoList, prvoList, ervoList, trvoList, evrvoList, oarvoList, drvoList);*/
		//return allReceiptVO;
		return null;
	}
	
	//获取全部提交的收款单
	public ArrayList<GatheringReceiptVO> getAllSubmittedGatheringReceipt(){
		try{
			gatheringReceiptPOList = bdService.getSubmittedGatheringReceiptInfo();
			gatheringReceiptVOList = new ArrayList<GatheringReceiptVO>();
			
			for(int i=0;i<gatheringReceiptPOList.size();i++){
				gatheringReceiptVOList.add(BusinessMainController.gatheringPOToVO(gatheringReceiptPOList.get(i)));
			}
			return gatheringReceiptVOList;
		}catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个收款单
	public int approveGatheringReceipt(GatheringReceiptVO grVO){
		GatheringReceiptPO grPO = BusinessMainController.gatheringVOToPO(grVO);
		grPO.setReceiptState(ReceiptState.APPROVE);
		try{
			bdService.saveGatheringReceiptInfo(grPO);
			return 0;
		}catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}	
	
	//获取全部提交的、待审批的合计收款单
	public ArrayList<CollectionReceiptVO> getAllSubmittedCollectionReceipt(){
		collectionReceiptVOList = collectionBL.getUnapprovedCollectionReceipt();
		return collectionReceiptVOList;
	}
	
	//审批一个合计收款单(BL层的方法呢？？？)
	public int approveCollectionReceipt(CollectionReceiptVO crVO){
		/*CollectionReceiptPO crPO = FinanceMainController.cvoToPO(crVO);
		crPO.setState(ReceiptState.APPROVE);
		crdService.saveCollectionReceiptInfo(crPO);
		*/
		return 0;
	}
	
	//获取全部提交的、待审批的付款单
	public ArrayList<PaymentReceiptVO> getAllSubmittedPaymentReceipt(){
		paymentReceiptVOList = paymentBL.getUnapprovedPaymentReceipt();
		return paymentReceiptVOList;
	}
	
	//审批一个付款单(BL层的方法呢？？？)
	public int approvePaymentReceipt(PaymentReceiptVO crVO){
		/*PaymentReceiptPO prPO = FinanceMainController.pvoToPO(crVO);
		prPO.setState(ReceiptState.APPROVE);
		try {
			prdService.saveSubmittedPaymentReceiptInfo(prPO);
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}*/
	}

	//获取全部提交的中转中心装车单
	public ArrayList<EnIntermediateReceiptVO> getAllSubmittedEnplaningReceipt(){
		try{
			enIntermediateReceiptPOList = itmdService.getSubmittedEnIntermediateReceiptInfo();
			enIntermediateReceiptVOList = new ArrayList<EnIntermediateReceiptVO>();
			
			for(int i=0; i<enIntermediateReceiptPOList.size();i++){
				enIntermediateReceiptVOList.add(IntermediateMainController.poToVO(enIntermediateReceiptPOList.get(i)));
			}
			return enIntermediateReceiptVOList;
		}catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个中转中心装车单
	public int approveEnplaningReceipt(EnIntermediateReceiptVO erVO){
		EnplaningReceiptPO erPO = IntermediateMainController.voToPO(erVO);
		erPO.setReceiptState(ReceiptState.APPROVE);
		try{
			itmdService.saveEnIntermediateReceiptInfo(erPO);
			return 0;
		}catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	//获取全部提交的中转中心到达单
	public ArrayList<TransferingReceiptVO> getAllSubmittedTransferingReceipt(){
		try{
			transferingReceiptPOList = itmdService.getSubmittedTransferingReceiptInfo();
			transferingReceiptVOList = new ArrayList<TransferingReceiptVO>();
			
			for(int i=0;i<transferingReceiptPOList.size();i++){
				transferingReceiptVOList.add(IntermediateMainController.poToVO(transferingReceiptPOList.get(i))); 
			}
			return transferingReceiptVOList;
		}catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个中转中心到达单
	public int approveTransferingReceipt(TransferingReceiptVO trVO){
		TransferingReceiptPO trPO = IntermediateMainController.voToPO(trVO);
		trPO.setReceiptState(ReceiptState.APPROVE);
		try{
			itmdService.saveTransferingReceiptInfo(trPO);
			return 0;
		}catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	//获取全部提交的营业厅装车单
	public ArrayList<EnVehicleReceiptVO> getAllSubmittedEnVehicleReceipt(){
		try{
			enVehicleReceiptPOList = bdService.getSubmittedEnVehicleReceiptInfo();
			enVehicleReceiptVOList = new ArrayList<EnVehicleReceiptVO>();
			
			for(int i=0; i<enVehicleReceiptPOList.size();i++){
				enVehicleReceiptVOList.add(BusinessMainController.enVehicleReceiptPOToVO(enVehicleReceiptPOList.get(i)));
			}
			return enVehicleReceiptVOList;
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个营业厅装车单
	public int approveEnVehicleReceipt(EnVehicleReceiptVO evrVO){
		EnVehicleReceiptPO evrPO = BusinessMainController.enVehicleReceiptVOToPO(evrVO);
		evrPO.setReceiptState(ReceiptState.APPROVE);
		try{
			bdService.saveEnVehicleReceiptInfo(evrPO);
			return 0;
		}catch (RemoteException e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	//获取全部提交的营业厅到达单
	public ArrayList<OrderAcceptReceiptVO> getAllSubmittedOrderAcceptReceipt(){
		try{
			orderAcceptReceiptPOList = bdService.getSubmittedOrderAcceptReceiptInfo();
			orderAcceptReceiptVOList = new ArrayList<OrderAcceptReceiptVO>();
			
			for(int i=0;i<orderAcceptReceiptPOList.size();i++){
				orderAcceptReceiptVOList.add(BusinessMainController.orderAcceptReceiptPOToVO(orderAcceptReceiptPOList.get(i))); 
			}
			return orderAcceptReceiptVOList;
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个营业厅到达单
	public int approveOrderAcceptReceipt(OrderAcceptReceiptVO oarVO){
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
	
	//获取全部提交的派件单
	public ArrayList<DistributeReceiptVO> getAllSubmittedDistributeReceipt(){
		try{
			distributeReceiptPOList = bdService.getSubmittedDistributeReceiptInfo();
			distributeReceiptVOList = new ArrayList<DistributeReceiptVO>();
			
			for(int i=0;i<distributeReceiptPOList.size();i++){
				distributeReceiptVOList.add(BusinessMainController.distributePOToVO(distributeReceiptPOList.get(i)));
			}
			return distributeReceiptVOList;
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审批一个派件单
	public int approveDistributeReceipt(DistributeReceiptVO drVO){
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
	
}
