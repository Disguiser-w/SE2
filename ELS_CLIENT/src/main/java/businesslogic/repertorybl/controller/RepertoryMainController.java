package businesslogic.repertorybl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businesslogic.datafactory.DataFactory;
import po.UserPO;
import presentation.repertoryui.EXwarehousePanel;
import presentation.repertoryui.InitializeInformationPanel;
import presentation.repertoryui.InventoryVerificationPanel;
import presentation.repertoryui.RepertoryFrame;
import presentation.repertoryui.ViewInventoryPanel;
import presentation.repertoryui.WarehousingPanel;
import vo.UserVO;
import dataservice.userdataservice.UserDataService;

public class RepertoryMainController {

	public static UserDataService userData;
	public static UserVO stockManVO;
	
	private RepertoryFrame repertoryFrame;
	
	// UserData的初始化，UserVO的初始化在此进行
	public RepertoryMainController(String stockManID){
	//RMI
		try{
			userData = DataFactory.getUserData();
		}catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		try{
			stockManVO = userPOToVO(userData.findUserByID(stockManID));
			repertoryFrame = new RepertoryFrame(stockManVO);
			repertoryFrame.addFuncLabel(new InitializeInformationPanel(stockManVO), "库存信息初始化");
			repertoryFrame.addFuncLabel(new WarehousingPanel(stockManVO), "入库");
			repertoryFrame.addFuncLabel(new EXwarehousePanel(stockManVO), "出库");
			repertoryFrame.addFuncLabel(new ViewInventoryPanel(stockManVO),"库存盘点");
			repertoryFrame.addFuncLabel(new InventoryVerificationPanel(stockManVO), "库存查看");
			repertoryFrame.showFrame();
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
		
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
