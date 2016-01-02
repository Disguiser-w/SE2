package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import businesslogic.receiptbl.GetDate;
import common.FileExporter;
import presentation.commonui.DateChooser;
import presentation.commonui.DateChooserPanel;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;
import vo.UserVO;

public class BusinessStateReceiptPanel extends OperationPanel {

	private static final long serialVersionUID = 1L;
	private MyLabel startDateLabel;
	private MyLabel endDateLabel;
	private MyLabel dateOKLabel;
	private MyLabel printLabel;

	private MyTextField startDate_Input;
	private MyTextField endDate_Input;

	private MyTable BSLTable;

	private ArrayList<CollectionReceiptVO> collectionReceiptVOs;
	private ArrayList<PaymentReceiptVO> paymentReceiptVOs;

	public BusinessStatementReceiptBLController controller;
	public UserVO userVO;

	public BusinessStateReceiptPanel(BusinessStatementReceiptBLController controller, UserVO userVO) {
		this.controller = controller;
		this.userVO = userVO;

		startDateLabel = new MyLabel("开始日期");
		endDateLabel = new MyLabel("结束日期");
		dateOKLabel = new MyLabel("确认");
		printLabel = new MyLabel("导出");


		startDate_Input = new MyTextField();
		endDate_Input = new MyTextField();

		// 提示框
		startDate_Input.setToolTipText("例:2015-12-08");

		startDateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startui();
			}
		});

		endDateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				endui();
			}
		});

		dateOKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dateOK();
			}
		});

		printLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				printui();
			}
		});

		collectionReceiptVOs = controller.showBSList("2011-01-01", GetDate.getdate()).cvos;
		paymentReceiptVOs = controller.showBSList("2010-01-01", GetDate.getdate()).pvos;
		setBaseInfo();
		setLayout(null);

		add(startDateLabel);
		add(endDateLabel);
		add(dateOKLabel);
		add(printLabel);
		add(startDate_Input);
		add(endDate_Input);

		startDateLabel.setLayout(new BorderLayout());
		new DateChooser(startDate_Input, startDateLabel);

		endDateLabel.setLayout(new BorderLayout());
		new DateChooser(endDate_Input, endDateLabel);

	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		startDateLabel.setBounds((int) (width * 4.896683673469388 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.6247448979591837 / 25), (int) (height * 1.1350293542074363 / 20));
		endDateLabel.setBounds((int) (width * 11.472704081632653 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.6247448979591837 / 25), (int) (height * 1.1350293542074363 / 20));
		dateOKLabel.setBounds((int) (width * 17.0109693877551 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		printLabel.setBounds((int) (width * 21.01019387755102 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7682551020408165 / 25), (int) (height * 1.1350293542074363 / 20));
		startDate_Input.setBounds((int) (width * 1.2063775510204085 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 3.5568877551020407 / 25), (int) (height * 1.1350293542074363 / 20));
		endDate_Input.setBounds((int) (width * 7.809948979591837 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 3.5566632653061225 / 25), (int) (height * 1.1350293542074363 / 20));
		BSLTable.setLocationAndSize((int) (width * 1.1002551020408165 / 25), (int) (height * 2.505479452054795 / 20),
				(int) (width * 23.007397959183675 / 25), (int) (height * 15.921154598825832 / 20));
	}

	private void setBaseInfo() {
		String[] head = new String[] { "编号", "日期", "金额", "操作人" };
		int[] widths = new int[] { 160, 152, 152, 160 };

		BSLTable = new MyTable(head, getInfos(collectionReceiptVOs, paymentReceiptVOs), widths, false);
		add(BSLTable);
	}

	private ArrayList<String[]> getInfos(ArrayList<CollectionReceiptVO> cvos, ArrayList<PaymentReceiptVO> pvos) {
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if (cvos != null || pvos != null) {
			if (cvos != null) {
				for (CollectionReceiptVO v : cvos) {
					lineInfo.add(new String[] { v.ID, v.date, v.totalMoney + "", v.userID });
				}
			}
			if (pvos != null) {
				for (PaymentReceiptVO v : pvos) {
					lineInfo.add(new String[] { v.ID, v.date, v.cost + "", v.userID });
				}
				return lineInfo;
			}
			return new ArrayList<String[]>();
		} else {
			return new ArrayList<String[]>();
		}
	}

	public void startui() {

	}

	public void endui() {

	}

	public void dateOK() {
		String beginTime = startDate_Input.getText();
		String endTime = endDate_Input.getText();
		if (beginTime.equals("") || endTime.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示", JOptionPane.CLOSED_OPTION);
		} else if (beginTime.compareTo(endTime) > 0) {
			JOptionPane.showMessageDialog(null, "起始日期竟然大于结束日期，你在逗我咩？", "提示", JOptionPane.WARNING_MESSAGE);
		} else {

			BusinessStatementReceiptVO vo = controller.showBSList(beginTime, endTime);
			collectionReceiptVOs = vo.cvos;
			paymentReceiptVOs = vo.pvos;
			BSLTable.setInfos(getInfos(collectionReceiptVOs, paymentReceiptVOs));
		}
	}

	public void printui() {
		String beginTime = startDate_Input.getText();
		String endTime = endDate_Input.getText();
		if (beginTime != "" && endTime != "") {
			BusinessStatementReceiptVO vo = controller.showBSList(beginTime, endTime);
			collectionReceiptVOs = vo.cvos;
			paymentReceiptVOs = vo.pvos;
		} else {
			collectionReceiptVOs = controller.showBSList("2011-01-01", GetDate.getdate()).cvos;
			paymentReceiptVOs = controller.showBSList("2010-01-01", GetDate.getdate()).pvos;
		}
		
		if(collectionReceiptVOs.size() == 0 && paymentReceiptVOs.size() == 0){
			 JOptionPane.showMessageDialog(null, "表格中无内容！", "提示",
					 JOptionPane.WARNING_MESSAGE);
		}
		else{
		String[][] BSRExcel = new String[collectionReceiptVOs.size() + paymentReceiptVOs.size()][4];
		int temp = 0;
		if (collectionReceiptVOs != null) {
			for (CollectionReceiptVO v1 : collectionReceiptVOs) {
				BSRExcel[temp][0] = v1.ID;
				BSRExcel[temp][1] = v1.date;
				BSRExcel[temp][2] = v1.totalMoney + "";
				BSRExcel[temp][3] = v1.userID;
				temp++;
			}
		}
		
		if (paymentReceiptVOs != null) {
			for (PaymentReceiptVO v2 : paymentReceiptVOs) {
				BSRExcel[temp][0] = v2.ID;
				BSRExcel[temp][1] = v2.date;
				BSRExcel[temp][2] = v2.cost + "";
				BSRExcel[temp][3] = v2.userID;
				temp++;
			}
		}
		String[] head = new String[] { "单据编号", "日期", "金额", "操作人" };
		try {
			FileExporter.exportExcel("businessstatement.xls", head, BSRExcel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		}

	}


}