package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import presentation.commonui.OperationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.FocusListener;


import vo.CityDistanceVO;
import businesslogic.managebl.CityDistanceBL;

public class ModifyDistancePanel extends OperationPanel {

	private static final long serialVersionUID = 67L;

	ManageFrame manageFrame;
	BasicDataManagePanel basicDataManagePanel;
	
	CityDistanceBL cityDistanceBL;

	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private JLabel function;
    private JLabel cityALabel;
    private JLabel cityBLabel;
    private JLabel distanceLabel;
    
    private JTextField cityAField;
    private JTextField cityBField;
    private JTextField distanceInput;
    private JTextField distancePost;
    
    private String cityAStr;
	private String cityBStr;
	
    private JButton infoOKButton;
    private JButton returnButton;
    
	public ModifyDistancePanel(ManageFrame manageFrame, BasicDataManagePanel managePanel, String cityA, String cityB){
		
		this.manageFrame = manageFrame;
		this.basicDataManagePanel = managePanel;
		
		cityDistanceBL = new CityDistanceBL();
		
		function = new JLabel("基础数据管理——修改城市间距离");
		
		cityALabel = new JLabel("城市A名称");
		cityBLabel = new JLabel("城市B名称");
		distanceLabel = new JLabel("城市间距离");
		
		cityAField = new JTextField(cityA);
		cityAField.setEditable(false);
	    cityBField = new JTextField(cityB);
	    cityBField.setEditable(false);
	    distanceInput = new JTextField();
	    distancePost = new JTextField("KM");
	    
	    cityAStr = cityA;
		cityBStr = cityB;
		
	    infoOKButton = new JButton("确认");
    	returnButton = new JButton("返回");
    	
    	//加监听
    	infoOKButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			Double distanceDouble = Double.parseDouble(distanceInput.getText());
				int returnNum = cityDistanceBL.modifyCityDistance(new CityDistanceVO(cityAStr, cityBStr, distanceDouble));
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
    	add(cityALabel);
    	add(cityBLabel);
    	add(distanceLabel);
    	add(cityAField);
    	add(cityBField);
    	add(distanceInput);
    	add(distancePost);
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
    	
    	cityALabel.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	cityBLabel.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	distanceLabel.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);

		cityAField.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		cityBField.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		distanceInput.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		distancePost.setBounds(PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		
		infoOKButton.setBounds(PANEL_WIDTH * 30 / 48, PANEL_HEIGHT * 36 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnButton.setBounds(PANEL_WIDTH * 6 / 48, PANEL_HEIGHT * 36 / 48,
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
		JOptionPane.showMessageDialog(null, "修改成功(●'◡'●)", "修改城市距离成功", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//修改失败时返回上一级界面，同时给用户提示信息
	public void failedModify(){
		JOptionPane.showMessageDialog(null, "修改失败(T_T)", "修改城市距离失败", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "城市距离信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
	
}
