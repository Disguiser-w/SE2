package presentation.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presentation.financeui.CostIncomeReceiptPanel_new;

public class UserPanel_new extends JLabel{
    private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    private String[] user_profession_type = {"快递员","营业厅业务员","中转中心业务员","仓库管理员","财务人员","管理员","总经理"};
    private String[] user_salaryPlan_type = {"Courier Salary Plan",""};
    private String[] user_authority_type = {"low","normal","high"};
    
    private JButton infoOKButton;
    
    private JLabel function;
    private JLabel user_name;
    private JLabel user_ID;
    private JLabel user_password;
    private JLabel user_profession;
    private JLabel user_salaryPlan;
    private JLabel user_authority;
    
    private JTextField user_name_Input;
    private JTextField user_ID_Input;
    private JTextField user_password_Input;
    
    private JComboBox user_profession_choose;
    private JComboBox user_salaryPlan_choose;
    private JComboBox user_authority_choose;
    
    public UserPanel_new(){
    	infoOKButton = new JButton("ok");
    	
    	function = new JLabel("新增用户");
    	user_name = new JLabel("姓名");
    	user_ID = new JLabel("用户编号");
    	user_password = new JLabel("初始密码");
    	user_profession = new JLabel("用户职位");
    	user_salaryPlan = new JLabel("薪水策略");
    	user_authority = new JLabel("权限");
    	
    	user_name_Input = new JTextField("");
    	user_ID_Input = new JTextField("");
    	user_password_Input = new JTextField("123456");
    	
    	user_profession_choose = new JComboBox(user_profession_type);
    	user_salaryPlan_choose = new JComboBox(user_salaryPlan_type);
    	user_authority_choose = new JComboBox(user_authority_type);

    	setCmpLocation();
    	
    	infoOKButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				okui();
			}
		});
    	
    	setLayout(null);
    	
    	add(infoOKButton);
    	add(function);
    	add(user_authority);
    	add(user_authority_choose);
    	add(user_ID);
    	add(user_ID_Input);
    	add(user_name);
    	add(user_name_Input);
    	add(user_password);
    	add(user_password_Input);
    	add(user_profession);
    	add(user_profession_choose);
    	add(user_salaryPlan);
    	add(user_salaryPlan_choose);
    }
    
    public void setCmpLocation(){
    	function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		infoOKButton.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
				PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
		user_name.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT / 4,
				PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
		user_ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		user_password.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 11 / 24,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		user_profession.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_salaryPlan.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_authority.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		user_name_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT / 4,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_ID_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_password_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 11 / 24,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_profession_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_salaryPlan_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		user_authority_choose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
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

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 550);
		frame.add(new UserPanel_new());
		frame.setVisible(true);
	}
    
    
}
