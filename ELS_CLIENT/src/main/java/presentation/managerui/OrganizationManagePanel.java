package presentation.managerui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
//import presentation.commonui.LocationHelper;

import vo.OrganizationVO;
import vo.RepertoryVO;
import type.OrganizationType;
import businesslogic.managebl.OrganizationBL;

public class OrganizationManagePanel extends OperationPanel {
	
	private static final long serialVersionUID = 20L;

	private ManageFrame manageFrame;
	
	private OrganizationBL organizationBL;

	private MyLabel addLabel;
	private MyLabel deleteLabel;
	private MyTextField inputField;
	private MyLabel searchLabel;

	private MyTable messageTable;
	
	private ArrayList<OrganizationVO> organizations;

	private int selectedIndex;
	
	public OrganizationManagePanel(ManageFrame manageFrame) {
		
		this.manageFrame = manageFrame;
		
		organizationBL = new OrganizationBL();
		
        addLabel = new MyLabel("新增机构");
        deleteLabel = new MyLabel("删除机构");
        inputField = new MyTextField();
        searchLabel = new MyLabel("查找机构");
        
		setLayout(null);

        add(addLabel);
        add(deleteLabel);
        add(inputField);
        add(searchLabel);
        
        organizations = organizationBL.showAllOrganizations();
        
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
		
		searchLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchui();
			}
		});
	}
	
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);

		addLabel.setBounds((int) (width * 2.624839948783611 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		deleteLabel.setBounds((int) (width * 6.594110115236876 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		inputField.setBounds((int) (width * 16.677336747759284 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.3392857142857142 / 20));
		searchLabel.setBounds((int) (width * 22.247119078104994 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 3.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 15.535714285714286 / 20));
	}
	
	
	private void setBaseInfos() {
		String[] head = new String[]{"机构类型", "机构名称", "机构编号", "下属仓库编号"};
		int[] widths = {120, 200, 140, 140};
		
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
			organizationBL.deleteOrganization(organizations.get(selectedIndex).organizationID);
		}
		else{
			if(JOptionPane.showConfirmDialog(null, "确认删除这些机构信息？", "",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
				return;
			for(int i: selectedIndexs){
				organizationBL.deleteOrganization(organizations.get(i).organizationID);
			}
		}
		organizations = organizationBL.showAllOrganizations();
		messageTable.setInfos(getInfos());
	}
	
	//查询界面
	public void searchui(){
		String keyword = inputField.getText();
		organizations = organizationBL.findOrganizationByKeyword(keyword);
		messageTable.setInfos(getInfos());
	}
	
	//刷新界面
	public void refreshui(){
		organizations = organizationBL.showAllOrganizations();
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

	
}
