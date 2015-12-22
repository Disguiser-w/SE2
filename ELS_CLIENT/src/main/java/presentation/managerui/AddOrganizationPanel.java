package presentation.managerui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;

import businesslogic.managebl.OrganizationBL;
import type.OrganizationType;
import vo.OrganizationVO;

public class AddOrganizationPanel extends OperationPanel {

	private static final long serialVersionUID = 21L;
	
	ManageFrame manageFrame;
	OrganizationManagePanel organizationManagePanel;
	
	OrganizationBL organizationBL;

	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private String[] organizationCategoryStr = {"中转中心","营业厅"};

    private JLabel function;
    private JLabel organizationCategory;
    private JLabel organizationID;
    private JLabel organizationIDPrompt;
    private JLabel organizationName;
    private JLabel organizationNamePrompt;
    private JLabel repertoryID;
    
    private JComboBox<String> categoryChoose;
    
    private MyTextField organizationNameInput;
    private MyTextField organizationIDInput;
    private MyTextField repertoryIDInput;
    private MyTextField repertoryIDPost;
    
    private MyLabel OKLabel;
    private MyLabel returnLabel;
    
    int categoryChooseInt = 0;
    
    boolean validID;
    boolean validName;
    
	public AddOrganizationPanel(ManageFrame manageFrame, OrganizationManagePanel managePanel){
		
		this.manageFrame = manageFrame;
		this.organizationManagePanel = managePanel;
		
		organizationBL = new OrganizationBL();
		
		function = new JLabel("机构管理——新增机构");
		
		organizationCategory = new JLabel("机构类别");
		organizationID = new JLabel("机构编号");
		organizationName = new JLabel("机构名称");
		repertoryID = new JLabel("下属仓库编号");
		
		categoryChoose = new JComboBox<String>(organizationCategoryStr);
		
	    organizationIDInput = new MyTextField();
	   
		organizationNameInput = new MyTextField();
	    repertoryIDInput = new MyTextField();
	    repertoryIDInput.setText("");
	    repertoryIDInput.setEditable(false);
	    repertoryIDPost = new MyTextField(); 
	    repertoryIDPost.setText("-CK");
	    repertoryIDPost.setEditable(false);
	    
	    organizationIDPrompt = new JLabel("格式：XXX（三位区号）-X（从0开始编号）");
	    organizationNamePrompt = new JLabel("请填入城市名+中转中心，如：南京中转中心");
	    
	    OKLabel = new MyLabel("确认");
    	returnLabel = new MyLabel("返回");
    	

    	//加监听
    	categoryChoose.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			categoryChooseInt = categoryChoose.getSelectedIndex();
    			if(categoryChooseInt == 0){
    				organizationIDPrompt.setText("格式：XXX（三位区号）-X（从0开始编号）");
    				organizationNamePrompt.setText("请填入城市名，如：南京");
    				repertoryIDInput.setText("");
    				repertoryIDPost.setText("-CK");
    			}
    			else{
    				organizationIDPrompt.setText("格式：XXX（三位区号）XXX（从001开始编号）");
    				organizationNamePrompt.setText("请填入所在城市名+营业厅名，如：南京市鼓楼营业厅");
    				repertoryIDInput.setText("/");
    				repertoryIDPost.setText("");
    			}
    		}
    	});
    	
    	organizationIDInput.addFocusListener(new FocusAdapter(){
    		public void focusLost(FocusEvent event){
    			String name = organizationIDInput.getText();
    			if(categoryChooseInt == 0){
    				repertoryIDInput.setText(name);
    				repertoryIDPost.setText("-CK");
    			}
    		}
    	});
    	
    	OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
    			
    			OrganizationType type; 
    			String name = organizationNameInput.getText();
    			String ID = organizationIDInput.getText();
    			categoryChooseInt = categoryChoose.getSelectedIndex();
    			
    			if(categoryChooseInt == 0){
    				type = OrganizationType.intermediateCenter;
    				if(ID.length() == 5 && ID.contains("-"))
    					validID = true;
    				if(name.contains("市") && name.endsWith("中转中心"))
    					validName = true;
    			}
    			else{
    				type = OrganizationType.businessHall;
    				if(ID.length() == 6)
    					validID = true;
    				if(name.endsWith("营业厅"))
    					validName = true;
    			}
    			
    			if(!validID){
    				warnning("错误的机构编号");
    				return;
	    		}
    			else if(!validName){
    				warnning("错误的机构名称");
    				return;
    			}
    			else{
    				int returnNum = organizationBL.addOrganization(new OrganizationVO(type, ID, name));
	    			if(returnNum==0)
	    				successAdd();
	    			else
	    				failedAdd();
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
    	
    	add(function);
    	add(organizationCategory);
    	add(organizationName);
    	add(organizationID);
    	add(repertoryID);
    	add(categoryChoose);
    	add(organizationIDInput);
    	add(organizationIDPrompt);
    	add(organizationNameInput);
    	add(organizationNamePrompt);
    	add(repertoryIDInput);
    	add(repertoryIDPost);
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
    	
    	organizationCategory.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 13/ 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
    	organizationID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		organizationName.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		repertoryID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		
		categoryChoose.setBounds(PANEL_WIDTH / 3, PANEL_HEIGHT * 13/ 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		organizationIDInput.setBounds(PANEL_WIDTH / 3, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		organizationIDPrompt.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH * 3 / 8, PANEL_HEIGHT / 16);
		organizationNameInput.setBounds(PANEL_WIDTH / 3, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		organizationNamePrompt.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH * 3 / 8, PANEL_HEIGHT / 16);
		repertoryIDInput.setBounds(PANEL_WIDTH / 3, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		repertoryIDPost.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		
		OKLabel.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 35 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnLabel.setBounds(PANEL_WIDTH * 5 / 72, PANEL_HEIGHT * 35 / 48,
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
		manageFrame.changePanel(organizationManagePanel);
		updateUI();
	}
	
	
	//新增成功时返回上一级界面，同时给用户提示信息
	public void successAdd(){
		JOptionPane.showMessageDialog(null, "添加成功(●'◡'●)", "新增用户成功", JOptionPane.INFORMATION_MESSAGE);
		manageFrame.changePanel(organizationManagePanel);
		updateUI();
	}
	
	//新增失败时返回上一级界面，同时给用户提示信息
	public void failedAdd(){
		JOptionPane.showMessageDialog(null, "添加失败(T_T)", "新增用户失败", JOptionPane.INFORMATION_MESSAGE);
		manageFrame.changePanel(organizationManagePanel);
		updateUI();
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "机构信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
	
}
