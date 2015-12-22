package presentation.repertoryui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;

import businesslogic.repertorybl.RepertoryBL;
import vo.UserVO;

public class InitializeInformationPanel extends OperationPanel {
	
	private static final long serialVersionUID = 147L;

	private RepertoryBL repertoryBL;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private JLabel maxRowLabel;
	private JLabel maxShelfLabel;
	private JLabel maxBitLabel;
	private JLabel maxRatioLabel;
	
	private MyTextField maxRowField;
	private MyTextField maxShelfField;
	private MyTextField maxBitField;
	private MyTextField maxRatioField;
	
	private JLabel maxRatioTip;
	
	private MyLabel OKLabel;
	
	private JLabel suggestLabel;

	public InitializeInformationPanel(UserVO userVO) {
		
		repertoryBL = new RepertoryBL(userVO.userID);
		
		maxRowLabel = new JLabel("请输入最大排数");
		maxRowField = new MyTextField();

		maxShelfLabel = new JLabel("请输入最大架数");
		maxShelfField = new MyTextField();

		maxBitLabel = new JLabel("请输入最大位数");
		maxBitField = new MyTextField();
		
		maxRatioLabel = new JLabel("请输入警戒比例");
		maxRatioField = new MyTextField();
		
		maxRatioTip = new JLabel("100以内的正整数");
		
		suggestLabel = new JLabel("Row:"+repertoryBL.getMaxRow()+"  Shelf:"+repertoryBL.getMaxShelf()+"  Digit:"+repertoryBL.getMaxDigit()+"  WarningRatio:"+repertoryBL.getMaxRatio());

		OKLabel = new MyLabel("确认");

		
		//加监听
		OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int maxRow = Integer.parseInt(maxRowField.getText());
				int maxShelf = Integer.parseInt(maxShelfField.getText());
				int maxDigit = Integer.parseInt(maxBitField.getText());
				int warningRatio = Integer.parseInt(maxRatioField.getText());
				int returnNum = repertoryBL.inventoryInitialization(maxRow, maxShelf, maxDigit, warningRatio);
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
		
		maxRowLabel.setBounds((int) (PANEL_WIDTH * 2.848911651728553 / 25), (int) (PANEL_HEIGHT * 2.544642857142857 / 20),
				(int) (PANEL_WIDTH * 5.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		maxShelfLabel.setBounds((int) (PANEL_WIDTH * 2.848911651728553 / 25), (int) (PANEL_HEIGHT * 4.642857142857143 / 20),
				(int) (PANEL_WIDTH * 5.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		maxBitLabel.setBounds((int) (PANEL_WIDTH * 2.848911651728553 / 25), (int) (PANEL_HEIGHT * 6.651785714285714 / 20),
				(int) (PANEL_WIDTH * 5.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		maxRatioLabel.setBounds((int) (PANEL_WIDTH * 2.848911651728553 / 25), (int) (PANEL_HEIGHT * 8.705357142857142 / 20),
				(int) (PANEL_WIDTH * 5.60179257362356 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		
		maxRowField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 2.544642857142857 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		maxShelfField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 4.642857142857143 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		maxBitField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 6.651785714285714 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		maxRatioField.setBounds((int) (PANEL_WIDTH * 9.891165172855313 / 25), (int) (PANEL_HEIGHT * 8.705357142857142 / 20),
				(int) (PANEL_WIDTH * 5.857874519846351 / 25), (int) (PANEL_HEIGHT * 1.5625 / 20));
		
		maxRatioTip.setBounds((int) (PANEL_WIDTH * 17.509603072983353 / 25), (int) (PANEL_HEIGHT * 8.705357142857142 / 20),
				(int) (PANEL_WIDTH * 4.801536491677337 / 25), (int) (PANEL_HEIGHT * 1.5178571428571428 / 20));
		
		OKLabel.setBounds((int) (PANEL_WIDTH * 11.107554417413573 / 25), (int) (PANEL_HEIGHT * 13.125 / 20),
				(int) (PANEL_WIDTH * 2.272727272727273 / 25), (int) (PANEL_HEIGHT * 1.8303571428571428 / 20));
		
		suggestLabel.setBounds((int) (PANEL_WIDTH * 9.107554417413573 / 25), (int) (PANEL_HEIGHT * 15.125 / 20),
				(int) (PANEL_WIDTH * 9.272727272727273 / 25), (int) (PANEL_HEIGHT * 1.8303571428571428 / 20));
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
		suggestLabel.setText("Row:"+repertoryBL.getMaxRow()+"  Shelf:"+repertoryBL.getMaxShelf()+"  Digit:"+repertoryBL.getMaxDigit()+"  WarningRatio:"+repertoryBL.getMaxRatio());
	}
	
	public void failedInitialization(){
		JOptionPane.showMessageDialog(null, "初始化失败(T_T)", "库存信息初始化失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
}

