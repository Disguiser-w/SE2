package presentation.userui;

import java.awt.event.ActionListener;
//import java.awt.event.FocusListener;
//import java.awt.event.FocusAdapter;
import java.awt.event.ActionEvent;
//import java.awt.event.FocusEvent;


import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

import businesslogic.userbl.UserBL;
import vo.UserVO;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import presentation.userui.UserMainPanel;

public class AddUserPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	AdminFrame fatherFrame;
	
	UserBL userBL;
	
    private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private String[] user_profession_type = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","管理员","财务人员","总经理"};
    private String[] user_salaryPlan_type = {"基础月薪+提成","计次提成","基础月薪"};
    private String[] user_authority_type = {"最低权限（普通人员权限）", "管理员权限","普通财务人员权限","最高权限（最高财务人员和总经理）"};
    
    private JLabel function;
    private JLabel user_profession;
    private JLabel user_salaryPlan;
    private JLabel user_authority;
    private JLabel user_name;
    private JLabel user_ID;
    private JLabel user_password;
    
    private JComboBox<String> user_profession_choose;
    private JComboBox<String> user_salaryPlan_choose;
    private JComboBox<String> user_authority_choose;
    
    private JTextField user_name_Input;
    private JTextField user_ID_Input;
    private JTextField user_password_Input;
    
    private JButton infoOKButton;
    private JButton returnButton;
    
    int proInt = 0;
	int professionInt = 0;
	int salaryPlanInt = 0;
	int authorityInt = 0;
    String userName = "";
	String userID = "";
	boolean validName = true;
	String password = "";
	String organization = "";
	
    public AddUserPanel(AdminFrame frame){
    	this.fatherFrame = frame;
    	
    	this.userBL = new UserBL();
    	
    	function = new JLabel("用户管理——新增用户");
    	
    	user_profession = new JLabel("用户职位");
    	user_salaryPlan = new JLabel("薪水策略");
    	user_authority = new JLabel("权限");
    	user_name = new JLabel("姓名");
    	user_ID = new JLabel("用户编号");
    	user_password = new JLabel("初始密码");
    	
    	infoOKButton = new JButton("确认");
    	returnButton = new JButton("返回");
    	
    	user_profession_choose = new JComboBox<String>(user_profession_type);
    	user_salaryPlan_choose = new JComboBox<String>(user_salaryPlan_type);
    	user_authority_choose = new JComboBox<String>(user_authority_type);

    	user_name_Input = new JTextField("");
    	user_ID_Input = new JTextField("");
    	user_password_Input = new JTextField("123456");
    
    	
    	//加监听
    	user_profession_choose.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			professionInt = user_profession_choose.getSelectedIndex();
    			
    			salaryPlanInt = 2;
    			authorityInt = 0;//大部分人的工资策略为基础月薪，权限为最低权限
    			
    			if(professionInt==0){	//快递员
    				salaryPlanInt = 0;
    			}
    			else if(professionInt==1){	//司机
    				salaryPlanInt = 1;
    			}
		    	else if(professionInt==5){	//管理员
    				authorityInt = 1;
    			}
    			else if(professionInt==6){	//财务人员
    				authorityInt = 2;
    			}
    			else if(professionInt==7){	//总经理
    				authorityInt = 3;
    			}
    			
    			user_salaryPlan_choose.setSelectedIndex(salaryPlanInt);
    			user_authority_choose.setSelectedIndex(authorityInt);
    			
    			String[] IDPreList = {"KD","SJ","CK","YYT","ZZZX","GLY","CW","JL"};
    			String IDPre = IDPreList[professionInt];
    			ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
						ProfessionType.stockman,ProfessionType.businessHallCounterman, ProfessionType.intermediateCenterCounterman, 
    					ProfessionType.financialStaff, ProfessionType.administrator,ProfessionType.manager};
    			
    			ProfessionType profession = professionList[professionInt];
    			String IDPost = userBL.getUserIDPost(profession);
    			
    			user_ID_Input.setText(IDPre+"-"+IDPost);
    		}
    	});
    	
    	/*user_name_Input.addFocusListener(new FocusAdapter(){
    		public void focusLost(FocusEvent event){
    			userName = user_name_Input.getText();
				if(userName.equals("")){
					warnning("新用户姓名不能为空");
				}
    		}
    	});*/
    	
    	infoOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				userName = user_name_Input.getText();
				if(userName.equals("")){
					warnning("新用户姓名不能为空");
					validName = false;
				}
				
				userID = user_ID_Input.getText();
				password = user_password_Input.getText();
				organization = "";
				
				professionInt = user_profession_choose.getSelectedIndex();
				ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
										ProfessionType.stockman,ProfessionType.businessHallCounterman, ProfessionType.intermediateCenterCounterman, 
				    					ProfessionType.financialStaff, ProfessionType.administrator,ProfessionType.manager};
				ProfessionType profession = professionList[professionInt];
				
				salaryPlanInt = user_salaryPlan_choose.getSelectedIndex();
				SalaryPlanType[] salaryPlanList = {SalaryPlanType.courierSalaryPlan,SalaryPlanType.driverSalaryPlan,SalaryPlanType.basicStaffSalaryPlan};
				SalaryPlanType salaryPlan = salaryPlanList[salaryPlanInt];
				
				authorityInt = user_authority_choose.getSelectedIndex();
				AuthorityType[] authorityList = {AuthorityType.lowest, AuthorityType.administrator, 
						AuthorityType.commonFianacialStaff, AuthorityType.highest};
				AuthorityType authority = authorityList[authorityInt];
			
				if(validName){
					UserVO uservo = new UserVO(userName, userID, password, profession, organization, salaryPlan,authority, 0);
					int returnNum = userBL.addUser(uservo);
					if(returnNum==0)
						successAdd();
					else
						failedAdd();
				}
			}
		});
    	
    	returnButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			returnui();
    		}
    	});

    	
    	//把组件加到Panel上
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
    	add(returnButton);
    	
    	setVisible(true);
    	
    	//位置设置
    	setCmpLocation();
    }
    
    
    //设置位置
    public void setCmpLocation(){
    	function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
    	
    	user_profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 8 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 13 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
		user_ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		user_password.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 33 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		user_profession_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 8 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_salaryPlan_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 13/ 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_authority_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_name_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_ID_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_password_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 33 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		
		infoOKButton.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 40 / 48,
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

    
    //返回上一级界面
	public void returnui() {
		UserVO vo = fatherFrame.vo;
		AdminFrame newAdminFrame = new AdminFrame(vo);
		newAdminFrame.addFuncLabel(new UserMainPanel(newAdminFrame));
		newAdminFrame.showFrame();
	}

	
	//新增成功时返回上一级界面，同时给用户提示信息
	public void successAdd(){
		returnui();
		JOptionPane.showMessageDialog(null, "添加成功(●'◡'●)", "新增用户成功", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//新增失败时返回上一级界面，同时给用户提示信息
	public void failedAdd(){
		returnui();
		JOptionPane.showMessageDialog(null, "添加失败(T_T)", "新增用户失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "用户信息错误", JOptionPane.ERROR_MESSAGE);
	}

	
}
