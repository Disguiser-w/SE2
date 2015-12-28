package presentation.managerui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.AddLabel;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.MySearchField;
import vo.LogDiaryVO;
import vo.OrganizationVO;
import vo.RepertoryVO;
import vo.UserVO;
import type.AuthorityType;
import type.OrganizationType;
import type.ProfessionType;
import type.SalaryPlanType;
import businesslogic.logdiarybl.controller.LogDiaryBLController;
import businesslogic.managebl.controller.OrganizationManageController;

public class OrganizationManagePanel extends OperationPanel {
	
	private static final long serialVersionUID = 20L;

	private ManageFrame manageFrame;
	
	private OrganizationManageController organizationManageControl;
	private LogDiaryBLController logDiaryControl;

	private LogDiaryVO logDiary;
	private String logDiaryTime;
	
	private AddLabel addLabel;
	private DeleteLabel deleteLabel;
	private MySearchField searchField;

	private MyTable messageTable;
	
	private ArrayList<OrganizationVO> organizations;

	private int selectedIndex;
	
	public OrganizationManagePanel(ManageFrame manageFrame, OrganizationManageController organizationControl) {
		
		this.manageFrame = manageFrame;
		
		organizationManageControl = organizationControl;
		logDiaryControl = new LogDiaryBLController();
		
        addLabel = new AddLabel("新增机构");
        deleteLabel = new DeleteLabel("删除机构");
        searchField = new MySearchField();
        
		setLayout(null);

        add(addLabel);
        add(deleteLabel);
        add(searchField);
        
        organizations = organizationManageControl.showAllOrganizations();
        
        addListener();
        setBaseInfos();
	}
	
	public void addListener(){
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
		
		searchField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchui();
			}
		});
	}
	
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);

		addLabel.setBounds((int) (width * 2.624839948783611 / 25), (int) (height * 1.0607142857142858 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.8303571428571428 / 22));
		deleteLabel.setBounds((int) (width * 6.594110115236876 / 25), (int) (height * 1.0607142857142858 / 20),
				(int) (width * 1.8303571428571428 / 25), (int) (height * 1.8303571428571428 / 22));
		searchField.setBounds((int) (width * 17.677336747759284 / 25), (int) (height * 1.2107142857142858 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.5303571428571428 / 22));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 3.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 15.035714285714286 / 20));
	}
	
	
	private void setBaseInfos() {
		String[] head = new String[]{"机构类型", "机构名称", "机构编号", "下属仓库编号"};
		int[] widths = {120, 200, 140, 133};
		
		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos(){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for(OrganizationVO organizationvo: organizations){
			infos.add(new String[]{categoryName(organizationvo.category), organizationvo.name, organizationvo.organizationID, 
					repertoryID(organizationvo.repertory)});
		}
		return infos;
	}
	
	
	//新增机构界面
	public void addui(){
		manageFrame.changePanel(new AddOrganizationPanel(manageFrame, this));
		
		logDiaryTime = getTimeNow();
		logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
				ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "新增一个机构");
		logDiaryControl.addLogDiary(logDiary, logDiaryTime);
	}
	
	//删除机构界面
	public void deleteui(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，选中某一个或某一些机构后再删除哦！", "没有选择用户", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else if(size == 1){
			if(JOptionPane.showConfirmDialog(null, "确认删除该机构信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			selectedIndex = selectedIndexs.get(0);
			organizationManageControl.deleteOrganization(organizations.get(selectedIndex).organizationID);
			
			logDiaryTime = getTimeNow();
			logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
					ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "删除一个机构");
			logDiaryControl.addLogDiary(logDiary, logDiaryTime);
		}
		else{
			if(JOptionPane.showConfirmDialog(null, "确认删除这些机构信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			for(int i: selectedIndexs){
				organizationManageControl.deleteOrganization(organizations.get(i).organizationID);
				
				logDiaryTime = getTimeNow();
				logDiary = new LogDiaryVO(logDiaryTime, new UserVO("刘钦", "JL-00001", "", 
						ProfessionType.manager, "", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0), "删除一个机构");
				logDiaryControl.addLogDiary(logDiary, logDiaryTime);
			}
		}
		organizations = organizationManageControl.showAllOrganizations();
		messageTable.setInfos(getInfos());
	}
	
	//查询界面
	public void searchui(){
		String keyword = searchField.getText();
		organizations = organizationManageControl.findOrganizationByKeyword(keyword);
		messageTable.setInfos(getInfos());
	}
	
	//刷新界面
	public void refreshui(){
		organizations = organizationManageControl.showAllOrganizations();
		messageTable.setInfos(getInfos());
	}
		
		
	//根据不同的机构类型返回机构类型名，给表去显示
	public String categoryName(OrganizationType organizationType){
		if(organizationType.equals(OrganizationType.businessHall))
			return "营业厅";
		else
			return "中转中心";
	}
	
	public String repertoryID(RepertoryVO repertoryvo){
		if(repertoryvo == null)
			return "/";
		else{
			return repertoryvo.repertoryID;
		}
	}
	
	
	//出现错误时给用户的提示信息
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "机构信息错误", JOptionPane.ERROR_MESSAGE);
	}

	//获取当前时间
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
}
