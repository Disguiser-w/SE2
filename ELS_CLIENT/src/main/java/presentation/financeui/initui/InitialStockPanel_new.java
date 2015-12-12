package presentation.financeui.initui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

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
import businesslogic.receiptbl.getDate;
import businesslogic.repertorybl.RepertoryBL;
import businesslogic.userbl.UserBL;

public class InitialStockPanel_new extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel completeLabel;
	private JButton InfoOKButton;
	private JButton cancelButton;
	private JButton next;
	private JButton previous;

	private JCheckBox all;

	private JLabel function;
	private JLabel humanInfo;
	private JLabel organizationInfo;
	private JLabel vehicleInfo;
	private JLabel stockInfo;
	private JLabel accountInfo;
	
	
	private JTable currentTable;
	@SuppressWarnings("unused")
	private JTableHeader currentTableHeader;
	private JTable userTable;
	private JTable organizationTable;
	private JTable vehicleTable;
	private JTable repertoryTable;
	private JTable accountTable;
	
	private ArrayList<JCheckBox> selectedUser;
	private ArrayList<JCheckBox> selectedOrganization;
	private ArrayList<JCheckBox> selectedVehicle;
	private ArrayList<JCheckBox> selectedRepertory;
	private ArrayList<JCheckBox> selectedAccount;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	ListSelectionModel selectionModel;
	private DefaultTableCellRenderer tcr;

	UserModel um;
	OrganizationModel om;
	VehicleModel vm;
	RepertoryModel rm;
	AccountModel am;
	
	ArrayList<ArrayList<String>> user=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> organization=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> vehicle=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> repertory=new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> account=new ArrayList<ArrayList<String>>();
	
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
	
//	private LocationHelper helper;

	 public InitialStockPanel_new(InitialStockBLController controller,UserBL userController,OrganizationController organizationController,
			 VehicleManagerController vehicleController,RepertoryBL repertoryController,AccountBLController accountBLController,FinanceFrame parent) {
	    this.controller=controller;
		this.financeFrame=parent;
		this.userController=userController;
		this.organizationController=organizationController;
		this.vehicleController=vehicleController;
		this.repertoryController=repertoryController;
		this.accountController= accountBLController;
		
		completeLabel  = new JLabel("建账完成");
		InfoOKButton = new JButton("确认添加");
		cancelButton = new JButton("返回");
		next = new JButton("上一页");
		previous = new JButton("下一页");
		all = new JCheckBox("全选");
		function = new JLabel("期初建账");
		humanInfo = new JLabel("人员信息");
		organizationInfo = new JLabel("机构信息");
		vehicleInfo = new JLabel("车辆信息");
		stockInfo = new JLabel("库存信息");
		accountInfo = new JLabel("银行账户");
		
		um = new UserModel(user);
		userTable = new JTable(um);
		om = new OrganizationModel(organization);
		organizationTable = new JTable(om);
		vm = new VehicleModel(vehicle);
		vehicleTable = new JTable(vm);
		rm = new RepertoryModel(repertory);
		repertoryTable = new JTable(rm);
		am =new AccountModel(account);
		accountTable =new JTable(am);
		
		//复选框的初始化
		selectedUser=new ArrayList<JCheckBox>();
		for(int i=0;i<8;i++){
			selectedUser.add(new JCheckBox());
		}
		selectedOrganization=new ArrayList<JCheckBox>();
		for(int i=0;i<8;i++){
			selectedOrganization.add(new JCheckBox());
		}
		selectedVehicle=new ArrayList<JCheckBox>();
		for(int i=0;i<8;i++){
			selectedVehicle.add(new JCheckBox());
		}
		selectedRepertory=new ArrayList<JCheckBox>();
		for(int i=0;i<8;i++){
			selectedRepertory.add(new JCheckBox());
		}
		selectedAccount=new ArrayList<JCheckBox>();
		for(int i=0;i<8;i++){
			selectedAccount.add(new JCheckBox());
		}
		
		addListener();
		
		
		

		setLayout(null);

		add(completeLabel);
		add(InfoOKButton);
		add(cancelButton);
		add(next);
		add(previous);
		add(all);
		add(function);
		add(humanInfo);
		add(organizationInfo);
		add(vehicleInfo);
		add(stockInfo);
		add(accountInfo);
		
		add(userTable.getTableHeader());
		add(userTable);
		add(organizationTable.getTableHeader());
		add(organizationTable);
		add(vehicleTable.getTableHeader());
		add(vehicleTable);
		add(repertoryTable.getTableHeader());
		add(repertoryTable);
		add(accountTable.getTableHeader());
		add(accountTable);		
//		helper = new LocationHelper(this);
		
		currentTable = userTable;
		currentTableHeader = userTable.getTableHeader();
		setTableColor();
		setCmpLocation(currentTable);
		setBaseInfo(currentTable);
		
		setVisible(true);
		

	}
	 
	 //监听方法
	public void addListener(){
		//人员信息显示
		humanInfo.addMouseListener(new MouseAdapter() {
			//显示当前所有user信息
			public void mouseClicked(MouseEvent e) {
				changeTable(userTable);
				int temp=user.size();
				refreshUser(userController.showAllUsers());
				um=new UserModel(user);
				for(int i=0;i<temp;i++){
					um.removeRow(0);
				}
				userTable.repaint();	
			}			
		});		
		//机构信息显示
		organizationInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeTable(organizationTable);
				int temp=organization.size();
				refreshOrganization(organizationController.showAllOrganizations());
				om=new OrganizationModel(organization);
				for(int i=0;i<temp;i++){
					om.removeRow(0);
				}
				organizationTable.repaint();
			}
		});
		//车辆信息显示
		vehicleInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeTable(vehicleTable);
				int temp=vehicle.size();
				System.out.println(vehicleController.getVehicleInfo().size());
				refreshVehicle(vehicleController.getVehicleInfo());
				vm=new VehicleModel(vehicle);
				for(int i=0;i<temp;i++){
					vm.removeRow(0);
				}
				vehicleTable.repaint();
			}
		});
		//仓库初始信息显示
		stockInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		//账户信息显示
		accountInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeTable(accountTable);
				int temp=account.size();
				System.out.println(accountController.showAll().size());
				refreshAccount(accountController.showAll());
				am=new AccountModel(account);
				for(int i=0;i<temp;i++){
					am.removeRow(0);
				}
				accountTable.repaint();				
			}
		});
		 //添加信息（通过复选框选择后确认添加------暂时不使用复选框）
		 InfoOKButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
					addInfo(currentTable);
				}
			});
			
		 //返回
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					financeFrame.toMainPanel();
				}
			});
			
			//期初建账完成(存储期初信息)
			completeLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					initVO=new InitInfoVO(getDate.getdate(), init_user, init_organization, init_vehicle, init_repertory, init_account);
					/*if(init_account.size()==0||init_organization.size()==0||init_repertory.size()==0
							||init_user.size()==0||init_vehicle.size()==0){
						JOptionPane.showMessageDialog(null,"输入的期初信息不完整（一共有五项哟）！", "提示",
								JOptionPane.CLOSED_OPTION);
					}
					else{
					*/
					System.out.println(getDate.getdate());
						controller.initInfo(initVO,getDate.getdate());
