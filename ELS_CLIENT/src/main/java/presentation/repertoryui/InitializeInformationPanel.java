package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import businesslogic.repertorybl.controller.RepertoryController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
<<<<<<< HEAD
=======
import businesslogic.logdiarybl.controller.LogDiaryBLController;
import businesslogic.repertorybl.controller.RepertoryController;
import vo.LogDiaryVO;
>>>>>>> 6ae698f408747916aa3566adf8d97750b76f3b84
import vo.UserVO;

public class InitializeInformationPanel extends OperationPanel {
	
	private static final long serialVersionUID = 147L;

	private RepertoryController repertoryControl;
	private LogDiaryBLController logDiaryControl;

	private LogDiaryVO logDiary;
	private String logDiaryTime;
	
	private UserVO stockMan;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private MyTextLabel maxRowLabel;
	private MyTextLabel maxShelfLabel;
	private MyTextLabel maxBitLabel;
	private MyTextLabel maxRatioLabel;
	
	private MyTextField maxRowField;
	private MyTextField maxShelfField;
	private MyTextField maxBitField;
	private MyTextField maxRatioField;
	
	private MyTextLabel maxRatioTip;
	
	private MyLabel OKLabel;
	
	private MyTextLabel suggestLabel;

	public InitializeInformationPanel(RepertoryController repertoryController, UserVO userVO) {
		
		repertoryControl = repertoryController;
		logDiaryControl = new LogDiaryBLController();
		
		stockMan = userVO;
		
		maxRowLabel = new MyTextLabel("请输入最大排数");
		maxRowField = new MyTextField();

		maxShelfLabel = new MyTextLabel("请输入最大架数");
		maxShelfField = new MyTextField();

		maxBitLabel = new MyTextLabel("请输入最大位数");
		maxBitField = new MyTextField();
		
		maxRatioLabel = new MyTextLabel("请输入警戒比例");
		maxRatioField = new MyTextField();
		
		maxRatioTip = new MyTextLabel("100以内的正整数");
		
		suggestLabel = new MyTextLabel("默认值：最大排数:"+repertoryControl.getMaxRow()+"，最大架数:"+repertoryControl.getMaxShelf()+"，最大位数:"+repertoryControl.getMaxDigit()+"，警戒比例:"+repertoryControl.getMaxRatio());

		OKLabel = new MyLabel("确认");

		
		//加监听
		OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int maxRow;
				int maxShelf;
				int maxDigit;
				int warningRatio;
				
				if(maxRowField.getText().equals(""))
					maxRow = 10;
				else
					maxRow = Integer.parseInt(maxRowField.getText());
				
				if(maxShelfField.getText().equals(""))
					maxShelf = 10;
				else
					maxShelf = Integer.parseInt(maxShelfField.getText());
				
				if(maxBitField.getText().equals(""))
					maxDigit = 10;
				else
					maxDigit = Integer.parseInt(maxBitField.getText());
				
				if(maxRatioField.getText().equals(""))
					warningRatio = 80;
				else
					warningRatio = Integer.parseInt(maxRatioField.getText());
				
				int returnNum = repertoryControl.inventoryInitialization(maxRow, maxShelf, maxDigit, warningRatio);
				maxRowField.setText("");
				maxShelfField.setText("");
				maxBitField.setText("");
				maxRatioField.setText("");
				maxRowField.setEditable(false);
				maxShelfField.setEditable(false);
				maxBitField.setEditable(false);
				maxRatioField.setEditable(false);
				if(returnNum==0){
					successInitialization();
				}
				else{
					failedInitialization();
				}
			}
		});
		
		
		//把组件加到Panel上
		setLayout(null);
		
		add(maxRowLabel);
		add(maxRowField);
		add(maxShelfLabel);
		add(maxShelfField);
		add(maxBitLabel);
		add(maxBitField);
		add(maxRatioLabel);
		add(maxRatioField);
		add(maxRatioTip);
		add(OKLabel);
		add(suggestLabel);
		
		setVisible(true);
		
        //位置设置
	    setCmpLocation();
	}

	
	//设置位置
	public void setCmpLocation(){
		
		maxRowLabel.setBounds((int) (PANEL_WIDTH * 4.848911651728553 / 25), (int) (PANEL_HEIGHT * 3.544642857142857 / 20),
				(int) (PANEL_WIDTH * 4.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		maxShelfLabel.setBounds((int) (PANEL_WIDTH * 4.848911651728553 / 25), (int) (PANEL_HEIGHT * 5.642857142857143 / 20),
				(int) (PANEL_WIDTH * 4.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		maxBitLabel.setBounds((int) (PANEL_WIDTH * 4.848911651728553 / 25), (int) (PANEL_HEIGHT * 7.651785714285714 / 20),
				(int) (PANEL_WIDTH * 4.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		maxRatioLabel.setBounds((int) (PANEL_WIDTH * 4.848911651728553 / 25), (int) (PANEL_HEIGHT * 9.705357142857142 / 20),
				(int) (PANEL_WIDTH * 4.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		
		maxRowField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 3.544642857142857 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		maxShelfField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 5.642857142857143 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		maxBitField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 7.651785714285714 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		maxRatioField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 9.705357142857142 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		
		maxRatioTip.setBounds((int) (PANEL_WIDTH * 15.909603072983353 / 25), (int) (PANEL_HEIGHT * 9.705357142857142 / 20),
				(int) (PANEL_WIDTH * 6.751536491677337 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		
		OKLabel.setBounds((int) (PANEL_WIDTH * 11.107554417413573 / 25), (int) (PANEL_HEIGHT * 13.725 / 20),
				(int) (PANEL_WIDTH * 2.801536491677337 / 25), (int) (PANEL_HEIGHT * 1.5303571428571428 / 20));
		
		suggestLabel.setBounds((int) (PANEL_WIDTH * 5.607554417413573 / 25), (int) (PANEL_HEIGHT * 15.725 / 20),
				(int) (PANEL_WIDTH * 15.272727272727273 / 25), (int) (PANEL_HEIGHT * 1.8303571428571428 / 20));
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}
	
	
	//出现错误时给用户的提示信息
	public void warnning(String message) {
		JOptionPane.showMessageDialog(null, message, "信息错误", JOptionPane.ERROR_MESSAGE);
	}
	
	public void successInitialization(){
		JOptionPane.showMessageDialog(null, "初始化成功(●'◡'●)", "库存信息初始化成功", JOptionPane.INFORMATION_MESSAGE);
		suggestLabel.setText("Row:"+repertoryControl.getMaxRow()+"  Shelf:"+repertoryControl.getMaxShelf()+"  Digit:"+repertoryControl.getMaxDigit()+"  WarningRatio:"+repertoryControl.getMaxRatio());

		logDiaryTime = getTimeNow();
		logDiary = new LogDiaryVO(logDiaryTime, stockMan, "初始化了库存信息");
		logDiaryControl.addLogDiary(logDiary, logDiaryTime);
	}
	
	public void failedInitialization(){
		JOptionPane.showMessageDialog(null, "初始化失败(T_T)", "库存信息初始化失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//获取当前时间
	public static String getTimeNow(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeNow = dateFormat.format(now); 
		return timeNow;
	}
	
}

