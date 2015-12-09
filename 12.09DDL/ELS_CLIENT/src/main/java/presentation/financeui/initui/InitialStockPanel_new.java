package presentation.financeui.initui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import dataservice.userdataservice.UserDataService;
import presentation.commonui.LocationHelper;
import presentation.financeui.FinanceFrame;
import vo.UserVO;
import businesslogic.businessbl.controller.VehicleManagerController;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.financebl.controller.InitialStockBLController;
import businesslogic.managebl.controller.OrganizationController;
import businesslogic.repertorybl.RepertoryBL;
import businesslogic.userbl.UserBL;

public class InitialStockPanel_new extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JTableHeader currentTableHeader;
	private JTable userTable;
	private JTable organizationTable;
	private JTable vehicleTable;
	private JTable repertoryTable;
	private JTable accountTable;
	

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
	
	//表格中数据从各个data中取出来
	public InitialStockBLController controller;
	public UserBL userController;
	public OrganizationController organizationController;
	public VehicleManagerController vehicleController;
	public RepertoryBL repertoryController;
	public AccountBLController accountController;
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
		
		addListener();
		
		
		

		setLayout(null);

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
		
		currentTable = accountTable;
		currentTableHeader = accountTable.getTableHeader();
		setTableColor();
		setCmpLocation(currentTable);
		setBaseInfo(currentTable);
		
		setVisible(true);
		

	}
	 
	public void addListener(){
		 //确认添加
		 InfoOKButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
				}
			});
			
		 //返回
			cancelButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					financeFrame.toMainPanel();
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
		
		currentTableHeader.setColumnModel(table.getColumnModel());
		currentTableHeader.setBounds(PANEL_WIDTH *3/ 32, PANEL_HEIGHT * 3 / 14,
				PANEL_WIDTH *44 /50 , PANEL_HEIGHT *3/ 50);
		
		table.setBackground(getBackground());
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		currentTableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		table.setRowSelectionAllowed(true);
        selectionModel = table.getSelectionModel();
		
	}
	
	public void setBaseInfo(JTable table){
		// 设置成不可编辑不可改变位置，大小
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getTableHeader().setReorderingAllowed(false);
					table.getTableHeader().setResizingAllowed(false);

					table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 8);
					tcr.setHorizontalAlignment(JLabel.CENTER);
					
					if(table.equals(userTable)){
						TableColumn column1 = userTable.getColumnModel().getColumn(0);
						TableColumn column2 = userTable.getColumnModel().getColumn(1);
						TableColumn column3 = userTable.getColumnModel().getColumn(2);
						TableColumn column4 = userTable.getColumnModel().getColumn(3);
						TableColumn column5 = userTable.getColumnModel().getColumn(4);
						TableColumn column6 = userTable.getColumnModel().getColumn(5);

						
						column1.setPreferredWidth(userTable.getWidth() * 3 / 10);
						column2.setPreferredWidth(userTable.getWidth() * 2 / 10);
						column3.setPreferredWidth(userTable.getWidth() * 1 / 10);
						column4.setPreferredWidth(userTable.getWidth() * 1 / 10);
						column5.setPreferredWidth(userTable.getWidth() * 1 / 10);
						column6.setPreferredWidth(userTable.getWidth() * 2 / 10);

						column1.setCellRenderer(tcr);
						column2.setCellRenderer(tcr);
						column3.setCellRenderer(tcr);
						column4.setCellRenderer(tcr);
						column5.setCellRenderer(tcr);
						column6.setCellRenderer(tcr);

					}
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
		repaint();
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
			return user.size();
			}
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(user==null){
				System.out.println("null");
				return null;
			}
			else{
			return user.get(row).get(col);
			}
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
				return organization.size();
				}
			}

			public int getColumnCount() {
				// TODO Auto-generated method stub
				return head.length;
			}
			
			public String getValueAt(int row, int col) {
				if(organization==null){
					System.out.println("null");
					return null;
				}
				else{
				return organization.get(row).get(col);
				}
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
				if(vehicle==null){
					return 0;
				}
				else{
				return vehicle.size();
				}
			}

			public int getColumnCount() {
				// TODO Auto-generated method stub
				return head.length;
			}
			
			public String getValueAt(int row, int col) {
				if(vehicle==null){
					System.out.println("null");
					return null;
				}
				else{
				return vehicle.get(row).get(col);
				}
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
				if(repertory==null){
					return 0;
				}
				else{
				return repertory.size();
				}
			}

			public int getColumnCount() {
				// TODO Auto-generated method stub
				return head.length;
			}
			
			public String getValueAt(int row, int col) {
				if(repertory==null){
					System.out.println("null");
					return null;
				}
				else{
				return repertory.get(row).get(col);
				}
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
		String head[]={"账号名称","金额"};
		
		public AccountModel(ArrayList<ArrayList<String>> content){
			account=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub
			if(account==null){
				return 0;
			}
			else{
			return account.size();
			}
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(account==null){
				System.out.println("null");
				return null;
			}
			else{
			return account.get(row).get(col);
			}
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

}
