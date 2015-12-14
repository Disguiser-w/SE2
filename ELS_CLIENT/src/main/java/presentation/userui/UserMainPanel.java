package presentation.userui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

import java.util.Vector;

import businesslogic.userbl.UserBL;
import vo.UserVO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class UserMainPanel extends JPanel implements ListSelectionListener{
	
	private static final long serialVersionUID = 2L;

	private AdminFrame adminFrame;
	
	private UserBL userBL;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private int pageNum;	//pageNum用来计表格的页数，从0开始计数
	private boolean isFindPattern;	//isFindPattern表示是不是搜索模式
	
	private JLabel function;

	private JButton addButton;
	private JButton deleteButton;
	private JButton modifyButton;
	private JButton searchButton;
	private JTextField searchTextField;
	private JButton refreshButton;
	
	private AllMessageTableModel allModel;
	private FindMessageTableModel findModel;
	private JTable allInfoTable;
	private JTable findInfoTable;
	private JLabel messageLabel;
	
	private JButton previousPage;
	private JButton nextPage;

	ListSelectionModel selectionMode;
	
	public UserMainPanel(AdminFrame adminFrame) {
		
		this.adminFrame = adminFrame;
		
		this.userBL = new UserBL();
		
		pageNum = 0;
		isFindPattern = false;
		
        function = new JLabel("用户管理 ");
        
        addButton = new JButton("新增用户");
        deleteButton = new JButton("删除用户");
        modifyButton = new JButton("修改用户信息");
        searchTextField = new JTextField("用户编号");
        searchButton = new JButton("查找");
        refreshButton = new JButton("刷新");
        
        previousPage = new JButton("上一页");
        nextPage = new JButton("下一页");
        
        allModel = new AllMessageTableModel();
		allInfoTable = new JTable(allModel);
		findModel = new FindMessageTableModel();
		findInfoTable = new JTable(findModel);
		messageLabel = new JLabel();
        
		
		//加监听
        addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addui();
			}
		});
        
        deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int chosenRow = -1; 
				String ID = "";
				if(isFindPattern){
					 chosenRow = findInfoTable.getSelectedRow();
					 ID = (String)(findModel.getValueAt(chosenRow, 1));
				}
				else{
					chosenRow = allInfoTable.getSelectedRow();
					ID = (String)(allModel.getValueAt(chosenRow,1));
				}
				//System.out.println("要删除编号为"+ID+"的员工");
				if(chosenRow != -1){
					userBL.deleteUser(ID);
					updateUI();//刷新
				}
				else{
        			warnning("没有选择要删除的用户");
        		}
			}
		});
        
        modifyButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		int chosenRow = -1; 
				if(isFindPattern)
					 chosenRow = findInfoTable.getSelectedRow();
				else
					chosenRow = allInfoTable.getSelectedRow();
				
        		if(chosenRow != -1){
        			modifyui(chosenRow);
        		}
        		else{
        			warnning("没有选择待修改的用户");
        		}
        	}
        });
        
        searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userID = searchTextField.getText();
				if(userID.equals("用户编号") || userID.equals("")){
					allui();
				}
				else
					searchui();
			}
		});
        
        refreshButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		allui();
        		findInfoTable.updateUI();
				allInfoTable.updateUI();
        	}
        });
        
        
        //由于查找出来的员工只有一个，不需要涉及到翻页功能，所以只调用了对应全部信息Table的setModel和setBaseInfo方法
        //如果改成按关键字查询，可能查出来的结果比较多，就可能涉及到翻页功能
        //以后如果要改成按关键字查询功能，记得这里也要改！！！
        previousPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (pageNum == 0)
					return;
				else {
					pageNum--;
					allInfoTable.setModel(new AllMessageTableModel());
					setBaseInfo(allInfoTable);
				}
			}
		});

		nextPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (pageNum >= (userBL.showAllUsers().size() - 2) / 10)
					return;
				else {
					pageNum++;
					allInfoTable.setModel(new AllMessageTableModel());
					setBaseInfo(allInfoTable);
				}
			}
		});
        
		
		//把组件加到Panel上
        setLayout(null);

        add(function);
        add(addButton);
        add(deleteButton);
        add(modifyButton);
        add(searchTextField);
        add(searchButton);
        add(refreshButton);
        add(allInfoTable.getTableHeader());
        add(allInfoTable);
        add(findInfoTable);
        add(previousPage);
        add(nextPage);
        
        setVisible(true);
        
        //颜色、边界、位置、JTable上信息显示设置
        setBorder();
        setCmpLocation(allInfoTable);
        setBaseInfo(allInfoTable);
        setInfos();
	}
	
	
	//覆盖Selection事件被触发后原有的方法
	public void valueChanged(ListSelectionEvent lse){
     	//System.out.println("你选了第"+allInfoTable.getSelectedRow()+"行");
	}
	
	//设置边界
	public void setBorder(){
		previousPage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	//设置位置
	public void setCmpLocation(JTable table){
		
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		
		addButton.setBounds(PANEL_WIDTH * 1 / 18, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		deleteButton.setBounds(PANEL_WIDTH * 13 / 54, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		modifyButton.setBounds(PANEL_WIDTH * 23 / 54, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		searchTextField.setBounds(PANEL_WIDTH * 12 / 18, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 4 / 27, PANEL_HEIGHT / 16);
		searchButton.setBounds(PANEL_WIDTH * 22 / 27, PANEL_HEIGHT * 2 / 16,
				PANEL_WIDTH * 3 / 27, PANEL_HEIGHT / 16);
		refreshButton.setBounds(PANEL_WIDTH * 24 / 27, PANEL_HEIGHT * 1 / 24,
				PANEL_WIDTH * 3 / 27, PANEL_HEIGHT / 16);
		allInfoTable.getTableHeader().setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 13 / 60, 
				PANEL_WIDTH * 66 / 72, PANEL_HEIGHT * 1 / 20);
		
		previousPage.setBounds(PANEL_WIDTH * 52 / 72, PANEL_HEIGHT * 7 / 8,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
		nextPage.setBounds(PANEL_WIDTH * 62 / 72, PANEL_HEIGHT * 7 / 8,
				PANEL_WIDTH / 9, PANEL_HEIGHT / 16);
		
		table.setBounds(PANEL_WIDTH / 18, PANEL_HEIGHT * 4 / 15,
				PANEL_WIDTH * 66 / 72, PANEL_HEIGHT * 12 / 20);
		
		table.setBackground(getBackground());
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		table.setRowSelectionAllowed(true);
        selectionMode = table.getSelectionModel();
        selectionMode.addListSelectionListener(this);
	}
	
	public void setBounds(int x, int y, int width, int height , JTable table) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation(table);
		repaint();
	}

	//设置Table初始显示的信息
	private void setBaseInfo(JTable table) {

		// 设置成不可编辑不可改变位置，大小
		//infoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		TableColumn column0 = table.getColumnModel().getColumn(0);
		TableColumn column1 = table.getColumnModel().getColumn(1);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		TableColumn column3 = table.getColumnModel().getColumn(3);
		TableColumn column4 = table.getColumnModel().getColumn(4);
		TableColumn column5 = table.getColumnModel().getColumn(5);

		// 设置宽度
		column0.setPreferredWidth(table.getWidth() * 8 / 48);
		column1.setPreferredWidth(table.getWidth() * 6 / 48);
		column2.setPreferredWidth(table.getWidth() * 8 / 48);
		column3.setPreferredWidth(table.getWidth() * 8 / 48);
		column4.setPreferredWidth(table.getWidth() * 9 / 48);
		column5.setPreferredWidth(table.getWidth() * 9 / 48);

		table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 10);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			
			private static final long serialVersionUID = 6L;

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
		column0.setCellRenderer(tcr);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);
		column5.setCellRenderer(tcr);
	}

	//设置JTable载入动态的内容
	private void setInfos() {
		if (userBL.showAllUsers().size() != 0)
			messageLabel.setText("姓名: "+"  用户编号 : "+"  职位 : ");
		else
			messageLabel.setText("姓名: 无"+"  用户编号 : 无"+"  职位 : 无");
		
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
	}
	
	
	//新增用户界面
	public void addui() {
		
		/*int width = fatherFrame.getWidth();
		int height = fatherFrame.getHeight();
		
		JFrame addFrame = new JFrame();
		addFrame.setSize(780, 455);
		addFrame.setVisible(true);
		addFrame.setBounds(width * 99 / 400, height * 7 / 25, 780, 450);
		
		AddDialog addDialog = new AddDialog(addFrame);
		addDialog.setFocusable(true);*/
		
		adminFrame.changePanel(new AddUserPanel(adminFrame, this));
	}

	//修改用户信息界面
	public void modifyui(int chosenRow){
	
		String userName;
		String userID;
		String userProfession;
		String userOrganization;
		String userSalaryPlan;
	
		//如果现在是搜索模式，就载入搜索Table被选中的行的信息
		if(isFindPattern){
			if(((String)findInfoTable.getModel().getValueAt(chosenRow, 2)).equals("财务人员")){
				//只有财务人员才有修改权限的需要
				userName = (String)findInfoTable.getModel().getValueAt(chosenRow, 0);
				userID = (String)findInfoTable.getModel().getValueAt(chosenRow, 1);
				userProfession = (String)findInfoTable.getModel().getValueAt(chosenRow, 2);
				userOrganization = (String)findInfoTable.getModel().getValueAt(chosenRow, 3);
				userSalaryPlan = (String)findInfoTable.getModel().getValueAt(chosenRow, 4);
				
				adminFrame.changePanel(new ModifyUserAuthorityPanel(adminFrame, this, userName, userID, userProfession, userOrganization, userSalaryPlan));
			}
			else{
				warnning("该用户不是财务人员，不需要修改权限");
			}
		}
		
		//如果现在不是搜索模式，就载入全部信息Table被选中的行的信息
		else{
			if(((String)allInfoTable.getModel().getValueAt(chosenRow, 2)).equals("财务人员")){
				//只有财务人员才有修改权限的需要
				userName = (String)allInfoTable.getModel().getValueAt(chosenRow, 0);
				userID = (String)allInfoTable.getModel().getValueAt(chosenRow, 1);
				userProfession = (String)allInfoTable.getModel().getValueAt(chosenRow, 2);
				userOrganization = (String)allInfoTable.getModel().getValueAt(chosenRow, 3);
				userSalaryPlan = (String)allInfoTable.getModel().getValueAt(chosenRow, 4);
				
				adminFrame.changePanel(new ModifyUserAuthorityPanel(adminFrame, this, userName, userID, userProfession, userOrganization, userSalaryPlan));
			}
			else{
				warnning("该用户不是财务人员，不需要修改权限");
			}
		}
		
	}
	
	//显示全部用户信息页面
	public void allui(){
		this.remove(findInfoTable);
		this.add(allInfoTable);
		setCmpLocation(allInfoTable);
        setBaseInfo(allInfoTable);
        setInfos();
        isFindPattern = false;
        searchTextField.setText("用户编号");
        updateUI();
	}
	
	//显示搜索用户信息页面
	public void searchui(){
		this.remove(allInfoTable);
		this.add(findInfoTable);
		setCmpLocation(findInfoTable);
        setBaseInfo(findInfoTable);
        setInfos();
        isFindPattern = true;
        updateUI();
	}
	
	
	//根据不同的职业类型返回职业名，给表去显示
	public String professionName(ProfessionType profession){
		int n = profession.ordinal();
		String[] professionNameList = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","财务人员","总经理"};
		if(n<5)
			return professionNameList[n];
		else
			return professionNameList[n-1];
	}
	
	//根据不同的薪水类型返回薪水类型名，给表去显示
	public String salaryPlanName(SalaryPlanType salaryPlan){
		int n = salaryPlan.ordinal();
		String[] salaryPlanNameList = {"基础月薪+提成","计次提成","基础月薪"};
		return salaryPlanNameList[n];
	}
	
	//根据不同的权限类型返回权限名，给表去显示
	public String authorityName(AuthorityType authority){
		int n = authority.ordinal();
		String[] authorityNameList = {"最低权限", "普通财务人员权限", "最高权限"};
		if(n<1)
			return authorityNameList[n];
		else
			return authorityNameList[n-1];
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "用户信息错误", JOptionPane.ERROR_MESSAGE);
	}

	
	//全部用户信息对应的表的Model
	public class AllMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 4L;
		
		Vector<UserVO> userVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 6;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			userVector = new Vector<UserVO>();
			for(int i=0;i<userBL.showAllUsers().size();i++){
				UserVO userVO = userBL.showAllUsers().get(i);
				if(!userVO.getProfession().equals(ProfessionType.administrator))
					userVector.add(userVO);
			}
			
			int index = pageNum * 10 + rowIndex;

				if (index > userVector.size()-1)
					return null;
				UserVO uservo = (UserVO)userVector.get(index);
	
				if (uservo != null) {
					if(columnIndex==0)
						return uservo.getName();
					else if(columnIndex==1)
						return uservo.getID();
					else if(columnIndex==2)
						return professionName(uservo.getProfession());
					else if(columnIndex==3)
						return uservo.getOrganization();
					else if(columnIndex==4)
						return salaryPlanName(uservo.getSalaryPlan());
					else if(columnIndex==5)
						return authorityName(uservo.getAuthority());
					else 
						return null;
				} 
				else
					return null;
		}

		public String getColumnName(int num) {
			if (num == 0)
				return "姓名";
			else if(num==1)
				return "编号";
			else if(num==2)
				return "职位";
			else if(num==3)
				return "所属机构";
			else if(num==4)
				return "薪水策略";
			else 
				return "权限";
		}

	}
	
	
	//搜索用户信息对应的表的Model
	public class FindMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = -4L;
		
		Vector<UserVO> userVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 6;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			String userID = searchTextField.getText();
			userVector = new Vector<UserVO>();
			UserVO userVO = userBL.findUser(userID);
			if(!userVO.getProfession().equals(ProfessionType.administrator))
				userVector.add(userVO);

			int index = pageNum * 10 + rowIndex;
			
			if (index > userVector.size()-1)
				return null;
			UserVO uservo = (UserVO)userVector.get(index);
	
			if (uservo != null){
				if(columnIndex==0)
					return uservo.getName();
				else if(columnIndex==1)
					return uservo.getID();
				else if(columnIndex==2)
					return professionName(uservo.getProfession());
				else if(columnIndex==3)
					return uservo.getOrganization();
				else if(columnIndex==4)
					return salaryPlanName(uservo.getSalaryPlan());
				else if(columnIndex==5)
					return authorityName(uservo.getAuthority());
				else 
					return null;
			}
			else{
				return null;
			}
		}

		public String getColumnName(int num) {
			if (num == 0)
				return "姓名";
			else if(num==1)
				return "编号";
			else if(num==2)
				return "职位";
			else if(num==3)
				return "所属机构";
			else if(num==4)
				return "薪水策略";
			else 
				return "权限";
		}

	}

	
