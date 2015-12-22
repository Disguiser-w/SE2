package presentation.managerui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import presentation.commonui.DateChooser;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;
import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import businesslogic.receiptbl.getDate;

public class CheckOperatingPanel extends OperationPanel {
	
	private static final long serialVersionUID = 69L;
	
	private BusinessStatementReceiptBLController businessController;
	
	private int tableWidth;
	private int tableHeight;
	
	private JLabel dateRange;
	private JLabel startDateLabel;
	private MyTextField startDateField;
	private JLabel startDateChooseLabel;
	private JLabel endDateLabel;
	private MyTextField endDateField;
	private JLabel endDateChooseLabel;
	private MyLabel confirmDateLabel;
	
	private ArrayList<CollectionReceiptVO> collectionReceiptVOs;
	private ArrayList<PaymentReceiptVO> paymentReceiptVOs;
	
	ArrayList<String[]> infos; 
	
	private MyTable messageTable;

	//private LocationHelper helper;

	public CheckOperatingPanel() {
		
		businessController = new BusinessStatementReceiptBLController();
		
		dateRange = new JLabel("日期范围");
		startDateLabel = new JLabel();
		startDateField = new MyTextField();
		startDateField.setToolTipText("例:2015-12-08");
		startDateChooseLabel = new JLabel("开始日期");
		endDateLabel = new JLabel();
		endDateField = new MyTextField();
		endDateField.setToolTipText("例:2015-12-08");
		endDateChooseLabel = new JLabel("结束日期");
		
		startDateLabel.setLayout(new BorderLayout());
		startDateLabel.add(new DateChooser(startDateField),BorderLayout.CENTER);
		
		endDateLabel.setLayout(new BorderLayout());
		endDateLabel.add(new DateChooser(endDateField),BorderLayout.CENTER);
		
		confirmDateLabel = new MyLabel("确认");
		
		collectionReceiptVOs = businessController.showBSList("20110101", getDate.getdate()).cvos;
		paymentReceiptVOs = businessController.showBSList("20100101", getDate.getdate()).pvos;
		
		//加监听
		confirmDateLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				confirmui();
			}
		});

		//把组件加到Panel上
		setLayout(null);
		
		add(dateRange);
		add(startDateLabel);
		add(startDateField);
		add(startDateChooseLabel);
		add(endDateLabel);
		add(endDateField);
		add(endDateChooseLabel);
		add(confirmDateLabel);

        setBaseInfos();
	}

	
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);
		
		tableWidth = width;
		tableHeight = height;
		
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
		confirmDateLabel.setBounds((int)(width * 19.6109693877551/25),(int)(height * 2.5048923679060664/20),
				(int)(width *  2.1683673469387754 /25),(int)(height *  1.1350293542074363/20));
		messageTable.setLocationAndSize((int)(width * 1.4987244897959184/25),(int)(height * 4.148727984344423/20)+(int)(height *  1.3424657534246576/20),
				(int)(width *  21.0140306122449 /25),(int)(height *  13.424657534246576/20)-(int)(height *  1.3424657534246576/20));
	}
	
	private void setBaseInfos(){
		String[] head = new String[]{"编号","日期","金额","操作人"};
		int[] widths = {150,150,150,150};
		infos = getInfos(collectionReceiptVOs,paymentReceiptVOs);
		messageTable = new MyTable(head, infos, widths, false);
		messageTable.setLocationAndSize((int)(tableWidth * 1.4987244897959184/25),(int)(tableHeight * 4.148727984344423/20)+(int)(tableHeight *  1.3424657534246576/20),
				(int)(tableWidth *  21.0140306122449 /25),(int)(tableHeight *  13.424657534246576/20)-(int)(tableHeight *  1.3424657534246576/20));
		add(messageTable);
	}
	
	private ArrayList<String[]> getInfos(ArrayList<CollectionReceiptVO> cvos,ArrayList<PaymentReceiptVO> pvos){
		
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		for(CollectionReceiptVO v : cvos){
			lineInfo.add(new String[]{v.ID,v.date,v.totalMoney+"",v.userID});
		}
		for(PaymentReceiptVO v : pvos){
			lineInfo.add(new String[]{v.ID,v.date,v.cost+"",v.userID});
		}
		return lineInfo;
	}
	
	public void confirmui(){
		String beginDate = startDateField.getText();
		String endDate = endDateField.getText();
		
		if(beginDate.equals("")||endDate.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示", JOptionPane.CLOSED_OPTION);
		}
		else{
			beginDate = beginDate.substring(0, 4) + beginDate.substring(5,7) + beginDate.substring(8);
			endDate = endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8);
			
		BusinessStatementReceiptVO vo = businessController.showBSList(beginDate, endDate);
		collectionReceiptVOs=vo.cvos;
		paymentReceiptVOs=vo.pvos;
		messageTable.setInfos(getInfos(collectionReceiptVOs, paymentReceiptVOs));
		}
	}


}
