package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import common.FileExporter;

import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import businesslogic.receiptbl.getDate;
import presentation.commonui.DateChooser;
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
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private MyLabel dateOKLabel;
	private MyLabel printLabel;
//	private JLabel sendLabel;

	private JLabel function;
	private JLabel dateRange;

	private MyTextField startDate_Input;
	private MyTextField endDate_Input;

	private MyTable BSLTable;
	
	private ArrayList<CollectionReceiptVO> collectionReceiptVOs;
	private ArrayList<PaymentReceiptVO> paymentReceiptVOs;

	public BusinessStatementReceiptBLController controller;
	public UserVO userVO;

	public BusinessStateReceiptPanel(BusinessStatementReceiptBLController controller,UserVO userVO) {
		this.controller=controller;
		this.userVO=userVO;
		
		startDateLabel = new JLabel("开始日期");
		endDateLabel =new JLabel("结束日期");
		dateOKLabel = new MyLabel("确认");
		printLabel = new MyLabel("打印");
//		sendLabel = new JLabel("发送");

		function = new JLabel("经营情况表");
		dateRange = new JLabel("日期范围");

		startDate_Input = new MyTextField();
		endDate_Input = new MyTextField();

		//提示框
		startDate_Input.setToolTipText("例:2015-12-08");

		startDateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				startui();
			}
		});

		endDateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				endui();
			}
		});

		dateOKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				dateOK();
			}
		});

		printLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				printui();
			}
		});


		collectionReceiptVOs = controller.showBSList("20110101", getDate.getdate()).cvos;
		paymentReceiptVOs = controller.showBSList("20100101", getDate.getdate()).pvos;
		setBaseInfo();
		setLayout(null);

		add(startDateLabel);
		add(endDateLabel);
		add(dateOKLabel);
		add(printLabel);
		add(function);
		add(dateRange);
		add(startDate_Input);
		add(endDate_Input);
		
		startDateLabel.setLayout(new BorderLayout());
		startDateLabel.add(new DateChooser(startDate_Input),BorderLayout.CENTER);
		
		endDateLabel.setLayout(new BorderLayout());
		endDateLabel.add(new DateChooser(endDate_Input),BorderLayout.CENTER);

	}



	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		
        startDateLabel.setBounds((int)(width * 8.896683673469388/25),(int)(height * 2.5048923679060664/20),(int)(width *  0.9247448979591837 /25),(int)(height *  1.1741682974559686/20));
        endDateLabel.setBounds((int)(width * 14.572704081632653/25),(int)(height * 2.5048923679060664/20),(int)(width *  1.052295918367347 /25),(int)(height *  1.2524461839530332/20));
        dateOKLabel.setBounds((int)(width * 21.0109693877551/25),(int)(height * 2.5048923679060664/20),(int)(width *  2.7683673469387754 /25),(int)(height *  1.1350293542074363/20));
		printLabel.setBounds((int)(width * 21.01019387755102/25),(int)(height * 0.43052837573385516/20),(int)(width *  2.7682551020408165 /25),(int)(height *   1.1350293542074363/20));
