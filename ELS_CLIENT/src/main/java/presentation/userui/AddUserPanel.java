package presentation.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import businesslogic.userbl.UserBL;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;

public class AddUserPanel extends OperationPanel {
	
	private static final long serialVersionUID = 1L;

	private AdminFrame adminFrame;
	private UserMainPanel userMainPanel;
	
	private UserBL userBL;
	
    private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private String[] user_profession_type = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","财务人员","总经理"};
    private String[] user_salaryPlan_type = {"基础月薪+提成","计次提成","基础月薪"};
    private String[] user_authority_type = {"最低权限（普通人员权限）", "普通财务人员权限", "最高权限（最高财务人员和总经理）"};
    private String[] user_authority_type_for_financialStaff = {"普通财务人员权限", "最高权限（最高财务人员和总经理）"};
    
    private MyTextLabel function;
    private MyTextLabel user_profession;
    private MyTextLabel user_salaryPlan;
    private MyTextLabel user_authority;
    private MyTextLabel user_name;
    private MyTextLabel user_ID;
    private MyTextLabel user_password;
    
    private MyComboBox<String> user_profession_choose;
    private MyComboBox<String> user_salaryPlan_choose;
    private MyComboBox<String> user_authority_choose;
    
    private MyTextField user_name_Input;
    private MyTextField user_ID_Input;
    private MyTextField user_password_Input;
    
    private MyLabel OKLabel;
    private MyLabel returnLabel;
    
