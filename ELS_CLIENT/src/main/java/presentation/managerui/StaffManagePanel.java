package presentation.managerui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

//import presentation.commonui.LocationHelper;




import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;
import businesslogic.userbl.UserBL;

public class StaffManagePanel extends JPanel implements ListSelectionListener{
	
	private static final long serialVersionUID = 8L;
	
	private ManageFrame manageFrame;
	
	private UserBL userBL;
	
	private int pageNum;	//pageNum用来计表格的页数，从0开始计数
	private boolean isFindPattern;	//isFindPattern表示是不是搜索模式
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private JLabel function;

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
	
	//private LocationHelper helper;

	public StaffManagePanel(ManageFrame manageFrame){
		
		this.manageFrame = manageFrame;
		
		this.userBL = new UserBL();
		
		pageNum = 0;
		isFindPattern = false;
		
        function = new JLabel("用户管理 ");
        
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
        
		previousPage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nextPage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		allInfoTable.setBackground(getBackground());
		allInfoTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		allInfoTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		findInfoTable.setBackground(getBackground());
		findInfoTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		findInfoTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
				
				if(chosenRow != -1){
					userBL.deleteUser(ID);
					allInfoTable.updateUI();//刷新
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
        
		
        setLayout(null);

        add(function);
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
        
        setCmpLocation(allInfoTable);
        setBaseInfo(allInfoTable);
        setInfos();
	}
	
	
	public void valueChanged(ListSelectionEvent lse){
     	//System.out.println("你选了第"+allInfoTable.getSelectedRow()+"行");
	}
	
	public void setCmpLocation(JTable table){
		
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		
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
	
	private void setBaseInfo(JTable table) {

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		TableColumn column0 = table.getColumnModel().getColumn(0);
		TableColumn column1 = table.getColumnModel().getColumn(1);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		TableColumn column3 = table.getColumnModel().getColumn(3);
		TableColumn column4 = table.getColumnModel().getColumn(4);
		TableColumn column5 = table.getColumnModel().getColumn(5);
		TableColumn column6 = table.getColumnModel().getColumn(6);
		
		// 设置宽度
		column0.setPreferredWidth(table.getWidth() * 6 / 49);
		column1.setPreferredWidth(table.getWidth() * 6 / 49);
		column2.setPreferredWidth(table.getWidth() * 9 / 49);
		column3.setPreferredWidth(table.getWidth() * 9 / 49);
		column4.setPreferredWidth(table.getWidth() * 8 / 49);
		column5.setPreferredWidth(table.getWidth() * 8 / 49);
		column6.setPreferredWidth(table.getWidth() * 3 / 49);

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
		column6.setCellRenderer(tcr);
	}

	// 设置载入动态的内容
	private void setInfos() {
		if (userBL.showAllUsers().size() != 0)
			messageLabel.setText("姓名: "+"  用户编号 : "+"  职位 : ");
		else
			messageLabel.setText("姓名: 无"+"  用户编号 : 无"+"  职位 : 无");
		
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void modifyui(int chosenRow){
		String userName;
		String userID;
		String userProfession;
		String userOldOrganization;
		String userSalaryPlan;
		String userAuthority;
		String userGrade;
		
		if(isFindPattern){
			userName = (String)findInfoTable.getModel().getValueAt(chosenRow, 0);
			userID = (String)findInfoTable.getModel().getValueAt(chosenRow, 1);
			userProfession = (String)findInfoTable.getModel().getValueAt(chosenRow, 2);
			userOldOrganization = (String)findInfoTable.getModel().getValueAt(chosenRow, 3);
			userSalaryPlan = (String)findInfoTable.getModel().getValueAt(chosenRow, 4);
			userAuthority = (String)findInfoTable.getModel().getValueAt(chosenRow, 5);
			userGrade = (String)findInfoTable.getModel().getValueAt(chosenRow, 6);
		}
		else{
			userName = (String)allInfoTable.getModel().getValueAt(chosenRow, 0);
			userID = (String)allInfoTable.getModel().getValueAt(chosenRow, 1);
			userProfession = (String)allInfoTable.getModel().getValueAt(chosenRow, 2);
			userOldOrganization = (String)allInfoTable.getModel().getValueAt(chosenRow, 3);
			userSalaryPlan = (String)allInfoTable.getModel().getValueAt(chosenRow, 4);
			userAuthority = (String)allInfoTable.getModel().getValueAt(chosenRow, 5);
			userGrade = allInfoTable.getModel().getValueAt(chosenRow, 6)+"";
		}
		
		manageFrame.changePanel(new ModifyStaffOrganizationPanel(manageFrame, userName, userID, userProfession, userOldOrganization, userSalaryPlan, userAuthority, userGrade));
	}
	
	public void searchui(){
		this.remove(allInfoTable);
		this.add(findInfoTable);
		setCmpLocation(findInfoTable);
        setBaseInfo(findInfoTable);
        setInfos();
        isFindPattern = true;
	}
	
	public void allui(){
		this.remove(findInfoTable);
		this.add(allInfoTable);
		setCmpLocation(allInfoTable);
        setBaseInfo(allInfoTable);
        setInfos();
        isFindPattern = false;
	}
	
	public String professionName(ProfessionType profession){
		int n = profession.ordinal();
		String[] professionNameList = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","管理员","财务人员","总经理"};
		return professionNameList[n];
	}
	
	public String salaryPlanName(SalaryPlanType salaryPlan){
		int n = salaryPlan.ordinal();
		String[] salaryPlanNameList = {"基础月薪+提成","计次提成","基础月薪"};
		return salaryPlanNameList[n];
	}
	
	public String authorityName(AuthorityType authority){
		int n = authority.ordinal();
		String[] authorityNameList = {"最低权限", "管理员权限","普通财务人员权限","最高权限"};
		return authorityNameList[n];
	}
	
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "用户信息错误", JOptionPane.ERROR_MESSAGE);
	}

	public class AllMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 4L;
		
		Vector<UserVO> userVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 7;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			userVector = new Vector<UserVO>();
			for(int i=0;i<userBL.showAllUsers().size();i++){
				userVector.add(userBL.showAllUsers().get(i));
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
					else if(columnIndex==6)
						return uservo.getGrades();
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
			else if(num==5)
				return "权限";
			else 
				return "绩点";
		}

	}
	
public class FindMessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = -4L;
		
