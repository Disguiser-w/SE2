package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import presentation.commonui.OperationPanel;
import vo.CityDistanceVO;
import businesslogic.managebl.CityDistanceBL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.FocusListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddDistancePanel extends OperationPanel {

	private static final long serialVersionUID = 67L;

	ManageFrame manageFrame;
	BasicDataManagePanel basicDataManagePanel;
	
	CityDistanceBL cityDistanceBL;

	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private JLabel function;
    private JLabel cityA;
    private JLabel cityB;
    private JLabel distance;
    
    private JTextField cityAInput;
    private JTextField cityBInput;
    private JTextField distanceInput;
    private JTextField distancePost;
    
    private JButton infoOKButton;
    private JButton returnButton;
    
    boolean validCityInput;
    
	public AddDistancePanel(ManageFrame manageFrame, BasicDataManagePanel managePanel){
		
		this.manageFrame = manageFrame;
		this.basicDataManagePanel = managePanel;
		
		cityDistanceBL = new CityDistanceBL();
		
		function = new JLabel("基础数据管理——新增城市间距离");
		
		cityA = new JLabel("城市A名称");
		cityB = new JLabel("城市B名称");
		distance = new JLabel("城市间距离");
		
		cityAInput = new JTextField();
	    cityBInput = new JTextField();
	    distanceInput = new JTextField();
	    distancePost = new JTextField("KM");
	    distancePost.setEditable(false);
		
	    infoOKButton = new JButton("确认");
    	returnButton = new JButton("返回");
    	
    	
    	//加监听
    	cityBInput.addFocusListener(new FocusAdapter(){
    		public void focusLost(FocusEvent event){
    			String cityAStr = cityAInput.getText();
    			String cityBStr = cityBInput.getText();
    			if(cityAStr.equals(cityBStr)){
    				warnning("城市A和城市B名称相同");
    				validCityInput = false;
    			}
    			else
    				validCityInput = true;
    		}
    	});
    	
    	infoOKButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			String cityAStr = cityAInput.getText();
    			String cityBStr = cityBInput.getText();
    			Double distanceDouble = Double.parseDouble(distanceInput.getText());
    			
    			if(validCityInput){
    				int returnNum = cityDistanceBL.addCityDistance(new CityDistanceVO(cityAStr, cityBStr, distanceDouble));
    				if(returnNum==0)
    					successAdd();
    				else
    					failedAdd();
    			}
    			else
    				warnning("城市A和城市B名称相同");
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
    	add(cityA);
    	add(cityB);
    	add(distance);
    	add(cityAInput);
    	add(cityBInput);
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
		function.setBounds(PANEL_WIDTH / 24, PANEL_HEIGHT / 8,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
    	
    	cityA.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	cityB.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	distance.setBounds(PANEL_WIDTH / 6, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);

		cityAInput.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		cityBInput.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 25 / 48,
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
	
	
	//新增成功时返回上一级界面，同时给用户提示信息
	public void successAdd(){
		JOptionPane.showMessageDialog(null, "添加成功(●'◡'●)", "新增城市距离成功", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//新增失败时返回上一级界面，同时给用户提示信息
	public void failedAdd(){
		JOptionPane.showMessageDialog(null, "添加失败(T_T)", "新增城市距离失败", JOptionPane.INFORMATION_MESSAGE);
		returnui();
	}
	
	//出现错误时给用户的提示信息
	public void warnning(String message){
		JOptionPane.showMessageDialog(null, message, "城市距离信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
	
}
