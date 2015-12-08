package presentation.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import type.AuthorityType;
import vo.UserVO;
import businesslogic.userbl.UserBL;

public class ModifyUserPanel extends JPanel{

	private static final long serialVersionUID = 15L;

	AdminFrame fatherFrame;
	
	UserBL userBL;
	
    private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private JLabel function;
    private JLabel name;
    private JTextField nameField;
    private JLabel ID;
    private JTextField IDField;
    private JLabel profession;
    private JTextField professionField;
    private JLabel organization;
    private JTextField organizationField;
    private JLabel salaryPlan;
    private JTextField salaryPlanField;
    private JLabel authority;
    private JComboBox<String> userAuthority;
	
    private JButton OKButton;
    private JButton returnButton;
	
    public ModifyUserPanel(AdminFrame frame, String userName, String userID, String userProfession, String userOrganization, String userSalaryPlan){
    	this.fatherFrame = frame;
    	
    	this.userBL = new UserBL();
    	
    	function = new JLabel("用户管理——修改用户权限");
    	
    	name = new JLabel("用户姓名");
		nameField = new JTextField(userName);
		nameField.setEditable(false);
		
		ID = new JLabel("用户编号");
		IDField = new JTextField(userID);
		IDField.setEditable(false);
		
		profession = new JLabel("职位");
		professionField = new JTextField(userProfession);
		professionField.setEditable(false);
		
		organization = new JLabel("机构");
		organizationField = new JTextField(userOrganization);
		organizationField.setEditable(false);
		
		salaryPlan = new JLabel("薪水策略");
		salaryPlanField = new JTextField(userSalaryPlan);
		salaryPlanField.setEditable(false);
		
		authority = new JLabel("权限");
		userAuthority = new JComboBox<String>();
		userAuthority.addItem("最低权限（普通人员权限）");
		userAuthority.addItem("管理员权限");
		userAuthority.addItem("普通财务人员权限");
		userAuthority.addItem("最高权限");

		OKButton = new JButton("确认");
		returnButton = new JButton("返回");
		
		setCmpLocation();
		
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int authorityInt = userAuthority.getSelectedIndex();
				AuthorityType[] authorityList = {AuthorityType.lowest, AuthorityType.administrator, 
						AuthorityType.commonFianacialStaff, AuthorityType.highest};
				AuthorityType authority = authorityList[authorityInt];
			
				int returnNum = userBL.modifyUserAuthority(userID, authority);
				
				if(returnNum==0){
					successModify();
				}
				else{
					failedModify();
				}
			}
		});
    	
    	returnButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			returnui();
    		}
    	});
    	
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
		add(OKButton);
		add(returnButton);
		
		setVisible(true);
		
    }
    
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
		
		OKButton.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 40 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnButton.setBounds(PANEL_WIDTH * 5 / 72, PANEL_HEIGHT * 40 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
    }
    
    public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	public void returnui() {
		UserVO vo = fatherFrame.vo;
		//fatherFrame.setVisible(false);
		
		AdminFrame newAdminFrame = new AdminFrame(vo);
		newAdminFrame.addFuncLabel(new UserMainPanel(newAdminFrame));
		newAdminFrame.showFrame();
	}

	public void successModify(){
		returnui();
		JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "修改用户权限成功", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void failedModify(){
		returnui();
		JOptionPane.showMessageDialog(null, "修改失败(T_T)", "修改用户权限失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "用户信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
}