    int proInt;
	int professionInt;
	int salaryPlanInt;
	int authorityInt;
    String userName;
	String userID;
	String password;
	String organization;
	boolean validName;
	
	
    public AddUserPanel(AdminFrame frame, UserMainPanel userMainPanel){
    	
    	this.adminFrame = frame;
    	this.userMainPanel = userMainPanel;
    	
    	try {
			this.userBL = new UserBL();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	function = new MyTextLabel("用户管理——新增用户");
    	
    	user_profession = new MyTextLabel("用户职位");
    	user_salaryPlan = new MyTextLabel("薪水策略");
    	user_authority = new MyTextLabel("权限");
    	user_name = new MyTextLabel("姓名");
    	user_ID = new MyTextLabel("用户编号");
    	user_password = new MyTextLabel("初始密码");
    	
    	OKLabel = new MyLabel("确认");
    	returnLabel = new MyLabel("返回");
    	
    	user_profession_choose = new MyComboBox<String>();
    	user_profession_choose.addItem(user_profession_type[0]);
    	user_profession_choose.addItem(user_profession_type[1]);
    	user_profession_choose.addItem(user_profession_type[2]);
    	user_profession_choose.addItem(user_profession_type[3]);
    	user_profession_choose.addItem(user_profession_type[4]);
    	user_profession_choose.addItem(user_profession_type[5]);
    	user_profession_choose.addItem(user_profession_type[6]);
    	
    	user_salaryPlan_choose = new MyComboBox<String>();
    	user_salaryPlan_choose.addItem(user_salaryPlan_type[0]);
    	user_salaryPlan_choose.addItem(user_salaryPlan_type[1]);
    	user_salaryPlan_choose.addItem(user_salaryPlan_type[2]);
    	
    	user_authority_choose = new MyComboBox<String>();
    	user_authority_choose.addItem(user_authority_type[0]);
    	user_authority_choose.addItem(user_authority_type[1]);
    	user_authority_choose.addItem(user_authority_type[2]);

    	user_name_Input = new MyTextField();
    	user_ID_Input = new MyTextField();
    	user_password_Input = new MyTextField();
    
    	proInt = 0;
		professionInt = 0;
		salaryPlanInt = 0;
		authorityInt = 0;
	    userName = "";
		userID = "";
		validName = true;
		password = "";
		organization = "";
		
		//先设置为快递员的，就算管理员不点击快递员，其他内容也可以被填充
		String IDPre = "KD";
		user_salaryPlan_choose.setSelectedIndex(0);
		user_salaryPlan_choose.setEnabled(false);
		user_authority_choose.setSelectedIndex(0);
		user_authority_choose.setEnabled(false);
		String IDPost = userBL.getUserIDPost(ProfessionType.courier);
		user_ID_Input.setText(IDPre+"-"+IDPost);
		user_ID_Input.setEditable(false);
		user_password_Input.setText("123456");
		user_password_Input.setEditable(false);
		
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
		    	else if(professionInt==5){	//财务人员
    				authorityInt = 1;
    			}
    			else if(professionInt==6){	//总经理
    				authorityInt = 2;
    			}

    			user_salaryPlan_choose.setSelectedIndex(salaryPlanInt);
    			user_salaryPlan_choose.setEnabled(false);
    			if(professionInt != 5){
    				//除了财务人员之外的其他人，权限都可以被确定
    				user_authority_choose.setSelectedIndex(authorityInt);
    				user_authority_choose.setEnabled(false);
    			}
    			else{
    				//如果是财务人员，权限只有2种
    				remove(user_authority_choose);
    				user_authority_choose = new MyComboBox<String>();
    				user_authority_choose.addItem(user_authority_type_for_financialStaff[0]);
    				user_authority_choose.addItem(user_authority_type_for_financialStaff[1]);
    				user_authority_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 18 / 48,
    						PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
    				add(user_authority_choose);
    				user_authority_choose.setEnabled(true);
    			}
    			
    			String[] IDPreList = {"KD","SJ","CK","YYT","ZZZX","CW","JL"};
    			String IDPre = IDPreList[professionInt];
    			ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
						ProfessionType.stockman,ProfessionType.businessHallCounterman, ProfessionType.intermediateCenterCounterman, 
						ProfessionType.financialStaff, ProfessionType.manager};
    			
    			ProfessionType profession = professionList[professionInt];
    			String IDPost = userBL.getUserIDPost(profession);
    			
    			user_ID_Input.setText(IDPre+"-"+IDPost);
    			user_ID_Input.setEditable(false);
    			
    			user_password_Input.setEditable(false);
    			
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
    	
    	OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				userName = user_name_Input.getText();
				if(userName.equals("")){
					warnning("新用户姓名不能为空");
					validName = false;
				}
				
				professionInt = user_profession_choose.getSelectedIndex();
				ProfessionType[] professionList = {ProfessionType.courier, ProfessionType.driver,
										ProfessionType.stockman,ProfessionType.businessHallCounterman, ProfessionType.intermediateCenterCounterman, 
										ProfessionType.financialStaff, ProfessionType.manager};
				ProfessionType profession = professionList[professionInt];
				
				salaryPlanInt = user_salaryPlan_choose.getSelectedIndex();
				SalaryPlanType[] salaryPlanList = {SalaryPlanType.courierSalaryPlan,SalaryPlanType.driverSalaryPlan,SalaryPlanType.basicStaffSalaryPlan};
				SalaryPlanType salaryPlan = salaryPlanList[salaryPlanInt];
				if(professionInt != 5){
					authorityInt = user_authority_choose.getSelectedIndex();
				}
				else{
					authorityInt = user_authority_choose.getSelectedIndex() + 1;
    				System.out.println(authorityInt);
				}
				AuthorityType[] authorityList = {AuthorityType.lowest,AuthorityType.commonFianacialStaff, AuthorityType.highest};
				AuthorityType authority = authorityList[authorityInt];
			
				userID = user_ID_Input.getText();
				password = user_password_Input.getText();
				if(professionInt==5 || professionInt==6)
				organization = "总部";
				
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
    	
    	returnLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
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
    	
    	user_profession.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 8 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_salaryPlan.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 13 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_authority.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_name.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
		user_ID.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		user_password.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 33 / 48,
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
		
		OKLabel.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 40 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnLabel.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 40 / 48,
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
