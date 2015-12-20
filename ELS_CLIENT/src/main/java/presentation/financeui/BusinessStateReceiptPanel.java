package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import businesslogic.receiptbl.getDate;
import presentation.commonui.DateChooser;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;

public class BusinessStateReceiptPanel extends OperationPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JLabel dateOKLabel;
	private JLabel printLabel;
	private JLabel sendLabel;
	private JLabel next;
	private JLabel previous;

	private JLabel function;
	private JLabel dateRange;

	private JTextField startDate_Input;
	private JTextField endDate_Input;

	private MyTable BSLTable;
	
	private ArrayList<CollectionReceiptVO> collectionReceiptVOs;
	private ArrayList<PaymentReceiptVO> paymentReceiptVOs;

	public BusinessStatementReceiptBLController controller;


	public BusinessStateReceiptPanel(BusinessStatementReceiptBLController controller) {
		this.controller=controller;
		
		startDateLabel = new JLabel("开始日期");
		endDateLabel =new JLabel("结束日期");
		dateOKLabel = new JLabel("确认");
		printLabel = new JLabel("打印");
		sendLabel = new JLabel("发送");
		next = new JLabel(">");
		previous = new JLabel("<");

		function = new JLabel("经营情况表");
		dateRange = new JLabel("日期范围");

		startDate_Input = new JTextField("", 11);
		endDate_Input = new JTextField("", 11);

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

		sendLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				sendui();
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
		add(sendLabel);
		add(next);
		add(previous);
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
        dateOKLabel.setBounds((int)(width * 19.6109693877551/25),(int)(height * 2.5048923679060664/20),(int)(width *  2.1683673469387754 /25),(int)(height *  1.1350293542074363/20));
		printLabel.setBounds((int)(width * 19.54719387755102/25),(int)(height * 0.43052837573385516/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.5264187866927592/20));
		sendLabel.setBounds((int)(width * 22.193877551020407/25),(int)(height * 0.3913894324853229/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.5264187866927592/20));
		previous.setBounds((int)(width * 21.237244897959183/25),(int)(height * 18.238747553816047/20),(int)(width *  1.0841836734693877 /25),(int)(height *  1.2915851272015655/20));
		next.setBounds((int)(width * 22.5765306122449/25),(int)(height * 18.199608610567516/20),(int)(width *  1.052295918367347 /25),(int)(height *  1.2915851272015655/20));
		function.setBounds((int)(width * 0.5420918367346939/25),(int)(height * 0.43052837573385516/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.6046966731898238/20));
		dateRange.setBounds((int)(width * 1.4987244897959184/25),(int)(height * 2.544031311154599/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.2524461839530332/20));
		startDate_Input.setBounds((int)(width * 5.0063775510204085/25),(int)(height * 2.544031311154599/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.1741682974559686/20));
		endDate_Input.setBounds((int)(width * 10.809948979591837/25),(int)(height * 2.5048923679060664/20),(int)(width *  3.2206632653061225 /25),(int)(height *  1.2915851272015655/20));
		BSLTable.setLocationAndSize((int)(width * 1.0987244897959184/25),(int)(height * 4.048727984344423/20),(int)(width *  23.0140306122449 /25),(int)(height *  13.424657534246576/20));		
	}
	private void setBaseInfo(){
		String[] head = new String[]{"编号","日期","金额","操作人"};
		int[] widths = new int[]{150,150,150,150};
		
		BSLTable = new MyTable(head, getInfos(collectionReceiptVOs,paymentReceiptVOs), widths, false);
		add(BSLTable);
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
	
/*	private void setBaseInfo() {

		// 设置成不可编辑不可改变位置，大小
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = table.getColumnModel().getColumn(0);
		TableColumn column2 = table.getColumnModel().getColumn(1);
		TableColumn column3 = table.getColumnModel().getColumn(2);
		TableColumn column4 = table.getColumnModel().getColumn(3);
		// 设置宽度
		column1.setPreferredWidth(table.getWidth() * 4 / 10);
		column2.setPreferredWidth(table.getWidth() * 2/ 10);
		column3.setPreferredWidth(table.getWidth() * 2 / 10);
		column4.setPreferredWidth(table.getWidth() * 2 / 10);



		table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 8);
		// tablePanel.setSize(tablePanel.getWidth(), h * 8 +
		// messageTable.getTableHeader().getHeight() + 4);

		//
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		tcr.setHorizontalAlignment(JLabel.CENTER);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);

	}
	*/

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
		else{
			beginTime=beginTime.substring(0, 4)+beginTime.substring(5,7)+beginTime.substring(8);
			endTime=endTime.substring(0, 4)+endTime.substring(5, 7)+endTime.substring(8);
		BusinessStatementReceiptVO vo=controller.showBSList(beginTime, endTime);
		collectionReceiptVOs=vo.cvos;
		paymentReceiptVOs=vo.pvos;
		BSLTable.setInfos(getInfos(collectionReceiptVOs, paymentReceiptVOs));
		}
	}

	public void printui() {

	}

	public void sendui() {
		

	}

