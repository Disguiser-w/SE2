package presentation.managerui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import businesslogic.managebl.PerWageBL;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import type.ProfessionType;
import vo.PerWageVO;

public class ModifyPerWagePanel extends OperationPanel {

	private static final long serialVersionUID = 69L;
	
	ManageFrame manageFrame;
	BasicDataManagePanel basicDataManagePanel;
	
	PerWageBL perWageBL;

	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private MyTextLabel function;
    private MyTextLabel professionLabel;
    private MyTextLabel perWageLabel;
    
    private MyTextField professionField;
    private MyTextField perWageInput;
    private MyTextField perWagePost;
    
    private MyLabel OKLabel;
    private MyLabel returnLabel;
    
    private String professionStr;
    
	public ModifyPerWagePanel(ManageFrame manageFrame, BasicDataManagePanel managePanel, String profession){
		
		this.manageFrame = manageFrame;
		this.basicDataManagePanel = managePanel;
		
		perWageBL = new PerWageBL();
		
		function = new MyTextLabel("基础数据管理——修改每次工资");
		
		professionLabel = new MyTextLabel("职业");
		perWageLabel = new MyTextLabel("每次工资");
		
		professionField = new MyTextField();
		professionField.setText(profession);
		professionField.setEditable(false);
	    perWageInput = new MyTextField();
	    perWagePost = new MyTextField();
	    perWagePost.setText("元");
	    
	    professionStr = profession;
		
	    OKLabel = new MyLabel("确认");
    	returnLabel = new MyLabel("返回");
    	
    	//加监听
    	OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

    			ProfessionType professionType = ProfessionType.courier; 
    			Double perWageDouble = Double.parseDouble(perWageInput.getText());

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
    			
				int returnNum = perWageBL.modifyPerWage(new PerWageVO(professionType, perWageDouble));
				if(returnNum==0)
					successModify();
				else
					failedModify();
    		}
    	});
    	
    	returnLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
    			returnui();
    		}
    	});
    	
    	
    	//把组件加到Panel上
    	setLayout(null);
    	
    	add(function);
    	add(professionLabel);
    	add(perWageLabel);
    	add(professionField);
    	add(perWageInput);
    	add(perWagePost);
    	add(OKLabel);
    	add(returnLabel);

    	setVisible(true);
    	
    	//位置设置
    	setCmpLocation();
	}
	
	
	//设置位置
	public void setCmpLocation(){
		function.setBounds(PANEL_WIDTH / 24, PANEL_HEIGHT / 10,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);

    	professionLabel.setBounds(PANEL_WIDTH / 4, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	perWageLabel.setBounds(PANEL_WIDTH / 4, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);

		professionField.setBounds(PANEL_WIDTH * 2 / 5, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		perWageInput.setBounds(PANEL_WIDTH * 2 / 5, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		perWagePost.setBounds(PANEL_WIDTH * 17 / 30, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		OKLabel.setBounds(PANEL_WIDTH * 35 / 48, PANEL_HEIGHT * 35 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnLabel.setBounds(PANEL_WIDTH * 5 / 48, PANEL_HEIGHT * 35 / 48,
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
		manageFrame.toMainPanel();
	}
	
	
	//修改成功时返回上一级界面，同时给用户提示信息
	public void successModify(){
		JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "修改每次工资成功", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//修改失败时返回上一级界面，同时给用户提示信息
	public void failedModify(){
		JOptionPane.showMessageDialog(null, "修改失败(T_T)", "修改每次工资失败", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "每次工资信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
}
