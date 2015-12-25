package presentation.financeui.initui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.financeui.FinanceFrame;
import type.AuthorityType;
import type.OrganizationType;
import type.ProfessionType;
import vo.AccountVO;
import vo.InitInfoVO;
import vo.LogDiaryVO;
import vo.OrganizationVO;
import vo.RepertoryVO;
import vo.UserVO;
import vo.VehicleVO;
import businesslogic.businessbl.controller.VehicleManagerController;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.financebl.controller.InitialStockBLController;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogic.managebl.controller.OrganizationController;
import businesslogic.receiptbl.getDate;
import businesslogic.repertorybl.RepertoryBL;
import businesslogic.userbl.UserBL;

public class InitialStockPanel_new extends OperationPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyLabel completeLabel;
	private MyLabel InfoOKButton;
	private MyLabel cancelButton;

	

	private MyLabel function;
	private MyLabel humanInfo;
	private MyLabel organizationInfo;
	private MyLabel vehicleInfo;
	private MyLabel stockInfo;
	private MyLabel accountInfo;
	
	
	private MyTable currentTable;
	private MyTable userTable;
	private MyTable organizationTable;
	private MyTable vehicleTable;
	private MyTable repertoryTable;
	private MyTable accountTable;
	

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	ArrayList<UserVO> userVOs;
	ArrayList<OrganizationVO> organizationVOs;
	ArrayList<VehicleVO> vehicleVOs;
	ArrayList<RepertoryVO> repertoryVOs;
	ArrayList<AccountVO> accountVOs;
	
	//最终加入InitInfoVO中的数据
	ArrayList<UserVO> init_user=new ArrayList<UserVO>();
	ArrayList<OrganizationVO> init_organization=new ArrayList<OrganizationVO>();
	ArrayList<VehicleVO> init_vehicle=new ArrayList<VehicleVO>();
	ArrayList<RepertoryVO> init_repertory=new ArrayList<RepertoryVO>();
	ArrayList<AccountVO> init_account=new ArrayList<AccountVO>();
	
	InitInfoVO initVO=new InitInfoVO();
	
	//表格中数据从各个data中取出来
	 InitialStockBLController controller;
	 UserBL userController;
	 OrganizationController organizationController;
	 VehicleManagerController vehicleController;
	 RepertoryBL repertoryController;
	 AccountBLController accountController;
	 
	 public UserVO userVO;
	//财务主界面
	public FinanceFrame financeFrame;
	private InitialStockPanel_main initMainPanel;
	

	 public InitialStockPanel_new(InitialStockBLController controller,UserBL userController,OrganizationController organizationController,
			 VehicleManagerController vehicleController,RepertoryBL repertoryController,AccountBLController accountBLController,FinanceFrame parent,
			 InitialStockPanel_main initMainPanel,UserVO userVO) {
	    this.controller=controller;
		this.financeFrame=parent;
		this.userController=userController;
		this.organizationController=organizationController;
		this.vehicleController=vehicleController;
		this.repertoryController=repertoryController;
		this.accountController= accountBLController;
		this.initMainPanel = initMainPanel;
		this.userVO=userVO;
		
		completeLabel  = new MyLabel("建账完成");
		InfoOKButton = new MyLabel("确认添加");
		cancelButton = new MyLabel("返回");
		function = new MyLabel("期初建账");
		humanInfo = new MyLabel("人员信息");
		organizationInfo = new MyLabel("机构信息");
		vehicleInfo = new MyLabel("车辆信息");
		stockInfo = new MyLabel("库存信息");
		accountInfo = new MyLabel("银行账户");
	
		addListener();

		setLayout(null);

		add(completeLabel);
		add(InfoOKButton);
		add(cancelButton);
		add(function);
		add(humanInfo);
		add(organizationInfo);
		add(vehicleInfo);
		add(stockInfo);
		add(accountInfo);
		
		setUserBaseInfo();
		currentTable = userTable;
		add(currentTable);
		setCmpLocation(currentTable);
		
		setVisible(true);
		

	}
	 
	 /**
	  * 设置表格基本信息
	  * */
	 public void setUserBaseInfo(){
		 String head[]={"姓名","编号","职业类型","所属机构","权限","绩点"};
		 int[] widths = new int[]{ 80, 100, 120, 120, 120,56};
		 userVOs = userController.showAllUsers();
		 userTable = new MyTable(head, getUserInfos(userVOs), widths, true);
	 }
	 
	 public void setOrganizationBaseInfo(){
		 String head[]={"机构名称","编号","类别"};
		 int[] widths = new int[]{200,200,196};
		 
		 organizationVOs = organizationController.showAllOrganizations();
		 organizationTable = new MyTable(head, getOrganizationInfos(organizationVOs), widths, true);
		 
	 }
	 
	 public void setVehicleBaseInfo(){
		 String head[]={"编号","所属机构","司机编号","目的机构"};
		 int[] widths = new int[]{180, 115,180, 121};
		 
		 vehicleVOs = controller.getVehicleInfo();
		 vehicleTable = new MyTable(head, getVehicleInfos(vehicleVOs), widths, true);
	 }
	 
	 public void setRepertoryBaseInfo(){
		  String head[]={"仓库编号","仓库管理员编号","排数","架数","位数","警戒比例"};
		  int[] widths = new int[]{ 140, 100, 70, 120, 66,100};
		  
		  repertoryVOs = repertoryController.showAllRepertorys();
		  repertoryTable = new MyTable(head, getRepertoryInfos(repertoryVOs), widths, true);
	 }
	 
	 public void setAccountBaseInfo(){
		 String head[]={"账户名称","金额"};
		 int[] widths = new int[]{298,298};
		 
		 accountVOs = accountController.showAll();
		 accountTable = new MyTable(head, getAccountInfos(accountVOs), widths, true);
	 }
	 
	 /**
	  * 填充表格中数据
	  * */
	 public ArrayList<String[]> getUserInfos(ArrayList<UserVO> vos){
		 ArrayList<String[]> lineInfos = new ArrayList<String[]>();
		 for(UserVO v : vos){
			String profession = profession(v.profession);
			String authority = authority(v.authority);
			lineInfos.add(new String[]{v.userName,v.userID,profession,v.organization,authority,v.grades+""});
		 }
		 return lineInfos;
	 }
	 
	 public ArrayList<String[]> getOrganizationInfos(ArrayList<OrganizationVO> vos){
		 ArrayList<String[]> lineInfos = new ArrayList<String[]>();
		 for(OrganizationVO v : vos){
			 String category = category(v.category);
			 lineInfos.add(new String[]{v.name,v.organizationID,category});
		 }
		 return lineInfos;
	 }
	 
	 public ArrayList<String[]> getVehicleInfos(ArrayList<VehicleVO> vos){
		 ArrayList<String[]> lineInfos = new ArrayList<String[]>();
		 for(VehicleVO v : vos){
			 lineInfos.add(new String[]{v.ID,v.local.name,v.driver.ID,v.destination.name});
		 }
		 return lineInfos;
	 }
	 
	 public ArrayList<String[]> getRepertoryInfos(ArrayList<RepertoryVO> vos){
		 ArrayList<String[]> lineInfos = new ArrayList<String[]>();
		 for(RepertoryVO v : vos){
			 lineInfos.add(new String[]{v.repertoryID,v.ownerID,v.maxRow+"",v.maxShelf+"",v.maxDigit+"",v.warningRatio+""});
		 }
		 return lineInfos;
	 }
	 
	 public ArrayList<String[]> getAccountInfos(ArrayList<AccountVO> vos){
		 ArrayList<String[]> lineInfos = new ArrayList<String[]>();
		 for(AccountVO v : vos){
			 lineInfos.add(new String[]{v.name,v.money+""});
		 }
		 return lineInfos;
	 }
	 
	 
		public void setCmpLocation(MyTable table){
			
			function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
					PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
			humanInfo.setBounds(PANEL_WIDTH* 4/ 60, PANEL_HEIGHT * 5 / 32,
					PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
			organizationInfo.setBounds(PANEL_WIDTH * 12 / 60, PANEL_HEIGHT *5 / 32,
					PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
			vehicleInfo.setBounds(PANEL_WIDTH * 20 / 60, PANEL_HEIGHT *5 / 32,
					PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
			stockInfo.setBounds(PANEL_WIDTH *28 / 60, PANEL_HEIGHT * 5 / 32,
					PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
			accountInfo.setBounds(PANEL_WIDTH * 36 / 60, PANEL_HEIGHT * 5 / 32,
					PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
			completeLabel.setBounds(PANEL_WIDTH *31/ 36, PANEL_HEIGHT / 24,
					PANEL_WIDTH * 2 / 18, PANEL_HEIGHT / 12);
			InfoOKButton.setBounds(PANEL_WIDTH * 15 / 18, PANEL_HEIGHT *95 / 96,
					PANEL_WIDTH *5 / 36, PANEL_HEIGHT *1 / 16);
			cancelButton.setBounds(PANEL_WIDTH * 12 / 18, PANEL_HEIGHT * 95 / 96,
					PANEL_WIDTH * 5 / 36, PANEL_HEIGHT *1 / 16);
			table.setLocationAndSize(PANEL_WIDTH *1/ 25, PANEL_HEIGHT * 7 / 28,
					PANEL_WIDTH *47 /50 , PANEL_HEIGHT *73/ 100);
		}
		

		/**
		 * 设置位置
		 * */
		public void setBounds(int x, int y, int width, int height,MyTable table) {

			super.setBounds(x, y, width, height);
			PANEL_WIDTH = width;
			PANEL_HEIGHT = height;
			setCmpLocation(table);
			repaint();
		}
		
		/**
		 * 表格跳转
		 * */
		public void changeTable(MyTable table){
			remove(currentTable);
			currentTable = table;
			
			setCmpLocation(currentTable);
			add(currentTable);
			currentTable.repaint();
			updateUI();
		}

		
	 //监听方法
	public void addListener(){
		//人员信息显示
		humanInfo.addMouseListener(new MouseAdapter() {
			//显示当前所有user信息
			public void mouseClicked(MouseEvent e) {
				setUserBaseInfo();
				changeTable(userTable);
				userTable.repaint();	
			}			
		});		
		//机构信息显示
		organizationInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setOrganizationBaseInfo();
				changeTable(organizationTable);
				organizationTable.repaint();
			}
		});
		//车辆信息显示
		vehicleInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVehicleBaseInfo();
				changeTable(vehicleTable);
				vehicleTable.repaint();
			}
		});
		//仓库初始信息显示
		stockInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setRepertoryBaseInfo();
				changeTable(repertoryTable);
				repertoryTable.repaint();
			}
		});
		//账户信息显示
		accountInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setAccountBaseInfo();
				changeTable(accountTable);
				accountTable.repaint();				
			}
		});
		 //添加信息（支持复选）
		 InfoOKButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					addInfo(currentTable);			
				}
			});
			
			
		 //返回
			cancelButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					initMainPanel.refresh();
					financeFrame.toMainPanel();
				}
			});
			
			//期初建账完成(存储期初信息)
			completeLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					initVO=new InitInfoVO(getDate.getdate(), userVO.userID,init_user, init_organization, init_vehicle, init_repertory, init_account);
					if(init_account.size()==0||init_organization.size()==0||init_repertory.size()==0
							||init_user.size()==0||init_vehicle.size()==0){
						JOptionPane.showMessageDialog(null,"输入的期初信息不完整（一共有五项哟）！", "提示",
								JOptionPane.CLOSED_OPTION);
					}
					else{
					LogDiaryBL bl = new LogDiaryBL();
					LogDiaryVO vo = new LogDiaryVO(getDate.getdate().substring(0, 4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate().substring(6), userVO, "新建了一套账");
					bl.addLogDiary(vo, getDate.getdate().substring(0, 4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate().substring(6));
					ArrayList<InitInfoVO> vos=controller.getAllInitInfo();
					if(vos==null){
						controller.initInfo(initVO,getDate.getdate());
						JOptionPane.showMessageDialog(null,"建账成功！", "提示",
								JOptionPane.CLOSED_OPTION);
					}
					else{
					boolean isExsit=false;
					for(InitInfoVO v:vos){
						if(v.time.equals(getDate.getdate())){
							isExsit=true;
						}
					}
					if(isExsit==true){
						JOptionPane.showMessageDialog(null,"建账失败（该日期总账已存在）！", "提示",
								JOptionPane.WARNING_MESSAGE);
					}
					else{
						controller.initInfo(initVO,getDate.getdate());
						JOptionPane.showMessageDialog(null,"建账成功！", "提示",
								JOptionPane.CLOSED_OPTION);
					}
					}
					}
				}
			});

		 
	 }
	
	//选中添加信息的具体方法
	public void addInfo(MyTable table){
			//当前表格为人员表格
			if(table.equals(userTable)){
				ArrayList<Integer> selectedIndexs = userTable.getSelectedIndex();
				int size = selectedIndexs.size();
				if (size == 0)
					return;
				else {
					for (int i : selectedIndexs){
						init_user.add(userVOs.get(i));
					}
				}
				JOptionPane.showMessageDialog(null, "添加人员成功！", "提示",
						JOptionPane.CLOSED_OPTION);
			}
			
			//当前表格为机构表格
			else if(table.equals(organizationTable)){
				ArrayList<Integer> selectedIndexs = organizationTable.getSelectedIndex();
				int size = selectedIndexs.size();
				if (size == 0)
					return;
				else {
					for (int i : selectedIndexs){
						init_organization.add(organizationVOs.get(i));
					}
				}
					JOptionPane.showMessageDialog(null, "添加机构成功！", "提示",
							JOptionPane.CLOSED_OPTION);
				}
			
			//当前表格为车辆表格
			else if(table.equals(vehicleTable)){
				ArrayList<Integer> selectedIndexs = vehicleTable.getSelectedIndex();
				int size = selectedIndexs.size();
				if (size == 0)
					return;
				else {
					for (int i : selectedIndexs){
						init_vehicle.add(vehicleVOs.get(i));
					}
				}
				JOptionPane.showMessageDialog(null, "添加车辆成功！", "提示",
						JOptionPane.CLOSED_OPTION);
			}
			
			//当前表格为库存表格
			else if(table.equals(repertoryTable)){
				ArrayList<Integer> selectedIndexs = repertoryTable.getSelectedIndex();
				int size = selectedIndexs.size();
				if (size == 0)
					return;
				else {
					for (int i : selectedIndexs){
						init_repertory.add(repertoryVOs.get(i));
					}
				}
				JOptionPane.showMessageDialog(null, "添加库存信息成功！", "提示",
						JOptionPane.CLOSED_OPTION);
			}
			
			//当前表格为账户表格
			else{
				ArrayList<Integer> selectedIndexs = accountTable.getSelectedIndex();
				int size = selectedIndexs.size();
				if (size == 0)
					return;
				else {
					for (int i : selectedIndexs){
						init_account.add(accountVOs.get(i));
					}
				}
				JOptionPane.showMessageDialog(null, "添加库存信息成功！", "提示",
						JOptionPane.CLOSED_OPTION);
				}
		}



	public void okui() {

	}
	
	

     //职业枚举类转为string
     public String profession(ProfessionType type){
    	 int n = type.ordinal();
 		String[] professionNameList = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","管理员","财务人员","总经理"};
 		return professionNameList[n];
     }
     
     //权限枚举类转为String
     public String authority(AuthorityType type){
    	 int n = type.ordinal();
    	 String[] authorityList ={ "最低权限", "管理员权限" , "普通财务人员权限", "最高权限"};
    	 return authorityList[n];
     }
     
     //机构类型枚举类转为String
     public String category(OrganizationType type){
    	 int n = type.ordinal();
    	 String[] category = {"营业厅","中转中心"};
    	 return category[n];
     }
     
     
  

	

}
