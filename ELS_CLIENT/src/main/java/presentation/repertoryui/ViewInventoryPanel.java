package presentation.repertoryui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import presentation.commonui.DateChooser;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;

import vo.InventoryCheckVO;
import vo.UserVO;
import businesslogic.repertorybl.RepertoryBL;

public class ViewInventoryPanel extends OperationPanel {
	
	private static final long serialVersionUID = 99L;
	
	private RepertoryBL repertoryBL;
	
	private JLabel dateRange;
	
	private JLabel startDateLabel;
	private MyTextField startDateField;
	private JLabel startDateChooseLabel;
	private JLabel endDateLabel;
	private MyTextField endDateField;
	private JLabel endDateChooseLabel;
	
	private MyLabel OKLabel;
	
	private JLabel enterNumTotalLabel;
	private JLabel enterFeeTotalLabel;
	private JLabel leaveNumTotalLabel;
	private JLabel leaveFeeTotalLabel;
	private JLabel planeBlockNumLabel;
	private JLabel trainBlockNumLabel;
	private JLabel truckBlockNumLabel;
	private JLabel defaultBlockNumLabel;
	
	private MyTextField enterNumTotalField;
	private MyTextField enterFeeTotalField;
	private MyTextField leaveNumTotalField;
	private MyTextField leaveFeeTotalField;
	private MyTextField planeBlockNumField;
	private MyTextField trainBlockNumField;
	private MyTextField truckBlockNumField;
	private MyTextField defaultBlockNumField;
	
	private InventoryCheckVO inventoryCheckVO;
	
