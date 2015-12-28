package presentation.managerui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import businesslogic.managebl.CityDistanceBL;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import vo.CityDistanceVO;

public class AddDistancePanel extends OperationPanel {

	private static final long serialVersionUID = 67L;

	ManageFrame manageFrame;
	BasicDataManagePanel basicDataManagePanel;
	
	CityDistanceBL cityDistanceBL;

	private int PANEL_WIDTH = 720;
    private int PANEL_HEIGHT = 480;
    
    private MyTextLabel function;
    private MyTextLabel cityA;
    private MyTextLabel cityB;
    private MyTextLabel distance;
    
    private MyTextField cityAInput;
    private MyTextField cityBInput;
    private MyTextField distanceInput;
    private MyTextField distancePost;
    
    private MyLabel OKLabel;
    private MyLabel returnLabel;
    
	public AddDistancePanel(ManageFrame manageFrame, BasicDataManagePanel managePanel){
		
		this.manageFrame = manageFrame;
		this.basicDataManagePanel = managePanel;
		
		cityDistanceBL = new CityDistanceBL();
		
		function = new MyTextLabel("基础数据管理——新增城市间距离");
		
		cityA = new MyTextLabel("城市A名称");
		cityB = new MyTextLabel("城市B名称");
		distance = new MyTextLabel("城市间距离");
		
		cityAInput = new MyTextField();
	    cityBInput = new MyTextField();
	    distanceInput = new MyTextField();
	    distancePost = new MyTextField();
	    distancePost.setText("KM");
	    distancePost.setEditable(false);
		
	    OKLabel = new MyLabel("确认");
    	returnLabel = new MyLabel("返回");
    	
    	
    	//加监听
    	
    	OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
    			String cityAStr = cityAInput.getText();
    			String cityBStr = cityBInput.getText();
    			if(cityAStr.equals(cityBStr)){
    				warnning("城市A和城市B名称相同");
    				return;
    			}
    			Double distanceDouble = Double.parseDouble(distanceInput.getText());
    			
    			if(distanceDouble < 0){
    				warnning("城市间距离应为正数");
    				return;
    			}
				int returnNum = cityDistanceBL.addCityDistance(new CityDistanceVO(cityAStr, cityBStr, distanceDouble));
				if(returnNum==0)
					successAdd();
				else
					failedAdd();
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
    	add(cityA);
    	add(cityB);
    	add(distance);
    	add(cityAInput);
    	add(cityBInput);
    	add(distanceInput);
    	add(distancePost);
    	add(OKLabel);
    	add(returnLabel);

    	setVisible(true);
    	
    	//位置设置
    	setCmpLocation();
	}
	
	
	//设置位置
	public void setCmpLocation(){
		function.setBounds(PANEL_WIDTH / 24, PANEL_HEIGHT / 8,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
    	
    	cityA.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	cityB.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 22 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
    	distance.setBounds(PANEL_WIDTH / 5, PANEL_HEIGHT * 27 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);

		cityAInput.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		cityBInput.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 22 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		distanceInput.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 27 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		distancePost.setBounds(PANEL_WIDTH * 2 / 3, PANEL_HEIGHT * 27 / 48,
				PANEL_WIDTH / 10, PANEL_HEIGHT / 16);
		
		OKLabel.setBounds(PANEL_WIDTH * 30 / 48, PANEL_HEIGHT * 36 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		returnLabel.setBounds(PANEL_WIDTH * 6 / 48, PANEL_HEIGHT * 36 / 48,
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
