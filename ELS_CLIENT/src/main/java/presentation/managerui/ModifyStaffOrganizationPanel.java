package presentation.managerui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.managebl.OrganizationBL;
import businesslogic.userbl.UserBL;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import type.OrganizationType;
import vo.OrganizationVO;

public class ModifyStaffOrganizationPanel extends OperationPanel {

	private static final long serialVersionUID = 16L;

	private ManageFrame manageFrame;
	private StaffManagePanel staffManagePanel;

	private UserBL userBL;
	private OrganizationBL organizationBL;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private JLabel function;

	private MyTextLabel name;
	private MyTextLabel ID;
	private MyTextLabel profession;
	private MyTextLabel organization;
	private MyTextLabel salaryPlan;
	private MyTextLabel authority;
	private MyTextLabel grade;

	private MyTextField nameField;
	private MyTextField IDField;
	private MyTextField professionField;
	private MyTextField salaryPlanField;
	private MyTextField authorityField;
	private MyTextField gradeField;

	private MyComboBox<String> organizationChoose;
	private String[] organizationNameList;
	private String[] organizationIDList;

	private MyLabel OKButton;
	private MyLabel returnButton;

	private String userIDStr;

	public ModifyStaffOrganizationPanel(ManageFrame frame, StaffManagePanel staffManagePanel, String userName,
			String userID, String userProfession, String userOldOrganization, String userSalaryPlan,
			String userAuthority, String userGrade) {

		this.manageFrame = frame;
		this.staffManagePanel = staffManagePanel;

		try {
			this.userBL = new UserBL();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.organizationBL = new OrganizationBL();

		ArrayList<OrganizationVO> organizationArr = organizationBL.showAllOrganizations();
		
		int intermediateCenterCount = 0;
		for (int i = 0; i < organizationArr.size(); i++) {
			if(organizationArr.get(i).category.equals(OrganizationType.intermediateCenter)){
				intermediateCenterCount++;
			}
		}

		if( (userID.startsWith("ZZZX")) || (userID.startsWith("CK"))){
			organizationNameList = new String[intermediateCenterCount];
			organizationIDList = new String[intermediateCenterCount];
			
			int j = 0;
			for (int i = 0; i < organizationArr.size(); i++) {
				if(organizationArr.get(i).category.equals(OrganizationType.intermediateCenter)){
					organizationNameList[j] = (organizationArr.get(i).name);
					organizationIDList[j] = (organizationArr.get(i).organizationID);
					j++;
				}
			}
		}
		else{
			organizationNameList = new String[organizationArr.size()-intermediateCenterCount];
			organizationIDList = new String[organizationArr.size()-intermediateCenterCount];
			
			int j = 0;
			for (int i = 0; i < organizationArr.size(); i++) {
				if(organizationArr.get(i).category.equals(OrganizationType.businessHall)){
					organizationNameList[j] = (organizationArr.get(i).name);
					organizationIDList[j] = (organizationArr.get(i).organizationID);
					j++;
				}
			}
		}

		function = new MyTextLabel("用户管理——修改用户机构");

		name = new MyTextLabel("用户姓名");
		nameField = new MyTextField();
		nameField.setText(userName);
		nameField.setEditable(false);

		ID = new MyTextLabel("用户编号");
		IDField = new MyTextField();
		IDField.setText(userID);
		IDField.setEditable(false);
		userIDStr = userID;

		profession = new MyTextLabel("职位");
		professionField = new MyTextField();
		professionField.setText(userProfession);
		professionField.setEditable(false);

		organization = new MyTextLabel("机构");
		organizationChoose = new MyComboBox<String>();
		for (int i = 0, size = organizationNameList.length; i < size; i++) {
			organizationChoose.addItem(organizationNameList[i]);
		}

		salaryPlan = new MyTextLabel("薪水策略");
		salaryPlanField = new MyTextField();
		salaryPlanField.setText(userSalaryPlan);
		salaryPlanField.setEditable(false);

		authority = new MyTextLabel("权限");
		authorityField = new MyTextField();
		authorityField.setText(userAuthority);
		authorityField.setEditable(false);

		grade = new MyTextLabel("绩点");
		gradeField = new MyTextField();
		gradeField.setText(userGrade);
		gradeField.setEditable(false);

		OKButton = new MyLabel("确认");
		returnButton = new MyLabel("返回");

		// 加监听
		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int chosenInt = organizationChoose.getSelectedIndex();
				String newOrganizationID = organizationIDList[chosenInt];
				
				if(userIDStr.startsWith("CK"))
					newOrganizationID = newOrganizationID + "-CK";
				
				int returnNum = userBL.modifyUserOrganization(userIDStr, newOrganizationID);

				if (returnNum == 0) {
					successModify();
				} else {
					failedModify();
				}
			}
		});

		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				returnui();
			}
		});

		// 把组件加到Panel上
		setLayout(null);

		add(function);
		add(name);
		add(nameField);
		add(ID);
		add(IDField);
		add(profession);
		add(professionField);
		add(organization);
		add(organizationChoose);
		add(salaryPlan);
		add(salaryPlanField);
		add(authority);
		add(authorityField);
		add(grade);
		add(gradeField);
		add(OKButton);
		add(returnButton);

		setVisible(true);

		// 位置设置
		setCmpLocation();
	}

	// 设置位置
	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 38, PANEL_WIDTH * 6 / 18, PANEL_HEIGHT / 12);

		name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 6 / 48, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 11 / 48, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 16 / 48, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		organization.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 21 / 48, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 26 / 48, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 31 / 48, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		grade.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 36 / 48, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);

		nameField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 6 / 48, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		IDField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 11 / 48, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		professionField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 16 / 48, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		organizationChoose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 21 / 48, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		salaryPlanField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 26 / 48, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		authorityField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 31 / 48, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		gradeField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 36 / 48, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);

		OKButton.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 41 / 48, PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnButton.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 41 / 48, PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	// 返回上一级界面
	public void returnui() {
		manageFrame.toMainPanel();
		staffManagePanel.refreshui();
	}

	// 修改成功时返回上一级界面，同时给用户提示信息
	public void successModify() {
		returnui();
		JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "为用户选择机构成功", JOptionPane.INFORMATION_MESSAGE);

	}

	// 修改失败时返回上一级界面，同时给用户提示信息
	public void failedModify() {
		returnui();
		JOptionPane.showMessageDialog(null, "修改失败(T_T)", "为用户选择机构失败", JOptionPane.INFORMATION_MESSAGE);
	}

	// 出现错误时给用户的提示信息
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "用户信息错误", JOptionPane.ERROR_MESSAGE);
	}

}
