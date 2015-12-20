package presentation.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.ArrayList;

import presentation.commonui.OperationPanel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyLabel;

import businesslogic.managebl.OrganizationBL;
import businesslogic.userbl.UserBL;
import vo.OrganizationVO;
import vo.UserVO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class UserMainPanel extends OperationPanel {
	
	private static final long serialVersionUID = 2L;

	private AdminFrame adminFrame;
	
	private UserBL userBL;
	private OrganizationBL organizationBL;
	
	private MyLabel addLabel;
	private MyLabel deleteLabel;
	private MyLabel modifyLabel;
	private MyLabel searchLabel;
	private MyTextField inputField;
	private JButton searchButton;

	private MyTable messageTable;
	
	private ArrayList<UserVO> users;
	
	private int selectedIndex;
	
	public UserMainPanel(AdminFrame adminFrame) {
		
		this.adminFrame = adminFrame;
		
		this.userBL = new UserBL();
		this.organizationBL = new OrganizationBL();
		
		addLabel = new MyLabel("增");
		deleteLabel = new MyLabel("删");
		modifyLabel = new MyLabel("改");
		searchLabel = new MyLabel();
		inputField = new MyTextField();
		searchButton = new JButton();

		setLayout(null);
		
		add(addLabel);
		add(deleteLabel);
		add(modifyLabel);
		add(searchLabel);
		add(inputField);
		add(searchButton);
		
		users = userBL.showAllUsers();

		addListener();
		setBaseInfos();
		// helper = new LocationHelper(this);
	}
	

	public void addListener() {
		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addui();
			}
		});

		deleteLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				deleteui();
			}
		});
		
		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				modifyui();
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				searchui();
			}
		});
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addLabel.setBounds((int) (width * 2.624839948783611 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		deleteLabel.setBounds((int) (width * 6.594110115236876 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		modifyLabel.setBounds((int) (width * 10.56338028169014 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		searchLabel.setBounds((int) (width * 15.781049935979514 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 0.9282970550576184 / 25), (int) (height * 1.2946428571428572 / 20));
		inputField.setBounds((int) (width * 16.677336747759284 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.3392857142857142 / 20));
		searchButton.setBounds((int) (width * 22.247119078104994 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 5.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 10.535714285714286 / 20));
	}
	

	private void setBaseInfos() {
		String[] head = new String[]{"姓名","用户编号","职业类型","所属机构","薪水策略","权限类型"};
		int[] widths = {60,90,120,120,90,120};
		
		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos(){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for(UserVO uservo: users){
			infos.add(new String[]{uservo.userName, uservo.userID, professionName(uservo.profession), 
					organizationName(uservo.organization),salaryPlanName(uservo.salaryPlan), authorityName(uservo.authority)});
		}
		return infos;
	}
	
	
	//新增用户界面
	public void addui() {
		adminFrame.changePanel(new AddUserPanel(adminFrame, this));
	}

	//删除用户界面
	public void deleteui(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的管理员，选中某一个或某一些人员后再删除哦！", "没有选择用户", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(size == 1){
			if(JOptionPane.showConfirmDialog(null, "确认删除该用户信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			selectedIndex = selectedIndexs.get(0);
			userBL.deleteUser(users.get(selectedIndex).userID);
		}
		else{
			if(JOptionPane.showConfirmDialog(null, "确认删除这些用户信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			for(int i: selectedIndexs){
				userBL.deleteUser(users.get(i).userID);
			}
		}
		users = userBL.showAllUsers();
		messageTable.setInfos(getInfos());
	}
	
	//修改用户权限界面
	public void modifyui(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的管理员，选中某一个人员后再修改哦！", "没有选择用户", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(size > 1){
			JOptionPane.showMessageDialog(null, "亲爱的管理员，只能选中某一个人员修改哦！", "选择用户过多", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		selectedIndex = selectedIndexs.get(0);
		
		UserVO vo = users.get(selectedIndex);
		if(!vo.profession.equals(ProfessionType.financialStaff)){
			warnning("该人员不是财务人员，没有修改权限的必要");
		}
		else{
			adminFrame.changePanel(new ModifyUserAuthorityPanel(adminFrame, this, vo.userName, vo.userID,
					professionName(vo.profession), organizationName(vo.organization), salaryPlanName(vo.salaryPlan)));
		}
	}
	
	//查询界面
	public void searchui(){
		String keyword = inputField.getText();
		users = userBL.findUserByKeyword(keyword);
		messageTable.setInfos(getInfos());
	}
	
	//刷新界面
	public void refreshui(){
		users = userBL.showAllUsers();
		messageTable.setInfos(getInfos());
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
		if(organizationID.equals("总部"))
			return "总部";
		else if(organizationID.equals(""))
			return "/（待总经理分配）";
		else if(organizationID.endsWith("-CK")){
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

	
}	