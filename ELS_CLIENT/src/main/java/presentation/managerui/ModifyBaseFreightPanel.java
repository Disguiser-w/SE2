package presentation.managerui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;

import vo.CostVO;
import type.ExpressType;
import businesslogic.managebl.CostBL;

public class ModifyBaseFreightPanel extends OperationPanel {

	private static final long serialVersionUID = 68L;

	ManageFrame manageFrame;
	BasicDataManagePanel basicDataManagePanel;
	
	CostBL costBL;

	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private JLabel function;
    private JLabel expressLabel;
    private JLabel baseFreightLabel;
    
    private MyTextField expressField;
    private MyTextField baseFreightInput;
    private MyTextField baseFreightPost;
    
    private MyLabel OKLabel;
    private MyLabel returnLabel;
    
    private String expressStr;
	
	public ModifyBaseFreightPanel(ManageFrame manageFrame, BasicDataManagePanel managePanel, String express){
		
		this.manageFrame = manageFrame;
		this.basicDataManagePanel = managePanel;
		
		costBL = new CostBL();
		
		function = new JLabel("基础数据管理——修改运费系数");
		
		expressLabel = new JLabel("快递种类");
		baseFreightLabel = new JLabel("运费系数");
		
		expressField = new MyTextField();
		expressField.setText(express);
		expressField.setEditable(false);
	    baseFreightInput = new MyTextField();
	    baseFreightPost = new MyTextField();
	    baseFreightPost.setText("元/(公里*公斤)");
	    baseFreightPost.setEditable(false);
	    
	    expressStr = express;
		
	    OKLabel = new MyLabel("确认");
    	returnLabel = new MyLabel("返回");
    	
    	//加监听
    	OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
    			
    			Double baseFreightDouble = Double.parseDouble(baseFreightInput.getText());
    			ExpressType expressType = ExpressType.ECONOMIC;
    			
    			ExpressType[] expressTypeList = {ExpressType.ECONOMIC, ExpressType.STANDARD, ExpressType.FAST};
    			String[] expressNameList = {"经济型","标准型","特快型"};
    			
    			int expressInt = 0;
    			for(expressInt=0;expressInt<3;expressInt++){
    				if(expressNameList[expressInt].equals(expressStr)){
    					expressType = expressTypeList[expressInt];
    					break;
    				}
    			}
    			
				int returnNum = costBL.modifyCost(new CostVO(expressType, baseFreightDouble));
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
    	add(expressLabel);
    	add(baseFreightLabel);
    	add(expressField);
    	add(baseFreightInput);
    	add(baseFreightPost);
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
    	
    	expressLabel.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	baseFreightLabel.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);

		expressField.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		baseFreightInput.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		baseFreightPost.setBounds(PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		OKLabel.setBounds(PANEL_WIDTH * 30 / 48, PANEL_HEIGHT * 35 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnLabel.setBounds(PANEL_WIDTH * 6 / 48, PANEL_HEIGHT * 35 / 48,
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
		JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "修改运费系数成功", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//修改失败时返回上一级界面，同时给用户提示信息
	public void failedModify(){
		JOptionPane.showMessageDialog(null, "修改失败(T_T)", "修改运费系数失败", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//修改错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "运费系数信息错误", JOptionPane.ERROR_MESSAGE);
	}

}

