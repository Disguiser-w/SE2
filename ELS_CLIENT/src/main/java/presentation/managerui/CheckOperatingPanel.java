package presentation.managerui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import presentation.commonui.DateChooser;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;
import businesslogic.financebl.controller.BusinessStatementReceiptBLController;

public class CheckOperatingPanel extends JPanel {
	
	private static final long serialVersionUID = 69L;
	
	private BusinessStatementReceiptBLController businessController;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
//	private int pageNum;	//pageNum用来计表格的页数，从0开始计数
	
	private JLabel function;
	
	private JLabel dateRange;
	private JLabel startDateLabel;
	private JTextField startDateField;
	private JLabel startDateChooseLabel;
	private JLabel endDateLabel;
	private JTextField endDateField;
	private JLabel endDateChooseLabel;
	private JButton confirmDateButton;
	
	ArrayList<ArrayList<String>> arr; 
	private BusinessStatementModel businessModel;
	private JTable messageTable;
	private DefaultTableCellRenderer tcr;
	
	private JLabel previousPageLabel;
	private JLabel nextPageLabel;
	
	//private LocationHelper helper;

	public CheckOperatingPanel() {
		
		businessController = new BusinessStatementReceiptBLController();
		
		function = new JLabel("查看经营情况表");
		
		dateRange = new JLabel("日期范围");
		startDateLabel = new JLabel();
		startDateField = new JTextField("", 11);
		startDateField.setToolTipText("例:2015-12-08");
		startDateChooseLabel = new JLabel("开始日期");
		endDateLabel = new JLabel();
		endDateField = new JTextField("", 11);
		endDateField.setToolTipText("例:2015-12-08");
		endDateChooseLabel = new JLabel("结束日期");
		confirmDateButton = new JButton("确认");
		
		arr = new ArrayList<ArrayList<String>>();
		businessModel = new BusinessStatementModel(arr);
		messageTable = new JTable(businessModel);
		
		previousPageLabel = new JLabel("上一页");
		nextPageLabel = new JLabel("下一页");

		
		//加监听
		startDateLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				startui();
			}
		});

		endDateLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				endui();
			}
		});

		confirmDateButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				confirmUI();
			}
		});

		previousPageLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		nextPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
			}
		});
		
		
		//把组件加到Panel上
		add(function);
		
		add(dateRange);
		add(startDateLabel);
		add(startDateField);
		add(startDateChooseLabel);
		add(endDateLabel);
		add(endDateField);
		add(endDateChooseLabel);
		add(confirmDateButton);
		
		add(messageTable);
		add(messageTable.getTableHeader());
		
		add(previousPageLabel);
		add(nextPageLabel);
		
		setLayout(null);
		
		//颜色、边界、位置、JTable上信息显示设置
        setTableColor();
		setBorder();
        setCmpLocation();
        setBaseInfo();
	}

	
	//给表加颜色，隔一行一个颜色
	public void setTableColor(){
			
		tcr = new DefaultTableCellRenderer(){
		
			private static final long serialVersionUID = 6L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);
	
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};
	}
	
	//设置边界
	public void setBorder(){
		messageTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		startDateLabel.setLayout(new BorderLayout());
		startDateLabel.add(new DateChooser(startDateField),BorderLayout.CENTER);
		endDateLabel.setLayout(new BorderLayout());
		endDateLabel.add(new DateChooser(endDateField),BorderLayout.CENTER);
	}
	
	public void setCmpLocation(){
		
		function.setBounds((int)(PANEL_WIDTH * 0.5420918367346939/25),(int)(PANEL_HEIGHT * 0.43052837573385516/20),
				(int)(PANEL_WIDTH *  6.919642857142857 /25),(int)(PANEL_HEIGHT *  1.6046966731898238/20));
		
		dateRange.setBounds((int)(PANEL_WIDTH * 1.4987244897959184/25),(int)(PANEL_HEIGHT * 2.544031311154599/20),
				(int)(PANEL_WIDTH *  3.1568877551020407 /25),(int)(PANEL_HEIGHT *  1.2524461839530332/20));
		startDateLabel.setBounds((int)(PANEL_WIDTH * 8.896683673469388/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
				(int)(PANEL_WIDTH *  0.9247448979591837 /25),(int)(PANEL_HEIGHT *  1.1741682974559686/20));
        endDateLabel.setBounds((int)(PANEL_WIDTH * 14.572704081632653/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
        		(int)(PANEL_WIDTH *  1.052295918367347 /25),(int)(PANEL_HEIGHT *  1.2524461839530332/20));
        startDateField.setBounds((int)(PANEL_WIDTH * 5.0063775510204085/25),(int)(PANEL_HEIGHT * 2.544031311154599/20),
				(int)(PANEL_WIDTH *  3.1568877551020407 /25),(int)(PANEL_HEIGHT *  1.1741682974559686/20));
		endDateField.setBounds((int)(PANEL_WIDTH * 10.809948979591837/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
				(int)(PANEL_WIDTH *  3.2206632653061225 /25),(int)(PANEL_HEIGHT *  1.2915851272015655/20));
		
		confirmDateButton.setBounds((int)(PANEL_WIDTH * 19.6109693877551/25),(int)(PANEL_HEIGHT * 2.5048923679060664/20),
				(int)(PANEL_WIDTH *  2.1683673469387754 /25),(int)(PANEL_HEIGHT *  1.1350293542074363/20));
		
		messageTable.getTableHeader().setBounds((int)(PANEL_WIDTH * 1.4987244897959184/25),(int)(PANEL_HEIGHT * 4.148727984344423/20),
				(int)(PANEL_WIDTH *  21.0140306122449 /25),(int)(PANEL_HEIGHT *  1.3424657534246576/20));
		messageTable.setBounds((int)(PANEL_WIDTH * 1.4987244897959184/25),(int)(PANEL_HEIGHT * 4.148727984344423/20)+(int)(PANEL_HEIGHT *  1.3424657534246576/20),
				(int)(PANEL_WIDTH *  21.0140306122449 /25),(int)(PANEL_HEIGHT *  13.424657534246576/20)-(int)(PANEL_HEIGHT *  1.3424657534246576/20));
		
		nextPageLabel.setBounds((int)(PANEL_WIDTH * 21.237244897959183/25),(int)(PANEL_HEIGHT * 18.238747553816047/20),
				(int)(PANEL_WIDTH *  1.0841836734693877 /25),(int)(PANEL_HEIGHT *  1.2915851272015655/20));
		previousPageLabel.setBounds((int)(PANEL_WIDTH * 22.5765306122449/25),(int)(PANEL_HEIGHT * 18.199608610567516/20),
				(int)(PANEL_WIDTH *  1.052295918367347 /25),(int)(PANEL_HEIGHT *  1.2915851272015655/20));
		
	}
	
	private void setBaseInfo(){

		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);
		TableColumn column3 = messageTable.getColumnModel().getColumn(2);
		TableColumn column4 = messageTable.getColumnModel().getColumn(3);
		
		// 设置宽度
		column1.setPreferredWidth(messageTable.getWidth() * 4 / 10);
		column2.setPreferredWidth(messageTable.getWidth() * 2/ 10);
		column3.setPreferredWidth(messageTable.getWidth() * 2 / 10);
		column4.setPreferredWidth(messageTable.getWidth() * 2 / 10);

		messageTable.setRowHeight((messageTable.getHeight() - messageTable.getTableHeader().getHeight()) / 10);

		tcr.setHorizontalAlignment(JLabel.CENTER);
		
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);
	}
	
	public void setList(ArrayList<CollectionReceiptVO> collectionReceiptList,ArrayList<PaymentReceiptVO> paymentReceiptList){
		
//		info.setList(collectionReceiptList, paymentReceiptList);
	}

	public void startui(){
		
	}

	public void endui(){

	}

	public void confirmUI(){
		
		String beginDate = startDateField.getText();
		String endDate = endDateField.getText();
		
		if(beginDate.equals("")||endDate.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示", JOptionPane.CLOSED_OPTION);
		}
		else{
			beginDate = beginDate.substring(0, 4) + beginDate.substring(5,7) + beginDate.substring(8);
			endDate = endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8);
			
		BusinessStatementReceiptVO vo = businessController.showBSList(beginDate, endDate);
		ArrayList<CollectionReceiptVO> cvos=vo.cvos;
		ArrayList<PaymentReceiptVO> pvos=vo.pvos;
		
		int temp = arr.size();
		refreshTable(cvos, pvos);
		
		businessModel = new BusinessStatementModel(arr);
		
		for(int i=0;i<temp;i++){
			businessModel.removeRow(0);
		}
		messageTable.repaint();		
		}
	}

	public void refreshTable(ArrayList<CollectionReceiptVO> cvos, ArrayList<PaymentReceiptVO> pvos){
		 for(CollectionReceiptVO v1: cvos){
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(v1.getID());
			lineInfo.add(v1.getDate());
			lineInfo.add(v1.getIncome()+"");
			lineInfo.add(v1.getUserID());
			arr.add(lineInfo);
		 }
		 for(PaymentReceiptVO v2: pvos){
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(v2.getID());
			lineInfo.add(v2.getDate());
			lineInfo.add(v2.getCost()+"");
			lineInfo.add(v2.getUserID());
			arr.add(lineInfo);
		 }
	 }
	
	
	class BusinessStatementModel extends AbstractTableModel{
			
		private static final long serialVersionUID = 1L;
		
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		
		String head[] = {"编号","日期","金额","操作人"};
			
		public BusinessStatementModel(ArrayList<ArrayList<String>> content) {
			arr = content;
		}
		 
		public int getRowCount(){
			return arr.size();
		}
	
		public int getColumnCount(){
			return head.length;
		}
		
		public String getValueAt(int row, int col){
			return arr.get(row).get(col);
		}

		public String getColumnName(int col){
			return head[col];
		}

		public void addRow(ArrayList<String> strArr){
			arr.add(strArr);
		}

		public void removeRow(int row) {
			arr.remove(row);
		}
			
	}
	
	 
}
