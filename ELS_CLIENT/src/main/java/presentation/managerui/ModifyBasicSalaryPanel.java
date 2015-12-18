package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import presentation.commonui.OperationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vo.BasicSalaryVO;
import type.ProfessionType;
import businesslogic.managebl.BasicSalaryBL;

public class ModifyBasicSalaryPanel extends OperationPanel {

	private static final long serialVersionUID = 68L;

	ManageFrame manageFrame;
	BasicDataManagePanel basicDataManagePanel;
	
	BasicSalaryBL basicSalaryBL;

	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private JLabel function;
    private JLabel professionLabel;
    private JLabel basicSalaryLabel;
    
    private JTextField professionField;
    private JTextField basicSalaryInput;
    private JTextField basicSalaryPost;
    
    private String professionStr;
	
    private JButton infoOKButton;
    private JButton returnButton;
    
	public ModifyBasicSalaryPanel(ManageFrame manageFrame, BasicDataManagePanel managePanel, String profession){
		
		this.manageFrame = manageFrame;
		this.basicDataManagePanel = managePanel;
		
		basicSalaryBL = new BasicSalaryBL();
		
		function = new JLabel("基础数据管理——修改基础月薪");
		
		professionLabel = new JLabel("职业");
		basicSalaryLabel = new JLabel("基础月薪");
		
		professionField = new JTextField(profession);
		professionField.setEditable(false);
	    basicSalaryInput = new JTextField();
	    basicSalaryPost = new JTextField("元");
	    
	    professionStr = profession;
		
	    infoOKButton = new JButton("确认");
    	returnButton = new JButton("返回");
    	
    	//加监听
    	infoOKButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			
    			ProfessionType professionType = ProfessionType.courier; 
    			Double basicSalaryDouble = Double.parseDouble(basicSalaryInput.getText());

    			ProfessionType[] professionTypeList = {ProfessionType.courier, ProfessionType.driver, ProfessionType.stockman, 
    					ProfessionType.businessHallCounterman, ProfessionType.intermediateCenterCounterman, ProfessionType.administrator,
    					ProfessionType.financialStaff, ProfessionType.manager};
    			String[] professionNameList = {"快递员","司机","仓库管理员","营业厅业务员","中转中心业务员","管理员","财务人员","总经理"};
    			
    			int professionInt = 0;
    			for(professionInt = 0;professionInt<8;professionInt++){
    				if(professionNameList[professionInt].equals(professionStr)){
    					professionType = professionTypeList[professionInt];
    					break;
    				}
    			}
    			
				int returnNum = basicSalaryBL.modifyBasicSalary(new BasicSalaryVO(professionType, basicSalaryDouble));
				if(returnNum==0)
					successModify();
				else
					failedModify();
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
    	add(professionLabel);
    	add(basicSalaryLabel);
    	add(professionField);
    	add(basicSalaryInput);
    	add(basicSalaryPost);
    	add(infoOKButton);
    	add(returnButton);

    	setVisible(true);
    	
    	//位置设置
    	setCmpLocation();
	}
	
	
	//设置位置
	public void setCmpLocation(){
		function.setBounds(PANEL_WIDTH / 24, PANEL_HEIGHT / 10,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
    	
    	professionLabel.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	basicSalaryLabel.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);

		professionField.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		basicSalaryInput.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		basicSalaryPost.setBounds(PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		infoOKButton.setBounds(PANEL_WIDTH * 30 / 48, PANEL_HEIGHT * 35 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnButton.setBounds(PANEL_WIDTH * 6 / 48, PANEL_HEIGHT * 35 / 48,
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
	public void returnui(){
		manageFrame.changePanel(basicDataManagePanel);
		updateUI();
	}
	
	
	//修改成功时返回上一级界面，同时给用户提示信息
	public void successModify(){
		JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "修改基础月薪成功", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//修改失败时返回上一级界面，同时给用户提示信息
	public void failedModify(){
		JOptionPane.showMessageDialog(null, "修改失败(T_T)", "修改基础月薪失败", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "基础月薪信息错误", JOptionPane.ERROR_MESSAGE);
	}

}