//					}
				}
			});

			//下一页
			next.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
				}
			});

			//上一页
			previous.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
				}
			});
		 
	 }
	
	//选中添加信息的具体方法
	public void addInfo(JTable table){
		int row=table.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "请选择需要添加的信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else{
			//当前表格为人员表格
			if(table.equals(userTable)){
				String ID=um.getValueAt(row, 1);
				if(ID==null){
					JOptionPane.showMessageDialog(null,"请选择需要添加的人员！", "提示",
							JOptionPane.CLOSED_OPTION);
				}
				else{
				UserVO vo=userController.findUser(ID);
				init_user.add(vo);
				JOptionPane.showMessageDialog(null, "添加人员成功！", "提示",
						JOptionPane.CLOSED_OPTION);
				um.removeRow(row);
				userTable.repaint();
			
				}
			}
			
			//当前表格为机构表格
			else if(table.equals(organizationTable)){
				String ID=om.getValueAt(row, 1);
				if(ID==null){
					JOptionPane.showMessageDialog(null,"请选择需要添加的机构！", "提示",
							JOptionPane.CLOSED_OPTION);
				}
				else{
					OrganizationVO vo=organizationController.findOrganization(ID);
					init_organization.add(vo);
					JOptionPane.showMessageDialog(null, "添加机构成功！", "提示",
							JOptionPane.CLOSED_OPTION);
					om.removeRow(row);
					organizationTable.repaint();
				}
				}
			
			//当前表格为车辆表格
			else if(table.equals(vehicleTable)){
				String ID=vm.getValueAt(row, 0);
				if(ID==null){
					JOptionPane.showMessageDialog(null, "请选择需要添加的车辆！", "提示",
							JOptionPane.CLOSED_OPTION);
					}
				else{
					ArrayList<VehicleVO> all=vehicleController.getVehicleInfo();
					for(VehicleVO v:all){
						if(v.ID.equals(ID)){
							init_vehicle.add(v);
							JOptionPane.showMessageDialog(null, "添加机构成功！", "提示",
									JOptionPane.CLOSED_OPTION);
							vm.removeRow(row);
							vehicleTable.repaint();
						}
					}
				}
			}
			
			//当前表格为库存表格
			else if(table.equals(repertoryTable)){
				String ID=rm.getValueAt(row, 0);
				if(ID==null){
					JOptionPane.showMessageDialog(null, "请选择需要添加的仓库！", "提示",
							JOptionPane.CLOSED_OPTION);
				}
				else{
					ArrayList<RepertoryVO> all=repertoryController.showAllRepertorys();
					for(RepertoryVO v:all){
						if(v.getRepertoryID().equals(ID)){
							init_repertory.add(v);
							JOptionPane.showMessageDialog(null, "添加仓库成功！", "提示",
									JOptionPane.CLOSED_OPTION);
							rm.removeRow(row);
							repertoryTable.repaint();
						}
					}
				}
			}
			
			//当前表格为账户表格
			else{
				String name=am.getValueAt(row, 0);
				if(name==null){
					JOptionPane.showMessageDialog(null, "请选择需要添加的账户！", "提示",
							JOptionPane.CLOSED_OPTION);
				}
				else{
					AccountVO  vo=accountController.findbyName(name);
					init_account.add(vo);
					JOptionPane.showMessageDialog(null, "添加账户成功！", "提示",
							JOptionPane.CLOSED_OPTION);
					am.removeRow(row);
					accountTable.repaint();
				}
			}
			
			}
		}
	
	

	public void setTableColor(){
		tcr = new DefaultTableCellRenderer(){

			private static final long serialVersionUID = 1L;
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};
		
	}
	
	public void setCmpLocation(JTable table){
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
		completeLabel.setBounds(PANEL_WIDTH *31/ 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		InfoOKButton.setBounds(PANEL_WIDTH * 15 / 18, PANEL_HEIGHT *40 / 48,
				PANEL_WIDTH *5 / 36, PANEL_HEIGHT *2 / 24);
		cancelButton.setBounds(PANEL_WIDTH * 12 / 18, PANEL_HEIGHT * 40 / 48,
				PANEL_WIDTH * 5 / 36, PANEL_HEIGHT *2 / 24);
		next.setBounds(PANEL_WIDTH *21/ 40, PANEL_HEIGHT * 42 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		previous.setBounds(PANEL_WIDTH *19/ 40, PANEL_HEIGHT * 42 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		all.setBounds(PANEL_WIDTH * 5 / 72, PANEL_HEIGHT * 40 / 48,
				PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
	
		table.setBounds(PANEL_WIDTH *3/ 32, PANEL_HEIGHT * 3 / 14+PANEL_HEIGHT *3/ 50,
				PANEL_WIDTH *44 /50 , PANEL_HEIGHT *30/ 50-PANEL_HEIGHT *3/ 50);
		
		table.getTableHeader().setColumnModel(table.getColumnModel());
		table.getTableHeader().setBounds(PANEL_WIDTH *3/ 32, PANEL_HEIGHT * 3 / 14,
				PANEL_WIDTH *44 /50 , PANEL_HEIGHT *3/ 50);
		
		table.setBackground(getBackground());
//		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		currentTableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		table.setRowSelectionAllowed(true);
        selectionModel = table.getSelectionModel();
		
	}
	
	public void setBaseInfo(JTable table){
		// 设置成不可编辑不可改变位置，大小
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getTableHeader().setReorderingAllowed(false);
					table.getTableHeader().setResizingAllowed(false);

					table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) *1/8 );
					tcr.setHorizontalAlignment(JLabel.CENTER);
					//人员 6列
					if(table.equals(userTable)){
						TableColumn column1 = userTable.getColumnModel().getColumn(0);
						TableColumn column2 = userTable.getColumnModel().getColumn(1);
						TableColumn column3 = userTable.getColumnModel().getColumn(2);
						TableColumn column4 = userTable.getColumnModel().getColumn(3);
						TableColumn column5 = userTable.getColumnModel().getColumn(4);
						TableColumn column6 = userTable.getColumnModel().getColumn(5);

						
						column1.setPreferredWidth(userTable.getWidth() * 1 / 10);
						column2.setPreferredWidth(userTable.getWidth() * 2 / 10);
						column3.setPreferredWidth(userTable.getWidth() * 2 / 10);
						column4.setPreferredWidth(userTable.getWidth() * 2 / 10);
						column5.setPreferredWidth(userTable.getWidth() * 2 / 10);
						column6.setPreferredWidth(userTable.getWidth() * 1 / 10);

						column1.setCellRenderer(tcr);
						column2.setCellRenderer(tcr);
						column3.setCellRenderer(tcr);
						column4.setCellRenderer(tcr);
						column5.setCellRenderer(tcr);
						column6.setCellRenderer(tcr);

					}
					//机构 3列
					else if(table.equals(organizationTable)){
					TableColumn column1 = organizationTable.getColumnModel().getColumn(0);
					TableColumn column2 = organizationTable.getColumnModel().getColumn(1);
					TableColumn column3 = organizationTable.getColumnModel().getColumn(2);
				
						
				    column1.setPreferredWidth(organizationTable.getWidth() * 3 / 10);
					column2.setPreferredWidth(organizationTable.getWidth() * 3 / 10);
					column3.setPreferredWidth(organizationTable.getWidth() * 4 / 10);
				
						
					column1.setCellRenderer(tcr);
					column2.setCellRenderer(tcr);
					column3.setCellRenderer(tcr);
					}
					//车辆 4列
					else if(table.equals(vehicleTable)){
						TableColumn column1 = vehicleTable.getColumnModel().getColumn(0);
						TableColumn column2 = vehicleTable.getColumnModel().getColumn(1);
						TableColumn column3 = vehicleTable.getColumnModel().getColumn(2);
						TableColumn column4 = vehicleTable.getColumnModel().getColumn(3);

						column1.setPreferredWidth(vehicleTable.getWidth() * 3 / 10);
						column2.setPreferredWidth(vehicleTable.getWidth() * 2 / 10);
						column3.setPreferredWidth(vehicleTable.getWidth() * 3 / 10);
						column4.setPreferredWidth(vehicleTable.getWidth() * 2 / 10);

						column1.setCellRenderer(tcr);
						column2.setCellRenderer(tcr);
						column3.setCellRenderer(tcr);
						column4.setCellRenderer(tcr);
					}
					//仓库 6列
					else if(table.equals(repertoryTable)){
						TableColumn column1 = repertoryTable.getColumnModel().getColumn(0);
						TableColumn column2 = repertoryTable.getColumnModel().getColumn(1);
						TableColumn column3 = repertoryTable.getColumnModel().getColumn(2);
						TableColumn column4 = repertoryTable.getColumnModel().getColumn(3);
						TableColumn column5 = repertoryTable.getColumnModel().getColumn(4);
						TableColumn column6 = repertoryTable.getColumnModel().getColumn(5);
						
						column1.setPreferredWidth(repertoryTable.getWidth() * 3 / 10);
						column2.setPreferredWidth(repertoryTable.getWidth() * 3 / 10);
						column3.setPreferredWidth(repertoryTable.getWidth() * 1 / 10);
						column4.setPreferredWidth(repertoryTable.getWidth() * 1 / 10);
						column5.setPreferredWidth(repertoryTable.getWidth() * 1 / 10);
						column6.setPreferredWidth(repertoryTable.getWidth() * 1 / 10);

						column1.setCellRenderer(tcr);
						column2.setCellRenderer(tcr);
						column3.setCellRenderer(tcr);
						column4.setCellRenderer(tcr);
						column5.setCellRenderer(tcr);
						column6.setCellRenderer(tcr);					
					}
					
					//账户 2列
					else{
						TableColumn column1 = accountTable.getColumnModel().getColumn(0);
						TableColumn column2 = accountTable.getColumnModel().getColumn(1);
							
					    column1.setPreferredWidth(accountTable.getWidth() * 5 / 10);
						column2.setPreferredWidth(accountTable.getWidth() * 5 / 10);
							
						column1.setCellRenderer(tcr);
						column2.setCellRenderer(tcr);
						
					}

	}
	/**
	 * 设置位置
	 * */
	public void setBounds(int x, int y, int width, int height,JTable table) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation(table);
		repaint();
	}
	
	/**
	 * 表格跳转
	 * */
	public void changeTable(JTable table){
		remove(currentTable);
		currentTable = table;
		add(currentTable);
		setCmpLocation(table);
		setBaseInfo(currentTable);
		currentTable.repaint();
	}

	


	public void okui() {

	}

	public void previousui() {

	}

	public void nextui() {

	}
	
	
	/**
	 * 人员表格原型
	 * */
	class UserModel extends AbstractTableModel{

		private static final long serialVersionUID = 1L;

		//薪水策略应该不要吧
         String head[]={"姓名","编号","职业类型","所属机构","权限","绩点"};
		
		public UserModel(ArrayList<ArrayList<String>> content){
			user=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			if(user==null){
				return 0;
			}
			else{
			return 8;
			}
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(row>user.size()-1){
				return null;
			}
			return user.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {

			user.add(v);
		}

		public void removeRow(int row) {
			user.remove(row);
		}
		
	}
	
	/**
	 * 机构表格原型
	 * */
	class OrganizationModel extends AbstractTableModel{

		private static final long serialVersionUID = 1L;

		   String head[]={"机构名称","编号","类别"};
			
			public OrganizationModel(ArrayList<ArrayList<String>> content){
				organization=content;
			}
			//行数
			public int getRowCount() {
				// TODO Auto-generated method stub
				if(organization==null){
					return 0;
				}
				else{
				return 8;
				}
			}

			public int getColumnCount() {
				// TODO Auto-generated method stub
				return head.length;
			}
			
			public String getValueAt(int row, int col) {
				if(row>organization.size()-1){
					return null;
				}
				return organization.get(row).get(col);
			}

			public String getColumnName(int col) {
				return head[col];
			}

			public void addRow(ArrayList<String> v) {

				organization.add(v);
			}

			public void removeRow(int row) {
				organization.remove(row);
			}
			
		
	}
	/**
	 * 车辆表格原型
	 * */
	
	class VehicleModel extends AbstractTableModel{

		private static final long serialVersionUID = 1L;

		   String head[]={"编号","所属机构","司机编号","目的机构"};
			
			public VehicleModel(ArrayList<ArrayList<String>> content){
				vehicle=content;
			}
			//行数
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 8;
				
			}

			public int getColumnCount() {
				// TODO Auto-generated method stub
				return head.length;
			}
			
			public String getValueAt(int row, int col) {
				if(row>vehicle.size()-1){
					return null;
				}
				return vehicle.get(row).get(col);
			}

			public String getColumnName(int col) {
				return head[col];
			}

			public void addRow(ArrayList<String> v) {
				vehicle.add(v);
			}

			public void removeRow(int row) {
				vehicle.remove(row);
			}
		
	}
	/**
	 * 库存表格原型
	 * */
	
	class RepertoryModel  extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		  String head[]={"仓库编号","仓库管理员编号","排数","架数","位数","警戒比例"};
			
			public RepertoryModel(ArrayList<ArrayList<String>> content){
			repertory=content;
			}
			//行数
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 8;
			}

			public int getColumnCount() {
				// TODO Auto-generated method stub
				return head.length;
			}
			
			public String getValueAt(int row, int col) {
				if(row>repertory.size()-1){
					return null;
				}
				return repertory.get(row).get(col);
			}

			public String getColumnName(int col) {
				return head[col];
			}

			public void addRow(ArrayList<String> v) {
				repertory.add(v);
			}

			public void removeRow(int row) {
				repertory.remove(row);
			}
		
	}
	/**
	 * 账户表格原型
	 * */
class AccountModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
//		ArrayList<ArrayList<String>> account = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"账户名称","金额"};
		
		public AccountModel(ArrayList<ArrayList<String>> content){
			account=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(row>account.size()-1){
				return null;
			}
			return account.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {
			account.add(v);
		}

		public void removeRow(int row) {
			account.remove(row);
		}
		
	}


/**
 * 人员信息表格: "姓名","编号","职业类型","所属机构","权限","绩点"
 * courier, driver, stockman, businessHallCounterman, intermediateCenterCounterman, administrator, financialStaff, manager;
	快递员	 司机	  仓库管理员 		 营业厅业务员				 中转中心业务员，                    管理员			财务人员           	 总经理		
 * lowest,administrator,commonFianacialStaff,highest;
 *  最低权限, 管理员权限 , 普通财务人员权限,     最高权限（最高财务人员和总经理）
 * */
     public void refreshUser(ArrayList<UserVO> vos){
    	 for(UserVO v:vos){
 			ArrayList<String> lineInfo=new ArrayList<String>();
 			lineInfo.add(v.getName());
 			lineInfo.add(v.getID());
 			lineInfo.add(profession(v.getProfession()));
 			lineInfo.add(v.getOrganization());
 			lineInfo.add(authority(v.getAuthority()));
 			lineInfo.add(v.getGrades()+"");
 			user.add(lineInfo);
    	 }
   }
     
