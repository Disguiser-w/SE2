package presentation.userui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
//import javax.swing.ListSelectionModel;



import java.util.ArrayList;

import businesslogic.managebl.OrganizationBL;
import businesslogic.userbl.UserBL;
import vo.OrganizationVO;
import vo.UserVO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class UserMainPanel extends JPanel{
	
	private static final long serialVersionUID = 2L;

	private AdminFrame adminFrame;
	
	private UserBL userBL;
	private OrganizationBL organizationBL;
	
	private JLabel addLabel;
	private JLabel delLabel;
	private JLabel modifyLabel;
	private JLabel searchLabel;
	private JTextField inputField;
	private JButton searchButton;

	private JTable messageTable;
	
	private JLabel previousPageLabel;
	private JLabel nextPageLabel;
	
	private ArrayList<JCheckBox> selectUser;
	private JLabel numOfPage;
	
	private ArrayList<UserVO> users;
	
	private boolean isDel;
	private boolean isModify;
	private int num;
	private int numOfChoose;
	private boolean isFirstTime;
	
	public UserMainPanel(AdminFrame adminFrame) {
		
		this.adminFrame = adminFrame;
		
		this.userBL = new UserBL();
		this.organizationBL = new OrganizationBL();
		
		addLabel = new JLabel("增");
		delLabel = new JLabel("删");
		modifyLabel = new JLabel("改");
		
		addLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		delLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		modifyLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		
        searchLabel = new JLabel();
		inputField = new JTextField();
		searchButton = new JButton();
		
		MessageTableModel model = new MessageTableModel();
		messageTable = new JTable(model);
		
		previousPageLabel = new JLabel(" < ");
		nextPageLabel = new JLabel(" > ");
		
		selectUser = new ArrayList<JCheckBox>();
		for (int i = 0; i < 8; i++) {
			JCheckBox box = new JCheckBox();
			selectUser.add(box);
			add(box);
		}
        
		numOfPage = new JLabel();

		setLayout(null);
		
		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(searchLabel);
		add(inputField);
		add(searchButton);
		add(messageTable);
		add(previousPageLabel);
		add(nextPageLabel);
		add(messageTable.getTableHeader());
		add(numOfPage);

		messageTable.setBackground(getBackground());

		num = 0;
		numOfChoose = 0;
		isModify = false;
		isFirstTime = true;
		
		users = userBL.showAllUsers();

		addListener();
		
		// helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		numOfPage.setBounds((int) (width * 12.32394366197183 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.4732142857142858 / 20));
		selectUser.get(0).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 5.625 / 20),
				(int) (width * 0.6722151088348272 / 25), (int) (height * 0.8035714285714286 / 20));
		selectUser.get(1).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 6.830357142857143 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectUser.get(2).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 8.125 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectUser.get(3).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 9.419642857142858 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectUser.get(4).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 10.758928571428571 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectUser.get(5).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 12.098214285714286 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectUser.get(6).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 13.348214285714286 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectUser.get(7).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 14.642857142857142 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		addLabel.setBounds((int) (width * 2.624839948783611 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		delLabel.setBounds((int) (width * 6.594110115236876 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		modifyLabel.setBounds((int) (width * 10.56338028169014 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		searchLabel.setBounds((int) (width * 15.781049935979514 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 0.9282970550576184 / 25), (int) (height * 1.2946428571428572 / 20));
		inputField.setBounds((int) (width * 16.677336747759284 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.3392857142857142 / 20));
		searchButton.setBounds((int) (width * 22.247119078104994 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
		messageTable.setBounds((int) (width * 1.0243277848911652 / 25), (int) (height * 5.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 10.535714285714286 / 20));
		messageTable.getTableHeader().setBounds((int) (width * 1.0243277848911652 / 25),
				(int) (height * 5.401785714285714 / 20) - (int) (height * 1.435714285714286 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 1.435714285714286 / 20));
		previousPageLabel.setBounds((int) (width * 11.331626120358514 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
	}

	public void paintComponent(Graphics g) {
		if (isFirstTime) {
			setInfos();
			isFirstTime = false;
		}
		super.paintComponent(g);
	}

	// 设置载入动态的内容
	public void setInfos() {
		for (JCheckBox i : selectUser) {
			i.setVisible(false);
		}
		users = userBL.showAllUsers();
		numOfPage.setText(num + 1 + "/" + ((users.size() - 1) / 8 + 1));

		messageTable.setModel(new MessageTableModel());
		setBaseInfos();
		// repaint();
	}
	
	public void addListener() {
		
		previousPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num == 0)
					return;
				else {
					num--;
					setInfos();
				}
			}
		});

		nextPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num >= (users.size() - 1) / 8)
					return;
				else {
					num++;
					setInfos();
				}
			}
		});

		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addui();
			}
		});
		
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JCheckBox button = ((JCheckBox) (e.getSource()));
				if(button.isSelected()){
					numOfChoose++;
				} 
				else{
					numOfChoose--;
				}

				if(numOfChoose == 1){
					isModify = true;
				} 
				else
					isModify = false;

				if (numOfChoose >= 1)
					isDel = true;
			}
		};

		for (JCheckBox i : selectUser) {
			i.addItemListener(listener);
		}

		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isModify) {
					modifyui();
				}
			}
		});

		delLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isDel) {
					if (JOptionPane.showConfirmDialog(null, "确认删除该用户信息？", "",
							JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
						return;
					}
					int n = 0;
					int m = 0;
					for (JCheckBox i : selectUser) {
						if (i.isSelected()) {
							i.setSelected(false);
							UserVO vo = users.get(num * 8 + n);
							if(!vo.profession.equals(ProfessionType.administrator)){
								userBL.deleteUser(vo.userID);
								m++;
							}
						}
						n++;
						isDel = false;
					}

					if ((users.size() - m - 1) / 8 + 1 < num + 1) {
						num--;
					}
					setInfos();
				}
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				String keyword = inputField.getText();
				for (JCheckBox i : selectUser) {
					i.setVisible(false);
				}
				users = userBL.findUserByKeyword(keyword);
				numOfPage.setText(num + 1 + "/" + ((users.size() - 1) / 8 + 1));

				messageTable.setModel(new MessageTableModel());
				setBaseInfos();
			}
		});
		
	}

	// 设置table的基本内容，图片，什么的
	private void setBaseInfos() {

		// 设置成不可编辑不可改变位置，大小
		// messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column0 = messageTable.getColumnModel().getColumn(0);
		TableColumn column1 = messageTable.getColumnModel().getColumn(1);
		TableColumn column2 = messageTable.getColumnModel().getColumn(2);
		TableColumn column3 = messageTable.getColumnModel().getColumn(3);
		TableColumn column4 = messageTable.getColumnModel().getColumn(4);
		TableColumn column5 = messageTable.getColumnModel().getColumn(5);

		// 设置宽度
		int tWidth = messageTable.getWidth();
		column0.setPreferredWidth(tWidth * 5 / 32);
		column1.setPreferredWidth(tWidth / 8);
		column2.setPreferredWidth(tWidth * 9 / 32);
		column3.setPreferredWidth(tWidth / 4);
		column4.setPreferredWidth(tWidth * 3 / 32);
		column5.setPreferredWidth(tWidth * 3 / 32);

		messageTable.setRowHeight(messageTable.getHeight() / 8);

		//
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
		column0.setCellRenderer(tcr);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);
		column5.setCellRenderer(tcr);
		
	}

	private class MessageTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 297L;

		public int getRowCount() {
			return 8;
		}

		public int getColumnCount() {
			return 6;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			int index = num * 8 + rowIndex;

			if (index > users.size() - 1)
				return null;

			UserVO uservo = users.get(index);

			switch (columnIndex) {
			case 0:
				selectUser.get(rowIndex).setVisible(true);
				return uservo.userName;
			case 1:
				return uservo.userID;
			case 2:
				return professionName(uservo.profession);
			case 3:
				if(uservo.organization.equals("总部"))
					return "总部";
				else if(uservo.organization.equals(""))
					return "";
				else
					return organizationName(uservo.organization);
				//return uservo.organization;
			case 4:
				return salaryPlanName(uservo.salaryPlan);
			case 5:
				return authorityName(uservo.authority);
			default:
				return null;
			}
		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "姓名";
			case 1:
				return "用户编号";
			case 2:
				return "职业类型";
			case 3:
				return "机构";
			case 4:
				return "薪水策略";
			case 5:
				return "权限类型";
			default:
				return null;
			}
		}

	}
	
	
	//新增用户界面
	public void addui() {
		adminFrame.changePanel(new AddUserPanel(adminFrame, this));
	}

	//修改用户权限界面
	public void modifyui(){
		int n = 0;
		for(JCheckBox i : selectUser){
			if(i.isSelected()){
				i.setSelected(false);
				break;
			}
			n++;
		}
		
		UserVO vo = users.get(num * 8 + n);
		if(!vo.profession.equals(ProfessionType.financialStaff)){
			warnning("该人员不是财务人员，没有修改权限的必要");
		}
		else{
			adminFrame.changePanel(new ModifyUserAuthorityPanel(adminFrame, this, vo.userName, vo.userID, professionName(vo.profession), vo.organization, salaryPlanName(vo.salaryPlan)));
		}
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
	
	//根据不同的机构编号返回机构名，给表去显示
	public String organizationName(String organizationID){
		if(organizationID.endsWith("-CK")){
			organizationID = organizationID.substring(0,5);
			OrganizationVO organizationvo = organizationBL.findOrganization(organizationID);
			return organizationvo.name+"仓库";
		}
		else{
			OrganizationVO organizationvo = organizationBL.findOrganization(organizationID);
			return organizationvo.name;
		}
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
	/*public class AllMessageTableModel extends AbstractTableModel {
		
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

	}*/
	
}
