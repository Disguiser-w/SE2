package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import presentation.commonui.OperationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.FocusListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
    
    private String[] organizationCategoryStr = {"营业厅","中转中心"};

    private JLabel function;
    private JLabel organizationCategory;
    private JLabel organizationID;
    private JLabel organizationIDPrompt;
    private JLabel organizationName;
    private JLabel repertoryID;
    
    private JComboBox<String> categoryChoose;
    
    private JTextField organizationNameInput;
    private JTextField organizationNamePost;
    private JTextField organizationIDInput;
    private JTextField repertoryIDInput;
    private JTextField repertoryIDPost;
    
    private JButton infoOKButton;
    private JButton returnButton;
    
    int categoryChooseInt = 0;
    boolean validID;
    
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
	    organizationIDInput = new JTextField();
	    organizationIDPrompt = new JLabel("格式：XXX000");
		organizationNameInput = new JTextField();
	    organizationNamePost = new JTextField("营业厅");
	    organizationNamePost.setEditable(false);
	    repertoryIDInput = new JTextField("/");
	    repertoryIDInput.setEditable(false);
	    repertoryIDPost = new JTextField(); 
	    repertoryIDPost.setEditable(false);
	    
	    infoOKButton = new JButton("确认");
    	returnButton = new JButton("返回");
    	

    	//加监听
    	categoryChoose.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			categoryChooseInt = categoryChoose.getSelectedIndex();
    			if(categoryChooseInt==1){
    				organizationNamePost.setText("中转中心");
    				organizationIDPrompt.setText("格式：XXX-0");
    				repertoryIDInput.setText("");
    				repertoryIDPost.setText("-CK");
    			}
    		}
    	});
    	
    	organizationIDInput.addFocusListener(new FocusAdapter(){
    		public void focusLost(FocusEvent event){
    			String name = organizationIDInput.getText();
    			if(categoryChooseInt==1){
    				repertoryIDInput.setText(name);
    				repertoryIDPost.setText("-CK");
    			}
    		}
    	});
    	
    	infoOKButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			
    			OrganizationType type; 
    			String name = organizationNameInput.getText()+organizationNamePost.getText();
    			String ID = organizationIDInput.getText();
    			categoryChooseInt = categoryChoose.getSelectedIndex();
    			
    			if(categoryChooseInt==0){
    				type = OrganizationType.businessHall;
    				if(ID.length()==6 && ID.endsWith("000")){
    					validID = true;
    				}
    			}
    			else{
    				type = OrganizationType.intermediateCenter;
    				if(ID.length()==5 && ID.endsWith("-0")){
    					validID = true;
    				}
    			}
    			
    			if(validID){
	    			int returnNum = organizationBL.addOrganization(new OrganizationVO(type, ID, name));
	    			if(returnNum==0)
	    				successAdd();
	    			else
	    				failedAdd();
	    		}
    			else{
    				warnning("错误的机构编号");
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
    	add(organizationCategory);
    	add(organizationName);
    	add(organizationID);
    	add(repertoryID);
    	add(categoryChoose);
    	add(organizationIDInput);
    	add(organizationIDPrompt);
    	add(organizationNameInput);
    	add(organizationNamePost);
    	add(repertoryIDInput);
    	add(repertoryIDPost);
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
    	
    	organizationCategory.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 13/ 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
    	organizationID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		organizationName.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		
		repertoryID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH * 7 / 24, PANEL_HEIGHT / 16);
		
		
		categoryChoose.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 13/ 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		organizationIDInput.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		organizationIDPrompt.setBounds(PANEL_WIDTH *2 / 3, PANEL_HEIGHT * 18 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		organizationNameInput.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		organizationNamePost.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 23 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		repertoryIDInput.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		repertoryIDPost.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 28 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		infoOKButton.setBounds(PANEL_WIDTH * 34 / 48, PANEL_HEIGHT * 35 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnButton.setBounds(PANEL_WIDTH * 5 / 72, PANEL_HEIGHT * 35 / 48,
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