/*class BusinessStatementModel extends AbstractTableModel{

		
		private static final long serialVersionUID = 1L;
		ArrayList<ArrayList<String>> c = new ArrayList<ArrayList<String>>();
		//操作人还要吗
		String head[]={"编号","日期","金额","操作人"};
		
	 public BusinessStatementModel(ArrayList<ArrayList<String>> content) {
			c=content;
		}
		//行数
		public int getRowCount() {
			// TODO Auto-generated method stub

			return 9;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
//			System.out.println(head.length);
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
			if(row>c.size()-1){
				return null;
			}
			return c.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}

		public void addRow(ArrayList<String> v) {

			c.add(v);
		}

		public void removeRow(int row) {
			c.remove(row);
		}
		
	}
	*/

/* public void refreshTable(ArrayList<CollectionReceiptVO> cvos,ArrayList<PaymentReceiptVO> pvos){
	 for(CollectionReceiptVO v1:cvos){
		ArrayList<String> lineInfo=new ArrayList<String>();
		lineInfo.add(v1.ID);
		lineInfo.add(v1.date);
		lineInfo.add(v1.totalMoney+"");
		lineInfo.add(v1.userID);
		c.add(lineInfo);
	 }
	 for(PaymentReceiptVO v2:pvos){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(v2.ID);
			lineInfo.add(v2.date);
			lineInfo.add(v2.cost+"");
			lineInfo.add(v2.userID);
			c.add(lineInfo);
		 }
 }
 
 public ArrayList<ArrayList<String>>  getStringOnthisPage(int num){
	 ArrayList<ArrayList<String>> all = c;
	 ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
	 if(all.size()<=9*num||num<0){
		 return null;
	 }
	 else{
		 for(int i =9*num;i<=9*num+8;i++){
			 if(all.size()>i){
				 temp.add(all.get(i));
			 }
		 }
	 }
	 return temp;
 }
 */
  


//	public static void main(String[] args) {
//		
//		BusinessStatementReceiptBLController controller = null;
//		JFrame frame = new JFrame();
//		frame.setSize(800, 550);
//		frame.add(new BusinessStateReceiptPanel(controller));
//		frame.setVisible(true);
//	}
 
 
 /*	public void setCmpLocation() {
	function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
			PANEL_WIDTH * 5 / 18, PANEL_HEIGHT / 12);
	printButton.setBounds(PANEL_WIDTH * 7 / 9, PANEL_HEIGHT / 24,
			PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
	sendButton.setBounds(PANEL_WIDTH * 8 / 9, PANEL_HEIGHT / 24,
			PANEL_WIDTH / 18, PANEL_HEIGHT / 12);
	dateRange.setBounds(PANEL_WIDTH / 4, PANEL_HEIGHT * 3 / 16,
			PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
	startDateButton.setBounds(PANEL_WIDTH * 19 / 36, PANEL_HEIGHT * 3 / 16,
			PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
	startDate_Input.setBounds(PANEL_WIDTH * 7 / 18, PANEL_HEIGHT * 3 / 16,
			PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
	endDateButton.setBounds(PANEL_WIDTH * 13 / 18, PANEL_HEIGHT * 3 / 16,
			PANEL_WIDTH / 36, PANEL_HEIGHT / 24);
	endDate_Input.setBounds(PANEL_WIDTH * 7 / 12, PANEL_HEIGHT * 3 / 16,
			PANEL_WIDTH / 9, PANEL_HEIGHT / 24);
	next.setBounds(PANEL_WIDTH * 61 / 72, PANEL_HEIGHT * 45 / 48,
			PANEL_WIDTH / 24, PANEL_HEIGHT / 24);
	previous.setBounds(PANEL_WIDTH * 65 / 72, PANEL_HEIGHT * 45 / 48,
			PANEL_WIDTH / 24, PANEL_HEIGHT / 24);

//	info.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 4 / 15,
//			PANEL_WIDTH * 5 / 6, PANEL_HEIGHT * 13 / 20);
}
*/
}