//		sendLabel.setBounds((int)(width * 22.193877551020407/25),(int)(height * 0.3913894324853229/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.5264187866927592/20));
		function.setBounds((int)(width * 0.5420918367346939/25),(int)(height * 0.43052837573385516/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.6046966731898238/20));
		dateRange.setBounds((int)(width * 1.4987244897959184/25),(int)(height * 2.544031311154599/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.2524461839530332/20));
		startDate_Input.setBounds((int)(width * 5.0063775510204085/25),(int)(height * 2.544031311154599/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.1741682974559686/20));
		endDate_Input.setBounds((int)(width * 10.809948979591837/25),(int)(height * 2.5048923679060664/20),(int)(width *  3.2206632653061225 /25),(int)(height *  1.2915851272015655/20));
		BSLTable.setLocationAndSize((int)(width * 1.0987244897959184/25),(int)(height * 4.048727984344423/20),(int)(width *  23.007397959183675 /25),(int)(height *  13.424657534246576/20));		
	}
	private void setBaseInfo(){
		String[] head = new String[]{"编号","日期","金额","操作人"};
		int[] widths = new int[]{160,152,152,160};
		
		BSLTable = new MyTable(head, getInfos(collectionReceiptVOs,paymentReceiptVOs), widths, false);
		add(BSLTable);
	}
	
	private ArrayList<String[]> getInfos(ArrayList<CollectionReceiptVO> cvos,ArrayList<PaymentReceiptVO> pvos){
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if(cvos!=null||pvos!=null){
		for(CollectionReceiptVO v : cvos){
			lineInfo.add(new String[]{v.ID,v.date,v.totalMoney+"",v.userID});
		}
		for(PaymentReceiptVO v : pvos){
			lineInfo.add(new String[]{v.ID,v.date,v.cost+"",v.userID});
		}
		return lineInfo;
		}
		else{
			return new ArrayList<String[]>();
		}
	}
	

	public void startui() {
		
	}

	public void endui() {

	}

	public void dateOK() {
		String beginTime=startDate_Input.getText();
		String endTime=endDate_Input.getText();
		if(beginTime.equals("")||endTime.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else if(beginTime.compareTo(endTime)>0){
			JOptionPane.showMessageDialog(null, "起始日期竟然大于结束日期，你在逗我咩？", "提示", 
					JOptionPane.WARNING_MESSAGE);
		}
		else{
//			LogDiaryBL bl = new LogDiaryBL();
//			LogDiaryVO logvo = new LogDiaryVO(getDate.getdate(), userVO, "查看了经营情况表");
//			bl.addLogDiary(logvo, getDate.getdate());
			beginTime=beginTime.substring(0, 4)+beginTime.substring(5,7)+beginTime.substring(8);
			endTime=endTime.substring(0, 4)+endTime.substring(5, 7)+endTime.substring(8);
		BusinessStatementReceiptVO vo=controller.showBSList(beginTime, endTime);
		collectionReceiptVOs=vo.cvos;
		paymentReceiptVOs=vo.pvos;
		BSLTable.setInfos(getInfos(collectionReceiptVOs, paymentReceiptVOs));
		}
	}

	public void printui() {
		String beginTime=startDate_Input.getText();
		String endTime=endDate_Input.getText();
		if(beginTime!=""&&endTime!=""){
		beginTime=beginTime.substring(0, 4)+beginTime.substring(5,7)+beginTime.substring(8);
		endTime=endTime.substring(0, 4)+endTime.substring(5, 7)+endTime.substring(8);
	    BusinessStatementReceiptVO vo=controller.showBSList(beginTime, endTime);
	    collectionReceiptVOs=vo.cvos;
	    paymentReceiptVOs=vo.pvos;
		}
		else{
			collectionReceiptVOs = controller.showBSList("20110101", getDate.getdate()).cvos;
			paymentReceiptVOs = controller.showBSList("20100101", getDate.getdate()).pvos;
			}
	    String[][] BSRExcel = new String[collectionReceiptVOs.size()+paymentReceiptVOs.size()][4];
	    int temp = 0;
	    if(collectionReceiptVOs!=null){
	    for(CollectionReceiptVO v1 : collectionReceiptVOs){
			BSRExcel[temp][0] = v1.ID;
			BSRExcel[temp][1] = v1.date;
			BSRExcel[temp][2] = v1.totalMoney+"";
			BSRExcel[temp][3] = v1.userID;
			temp++;
	    }
	    }
	    if(paymentReceiptVOs!=null){
	    	for(PaymentReceiptVO v2 : paymentReceiptVOs){
	    		BSRExcel[temp][0] = v2.ID;
				BSRExcel[temp][1] = v2.date;
				BSRExcel[temp][2] = v2.cost+"";
				BSRExcel[temp][3] = v2.userID;
				temp++;
	    	}
	    }
		String[] head = new String[]{"单据编号","日期","金额","操作人"};
		try {
			FileExporter.exportExcel("businessstatement.xls", head, BSRExcel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("导出excel失败了");
		}
	
	}

	public void sendui() {
		

	}

}