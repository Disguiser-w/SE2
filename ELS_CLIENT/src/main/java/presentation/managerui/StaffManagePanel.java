package presentation.managerui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import businesslogic.financebl.controller.LogDiaryBLController;
import businesslogic.managebl.controller.OrganizationManageController;
import businesslogic.receiptbl.GetDate;
import businesslogic.userbl.controller.UserManageController;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.ModifyLabel;
import presentation.special_ui.MySearchField;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.LogDiaryVO;
import vo.OrganizationVO;
import vo.UserVO;


public class StaffManagePanel extends OperationPanel {
	
	private static final long serialVersionUID = 8L;
	
	private ManageFrame manageFrame;
	
	private UserManageController userManageControl;
	private OrganizationManageController organizationManageControl;
	private LogDiaryBLController logDiaryControl;
	
	private LogDiaryVO logDiary;
	private String logDiaryTime;
	
	private DeleteLabel deleteLabel;
	private ModifyLabel modifyLabel;
	private MySearchField searchField;

	private MyTable messageTable;
	
	private ArrayList<UserVO> users;
	
	private int selectedIndex;
	
	public StaffManagePanel(ManageFrame manageFrame, UserManageController userController, OrganizationManageController organizationController) {
		
		this.manageFrame = manageFrame;
		
		this.userManageControl = userController;
		this.organizationManageControl = organizationController;
		this.logDiaryControl = new LogDiaryBLController();
		
		deleteLabel = new DeleteLabel("删除用户");
		modifyLabel = new ModifyLabel("分配机构");
		searchField = new MySearchField();

		setLayout(null);
		
		add(deleteLabel);
		add(modifyLabel);
		add(searchField);
		
		users = userManageControl.showAllUsers();

		addListener();
		setBaseInfos();
		// helper = new LocationHelper(this);
	}
	

	public void addListener() {

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
		
		searchField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchui();
			}
		});
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		deleteLabel.setBounds((int) (width * 6.594110115236876 / 25), (int) (height * 1.0607142857142858 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.8303571428571428 / 22));
		modifyLabel.setBounds((int) (width * 10.56338028169014 / 25), (int) (height * 1.0607142857142858 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.8303571428571428 / 22));
		searchField.setBounds((int) (width * 17.677336747759284 / 25), (int) (height * 1.2107142857142858 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.5303571428571428 / 22));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 3.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 15.035714285714286 / 20));
	}
	

	private void setBaseInfos() {
		String[] head = new String[]{"姓名","用户编号","职业类型","所属机构","薪水策略","权限类型", "绩点"};
		int[] widths = {60, 80, 100, 100, 90, 120, 43};
		
		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos(){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for(UserVO uservo: users){
			infos.add(new String[]{uservo.userName, uservo.userID, professionName(uservo.profession), 
					organizationName(uservo.organization),salaryPlanName(uservo.salaryPlan), authorityName(uservo.authority), uservo.grades+""});
		}
		return infos;
	}
	

	//删除用户界面
	public void deleteui(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，选中某一个或某一些人员后再删除哦！", "没有选择用户", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(size == 1){
			if(JOptionPane.showConfirmDialog(null, "确认删除该用户信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			selectedIndex = selectedIndexs.get(0);
			if(users.get(selectedIndex).profession.equals(ProfessionType.administrator)){
				JOptionPane.showMessageDialog(null, "亲爱的总经理，不可以删除管理员哦！", "删除出错", JOptionPane.WARNING_MESSAGE);
				return;
			}
			userManageControl.deleteUser(users.get(selectedIndex).userID);
			logDiary = new LogDiaryVO(GetDate.getTime(), new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "删除一个用户");
			logDiaryTime = getTimeNow();
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
		else{
			if(JOptionPane.showConfirmDialog(null, "确认删除这些用户信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			for(int i: selectedIndexs){
				userManageControl.deleteUser(users.get(i).userID);
				logDiary = new LogDiaryVO(GetDate.getTime(), new UserVO("刘钦", "JL-00001", "", 
						ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "删除一个用户");
				logDiaryTime = getTimeNow();
				logDiaryControl.addLogDiary(logDiary, logDiaryTime);
			}
		}
		users = userManageControl.showAllUsers();
		messageTable.setInfos(getInfos());
	}
	
	//修改用户机构界面
	public void modifyui(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，选中某一个人员后再修改哦！", "没有选择用户", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(size > 1){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，只能选中某一个人员修改哦！", "选择用户过多", JOptionPane.WARNING_MESSAGE);
			return;
		}
		selectedIndex = selectedIndexs.get(0);
		
		UserVO vo = users.get(selectedIndex);
		if(vo.organization.equals("总部")){
			warnning("该人员属于总部，没有修改机构的必要");
		}
		else{
			manageFrame.changePanel(new ModifyStaffOrganizationPanel(manageFrame, this, vo.userName, vo.userID, 
					professionName(vo.profession), vo.organization, salaryPlanName(vo.salaryPlan), authorityName(vo.authority), vo.grades+""));
			
			logDiaryTime = getTimeNow();
			logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "修改一个用户信息");
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
	}
	
	//查询界面
	public void searchui(){
		String keyword = searchField.getText();
		users = userManageControl.findUserByKeyword(keyword);
		messageTable.setInfos(getInfos());
	}
	
	//刷新界面
	public void refreshui(){
		users = userManageControl.showAllUsers();
		messageTable.setInfos(getInfos());
	}
		
		
	//根据不同的职业类型返回职业名，给表去显示
	public String professionName(ProfessionType profession){
		int n = profession.ordinal();
		String[] professionNameList = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","管理员", "财务人员","总经理"};
		return professionNameList[n];
	}
	
	//根据不同的机构编号返回机构名，给表去显示
	public String organizationName(String organizationID){
		if(organizationID.equals("总部"))
			return "总部";
		else if(organizationID.equals(""))
			return "/（待总经理分配）";
		else if(organizationID.endsWith("-CK")){
			organizationID = organizationID.substring(0,5);
			OrganizationVO organizationvo = organizationManageControl.findOrganization(organizationID);
			return organizationvo.name+"仓库";
		}
		else{
			OrganizationVO organizationvo = organizationManageControl.findOrganization(organizationID);
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
	
	//获取当前时间
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
}