/**
 * 机构表格信息: "机构名称","编号","类别"
 * businessHall, intermediateCenter;
//	营业厅                 中转中心
 * */
     public void refreshOrganization(ArrayList<OrganizationVO> vos){
    	 for(OrganizationVO v:vos){
  			ArrayList<String> lineInfo=new ArrayList<String>();
  			lineInfo.add(v.getName());
  			lineInfo.add(v.getOrganizationID());
  			lineInfo.add(category(v.getCategory()));
  			organization.add(lineInfo);
    	 }
     }
     
/**
 * 车辆表格信息
 * "编号","所属机构","司机编号","目的机构"
 * */
     public void refreshVehicle(ArrayList<VehicleVO> vos){
    	 for(VehicleVO v:vos){
    		 ArrayList<String> lineInfo = new ArrayList<String>();
    		 lineInfo.add(v.ID);
    		 lineInfo.add(v.local.getOrganizationID());
    		 lineInfo.add(v.driver.ID);
    		 lineInfo.add(v.destination.getOrganizationID());
    		 vehicle.add(lineInfo);
    	 }
     }
     
/**
 * 仓库初始信息： "仓库编号","仓库管理员编号","排数","架数","位数","警戒比例"
 * */
     public void refreshRepertory(ArrayList<RepertoryVO> vos){
    	 for(RepertoryVO v:vos){
    		 ArrayList<String> lineInfo = new ArrayList<String>();
    		 lineInfo.add(v.getRepertoryID());
    		 lineInfo.add(v.getOwnerID());
    		 lineInfo.add(v.getMaxRow()+"");
    		 lineInfo.add(v.getMaxShelf()+"");
    		 lineInfo.add(v.getMaxDigit()+"");
    		 repertory.add(lineInfo);
    	 }
     }
     
