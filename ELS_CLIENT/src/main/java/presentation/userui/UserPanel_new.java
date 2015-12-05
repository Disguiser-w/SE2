package presentation.userui;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import businesslogic.userbl.UserBL;
import vo.UserVO;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class UserPanel_new extends JLabel{
	UserBL userBL;
	
    private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    private String[] user_profession_type = {"快递员", "司机", "业务员", "财务人员", "仓库管理员",  "总经理", "管理员"};
    private String[] user_salaryPlan_type = {"快递员薪水策略（基础月薪+提成）","司机薪水策略（计次提成）", "业务员薪水策略（基础月薪）","财务人员薪水策略（基础月薪）","仓库管理员薪水策略（基础月薪）","总经理薪水策略（基础月薪）","管理员薪水策略（基础月薪）"};
    private String[] user_authority_type = {"最低权限（普通人员权限）", "管理员权限", "普通财务人员权限", "最高权限（最高财务人员和总经理）"};
    
    private JLabel function;
    private JLabel user_profession;
    private JLabel user_salaryPlan;
    private JLabel user_authority;
    private JLabel user_name;
    private JLabel user_ID;
    private JLabel user_password;
    
    private JComboBox user_profession_choose;
    private JComboBox user_salaryPlan_choose;
    private JComboBox user_authority_choose;
    
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
	boolean validPre = false;
	boolean validPost = true;
	String password = "";
	String organization = "";
	
    public UserPanel_new(){
    	this.userBL = new UserBL();
    	
    	user_profession = new JLabel("用户职位");
    	user_salaryPlan = new JLabel("薪水策略");
    	user_authority = new JLabel("权限");
    	function = new JLabel("新增用户");
    	user_name = new JLabel("姓名");
    	user_ID = new JLabel("用户编号");
    	user_password = new JLabel("初始密码");
    	infoOKButton = new JButton("ok");
    	
    	user_profession_choose = new JComboBox(user_profession_type);
    	user_salaryPlan_choose = new JComboBox(user_salaryPlan_type);
    	user_authority_choose = new JComboBox(user_authority_type);

    	user_name_Input = new JTextField("");
    	user_ID_Input = new JTextField("");
    	user_password_Input = new JTextField("123456");
    	
    	setCmpLocation();
    	
    	user_profession_choose.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			professionInt = user_profession_choose.getSelectedIndex();
    			salaryPlanInt = professionInt;
    			user_salaryPlan_choose.setSelectedIndex(salaryPlanInt);
    			if(professionInt==0 || professionInt==1 || professionInt==2 || professionInt==4){
    				authorityInt = 0;
    			}
    			else if(professionInt==3){
    				authorityInt = 2;
    			}
    			else if(professionInt==6){
    				authorityInt = 1;
    			}
    			else if(professionInt==5){
    				authorityInt = 3;
    			}
    			user_authority_choose.setSelectedIndex(authorityInt);
    			
    			String[] IDPreList = {"KD", "SJ", "YWY",  "CW",  "CK", "JL", "GLY"};
    			String IDPre = IDPreList[professionInt];
    			ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
						ProfessionType.counterman, ProfessionType.financialStaff, 
						ProfessionType.stockman, ProfessionType.manager, ProfessionType.administrator};
    			ProfessionType profession = professionList[professionInt];
    			String IDPost = userBL.getUserIDPost(profession);
    			
    			user_ID_Input.setText(IDPre+"-"+IDPost);
    		}
    	});
    	
    	user_name_Input.addFocusListener(new FocusAdapter(){
    		public void focusLost(FocusEvent event){
    			userName = user_name_Input.getText();
				if(userName.equals("")){
					warnning("新用户姓名不能为空");
				}
    		}
    	});
    	
    	infoOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				password = user_password_Input.getText();
				organization = "";
				
				professionInt = user_profession_choose.getSelectedIndex();
				ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
												ProfessionType.counterman, ProfessionType.financialStaff, 
												ProfessionType.stockman, ProfessionType.manager, ProfessionType.administrator};
				ProfessionType profession = professionList[professionInt];
				
				salaryPlanInt = user_salaryPlan_choose.getSelectedIndex();
				SalaryPlanType[] salaryPlanList = {SalaryPlanType.courierSalaryPlan, SalaryPlanType.driverSalaryPlan,
						SalaryPlanType.courierSalaryPlan, SalaryPlanType.financialStaffSalaryPlan,
						SalaryPlanType.stockmanSalaryPlan, SalaryPlanType.managerSalaryPlan, SalaryPlanType.administratorSalaryPlan};
				SalaryPlanType salaryPlan = salaryPlanList[salaryPlanInt];
				
				authorityInt = user_authority_choose.getSelectedIndex();
				AuthorityType[] authorityList = {AuthorityType.lowest, AuthorityType.administrator, 
						AuthorityType.commonFianacialStaff, AuthorityType.highest};
				AuthorityType authority = authorityList[authorityInt];
			
				UserVO uservo = new UserVO(userName, userID, password, profession, organization, salaryPlan,authority, 0);
				int returnNum = userBL.addUser(uservo);
				System.out.println(returnNum);
				
				//okui();
			}
		});
    	
    	setLayout(null);
    	
    	add(function);
    	add(user_profession);
    	add(user_profession_choose);
    	add(user_salaryPlan);
    	add(user_salaryPlan_choose);
    	add(user_authority);
    	add(user_authority_choose);
    	add(user_name);
    	add(user_name_Input);
    	add(user_ID);
    	add(user_ID_Input);
    	add(user_password);
    	add(user_password_Input);
    	add(infoOKButton);
    }
    
    public void setCmpLocation(){
    	function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
    	
    	user_profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT / 4,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 11 / 24,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		
		user_name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
		user_ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		user_password.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		/*user_profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);*/
		
		user_profession_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT /4,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_salaryPlan_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_authority_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 11 / 24,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		
		user_name_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_ID_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_password_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		
		/*user_profession_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_salaryPlan_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_authority_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);*/
		
		infoOKButton.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 24);
    }
    
    public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	public void okui() {

	}

	public boolean isNumber(char ch){
		if(ch>=48 && ch<= 57)
			return true;
		else
			return false;
	}
	
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "用户信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new UserPanel_new());
		frame.setVisible(true);
	}
    
    
}
