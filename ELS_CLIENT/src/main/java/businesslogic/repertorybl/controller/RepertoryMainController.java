package businesslogic.repertorybl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.ImageGetter;

import businesslogic.datafactory.DataFactory;
import po.UserPO;
import presentation.repertoryui.CreateReceiptPanel;
import presentation.repertoryui.EXwarehousePanel;
import presentation.repertoryui.InitializeInformationPanel;
import presentation.repertoryui.InventoryVerificationPanel;
import presentation.repertoryui.LookReceiptPanel;
import presentation.repertoryui.RepertoryFrame;
import presentation.repertoryui.ViewInventoryPanel;
import presentation.repertoryui.WarehousingMainPanel;
import vo.UserVO;
import dataservice.userdataservice.UserDataService;

public class RepertoryMainController {

	public static UserDataService userData;
	public static UserVO stockManVO;
	
	private RepertoryFrame repertoryFrame;
	
	private RepertoryController repertoryController;
	private GoodsController goodsController;
	private EnterRepertoryReceiptController enterRepertoryReceiptController;
	private LeaveRepertoryReceiptController leaveRepertoryReceiptController;
	
	// UserData的初始化，UserVO的初始化在此进行
	public RepertoryMainController(String stockManID){
	//RMI
		try{
			userData = DataFactory.getUserData();
			
			stockManVO = userPOToVO(userData.findUserByID(stockManID));
		}catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		repertoryController = new RepertoryController(stockManVO.userID);
		goodsController = new GoodsController();
		enterRepertoryReceiptController = new EnterRepertoryReceiptController();
		leaveRepertoryReceiptController = new LeaveRepertoryReceiptController();
		
		repertoryFrame = new RepertoryFrame(stockManVO);
		InitializeInformationPanel initializeInfoPanel = new InitializeInformationPanel(repertoryController, stockManVO);
		WarehousingMainPanel wareHousingMainPanel = new WarehousingMainPanel(repertoryFrame, repertoryController, goodsController, stockManVO);
		EXwarehousePanel exwareHousePanel = new EXwarehousePanel(repertoryFrame, repertoryController, goodsController, stockManVO);
		ViewInventoryPanel viewInventoryPanel = new ViewInventoryPanel(repertoryController, stockManVO);
		InventoryVerificationPanel inventoryVerificationPanel = new InventoryVerificationPanel(repertoryFrame, repertoryController, stockManVO);
		CreateReceiptPanel createReceiptPanel = new CreateReceiptPanel(repertoryFrame, repertoryController, goodsController, enterRepertoryReceiptController, leaveRepertoryReceiptController, stockManVO);
		LookReceiptPanel lookReceiptPanel = new LookReceiptPanel(repertoryFrame, enterRepertoryReceiptController, leaveRepertoryReceiptController, stockManVO);
		
		repertoryFrame.initializeInformationPanel = initializeInfoPanel;
		repertoryFrame.warehousingMainPanel = wareHousingMainPanel;
		repertoryFrame.exwarehousePanel = exwareHousePanel;
		repertoryFrame.viewInventoryPanel = viewInventoryPanel;
		repertoryFrame.inventoryVertificationPanel = inventoryVerificationPanel;
		repertoryFrame.createReceiptPanel = createReceiptPanel;
		repertoryFrame.lookReceiptPanel = lookReceiptPanel;
		
		repertoryFrame.addFuncLabel(initializeInfoPanel, "库存信息初始化", ImageGetter.getImage("initializeInventoryMessage.png").getImage());
		repertoryFrame.addFuncLabel(wareHousingMainPanel, "入库", ImageGetter.getImage("enterRepertory.png").getImage());
		repertoryFrame.addFuncLabel(exwareHousePanel, "出库", ImageGetter.getImage("leaveRepertory.png").getImage());
		repertoryFrame.addFuncLabel(viewInventoryPanel, "库存盘点", ImageGetter.getImage("viewInventory.png").getImage());
		repertoryFrame.addFuncLabel(inventoryVerificationPanel, "库存查看", ImageGetter.getImage("inventoryVertification.png").getImage());
		repertoryFrame.addFuncLabel(createReceiptPanel, "生成单据", ImageGetter.getImage("newReceipt.png").getImage());
		repertoryFrame.addFuncLabel(lookReceiptPanel, "查看单据", ImageGetter.getImage("reviewReceipt.png").getImage());
		repertoryFrame.showFrame();
		
	}
	
	// vo和po的转化,static
	public static UserPO userVOToPO(UserVO uservo){
		UserPO userpo = new UserPO(uservo.userName, uservo.userID, uservo.password, uservo.profession,
					uservo.organization, uservo.salaryPlan, uservo.authority, uservo.grades);
		return userpo;
	}
	
	public static UserVO userPOToVO(UserPO userpo){
		UserVO uservo = new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
}
