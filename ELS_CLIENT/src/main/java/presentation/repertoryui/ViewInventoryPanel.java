package presentation.repertoryui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.repertorybl.controller.RepertoryController;
import presentation.commonui.DateChooser;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import vo.InventoryCheckVO;
import vo.UserVO;

public class ViewInventoryPanel extends OperationPanel {

	private static final long serialVersionUID = 99L;

	private RepertoryController repertoryControl;

	private JLabel dateRange;

	private MyLabel startDateLabel;
	private MyTextLabel startDateChooseLabel;
	private MyLabel endDateLabel;
	private MyTextLabel endDateChooseLabel;

	private MyTextField startDateField;
	private MyTextField endDateField;

	private MyLabel OKLabel;

	private MyTextLabel enterNumTotalLabel;
	private MyTextLabel enterFeeTotalLabel;
	private MyTextLabel leaveNumTotalLabel;
	private MyTextLabel leaveFeeTotalLabel;
	private MyTextLabel planeBlockNumLabel;
	private MyTextLabel trainBlockNumLabel;
	private MyTextLabel truckBlockNumLabel;
	private MyTextLabel defaultBlockNumLabel;

	private MyTextField enterNumTotalField;
	private MyTextField enterFeeTotalField;
	private MyTextField leaveNumTotalField;
	private MyTextField leaveFeeTotalField;
	private MyTextField planeBlockNumField;
	private MyTextField trainBlockNumField;
	private MyTextField truckBlockNumField;
	private MyTextField defaultBlockNumField;

	private InventoryCheckVO inventoryCheckVO;

	public ViewInventoryPanel(RepertoryController repertoryController, UserVO userVO) {

		repertoryControl = repertoryController;

		dateRange = new MyTextLabel("日期范围");

		startDateLabel = new MyLabel();
		startDateField = new MyTextField();
		startDateField.setToolTipText("例:2015-10-01");
		startDateChooseLabel = new MyTextLabel("开始日期");
		endDateLabel = new MyLabel();
		endDateField = new MyTextField();
		endDateField.setToolTipText("例:2015-10-01");
		endDateChooseLabel = new MyTextLabel("结束日期");

		startDateLabel.setLayout(new BorderLayout());
		new DateChooser(startDateField, startDateLabel);

		endDateLabel.setLayout(new BorderLayout());
		new DateChooser(endDateField, endDateLabel);

		OKLabel = new MyLabel("确认");

		enterNumTotalLabel = new MyTextLabel("入库数量总计");
		enterFeeTotalLabel = new MyTextLabel("入库金额总计");
		leaveNumTotalLabel = new MyTextLabel("出库数量总计");
		leaveFeeTotalLabel = new MyTextLabel("出库金额总计");
		planeBlockNumLabel = new MyTextLabel("飞机区数量");
		trainBlockNumLabel = new MyTextLabel("火车区数量");
		truckBlockNumLabel = new MyTextLabel("汽车区数量");
		defaultBlockNumLabel = new MyTextLabel("机动区数量");

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

		dateRange.setBounds((int) (width * 1.4987244897959184 / 25), (int) (height * 2.544031311154599 / 20),
				(int) (width * 3.1568877551020407 / 25), (int) (height * 1.2524461839530332 / 20));
		startDateLabel.setBounds((int) (width * 9.296683673469388 / 25), (int) (height * 2.5048923679060664 / 20),
				(int) (width * 2.4047448979591837 / 25), (int) (height * 1.1741682974559686 / 20));
		endDateLabel.setBounds((int) (width * 17.072704081632653 / 25), (int) (height * 2.5048923679060664 / 20),
				(int) (width * 2.4047448979591837 / 25), (int) (height * 1.1741682974559686 / 20));
		startDateField.setBounds((int) (width * 5.0063775510204085 / 25), (int) (height * 2.544031311154599 / 20),
				(int) (width * 4.1568877551020407 / 25), (int) (height * 1.1741682974559686 / 20));
		endDateField.setBounds((int) (width * 12.809948979591837 / 25), (int) (height * 2.5048923679060664 / 20),
				(int) (width * 4.1568877551020407 / 25), (int) (height * 1.1741682974559686 / 20));
		OKLabel.setBounds((int) (width * 19.9109693877551 / 25), (int) (height * 2.5048923679060664 / 20),
				(int) (width * 2.1683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		enterNumTotalLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 6 / 20),
				(int) (width * 3.5683673469387754 / 25), (int) (height * 1 / 20));
		enterFeeTotalLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 7.5 / 20),
				(int) (width * 3.1683673469387754 / 25), (int) (height * 1 / 20));
		leaveNumTotalLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 9 / 20),
				(int) (width * 3.1683673469387754 / 25), (int) (height * 1 / 20));
		leaveFeeTotalLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 10.5 / 20),
				(int) (width * 3.1683673469387754 / 25), (int) (height * 1 / 20));
		planeBlockNumLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 12 / 20),
				(int) (width * 3.1683673469387754 / 25), (int) (height * 1 / 20));
		trainBlockNumLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 13.5 / 20),
				(int) (width * 3.1683673469387754 / 25), (int) (height * 1 / 20));
		truckBlockNumLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 15 / 20),
				(int) (width * 3.1683673469387754 / 25), (int) (height * 1 / 20));
		defaultBlockNumLabel.setBounds((int) (width * 7.6109693877551 / 25), (int) (height * 16.5 / 20),
				(int) (width * 3.1683673469387754 / 25), (int) (height * 1 / 20));
		enterNumTotalField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 6 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
		enterFeeTotalField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 7.5 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
		leaveNumTotalField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 9 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
		leaveFeeTotalField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 10.5 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
		planeBlockNumField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 12 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
		trainBlockNumField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 13.5 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
		truckBlockNumField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 15 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
		defaultBlockNumField.setBounds((int) (width * 12.6109693877551 / 25), (int) (height * 16.5 / 20),
				(int) (width * 4.1683673469387754 / 25), (int) (height * 1 / 20));
	}

	public void checkUI() {

		String startDateStr = startDateField.getText();
		String endDateStr = endDateField.getText();

		inventoryCheckVO = repertoryControl.inventoryCheck(startDateStr, endDateStr);

		if (startDateStr.equals("") || endDateStr.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示", JOptionPane.CLOSED_OPTION);
		} else {
			inventoryCheckVO = repertoryControl.inventoryCheck(startDateStr, endDateStr);
			enterNumTotalField.setText(inventoryCheckVO.enterTotal + "");
			enterFeeTotalField.setText(inventoryCheckVO.enterFeeTotal + "");
			leaveNumTotalField.setText(inventoryCheckVO.leaveTotal + "");
			leaveFeeTotalField.setText(inventoryCheckVO.leaveFeeTotal + "");
			planeBlockNumField.setText(inventoryCheckVO.stockNum[0] + "");
			trainBlockNumField.setText(inventoryCheckVO.stockNum[1] + "");
			truckBlockNumField.setText(inventoryCheckVO.stockNum[2] + "");
			defaultBlockNumField.setText(inventoryCheckVO.stockNum[3] + "");
		}
	}

}
