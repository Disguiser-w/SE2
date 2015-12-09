package presentation.financeui;

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

import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import presentation.commonui.DateChooser;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;

public class BusinessStateReceiptPanel extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JButton startDateButton;
	private JLabel startDateLabel;
//	private JButton endDateButton;
	private JLabel endDateLabel;
	private JButton dateOKButton;
	private JButton printButton;
	private JButton sendButton;
	private JButton next;
	private JButton previous;

	private JLabel function;
	private JLabel dateRange;

	private JTextField startDate_Input;
	private JTextField endDate_Input;
	
	private JTable table;

	private BusinessStatementModel bm;
	ArrayList<ArrayList<String>> c=new ArrayList<ArrayList<String>>();

	public BusinessStatementReceiptBLController controller;
	public CollectionReceiptBLController collectionController;
	public PaymentReceiptBLController paymentController;

//	private LocationHelper helper;


	public BusinessStateReceiptPanel(BusinessStatementReceiptBLController controller) {
		this.controller=controller;
		
//		startDateButton = new JButton("start");
		startDateLabel = new JLabel("开始日期");
//		endDateButton = new JButton("end");
		endDateLabel =new JLabel("结束日期");
		dateOKButton = new JButton("ok");
		printButton = new JButton("print");
		sendButton = new JButton("send");
		next = new JButton("next");
		previous = new JButton("previous");

		function = new JLabel("经营情况表");
		dateRange = new JLabel("日期范围");

		startDate_Input = new JTextField("", 11);
		endDate_Input = new JTextField("", 11);

		//提示框
		startDate_Input.setToolTipText("例:2015-12-08");
//		info = new BusinessStateReceiptInfoTable(13, 4);
		
		//model
		bm=new BusinessStatementModel(c);
		//新建table
		table=new JTable(bm);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(table.getTableHeader());
		add(table);


//		setCmpLocation();

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

		dateOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dateOK();
			}
		});

		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				printui();
			}
		});

		sendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				sendui();
			}
		});

		next.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				nextui();
			}
		});

		previous.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				previousui();
			}
		});

		setLayout(null);

//		add(startDateButton);
		add(startDateLabel);
//		add(endDateButton);
		add(endDateLabel);
		add(dateOKButton);
		add(printButton);
		add(sendButton);
		add(next);
		add(previous);
		add(function);
		add(dateRange);
		add(startDate_Input);
		add(endDate_Input);
		
		add(table);
		startDateLabel.setLayout(new BorderLayout());
		startDateLabel.add(new DateChooser(startDate_Input),BorderLayout.CENTER);
		
		endDateLabel.setLayout(new BorderLayout());
		endDateLabel.add(new DateChooser(endDate_Input),BorderLayout.CENTER);
//		add(info);
		
//		helper = new LocationHelper(this);

	}



	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		
//		startDateButton.setBounds((int)(width * 8.896683673469388/25),(int)(height * 2.5048923679060664/20),(int)(width *  0.9247448979591837 /25),(int)(height *  1.1741682974559686/20));
        startDateLabel.setBounds((int)(width * 8.896683673469388/25),(int)(height * 2.5048923679060664/20),(int)(width *  0.9247448979591837 /25),(int)(height *  1.1741682974559686/20));
