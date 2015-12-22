package presentation.userui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import type.AuthorityType;
import businesslogic.userbl.UserBL;

public class ModifyUserAuthorityPanel extends OperationPanel {

	private static final long serialVersionUID = 15L;

	private AdminFrame adminFrame;
	private UserMainPanel userMainPanel;
	
	private UserBL userBL;
	
	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private MyTextLabel function;
    private MyTextLabel name;
    private MyTextLabel ID;
    private MyTextLabel profession;
    private MyTextLabel organization;
    private MyTextLabel salaryPlan;
    private MyTextLabel authority;
    
    private MyTextField nameField;
    private MyTextField IDField;
    private MyTextField professionField;
    private MyTextField organizationField;
    private MyTextField salaryPlanField;
    
    private MyComboBox<String> userAuthority;
    
    private MyLabel OKLabel;
    private MyLabel returnLabel;
	
    private String userIDStr;
    
    public ModifyUserAuthorityPanel(AdminFrame frame, UserMainPanel userMainPanel, String userName, String userID, String userProfession, String userOrganization, String userSalaryPlan){
    	
    	this.adminFrame = frame;
    	this.userMainPanel = userMainPanel;
    	
    	this.userBL = new UserBL();
    	
    	function = new MyTextLabel("用户管理——修改用户权限");
    	
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
		organizationField = new MyTextField();
		organizationField.setText(userOrganization);
		organizationField.setEditable(false);
		
		salaryPlan = new MyTextLabel("薪水策略");
		salaryPlanField = new MyTextField();
		salaryPlanField.setText(userSalaryPlan);
		salaryPlanField.setEditable(false);
		
		authority = new MyTextLabel("权限");
		userAuthority = new MyComboBox<String>();
		userAuthority.addItem("普通财务人员权限");
		userAuthority.addItem("最高权限");

		OKLabel = new MyLabel("确认");
		returnLabel = new MyLabel("返回");
		
		
		//加监听
		OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int authorityInt = userAuthority.getSelectedIndex();
				AuthorityType[] authorityList = {AuthorityType.commonFianacialStaff, AuthorityType.highest};
				AuthorityType authority = authorityList[authorityInt];
			
				int returnNum = userBL.modifyUserAuthority(userIDStr, authority);
				
				if(returnNum==0){
					successModify();
				}
				else{
					failedModify();
				}
			}
		});
    	
    	returnLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
    			returnui();
    		}
    	});
    	
		
    	//把组件加到Panel上
		setLayout(null);
		
		add(name);
		add(nameField);
		add(ID);
		add(IDField);
		add(profession);
		add(professionField);
		add(organization);
		add(organizationField);
		add(salaryPlan);
		add(salaryPlanField);
		add(authority);
		add(userAuthority);
		add(OKLabel);
		add(returnLabel);
		
		setVisible(true);
		
		//位置设置
		setCmpLocation();
    }
    
    
    //设置位置
    public void setCmpLocation(){
    	function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
    	
    	name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 8 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 13/ 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	organization.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 33 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		
		nameField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 8 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		IDField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 13/ 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		professionField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		organizationField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		salaryPlanField.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		userAuthority.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 33 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		
		OKLabel.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 40 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnLabel.setBounds(PANEL_WIDTH * 5 / 72, PANEL_HEIGHT * 40 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
    }
    
    public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

    
    //返回上一级界面
	public void returnui() {
		adminFrame.toMainPanel();
		userMainPanel.refreshui();
	}

	
	
	//修改成功时返回上一级界面，同时给用户提示信息
	public void successModify(){
		returnui();
		JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "修改用户权限成功", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//修改失败时返回上一级界面，同时给用户提示信息
	public void failedModify(){
		returnui();
		JOptionPane.showMessageDialog(null, "修改失败(T_T)", "修改用户权限失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "用户信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
	
}
