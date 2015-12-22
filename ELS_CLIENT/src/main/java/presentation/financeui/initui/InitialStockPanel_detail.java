package presentation.financeui.initui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.financeui.FinanceFrame;
import type.AuthorityType;
import type.OrganizationType;
import type.ProfessionType;
import vo.AccountVO;
import vo.InitInfoVO;
import vo.OrganizationVO;
import vo.RepertoryVO;
import vo.UserVO;
import vo.VehicleVO;
import businesslogic.businessbl.controller.VehicleManagerController;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.financebl.controller.InitialStockBLController;
import businesslogic.managebl.controller.OrganizationController;
import businesslogic.repertorybl.RepertoryBL;
import businesslogic.userbl.UserBL;

public class InitialStockPanel_detail extends OperationPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel cancelLabel;

	private JLabel function;
	private JLabel humanInfo;
	private JLabel organizationInfo;
	private JLabel vehicleInfo;
	private JLabel stockInfo;
	private JLabel accountInfo;
	
	
	private MyTable currentTable;
	private MyTable userTable;
	private MyTable organizationTable;
	private MyTable vehicleTable;
	private MyTable repertoryTable;
	private MyTable accountTable;
	

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	

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
	//财务主界面
	public FinanceFrame financeFrame;
	public String time;

	 public InitialStockPanel_detail(InitialStockBLController controller,FinanceFrame parent,String time) {
	    this.controller=controller;
		this.financeFrame=parent;
		this.time=time;
		cancelLabel = new JLabel("返回");
		function = new JLabel("期初建账");
		humanInfo = new JLabel("人员信息");
		organizationInfo = new JLabel("机构信息");
		vehicleInfo = new JLabel("车辆信息");
		stockInfo = new JLabel("库存信息");
		accountInfo = new JLabel("银行账户");
		
	
		addListener();
		
		setLayout(null);

		add(cancelLabel);
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
		 int[] widths = new int[]{ 80, 100, 120, 120, 120,80};
		 init_user = controller.getInitInfo(time).userVOs;
		 userTable = new MyTable(head, getUserInfos(init_user), widths, false);
	 }
	 
	 public void setOrganizationBaseInfo(){
		 String head[]={"机构名称","编号","类别"};
		 int[] widths = new int[]{200,200,220};
		 
		 init_organization = controller.getInitInfo(time).organizationVOs;
		 organizationTable = new MyTable(head, getOrganizationInfos(init_organization), widths, false);
		 
	 }
	 
	 public void setVehicleBaseInfo(){
		 String head[]={"编号","所属机构","司机编号","目的机构"};
		 int[] widths = new int[]{180, 130,180, 130};
		 
		 init_vehicle = controller.getInitInfo(time).vehicleVOs;
		 vehicleTable = new MyTable(head, getVehicleInfos(init_vehicle), widths, false);
	 }
	 
	 public void setRepertoryBaseInfo(){
		  String head[]={"仓库编号","仓库管理员编号","排数","架数","位数","警戒比例"};
		  int[] widths = new int[]{ 140, 100, 70, 120, 80,100};
		  
		  init_repertory = controller.getInitInfo(time).repertoryVOs;
		  repertoryTable = new MyTable(head, getRepertoryInfos(init_repertory), widths, false);
	 }
	 
	 public void setAccountBaseInfo(){
		 String head[]={"账户名称","金额"};
		 int[] widths = new int[]{310,310};
		 
		 init_account = controller.getInitInfo(time).accoutVOs;
		 accountTable = new MyTable(head, getAccountInfos(init_account), widths, false);
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
		 //返回
			cancelLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					financeFrame.toMainPanel();
				}
			});
			
	 }
	
	
	public void setCmpLocation(MyTable table){
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		humanInfo.setBounds(PANEL_WIDTH* 6/ 60, PANEL_HEIGHT * 5 / 32,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		organizationInfo.setBounds(PANEL_WIDTH * 40 / 180, PANEL_HEIGHT *5 / 32,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		vehicleInfo.setBounds(PANEL_WIDTH * 60 / 180, PANEL_HEIGHT *5 / 32,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		stockInfo.setBounds(PANEL_WIDTH *80 / 180, PANEL_HEIGHT * 5 / 32,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		accountInfo.setBounds(PANEL_WIDTH * 100 / 180, PANEL_HEIGHT * 5 / 32,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
		cancelLabel.setBounds(PANEL_WIDTH * 15 / 18, PANEL_HEIGHT *46 / 48,
				PANEL_WIDTH *5 / 36, PANEL_HEIGHT *2 / 24);
		table.setLocationAndSize(PANEL_WIDTH *1/ 25, PANEL_HEIGHT * 3 / 14,
				PANEL_WIDTH *47 /50 , PANEL_HEIGHT *73/ 100);
	}
	
	/**
	 * 设置位置
	 * */
	public void setBounds(int x, int y, int width, int height,MyTable table){
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
		add(currentTable);
		setCmpLocation(table);
		currentTable.repaint();
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
     
     
     /*
      * 为了翻页而写的若干个方法
      * 统计当前count页上的数据
      * **/
     public ArrayList<UserVO> getUserOnThisPage(int num){
    	 ArrayList<UserVO> vos = controller.getInitInfo(time).userVOs;
    	 ArrayList<UserVO> userTemp = new ArrayList<UserVO>();
    	 if(vos.size()<=num*8||num<0){
    		 return null;
    	 }
    	 else{
    	 for(int i=8*num;i<=8*num+7;i++){
    		 if(vos.size()>i){
    		 userTemp.add(vos.get(i));
    		 }
    	 }
    	 return userTemp;
     }
     }
    	 
    	 public ArrayList<OrganizationVO> getOrganizationOnThisPage(int num){
        	 ArrayList<OrganizationVO> vos = controller.getInitInfo(time).organizationVOs;
        	 ArrayList<OrganizationVO> organizationTemp = new ArrayList<OrganizationVO>();
        	 if(vos.size()<=num*8||num<0){
        		 return null;
        	 }
        	 else{
        	 for(int i=8*num;i<=8*num+7;i++){
        		 if(vos.size()>i){
        			 organizationTemp.add(vos.get(i));
        		 }
        	 }
        	 return organizationTemp;
         }
    	 }
    	 
    	 public ArrayList<VehicleVO> getVehicleOnThisPage(int num){
        	 ArrayList<VehicleVO> vos = controller.getInitInfo(time).vehicleVOs;
        	 ArrayList<VehicleVO> vehicleTemp = new ArrayList<VehicleVO>();
        	 if(vos.size()<=num*8||num<0){
        		 return null;
        	 }
        	 else{
        	 for(int i=8*num;i<=8*num+7;i++){
        		 if(vos.size()>i){
        			 vehicleTemp.add(vos.get(i));
        		 }
        	 }
        	 return vehicleTemp;
         }
    	 }
     
        	 public ArrayList<RepertoryVO> getRepertoryOnThisPage(int num){
            	 ArrayList<RepertoryVO> vos =controller.getInitInfo(time).repertoryVOs;
            	 ArrayList<RepertoryVO> repertoryTemp = new ArrayList<RepertoryVO>();
            	 if(vos.size()<=num*8||num<0){
            		 return null;
            	 }
            	 else{
            	 for(int i=8*num;i<=8*num+7;i++){
            		 if(vos.size()>i){
            			 repertoryTemp.add(vos.get(i));
            		 }
            	 }
            	 return repertoryTemp;
             }
        	 }
        	 
        	 public ArrayList<AccountVO> getAccountOnThisPage(int num){
            	 ArrayList<AccountVO> vos = controller.getInitInfo(time).accoutVOs;
            	 ArrayList<AccountVO> accountTemp = new ArrayList<AccountVO>();
            	 if(vos.size()<=num*8||num<0){
            		 return null;
            	 }
            	 else{
            	 for(int i=8*num;i<=8*num+7;i++){
            		 if(vos.size()>i){
            			 accountTemp.add(vos.get(i));
            		 }
            	 }
            	 return accountTemp;
             }
        	 }


	

}