/**
 * 账户信息： "账户名称","金额"
 * */
     public void refreshAccount(ArrayList<AccountVO> vos){
    	 for(AccountVO v:vos){
    		 ArrayList<String> lineInfo = new ArrayList<String>();
    		 lineInfo.add(v.getName());
    		 lineInfo.add(v.getMoney()+"");
    		 account.add(lineInfo);
    	 }
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
    	 String[] authorityList ={ "最低权限", "管理员权限" , "普通财务人员权限", "最高权限(最高财务人员和总经理)"};
    	 return authorityList[n];
     }
     
     //机构类型枚举类转为String
     public String category(OrganizationType type){
    	 int n = type.ordinal();
    	 String[] category = {"营业厅","中转中心"};
    	 return category[n];
     }
	/*public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		InitialStockBLController controller = new InitialStockBLController();
		AccountBLController accountBLController =new AccountBLController();
		UserVO vo=new UserVO("本宝宝", "CW-00001", null, null, null, null, null, 0);
		FinanceFrame financeframe = new FinanceFrame(vo);
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new InitialStockPanel_new(controller,accountBLController,financeframe));
		frame.setVisible(true);
	}
	*/

/*private void setBaseInfo() {

// 设置成不可编辑不可改变位置，大小
accountTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
accountTable.getTableHeader().setReorderingAllowed(false);
accountTable.getTableHeader().setResizingAllowed(false);

TableColumn column1 = accountTable.getColumnModel().getColumn(0);
TableColumn column2 = accountTable.getColumnModel().getColumn(1);
// 设置宽度
column1.setPreferredWidth(accountTable.getWidth() * 5 / 10);
column2.setPreferredWidth(accountTable.getWidth() * 5 / 10);

accountTable.setRowHeight((accountTable.getHeight() - accountTable.getTableHeader().getHeight()) / 8);
DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		if (row % 2 == 0)
			setBackground(Color.cyan);
		else
			setBackground(Color.white);

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
};

tcr.setHorizontalAlignment(JLabel.CENTER);
column1.setCellRenderer(tcr);
column2.setCellRenderer(tcr);

}
*/
     
     /*	public AuthorityType getAuthorityType(String str){
		if(str.equals("最低权限")){
			return AuthorityType.lowest;
		}
		else if(str.equals("管理员权限")){
			return AuthorityType.administrator;
		}
		else if(str.equals("普通财务人员权限")){
			return AuthorityType.commonFianacialStaff;
		}
		else{
			return AuthorityType.highest;
		}
	}
	*/

	

}