//		endDateButton.setBounds((int)(width * 14.572704081632653/25),(int)(height * 2.5048923679060664/20),(int)(width *  1.052295918367347 /25),(int)(height *  1.2524461839530332/20));
        endDateLabel.setBounds((int)(width * 14.572704081632653/25),(int)(height * 2.5048923679060664/20),(int)(width *  1.052295918367347 /25),(int)(height *  1.2524461839530332/20));
		dateOKButton.setBounds((int)(width * 19.6109693877551/25),(int)(height * 2.5048923679060664/20),(int)(width *  2.1683673469387754 /25),(int)(height *  1.1350293542074363/20));
		printButton.setBounds((int)(width * 19.54719387755102/25),(int)(height * 0.43052837573385516/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.5264187866927592/20));
		sendButton.setBounds((int)(width * 22.193877551020407/25),(int)(height * 0.3913894324853229/20),(int)(width *  2.232142857142857 /25),(int)(height *  1.5264187866927592/20));
		next.setBounds((int)(width * 21.237244897959183/25),(int)(height * 18.238747553816047/20),(int)(width *  1.0841836734693877 /25),(int)(height *  1.2915851272015655/20));
		previous.setBounds((int)(width * 22.5765306122449/25),(int)(height * 18.199608610567516/20),(int)(width *  1.052295918367347 /25),(int)(height *  1.2915851272015655/20));
		function.setBounds((int)(width * 0.5420918367346939/25),(int)(height * 0.43052837573385516/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.6046966731898238/20));
		dateRange.setBounds((int)(width * 1.4987244897959184/25),(int)(height * 2.544031311154599/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.2524461839530332/20));
		startDate_Input.setBounds((int)(width * 5.0063775510204085/25),(int)(height * 2.544031311154599/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.1741682974559686/20));
		endDate_Input.setBounds((int)(width * 10.809948979591837/25),(int)(height * 2.5048923679060664/20),(int)(width *  3.2206632653061225 /25),(int)(height *  1.2915851272015655/20));
		table.getTableHeader().setBounds((int)(width * 1.4987244897959184/25),(int)(height * 4.148727984344423/20),(int)(width *  21.0140306122449 /25),(int)(height *  1.3424657534246576/20));
		table.setBounds((int)(width * 1.4987244897959184/25),(int)(height * 4.148727984344423/20)+(int)(height *  1.3424657534246576/20),(int)(width *  21.0140306122449 /25),(int)(height *  13.424657534246576/20)-(int)(height *  1.3424657534246576/20));
		setBaseInfo();
		
	}
	
	private void setBaseInfo() {

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



		table.setRowHeight((table.getHeight() - table.getTableHeader().getHeight()) / 12);
		// tablePanel.setSize(tablePanel.getWidth(), h * 8 +
		// messageTable.getTableHeader().getHeight() + 4);

		//
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			/**
			 * 
			 */
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

	public void setList(ArrayList<CollectionReceiptVO> collectionReceiptList,
			ArrayList<PaymentReceiptVO> paymentReceiptList) {
		
//		info.setList(collectionReceiptList, paymentReceiptList);
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
		else{
			beginTime=beginTime.substring(0, 4)+beginTime.substring(5,7)+beginTime.substring(8);
			endTime=endTime.substring(0, 4)+endTime.substring(5, 7)+endTime.substring(8);
		BusinessStatementReceiptVO vo=controller.showBSList(beginTime, endTime);
		ArrayList<CollectionReceiptVO> cvos=vo.cvos;
		ArrayList<PaymentReceiptVO> pvos=vo.pvos;
		int temp=c.size();
		refreshTable(cvos, pvos);
		bm=new BusinessStatementModel(c);
		for(int i=0;i<temp;i++){
			bm.removeRow(0);
		}
		table.repaint();		
		}
	}

	public void printui() {

	}

	public void sendui() {

	}

	public void nextui() {

	}

	public void previousui() {

	}
	
class BusinessStatementModel extends AbstractTableModel{

		
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

			return c.size();
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
//			System.out.println(head.length);
			return head.length;
		}
		
		public String getValueAt(int row, int col) {
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

 public void refreshTable(ArrayList<CollectionReceiptVO> cvos,ArrayList<PaymentReceiptVO> pvos){
	 for(CollectionReceiptVO v1:cvos){
		ArrayList<String> lineInfo=new ArrayList<String>();
		lineInfo.add(v1.getID());
		lineInfo.add(v1.getDate());
		lineInfo.add(v1.getIncome()+"");
		lineInfo.add(v1.getUserID());
		c.add(lineInfo);
	 }
	 for(PaymentReceiptVO v2:pvos){
			ArrayList<String> lineInfo=new ArrayList<String>();
			lineInfo.add(v2.getID());
			lineInfo.add(v2.getDate());
			lineInfo.add(v2.getCost()+"");
			lineInfo.add(v2.getUserID());
			c.add(lineInfo);
		 }
 }

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