	public ViewInventoryPanel(UserVO userVO) {
		
		repertoryBL = new RepertoryBL(userVO.userID);
		
		dateRange = new JLabel("日期范围");
		
		startDateLabel = new JLabel();
		startDateField = new MyTextField();
		startDateField.setToolTipText("例:2015-10-01");
		startDateChooseLabel = new JLabel("开始日期");
		endDateLabel = new JLabel();
		endDateField = new MyTextField();
		endDateField.setToolTipText("例:2015-10-01");
		endDateChooseLabel = new JLabel("结束日期");

		startDateLabel.setLayout(new BorderLayout());
		startDateLabel.add(new DateChooser(startDateField),BorderLayout.CENTER);
		endDateLabel.setLayout(new BorderLayout());
		endDateLabel.add(new DateChooser(endDateField),BorderLayout.CENTER);
		
		OKLabel = new MyLabel("确认");
		
		enterNumTotalLabel = new JLabel("入库数量总计");
		enterFeeTotalLabel = new JLabel("入库金额总计");
		leaveNumTotalLabel = new JLabel("出库数量总计");
		leaveFeeTotalLabel = new JLabel("出库金额总计");
		planeBlockNumLabel = new JLabel("飞机区数量");
		trainBlockNumLabel = new JLabel("火车区数量");
		truckBlockNumLabel = new JLabel("汽车区数量");
		defaultBlockNumLabel = new JLabel("机动区数量");
		
		enterNumTotalField = new MyTextField();
		enterFeeTotalField = new MyTextField();
		leaveNumTotalField = new MyTextField();
		leaveFeeTotalField = new MyTextField();
		planeBlockNumField = new MyTextField();
		trainBlockNumField = new MyTextField();
		truckBlockNumField = new MyTextField();
		defaultBlockNumField = new MyTextField();
		
		OKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkUI();
			}
		});
		
		
		setLayout(null);
		
		add(dateRange);
		add(startDateLabel);
		add(startDateField);
		add(startDateChooseLabel);
		add(endDateLabel);
		add(endDateField);
		add(endDateChooseLabel);
		add(OKLabel);
		add(enterNumTotalLabel);
		add(enterFeeTotalLabel);
		add(leaveNumTotalLabel);
		add(leaveFeeTotalLabel);
		add(planeBlockNumLabel);
		add(trainBlockNumLabel);
		add(truckBlockNumLabel);
		add(defaultBlockNumLabel);
		
		add(enterNumTotalField);
		add(enterFeeTotalField);
		add(leaveNumTotalField);
		add(leaveFeeTotalField);
		add(planeBlockNumField);
		add(trainBlockNumField);
		add(truckBlockNumField);
		add(defaultBlockNumField);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		dateRange.setBounds((int)(width * 1.4987244897959184/25),(int)(height * 2.544031311154599/20),
				(int)(width *  3.1568877551020407 /25),(int)(height *  1.2524461839530332/20));
		startDateLabel.setBounds((int)(width * 8.896683673469388/25),(int)(height * 2.5048923679060664/20),
				(int)(width *  0.9247448979591837 /25),(int)(height *  1.1741682974559686/20));
        endDateLabel.setBounds((int)(width * 14.572704081632653/25),(int)(height * 2.5048923679060664/20),
        		(int)(width *  1.052295918367347 /25),(int)(height *  1.2524461839530332/20));
        startDateField.setBounds((int)(width * 5.0063775510204085/25),(int)(height * 2.544031311154599/20),
				(int)(width *  3.1568877551020407 /25),(int)(height *  1.1741682974559686/20));
		endDateField.setBounds((int)(width * 10.809948979591837/25),(int)(height * 2.5048923679060664/20),
				(int)(width *  3.2206632653061225 /25),(int)(height *  1.2915851272015655/20));
		OKLabel.setBounds((int)(width * 19.6109693877551/25),(int)(height * 2.5048923679060664/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1.1350293542074363/20));
		enterNumTotalLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 6/20),
				(int)(width *  3.5683673469387754 /25),(int)(height *  1/20));
		enterFeeTotalLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 7.5/20),
				(int)(width *  3.1683673469387754 /25),(int)(height *  1/20));
		leaveNumTotalLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 9/20),
				(int)(width *  3.1683673469387754 /25),(int)(height *  1/20));
		leaveFeeTotalLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 10.5/20),
				(int)(width *  3.1683673469387754 /25),(int)(height *  1/20));
		planeBlockNumLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 12/20),
				(int)(width *  3.1683673469387754 /25),(int)(height *  1/20));
		trainBlockNumLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 13.5/20),
				(int)(width *  3.1683673469387754 /25),(int)(height *  1/20));
		truckBlockNumLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 15/20),
				(int)(width *  3.1683673469387754 /25),(int)(height *  1/20));
		defaultBlockNumLabel.setBounds((int)(width * 5.6109693877551/25),(int)(height * 16.5/20),
				(int)(width *  3.1683673469387754 /25),(int)(height *  1/20));
		enterNumTotalField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 6/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
		enterFeeTotalField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 7.5/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
		leaveNumTotalField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 9/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
		leaveFeeTotalField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 10.5/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
		planeBlockNumField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 12/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
		trainBlockNumField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 13.5/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
		truckBlockNumField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 15/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
		defaultBlockNumField.setBounds((int)(width * 12.6109693877551/25),(int)(height * 16.5/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1/20));
	}
	
	
	public void checkUI(){
		
		String startDateStr = startDateField.getText();
		String endDateStr = endDateField.getText();
		
		inventoryCheckVO = repertoryBL.inventoryCheck(startDateStr, endDateStr);

		if(startDateStr.equals("") || endDateStr.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示", JOptionPane.CLOSED_OPTION);
		}
		else{
			inventoryCheckVO = repertoryBL.inventoryCheck(startDateStr, endDateStr);
			enterNumTotalField.setText(inventoryCheckVO.enterTotal+"");
			enterFeeTotalField.setText(inventoryCheckVO.enterFeeTotal+"");
			leaveNumTotalField.setText(inventoryCheckVO.leaveTotal+"");
			leaveFeeTotalField.setText(inventoryCheckVO.leaveFeeTotal+"");
			planeBlockNumField.setText(inventoryCheckVO.stockNum[0]+"");
			trainBlockNumField.setText(inventoryCheckVO.stockNum[1]+"");
			truckBlockNumField.setText(inventoryCheckVO.stockNum[2]+"");
			defaultBlockNumField.setText(inventoryCheckVO.stockNum[3]+"");
		}
	}
	
}