/*	public class AddDialog extends JDialog{
	
		private static final long serialVersionUID = 11L;
	
		JFrame sourceFrame;
		JPanel panel;
		
		private String[] user_profession_type = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","管理员","财务人员","总经理"};
	    private String[] user_salaryPlan_type = {"基础月薪+提成","计次提成","基础月薪"};
	    private String[] user_authority_type = {"最低权限（普通人员权限）", "管理员权限","普通财务人员权限","最高权限（最高财务人员和总经理）"};
	    
	    private JLabel function;
	    private JLabel user_profession;
	    private JLabel user_salaryPlan;
	    private JLabel user_authority;
	    private JLabel user_name;
	    private JLabel user_ID;
	    private JLabel user_password;
	    
	    private JComboBox<String> user_profession_choose;
	    private JComboBox<String> user_salaryPlan_choose;
	    private JComboBox<String> user_authority_choose;
	    
	    private JTextField user_name_Input;
	    private JTextField user_ID_Input;
	    private JTextField user_password_Input;
	    
	    private JButton infoOKButton;
	    
	    int proInt = 0;
		int professionInt = 0;
		int salaryPlanInt = 0;
		int authorityInt = 0;
	    String userName = "";
		String userID = "";
		boolean validName = true;
		String password = "";
		String organization = "";
		
		public AddDialog(JFrame frame){
			
			super(frame);
			
			this.sourceFrame = frame;
			panel = new JPanel();
			panel.setSize(720, 480);
			this.sourceFrame.add(panel);
	
			user_profession = new JLabel("用户职位");
	    	user_salaryPlan = new JLabel("薪水策略");
	    	user_authority = new JLabel("权限");
	    	function = new JLabel("新增用户");
	    	user_name = new JLabel("姓名");
	    	user_ID = new JLabel("用户编号");
	    	user_password = new JLabel("初始密码");
	    	infoOKButton = new JButton("ok");
	    	
	    	user_profession_choose = new JComboBox<String>(user_profession_type);
	    	user_salaryPlan_choose = new JComboBox<String>(user_salaryPlan_type);
	    	user_authority_choose = new JComboBox<String>(user_authority_type);
	
	    	user_name_Input = new JTextField("");
	    	user_ID_Input = new JTextField("");
	    	user_password_Input = new JTextField("123456");
	    	
	    	user_profession_choose.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent event){
	    			professionInt = user_profession_choose.getSelectedIndex();
	    			
	    			salaryPlanInt = 2;
	    			authorityInt = 0;//大部分人的工资策略为基础月薪，权限为最低权限
	    			
	    			if(professionInt==0){	//快递员
	    				salaryPlanInt = 0;
	    			}
	    			else if(professionInt==1){	//司机
	    				salaryPlanInt = 1;
	    			}
			    	else if(professionInt==5){	//管理员
	    				authorityInt = 1;
	    			}
	    			else if(professionInt==6){	//财务人员
	    				authorityInt = 2;
	    			}
	    			else if(professionInt==7){	//总经理
	    				authorityInt = 3;
	    			}
	    			
	    			user_salaryPlan_choose.setSelectedIndex(salaryPlanInt);
	    			user_authority_choose.setSelectedIndex(authorityInt);
	    			
	    			String[] IDPreList = {"KD","SJ","CK","YYT","ZZZX","GLY","CW","JL"};
	    			String IDPre = IDPreList[professionInt];
	    			ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
							ProfessionType.stockman,ProfessionType.businessHallCounterman, ProfessionType.intermediateCenterCounterman, 
							ProfessionType.administrator, ProfessionType.financialStaff, ProfessionType.manager};
	    			
	    			ProfessionType profession = professionList[professionInt];
	    			String IDPost = userBL.getUserIDPost(profession);
	    			
	    			user_ID_Input.setText(IDPre+"-"+IDPost);
	    		}
	    	});
	    	
	    	infoOKButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					userName = user_name_Input.getText();
					if(userName.equals("")){
						warnning("新用户姓名不能为空");
						validName = false;
					}
					
					userID = user_ID_Input.getText();
					password = user_password_Input.getText();
					organization = "";
					
					professionInt = user_profession_choose.getSelectedIndex();
					ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
											ProfessionType.stockman,ProfessionType.businessHallCounterman, ProfessionType.intermediateCenterCounterman, 
											 ProfessionType.administrator, ProfessionType.financialStaff, ProfessionType.manager};
					ProfessionType profession = professionList[professionInt];
					
					salaryPlanInt = user_salaryPlan_choose.getSelectedIndex();
					SalaryPlanType[] salaryPlanList = {SalaryPlanType.courierSalaryPlan,SalaryPlanType.driverSalaryPlan,SalaryPlanType.basicStaffSalaryPlan};
					SalaryPlanType salaryPlan = salaryPlanList[salaryPlanInt];
					
					authorityInt = user_authority_choose.getSelectedIndex();
					AuthorityType[] authorityList = {AuthorityType.lowest, AuthorityType.administrator, 
							AuthorityType.commonFianacialStaff, AuthorityType.highest};
					AuthorityType authority = authorityList[authorityInt];
				
					if(validName){
						UserVO uservo = new UserVO(userName, userID, password, profession, organization, salaryPlan,authority, 0);
						int returnNum = userBL.addUser(uservo);
						if(returnNum==0){
							successAdd();
							allInfoTable.updateUI();
						}
						else
							failedAdd();
					}
				}
			});
			
			panel.setLayout(null);
			
	    	panel.add(function);
	    	panel.add(user_profession);
	    	panel.add(user_profession_choose);
	    	panel.add(user_salaryPlan);
	    	panel.add(user_salaryPlan_choose);
	    	panel.add(user_authority);
	    	panel.add(user_authority_choose);
	    	panel.add(user_name);
	    	panel.add(user_name_Input);
	    	panel.add(user_ID);
	    	panel.add(user_ID_Input);
	    	panel.add(user_password);
	    	panel.add(user_password_Input);
	    	panel.add(infoOKButton);
			
			panel.setVisible(true);
			
			user_profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			user_salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 9 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			user_authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 14 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			user_name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 19 / 48,
					PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
			user_ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 24 / 48,
					PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
			user_password.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 29 / 48,
					PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
			
			user_profession_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 4 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			user_salaryPlan_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 9 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			user_authority_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 14 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			user_name_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 19 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			user_ID_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 24 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			user_password_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 29 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			
			infoOKButton.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 36 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			
			repaint();
			
		}
		
		public void successAdd(){
			this.setVisible(false);
			this.sourceFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, "添加成功(●'◡'●)", "新增用户成功", JOptionPane.INFORMATION_MESSAGE);
		}
		
		public void failedAdd(){
			this.setVisible(false);
			this.sourceFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, "添加失败(T_T)", "新增用户失败", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}*/

	/*public class ModifyDialog extends JDialog{

		private static final long serialVersionUID = 10L;

		JFrame sourceFrame;
		JPanel panel;
		
		JLabel name;
		JTextField nameField;
		JLabel ID;
		JTextField IDField;
		JLabel profession;
		JTextField professionField;
		JLabel organization;
		JTextField organizationField;
		JLabel salaryPlan;
		JTextField salaryPlanField;
		JLabel authority;
		JComboBox<String> userAuthority;
		
		JButton OKBtn;
		
		public ModifyDialog(JFrame frame, int chosenRow){
			
			super(frame);
			
			this.sourceFrame = frame;
			panel = new JPanel();
			panel.setSize(720, 480);
			//panel.setBounds(250, 150, 600, 400);
			this.sourceFrame.add(panel);

			name = new JLabel("用户姓名");
			String userName = (String)allInfoTable.getModel().getValueAt(chosenRow, 0);
			nameField = new JTextField(userName);
			nameField.setEditable(false);
			
			ID = new JLabel("用户编号");
			String userID = (String)allInfoTable.getModel().getValueAt(chosenRow, 1);
			IDField = new JTextField(userID);
			IDField.setEditable(false);
			
			profession = new JLabel("职位");
			String userProfession = (String)allInfoTable.getModel().getValueAt(chosenRow, 2);
			professionField = new JTextField(userProfession);
			professionField.setEditable(false);
			
			organization = new JLabel("机构");
			String userOrganization = (String)allInfoTable.getModel().getValueAt(chosenRow, 3);
			organizationField = new JTextField(userOrganization);
			organizationField.setEditable(false);
			
			salaryPlan = new JLabel("薪水策略");
			String userSalaryPlan = (String)allInfoTable.getModel().getValueAt(chosenRow, 4);
			salaryPlanField = new JTextField(userSalaryPlan);
			salaryPlanField.setEditable(false);
			
			authority = new JLabel("权限");
			userAuthority = new JComboBox<String>();
			userAuthority.addItem("最低权限（普通人员权限）");
			userAuthority.addItem("管理员权限");
			userAuthority.addItem("普通财务人员权限");
			userAuthority.addItem("最高权限");

			OKBtn = new JButton("确认");
			
			panel.setLayout(null);
			
			panel.add(name);
			panel.add(nameField);
			panel.add(ID);
			panel.add(IDField);
			panel.add(profession);
			panel.add(professionField);
			panel.add(organization);
			panel.add(organizationField);
			panel.add(salaryPlan);
			panel.add(salaryPlanField);
			panel.add(authority);
			panel.add(userAuthority);
			panel.add(OKBtn);
			
			panel.setVisible(true);
			
			name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 9 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 14 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			organization.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 19 / 48,
					PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
			salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 24 / 48,
					PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
			authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 29 / 48,
					PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
			
			nameField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 4 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			IDField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 9 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			professionField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 14 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			organizationField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 19 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			salaryPlanField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 24 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			userAuthority.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 29 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			
			OKBtn.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 36 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			
			repaint();
			
			OKBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					int authorityInt = userAuthority.getSelectedIndex();
					AuthorityType[] authorityList = {AuthorityType.lowest, AuthorityType.administrator, 
							AuthorityType.commonFianacialStaff, AuthorityType.highest};
					AuthorityType authority = authorityList[authorityInt];
				
					int returnNum = userBL.modifyUserAuthority(userID, authority);
					
					if(returnNum==0){
						successModify();
						allInfoTable.updateUI();
					}
					else{
						failedModify();
					}
				}
				
			});
			
		}
		
		public void successModify(){
			this.setVisible(false);
			this.sourceFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "用户权限修改成功", JOptionPane.INFORMATION_MESSAGE);
		}
		
		public void failedModify(){
			this.setVisible(false);
			this.sourceFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, "修改失败(T_T)", "用户权限修改失败", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}*/
	
}