		Vector<UserVO> userVector;
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 7;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			String userID = searchTextField.getText();
			userVector = new Vector<UserVO>();
			userVector.add(userBL.findUser(userID));

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
				else if(columnIndex==5)
					return uservo.getGrades();
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
			else if(num==6)
				return "权限";
			else 
				return "绩点";
		}

	}


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
		JTextField authorityField;
		
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
			organizationField.setEditable(true);
			
			salaryPlan = new JLabel("薪水策略");
			String userSalaryPlan = (String)allInfoTable.getModel().getValueAt(chosenRow, 4);
			salaryPlanField = new JTextField(userSalaryPlan);
			salaryPlanField.setEditable(false);
			
			authority = new JLabel("权限");
			String userAuthority = (String)allInfoTable.getModel().getValueAt(chosenRow, 5);
			authorityField = new JTextField(userAuthority);
			authorityField.setEditable(false);

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
			panel.add(authorityField);
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
			authorityField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 29 / 48,
					PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
			
			OKBtn.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 36 / 48,
					PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
			
			repaint();
			
			OKBtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					String newOrganization = organizationField.getText();
					int returnNum = userBL.modifyUserOrganization(userID, newOrganization);
					
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
			JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "用户机构修改成功", JOptionPane.INFORMATION_MESSAGE);
		}
		
		public void failedModify(){
			this.setVisible(false);
			this.sourceFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, "修改失败(T_T)", "用户机构修改失败", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	public class MessageTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 4L;
		
		Vector<UserVO> userVector;
		
		public MessageTableModel(){
			super();
			userVector = new Vector<UserVO>();
			for(int i=0;i<userBL.showAllUsers().size();i++){
				userVector.add(userBL.showAllUsers().get(i));
			}
		}
		
		public void changeForSearch(String userID){
			userVector.clear();
			userVector.add(userBL.findUser(userID));
		}
		
		public int getRowCount() {
			return 10;
		}

		public int getColumnCount() {
			return 7;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			int index = num * 10 + rowIndex;

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
				else if(columnIndex==6)
					return uservo.getGrades();
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
			else if(num==5)
				return "权限";
			else 
				return "绩点";
		}

	}*/
	
}
